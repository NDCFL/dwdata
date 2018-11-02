//生成用户数据
$('#qqphone').bootstrapTable({
    method: 'post',
    contentType: "application/x-www-form-urlencoded",//必须要有！！！！
    url: "/qqPhone/qqPhoneList",//要请求数据的文件路径
    toolbar: '#toolbar',//指定工具栏
    striped: true, //是否显示行间隔色
    dataField: "res",
    sortable: true, //是否启用排序 sortOrder: "ID asc",
    sortOrder: "ID asc",
    pagination: true,//是否分页
    queryParamsType: 'limit',//查询参数组织方式
    queryParams: queryParams,//请求服务器时所传的参数
    sidePagination: 'server',//指定服务器端分页
    pageNumber: 1, //初始化加载第一页，默认第一页
    pageSize: 10,//单页记录数
    pageList: [10, 20, 30, 40, 50, 60, 70, 80, 90, 100],//分页步进值
    showRefresh: true,//刷新按钮
    showColumns: true,
    clickToSelect: true,//是否启用点击选中行
    toolbarAlign: 'right',//工具栏对齐方式
    buttonsAlign: 'right',//按钮对齐方式
    toolbar: '#toolbar', search: true,
    uniqueId: "id",                     //每一行的唯一标识，一般为主键列
    showExport: true,
    exportDataType: 'all',
    columns: [
        {
            title: '查询时间',
            field: 'createTime',
            align: 'center',
            sortable: true,
            formatter: function (value) {
                var date = new Date(value);
                var y = date.getFullYear();
                var m = date.getMonth() + 1;
                var d = date.getDate();
                var h = date.getHours();
                var mi = date.getMinutes();
                var ss = date.getSeconds();
                return '<span style="color: #000">'+y + "年" + m + "月" + d + "日" + h + ":" + mi+'</span> ';
            }
        },
        {
            title: '被查人',
            field: 'bcr',
            align: 'center',
            sortable: true,
            formatter: function (value) {
                return '<span style="color: #000">'+value+'</span> ';
            }
        }
        ,
        {
            title: '被查人QQ号',
            field: 'qq',
            align: 'center',
            sortable: true,
            formatter: function (value) {
                return '<span style="color: #000">'+value+'</span> ';
            }
        }
        ,
        {
            title: '查询状态',
            field: 'isActive',
            align: 'center',
            formatter: function (value, row, index) {
                if (value == 1) {
                    //表示启用状态
                    return '<span style="color: green">采集中</span>';
                } else if(value ==0){
                    //表示启用状态
                    return '<span style="color: #000">采集完成</span>';
                }else{
                    //表示启用状态
                    return '<span style="color: #000">采集失败</span>';
                }
            }
        }
        ,
        {
            title: '查询结果',
            align: 'center',
            field: '',
            formatter: function (value, row, index) {
                var e = '<a title="点击查看详情" onclick="edit('+row.id+')" >点击查看详情</a> ';
                if(row.isActive==0){
                    return e;
                }
            }
        }
    ],
    locale: 'zh-CN',//中文支持,
    responseHandler: function (res) {
        if (res) {
            return {
                "res": res.rows,
                "total": res.total
            };
        } else {
            return {
                "rows": [],
                "total": 0
            };
        }
    }
})

