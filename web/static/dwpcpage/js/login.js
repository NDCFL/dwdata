$(document).ready(function() {
    $(".yz_p").click(function() {
        $(this).siblings(".yz").toggle();
        $(".isshow").val(parseInt($(".isshow").val())==0?1:0);
    })
//登录验证
    $(".login").click(function() {
        var isshow = $(".isshow").val();
        var phone = $("#phone").val();
        var pass = $("#password").val();
        var code = $("#code").val();
        //登录
        if(isshow==0){
            if(!phone || phone.length != 11 || !pass) {
                $(".tishi").html("账号或密码输入有误");
                return;
            }
            $.post(
                "/pcUser/getInfo", {
                    "phone": phone,
                    "password": pass
                },
                function(msg) {
                    if(msg.result=="success"){
                        //登录成功，调到首页
                        location.href = "/pcUser/pcUserMain";
                    }else{
                        $(".tishi").html(msg.message);
                    }
                }, "json"
            );
        }else{
            //执行注册操作
            if(!phone || phone.length != 11 || !pass || !code) {
                $(".tishi").html("账号,密码或短信验证码输入有误");
                return;
            }
            $.post(
                "/pcUser/register", {
                    "mobile": phone,
                    "password": pass,
                    "code":code
                },
                function(msg) {
                    if(msg.result=="success"){
                        //登录成功，调到首页
                        location.href = "/pcUser/pcUserMain";
                    }else{
                        $(".tishi").html(msg.message);
                    }
                }, "json"
            );
        }
    })
    var countdown = 60;
    $(function () {
        //获取短信验证码
        var validCode = true;
        $(".yzm").click(function () {
            var phone = $("#phone").val();
            if(!phone){
                $(".tishi").html("手机号格式错误");
                return;
            }
            $.post(
                "/code/sendCode",
                {
                    "mobile":phone
                },
                function (data) {
                    if(data.result=="success"){
                        $(".tishi").html("短信发送成功，请注意查收");
                        var time = 60;
                        if (validCode) {
                            validCode = false;
                            var t = setInterval(function () {
                                time--;
                                $("#yzmtext").html("重发"+time + "秒");
                                if (time == 0) {
                                    clearInterval(t);
                                    $("#yzmtext").html("重新获取");
                                    validCode = true;
                                }
                            }, 1000)
                        }
                    }else{
                        $(".tishi").html("短信发送失败请重新发送");
                    }
                },"json"
            );
        })
    })
})