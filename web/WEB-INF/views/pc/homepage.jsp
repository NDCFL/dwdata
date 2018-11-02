<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/6/26 0026
  Time: 下午 7:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <title>主页</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <jsp:include page="../common/css.jsp"></jsp:include>
    <link href="<%=path%>/pc/resources/css/jquery-ui-themes.css" type="text/css" rel="stylesheet"/>
    <link href="<%=path%>/pc/resources/css/axure_rp_page.css" type="text/css" rel="stylesheet"/>
    <link href="<%=path%>/pc/data/styles.css" type="text/css" rel="stylesheet"/>
    <link href="<%=path%>/pc/files/homepage/styles.css" type="text/css" rel="stylesheet"/>
    <link href="<%=path%>/static/css/card.css" type="text/css" rel="stylesheet"/>
    <link href="<%=path%>/static/js/layui/css/layui.css" type="text/css" rel="stylesheet"/>
    <jsp:include page="../common/js.jsp"></jsp:include>
    <script src="<%=path%>/pc/files/homepage/data.js"></script>
    <style>
        .card:hover{
            border: solid 0.5px rgb(5,131,206);
        }
    </style>
</head>
<body style="background-color: #ecf0f1;overflow-x: hidden;overflow-y: hidden">
<div id="base" class="">
    <!-- Unnamed (矩形) -->
    <div id="u0" class="ax_default card_div_1">
        <div class="card_div_2">
            <div class="card_div_3">
                <div class="card_div_4">
                    <img src="<%=path%>/static/img/admin.png" alt="" class="card_div_5">
                </div>
                <div class="card_div_6">
                    <input type="text" id="name" class="card_div_7" placeholder="请输入姓名">
                </div>
            </div>
            <div class="card_div_8">
                <div class="card_div_9">
                    <img src="<%=path%>/static/img/card.png" alt="" class="card_div_10">
                </div>
                <div class="card_div_11">
                    <input type="text" class="card_div_12"  id="idcard" placeholder="请输入身份证号">
                </div>
            </div>
            <div class="card_div_13">
                <div class="card_div_14">
                    <img src="<%=path%>/static/img/iPhone.png" alt="" class="card_div_15">
                </div>
                <div class="card_div_16">
                    <input type="text" class="card_div_17" id="call_num" placeholder="请输入手机号">
                </div>
            </div>
            <div class="card_div_18">
                <button class="card_div_19" id="addkehu">添加客户</button>
            </div>
        </div>
        <div class="card_div_20 cardlist" id="app">
            <div class="card card_div_21" v-for="(site,index) in sites">
                <div class="card_div_22">
                    <div class="card_div_23">
                        <div class="card_div_24">
                           <span v-if="site.kehuType==1 || site.kehuType==3">
                                <img src="<%=path%>/static/img/gou_active.png" v-bind:id='"gou_"+site.id' onclick="gou(this.id);" alt="" class="card_div_25">
                           </span>
                            <span v-else>
                                <img src="<%=path%>/static/img/border.png" v-bind:id='"gou_"+site.id' onclick="gou(this.id);" alt="" class="card_div_25">
                           </span>
                            <div class="card_div_26">
                                已合作用户
                            </div>
                        </div>
                        <div class="card_div_27">
                            <img v-bind:src="site.kehuHeadImg" alt="" class="card_div_28">
                        </div>
                    </div>
                    <a @click='info(site.id)'>
                        <div class="card_div_29">
                            <div class="card_div_30">
                                <span class="card_div_31">{{site.kehuName}}</span>
                                <span style="float: left;">
                                <img src="<%=path%>/static/img/name_blue.png" alt="" class="card_div_32">
                            </span>
                                <span class="card_div_33">{{site.kehuAge}}岁</span>

                            </div>
                            <div class="card_div_34">
                            <span style="float: left;">
                                <img src="<%=path%>/static/img/home.png" alt="" class="card_div_35">
                            </span>
                                <p v-if="site.kehuAddress.length>11" style="font-size: 14px;text-align: left;">{{site.kehuAddress}}</p>
                                <p v-else class="card_div_36">{{site.kehuAddress}}</p>
                            </div>
                            <div class="card_div_34">
                           <span style="float: left;">
                                <img src="<%=path%>/static/img/pone_grey.png" alt="" class="card_div_35">
                            </span>
                                <span class="card_div_36">{{site.kehuPhone}}</span>
                            </div>
                            <div class="card_div_34">
                           <span style="float: left;">
                                <img src="<%=path%>/static/img/time_grey.png" alt="" class="card_div_35">
                            </span>
                                <span class="card_div_36">上次查询:{{site.upTime | time}}</span>
                            </div>
                            <div class="card_div_34">
                           <span style="float: left;">
                                <img src="<%=path%>/static/img/bz.png" alt="" class="card_div_35">
                            </span>
                                <span v-if="site.remark.length>6" class="card_div_36">{{site.remark.substring(0,6)}}...</span>
                                <span v-else class="card_div_36">{{site.remark}}</span>
                            </div>
                        </div>
                    </a>
                </div>
                <div class="card_div_37">
                    <span v-if="site.kehuType==0 || site.kehuType==3">
                        <img src="<%=path%>/static/img/xin_active.png" v-bind:id='"xin_"+site.id'  onclick="xin(this.id);" alt="" class="card_div_38">
                    </span>
                    <span v-else>
                        <img src="<%=path%>/static/img/xin.png" v-bind:id='"xin_"+site.id'  onclick="xin(this.id);" alt="" class="card_div_38">
                    </span>
                    <span>
                    <img src="<%=path%>/static/img/ideacard.png" alt="" class="card_div_39">
                </span>
                    <span class="card_div_40">{{site.kehuIdcard}}</span>
                </div>
            </div>
        </div>
        <div style="height: 120px;width: 100%;background-color: #E4E4E4;">
            <div style="width: 100%;height: 60px;float: right;">
                <div class="page-bar" style="margin-top: 10px;margin-left:560px;height: 45px;width: 450px">
                    <ul>
                        <li v-if="cur>1"><a v-on:click="cur--,pageClick()">上一页</a></li>
                        <li v-if="cur==1"><a class="banclick">上一页</a></li>
                        <li v-for="index in indexs"  v-bind:class="{ 'active': cur == index}">
                            <a v-on:click="btnClick(index)">{{ index }}</a>
                        </li>
                        <li v-if="cur!=all"><a v-on:click="cur++,pageClick()">下一页</a></li>
                        <li v-if="cur == all"><a class="banclick">下一页</a></li>
                        <li><a>共<i>{{all}}</i>页</a></li>
                    </ul>
                </div>
            </div>
            <div style="text-align: center;font-size: 14px;color:#797979;height: 30px;line-height: 40px">
                2017-2018 192.168.3.18:8080 All Rights Reserved 一步信息公司 版权所有 鄂ICP备18008037号
            </div>
        </div>
    </div>

    <!-- Unnamed (topbar) -->
    <div  style="position: absolute;left:501px;top: 628px;width: 30px;height: 30px;z-index: 9999">
        <img src="<%=path%>/pc/images/homepage/u323.png" alt="" style="width: 30px;height: 30px;">
    </div>
    <!-- Unnamed (矩形) -->
    <div id="u3" class="ax_default box_1">
        <div id="u3_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u4" class="text" style="display: none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <!-- Unnamed (矩形) -->
    <div id="u5" class="ax_default _二级标题">
        <div id="u5_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u6" class="text" style="visibility: visible;">
            <p><span style="color:#999999;">可用积分:</span><span style="color:#E74C3C;">${userPCVo.jifen}</span></p>
        </div>
    </div>

    <!-- Unnamed (图片) -->
    <div id="u7" class="ax_default _图片" title="修改密码">
        <img id="u7_img" class="img " src="<%=path%>/pc/images/homepage/u7.png"/>
        <!-- Unnamed () -->
        <div id="u8" class="text" style="display: none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <!-- Unnamed (图片) -->
    <div id="u9" class="ax_default _图片" title="退出账号">
        <img id="u9_img" class="img " src="<%=path%>/pc/images/homepage/u9.png"/>
        <!-- Unnamed () -->
        <div id="u10" class="text" style="display: none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <!-- Unnamed (矩形) -->
    <div id="u11" class="ax_default box_2">
        <div id="u11_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u12" class="text" style="display: none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <!-- Unnamed (图片) -->
    <div id="u13" class="ax_default _图片">
        <img id="u13_img" class="img " src="<%=path%>/pc/images/homepage/u13.png"/>
        <!-- Unnamed () -->
        <div id="u14" class="text" style="display: none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <!-- Unnamed (图片) -->
    <div id="u15" class="ax_default _图片 ax_default_hidden" style="display: none; visibility: hidden">
        <img id="u15_img" class="img " src="<%=path%>/pc/images/homepage/u15.png"/>
        <!-- Unnamed () -->
        <div id="u16" class="text" style="display: none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <!-- Unnamed (矩形) -->
    <div id="u17" class="ax_default label">
        <div id="u17_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u18" class="text" style="visibility: visible;">
            <p><span>价格</span></p>
        </div>
    </div>

    <!-- Unnamed (热区) -->
    <div id="u19" class="ax_default">
    </div>

    <!-- Unnamed (图片) -->
    <div id="u20" class="ax_default _图片">
        <img id="u20_img" class="img " src="<%=path%>/pc/images/homepage/u20.png"/>
        <!-- Unnamed () -->
        <div id="u21" class="text" style="display: none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <!-- Unnamed (图片) -->
    <div id="u22" class="ax_default _图片 ax_default_hidden" style="display: none; visibility: hidden">
        <img id="u22_img" class="img " src="<%=path%>/pc/images/homepage/u22.png"/>
        <!-- Unnamed () -->
        <div id="u23" class="text" style="display: none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <!-- Unnamed (矩形) -->
    <div id="u24" class="ax_default label">
        <div id="u24_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u25" class="text" style="visibility: visible;">
            <p><span>查询记录</span></p>
        </div>
    </div>

    <!-- Unnamed (热区) -->
    <div id="u26" class="ax_default">
    </div>

    <!-- Unnamed (矩形) -->
    <div id="u27" class="ax_default box_2">
        <div id="u27_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u28" class="text" style="display: none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <!-- Unnamed (图片) -->
    <div id="u29" class="ax_default _图片">
        <img id="u29_img" class="img " src="<%=path%>/pc/images/homepage/u29.png"/>
        <!-- Unnamed () -->
        <div id="u30" class="text" style="display: none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <!-- Unnamed (图片) -->
    <div id="u31" class="ax_default _图片">
        <img id="u31_img" class="img " src="<%=path%>/pc/images/homepage/u31.png"/>
        <!-- Unnamed () -->
        <div id="u32" class="text" style="display: none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <!-- Unnamed (矩形) -->
    <div id="u33" class="ax_default _三级标题">
        <div id="u33_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u34" class="text" style="visibility: visible;">
            <p><span id="phone">手机号</span></p>
        </div>
    </div>

    <!-- Unnamed (矩形) -->
    <div id="u35" class="ax_default _三级标题">
        <div id="u35_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u36" class="text" style="visibility: visible;">
            <p><span>地网数据</span></p>
        </div>
    </div>

    <!-- Unnamed (图片) -->
    <div id="u37" class="ax_default _图片">
        <img id="u37_img" class="img " src="<%=path%>/pc/images/homepage/u37.png"/>
        <!-- Unnamed () -->
        <div id="u38" class="text" style="display: none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <!-- Unnamed (图片) -->
    <div id="u39" class="ax_default _图片 ax_default_hidden" style="display: none; visibility: hidden">
        <img id="u39_img" class="img " src="<%=path%>/pc/images/homepage/u39.png"/>
        <!-- Unnamed () -->
        <div id="u40" class="text" style="display: none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <!-- Unnamed (矩形) -->
    <div id="u41" class="ax_default label">
        <div id="u41_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u42" class="text" style="visibility: visible;">
            <p><span>API服务</span></p>
        </div>
    </div>

    <!-- Unnamed (热区) -->
    <div id="u43" class="ax_default">
    </div>

    <!-- Unnamed (图片) -->
    <div id="u44" class="ax_default _图片 ax_default_hidden"  style="display: none; visibility: hidden;">
        <img id="u44_img" class="img " src="<%=path%>/pc/images/homepage/u44.png"/>
        <!-- Unnamed () -->
        <div id="u45" class="text" style="display: none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <!-- Unnamed (热区) -->
    <div id="u46" class="" onclick="javascript:location.href='/pcUser/exit'">
    </div>

    <!-- Unnamed (矩形) -->
    <div id="u47" class="ax_default _一级标题">
        <div id="u47_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u48" class="text" style="visibility: visible;">
            <p><span>${userPCVo.kehu}人</span></p>
        </div>
    </div>

    <!-- Unnamed (矩形) -->
    <div id="u49" class="ax_default _一级标题">
        <div id="u49_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u50" class="text" style="visibility: visible;">
            <p><span>我的客户</span></p>
        </div>
    </div>

    <!-- Unnamed (矩形) -->
    <div id="u51" class="ax_default _一级标题 ax_default_hidden" style="display: none; visibility: hidden">
        <div id="u51_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u52" class="text" style="visibility: visible;">
            <p><span>我的客户</span></p>
        </div>
    </div>

    <!-- Unnamed (热区) -->
    <div id="u53" class="ax_default" title="进入我的客户库">
    </div>

    <!-- Unnamed (矩形) -->
    <div id="u54" class="ax_default label ax_default_hidden" style="display: none; visibility: hidden">
        <a href="/pcUser/exit">
            <div id="u54_div" class=""></div>
            <!-- Unnamed () -->
            <div id="u55" class="text" style="visibility: visible;">
                <p><span>退出账号</span></p>
            </div>

        </a>
    </div>

    <!-- Unnamed (矩形) -->
    <div id="u56" class="ax_default label ax_default_hidden" style="display: none; visibility: hidden">
        <div id="u56_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u57" class="text" style="visibility: visible;">
            <p><span>修改密码</span></p>
        </div>
    </div>

    <!-- Unnamed (热区) -->
    <div id="u58" class="ax_default" title="修改密码">
    </div>

    <!-- Unnamed (热区) -->
    <div id="u59" class="ax_default">
    </div>

    <!-- Unnamed (热区) -->
    <div id="u60" class="ax_default" title="充值">
    </div>

    <!-- 加入地网 (组合) -->
    <div id="u61" class="ax_default" data-label="加入地网"  style="">

        <!-- Unnamed (图片) -->
        <div id="u62" class="ax_default _图片">
            <img id="u62_img" class="img " src="<%=path%>/pc/images/homepage/u62.png"/>
            <!-- Unnamed () -->
            <div id="u63" class="text" style="display: none; visibility: hidden">
                <p><span></span></p>
            </div>
        </div>

        <!-- Unnamed (图片) -->
        <div id="u64" class="ax_default _图片 ax_default_hidden" style="display: none; visibility: hidden">
            <img id="u64_img" class="img " src="<%=path%>/pc/images/homepage/u64.png"/>
            <!-- Unnamed () -->
            <div id="u65" class="text" style="display: none; visibility: hidden">
                <p><span></span></p>
            </div>
        </div>

        <!-- Unnamed (矩形) -->
        <div id="u66" class="ax_default label">
            <div id="u66_div" class=""></div>
            <!-- Unnamed () -->
            <div id="u67" class="text" style="visibility: visible;">
                <p><span>加入地网</span></p>
            </div>
        </div>

        <!-- Unnamed (热区) -->
        <div id="u68" class="ax_default">
        </div>
    </div>

    <!-- Unnamed (矩形) -->
    <div id="u69" class="ax_default box_2">
        <div id="u69_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u70" class="text" style="display: none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <!-- Unnamed (图片) -->
    <div id="u71" class="ax_default _图片">
        <img id="u71_img" class="img " src="<%=path%>/pc/images/homepage/u71.png"/>
        <!-- Unnamed () -->
        <div id="u72" class="text" style="display: none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <!-- Unnamed (矩形) -->
    <div id="u73" class="ax_default box_2">
        <div id="u73_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u74" class="text" style="display: none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <!-- Unnamed (矩形) -->
    <div id="u75" class="ax_default box_2 ax_default_hidden" style="display: none; visibility: hidden">
        <div id="u75_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u76" class="text" style="display: none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <!-- Unnamed (矩形) -->
    <div id="u77" class="ax_default box_2 ax_default_hidden" style="display: none; visibility: hidden">
        <div id="u77_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u78" class="text" style="display: none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <!-- Unnamed (矩形) -->
    <div id="u79" class="ax_default box_2 ax_default_hidden" style="display: none; visibility: hidden">
        <div id="u79_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u80" class="text" style="display: none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <!-- Unnamed (图片) -->
    <div id="u81" class="ax_default _图片">
        <img id="u81_img" class="img " src="<%=path%>/pc/images/homepage/u81.png"/>
        <!-- Unnamed () -->
        <div id="u82" class="text" style="display: none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <!-- Unnamed (图片) -->
    <div id="u83" class="ax_default _图片 ax_default_hidden" style="display: none; visibility: hidden">
        <img id="u83_img" class="img " src="<%=path%>/pc/images/homepage/u83.png"/>
        <!-- Unnamed () -->
        <div id="u84" class="text" style="display: none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <!-- Unnamed (图片) -->
    <div id="u85" class="ax_default _图片 ax_default_hidden" style="display: none; visibility: hidden">
        <img id="u85_img" class="img " src="<%=path%>/pc/images/homepage/u83.png"/>
        <!-- Unnamed () -->
        <div id="u86" class="text" style="display: none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <!-- Unnamed (图片) -->
    <div id="u87" class="ax_default _图片">
        <img id="u87_img" class="img " src="<%=path%>/pc/images/homepage/u87.png"/>
        <!-- Unnamed () -->
        <div id="u88" class="text" style="display: none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <!-- Unnamed (图片) -->
    <div id="u89" class="ax_default _图片 ax_default_hidden" style="display: none; visibility: hidden">
        <img id="u89_img" class="img " src="<%=path%>/pc/images/homepage/u89.png"/>
        <!-- Unnamed () -->
        <div id="u90" class="text" style="display: none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <!-- Unnamed (图片) -->
    <div id="u91" class="ax_default _图片">
        <img id="u91_img" class="img " src="<%=path%>/pc/images/homepage/u89.png"/>
        <!-- Unnamed () -->
        <div id="u92" class="text" style="display: none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <!-- Unnamed (图片) -->
    <div id="u93" class="ax_default _图片">
        <img id="u93_img" class="img " src="<%=path%>/pc/images/homepage/u93.png"/>
        <!-- Unnamed () -->
        <div id="u94" class="text" style="display: none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <!-- Unnamed (图片) -->
    <div id="u95" class="ax_default _图片 ax_default_hidden" style="display: none; visibility: hidden">
        <img id="u95_img" class="img " src="<%=path%>/pc/images/homepage/u95.png"/>
        <!-- Unnamed () -->
        <div id="u96" class="text" style="display: none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <!-- Unnamed (图片) -->
    <div id="u97" class="ax_default _图片 ax_default_hidden" style="display: none; visibility: hidden">
        <img id="u97_img" class="img " src="<%=path%>/pc/images/homepage/u95.png"/>
        <!-- Unnamed () -->
        <div id="u98" class="text" style="display: none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <!-- Unnamed (图片) -->
    <div id="u99" class="ax_default _图片">
        <img id="u99_img" class="img " src="<%=path%>/pc/images/homepage/u99.png"/>
        <!-- Unnamed () -->
        <div id="u100" class="text" style="display: none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <!-- Unnamed (图片) -->
    <div id="u101" class="ax_default _图片 ax_default_hidden" style="display: none; visibility: hidden">
        <img id="u101_img" class="img " src="<%=path%>/pc/images/homepage/u101.png"/>
        <!-- Unnamed () -->
        <div id="u102" class="text" style="display: none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <!-- Unnamed (图片) -->
    <div id="u103" class="ax_default _图片 ax_default_hidden" style="display: none; visibility: hidden">
        <img id="u103_img" class="img " src="<%=path%>/pc/images/homepage/u101.png"/>
        <!-- Unnamed () -->
        <div id="u104" class="text" style="display: none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <!-- Unnamed (矩形) -->
    <div id="u105" class="ax_default label">
        <div id="u105_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u106" class="text" style="visibility: visible;">
            <p><span>全部客户</span></p>
        </div>
    </div>

    <!-- Unnamed (矩形) -->
    <div id="u107" class="ax_default label ax_default_hidden" style="display: none; visibility: hidden">
        <div id="u107_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u108" class="text" style="visibility: visible;">
            <p><span>全部客户</span></p>
        </div>
    </div>

    <!-- Unnamed (矩形) -->
    <div id="u109" class="ax_default label">
        <div id="u109_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u110" class="text" style="visibility: visible;">
            <p><span>全部客户</span></p>
        </div>
    </div>

    <!-- Unnamed (矩形) -->
    <div id="u111" class="ax_default label" style="">
        <div id="u111_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u112" class="text" style="visibility: visible;">
            <p><span>特别关注</span></p>
        </div>
    </div>

    <!-- Unnamed (矩形) -->
    <div id="u113" class="ax_default label ax_default_hidden" style="display: none; visibility: hidden">
        <div id="u113_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u114" class="text" style="visibility: visible;">
            <p><span>特别关注</span></p>
        </div>
    </div>

    <!-- Unnamed (矩形) -->
    <div id="u115" class="ax_default label ax_default_hidden" style="display: none; visibility: hidden">
        <div id="u115_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u116" class="text" style="visibility: visible;">
            <p><span>特别关注</span></p>
        </div>
    </div>

    <!-- Unnamed (矩形) -->
    <div id="u117" class="ax_default label">
        <div id="u117_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u118" class="text" style="visibility: visible;">
            <p><span>已合作客户</span></p>
        </div>
    </div>

    <!-- Unnamed (矩形) -->
    <div id="u119" class="ax_default label ax_default_hidden" style="display: none; visibility: hidden">
        <div id="u119_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u120" class="text" style="visibility: visible;">
            <p><span>已合作客户</span></p>
        </div>
    </div>

    <!-- Unnamed (矩形) -->
    <div id="u121" class="ax_default label ax_default_hidden" style="display: none; visibility: hidden">
        <div id="u121_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u122" class="text" style="visibility: visible;">
            <p><span>已合作客户</span></p>
        </div>
    </div>

    <!-- Unnamed (矩形) -->
    <div id="u123" class="ax_default label">
        <div id="u123_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u124" class="text" style="visibility: visible;">
            <p><span>未合作客户</span></p>
        </div>
    </div>

    <!-- Unnamed (矩形) -->
    <div id="u125" class="ax_default label ax_default_hidden" style="display: none; visibility: hidden">
        <div id="u125_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u126" class="text" style="visibility: visible;">
            <p><span>未合作客户</span></p>
        </div>
    </div>

    <!-- Unnamed (矩形) -->
    <div id="u127" class="ax_default label ax_default_hidden" style="display: none; visibility: hidden">
        <div id="u127_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u128" class="text" style="visibility: visible;">
            <p><span>未合作客户</span></p>
        </div>
    </div>

    <!-- Unnamed (热区) -->
    <div id="u129" class="ax_default" onclick="qbkh();">
    </div>

    <!-- Unnamed (热区) -->
    <div id="u130" class="ax_default" onclick="tbgz();">
    </div>

    <!-- Unnamed (热区) -->
    <div id="u131" class="ax_default" onclick="yhzkh();">
    </div>

    <!-- Unnamed (热区) -->
    <div id="u132" class="ax_default" onclick="whzkh();">
    </div>

    <!-- Unnamed (矩形) -->
    <div id="u133" class="ax_default label">
        <div id="u133_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u134" class="text" style="visibility: visible;">
            <p><span>【添加客户】后点击客户卡片即查看查询结果。</span></p>
        </div>
    </div>

    <!-- Unnamed (图片) -->
    <div id="u135" class="ax_default _图片">
        <img id="u135_img" class="img " src="<%=path%>/pc/images/homepage/u135.jpg"/>
        <!-- Unnamed () -->
        <div id="u136" class="text" style="display: none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <!-- Unnamed (图片) -->
    <div id="u137" class="ax_default _图片">
        <img id="u137_img" class="img " src="<%=path%>/pc/images/homepage/u137.png"/>
        <!-- Unnamed () -->
        <div id="u138" class="text" style="display: none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <!-- Unnamed (矩形) -->
    <div id="u139" class="ax_default box_1 ax_default_hidden" style="display: none; visibility: hidden">
        <div id="u139_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u140" class="text" style="display: none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>


    <!-- Unnamed (矩形) -->
    <div id="u319" class="ax_default _三级标题">
        <div id="u319_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u320" class="text" style="visibility: visible;">
            <p><span>扫一扫，领</span></p>
        </div>
    </div>

    <!-- Unnamed (矩形) -->
    <div id="u321" class="ax_default _二级标题">
        <div id="u321_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u322" class="text" style="visibility: visible;">
            <p><span>地网公告</span></p>
        </div>
    </div>
