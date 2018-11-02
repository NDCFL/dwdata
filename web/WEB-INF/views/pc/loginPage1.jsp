<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/6/22 0022
  Time: 下午 8:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>地网数据登录</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=path%>/static/css/pclogin.css">
</head>
<body>
<div style="height: 420px;background-image: url(<%=path%>/static/img/ban.png);">
    <div class="first">
        <div class="welcomefont">
            welcome
        </div>
        <div style="width: 100%;" >
            <div class="bigdiv">
                <div class=" bigtitle" style="">
                    地网数据中心管理系统
                </div>
                <div style="" class="smalltitle">
                    Management system of data center of DiWang
                </div>
            </div>
        </div>
    </div>
</div>
<div style="height: auto">
    <div style="height:50px;margin-top: 20px;">
        <p style="text-align: center;line-height: 60px;color: red" id="msg"></p>
    </div>
    <div style="height:60px;margin-top: 0px;">
        <div class="alldiv">
            <div style="background-color:  rgba(13, 37, 80, 1);width: 60px;height: 60px;float: left">
                <img src="<%=path%>/static/img/phone.png" alt="" style="width: 30px;height: 30px;margin-top: 15px">
            </div>
            <div style="width: 330px;height: 60px;float: right">
                <input type="text" class="phone"  placeholder="请输入手机号" maxlength="11" minlength="11">
            </div>
        </div>
    </div>
    <div style="height:60px;margin-top: 50px">
        <div class="alldiv">
            <div style="background-color:  rgba(13, 37, 80, 1);width: 60px;height: 60px;float: left">
                <img src="<%=path%>/static/img/password.png" alt="" style="width: 30px;height: 30px;margin-top: 15px">
            </div>
            <div style="width: 330px;height: 60px;float: right">
                <input type="password" class="password"  placeholder="请输入密码">
            </div>
        </div>
    </div>
    <div style="height:40px;">
        <div style="height: 100%;width: 400px;text-align: left;margin: auto;">
            <div class="zhuce"><span id="zhuce">注册</span>/<span id="findPwd">设置新密码</span></div>
        </div>
    </div>
    <div style="height:60px;margin-top: 10px;display: none" class="isshowcode">
        <div style="height: 100%;width: 400px;text-align: center;margin: auto">
            <div style="border: solid 1px rgba(13, 37, 80, 1);width: 195px;height: 60px;float: left">
                <input type="text" class="codediv" placeholder="请输如验证码" maxlength="4" minlength="4">
            </div>
            <div style="border: solid 1px rgba(13, 37, 80, 1);width: 195px;height: 60px;float: right;border-radius: 5px">
                <div style="width:70%;height: 100%;text-align: center;margin: auto;">
                    <div style="width:40px;height: 100%;float: left">
                        <img src="<%=path%>/static/img/new.png" alt="" style="width: 30px;height: 30px;margin-top: 15px">
                    </div>
                    <div style="height: 100%;float: left" >
                        <span class="yzm">获取验证码</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div style="height:60px;margin-top: 10px">
        <div style="height: 100%;width: 400px;text-align: center;margin: auto">
            <button class="loginbut">登录</button>
        </div>
    </div>
    <div style="height:40px;">
        <div style="height: 100%;width: 400px;text-align: center;margin: auto;line-height: 40px">
            <p><span>登录即代表您同意</span><a style="color:#225599;" href="https://www.baidu.com">《地网数据安全保密服务条款》</a></p>
        </div>
    </div>

</div>
<input type="text" name="types" id ="types" value="0" >
</body>
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script>
    $("#zhuce").click(function () {
        $("#types").val(1);
        $(".isshowcode").show();
    });
    $("#findPwd").click(function () {
        $("#types").val(2);
        $(".isshowcode").show();
    });
    $(".loginbut").click(function () {
        var phone = $(".phone").val();
        var password = $(".password").val();
        var code = $(".codediv").val();
        var types = $("#types").val();
        if(!phone){
            $("#msg").html("手机号不能为空");
            return;
        }
        if(isNaN(phone)){
            $("#msg").html("手机号格式有误");
            return;
        }
        if(!password){
            $("#msg").html("登录密码不能为空");
            return;
        }
        if(types==0){
            //调用登录的接口
            $.post(
                "/pcUser/getInfo",
                {
                    "phone":phone,
                    "password":password
                },
                function (data) {
                    $("#msg").html(data.message);
                    if(data.result=="success"){
                        location.href = "/pcUser/pcUserMain";
                    }else{

                    }
                },"json"
            );
        }else if(types==1){
            if(!code){
                $("#msg").html("手机验证码不能为空");
                return;
            }
            //调用注册的接口
            $.post(
                "/pcUser/register",
                {
                    "mobile":phone,
                    "password":password,
                    "code":code
                },
                function (data) {
                    if(data.result=="success"){
                        $("#msg").html(data.message);
                        location.href = "/pcUser/pcUserMain";
                    }else{
                        $("#msg").html(data.message);
                    }
                },"json"
            );
        }else if(types == 2){
            if(!code){
                $("#msg").html("手机验证码不能为空");
                return;
            }
            //调用找回密码的接口
            $.post(
                "/pcUser/setPwd",
                {
                    "mobile":phone,
                    "password":password,
                    "code":code
                },
                function (data) {
                    if(data.result=="success"){
                        $("#msg").html(data.message);
                        location.href = "/pcUser/pcUserMain";
                    }else{
                        $("#msg").html(data.message);
                    }
                },"json"
            );
        }
    });
    var countdown = 60;
    $(function () {
        //获取短信验证码
        var validCode = true;
        $(".yzm").click(function () {
            var types = $("#types").val();
            var codes="";
            var phone = $(".phone").val();
            if(!phone){
                $("#msg").html("手机号不能为空!");
                return;
            }
            if(phone.length!=11){
                $("#msg").html("手机号输入有误!");
                return;
            }
            if(types==1){
                codes = "register";
            }else if(types==2){
                codes = "findPwd";
            }
            alert(codes);
            $.post(
                "/code/sendCode",
                {
                    "mobile":phone,
                    "codeType":codes
                },
                function (data) {
                    console.log(data);
                    if(data.result=="success"){
                        if(data.message.indexOf("不存在")>-1){
                            $("#types").val(1);
                        }
                        var time = 60;
                        if (validCode) {
                            validCode = false;
                            var t = setInterval(function () {
                                time--;
                                $(".yzm").html("重发"+time + "秒");
                                if (time == 0) {
                                    clearInterval(t);
                                    $(".yzm").html("重新获取");
                                    validCode = true;
                                }
                            }, 1000)
                        }
                    }else{

                    }
                },"json"
            );
        })
    })
</script>

</html>
