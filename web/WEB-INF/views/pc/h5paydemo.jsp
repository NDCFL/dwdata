<%--
  Created by IntelliJ IDEA.
  User: Wang Genshen
  Date: 2017-07-04
  Time: 22:25
  To change this template use File | Settinfz | File Templates.

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>微信充值</title>
    <link href="<%=path %>/static/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="<%=path %>/static/js/sweet-alert/sweet-alert.css" rel="stylesheet"/>
</head>
<body>
<form role="form"  id="money">
    <div class="form-group">
        <label >充值金额:</label>
        <input ttype="number" name="userMoney"  id="userMoney" class="form-control" placeholder="请输入充值金额" >
    </div>
    <button class="btn btn-sm btn-primary pull-right m-t-n-xs" type="button" id="inmoney"><strong>确认充值</strong></button>
</form>
</body>
<script src="<%=path %>/static/js/jquery.min.js"></script>
<script src="<%=path %>/static/js/bootstrap.min.js"></script>
<script src="<%=path %>/static/js/sweet-alert/sweet-alert.min.js"></script>
<script>
    $("#inmoney").click(function () {
        $.post(
            "/pay/pay",
            {
                "userMoney":$("#userMoney").val()
            },function (msg) {
                console.log(msg);
                if(msg.result=="success"){
                    location.href = msg.message;
                }else{
                    alert(msg.message());
                }
            },"json"
        );
    });
</script>
</html>
