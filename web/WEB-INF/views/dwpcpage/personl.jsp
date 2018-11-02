<%--
  Created by IntelliJ IDEA.
  User: 17702
  Date: 2018/7/7
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path =request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>个人中心-我的积分</title>
    <link rel="stylesheet" href="<%=path%>/static/dwpcpage/css/person.css" />
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
        .addborder{
            border: 2px solid #0583CE;
        }
        a:hover{
            cursor: pointer;
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
                <span class="ul_right_span3" id="phone_val">166****5632 </span>
                <div class="scr_img2"><img src="<%=path%>/static/dwpcpage/images/arrow_down.png" alt=""/></div>
                <ul class="ul_down" style="display: none;border-top: solid 1px #fff">
                    <a href="/pcUser/personl" style="color: #fff"><li style="width: 162px;">个人中心</li></a>
                    <a data-toggle="modal" data-target="#myModal" style="color: #fff"><li style="width: 162px;">绑定微信</li></a>
                    <a href="/pcUser/exit" style="color: #fff"><li style="width: 162px;">修改密码</li></a>
                    <a href="/pcUser/exit" style="color: #fff"><li style="width: 162px;">退出账号</li></a>
                </ul>
            </li>
            <li class="sur_li2"><span id="" class="ul_right_span1">可用积分：<span id="kehujifen">${userPCVo.jifen}</span></span><span
                    class="ul_right_span2"><a href="/pcUser/personl" style="color: #fff;">充值</a></span></li>
        </ul>
    </div>
</div>
<!--主体部分-->
<div class="main">
    <div class="main_samll">
        <div class="ms_left">
            <div class="gonggao">
                <div class="laba"><em></em><span class="laba_word">个人中心</span></div>
            </div>
            <ul class="msl_ul">
                <li><div class="msl_img img1"></div><span class="msl_span1"><a href="/pcUser/personl" class="demo">我的积分</a></span></li>
                <li><div class="msl_img img2"></div><span class="msl_span1"><a href="/pcUser/price">功能价格</a></span></li>
                <li><div class="msl_img img3"></div><span class="msl_span1"><a href="/pcUser/find">查询记录</a></span></li>
                <li><div class="msl_img img4"></div><span class="msl_span1"><a href="/pcUser/api">API服务</a></span></li>
            </ul>
            <!--地网小姐姐-->
            <div class="diwang">
                <p class="girl">加客服，进群领积分</p>
                <div class="erweima"><img src="<%=path%>/static/dwpcpage/images/erweima.jpg" alt="" /></div>
            </div>
            <!--ICP-->
            <div class="icp">鄂ICP备18008037号</div>
        </div>
        <div class="ms_right">
            <div class="r_top">
                <div class="logo">
                    <img src="<%=path%>/static/dwpcpage/images/logo.jpg" alt="" />
                </div>
                <div class="logo_right">
                    <div class="rt" style="margin-top: -10px">
                        <a>地网数据</a>欢迎您，<span class="phone_val">166****2839</span>
                    </div>
                    <div class="rb" style="margin-top: 10px;font-size: 16px">
                        可用积分：<span class="span1 jifen_val" >${userPCVo.jifen}</span>
                        <i><img src="<%=path%>/static/dwpcpage/images/gift.png" alt="图片加载失败" /></i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="span3" style="margin-left: -20px">赠送积分总计：<span class="span2" id="give_sco">${userPCVo.giveJifen}</span></span>
                    </div>
                </div>
            </div>
            <div class="r_center" id="list" style="visibility:hidden">
                    <div v-for="(jifen,index) in lists" class="box1 addborder" :id="'border_'+ index" v-if="index==1"  onclick="addborder(this.id)">
                        <span >{{jifen.money}}</span>元<span style="font-size: 14px">（{{jifen.jifen}}分）</span>
                        <input type="hidden" name="pay_money" :id="'pay_border_'+ index" :value="jifen.money">
                        <div style="clear: both;font-size: 12px;margin-top: -10px;float: right;color: red">另送{{jifen.zsJiFen}}积分</div>
                    </div>
                    <div class="box1" :id="'border_'+ index" v-else-if="index>1"  onclick="addborder(this.id)">
                        <span >{{jifen.money}}</span>元<span style="font-size: 14px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                        <input type="hidden" name="pay_money" :id="'pay_border_'+ index" :value="jifen.money">
                        <div style="clear: both;font-size: 12px;margin-top: -10px;float: right;color: red">{{jifen.days}}天内免费使用</div>
                    </div>
                    <div class="box1" :id="'border_'+ index"  v-else  onclick="addborder(this.id)">
                        <span >{{jifen.money}}</span>元<span style="font-size: 14px">（{{jifen.jifen}}分）&nbsp;&nbsp;</span>
                        <input type="hidden" name="pay_money" :id="'pay_border_'+ index" :value="jifen.money">
                        <span style="clear: both;font-size: 12px;margin-top: -10px;float: right;color: red">另送{{jifen.zsJiFen}}积分</span>
                    </div>
            </div>
            <div class="r_bottom">
                <div class="box2 box21" id ="wxPay">
                    <span><img src="<%=path%>/static/dwpcpage/images/wx_pay.png" alt="" /></span>
                </div>
                <div class="box2 box22" id="zfbPay">
                    <span><img src="<%=path%>/static/dwpcpage/images/ali.png" alt="" /></span>
                </div>
                <div class="box2 box23" data-toggle="modal" data-target="#myModal1">
                    <span><img src="<%=path%>/static/dwpcpage/images/weixin.png" alt="" /></span><i>客服代充</i>
                </div>
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
                    地网数据微信
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
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true">
    <div class="modal-dialog" style="width: 500px;border-radius: 0px;height: 400px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel2">
                    支付
                </h4>
            </div>
            <div class="modal-body">
                <div style="text-align: center;font-size: 28px" id="mm">
                    扫一扫，开始支付
                </div>
                <div style="text-align: center;margin: auto;">
                    <img src="<%=path%>/static/dwpcpage/images/kf.jpg" id="payimg" alt="地网数据客服" style="height: 258px;width: 258px">
                    <p style="color: red;margin-top: -20px">*不支付截图分享支付</p>
                </div>
                <div>
                    <div>
                        <button type="button" id="paySuc" class="btn btn-primary"  style="background-color: #0F8EEC;margin-left: 89px;width: 120px">
                            已完成支付
                        </button>
                        <button type="button" id="fail"  class="btn btn-default" data-dismiss="modal" style="margin-left: 40px;width: 120px">支付遇到问题
                        </button>
                    </div>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="pay" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true">
    <div class="modal-dialog" style="width: 500px;border-radius: 0px;height: 400px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="pay_">
                    地网数据微信
                </h4>
            </div>
            <div class="modal-body">
                <div style="text-align: center">
                    扫一扫，加客服充值使用
                </div>
                <div style="text-align: center;margin: auto">
                    <img src="<%=path%>/static/dwpcpage/images/kf.jpg" alt="地网数据客服" style="height: 258px;width: 258px">
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<input type="text" name="allmoney" id="allmoney" value="99">
<input type="text" name="orderId" id="orderId" value="">
</body>
<script src="<%=path%>/static/dwpcpage/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/static/dwpcpage/js/bootstrap.min.js"></script>
<script src="<%=path%>/static/dwpcpage/js/person.js"></script>
<script src="https://cdn.bootcss.com/vue/2.2.2/vue.min.js"></script>
<script src="<%=path%>/static/js/plugins/layer/layer.js"></script>
<script>
    var phone = "${userPCVo.phone}";
    if (!phone) {
        location.href = "/pcUser/exit";
    }
    var mphone = phone.substr(0, 3) + '****' + phone.substr(7);
    $('.ul_right_span3').text(mphone);
    $('.phone_val').text(mphone);
    refushInfo();

    $.post(
        "/jiFen/getJiFen",
        function (msg) {
            new Vue({
                el: '#list',
                data: {
                    lists: msg
                }
            });
            document.getElementById("list").style.visibility="visible"
        },"json"
    );
    $("#zfbPay").click(function () {
        layer.msg("正在开发中...");
    });
    function addborder(val) {
        $("#allmoney").val($("#pay_"+val).val());
        $("#"+val).addClass("addborder");
        $("#"+val).siblings().removeClass("addborder");
    }
    $("#wxPay").click(function () {
        if(parseInt($("#allmoney").val())>1000){
            $("#pay").modal('show');
        }else{
            $.post(
                "/pay/rqcode",
                {
                    "userMoney":$("#allmoney").val()
                },
                function (data) {
                    if(data.result=="success"){
                        //支付成功
                        $("#payimg").attr("src",data.imgUrl);
                        $("#mm").html($("#allmoney").val()+"元");
                        $("#myModal2").modal('show');
                        $("#orderId").val(data.orderId);
                    }else{
                        layer.alert("服务器繁忙");
                    }
                },"json"
            );
        }
    });
    $("#fail").click(function () {
        $("#myModal2").modal("hide");
        $("#myModal1").modal("show");
    });

    $("#paySuc").click(function () {
        refushInfo();
    });
    function refushInfo() {
        $.post(
            "/pcUser/refushInfo",
            function (data) {
                $(".jifen_val").html(data.jifen);
                $("#kehujifen").html(data.jifen);
                $("#give_sco").html(data.giveJifen);
                $("#myModal2").modal("hide");
            },"json"
        );
    }
</script>

</html>
