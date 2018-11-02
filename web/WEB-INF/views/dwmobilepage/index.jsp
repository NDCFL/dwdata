<%--
  Created by IntelliJ IDEA.
  User: 17702
  Date: 2018/7/11
  Time: 17:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge，chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta http-equiv="Expires" content="-1">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <link rel="stylesheet" href="<%=path%>/static/dwmobilepage/css/index.css" />
    <title>借条平台认证</title>
</head>

<body>
<header>
    <div class="head_left">
        <canvas id="process" width="200" height="200"></canvas>
    </div>
    <div class="head_right">
        <h1>信贷认证</h1>
        <p style="margin-top: 30px;margin-left: -30px">认证越详细、越真实，通过额度越高</p>
    </div>
</header>
<div class="main">
    <ul>
        <li>
            <a href="<%=path%>/startHighFind/mifang" id="mifang_url">
                <div class="li_left">
                    <span class="span1"><img src="<%=path%>/static/dwmobilepage/images/mifang.jpg" alt="" /></span>
                    <span class="span2" style="position: absolute;top:0;font-size: 0.4rem;color: #000">米房服务</span>
                </div>
                <div class="li_right">
                    <span class="span3" id="mifang"  style="display: none;"><img src="<%=path%>/static/dwmobilepage/images/ok.png" alt="" /></span>
                    <span class="span5" id="mifang_init">未认证</span>
                    <span class="span4"></span>
                </div>
            </a>
        </li>
        <li>
            <a href="<%=path%>/startHighFind/youpingzheng" id="youpingzheng_url">
                <div class="li_left">
                    <span class="span1"><img src="<%=path%>/static/dwmobilepage/images/youpingzheng.jpg" alt="" /></span>
                    <span class="span2" style="position: absolute;top:0;font-size: 0.4rem;color: #000">有凭证</span>
                </div>
                <div class="li_right">
                    <span class="span3" id="youpingzheng" style="display: none;"><img src="<%=path%>/static/dwmobilepage/images/ok.png" alt="" /></span>
                    <span class="span5" id="youpingzheng_init">未认证</span>
                    <span class="span4"></span>
                </div>
            </a>
        </li>
        <li>
            <a href="<%=path%>/startHighFind/jinjiedao" id="jinjiedao_url">
                <div class="li_left">
                    <span class="span1"><img src="<%=path%>/static/dwmobilepage/images/jinjiedao.jpg" alt="" /></span>
                    <span class="span2" style="position: absolute;top:0;font-size: 0.4rem;color: #000">今借到</span>
                </div>
                <div class="li_right">
                    <span class="span3" style="display: none;" id="jinjiedaoStatus"><img src="<%=path%>/static/dwmobilepage/images/ok.png" alt="" /></span>
                    <span class="span5" id="jinjiedaoStatus_init">未认证</span>
                    <span class="span4"></span>
                </div>
            </a>
        </li>
        <li>
            <a href="<%=path%>/startHighFind/xiaocai" id="xiaocai_url">
                <div class="li_left">
                    <span class="span1"><img src="<%=path%>/static/dwmobilepage/images/xiaocai.jpg" alt="" /></span>
                    <span class="span2" style="position: absolute;top:0;font-size: 0.4rem;color: #000">小财管家</span>
                </div>
                <div class="li_right">
                    <span class="span3" id="xiaocaiguanjia" style="display: none;"><img src="<%=path%>/static/dwmobilepage/images/ok.png" alt="" /></span>
                    <span class="span5" id="xiaocaiguanjia_init">未认证</span>
                    <span class="span4"></span>
                </div>
            </a>
        </li>
    </ul>
</div>
<input type="hidden" id="init">
</body>
<script src="<%=path%>/static/dwmobilepage/js/jquery.min.js"></script>
<script src="<%=path%>/static/dwmobilepage/js/mobile-detect.min.js"></script>
<script src="<%=path%>/static/dwmobilepage/js/flexible.js"></script>
<script>

    (function() {
        var url = window.location.href;
        var id = "${highFindVo.id}";
        localStorage.removeItem("id");
        localStorage.setItem("id",id);
        localStorage.removeItem("url");
        localStorage.setItem("url",url);
        $.post(
            "/highSearch/updateBase",
            {
                "device":devices,
                "id":id
            },function (data) {
            },"json"
        );
        var cnt = 0;
        $.post(
            "/highFind/findHighFindStatus/"+id,
            function (data) {
                console.log(data);
                if(data.isActive==1){
                    $("#mifang_url").removeAttr("href");
                    $("#youpingzheng_url").removeAttr("href");
                    $("#jinjiedao_url").removeAttr("href");
                    $("#xiaocai_url").removeAttr("href");
                    location.href = "/pay/index?id="+id;
                }else{
                    if(data.mifangStatus==0){
                        $("#mifang").show();
                        $("#mifang_init").hide();
                        $("#mifang_url").removeAttr("href");
                        cnt = parseInt(cnt)+25;
                        $("#init").val(cnt);
                        animate();
                    }
                    if(data.youpingzhengStatus==0){
                        $("#youpingzheng").show();
                        $("#youpingzheng_init").hide();
                        $("#youpingzheng_url").removeAttr("href");
                        cnt = parseInt(cnt)+24;
                        $("#init").val(cnt);
                        animate();
                    }
                    if(data.jinjiedaoStatus==0){
                        $("#jinjiedaoStatus").show();
                        $("#jinjiedaoStatus_init").hide();
                        $("#jinjiedao_url").removeAttr("href");
                        cnt = parseInt(cnt)+24;
                        $("#init").val(cnt);
                        animate();
                    }
                    if(data.xiaocaiguanjiaStatus==0){
                        $("#xiaocaiguanjia").show();
                        $("#xiaocaiguanjia_init").hide();
                        $("#xiaocai_url").removeAttr("href");
                        cnt = parseInt(cnt)+24;
                        animate();
                    }
                }
            },"json"
        );
        var c = document.getElementById('process'),
            process = 0,
            ctx = c.getContext('2d');
        // 画白色的圆
        ctx.beginPath();
        ctx.arc(100, 100, 60, 0, Math.PI * 2);
        ctx.closePath();
        ctx.fillStyle = '#fff';
        ctx.fill();
        function animate() {
            requestAnimationFrame(function() {
                process = process +1 ;
                drawCricle(ctx, process);
                if(process < parseInt($("#init").val())) {
                    animate();
                }
            });
        }
        function drawCricle(ctx, percent) {
            // 画进度环
            ctx.beginPath();
            ctx.moveTo(100, 100);
            ctx.arc(100, 100, 60, Math.PI * 1.5, Math.PI * (1.5 + 2 * percent / 100));
            ctx.closePath();
            ctx.fillStyle = '#FF9600';
            ctx.fill();
            // 画内填充圆
            ctx.beginPath();
            ctx.arc(100, 100, 55, 0, Math.PI * 2);
            ctx.closePath();
            ctx.fillStyle = '#3762FF';
            ctx.fill();
            // 填充文字
            ctx.font = "bold 11pt Microsoft YaHei";
            ctx.fillStyle = '#fff';
            ctx.textAlign = 'center';
            ctx.textBaseline = 'middle';
            ctx.moveTo(100, 100);
            ctx.fillText(process + "%", 100, 90);
            ctx.fillText("资料完成度", 100, 115);
        }
    }());
</script>

</html>