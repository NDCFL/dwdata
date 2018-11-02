//生成用户数据
$('#mytab1').bootstrapTable({
    method: 'post',
    contentType: "application/x-www-form-urlencoded",//必须要有！！！！
    url: "/police/policeList",//要请求数据的文件路径
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
            title: '被查人身份证号',
            field: 'bcrIdcard',
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
                    return '<span style="color: #000">匹配</span>';
                } else {
                    //表示启用状态
                    return '<span style="color: #000">不匹配</span>';
                }
            }
        }
        ,
        {
            title: '查询结果',
            align: 'center',
            field: '',
            formatter: function (value, row, index) {
                var e = '<a title="点击查看详情" onclick="edit1('+row.id+');">点击查看详情</a> ';
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
function edit1(name) {
    $.post("/police/findFindPolice/" + name,
        function (msg) {
        console.log(msg);
            if(msg.code==0){
                if(msg.isActive==0){
                    //查询一致
                    var info = JSON.parse(msg.data);
                    $("#sex").html(info.data.gender);
                    $("#jiguan").html(info.data.county==null?"无":info.data.county);
                    $("#riqi").html(info.data.birthday);
                    $("#jg").html(info.data.domicilePlace);
                    $("#info").modal('show');
                    $(".police_name").html(msg.bcr);
                    $(".police_idcard").html(msg.bcrIdcard);
                    $("#police_img").attr("src",msg.headImg);
                }else{
                    //查询不一致
                    $("#no_name").html(msg.bcr);
                    $("#no_card").html(msg.bcrIdcard);
                    $("#info_1").modal('show');
                }
                refushPolice();
            }else{
                layer.alert("查询失败", {icon:5});
                refushPolice();
            }
        },
        "json"
    );
}
function refushPolice() {
    $('#mytab1').bootstrapTable('refresh', {url: '/police/policeList'});
}
$('#info').on('hide.bs.modal',
    function() {
        $("#sex").html("");
        $("#jiguan").html("");
        $("#riqi").html("");
        $("#jg").html("");
        $(".police_name").html("");
        $(".police_idcard").html("");
        $("#police_img").attr("src","");
    })
$("#card_bt").click(function () {
    var name = $("#card_name").val();
    var card = $("#idcard").val();
    var ages = card.substring(6,10);
    if(!name){
        layer.alert("姓名不能为空");
        return;
    }

    if(!card){
        layer.alert("身份证不能为空");
        return;
    }
    if(parseInt(ages)<1920){
        layer.alert("身份证年龄过大，请检查后后重新输入");
        return;
    }
    $.post(
        "/police/policeAddSave",
        {
            "bcr":name,
            "bcrIdcard":card,
            "kehuId":$("#khid").val()
        },
        function(msg){
            if(!msg.pcUserId){
                layer.alert("积分不足，请充值");
                return;
            }else{
                if(msg.code==0){
                    if(msg.isActive==0){
                        //查询一致
                        var info = JSON.parse(msg.data);
                        $("#sex").html(info.data.gender);
                        $("#jiguan").html(info.data.county==null?"无":info.data.county);
                        $("#riqi").html(info.data.birthday);
                        $("#jg").html(info.data.domicilePlace);
                        $("#info").modal('show');
                        $(".police_name").html(msg.bcr);
                        $(".police_idcard").html(msg.bcrIdcard);
                        $("#police_img").attr("src",msg.headImg);
                    }else{
                        //查询不一致
                        $("#no_name").html(msg.bcr);
                        $("#no_card").html(msg.bcrIdcard);
                        $("#info_1").modal('show');
                    }
                    refushPolice();
                }else{
                    //查询不一致
                    $("#no_name").html(msg.bcr);
                    $("#no_card").html(msg.bcrIdcard);
                    $("#info_1").modal('show');
                    refushPolice();
                }
            }

        },"json"
    );
});