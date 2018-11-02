<%--
  Created by IntelliJ IDEA.
  User: 17702
  Date: 2018/7/11
  Time: 20:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge，chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta http-equiv="Expires" content="-1">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <link rel="stylesheet" href="<%=path%>/static/dwmobilepage/css/xiaocai.css" />
    <link rel="stylesheet" href="<%=path%>/static/dwmobilepage/css/base.css" />
    <title>二维码已失效</title>
</head>
<body>
<div class="top" style="margin-top: 30px">
    <div class="top_img"><img src="<%=path%>/static/dwmobilepage/images/error.png" alt="" /></div>
    <div style="color:#000;margin-top: 40px;font-size: 18px">此二维码已失效，请打开最新二维码</div>
</div>
</body>
<script src="<%=path%>/static/dwmobilepage/js/jquery.min.js"></script>
<script src="<%=path%>/static/dwmobilepage/js/mobile-detect.min.js"></script>
<script src="<%=path%>/static/dwmobilepage/js/flexible.js"></script>
<script src="<%=path%>/static/dwmobilepage/js/hui.js"></script>
</html>
