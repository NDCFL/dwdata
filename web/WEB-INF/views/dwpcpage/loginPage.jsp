<%--
  Created by IntelliJ IDEA.
  User: 17702
  Date: 2018/7/5
  Time: 11:23
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
    <link rel="stylesheet" href="<%=path%>/static/dwpcpage/css/login.css"/>
    <title>地网数据-登录</title>
</head>
<body style=" overflow-x:hidden;">
<div class="header">
    <img src="<%=path%>/static/dwpcpage/images/bg.png"/>
    <div class="bg">
        <div class="word1">Welcome</div>
        <div class="border">
            <div class="p1">地网数据中心管理系统</div>
            <div class="p2">Management system of data center of DiWang</div>
        </div>
    </div>
</div>
<div class="main">
    <div class="main_samll">
        <p class="tishi"></p>
        <div class="phone">
            <div class="icon"><img src="<%=path%>/static/dwpcpage/images/phone.png" alt=""/></div>
            <input type="text" maxlength="11" minlength="11" placeholder="请输入手机号" value="" id="phone"
                   style="outline-color: #0D2550"/></div>
        <div class="pass">
            <div class="icon"><img src="<%=path%>/static/dwpcpage/images/pass.png"/></div>
            <input type="password" name="" id="password" value="" placeholder="请输入密码" style="outline-color: #0D2550"/>
        </div>
        <p class="yz_p">注册/设置新密码</p>
        <div class="yz" style="display: none;">
            <div class="test">
                <div class="test_input"><input type="text" id="code" placeholder="请输入验证码"
                                               style="outline-color: #0D2550"></div>
                <div class="yzm">
                    <div class="img"><img src="<%=path%>/static/dwpcpage/images/yz.png" alt=""/></div>
                    <span id="yzmtext">获取验证码</span></div>
            </div>
        </div>
        <div class="login">登录</div>
        <p style="font-size: 10px">
            <span>登录即代表您同意</span>
            <span style="color: #225599;"><a href="/loginxy.jsp" style="color: #225599;">《地网数据安全保密服务条款》</a></span>
        </p>
    </div>
</div>
<input type="hidden" class="isshow" value="0">
</body>
<script src="<%=path%>/static/dwpcpage/js/jquery.min.js"></script>
<script src="<%=path%>/static/dwpcpage/js/login.js"></script>




