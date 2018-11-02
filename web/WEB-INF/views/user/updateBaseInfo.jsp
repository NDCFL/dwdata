<%--
  Created by IntelliJ IDEA.
  User: chenfeilong
  Date: 2017/10/28
  Time: 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>基础数据修改</title>
    <link href="<%=path%>/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=path%>/static/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=path%>/static/css/animate.min.css" rel="stylesheet">
    <link href="<%=path%>/static/css/style.min.css" rel="stylesheet">
    <link href="<%=path%>/static/css/bootstrapValidator.css" rel="stylesheet">
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <form class="form-horizontal" role="form" id="updateInfo">
        <div class="form-group">
            <label class="col-sm-3 control-label">我的余额：</label>
            <div class="col-sm-3">
                <input id="money" name="money" class="form-control" style="outline: none;border: none;background-color: #f3f3f4" readonly  type="text" >
            </div>
            <div class="col-sm-2">
                <a class="btn btn-primary col-sm-5" data-toggle="modal" data-target="#myModal1">充值</a>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">code：</label>
            <div class="col-sm-4">
                <input id="code" name="code" class="form-control"  type="text" >
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">key：</label>
            <div class="col-sm-4">
                <input id="key" name="key" class="form-control"  type="text" >
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-4 col-sm-offset-3">
                <button class="btn btn-primary col-sm-12" id="btn" type="button">修改</button>
            </div>
        </div>
    </form>
</div>
<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel1">
                    地网数据微信
                </h4>
            </div>
            <div class="modal-body">
                <div style="text-align: center">
                    扫一扫，地网小姐姐耐心为您服务
                </div>
                <div style="text-align: center;margin: auto">
                    <img src="<%=path%>/static/dwpcpage/images/kf.jpg" alt="地网数据客服" style="height: 258px;width: 258px">
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
<%--<jsp:include page="../common/js.jsp"></jsp:include>--%>
<script src="<%=path%>/static/js/jquery.min.js?v=2.1.4"></script>
<script src="<%=path%>/static/js/bootstrap.min.js?v=3.3.5"></script>
<script type="text/javascript" src="<%=path%>/static/js/bootstrapValidator.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/static/js/jquery.formautoFill.js"></script>
<script src="<%=path%>/static/js/plugins/layer/layer.js"></script>
<script>
    $.post(
        "/api/getApiInfo",
        function (data) {
            $("#code").val(data.code);
            $("#key").val(data.key);
            $("#money").val(data.money);
        },"json"
    );
    $("#btn").click(function () {
        $.post(
            "/api/updateBaseInfo",
            $("#updateInfo").serialize(),
            function (data) {
                layer.alert(data.message);
            },"json"
        );
    });
</script>
</html>