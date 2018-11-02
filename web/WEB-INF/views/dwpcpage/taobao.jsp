<%--
  Created by IntelliJ IDEA.
  User: 17702
  Date: 2018/7/8
  Time: 11:15
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
    <title>淘宝/京东/支付宝</title>
    <link rel="stylesheet" type="text/css" href="<%=path%>/static/dwpcpage/css/tb.css" />
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
        a:hover{
            cursor: pointer;
        }
        td{
            border: 1px solid #ddd;
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
<div class="mengban" style="">
    <div style="width: 1200px;height: 100px;text-align: center;margin: auto;border-bottom: solid 2px #BBBBBB">
        <a href="/startDianShang/info/${dianShangVo.kehuId}/taobao/${dianShangVo.time}">
            <div style="width: 100px;height: 100px;float: left;border-bottom: solid 4px #0583CE">
                <img src="<%=path%>/static/dwpcpage/images/tb.png" style="width: 70px;padding-top: 30px" alt="">
            </div>
        </a>
        <a href="/startDianShang/info/${dianShangVo.kehuId}/jd/${dianShangVo.time}">
            <div style="width: 100px;height: 100px;float: left;">
                <img src="<%=path%>/static/dwpcpage/images/jd.png" style="width: 70px;padding-top: 30px" alt="">
            </div>
        </a>
        <%--<a href="/startDianShang/info/${dianShangVo.kehuId}/zfb">--%>
        <a>
            <div style="width: 100px;height: 100px;float: left;">
                <img src="<%=path%>/static/dwpcpage/images/zfb.png" style="width: 70px;padding-top: 26px" alt="">
            </div>
        </a>
        <div style="float: right;padding-top: 75px;color: #000000;font-size: 14px;height: 30px">
            更新时间：<span id="update_time">2018年06月25日22:14</span>
        </div>
    </div>
    <div id="is_show">
        <div style="width: 1200px;height: 40px;text-align: center;margin: auto;margin-top: 15px;">
            <div style="float: left;font-size: 18px;color: #000;line-height: 40px">
                【地网数据】淘宝报告
            </div>
            <div style="width: 100px;float: right;">
                <button style="background-color: #0583CE;color: #fff;border: none;width: 80px;height: 30px;line-height: 30px;border-radius: 4px;">下载</button>
            </div>
        </div>
        <div style="width: 1200px;height:400px;text-align: center;margin: auto;margin-top: 15px;">
            <div id="container" style="height: 100%;width: 100%"></div>
        </div>
        <div style="width: 1200px;height:auto;text-align: center;margin: auto;margin-top: 15px;">
            <table width="100%" border="1" class="table2" id="address">
                <tr style="height: 40px;background-color: #0583CE;color: #fff">
                    <td>类型</td>
                    <td>使用频率</td>
                    <td>使用月份</td>
                    <td>累计金额</td>
                    <td>订单数</td>
                    <td>月平均消费</td>
                    <td>平均每笔消费</td>
                </tr>
                <tr style="height: 40px" v-for="site in sites" v-if="site.id==1">
                    <td>全部电商使用统计（一年内）</td>
                    <td>
                        <span v-if="site.months>=6 && site.cnt>=12">经常使用</span>
                        <span v-else>偶尔使用</span>
                    </td>
                    <td>
                        <span v-if="site.months>=6 && site.cnt>=12">使用月份大于6并且订单数大于12</span>
                        <span v-else>使用月份小于6并且订单数小于12</span>
                    </td>
                    <td>￥{{site.price}}</td>
                    <td>{{site.cnt}}</td>
                    <td>￥{{site.price/12 | formatMoney }}</td>
                    <td>￥{{site.price/site.cnt | formatMoney }}</td>
                </tr>
                <tr style="height: 40px" v-for="site in sites" v-if="site.id==null">
                    <td>查询人【{{site.receiverName}}】电商收货使用统计（一年内）</td>
                    <td>
                        <span v-if="site.months>=6 && site.cnt>=12">经常使用</span>
                        <span v-else>偶尔使用</span>
                    </td>
                    <td>
                        <span v-if="site.months>=6 && site.cnt>=12">使用月份大于6并且订单数大于12</span>
                        <span v-else>使用月份小于6并且订单数小于12</span>
                    </td>
                    <td>￥{{site.price}}</td>
                    <td>{{site.cnt}}</td>
                    <td>￥{{site.price/12 | formatMoney }}</td>
                    <td>￥{{site.price/site.cnt | formatMoney }}</td>
                </tr>
            </table>
        </div>
        <div style="width: 1200px;height:auto;text-align: center;margin: auto;margin-top: 15px;">
            <table width="100%" border="1" class="table2" id="baseInfo">
                <tr style="height: 40px;background-color: #0583CE;color: #fff">
                    <td colspan="4">淘宝基本信息</td>
                </tr>
                <tr style="height: 40px">
                    <td>真实姓名</td>
                    <td>{{realName}}</td>
                    <td>身份证</td>
                    <td>{{cardCode}}</td>
                </tr>
                <tr style="height: 40px">
                    <td>手机号</td>
                    <td>{{cellPhone}}</td>
                    <td>会员等级</td>
                    <td>{{level}}</td>
                </tr>
                <tr style="height: 40px">
                    <td>用户名</td>
                    <td>{{nickName}}</td>
                    <td>淘气值</td>
                    <td>{{score}}</td>
                </tr>
                <tr style="height: 40px">
                    <td>安全等级</td>
                    <td>{{securityLevel}}</td>
                    <td>实名认证</td>
                    <td>
                        <span v-if="validateRealName=true">已实名认证</span>
                        <span v-else>未实名认证</span>
                    </td>
                </tr>
                <tr style="height: 40px">
                    <td>报告获取时间</td>
                    <td>2018-06-06</td>
                    <td></td>
                    <td></td>
                </tr>
            </table>
        </div>
        <div style="width: 1200px;height:auto;text-align: center;margin: auto;margin-top: 15px;">
            <table width="100%" border="1" class="table2" id="address_list">
                <tr style="height: 40px;background-color: #0583CE;color: #fff">
                    <td colspan="9">收货地址综合分析</td>
                </tr>
                <tr style="height: 40px">
                    <td>#</td>
                    <td>地址相似地址合并数量</td>
                    <td>一年内使用评率</td>
                    <td>姓名</td>
                    <td>电话</td>
                    <td>消费次数</td>
                    <td>消费金额</td>
                    <td>最早下单</td>
                    <td>最近下单</td>
                </tr>
                <tr style="height: 40px" v-for="(site,index) in sites">
                    <td>{{index+1}}</td>
                    <td>{{site.receiverAddr}}</td>
                    <td>
                        <span v-if="site.months>=6 && site.cnt>=12">经常使用</span>
                        <span v-else>偶尔使用</span>
                    </td>
                    <td>{{site.receiverName}}</td>
                    <td>{{site.receiverPhone}}</td>
                    <td>{{site.cnt}}</td>
                    <td>￥{{site.price}}</td>
                    <td>{{site.min | time}}</td>
                    <td>{{site.max | time}}</td>
                </tr>
            </table>
        </div>
        <div style="width: 1200px;height:auto;text-align: center;margin: auto;margin-top: 15px;">
            <table width="100%" border="1" class="table2" id="time_list">
                <tr style="height: 40px;background-color: #0583CE;color: #fff">
                    <td colspan="15">收货地址统计（月消费次数和金额）</td>
                </tr>
                <tr style="height: 40px">
                    <td>#</td>
                    <td>地址日期</td>
                    <td id="time0">2018-03</td>
                    <td id="time1">2018-03</td>
                    <td id="time2">2018-03</td>
                    <td id="time3">2018-03</td>
                    <td id="time4">2018-03</td>
                    <td id="time5">2018-03</td>
                    <td id="time6">2018-03</td>
                    <td id="time7">2018-03</td>
                    <td id="time8">2018-03</td>
                    <td id="time9">2018-03</td>
                    <td id="time10">2018-03</td>
                    <td id="time11">2018-03</td>
                </tr>
                <tr style="height: 40px" v-for="(site,index) in sites">
                    <td>{{index+1}}</td>
                    <td>{{site.address}}</td>
                    <td>
                        <div v-if="site.oneMoney==0.00 && site.oneMonth==0">
                            -
                        </div>
                        <div v-else>
                            <div>({{site.oneMonth}}次)</div>
                            <div>￥{{site.oneMoney}}</div>
                        </div>
                    </td>
                    <td>
                        <div v-if="site.twoMoney==0.00 && site.twoMonth==0">
                            -
                        </div>
                        <div v-else>
                            <div>({{site.twoMonth}}次)</div>
                            <div>￥{{site.twoMoney}}</div>
                        </div>
                    </td>
                    <td>
                        <div v-if="site.threeMoney==0.00 && site.threeMonth==0">
                            -
                        </div>
                        <div v-else>
                            <div>({{site.threeMonth}}次)</div>
                            <div>￥{{site.threeMoney}}</div>
                        </div>
                    </td>
                    <td>
                        <div v-if="site.fourMoney==0.00 && site.fourMonth==0">
                            -
                        </div>
                        <div v-else>
                            <div>({{site.fourMonth}}次)</div>
                            <div>￥{{site.fourMoney}}</div>
                        </div>
                    </td>
                    <td>
                        <div v-if="site.fiveMoney==0.00 && site.fiveMonth==0">
                            -
                        </div>
                        <div v-else>
                            <div>({{site.fiveMonth}}次)</div>
                            <div>￥{{site.fiveMoney}}</div>
                        </div>
                    </td>
                    <td>
                        <div v-if="site.sixMoney==0.00 && site.sixMonth==0">
                            -
                        </div>
                        <div v-else>
                            <div>({{site.sixMonth}}次)</div>
                            <div>￥{{site.sixMoney}}</div>
                        </div>
                    </td>
                    <td>
                        <div v-if="site.sixMoney==0.00 && site.sixMonth==0">
                            -
                        </div>
                        <div v-else>
                            <div>({{site.sixMonth}}次)</div>
                            <div>￥{{site.sixMoney}}</div>
                        </div>
                    </td>
                    <td>
                        <div v-if="site.sevenMoney==0.00 && site.sevenMonth==0">
                            -
                        </div>
                        <div v-else>
                            <div>({{site.sevenMonth}}次)</div>
                            <div>￥{{site.sevenMoney}}</div>
                        </div>
                    </td>
                    <td>
                        <div v-if="site.nineMoney==0.00 && site.nineMonth==0">
                            -
                        </div>
                        <div v-else>
                            <div>({{site.nineMonth}}次)</div>
                            <div>￥{{site.nineMoney}}</div>
                        </div>
                    </td>
                    <td>
                        <div v-if="site.tenMoney==0.00 && site.tenMonth==0">
                            -
                        </div>
                        <div v-else>
                            <div>({{site.tenMonth}}次)</div>
                            <div>￥{{site.tenMoney}}</div>
                        </div>
                    </td>
                    <td>
                        <div v-if="site.elevenMoney==0.00 && site.elevenMonth==0">
                            -
                        </div>
                        <div v-else>
                            <div>({{site.elevenMonth}}次)</div>
                            <div>￥{{site.elevenMoney}}</div>
                        </div>
                    </td>
                    <td>
                        <div v-if="site.twelveMoney==0.00 && site.twelveMonth==0">
                            -
                        </div>
                        <div v-else>
                            <div>({{site.twelveMonth}}次)</div>
                            <div>￥{{site.twelveMoney}}</div>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
        <div style="width: 1200px;height:auto;text-align: center;margin: auto;margin-top: 15px;">
            <table width="100%" border="1" class="table2" id="order_list">
                <tr style="height: 40px;background-color: #0583CE;color: #fff">
                    <td colspan="15">电单详情</td>
                </tr>
                <tr style="height: 40px">
                    <td>时间</td>
                    <td>商品</td>
                    <td>价格</td>
                    <td>购买数量</td>
                    <td>店铺名称</td>
                    <td>收货地址</td>
                    <td>收货人</td>
                    <td>收货手机号</td>
                    <td>交易状态</td>
                </tr>
                <tr style="height: 40px" v-for="site in sites">
                    <td>{{site.transTime}}</td>
                    <td style="width: 200px">{{site.items[0].productName}}</td>
                    <td>￥{{site.totalPrice}}</td>
                    <td>{{site.items[0].productCnt}}</td>
                    <td>{{site.shopInfo.nick}}</td>
                    <td style="width: 200px">
                        <span v-if="site.receiverAddr==null">-</span>
                        <span v-else>{{site.receiverAddr}}</span>
                    </td>
                    <td>{{site.receiverName}}</td>
                    <td>{{site.receiverCellPhone}}</td>
                    <td>{{site.statusText}}</td>
                </tr>

            </table>
        </div>
        <div style="height: 40px;width: 100%">
    </div>
    <div id="is_hidden" style="text-align: center;font-size: 14px;">
        扫码方未认证
    </div>
</div>
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
<input type="hidden" id="khid" value="${kehuVo.id}">
</body>
<script src="<%=path%>/static/dwpcpage/js/jquery.min.js"></script>
<script src="<%=path%>/static/dwpcpage/js/fuzhai_01.js"></script>
<script src="<%=path%>/static/dwpcpage/js/bootstrap.min.js"></script>
<script src="<%=path%>/static/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
<script src="<%=path%>/static/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
<script src="<%=path%>/static/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="<%=path%>/static/js/plugins/layer/layer.js"></script>
<script src="<%=path%>/static/js/vue.min.js"></script>
<script src="https://img.hcharts.cn/highcharts/highcharts.js"></script>
<script src="https://img.hcharts.cn/highcharts/modules/exporting.js"></script>
<script src="https://img.hcharts.cn/highcharts/modules/oldie.js"></script>
<script src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>
<script>
    var phone = "${userPCVo.phone}";
    if (!phone) {
        location.href = "/pcUser/exit";
    }
    var mphone = phone.substr(0, 3) + '****' + phone.substr(7);
    $('.phone_val').html(mphone);
    refushInfo();
    function refushInfo() {
        $.post(
            "/pcUser/refushInfo",
            function (data) {
                $("#kehujifen").html(data.jifen);
            },"json"
        );
    }
    function formartTime1(value) {
        alert(value);
        var date = new Date(parseInt(value));
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        var d = date.getDate();
        var h = date.getHours();
        var mi = date.getMinutes();
        var ss = date.getSeconds();
        return y + '年' + (parseInt(m)<10?"0"+m:m) + '月' + (parseInt(d)<10?"0"+d:d) + '日 '+(parseInt(h)<10?"0"+h:h) + ':'+(parseInt(mi)<10?"0"+mi:mi)+ ':'+(parseInt(ss)<10?"0"+ss:ss) ;
    }
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
    Vue.filter('formatMoney',
        function(value) {
            return value.toFixed(2);
        }
    );
</script>
<script>
    $("#update_time").html(formartTime1("${dianShangVo.times}"));
    console.log('${dianShangVo.times}');
    var datainfo = ${dianShangVo.tbData};
    if(datainfo){
        $("#is_hidden").hide();
        $("#is_show").show();
        //地址统计
        new Vue({
            el: '#order_list',
            data: {
                sites: datainfo.orders
            }
        })
        //基本信息统计
        new Vue({
            el: '#baseInfo',
            data: datainfo.basicInfo
        })
        var index = layer.load(1, {
            shade: [0.1,'#fff'] //0.1透明度的白色背景
        });
        $("#address").hide();
        $("#address_list").hide();
        $("#time_list").hide();
        $.post(
            "/dianShang/getTaoBaoInfo",
            {
                id:"${dianShangVo.id}"
            },function (data) {
                layer.close(index);
                $("#address").show();
                $("#address_list").show();
                $("#time_list").show();
                //绚烂图表
                view_taobao(data.monthVos);
                timeList(data.monthAndMoneyVos);
                addressList(data.taoBaoItemVos);
                address(data.taoBaoItemVoList);
            },"json"
        );
    }else{
        $("#is_hidden").show();
        $("#is_show").hide();
    }
    //没个地址一年统计
    function getTime() {
        var date = null;
        var dates = new Array();
        for(var i=0;i<12;i++){
            date = new Date();
            dates.push(formartTime(date.setMonth(date.getMonth()-i)));
            date = new Date();
            $("#time"+i).html(formartTime(date.setMonth(date.getMonth()-i)));
        }
        return dates;
    }
    function address(val) {
        new Vue({
            el: '#address',
            data: {
                sites: val
            }
        })
    }
    function timeList(val) {
        new Vue({
            el: '#time_list',
            data: {
                sites: val
            }
        })
    }
    function addressList(val) {
        new Vue({
            el: '#address_list',
            data: {
                sites: val
            }
        })
    }
    function view_taobao(val) {
        var chart = Highcharts.chart('container', {
            chart: {
                type: 'line'
            },
            title: {
                text: '年消费统计'
            },
            subtitle: {
                text: '数据来源: 192.168.3.18:8080'
            },
            xAxis: {
                categories: getTime()
            },
            yAxis: {
                title: {
                    text: '金额 (元)'
                }
            },
            plotOptions: {
                line: {
                    dataLabels: {
                        // 开启数据标签
                        enabled: true
                    },
                    // 关闭鼠标跟踪，对应的提示框、点击事件会失效
                    enableMouseTracking: false
                }
            },
            series: [{
                name: '年消费',
                data: [parseInt(val[0].oneMoney),parseInt(val[0].twoMoney),parseInt(val[0].threeMoney),parseInt(val[0].fourMoney),parseInt(val[0].fiveMoney),parseInt(val[0].sixMoney),parseInt(val[0].sevenMoney),parseInt(val[0].eightMoney),parseInt(val[0].nineMoney),parseInt(val[0].tenMoney),parseInt(val[0].elevenMoney),parseInt(val[0].twelveMoney)]
            }]
            ,
            plotOptions: {
                series: {
                    cursor: 'pointer',
                    point: {
                        events: {
                            // 数据点点击事件
                            // 其中 e 变量为事件对象，this 为当前数据点对象
                            click: function (e) {
                                $('.message').html( Highcharts.dateFormat('%Y-%m-%d', this.x) + ':<br/>  访问量：' +this.y );
                            }
                        }
                    },
                    marker: {
                        lineWidth: 1
                    }
                }
            }
        });
    }
</script>

</html>
