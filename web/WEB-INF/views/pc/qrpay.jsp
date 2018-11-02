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
    <title>地网数据认证</title>
</head>
<body>
</body>
<script src="<%=path %>/static/js/jquery.min.js"></script>
<script src="<%=path %>/static/js/main.js"></script>
<script src="<%=path %>/static/js/plugins/layer/layer.js"></script>

<script>
    $(function() {
        if (typeof WeixinJSBridge == "undefined") {
            if (document.addEventListener) {
                document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
            } else if (document.attachEvent) {
                document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
                document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
            }
        } else {
            onBridgeReady();
        }
    });

    function onBridgeReady() {
        WeixinJSBridge.invoke(
            'getBrandWCPayRequest', {
                "appId": "${appId}",
                "timeStamp": "${timeStamp}",
                "nonceStr": "${nonceStr}",
                "package": "${packages}",
                "signType": "MD5",
                "paySign": "${paySign}",
                "total_fee":"${userMoney}"
            }, function (res) {
                if (res.err_msg == "get_brand_wcpay_request:ok") {
                    var url = localStorage.getItem("url");
                    location.href = url;
                } else if (res.err_msg == "get_brand_wcpay_request:cancel") {
                    layer.alert("支付失败");
                    var url = localStorage.getItem("url");
                    location.href = url;
                } else if (res.err_msg == "get_brand_wcpay_request:fail") {
                    layer.alert("支付失败");
                    var url = localStorage.getItem("url");
                    location.href = url;
                }
            });
    }
</script>
</html>
