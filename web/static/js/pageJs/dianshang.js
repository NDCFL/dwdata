//生成用户数据
$('#dianshang').bootstrapTable({
    method: 'post',
    contentType: "application/x-www-form-urlencoded",//必须要有！！！！
    url: "/dianShang/dianShangList",//要请求数据的文件路径
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
            title: '查询项目',
            field: 'find',
            align: 'center',
            sortable: true
        }
        ,
        {
            title: '查询状态',
            field: 'isActive',
            align: 'center',
            formatter: function (value, row, index) {
                if(row.tbStatus==0 && row.jdStatus == 0){
                    //表示启用状态
                    return '<span style="color: #000">淘宝/京东认证成功</span>';
                }else if(row.tbStatus==0){
                    //表示启用状态
                    return '<span style="color: #000">淘宝认证成功</span>';
                }else if(row.jdStatus==0){
                    //表示启用状态
                    return '<span style="color: #000">京东认证成功</span>';
                }else if(!row.tbStatus || !row.jdStatus){
                    //表示启用状态
                    return '<span style="color: green">认证中</span>';
                }else{
                    return '<span style="color: #000">认证失败</span>';
                }
            }
        }
        ,
        {
            title: '查询结果',
            align: 'center',
            field: '',
            formatter: function (value, row, index) {
                var e = '<a title="点击查看详情" onclick="edit('+row.time+')" >点击查看详情</a> ';
                if (row.tbStatus == 0 || row.jdStatus==0) {
                    //表示启用状态
                    return e;
                } else if(row.tbStatus==0 && row.tbStatus == 0){
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
function del(dianShangid, status) {
    if (status == 0) {
        layer.msg("删除失败，已经启用的不允许删除!", {icon: 2, time: 1000});
        return;
    }
    layer.confirm('确认要删除吗？', function (index) {
        $.ajax({
            type: 'POST',
            url: '/dianShang/deleteDianShang/' + dianShangid,
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
function edit(time) {
    location.href = "/startDianShang/info/"+$("#khid").val()+"/taobao/"+time;
}
function updatestatus(id, status) {
    $.post("/dianShang/updateStatus/" + id + "/" + status,
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
    $('#dianshang').bootstrapTable('refresh', {url: '/dianShang/dianShangList'});
})
function refushPhone() {
    $('#dianshang').bootstrapTable('refresh', {url: '/dianShang/dianShangList'});
}
$("#update").click(function () {
    $.post(
        "/dianShang/dianShangUpdateSave",
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
        "/dianShang/dianShangAddSave",
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
            "/dianShang/deleteManyDianShang",
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
$("#dianShang_bt").click(function () {
    var name = $("#dianShang_name").val();
    var card = $("#dianShang_dianShang").val();
    if(!name){
        layer.alert("姓名不能为空");
        return;
    }
    if(!card){
        layer.alert("手机号不能为空");
        return;
    }
    $.post(
        "/dianShang/dianShangAddSave",
        {
            "bcr":name,
            "bcrPhone":card,
            kehuId:$("#khid").val()
        },
        function(data){
            alert(data);
            if(data.result=="success"){
                //查询一
                $("#dianShang_name_").html(name);
                $("#dianShang_dianShang_").html(card);
                $("#dianShang_msg").html('<span style="color: #1BAD1A">校验通过:'+card+'是'+name+'名下手机号</span>');
                $("#dianShang_module").modal('show');
            }else{
                if(data.message.indexOf("积分")>-1){
                    layer.alert(data.message);
                }else{
                    //查询一
                    $("#dianShang_name_").html(name);
                    $("#dianShang_dianShang_").html(card);
                    $("#dianShang_msg").html('校验<span style="color: red">不通过:</span>'+card+'<span style="color: red">不是</span>'+name+'名下手机号');
                    $("#dianShang_module").modal('show');
                }
            }
            refushPhone();
        },"json"
    );
});