<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/6/26 0026
  Time: 下午 5:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();

%>
<html>
<head>
    <title>登录</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <link href="<%=path%>/pc/resources/css/jquery-ui-themes.css" type="text/css" rel="stylesheet"/>
    <link href="<%=path%>/pc/resources/css/axure_rp_page.css" type="text/css" rel="stylesheet"/>
    <link href="<%=path%>/pc/data/styles.css" type="text/css" rel="stylesheet"/>
    <link href="<%=path%>/pc/files/login/styles.css" type="text/css" rel="stylesheet"/>
    <script src="<%=path%>/pc/resources/scripts/jquery-1.7.1.min.js"></script>
    <script src="<%=path%>/pc/resources/scripts/jquery-ui-1.8.10.custom.min.js"></script>
    <script src="<%=path%>/pc/resources/scripts/axure/axQuery.js"></script>
    <script src="<%=path%>/pc/resources/scripts/axure/globals.js"></script>
    <script src="<%=path%>/pc/resources/scripts/axutils.js"></script>
    <script src="<%=path%>/pc/resources/scripts/axure/annotation.js"></script>
    <script src="<%=path%>/pc/resources/scripts/axure/axQuery.std.js"></script>
    <script src="<%=path%>/pc/resources/scripts/axure/doc.js"></script>
    <script src="<%=path%>/pc/data/document.js"></script>
    <script src="<%=path%>/pc/resources/scripts/messagecenter.js"></script>
    <script src="<%=path%>/pc/resources/scripts/axure/events.js"></script>
    <script src="<%=path%>/pc/resources/scripts/axure/recording.js"></script>
    <script src="<%=path%>/pc/resources/scripts/axure/action.js"></script>
    <script src="<%=path%>/pc/resources/scripts/axure/expr.js"></script>
    <script src="<%=path%>/pc/resources/scripts/axure/geometry.js"></script>
    <script src="<%=path%>/pc/resources/scripts/axure/flyout.js"></script>
    <script src="<%=path%>/pc/resources/scripts/axure/ie.js"></script>
    <script src="<%=path%>/pc/resources/scripts/axure/model.js"></script>
    <script src="<%=path%>/pc/resources/scripts/axure/repeater.js"></script>
    <script src="<%=path%>/pc/resources/scripts/axure/sto.js"></script>
    <script src="<%=path%>/pc/resources/scripts/axure/utils.temp.js"></script>
    <script src="<%=path%>/pc/resources/scripts/axure/variables.js"></script>
    <script src="<%=path%>/pc/resources/scripts/axure/drag.js"></script>
    <script src="<%=path%>/pc/resources/scripts/axure/move.js"></script>
    <script src="<%=path%>/pc/resources/scripts/axure/visibility.js"></script>
    <script src="<%=path%>/pc/resources/scripts/axure/style.js"></script>
    <script src="<%=path%>/pc/resources/scripts/axure/adaptive.js"></script>
    <script src="<%=path%>/pc/resources/scripts/axure/tree.js"></script>
    <script src="<%=path%>/pc/resources/scripts/axure/init.temp.js"></script>
    <script src="<%=path%>/pc/files/login/data.js"></script>
    <script src="<%=path%>/pc/resources/scripts/axure/legacy.js"></script>
    <script src="<%=path%>/pc/resources/scripts/axure/viewer.js"></script>
    <script src="<%=path%>/pc/resources/scripts/axure/math.js"></script>
    <script type="text/javascript">
        $axure.utils.getTransparentGifPath = function() { return '<%=path%>/pc/resources/images/transparent.gif'; };
        $axure.utils.getOtherPath = function() { return '<%=path%>/pc/resources/Other.html'; };
        $axure.utils.getReloadPath = function() { return '<%=path%>/pc/resources/reload.html'; };
    </script>
    <style>
        .loginclick{
            position:absolute;left: 760px; top: 790px;width: 400px;height: 60px;font-size: 28px;background-color: #0583ce;border-radius: 5px;
        }
        .logintext{
            position:absolute;left: 800px; top: 860px;
        }
        .srclogin{
            position: absolute;left: 760px;top: 710px; width: 400px;height: 60px;font-size: 28px;background-color: #0583ce;border-radius: 5px;
        }
        .srctext{
            position: absolute;
            left: 806px;
            top: 780px;
            width: 309px;
            height: 20px;
        }
        input::-webkit-input-placeholder,textarea::-webkit-input-placeholder{
            color:rgb(153, 153, 153);
            font-size:20px;
        }
    </style>
