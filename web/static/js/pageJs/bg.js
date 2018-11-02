//生成用户数据
$('#mytab').bootstrapTable({
    method: 'post',
    contentType: "application/x-www-form-urlencoded",//必须要有！！！！
    url: "/carrieroperator/carrieroperatorList",//要请求数据的文件路径
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
                return '<span style="color: #000">'+y + "年" + (parseInt(m)<10?"0"+m:m) + "月" + (parseInt(d)<10?"0"+d:d) + "日" + (parseInt(h)<10?"0"+h:h) + ":" + (parseInt(mi)<10?"0"+mi:mi)+'</span> ';
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
            title: '被查人手机号',
            field: 'bcrPhone',
            align: 'center',
            sortable: true,
            formatter: function (value) {
                return '<span style="color: #000">'+value+'</span> ';
            }
        }
        ,
        {
            title: '当前状态',
            field: 'isActive',
            align: 'center',
            formatter: function (value, row, index) {
                if (value == 0) {
                    //表示启用状态
                    return '<span style="color: #000">采集成功</span>';
                } else if(value==2){
                    //表示启用状态
                    return '<span style="color: #000">采集失败</span>';
                }else{
                    //表示启用状态
                    return '<span style="color: green">采集中</span>';
                }
            }
        }
        ,
        {
            title: '查询结果',
            align: 'center',
            field: '',
            formatter: function (value, row, index) {
                var e = '<a title="点击查看详情" href="'+row.url+'" target="_blank" id="carrieroperator" >点击查看详情</a> ';
                if(row.isActive==0){
                    return e ;
                }else{
                    return '<span></span>'
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
function del(carrieroperatorid, status) {
    if (status == 0) {
        layer.msg("删除失败，已经启用的不允许删除!", {icon: 2, time: 1000});
        return;
    }
    layer.confirm('确认要删除吗？', function (index) {
        $.ajax({
            type: 'POST',
            url: '/carrieroperator/deleteCarrieroperator/' + carrieroperatorid,
            dataType: 'json',
            success: function (data) {
                if (data.message == '删除成功!') {
                    layer.alert(data.message, {icon: 6});
                } else {
                    layer.alert(data.message, {icon: 6});
                }
                refush();
            },
            error: function (data) {
                console.log(data.msg);
            },
        });
    });
}
function edit(name) {
    $.post("/carrieroperator/findCarrieroperator/" + name,
        function (data) {
            $("#updateform").autofill(data);
        },
        "json"
    );
}
function updatestatus(id, status) {
    $.post("/carrieroperator/updateStatus/" + id + "/" + status,
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
            refush();
        },
        "json"
    );
}
//查询按钮事件
$('#search_btn').click(function () {
    $('#mytab').bootstrapTable('refresh', {url: '/carrieroperator/carrieroperatorList'});
})
function refush() {
    $('#mytab').bootstrapTable('refresh', {url: '/carrieroperator/carrieroperatorList'});
}
$("#update").click(function () {
    $.post(
        "/carrieroperator/carrieroperatorUpdateSave",
        $("#updateform").serialize(),
        function (data) {
            if (data.message == "修改成功!") {
                layer.alert(data.message, {icon: 6});
                refush();
            } else {
                layer.alert(data.message, {icon: 6});
                refush();
            }
        }, "json"
    );
});
$("#add").click(function () {
    $.post(
        "/carrieroperator/carrieroperatorAddSave",
        $("#formadd").serialize(),
        function (data) {
            if (data.message == "新增成功!") {
                layer.alert(data.message, {icon: 6});
                refush();
            } else {
                layer.alert(data.message, {icon: 6});
                refush();
            }
        }, "json"
    );
});


var countdown = 60;
var validCode = true;

function sendCode() {
    var phone = $("#phoneval").val();
    var money = $("#kehujifen").html();

    if (!phone) {
        layer.alert("手机号输入有误,请重试");
        return;
    }
    if(money<=0){
        layer.alert("积分不足，请先充值");
        return;
    }
    layer.msg("短信发送中，请注意查收...");
    $("#code").attr("disabled","disabled");
    $.post(
        "/carrieroperator/phoneCheck",
        {
            tel: phone
        }, function (data) {
            console.log(data);
            if (data.result == "success") {
                var reg = JSON.parse(data.message);
                $("#sid").val(reg.data.sid);
                layer.msg("短信发送成功");
                var time =120;
                if (validCode) {
                    validCode = false;
                    var t = setInterval(function () {
                        time--;
                        $("#code").val("重发" + time + "秒");
                        if (time == 0) {
                            $("#code").removeAttr("disabled");
                            clearInterval(t);
                            $("#code").val("重新获取");
                            validCode = true;
                        }
                    }, 1000)
                }
            }else{
                layer.alert(data.message);
                $("#code").removeAttr("disabled");
            }
        }, "json"
    );
}
function getBaoGao() {
    var sid = $("#sid").val();
    if (!sid) {
        layer.alert("短信验证码失败,请重试");
        return;
    }
    $("#btns").attr("disabled","disabled");
    var phone = $("#phoneval").val();
    var password = $("#passwordval").val();
    var code = $("#codeval").val();
    if(!password){
        layer.alert("服务密码不能为空");
        return;
    }
    if(!code){
        layer.alert("短信验证码不能为空");
        return;
    }
    var index = layer.load(1, {
        shade: [0.1,'#fff'] //0.1透明度的白色背景
    });
    try {
        $.post(
            "/carrieroperator/getLogin",
            {
                "tel": phone,
                "sid": sid,
                "password": password,
                "sms_code": $("#codeval").val(),
                "name":$("#khname").text(),
                "headImg":$("#headimg").attr("src"),
                "kehuId":$("#khid").val()
            }, function (data) {
                if (data.result == "success") {
                    $('#mytab').bootstrapTable('refresh', {url: '/carrieroperator/carrieroperatorList'});
                    layer.close(index);
                    layer.alert("正在采集中，采集完成后即可查看");
                    $("#phoneval").val("");
                    $("#passwordval").val("");
                    $("#codeval").val("");
                    $("#btns").removeAttr("disabled")
                    $("#code").val("获取验证码");
                    $("#code").removeAttr("disabled");
                    validCode = true;
                    // //调用采集
                    // $.post(
                    //     "/carrieroperator/success",
                    //     {
                    //         "id": data.message,
                    //         "sid": sid,
                    //         "headImg":$("#headimg").attr("src")
                    //     }, function (data) {
                    //         if (data.result == "success") {
                    //             $("#btns").removeAttr("disabled")
                    //             $("#code").val("获取验证码");
                    //             $("#code").removeAttr("disabled");
                    //             validCode = true;
                    //             $('#mytab').bootstrapTable('refresh', {url: '/carrieroperator/carrieroperatorList'});
                    //         } else {
                    //         }
                    //     }, "json"
                    // );
                } else {
                    //如果返回的是字符则说明是报错了
                    layer.close(index);
                    if(isNaN(data.message)){
                        $('#mytab').bootstrapTable('refresh', {url: '/carrieroperator/carrieroperatorList'});
                        layer.alert(data.message);
                        $("#btns").removeAttr("disabled")
                    }else{
                        $("#bgid").val(data.message);
                        $("#check_two").modal('show');
                        $('#check_two').modal({backdrop: 'static', keyboard: false});
                    }
                }
            }, "json"
        );
    } catch (err) {
        //在这里处理错误
        console.log(JSON.stringify(err.c));
    }
}
$("#checkTo").click(function () {
    var sid = $("#sid").val();
    if (!sid) {
        layer.alert("短信验证失败,请重试");
        return;
    }
    $.post(
        "/carrieroperator/checkTwoLogin",
        {
            "sid": sid,
            "sms_code": $("#twocode").val(),
            "headImg":$("#headimg").attr("src"),
            "id":$("#bgid").val()
        }, function (data) {
            if (data.result == "success") {
                $('#mytab').bootstrapTable('refresh', {url: '/carrieroperator/carrieroperatorList'});
                layer.alert("正在采集中，采集完成后即可查看");
                $("#check_two").modal('hide');
                //$('#check_two').modal({backdrop: 'static', keyboard: false});
                // //调用采集
                // $.post(
                //     "/carrieroperator/success",
                //     {
                //         "id": $("#bgid").val(),
                //         "sid": sid,
                //         "headImg":$("#headimg").attr("src")
                //     }, function (data) {
                //         refush();
                //     }, "json"
                // );
            } else {
                layer.alert(data.message);
                $("#twocode").val("");
            }
        }, "json"
    );
});
