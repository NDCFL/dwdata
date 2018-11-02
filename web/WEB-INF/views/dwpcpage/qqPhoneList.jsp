<%--
  Created by IntelliJ IDEA.
  User: 17702
  Date: 2018/7/20
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: 17702
  Date: 2018/7/10
  Time: 15:23
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
    <title>通讯录详情页</title>
    <link rel="stylesheet" type="text/css" href="<%=path%>/static/dwpcpage/css/wenzi.css"/>
    <link rel="stylesheet" href="<%=path%>/static/css/bootstrap.min.css">
    <style>
        .btn-default {
            color: #333;
            background-color: #fff;
            border-color: #ccc;
            height: 33px;
        }

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

        .phone_css {
            text-align: left;
            height: 36px;
            line-height: 26px;
            color: #101010;
            font-size: 16px;
            padding-left: 10px;
        }

        a:hover {
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
                <span class="ul_right_span3 phone_val">166****5632 </span>
                <div class="scr_img2"><img src="<%=path%>/static/dwpcpage/images/arrow_down.png" alt=""/></div>
                <ul class="ul_down" style="display: none;">
                    <li style="width: 162px;"><a href="/pcUser/personl">个人中心</a></li>
                    <li style="width: 162px;"><a data-toggle="modal" data-target="#myModal">绑定微信</a></li>
                    <li style="width: 162px;"><a href="/pcUser/exit">修改密码</a></li>
                    <li style="width: 162px;"><a href="/pcUser/exit">退出账号</a></li>
                </ul>
            </li>
            <li class="sur_li2"><span id="" class="ul_right_span1">可用积分：<span id="kehujifen"
                                                                              class="jifen_val">${userPCVo.jifen}</span></span><span
                    class="ul_right_span2"><a href="/pcUser/personl" style="color: #fff;">充值</a></span></li>
        </ul>
    </div>
</div>
<!--主体部分-->
<div class="mengban" >
    <div style="height:60px;width: 1200px;text-align: center;margin: auto;margin-top: 40px;">
        <div style="height:60px;float: left;text-align: right;font-size: 36px;line-height: 80px;text-align: center;margin-left: 500px">
            ${kehuVo.kehuName}的通讯录
        </div>
        <div style="width: 19%;height:60px;float: left;font-size: 12px;color: #797979;line-height: 100px">
            更新时间：<span id="time">2018年08月08日</span>
        </div>
        <div style="width: 15%;height:30px;float: left;height: 60px">
            <button style="height: 40px;width: 80px;background-color: #0583CE;border: none;border-radius: 4px;color: #fff;margin-top: 15px" id="btn">
                下载
            </button>
        </div>
    </div>
    <div style="width:1200px;height: 182px;text-align: center;margin: auto;margin-top: 40px;">
        <div style="width: 147px;height: 100%;float: left;float: left">
            <img src="${kehuVo.kehuHeadImg}" alt="" style="width: 100%;height: 100%">
        </div>
        <div style="width: 500px;height: 182px;float: left;margin-left: 10px">
            <div style="width: 100%;height: 30%;padding-top: -15px;">
                <span style="font-size: 28px;float: left">${kehuVo.kehuName}</span>
                <span style="float: left">
                    <img src="<%=path%>/static/dwpcpage/images/nv.png" alt="" id="sex_val"
                         style="width: 25px;height: 25px;margin-top:5px;margin-left: 15px">
                </span>
                <span style="font-size: 28px;float: left;margin-left: 15px">${kehuVo.kehuAge}岁</span>
            </div>
            <div style="width: 100%;height: 25%">
                <span style="font-size: 16px;float: left;line-height: 45px">QQ:${qqPhoneVo.qq}</span>
                <span style="font-size: 16px;float: left;line-height: 45px;margin-left: 30px">呢称：${qqPhoneVo.nickName}</span>
            </div>
            <div style="width: 100%;height: 25%">
                <span style="font-size: 16px;float: left;line-height: 45px">通讯录绑定手机号：${qqPhoneVo.phone}</span>
            </div>
            <div style="width: 100%;height: 25%">
                <span style="font-size: 16px;float: left;line-height: 45px">身份证号：${kehuVo.kehuIdcard}</span>
            </div>
        </div>
    </div>
    <div style="width:1200px;height: 182px;text-align: center;margin: auto;margin-top: 40px">
        <div style="width: 100%;border-top: 1px dashed #8C8C8C;padding-top: 30px">
            <table id="mytab" class="table table-hover table-striped" style="margin-top: 20px">

            </table>
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
<%--网站信息的修改--%>
<div class="modal fade" id="update_phone" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 400px">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title">
                    修改手机号
                </h4>
            </div>
            <form class="form-horizontal">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-3 control-label" style="font-weight: 400;color: #000">新手机号</label>
                        <div class="col-sm-8">
                            <input type="hidden" value="${kehuVo.id}" name="id" id="id_val">
                            <input name="phone" minlength="2" id="update_phone_val" placeholder="请输入新手机号"
                                   style="border-radius: 0px" maxlength="20" type="text" value="" class="form-control"
                                   required="" aria-required="true">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                    </button>
                    <button type="button" id="update_phone_btn" class="btn btn-primary">
                        确认修改
                    </button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<input type="hidden" id="id" value="${qqPhoneVo.id}">
</body>
<script src="<%=path%>/static/dwpcpage/js/jquery.min.js"></script>
<script src="<%=path%>/static/dwpcpage/js/wenzi.js"></script>
<script src="<%=path%>/static/dwpcpage/js/bootstrap.min.js"></script>
<script src="<%=path%>/static/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="<%=path%>/static/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
<script src="<%=path%>/static/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="<%=path%>/static/js/plugins/layer/layer.js"></script>
<script src="<%=path%>/static/js/pageJs/qqphonelist.js"></script>
<script src="<%=path%>/static/js/vue.min.js"></script>
<script src="<%=path%>/static/dwpcpage/js/jquery.table2excel.min.js"></script>
<script>
    var phone = "${userPCVo.phone}";
    if (!phone) {
        location.href = "/pcUser/exit";
    }
    var mphone = phone.substr(0, 3) + '****' + phone.substr(7);
    $('.phone_val').html(mphone);
    $("#time").html(formartTime("${qqPhoneVo.createTime}"));
    var sex_val = "${kehuVo.kehuSex}";
    if(sex_val==1){
        $("#sex_val").attr("src","<%=path%>/static/dwpcpage/images/man.png");
    }else{
        $("#sex_val").attr("src","<%=path%>/static/dwpcpage/images/nv.png");
    }
    $("#time").html(formartTime("${qqPhoneVo.createTime}"));
    function formartTime(value) {
        var date = new Date(value);
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        var d = date.getDate();
        var h = date.getHours();
        var mi = date.getMinutes();
        var ss = date.getSeconds();
        return y + '年' + (parseInt(m) < 10 ? "0" + m : m) + '月' + (parseInt(d) < 10 ? "0" + d : d) + '日';
    }

    Vue.filter('time',
        function (value) {
            var date = new Date(value);
            Y = date.getFullYear(),
                m = date.getMonth() + 1,
                d = date.getDate();
            if (m < 10) {
                m = '0' + m;
            }
            if (d < 10) {
                d = '0' + d;
            }
            var t = Y + '-' + m + '-' + d;
            return t;
        }
    );
    $("#hz").click(function () {
        var isactive;
        if (!$("#hz").hasClass("ltlt_i")) {
            $("#hz").removeClass();
            $("#hz").addClass("ltlt_i iii");
        } else {
            $("#hz").removeClass();
            $("#hz").addClass("ltlt_i1 iii");
            isactive = 0;
        }
        $.post(
            "/keHu/updateStatus",
            {
                "id": ${kehuVo.id},
                "status": 1,
                "isactive": isactive
            }, function (msg) {
            }, "json"
        );
    });
    refushInfo();

    function refushInfo() {
        $.post(
            "/pcUser/refushInfo",
            function (data) {
                $("#kehujifen").html(data.jifen);
            }, "json"
        );
    }
</script>
<script>
    $(function() {
        $("#btn").click(function(){
            $("#mytab").table2excel({
                exclude: ".noExl",
                name: "Apptoapi Excel",
                filename: "【地网数据】${kehuVo.kehuName}通讯录",
                exclude_img: true,
                exclude_links: true,
                exclude_inputs: true
            });
        });
    });
</script>

</html>
