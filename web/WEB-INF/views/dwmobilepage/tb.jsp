<%--
  Created by IntelliJ IDEA.
  User: 17702
  Date: 2018/7/11
  Time: 20:12
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
    <link rel="stylesheet" href="<%=path%>/static/dwmobilepage/css/youpingzheng.css" />
    <link rel="stylesheet" href="<%=path%>/static/dwmobilepage/css/base.css" />
    <title>淘宝服务认证</title>
    <style>
        .is_active{
            background-color: #3085F9;
        }
        .is_active_1{
            background-color: #c0c0c0;
        }
    </style>
</head>
<body  style="background-color: #fff">
<div class="top">
    <div class="top_img"><img src="<%=path%>/static/dwpcpage/images/tb.png" alt="" style="width: 100px;margin-left: 13px" /></div>
    <span style="color: #0076ff">淘宝</span>
</div>
<div class="main">
    <ul class="main_ul" style="width: 80%;margin: 0 auto;">
        <li>
            <span class="span1"><img src="<%=path%>/static/dwmobilepage/images/phone.png" alt="" /></span>
            <input type="text" name="" id="phone"  value="" placeholder="请输入手机号"/>
        </li>
        <li>
            <span class="span2"><img src="<%=path%>/static/dwmobilepage/images/password.png" alt="" /></span>
            <input type="password" name="" id="password" value="" placeholder="请输入淘宝登录密码"/>
        </li>
        <li>
            <span class="span2" style="width: 0.55rem;position:absolute;left: 0.15rem;"><img src="<%=path%>/static/dwmobilepage/images/yanzheng.png" alt="" /></span>
            <input type="text" name="" id="code_val" value="" placeholder="请输入短信验证码"/>
            <span class="span3" id="span3">获取</span>
        </li>
    </ul>
</div>
<footer>
    <div class="button" ><button onclick="down();" id="btn">提交</button></div>
    <div style="color: #225599;text-align: center;height: 40px;line-height: 40px" onclick="javascript :history.back(-1);">
        手动返回电商认证页面
    </div>
    <div class="bg">
        <div class="bg_left"><img src="<%=path%>/static/dwmobilepage/images/bj1.png" alt="" /></div>
        <div class="bg_right"><img src="<%=path%>/static/dwmobilepage/images/bj2.png" alt="" /></div>
    </div>
</footer>
<input type="text" id="taskId" value="">
</body>
<script src="<%=path%>/static/dwmobilepage/js/jquery.min.js"></script>
<script src="<%=path%>/static/dwmobilepage/js/mobile-detect.min.js"></script>
<script src="<%=path%>/static/dwmobilepage/js/flexible.js"></script>
<script src="<%=path%>/static/dwmobilepage/js/hui.js"></script>
<script>
    var id = localStorage.getItem("id");
    $(function () {
        //获取短信验证码
        var validCode = true;
        $(".span3").click(function () {
            var phone = $("#phone").val();
            var password = $("#password").val();
            if(!phone){
                hui.toast("手机号格式错误");
                return;
            }
            if(!password){
                hui.toast("密码不能为空");
                return;
            }
            $("#span3").attr("disabled","disabled");
            $("#span3").removeClass("is_active");
            $("#span3").addClass("is_active_1");
            $.post(
                "/dianShang/getTaoBaoPhone",
                {
                    "phone":phone,
                    "id":id,
                    "password":password
                },
                function (data) {
                    if(data.result=="success"){
                        hui.toast("短信发送成功，请注意查收");
                        var msg = JSON.parse(data.message);
                        $("#taskId").val(msg.data.tid);
                        var time = 60;
                        if (validCode) {
                            validCode = false;
                            var t = setInterval(function () {
                                time--;
                                $(".span3").html("重发"+time + "秒");
                                if (time == 0) {
                                    $("#span3").removeAttr("disabled");
                                    $("#span3").removeClass("is_active_1");
                                    $("#span3").addClass("is_active");
                                    clearInterval(t);
                                    $(".span3").html("重新获取");
                                    validCode = true;
                                }
                            }, 1000)
                        }
                    }else{
                        hui.toast(data.message);
                        $("#span3").removeAttr("disabled");
                        $("#span3").removeClass("is_active_1");
                        $("#span3").addClass("is_active");
                        clearInterval(t);
                        $(".span3").html("重新获取");
                    }
                },"json"
            );
        })
    })
    function down() {
        var phone = $("#phone").val();
        var code_val = $("#code_val").val();
        var password = $("#password").val();
        var taskId = $("#taskId").val();
        if(!taskId){
            hui.toast("认证未启动，请获取验证码");
            return;
        }
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
        if(!code_val){
            hui.toast("短信验证码不能为空");
            return;
        }
        hui.toast("提交中，提交完成后会自动返回信贷认证","long");
        $("#btn").attr("disabled","disabled");
        $("#btn").removeClass("is_active");
        $("#btn").addClass("is_active_1");
        $.post(
            "/dianShang/getTaoBaoData",
            {
                "tid":taskId,
                "sms":code_val,
                "id":id
            },
            function (data) {
                if(data.result=="fail"){
                    hui.toast("手机号或验证码输入有误，请重新认证");
                    $("#btn").removeAttr("disabled");
                    $("#btn").removeClass("is_active_1");
                    $("#btn").addClass("is_active");
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

