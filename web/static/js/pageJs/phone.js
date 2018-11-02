//生成用户数据
$('#mytab2').bootstrapTable({
    method: 'post',
    contentType: "application/x-www-form-urlencoded",//必须要有！！！！
    url: "/phone/phoneList",//要请求数据的文件路径
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
            title: '状态',
            field: 'isActive',
            align: 'center',
            formatter: function (value, row, index) {
                if (value == 0) {
                    //表示启用状态
                    return '<span style="color: #000">一致</span>';
                } else {
                    //表示启用状态
                    return '<span style="color: #000">不一致</span>';
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
                return e;
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
function del(phoneid, status) {
    if (status == 0) {
        layer.msg("删除失败，已经启用的不允许删除!", {icon: 2, time: 1000});
        return;
    }
    layer.confirm('确认要删除吗？', function (index) {
        $.ajax({
            type: 'POST',
            url: '/phone/deletePolice/' + phoneid,
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
    $.post("/phone/findFindPhone/" + name,
        function (msg) {
            if(msg.isActive==0){
                //查询一
                $("#phone_name_").html(msg.bcr);
                $("#phone_phone_").html(msg.bcrPhone);
                $("#phone_msg").html('<span style="color: #1BAD1A">校验通过:'+msg.bcrPhone+'是'+msg.bcr+'名下手机号</span>');
                $("#phone_module").modal('show');
            }else{
                //查询一
                $("#phone_name_").html(msg.bcr);
                $("#phone_phone_").html(msg.bcrPhone);
                $("#phone_msg").html('校验<span style="color: red">不通过:</span>'+msg.bcrPhone+'<span style="color: red">不是</span>'+msg.bcr+'名下手机号');
                $("#phone_module").modal('show');
            }
        },
        "json"
    );
}
function updatestatus(id, status) {
    $.post("/phone/updateStatus/" + id + "/" + status,
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
    $('#mytab').bootstrapTable('refresh', {url: '/phone/phoneList'});
})
function refushPhone() {
    $('#mytab2').bootstrapTable('refresh', {url: '/phone/phoneList'});
}
$("#update").click(function () {
    $.post(
        "/phone/phoneUpdateSave",
        $("#updateform").serialize(),
        function (data) {
            if (data.message == "修改成功!") {
                layer.alert(data.message, {icon: 6});
                refushPhone();
            } else {
                layer.alert(data.message, {icon: 6});
                refushPhone();
            }
        }, "json"
    );
});
$("#add").click(function () {
    $.post(
        "/phone/phoneAddSave",
        $("#formadd").serialize(),
        function (data) {
            if (data.message == "新增成功!") {
                layer.alert(data.message, {icon: 6});
                refushPhone();
            } else {
                layer.alert(data.message, {icon: 6});
                refushPhone();
            }
        }, "json"
    );
});

$("#updateSta").click(function () {
    layer.confirm('确认要执行批量修改收支科目状态吗？',function(index){
        $.post(
            "/phone/deleteManyPolice",
            {
                "manyId":$("#statusId").val(),
                "status":$("#status").val()
            },
            function(data){
                if(data.message=="修改成功!"){
                    layer.alert(data.message, {icon:6});
                    refushPhone();
                }else{
                    layer.alert(data.message, {icon:6});
                    refushPhone();
                }
            },"json"
        );
    });
});
$("#phone_bt").click(function () {
    var name = $("#phone_name").val();
    var card = $("#phone_phone").val();
    if(!name){
        layer.alert("姓名不能为空");
        return;
    }
    if(!card){
        layer.alert("手机号不能为空");
        return;
    }
    $.post(
        "/phone/phoneAddSave",
        {
            "bcr":name,
            "bcrPhone":card,
            kehuId:$("#khid").val()
        },
        function(data){
            alert(data);
            if(data.result=="success"){
                //查询一
                $("#phone_name_").html(name);
                $("#phone_phone_").html(card);
                $("#phone_msg").html('<span style="color: #1BAD1A">校验通过:'+card+'是'+name+'名下手机号</span>');
                $("#phone_module").modal('show');
            }else{
                if(data.message.indexOf("积分")>-1){
                    layer.alert(data.message);
                }else{
                    //查询一
                    $("#phone_name_").html(name);
                    $("#phone_phone_").html(card);
                    $("#phone_msg").html('校验<span style="color: red">不通过:</span>'+card+'<span style="color: red">不是</span>'+name+'名下手机号');
                    $("#phone_module").modal('show');
                }
            }
            refushPhone();
        },"json"
    );
});