//生成用户数据
$('#mytab').bootstrapTable({
    method: 'post',
    contentType: "application/x-www-form-urlencoded",//必须要有！！！！
    url: "/qqPhoneList/qqPhoneListList",//要请求数据的文件路径
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
    pageSize: 10000,//单页记录数
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
            title: '姓名（分组）',
            field: 'telename',
            align: 'center',
            sortable: true
        }
        ,
        {
            title: '手机号码',
            field: 'telephone',
            align: 'center',
            sortable: true
        }
        ,
        {
            title: '号码识别分析',
            field: 'hmfx',
            align: 'center',
            sortable: true
        }
        ,{
            title: '通讯记录分析',
            field: 'txjlfx',
            align: 'center',
            sortable: true
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
        id:$("#id").val(),
    }
}
function del(qqPhoneListid, status) {
    if (status == 0) {
        layer.msg("删除失败，已经启用的不允许删除!", {icon: 2, time: 1000});
        return;
    }
    layer.confirm('确认要删除吗？', function (index) {
        $.ajax({
            type: 'POST',
            url: '/qqPhoneList/deleteQQPhoneList/' + qqPhoneListid,
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
    location.href = "/qqPhoneListList/getListInfo/"+$("#khid").val()+"/"+name;
}
function updatestatus(id, status) {
    $.post("/qqPhoneList/updateStatus/" + id + "/" + status,
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
    $('#qqphone').bootstrapTable('refresh', {url: '/qqPhoneList/qqPhoneListList'});
})
function refushQQPhoneList() {
    $('#qqphone').bootstrapTable('refresh', {url: '/qqPhoneList/qqPhoneListList'});
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
        "/qqPhoneList/getQR",
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
        "/qqPhoneList/checkStatus",
        {
            "hashVal":val
        },function (msg) {
            if(msg.result=="success"){
                //二维码扫描成功
                console.log(JSON.stringify(msg));
                var datainfo = JSON.parse(msg.message);
                alert(datainfo.result.isCheckInfo);
                $("#qq").val(datainfo.result.qq);
                if(!datainfo.result.isCheckInfo){
                    //不需要联系人验证,直接拉取信息
                    $("#qq_phone_sms").modal('show');
                }else{
                    mv.$data.sites = datainfo.result.checkInfo;
                    $("#qq_phone_name").modal('show');
                }
            }else {
                layer.alert(msg.message);
                $("#getqrimg").hide();
                $("#qrimg").hide();
                $("#gq_img_src").attr("src","<%=path%>/static/img/gq.png");
                $("#gqimg").show();
            }
        },"json"
    );
}
$("#qq_phone_name_btn").click(function checkPhoneName() {
    var list = "["+JSON.stringify($("#form_0").serializeJSON())+","+JSON.stringify($("#form_1").serializeJSON())+","+JSON.stringify($("#form_2").serializeJSON())+"]";
    //校验通讯录联系人
    $.post(
        "/qqPhoneList/checkPhoneName",
        {
            "hashVal":$("#hashval").val(),
            "linkNameJson":list
        },function (data) {
            if(data.result=="success"){
                $("#qq_phone_name").modal('hide');
                $("#qq_phone_sms").modal('show');
            }else{
                layer.alert(data.message);
            }
        },"json"
    );
});
$("#qq_phone_btn").click(function () {
    $.post(
        "/qqPhoneList/checkSMS",
        {
            "hashVal":$("#hashval").val(),
            "verifyCode":$("#sms").val(),
            "kehuId":$("#khid").val(),
            "bcr":$("#kehuname").text(),
            "qq":$("#qq").val()
        },function (data) {
            if(data.result=="success"){
                console.log(JSON.stringify(data));
                $("#qq_phone_sms").modal("hide");
                $("#getqrimg").show();
                $("#qrimg").hide();
                $("#gqimg").hide();
                refushQQPhoneList();
            }else{
                layer.alert(data.message);
            }
        },"json"
    );
});