</div>
<input type="hidden" name="allpage" id="allpage" value="" style="position: absolute;top: 10px;">
<input type="hidden" name="currentpage" id="currentpage" value="1"  style="position: absolute;top: 30px;">
<input type="hidden" name="type" id="type" value=""  style="position: absolute;top: 30px;">
</body>
<script src="https://cdn.bootcss.com/vue/2.2.2/vue.min.js"></script>
<script src="<%=path%>/static/js/plugins/layer/layer.js"></script>
<script>
    var phone = "${userPCVo.phone}";
    if(!phone){
        location.href = "/pcUser/exit";
    }
    var mphone = phone.substr(0, 3) + '****' + phone.substr(7);
    $('#phone').html(mphone);
    $(".cardlist").mouseleave(function () {
        $(".card").removeClass("active_card");
    });
    function info(val) {
        location.href="/pc/detailpage/"+val;
    }
    function gou(val) {
        var imgs = $("#"+val).attr("src");
        var isactive ='';
        if(imgs.lastIndexOf("active")>-1){
            $("#"+val).attr("src","<%=path%>/static/img/border.png");
            isactive = 1;
        }else{
            $("#"+val).attr("src","<%=path%>/static/img/gou_active.png");
            isactive = 0;
        }
        val = val.substring(4,val.length);
        $.post(
            "/keHu/updateStatus",
            {
                "id":val,
                "status":1,
                "isactive":isactive
            },function (msg) {
                if(msg.result=="success"){
                    alert("操作成功");
                }else{
                    alert("操作失败");
                }

            },"json"
        );
    }
    function tbgz() {
        $("#type").val(0);
        $("#currentpage").val(1);
        refush($("#currentpage").val(),0);
    }
    function yhzkh() {
        $("#type").val(1);
        $("#currentpage").val(1);
        refush($("#currentpage").val(),1);
    }
    function whzkh() {
        $("#type").val(2);
        $("#currentpage").val(1);
        refush($("#currentpage").val(),2);
    }
    function qbkh() {
        $("#type").val(-1);
        $("#currentpage").val(1);
        refush($("#currentpage").val(),-1);
    }
    function xin(val) {
        var imgs = $("#"+val).attr("src");
        var isactive ='';
        if(imgs.lastIndexOf("active")>-1){
            $("#"+val).attr("src","<%=path%>/static/img/xin.png");
            isactive = 1;
        }else{
            $("#"+val).attr("src","<%=path%>/static/img/xin_active.png");
            isactive = 0;
        }
        val = val.substring(4,val.length);
        $.post(
            "/keHu/updateStatus",
            {
                "id":val,
                "status":0,
                "isactive":isactive
            },function (msg) {
                if(msg.result=="success"){
                }else{
                }
            },"json"
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
            cur: function(oldValue , newValue){
                console.log(arguments);
            }
        },
        methods: {
            btnClick: function(data){//页码点击事件
                if(data != this.cur){
                    var type = $("#type").val();
                    if(!type){
                        type = null;
                    }
                    this.cur = data;
                    refush(this.cur,type);
                }
            },
            pageClick: function(){
                var type = $("#type").val();
                if(!type){
                    type = null;
                }
                refush(this.cur,type);
            }
        },
        computed: {
            indexs: function(){
                var left = 1;
                var right = this.all;
                var ar = [];
                if(this.all>= 5){
                    if(this.cur > 3 && this.cur < this.all-2){
                        left = this.cur - 2
                        right = this.cur + 2
                    }else{
                        if(this.cur<=3){
                            left = 1
                            right = 5
                        }else{
                            right = this.all
                            left = this.all -4
                        }
                    }
                }
                while (left <= right){
                    ar.push(left)
                    left ++
                }
                return ar
            }
        }
    })
    refush($("#currentpage").val(),-1);
    function refush(val,type) {
        $.post(
            "/keHu/keHuList",
            {
                "pageSize":9,
                "pageIndex":val,
                "kehuType":type
            },function (msg) {
                vm.$data.sites = msg.rows;
                pageBar.$data.all = Math.ceil(parseFloat(parseInt(msg.total)/9));
            },"json"
        );
    }
    $("#addkehu").click(function () {
        var kehuPhone = $("#call_num").val();
        var kehuIdcard = $("#idcard").val();
        var kehuName = $("#name").val();
        if(!kehuName){
            layer.alert("姓名、身份证号或手机号输入有误");
            return;
        }
        if(!kehuIdcard){
            layer.alert("姓名、身份证号或手机号输入有误");
            return;
        }
        if(!kehuPhone){
            layer.alert("姓名、身份证号或手机号输入有误");
            return;
        }
        var index = layer.load(1, {
            shade: [0.1,'#fff'] //0.1透明度的白色背景
        });
        $.post(
            "/keHu/keHuAddSave",
            {
                "kehuPhone":kehuPhone,
                "kehuIdcard":kehuIdcard,
                "kehuName":kehuName
            },function (msg) {
                layer.close(index);
                refush(1,$("#type").val());
                if(msg.result=="success"){
                    layer.alert("添加成功");
                }else{
                    layer.alert("添加失败");
                }
            },"json"

        );
    });
    Vue.filter('time',
        function(value) {
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