//请求服务数据时所传参数
function queryParams(params) {
    var title = "";
    $(".search input").each(function () {
        title = $(this).val();
    });
    return {
        //每页多少条数据
        pageSize: this.pageSize,
        //请求第几页
        pageIndex: this.pageNumber,
        searchVal: title,
        kehuId:$("#khid").val()
    }
}
function del(qqPhoneid, status) {
    if (status == 0) {
        layer.msg("删除失败，已经启用的不允许删除!", {icon: 2, time: 1000});
        return;
    }
    layer.confirm('确认要删除吗？', function (index) {
        $.ajax({
            type: 'POST',
            url: '/qqPhone/deleteQQPhone/' + qqPhoneid,
            dataType: 'json',
            success: function (data) {
                if (data.message == '删除成功!') {
                    layer.alert(data.message, {icon: 6});
                } else {
                    layer.alert(data.message, {icon: 6});
                }
                refushPhone();
            },
            error: function (data) {
                console.log(data.msg);
            },
        });
    });
}
function edit(name) {
    location.href = "/qqPhoneList/getListInfo/"+$("#khid").val()+"/"+name;
}
function updatestatus(id, status) {
    $.post("/qqPhone/updateStatus/" + id + "/" + status,
        function (data) {
            if(status==0){
                if(data.message=="ok"){
                    layer.alert("已启用", {icon:6});
                }else{
                    layer.alert("操作失败", {icon:6});
                }
            }else{
                if(data.message=="ok"){
                    layer.alert("已停用", {icon:5});
                }else{
                    layer.alert("操作失败", {icon:5});
                }
            }
            refushPhone();
        },
        "json"
    );
}
//查询按钮事件
$('#search_btn').click(function () {
    $('#qqphone').bootstrapTable('refresh', {url: '/qqPhone/qqPhoneList'});
})
function refushQQPhone() {
    $('#qqphone').bootstrapTable('refresh', {url: '/qqPhone/qqPhoneList'});
}
$("#getqrimg").click(function () {
    layer.msg("正在获取二维码");
    getQR();
});
$("#gq_img_src").click(function () {
    layer.msg("二维码刷新中");
    getQR();
});
function getQR() {
    $.post(
        "/qqPhone/getQR",
        function (data) {
            if(data.result=="success"){
                var datainfo = new Array();
                datainfo = data.message.toString().split("++");
                $("#img_src").attr("src",datainfo[0]);
                $("#hashval").val(datainfo[1]);
                $("#getqrimg").hide();
                $("#gqimg").hide();
                $("#qrimg").show();
                getTimes(datainfo[1]);
            }
        },"json"
    );
}
function getTimes(val) {
    $.post(
        "/qqPhone/checkStatus",
        {
            "hashVal":val
        },function (msg) {
            if(msg.result=="success"){
                //二维码扫描成功
                console.log(JSON.stringify(msg));
                var datainfo = JSON.parse(msg.message);
                layer.msg("二维码扫描成功");
                $("#qq").val(datainfo.result.qq);
                if(!datainfo.result.isCheckInfo){
                    //不需要联系人验证,直接拉取信息
                    $("#qq_phone_sms").modal('show');
                    $('#qq_phone_sms').modal({backdrop: 'static', keyboard: false});
                }else{
                    mv.$data.sites = datainfo.result.checkInfo;
                    $("#qq_phone_name").modal('show');
                }
            }else {
                layer.alert(msg.message);
                $("#getqrimg").hide();
                $("#qrimg").hide();
                $("#gq_img_src").attr("src","/static/img/gq.png");
                $("#gqimg").show();
            }
        },"json"
    );
}
$("#qq_phone_name_btn").click(function checkPhoneName() {
    layer.msg("联系人校验中，请等待");
    var index = layer.load(1, {
        shade: [0.1,'#fff'] //0.1透明度的白色背景
    });
    var list = "["+JSON.stringify($("#form_0").serializeJSON())+","+JSON.stringify($("#form_1").serializeJSON())+","+JSON.stringify($("#form_2").serializeJSON())+"]";
    //校验通讯录联系人
    $.post(
        "/qqPhone/checkPhoneName",
        {
            "hashVal":$("#hashval").val(),
            "linkNameJson":list
        },function (data) {
            console.log(data);
            layer.close(index);
            if(data.result=="success"){
                var datainfo = JSON.parse(data.message);
                $("#qq").val(datainfo.result.qq);
                $("#qq_phone_name").modal('hide');
                $("input[name='input_value']").val("");
                $("#qq_phone_sms").modal('show');
            }else{
                layer.alert(data.message);
            }
        },"json"
    );
});
$("#qq_phone_btn").click(function () {
    layer.msg("短信校验中，请等待");
    $.post(
        "/qqPhone/checkSMS",
        {
            "hashVal":$("#hashval").val(),
            "verifyCode":$("#sms").val(),
            "kehuId":$("#khid").val(),
            "bcr":$("#kehuname").text(),
            "qq":$("#qq").val()
        },function (data) {
            if(data.result=="success"){
                layer.alert("正在采集中，采集完成后即可查看");
                console.log(JSON.stringify(data));
                $("#qq_phone_sms").modal("hide");
                $("#getqrimg").show();
                $("#qrimg").hide();
                $("#gqimg").hide();
                $("#sms").val("");
                refushQQPhone();
            }else{
                layer.alert(data.message);
            }
        },"json"
    );
});