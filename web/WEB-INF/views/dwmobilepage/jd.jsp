<%--
  Created by IntelliJ IDEA.
  User: 17702
  Date: 2018/7/11
  Time: 20:21
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
    <link rel="stylesheet" href="<%=path%>/static/dwmobilepage/css/mifang.css" />
    <link rel="stylesheet" href="<%=path%>/static/dwmobilepage/css/base.css" />
    <title>京东认证</title>
    <style>
        .is_active{
            background-color: #3085F9;
        }
        .is_active_1{
            background-color: #c0c0c0;
        }
    </style>
</head>
<body style="background-color: #fff">
<div class="top">
    <div class="top_img"><img src="<%=path%>/static/dwpcpage/images/jd.png" alt="" /></div>
    <span style="color: #0076ff">京东</span>
</div>
<div class="main">
    <ul class="main_ul" style="width: 80%;margin: 0 auto;">
        <li>
            <span class="span1"><img src="<%=path%>/static/dwmobilepage/images/phone.png" alt="" /></span>
            <input type="text" name="" id="phone" value="" placeholder="请输入手机号"/>
        </li>
        <li>
            <span class="span1"><img src="<%=path%>/static/dwmobilepage/images/password.png" alt="" /></span>
            <input type="password" name="" id="password" value="" placeholder="请输入登录密码"/>
        </li>
    </ul>
</div>
<footer>
    <div class="button"><button onclick="down();" id="btn">提交</button></div>
    <div style="color: #225599;text-align: center;height: 40px;line-height: 40px" onclick="javascript :history.back(-1);">
        手动返回信贷认证页面
    </div>
    <div class="bg">
        <div class="bg_left"><img src="<%=path%>/static/dwmobilepage/images/bj1.png" alt="" /></div>
        <div class="bg_right"><img src="<%=path%>/static/dwmobilepage/images/bj2.png" alt="" /></div>
    </div>
</footer>
<input type="hidden" id="taskId" value="">
</body>
<script src="<%=path%>/static/dwmobilepage/js/jquery.min.js"></script>
<script src="<%=path%>/static/dwmobilepage/js/mobile-detect.min.js"></script>
<script src="<%=path%>/static/dwmobilepage/js/flexible.js"></script>
<script src="<%=path%>/static/dwmobilepage/js/hui.js"></script>
<script>
    var id = localStorage.getItem("id");
    function down() {
        var phone = $("#phone").val();
        var password = $("#password").val();
        if(!phone){
            hui.toast("手机号格式错误");
            return;
        }
        if(phone.length!=11){
            hui.toast("手机号格式错误");
            return;
        }
        if(!password){
            hui.toast("密码不能为空");
            return;
        }
        hui.toast("提交中，提交完成后会自动返回信贷认证","long");
        $("#btn").attr("disabled","disabled");
        $("#btn").removeClass("is_active");
        $("#btn").addClass("is_active_1");
        $.post(
            "/dianShang/getJinDongData",
            {
                "phone":phone,
                "password":password,
                "id":id
            },
            function (data) {
                $("#btn").removeAttr("disabled");
                $("#btn").removeClass("is_active_1");
                $("#btn").addClass("is_active");
                if(data.result=="fail"){
                    hui.toast(data.message);
                }else{
                    var url = localStorage.getItem("url");
                    location.href = url;
                }
            },
            "json"
        );
    }
</script>
</html>
