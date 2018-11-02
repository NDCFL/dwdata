<%--
  Created by IntelliJ IDEA.
  User: 17702
  Date: 2018/7/10
  Time: 15:19
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
    <title>公安局</title>
    <link rel="stylesheet" type="text/css" href="<%=path%>/static/dwpcpage/css/gongan.css" />
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
        .phone_css{
            text-align: left;height: 36px;line-height: 26px;
            color: #101010;
            font-size: 14px;
            padding-left: 10px;
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
                <span class="ul_right_span3 phone_val">166****5632 </span>
                <div class="scr_img2"><img src="<%=path%>/static/dwpcpage/images/arrow_down.png" alt=""/></div>
                <ul class="ul_down" style="display: none;">
                    <li style="width: 162px;"><a href="/pcUser/personl">个人中心</a></li>
                    <li style="width: 162px;"><a data-toggle="modal" data-target="#myModal">绑定微信</a></li>
                    <li style="width: 162px;"><a href="/pcUser/exit">修改密码</a></li>
                    <li style="width: 162px;"><a href="/pcUser/exit">退出账号</a></li>
                </ul>
            </li>
            <li class="sur_li2"><span id="" class="ul_right_span1">可用积分：<span id="kehujifen" class="jifen_val">${userPCVo.jifen}</span></span><span
                    class="ul_right_span2"><a href="/pcUser/personl" style="color: #fff;">充值</a></span></li>
        </ul>
    </div>
</div>
<!--主体部分-->
<div class="mengban"></div>
<div class="main">
    <div class="main_samll">
        <div class="main_toptop">
            <div class="main_top">
                <div class="mt_left">
                    <div class="mtl_left">
                        <img src="${kehuVo.kehuHeadImg}" alt="" id="headimg" />
                    </div>
                    <div class="mtl_right">
                        <div class="ltr_top">
                            <span style="width: 100px;font-size: 24px;" id="khname">${kehuVo.kehuName}</span>
                            <span class="span2"><img src="<%=path%>/static/dwpcpage/images/boy.png" alt="" /></span>
                            <span class="span3">${kehuVo.kehuAge}岁</span>
                        </div>
                        <div class="ltr_center">
                            <ul>
                                <li class="li2">籍贯：<span id="address">${kehuVo.kehuAddress}</span></li>
                                <li class="li2">手机：<span id="phone">${kehuVo.kehuPhone}</span><a style="color: #225599;margin-left: 5px" data-toggle="modal" data-target="#update_phone">更换手机号</a></li>
                                <li class="li2">微信：<span id="wx">未知</span></li>
                                <li class="li2">上次查询时间：<span id="uptime">${kehuVo.upTime}</span></li>
                                <li class="li2">身份证：<span>${kehuVo.kehuIdcard}</span></li>
                                <li class="li2 li3"><a style="color: #0583CE"><i class="ltlt_i iii" id="hz"></i><i class="ii">已合作客户</i></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="mt_right">
                    <ul class="mtr_ul" id="remarkList">
                        <li v-for="site in sites">
                            <span>{{site.content}}</span>
                            <span style="float: right;margin-right: 5px;color: #afa1a1">{{site.createTime | time}}记</span>
                        </li>
                    </ul>
                    <div class="bz" style="border-top: 1px solid #D4D4D4;"><i><img src="<%=path%>/static/dwpcpage/images/w.png" alt="" /></i><input type="text" maxlength="30" minlength="1" id="remark"  placeholder="添加备注，如家庭、金额、公司" /><button type="button" id="add_remark" class="btn btn-primary">提交</button></div>
                </div>
                <div style="clear: both;"></div>
            </div>
        </div>
        <div class="main_nav">
            <div class="mn_ul">
                <li><a href="/pcUser/info/${kehuVo.id}">负债查询</a></li>
                <li><a href="/pcUser/phone/${kehuVo.id}">手机通讯</a></li>
                <li class="demo4">
                    <a  href="/pcUser/police/${kehuVo.id}">公安局查人</a>
                </li>
                <li>
                    <a  href="/pcUser/dianshang/${kehuVo.id}">电商出行</a>
                </li>
                <li>
                    <a  href="/pcUser/shebao/${kehuVo.id}">社保/学历</a>
                </li>
                <li>
                    <a  href="/pcUser/font/${kehuVo.id}">文字与影像</a>
                </li>
            </div>
        </div>
        <!--选项卡部分-->
        <!--负债查询-->
        <div class="detail_cont">
            <div class="nav_small">
                <ul class="ns_ul">
                    <li style="color: #0583CE;background: #fff;">身份证查询</li>
                    <li style="margin-left: 0.5%;">名下手机号校验</li>
                    <li style="margin-left: 0.5%;">手机定位</li>
                </ul>
                <div class="tab_cont">
                    <div class="tab_box">
                        <ul class="tab_box1">
                            <li>
                                <label for="card_name">姓  &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;名</label>
                                <input type="text" id="card_name" type="" name="" value="" placeholder="请输入姓名"/>
                            </li>
                            <li>
                                <label for="idcard">身份证号</label>
                                <input type="text" id="idcard" type="" name="idcard" value="" placeholder="请输入身份证号"/>
                            </li>
                            <li><button id="card_bt">查询</button></li>
                        </ul>
                        <div class="table22">
                            <div class="table11">
                                <table class="table table-hover" id="mytab1">
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="tab_box" style="display: none;">
                        <ul class="tab_box1">
                            <li>
                                <label for="phone_name">姓  &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;名</label>
                                <input type="text" id="phone_name"  type="" name="phone_name" value="" placeholder="请输入姓名"/>
                            </li>
                            <li>
                                <label for="phone">手机号码</label>
                                <input type="text" type="" id="phone_phone" name="phone" value="" placeholder="请输入手机号"/>
                            </li>
                            <li><button id="phone_bt">查询</button></li>
                        </ul>
                        <div class="table22">
                            <div class="table11">
                                <table class="table table-hover" id="mytab2">

                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="tab_box" style="display: none;">
                        <ul class="tab_box2">
                            <li>客服私查优惠价<i style="color: red;">9.9</i>元/次：<i style="color: red;">详情地址+精准地图定位</i></li>
                            <div class="tab2_img"><img src="<%=path%>/static/dwpcpage/images/erweima.jpg" alt=""  style="width: 150px;height: 150px;"></div>
                            <li style="color: #9d9d9d;">扫一扫，加好友，地网小姐姐耐心为您服务</li>
                        </ul>
                    </div>
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
<div class="modal fade" id="info" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true">
    <div class="modal-dialog" style="width: 500px;border-radius: 0px;height: 400px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="info1">
                    校验结果
                </h4>
            </div>
            <div class="modal-body">
                <div style="text-align: center;margin: auto" >
                    <div style="width: 300px;height: 90px;text-align: center;margin: auto;">
                        <p style="text-align: left;padding-left: 40px;font-size: 16px;color: #101010" >
                            姓名:<span class="police_name"></span>
                        </p>
                        <p style="text-align: left;padding-left: 40px;font-size: 16px;color: #101010">
                            身份证:<span class="police_idcard"></span>
                        </p>
                        <p style="text-align: left;padding-left: 40px;font-size: 16px;color: #1BAD1A">
                            检验结果:姓名与身份证号匹配
                        </p>
                    </div>
                    <div style="width: 100%;height: 40px">

                    </div>
                    <div style="width: 100%;height: 250px">
                        <div style="width: 178px;height: 220px;float: left;">
                            <img src="" id="police_img" alt="" style="width: 100%">
                        </div>
                        <div style="width: 280px;height: 220px;float: left;">
                            <div class="phone_css ">
                                姓名:<span class="police_name"></span>
                            </div>
                            <div class="phone_css">
                                性别:<span id="sex"></span>
                            </div>
                            <div class="phone_css">
                                籍贯:<span id="jiguan"></span>
                            </div>
                            <div class="phone_css">
                                出生日期:<span id="riqi"></span>
                            </div>
                            <div class="phone_css ">
                                身份证号:<span class="police_idcard"></span>
                            </div>
                            <div class="phone_css">
                                签发机关:<span id="jg"></span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="info_1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true">
    <div class="modal-dialog" style="width: 500px;border-radius: 0px;height: 400px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="info2">
                    校验结果
                </h4>
            </div>
            <div class="modal-body">
                <div style="text-align: center;margin: auto">
                    <div style="width: 300px;height: 90px;text-align: center;margin: auto;">
                        <p style="text-align: left;padding-left: 40px;font-size: 16px;color: #101010">
                            姓名:<span id="no_name"></span>
                        </p>
                        <p style="text-align: left;padding-left: 40px;font-size: 16px;color: #101010">
                            身份证:<span id="no_card"></span>
                        </p>
                        <p style="text-align: left;padding-left: 40px;font-size: 16px;color: #CC0000">
                            检验结果:姓名与身份证号不匹配
                        </p>
                    </div>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="phone_module" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true">
    <div class="modal-dialog" style="width: 500px;border-radius: 0px;height: 400px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="phone_module1">
                    校验结果
                </h4>
            </div>
            <div class="modal-body">
                <div style="text-align: center;margin: auto">
                    <div style="width: 400px;height: 90px;text-align: center;margin: auto;">
                        <p style="text-align: left;padding-left: 40px;font-size: 16px;color: #101010">
                            姓名:<span id="phone_name_"></span>
                        </p>
                        <p style="text-align: left;padding-left: 40px;font-size: 16px;color: #101010">
                            手机号:<span id="phone_phone_"></span>
                        </p>
                        <p style="text-align: left;padding-left: 40px;font-size: 16px;" id="phone_msg">

                        </p>
                    </div>
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
                <h4 class="modal-title" >
                    修改手机号
                </h4>
            </div>
            <form class="form-horizontal">
                <div class="modal-body">
                    <div class="form-group">
                        <label class="col-sm-3 control-label" style="font-weight: 400;color: #000">新手机号</label>
                        <div class="col-sm-8">
                            <input type="hidden" value="${kehuVo.id}" name="id" id="id_val">
                            <input  name="phone" minlength="2" id="update_phone_val" placeholder="请输入新手机号" style="border-radius: 0px" maxlength="20" type="text" value="" class="form-control" required="" aria-required="true">
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
<input type="text" id="khid" value="${kehuVo.id}">
</body>
<script src="<%=path%>/static/dwpcpage/js/jquery.min.js"></script>
<script src="<%=path%>/static/js/plugins/layer/layer.js"></script>
<script src="<%=path%>/static/dwpcpage/js/gongan.js"></script>
<script src="<%=path%>/static/js/vue.min.js"></script>
<script src="<%=path%>/static/dwpcpage/js/bootstrap.min.js"></script>
<script src="<%=path%>/static/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="<%=path%>/static/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
<script src="<%=path%>/static/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="<%=path%>/static/js/pageJs/police.js"></script>
<script src="<%=path%>/static/js/pageJs/phone.js"></script>
<script>
    var phone = "${userPCVo.phone}";
    if (!phone) {
        location.href = "/pcUser/exit";
    }
    var mphone = phone.substr(0, 3) + '****' + phone.substr(7);
    $('.phone_val').html(mphone);

    function info(val) {
        location.href = "/pcUser/info/" + val;
    }
    refushInfo();
    function refushInfo() {
        $.post(
            "/pcUser/refushInfo",
            function (data) {
                $("#kehujifen").html(data.jifen);
            },"json"
        );
    }
    $("#update_phone_btn").click(function () {
        var phone = $("#update_phone_val").val();
        if(!phone){
            layer.alert("手机号输入有误");
            return;
        }
        if(phone.length!=11){
            layer.alert("手机号输入有误");
            return;
        }
        $.post(
            "/keHu/updatePhone",
            {
                "id":$("#id_val").val(),
                "phone":phone
            },function (data) {
                if(data.result=="success"){
                    layer.alert(data.message);
                    $("#phone").html(phone);
                    $("#update_phone").modal('hide');
                }else{
                    layer.alert(data.message);
                }
            },"json"
        );
    });
    var time = "${kehuVo.upTime}";
    $("#uptime").html(formartTime(time));
    var type = "${kehuVo.kehuType}";
    if(type==1 || type==3){
        $("#hz").removeClass("ltlt_i").addClass("ltlt_i1");
    }
    $("#hz").click(function () {
        var  isactive;
        if (!$("#hz").hasClass("ltlt_i")) {
            $("#hz").removeClass();
            $("#hz").addClass("ltlt_i iii");
            isactive = 1;
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
    function formartTime(value) {
        var date = new Date(value);
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        var d = date.getDate();
        var h = date.getHours();
        var mi = date.getMinutes();
        var ss = date.getSeconds();
        return y + '年' + (parseInt(m)<10?"0"+m:m) + '月' + (parseInt(d)<10?"0"+d:d) + '日';
    }
    var vm = new Vue({
        el: '#remarkList',
        data: {
            sites: ''
        }
    });
    refush();
    function refush() {
        $.post(
            "/pcUserRemark/getAllList",
            {
                "kehuId":"${kehuVo.id}"
            },
             function (msg) {
                vm.$data.sites = msg;
            }, "json"
        );
    }
    $("#add_remark").click(function () {
        var remark = $("#remark").val();
        if(!remark){
            layer.alert("备注不允许为空");
            return;
        }
        $.post(
            "/pcUserRemark/pcUserRemarkAddSave",
            {
              "content":remark,
              "kehuId":${kehuVo.id}
            },function (data) {
                $("#remark").val("");
                refush();
            },"json"
        );
    });
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

</script>

</html>
