<%@ page import="com.dwsj.vo.UserPCVo" %><%--
  Created by IntelliJ IDEA.
  User: 17702
  Date: 2018/7/6
  Time: 16:08
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
    <title>首页</title>
    <link rel="stylesheet" href="<%=path%>/static/dwpcpage/css/index.css"/>
    <link rel="stylesheet" href="<%=path%>/static/dwpcpage/css/bootstrap.min.css">
    <link href="<%=path%>/static/js/layui/css/layui.css" type="text/css" rel="stylesheet"/>
    <style>
        a:hover{
            cursor: pointer;
        }
        a:link, a:visited, a:hover, a:active {
            text-decoration: none;
        }
        a:hover {
            color: #0583CE;
        }
        .addkehu:hover{
            color: #fff;
            cursor: pointer;
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
        .modal-footer {
            padding: 15px;
            text-align: right;
            border-top: 0px solid #e5e5e5;
        }
    </style>
</head>
<body oncontextmenu="doNothing()">
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
                <div class="laba" style="margin-top: 5px"><em></em><span class="laba_word">地网公告</span></div>
                <p class="gg_content">
                    请输入姓名、手机号、身份证号查询客户信息。
                </p>
            </div>
            <ul class="msl_ul">
                <li onclick="qbkh(this.id);" id="type_-1">
                    <a ><div class="msl_img img1"></div></a>
                        <span class="msl_span1"><a class="demo">全部客户：<i id="type1">0</i></a></span>

                </li>
                <li onclick="tbgz(this.id);" id="type_0">
                    <a ><div class="msl_img img2"></div></a>
                    <span class="msl_span1"><a>特别关注：<i id="type2">0</i></a></span>
                </li>
                <li onclick="yhzkh(this.id);" id="type_1">
                    <a ><div class="msl_img img3"></div></a>
                    <span class="msl_span1"><a>已合作：<i id="type3">0</i></a></span>
                </li>
                <li onclick="whzkh(this.id);" id="type_2">
                    <a ><div class="msl_img img4"></div></a>
                    <span class="msl_span1"><a>未合作：<i id="type4">0</i></a></span>
                </li>
            </ul>
            <!--地网小姐姐-->
            <div class="diwang">
                <p class="girl">加客服，进群领积分</p>
                <div class="erweima"><img src="<%=path%>/static/dwpcpage/images/erweima.jpg" alt=""/></div>
            </div>
            <!--ICP-->
            <div class="icp">鄂ICP备18008037号</div>
        </div>
        <div class="ms_right">
            <div class="msr_top">
                    <div class="userName dwinput">
                        <label for="name">姓名 &nbsp;</label>
                        <input type="text" name="name" id="name" onkeyup="getInfo_(this.value)" value="" placeholder="请输入姓名" />
                    </div>
                    <div class="userPhone dwinput">
                        <label for="call_num">手机号&nbsp; </label>
                        <input type="tel" name="name" id="call_num" maxlength="11" minlength="11" value=""
                               placeholder="请输入手机号"/>
                    </div>
                    <div class="userId dwinput">
                        <label for="idcard">身份证&nbsp; </label>
                        <input type="text" name="idcard" id="idcard" maxlength="18" minlength="18" value=""
                               placeholder="请输入身份证" style="width: 250px;"/>
                    </div>
                    <div class="add">
                        <a class="addkehu">
                            <span>
                                <img src="<%=path%>/static/dwpcpage/images/add_white.png" alt="地网数据" style="width: 20px;height: 20px;margin-left: -2px;margin-top: -2px"/>
                            </span>
                            <span style="margin-left: -4px">添加客户</span>
                        </a>
                    </div>
            </div>
            <div class="msr_center" id="app"  style="visibility:hidden">
                <div class="list1" v-for="(site,index) in sites">
                    <div class="list_top">
                        <div class="lt_left">
                            <div class="ltl_top">
                                <a style="color: #000">
                                    <i v-if="site.kehuType==1 || site.kehuType==3" class="ltlt_i1 iii" v-bind:id='"gou_"+site.id' onclick="gou(this.id);"></i>
                                    <i v-else class="ltlt_i iii" v-bind:id='"gou_"+site.id' onclick="gou(this.id);"></i>
                                    <span>已合作客户</span>
                                </a>
                            </div>
                            <div class="ltl_bottom">
                                <i>
                                    <img v-bind:src="site.kehuHeadImg" alt="" style="width: 112px;height: 133px;">
                                </i>
                            </div>
                        </div>
                        <div class="lt_right" @click='info(site.id)'>
                            <a style="color: #000">
                                <div class="ltr_top">
                                    <span style="width: 70px;float: left;margin-left: -4px">{{site.kehuName}}</span>
                                    <span class="span2" v-if="site.kehuSex==1"><img style="margin-top: 6px"
                                                                                    src="<%=path%>/static/dwpcpage/images/man.png"
                                                                                    alt="地网数据"/></span>
                                    <span class="span2" v-else><img style="margin-top: 6px"
                                                                    src="<%=path%>/static/dwpcpage/images/nv.png"
                                                                    alt="地网数据"/></span>
                                    <span class="span3">{{site.kehuAge}}岁</span>
                                </div>
                                <div class="ltr_center">
                                    <ul>
                                        <li class="li1">籍贯：<span>{{site.kehuAddress}}</span></li>
                                        <li class="li2">手机：<span>{{site.kehuPhone}}</span></li>
                                        <li class="li3">备注：<span v-if="site.remark.length>6">{{site.remark.substring(0,6)}}...</span><span
                                                v-else>{{site.remark}}</span></li>
                                    </ul>
                                </div>

                            </a>
                        </div>
                    </div>
                    <div class="list_bottom">
                        <a style="color: #000">
                            <i v-if="site.kehuType==0 || site.kehuType==3" class="ltb_star1 star" v-bind:id='"xin_"+site.id'
                               onclick="xin(this.id);"></i>
                            <i v-else class="ltb_star star" v-bind:id='"xin_"+site.id' onclick="xin(this.id);"></i>
                            <span class="ltb_id">身份证：{{site.kehuIdcard}}</span>
                        </a>
                    </div>
                </div>
                <div style="clear: both;"></div>
            </div>
            <div class="msr_bottom">
                <!-- 分页器 -->
                <ul class="pagination page-bar">
                    <li v-if="cur>1"><a v-on:click="cur--,pageClick()">上一页</a></li>
                    <li v-if="cur==1"><a class="banclick">上一页</a></li>
                    <li v-for="index in indexs" v-bind:class="{ 'active': cur == index}">
                        <a v-on:click="btnClick(index)">{{ index }}</a>
                    </li>
                    <li v-if="cur!=all"><a v-on:click="cur++,pageClick()">下一页</a></li>
                    <li v-if="cur == all"><a class="banclick">下一页</a></li>
                    <li><a>共<i>{{all}}</i>页</a></li>
                </ul>
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
<%--网站信息的修改--%>
<div class="modal fade" id="msg" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 360px;margin-left: 130px">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="addgiveModule">
                    该客户已存在
                </h4>
            </div>
            <div class="modal-body">
                <p>1、不可添加同姓名、手机号和身份证的客户</p>
                <p>2、可以添加同姓名、身份证号，不同手机号的客户</p>
            </div>
            <div class="modal-footer">
                <button type="button" id="update_phone_btn" class="btn btn-primary" style="border-top: 0px solid #e5e5e5;" data-dismiss="modal">
                    确认
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<input type="hidden" name="allpage" id="allpage" value="" style="position: absolute;top: 10px;">
<input type="hidden" name="currentpage" id="currentpage" value="1" style="position: absolute;top: 30px;">
<input type="hidden" name="type" id="type" value="" style="position: absolute;top: 30px;">
</body>
<script src="<%=path%>/static/dwpcpage/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/static/dwpcpage/js/bootstrap.min.js"></script>
<script src="<%=path%>/static/dwpcpage/js/index.js"></script>
<script src="<%=path%>/static/js/vue.min.js"></script>
<script src="<%=path%>/static/js/plugins/layer/layer.js"></script>
<script>
    function getInfo_(val) {
        refush($("#currentpage").val(), "",val);
    }
    function doNothing(){
        window.event.returnValue=false;
        return false;
    }
    var phone = "${userPCVo.phone}";
    if (!phone) {
        location.href = "/pcUser/loginPage";
    }
    var mphone = phone.substr(0, 3) + '****' + phone.substr(7);
    $('#phone_val').html(mphone);

    function info(val) {
        location.href = "/pcUser/info/" + val;
    }

    function keHuCount() {
        $.post(
            "/keHu/keHuCount",
            function (data) {
                $("#type1").html(data.allCount);
                $("#type2").html(data.gzCount);
                $("#type3").html(data.hzCount);
                $("#type4").html(data.whzCount);
                $("#kehuall").html(data.allCount);
                $("#kehujifen").html(data.jifen);
            }, "json"
        );
    }
    $.post(
        "/keHu/keHuCount",
        function (data) {
            if(parseInt(data.allCount)<=3){
                $.getJSON('/static/dwpcpage/jc.json', function(json){
                    layer.photos({
                        photos: json
                        ,shift:5
                        ,shade: 0.5
                        ,closeBtn:1
                    });
                });
            }
        }, "json"
    );
    function gou(val) {
        if (!$("#" + val).hasClass("ltlt_i")) {
            $("#" + val).removeClass("ltlt_i1");
            $("#" + val).addClass("ltlt_i");
            isactive = 1;
        } else {
            $("#" + val).removeClass("ltlt_i");
            $("#" + val).addClass("ltlt_i1");
            isactive = 0;
        }
        val = val.substring(4, val.length);
        $.post(
            "/keHu/updateStatus",
            {
                "id": val,
                "status": 1,
                "isactive": isactive
            }, function (msg) {
                if (msg.result == "success") {
                    keHuCount();
                } else {
                }

            }, "json"
        );
    }

    function tbgz(val) {
        $("#type").val("0,3");
        $("#currentpage").val(1);
        refush($("#currentpage").val(), "0,3",$("#name").val());
        $("#" + val + " a").addClass("demo");
        $("#" + val).siblings().find("a").removeClass("demo");
    }

    function yhzkh(val) {
        $("#type").val("1,3");
        $("#currentpage").val(1);
        refush($("#currentpage").val(), "1,3",$("#name").val());
        $("#" + val + " a").addClass("demo");
        $("#" + val).siblings().find("a").removeClass("demo");
    }

    function whzkh(val) {
        $("#type").val("-1,2,0");
        $("#currentpage").val(1);
        refush($("#currentpage").val(), "-1,2,0",$("#name").val());
        $("#" + val + " a").addClass("demo");
        $("#" + val).siblings().find("a").removeClass("demo");
    }

    function qbkh(val) {
        $("#type").val("");
        $("#currentpage").val(1);
        refush($("#currentpage").val(), "",$("#name").val());
        $("#" + val + " a").addClass("demo");
        $("#" + val).siblings().find("a").removeClass("demo");
    }

    function xin(val) {
        //不勾上
        if (!$("#" + val).hasClass("ltb_star")) {
            $("#" + val).removeClass("ltb_star1");
            $("#" + val).addClass("ltb_star");
            isactive = 1;
        } else {
            //勾上
            $("#" + val).removeClass("ltb_star");
            $("#" + val).addClass("ltb_star1");
            isactive = 0;
        }
        val = val.substring(4, val.length);
        $.post(
            "/keHu/updateStatus",
            {
                "id": val,
                "status": 0,
                "isactive": isactive
            }, function (msg) {
                if (msg.result == "success") {
                    keHuCount();
                } else {
                }
            }, "json"
        );
    }

    var vm = new Vue({
        el: '#app',
        data: {
            sites: ''
        }
    });
    var pageBar = new Vue({
        el: '.page-bar',
        data: {
            all: '', //总页数
            cur: 1//当前页码
        },
        watch: {
            cur: function (oldValue, newValue) {
                console.log(arguments);
            }
        },
        methods: {
            btnClick: function (data) {//页码点击事件
                if (data != this.cur) {
                    var type = $("#type").val();
                    this.cur = data;
                    refush(this.cur, type,$("#name").val());
                }
            },
            pageClick: function () {
                var type = $("#type").val();
                refush(this.cur, type,$("#name").val());
            }
        },
        computed: {
            indexs: function () {
                var left = 1;
                var right = this.all;
                var ar = [];
                if (this.all >= 5) {
                    if (this.cur > 3 && this.cur < this.all - 2) {
                        left = this.cur - 2
                        right = this.cur + 2
                    } else {
                        if (this.cur <= 3) {
                            left = 1
                            right = 5
                        } else {
                            right = this.all
                            left = this.all - 4
                        }
                    }
                }
                while (left <= right) {
                    ar.push(left)
                    left++
                }
                return ar
            }
        }
    })
    refush($("#currentpage").val(), "",$("#name").val());
    function refush(val, type,search) {
        keHuCount();
            $.post(
            "/keHu/keHuList",
            {
                "pageSize": 9,
                "pageIndex": val,
                "ids": type,
                "serchVal":search
            }, function (msg) {
                if(!msg){
                    location.href="/pcUser/loginPage";
                }else{
                    vm.$data.sites = msg.rows;
                    pageBar.$data.all = Math.ceil(parseFloat(parseInt(msg.total) / 9));
                    document.getElementById("app").style.visibility="visible";
                }
            }, "json"
        );
    }

    $(".add").click(function () {
        var kehuPhone = $("#call_num").val();
        var kehuIdcard = $("#idcard").val();
        var kehuName = $("#name").val();
        if (!kehuName) {
            layer.alert("姓名、身份证号或手机号输入有误");
            return;
        }
        if (!kehuIdcard) {
            layer.alert("姓名、身份证号或手机号输入有误");
            return;
        }
        if(kehuIdcard.length!=18){
            layer.alert("姓名、身份证号或手机号输入有误");
            return;
        }
        if (!kehuPhone) {
            layer.alert("姓名、身份证号或手机号输入有误");
            return;
        }
        if(parseInt(kehuIdcard.substring(6,10))<1920){
            layer.alert("身份证号格式有误");
            return;
        }
        var index = layer.msg("正在添加客户");
        $.post(
            "/keHu/keHuAddSave",
            {
                "kehuPhone": kehuPhone,
                "kehuIdcard": kehuIdcard,
                "kehuName": kehuName
            }, function (msg) {
                layer.close(index);
                refush(1, $("#type").val(),'');
                if (msg.result == "success") {
                    if(msg.message.indexOf("客户已存在")>-1){
                        $("#msg").modal("show");
                    }
                    $("#call_num").val("");
                    $("#idcard").val("");
                    $("#name").val("");
                } else {
                    layer.alert(msg.message);
                }
            }, "json"
        );
    });
    refushInfo();
    function refushInfo() {
        $.post(
            "/pcUser/refushInfo",
            function (data) {
                $("#kehujifen").html(data.jifen);
            },"json"
        );
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
    function g() {
        if (!$(this).hasClass("ltb_star")) {
            $(this).removeClass("ltb_star1");
            $(this).addClass("ltb_star");
        } else {
            $(this).removeClass("ltb_star");
            $(this).addClass("ltb_star1");

        }
    }
    var websocket;

    // 首先判断是否 支持 WebSocket
    if('WebSocket' in window) {
        websocket = new WebSocket("ws://192.168.3.18:8080/sendOne?user=${userPCVo.id}");
    } else if('MozWebSocket' in window) {
        websocket = new MozWebSocket("ws://192.168.3.18:8080/sendOne?user=${userPCVo.id}");
    } else {
        websocket = new SockJS("http://192.168.3.18:8080/sendAll?user=${userPCVo.id}");
    }
    // 打开连接时
    websocket.onopen = function(evnt) {
        console.log("  websocket.onopen  ");
    };
    // 收到消息时
    websocket.onmessage = function(evnt) {
//        alert(evnt.data);
//        layer.alert(evnt.data, {icon: 1},function () {
//            location.href="/index";
//        });
    };
    websocket.onerror = function(evnt) {
        console.log("  websocket.onerror  ");
    };
    websocket.onclose = function(evnt) {
        layer.alert("登录已失效，请重新登录!", {icon: 2},function () {
            location.href="/pcUser/loginPage";
        });
    };
</script>
</html>

