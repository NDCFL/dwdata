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
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title>电商认证支付</title>
    <link rel="stylesheet" type="text/css" href="<%=path%>/static/css/hui.css" />
</head>
<body style="background-color: #F5F5F5;">
<div class="hui-wrap">
    <div class="hui-common-title" style="margin-top:15px;">
        <div class="hui-common-title-txt" style="font-weight: bold;font-size: 16px;width: 100%;">电商认证服务费用</div>
    </div>
    <div class="hui-common-title">
        <div class="hui-common-title-txt" style="font-size: 36px;margin-right: 10px;">￥2.90</div>
    </div>
    <div class="hui-list" style="margin-top:22px;">
        <div style="height: 60px;">
            <div style="width: 20%;height: 100%;float: left;">
                <img src="<%=path%>/static/img/eror_1.png" alt="" style="width: 40px;height: 40px;float: right;margin-top: 10px;margin-right: 10px;" />
            </div>
            <div style="height: 60px;">
                <div style="width: 75%;height: 100%;float: left;margin-left: 10px;">
                    <div style="height: 30px;width: 100%;line-height: 42px;color: #313030;font-size: 14px;">
                        为保护您的账号密码安全,防止审核员恶意
                    </div>
                    <div style="height: 30px;width: 100%;line-height: 25px;color: #313030;font-size: 14px;">
                        登录账号,整个认证过程由您亲自完成。
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div style="text-align: center;margin: auto;margin-top: 12px;">
        <form role="form"  id="money" action="<%=path%>/pay/dspay">
            <div class="form-group">
                <input type="hidden" name="openid" value="${openid}">
            </div>
            <button type="submit" class="hui-button hui-button-large" style="background-color: #1BAD1A;color: #fff;font-size: 18px;width: 90%;text-align: center;margin: auto;margin-top: 20px;height: 50px;line-height: 50px;border-radius: 6px;">立即支付</button>
        </form>
    </div>
    <div class="hui-footer-text" style="text-align: center;color: #797979;margin-bottom: 0px;height: 400px;line-height: 700px;">支付安全由地网数据承担,最终解释权属于地网数据</div>
</div>
<script src="<%=path%>/static/js/hui.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>

