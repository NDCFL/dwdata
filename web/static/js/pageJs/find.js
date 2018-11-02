//生成用户数据
$('#mytab').bootstrapTable({
    method: 'post',
    contentType: "application/x-www-form-urlencoded",//必须要有！！！！
    url: "/consumeHistory/consumeHistoryList",//要请求数据的文件路径
    dataField: "res",
    sortOrder: "ID asc",
    pagination: true,//是否分页
    queryParamsType: 'limit',//查询参数组织方式
    queryParams: queryParams,//请求服务器时所传的参数
    sidePagination: 'server',//指定服务器端分页
    pageNumber: 1, //初始化加载第一页，默认第一页
    pageSize: 20,//单页记录数
    pageList: [20, 30, 40, 50, 60, 70, 80, 90, 100],//分页步进值
    uniqueId: "id",
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
                if(!value){
                    value = "未填写";
                }
                return '<span style="color: #000">'+value+'</span> ';
            }
        }
        ,
        {
            title: '项目',
            field: 'remark',
            align: 'center',
            sortable: true
        }
        ,
        {
            title: '消耗积分',
            field: 'amount',
            align: 'center',
            sortable: true
        }
        ,
        {
            title: '状态',
            field: 'isActive',
            align: 'center',
            formatter: function (value, row, index) {
                if (value == 0) {
                    //表示启用状态
                    return '<span style="color: #000">成功</span>';
                } else {
                    //表示启用状态
                    return '<span style="color: green">失败</span>';
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
        searchVal: title
    }
}
function refush() {
    $('#mytab').bootstrapTable('refresh', {url: '/consumeHistory/consumeHistoryList'});
}