<%--
  Created by IntelliJ IDEA.
  User: 17702
  Date: 2018/7/7
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path =request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>个人中心-API服务</title>
    <link rel="stylesheet" href="<%=path%>/static/dwpcpage/css/API.css" />
    <link rel="stylesheet" href="<%=path%>/static/dwpcpage/css/bootstrap.min.css">
    <style>
        .modal-content {
            position: relative;
            background-color: #fff;
            -webkit-background-clip: padding-box;
            background-clip: padding-box;
            border: 1px solid #999;
            border: 1px solid rgba(0, 0, 0, .2);
            border-radius: 0px;
            outline: 0;
            -webkit-box-shadow: 0 3px 9px rgba(0, 0, 0, .5);
            box-shadow: 0 3px 9px rgba(0, 0, 0, .5);
        }

        .modal {
            position: fixed;
            top: 30%;
            right: 0;
            bottom: 0;
            left: 0;
            z-index: 1050;
            display: none;
            overflow: hidden;
            -webkit-overflow-scrolling: touch;
            outline: 0;
        }
    </style>
</head>
<body>
<!--顶部导航条-->
<div class="shortcut">
    <div class="shortcut_small">
        <ul class="sc_ul_left">
            <li style="width: 180px;">
                <a href="<%=path%>/pcUser/homepage" style="color: #fff">
                    <div class="scl_img"></div>
                    <span class="ul_left_span1">我的客户：<i id="kehuall">${userPCVo.kehu}</i></span>
                </a>
            </li>
            <li data-toggle="modal" data-target="#myModal">微信公众号</li>
            <li data-toggle="modal" data-target="#myModal1">加入地网</li>
            <li><a href="/pcUser/price" style="color: #fff">功能定价</a></li>
            <li data-toggle="modal" data-target="#myModal1">联系我们</li>
        </ul>
        <ul class="sc_ul_right">
            <li class="sur_li1">
                <div class="scr_img1"><img src="<%=path%>/static/dwpcpage/images/sc_icon2.png" alt=""/></div>
                <span class="ul_right_span3 phone_val" >166****5632 </span>
                <div class="scr_img2"><img src="<%=path%>/static/dwpcpage/images/arrow_down.png" alt=""/></div>
                <ul class="ul_down" style="display: none;">
                    <li style="width: 162px;"><a href="/pcUser/personl">个人中心</a></li>
                    <li style="width: 162px;"><a data-toggle="modal" data-target="#myModal">绑定微信</a></li>
                    <li style="width: 162px;"><a href="/pcUser/exit">修改密码</a></li>
                    <li style="width: 162px;"><a href="/pcUser/exit">退出账号</a></li>
                </ul>
            </li>
            <li class="sur_li2"><span id="" class="ul_right_span1">可用积分：<span id="kehujifen">${userPCVo.jifen}</span></span><span
                    class="ul_right_span2">充值</span></li>
        </ul>
    </div>
</div><!--主体部分-->
<!--主体部分-->
<div class="main">
    <div class="main_samll">
        <div class="ms_left">
            <div class="gonggao">
                <div class="laba"><em></em><span class="laba_word">个人中心</span></div>
            </div>
            <ul class="msl_ul">
                <li><div class="msl_img img1"></div><span class="msl_span1"><a href="/pcUser/personl">我的积分</a></span></li>
                <li><div class="msl_img img2"></div><span class="msl_span1"><a href="/pcUser/price">功能价格</a></span></li>
                <li><div class="msl_img img3"></div><span class="msl_span1"><a href="/pcUser/find">查询记录</a></span></li>
                <li><div class="msl_img img4"></div><span class="msl_span1"><a href="/pcUser/api" class="demo">API服务</a></span></li>
            </ul>
            <!--地网小姐姐-->
            <div class="diwang">
                <p class="girl">地网小姐姐</p>
                <div class="erweima"><img src="<%=path%>/static/dwpcpage/images/erweima.jpg" alt="" /></div>
            </div>
            <!--ICP-->
            <div class="icp">鄂ICP备18008037号</div>
        </div>
        <div class="ms_right">
            <div class="msr_small">
                <div class="img"><img src="<%=path%>/static/dwpcpage/images/erweima.jpg" alt="" /></div>
                <p>扫一扫，加好友，地网小姐姐耐心为您服务</p>
            </div>
        </div>
    </div>
</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 500px;border-radius: 0px;height: 400px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    地网数据微信公众号
                </h4>
            </div>
            <div class="modal-body">
                <div style="text-align: center">
                    扫一扫，轻松手机管理客户
                </div>
                <div style="text-align: center;margin: auto">
                    <img src="<%=path%>/static/dwpcpage/images/gzh.jpg" alt="地网数据公众号">
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true">
    <div class="modal-dialog" style="width: 500px;border-radius: 0px;height: 400px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel1">
                    地网数据微信公众号
                </h4>
            </div>
            <div class="modal-body">
                <div style="text-align: center">
                    扫一扫，地网小姐姐耐心为您服务
                </div>
                <div style="text-align: center;margin: auto">
                    <img src="<%=path%>/static/dwpcpage/images/kf.jpg" alt="地网数据客服" style="height: 258px;width: 258px">
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
<script src="<%=path%>/static/dwpcpage/js/jquery.min.js"></script>
<script src="<%=path%>/static/dwpcpage/js/API.js"></script>
<script>
    var phone = "${userPCVo.phone}";
    if (!phone) {
        location.href = "/pcUser/exit";
    }
    var mphone = phone.substr(0, 3) + '****' + phone.substr(7);
    $('.phone_val').html(mphone);
</script>
</html>