</head>
<body>
<div id="base" class="">
    <!-- Unnamed (图片) -->
    <div id="u1789" style="">
        <img id="u1789_img" class="img " src="<%=path%>/pc/images/login/u1789.png"/>
        <!-- Unnamed () -->
        <div id="u1790" class="text" style="display: none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <!-- Unnamed (矩形) -->
    <div id="u1791"  style="">
        <div id="u1791_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u1792" class="text" style="visibility: visible;">
            <p><span>地网数据中心管理系统</span></p>
        </div>
    </div>

    <!-- Unnamed (矩形) -->
    <div id="u1793" class="ax_default label" style="">
        <div id="u1793_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u1794" class="text" style="visibility: visible;">
            <p><span>Management system of data center of DiWang</span></p>
        </div>
    </div>

    <!-- Unnamed (矩形) -->
    <div id="u1795" class="ax_default box_1"  style="">
        <div id="u1795_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u1796" class="text" style="display: none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <!-- Unnamed (矩形) -->
    <div id="u1797"  style="">
        <div id="u1797_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u1798" class="text" style="visibility: visible;">
            <p><span>Welcome</span></p>
        </div>
    </div>

    <div id="msg" style="position: absolute;position: absolute;left: 760px;top:450px;text-align: center;width: 402px;height: 30px;color: red;text-align: center">
    </div>
    <!-- 手机号输入 (组合) -->
    <div id="u1799" class="ax_default"  style="">

        <!-- 手机号未选中 (矩形) -->
        <div id="u1800" class="ax_default box_1"  style="">
            <div id="u1800_div" class=""></div>
            <!-- Unnamed () -->
            <div id="u1801" class="text" style="display: none; visibility: hidden">
                <p><span></span></p>
            </div>
        </div>

        <!-- 手机号选中 (矩形) -->
        <div id="u1802" class="ax_default box_1 ax_default_hidden" style="display: none; visibility: hidden;">
            <div id="u1802_div" class=""></div>
            <!-- Unnamed () -->
            <div id="u1803" class="text" style="display: none; visibility: hidden">
                <p><span></span></p>
            </div>
        </div>

        <!-- Unnamed (矩形) -->
        <div id="u1804" class="ax_default box_2" style="">
            <div id="u1804_div" class=""></div>
            <!-- Unnamed () -->
            <div id="u1805" class="text" style="display: none; visibility: hidden">
                <p><span></span></p>
            </div>
        </div>
        <!-- 手机号 (文本框) -->
        <div id="u1806" class="ax_default text_field" style="">
            <input id="u1806_input" placeholder="请输入手机号" class="phone" type="tel" value="" maxlength="11"/>
        </div>

        <!-- Unnamed (图片) -->
        <div id="u1807" style="">
            <img id="u1807_img" class="img " src="<%=path%>/pc/images/login/u1807.png"/>
            <!-- Unnamed () -->
            <div id="u1808" class="text" style="display: none; visibility: hidden">
                <p><span></span></p>
            </div>
        </div>
    </div>

    <!-- 登录 (组合) -->
    <div id="" class="ax_default" style="">

        <!-- 【登录】 (矩形) -->
        <div id="login_button" class="loginbut srclogin" style="">
            <!-- Unnamed () -->
                <p style="color: #fff;line-height: 60px"><span>登录</span></p>
        </div>

        <!-- Unnamed (矩形) -->
        <div class="ax_default label srctext" id="login_text">
            <div id="u1812_div" class=""></div>
            <!-- Unnamed () -->
            <div id="u1813" class="text" style="visibility: visible;">
                <p style="text-align: center;margin: auto"><span>登录即代表您同意</span><span style="color:#225599;">《地网数据安全保密服务条款》</span></p>
            </div>
        </div>
    </div>

    <!-- 密码输入 (组合) -->
    <div id="u1815" class="ax_default" style="">

        <!-- 手机号未选中 (矩形) -->
        <div id="u1816" class="ax_default box_1" style="">
            <div id="u1816_div" class=""></div>
            <!-- Unnamed () -->
            <div id="u1817" class="text" style="display: none; visibility: hidden">
                <p><span></span></p>
            </div>
        </div>

        <!-- 手机号选中 (矩形) -->
        <div id="u1818" class="ax_default box_1 ax_default_hidden" style="display: none; visibility: hidden;">
            <div id="u1818_div" class=""></div>
            <!-- Unnamed () -->
            <div id="u1819" class="text" style="display: none; visibility: hidden">
                <p><span></span></p>
            </div>
        </div>

        <!-- Unnamed (矩形) -->
        <div id="u1820" class="ax_default box_2" style="">
            <div id="u1820_div" class=""></div>
            <!-- Unnamed () -->
            <div id="u1821" class="text" style="display: none; visibility: hidden">
                <p><span></span></p>
            </div>
        </div>

        <!-- 密码 (文本框) -->
        <div id="u1822" class="ax_default text_field" style="">
            <input id="u1822_input" class="password" placeholder="请输入密码" type="password" value="" maxlength="32"/>
        </div>

        <!-- Unnamed (图片) -->
        <div id="u1823" style="">
            <img id="u1823_img" class="img " src="<%=path%>/pc/images/login/u1823.png"/>
            <!-- Unnamed () -->
            <div id="u1824" class="text" style="display: none; visibility: hidden">
                <p><span></span></p>
            </div>
        </div>
    </div>

    <!-- 【注册/忘记密码】 (矩形) -->
    <div style="position:absolute;left: 760px; top: 670px;width: 402px;height: 30px">
        <!-- Unnamed () -->
        <div style="visibility: visible;">
            <p><span id="zhuce">注册</span>/<span id="findPwd">设置新密码</span></p>
        </div>
    </div>

    <!-- 验证码获取 (组合) -->
    <div class="ax_default" id="yzmshow" style="display: none;">

        <!-- 【获取验证码】 (组合) -->
        <div id="u1828" class="ax_default" style="">

            <!-- Unnamed (矩形) -->
            <div id="u1829" class="ax_default box_2" style="">
                <div id="u1829_div" class=""></div>
                <!-- Unnamed () -->
                <div id="u1830" class="text" style="display: none; visibility: hidden">
                    <p><span></span></p>
                </div>
            </div>

            <!-- Unnamed (组合) -->
            <div id="u1831" class="ax_default" style="">

                <!-- Unnamed (矩形) -->
                <div id="u1832" style="">
                    <div id="u1832_div" class=""></div>
                    <!-- Unnamed () -->
                    <div id="u1833" class="text" style="visibility: visible;">
                        <p><span class="yzm">获取验证码</span></p>
                    </div>
                </div>

                <!-- Unnamed (图片) -->
                <div id="u1834" style="">
                    <img id="u1834_img" class="img " src="<%=path%>/pc/images/login/u1834.png"/>
                    <!-- Unnamed () -->
                    <div id="u1835" class="text" style="display: none; visibility: hidden">
                        <p><span></span></p>
                    </div>
                </div>
            </div>
        </div>

        <!-- 验证码输入框 (组合) -->
        <div id="u1836" class="ax_default">

            <!-- 验证码未选中 (矩形) -->
            <div id="u1837" class="ax_default box_1" style="">
                <div id="u1837_div" class=""></div>
                <!-- Unnamed () -->
                <div id="u1838" class="text" style="display: none; visibility: hidden">
                    <p><span></span></p>
                </div>
            </div>

            <!-- 验证码选中 (矩形) -->
            <div id="u1839" class="ax_default box_1 ax_default_hidden" style="display: none; visibility: hidden;" >
                <div id="u1839_div" class=""></div>
                <!-- Unnamed () -->
                <div id="u1840" class="text" style="display: none; visibility: hidden">
                    <p><span></span></p>
                </div>
            </div>

            <!-- 输入验证码 (文本框) -->
            <div id="u1841" class="ax_default text_field" style="">
                <input id="u1841_input" placeholder="请输入验证码" class="codediv" type="text" value="" maxlength="6"/>
            </div>
        </div>
    </div>
</div>
<input type="hidden" name="types" id ="types" value="0" >
</body>
<script>
    $("#zhuce").click(function () {
        $("#types").val(1);
        //显示验证码框
        $("#yzmshow").show();
        //登录按钮往下移动
        $("#login_button").addClass("loginclick").removeClass("srclogin");
        //协议往下移动
        $("#login_text").addClass("logintext").removeClass("srctext");
    });
    $("#findPwd").click(function () {
        //显示验证码框
        $("#yzmshow").show();
        //登录按钮往下移动
        $("#login_button").addClass("loginclick").removeClass("srclogin");
        //协议往下移动
        $("#login_text").addClass("logintext").removeClass("srctext");
        $("#types").val(2);
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
                $("#msg").html("手机号不能为空");
                return;
            }
            if(phone.length!=11){
                $("#msg").html("手机号输入有误");
                return;
            }
            if(types==1){
                codes = "register";
            }else if(types==2){
                codes = "findPwd";
            }
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

