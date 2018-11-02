<%--
  Created by IntelliJ IDEA.
  User: 17702
  Date: 2018/7/8
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <title>负债查询-普通查询</title>
    <link rel="stylesheet" type="text/css" href="<%=path%>/static/dwpcpage/css/fuzhai_01.css" />
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
        .data_info_{
            text-align: center;
        }
        .btn-primary {
            color: #fff;
            background-color: #1493f0;
        }
        a:hover{
            cursor: pointer;
        }
    </style>
</head>
<body style="background-color: #e4e4e4">
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
</div><!--主体部分-->
<div class="mengban"></div>
<div class="main" style="background-color: #e4e4e4">
    <div class="main_toptop"></div>
    <div class="main_navnav"></div>
    <div class="main_samll">
        <div class="main_top">
            <div class="mt_left">
                <div class="mtl_left">
                    <img src="${kehuVo.kehuHeadImg}" alt="" />
                </div>
                <div class="mtl_right">
                    <div class="ltr_top">
                        <span style="width: 100px;font-size: 24px;" id="kehuname">${kehuVo.kehuName}</span>
                        <span class="span2"><img src="<%=path%>/static/dwpcpage/images/boy.png" alt="" /></span>
                        <span class="span3">${kehuVo.kehuAge}岁</span>
                    </div>
                    <div class="ltr_center">
                        <ul  >
                            <li class="li2">籍贯：<span id="address">${kehuVo.kehuAddress}</span></li>
                            <li class="li2">手机：<span id="phone">${kehuVo.kehuPhone}</span><a style="color: #225599;margin-left: 5px" data-toggle="modal" data-target="#update_phone">更换手机号</a></li>
                            <li class="li2">微信：<span id="wx">未知</span></li>
                            <li class="li2">上次查询时间：<span id="uptime">${kehuVo.upTime}</span></li>
                            <li class="li2">身份证：<span id="kehucard">${kehuVo.kehuIdcard}</span></li>
                            <li class="li2 li3"><a style="color: #0583CE"><i class="ltlt_i iii" id="hz"></i><i class="ii">已合作客户</i></a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="mt_right" >
                <ul class="mtr_ul" id="remarkList" style="visibility: hidden">
                    <li v-for="site in sites">
                        <span>{{site.content}}</span>
                        <span style="float: right;margin-right: 5px;color: #afa1a1">{{site.createTime | time}}记</span>
                    </li>
                </ul>
                <div class="bz" style="border-top: 1px solid #D4D4D4;"><i><img src="<%=path%>/static/dwpcpage/images/w.png" alt="" /></i><input type="text" maxlength="30" minlength="1" id="remark"  placeholder="添加备注，如家庭、金额、公司" /><button type="button" id="add_remark" style="background-color: #1493f0;border: none;border-radius: 4px;color: #fff;width: 44px;height: 24px;line-height: 24px">提交</button></div>
            </div>
            <div style="clear: both;"></div>
        </div>
        <div class="main_nav">
            <div class="mn_ul">
                <li class="demo4"><a href="/pcUser/info/${kehuVo.id}">负债查询</a></li>
                <li><a href="/pcUser/phone/${kehuVo.id}">手机通讯</a></li>
                <li>
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
                    <li style="color: #0583CE;background: #fff;;text-align:center" >普通负债查询<span id="ptcx" style="width: 40px;height: 30px;padding: 9px 16px;background: #0583CE;color: #fff;margin-right: -105px;margin-left:40px;border-radius: 4px;">更新</span></li>
                    <li style="margin-left: 0.5%;">高级负债查询</li>
                    <li style="margin-left: 0.5%;">网贷、小贷、分期、多手机号<span id="fxbt" style="width: 40px;height: 30px;padding: 9px 16px;background: #0583CE;color: #fff;margin-right: 0px;margin-left:20px;border-radius: 4px;display: none">更新</span></li>
                </ul>
                <div class="tab_cont">
                    <div class="tab_box" style="background: #e4e4e4;">
                        <div class="tab_small_box">
                            <div class="tab_sbox">
                               <div style="width: 100%;height: 80px;background-color: #f5f5f5">
                                   <div class="ts_top" style="width: 100%;padding: 15px">
                                       <div class="ts_top_img"><img src="<%=path%>/static/dwpcpage/images/wuyoulogo.jpg" alt="" /></div>
                                       <div class="ts_top_right">
                                           <p class="p1"><span class="span1">无忧交易宝</span><span class="span2">${kehuVo.kehuName}</span></p>
                                           <p class="p2">更新时间：2018年6月23日14:36</p>
                                       </div>
                                   </div>
                               </div>
                                <div class="ts_bottom">
                                    <ul id="wyjyb" style="display: none;">
                                        <li v-for="wyjybss in wyjybs">
                                            <div class="tsb_title" style="color: red">合同一</div>
                                            <ul class="tsb_ul">
                                                <li>出借人：<span>{{wyjybss.buyer}}</span></li>
                                                <li>金额：<span>{{wyjybss.sellAmount}}</span></li>
                                                <li>借款日期：<span>{{wyjybss.sellTimeStr}}</span></li>
                                                <li>还款日期：<span>{{wyjybss.rentExpireDate}}</span></li>
                                            </ul>
                                        </li>

                                    </ul>
                                    <div style="text-align: center;color: #777;margin-top: 40px" id="wyjyb_no_data">请手动更新</div>
                                    <div style="text-align: center;color: red;margin-top: 40px;display: none" id="wyjyb_no_data_">平台无数据</div>
                                </div>
                            </div>
                            <div class="tab_sbox" style="margin-left: 7px;">
                                <div style="width: 100%;height: 80px;background-color: #f5f5f5">
                                    <div class="ts_top">
                                        <div class="ts_top_img"><img src="<%=path%>/static/dwpcpage/images/suxin.jpg" alt="" /></div>
                                        <div class="ts_top_right">
                                            <p class="p1"><span class="span1">速信云/速信记账服务</span><span class="span2">${kehuVo.kehuName}</span></p>
                                            <p class="p2">更新时间：2018年6月23日14:36</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="ts_bottom">
                                    <ul id="sxy" style="display: none;" >
                                        <li v-for="sxyss in sxys">
                                            <div class="tsb_title">合同一</div>
                                            <ul class="tsb_ul">
                                                <li>借款人：<span>{{sxyss.jkname}}</span></li>
                                                <li>出借人：<span>{{sxyss.cjname}}</span></li>
                                                <li>金额：<span>{{sxyss.money}}</span></li>
                                                <li>借款日期：<span>{{sxyss.start}}</span></li>
                                                <li>还款日期：<span>{{sxyss.end}}</span></li>
                                                <li>合同状态：<span>{{sxyss.status}}</span></li>
                                            </ul>
                                        </li>
                                    </ul>
                                    <div style="text-align: center;color: #777;margin-top: 40px" id="sxy_no_data">请手动更新</div>
                                    <div style="text-align: center;color: red;margin-top: 40px;display: none" id="sxy_no_data_">平台无数据</div>
                                </div>
                            </div>
                            <div class="tab_sbox" style="margin-left: 7px;">
                                <div style="width: 100%;height: 80px;background-color: #f5f5f5">
                                    <div class="ts_top">
                                        <div class="ts_top_img"><img src="<%=path%>/static/dwpcpage/images/zhongcheng.jpg" alt="" /></div>
                                        <div class="ts_top_right">
                                            <p class="p1"><span class="span1">中诚介</span><span class="span2">${kehuVo.kehuName}</span></p>
                                            <p class="p2">更新时间：2018年6月23日14:36</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="ts_bottom">
                                    <ul id="zcj" style="display: none">
                                        <li v-for="zcjss in zcjs">
                                            <div class="tsb_title">合同一</div>
                                            <ul class="tsb_ul">
                                                <li>借款人和出借人：<span>{{zcjss.member.realName}}、{{zcjss.memberRelation.realName}}</span></li>
                                                <li>借款日期：<span>{{zcjss.loanTime}}</span></li>
                                                <li>金额：<span>{{zcjss.loanTotal}}</span></li>
                                                <li>还款日期：<span>{{zcjss.expireTime}}</span></li>
                                                <li>合同状态：<span>{{zcjss.loanStatuItem.text}}</span></li>
                                            </ul>
                                        </li>
                                    </ul>
                                    <div style="text-align: center;color: #777;margin-top: 40px" id="zcj_no_data">请手动更新</div>
                                    <div style="text-align: center;color: red;margin-top: 40px;display: none" id="zcj_no_data_">平台无数据</div>
                                </div>
                            </div>
                            <div class="tab_sbox" style="margin-left: 7px;">
                                <div style="width: 100%;height: 80px;background-color: #f5f5f5">
                                    <div class="ts_top">
                                        <div class="ts_top_img"><img src="<%=path%>/static/dwpcpage/images/xinyu.jpg" alt="" /></div>
                                        <div class="ts_top_right">
                                            <p class="p1"><span class="span1">信誉搜</span><span class="span2">${kehuVo.kehuName}</span></p>
                                            <p class="p2">更新时间：2018年6月23日14:36</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="ts_bottom">
                                    <div class="ts_spec">
                                        <span class="p4" id="xys_data" style="display: none"></span>
                                        <span class="p4" id="xys_no_data" style="text-align: center;color: #777;margin-top: 20px;">请手动更新</span>
                                        <div style="text-align: center;color: red;margin-top: 20px;display: none" id="xys_no_data_">平台无数据</div>
                                    </div>
                                </div>
                            </div>
                            <div class="tab_sbox">
                                <div style="width: 100%;height: 80px;background-color: #f5f5f5">
                                    <div class="ts_top">
                                        <div class="ts_top_img"><img src="<%=path%>/static/dwpcpage/images/pingzheng.jpg" alt="" /></div>
                                        <div class="ts_top_right">
                                            <p class="p1"><span class="span1">凭证云</span><span class="span2">${kehuVo.kehuName}</span></p>
                                            <p class="p2">更新时间：2018年6月23日14:36</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="ts_bottom">
                                    <div class="ts_spec1" style="height: 300px">
                                        <div class="p4" id="pzy"  style="text-align: center;color: #777;height: 300px;overflow-x: hidden;overflow-y: hidden;display: none">
                                            <iframe src="" id="pzy_html"  frameborder="no" border="0" marginwidth="0" marginheight="0"  allowtransparency="yes" frameborder="0" style="height: 300px"></iframe>
                                        </div>
                                        <div class="p4" id="pzy_no_data" style="text-align: center;color: #777;display: block;margin-top: 35px">请手动更新</div>
                                        <div style="text-align: center;color: red;margin-top: 40px;display: none" id="pzy_no_data_">平台无数据</div>
                                    </div>
                                </div>
                            </div>
                            <div class="tab_sbox" style="margin-left: 7px;">
                                <div style="width: 100%;height: 80px;background-color: #f5f5f5">
                                    <div class="ts_top">
                                        <div class="ts_top_img"><img src="<%=path%>/static/dwpcpage/images/jinshou.jpg" alt="" /></div>
                                        <div class="ts_top_right">
                                            <p class="p1"><span class="span1">今收到</span><span class="span2">${kehuVo.kehuName}</span></p>
                                            <p class="p2">更新时间：2018年6月23日14:36</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="ts_bottom">
                                    <div class="ts_spec">
                                        <span class="p3" id="jsd_data" style="color: #777;padding-top: 20px;">请手动更新</span>
                                        <div style="text-align: center;color: red;margin-top: 40px;display: none" id="jsd_no_data_">平台无数据</div>
                                    </div>
                                </div>
                            </div>
                            <div class="tab_sbox" style="margin-left: 7px;">
                                <div style="width: 100%;height: 80px;background-color: #f5f5f5">
                                    <div class="ts_top">
                                        <div class="ts_top_img"><img src="<%=path%>/static/dwpcpage/images/jujian.jpg" alt="" /></div>
                                        <div class="ts_top_right">
                                            <p class="p1"><span class="span1">居间云</span><span class="span2">${kehuVo.kehuName}</span></p>
                                            <p class="p2">更新时间：2018年6月23日14:36</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="ts_bottom">
                                    <div class="ts_spec">
                                        <span class="p3" style="color: #777;padding-top: 20px;" id="jjy_data">请手动更新</span>
                                        <div style="text-align: center;color: red;margin-top: 40px;display: none" id="jjy_no_data_">平台无数据</div>
                                    </div>
                                </div>
                            </div>
                            <div class="tab_sbox" style="margin-left: 7px;">
                                <div style="width: 100%;height: 80px;background-color: #f5f5f5">
                                    <div class="ts_top">
                                        <div class="ts_top_img"><img src="<%=path%>/static/dwpcpage/images/qianle.jpg" alt="" /></div>
                                        <div class="ts_top_right">
                                            <p class="p1"><span class="span1">签了么</span><span class="span2">${kehuVo.kehuName}</span></p>
                                            <p class="p2">更新时间：2018年6月23日14:36</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="ts_bottom">
                                    <div class="ts_spec">
                                        <span class="p3" id="qlm_data" style="padding-top: 20px;text-align: left;width: 80%;margin-left: 10%;display: none">*签了么服务器故障，暂无数据，签了么服务器恢复，数据恢复</span>
                                        <span class="p3" id="qlm_no_data" style="color: #777;padding-top: 20px;">请手动更新</span>
                                        <div style="text-align: center;color: red;margin-top: 40px;display: none" id="qlm_no_data_">平台无数据</div>
                                    </div>
                                </div>
                            </div>
                            <div style="clear: both;"></div>
                        </div>
                    </div>
                    <div class="tab_box" style="display: none;">
                        <ul class="ns_ul_ul">
                            <li style="background: #fff;"><i><img src="<%=path%>/static/dwpcpage/images/mifang.jpg" alt="" /></i><span>米房服务</span></li>
                            <li><i><img src="<%=path%>/static/dwpcpage/images/youping.jpg" alt="" /></i><span>有凭证</span></li>
                            <li><i><img src="<%=path%>/static/dwpcpage/images/jinjiedao.jpg" alt="" /></i><span>今借到</span></li>
                            <li><i><img src="<%=path%>/static/dwpcpage/images/xiaocai.jpg" alt="" /></i><span>小财管家</span></li>
                            <li class="disabled"><i><img src="<%=path%>/static/dwpcpage/images/jiedaibao.jpg" alt="" /></i><span>即将上线</span></li>
                            <li class="disabled"><i><img src="<%=path%>/static/dwpcpage/images/wuyou.jpg" alt="" /></i><span>即将上线</span></li>
                            <li class="disabled"><i><img src="<%=path%>/static/dwpcpage/images/cloud.png" alt="" /></i><span>即将上线</span></li>
                            <div class="li_last">
                                <p style="color: #000">*为有效防止骗贷，认证费用将由扫码方支付</p>
                                <button id="find_btn">免费查询</button>
                                <p>*完成认证后，点击平台logo即可查看数据</p>
                            </div>
                        </ul>
                        <div class="tab_cont_cont">
                            <div class="tab_box_box">
                                <div id="mifang_have_data" class="data_info">
                                    <table width="100%" border="0" class="table_gj_3">
                                        <tr style="height: 100px;border: none;">
                                            <td width="40%" style="text-align: left;">
                                                <div style="width: 60px;height: 60px;position:relative;left: 10px"><img src="<%=path%>/static/img/a2.jpg" alt="" class="wx_img" style="width: 100%;"></div>
                                                <span style="margin-top: 5px;padding-left: 11px">扫码方手机型号:<span style ="color: red;" class="device">iphone6</span></span>
                                                报告获取时间：<span class="createTime">2018-07-09 10:32:53</span>
                                            </td>
                                            <td style="color: #000;text-align: right;border: none;font-size: 24px;background: #fff;" width="15%" rowspan="2">基本信息</td>
                                            <td style="color: #000;text-align: left;border: none;font-size:14px;background: #fff;width: 60px" rowspan="2">&nbsp;</td>
                                            <td style="color: #0583CE;text-align: left;border: none;font-size: 18px;background: #fff;" rowspan="2"><img src="<%=path%>/static/dwpcpage/images/dingwei.png" alt="" style="width: 20px;height: 20px;" /><span style="box-sizing:border-box;padding:5px;font-weight: 700;" class="address">武汉</span>（ip粗略定位）
                                                <a data-toggle="modal" data-target="#phone_dinwei" style="font-size: 14px;color: red;text-decoration: underline;">精准手机定位</a>
                                            </td>
                                        </tr>
                                    </table>
                                    <table width="100%" border="0" class="table2" id="mifang">
                                        <tr>
                                            <td>用户名</td>
                                            <td>{{username}}</td>
                                            <td>姓名</td>
                                            <td>{{name}}</td>
                                        </tr>
                                        <tr>
                                            <td>手机号</td>
                                            <td>{{mobile}}</td>
                                            <td>钱包余额</td>
                                            <td>{{walletBalance}}</td>
                                        </tr>
                                        <tr>
                                            <td>待还余额</td>
                                            <td>{{toRepayAmt}}</td>
                                            <td>代收金额</td>
                                            <td>{{receiveAmt}}</td>
                                        </tr>
                                    </table>
                                    <table width="100%" border="0" class="table_gj_1" style="margin-bottom: 10px;" id="mifang1">
                                        <tr style="height: 80px;border: none;">
                                            <td colspan="3" style="color: #000;text-align: center;border: none;font-size: 24px;background: #fff;">信用报告</td>
                                        </tr>
                                        <tr>
                                            <td colspan="3" style="background: #4D87B9;text-align: left;text-indent: 20px;border: none;border: 1px solid #ddd;color: #fff;">借贷概要</td>
                                        </tr>
                                        <tr>
                                            <td width="33.33%">当前借入金额<br /><span>{{curBorrowAmt}}</span>元</td>
                                            <td width="33.33%">当前借出金额<br /><span>{{curLendAmt}}</span>元</td>
                                            <td width="33.33%">当前担保金额<br /><span>{{curGuaranteeAmt}}</span>元</td>
                                        </tr>
                                        <tr>
                                            <td>累计借入金额<br /><span>{{borrowAmt}}</span>元</td>
                                            <td>累计借入笔数<br /><span>{{lendAmt}}</span>笔</td>
                                            <td>累计借入人数<br /><span>{{guaranteeAmt}}</span>人</td>
                                        </tr>
                                        <tr>
                                            <td>累计借出金额<br /><span>{{borrowCnt}}</span>元</td>
                                            <td>累计借出笔数<br /><span>{{lendCnt}}</span>笔</td>
                                            <td>累计借出人数<br /><span>{{guaranteeCnt}}</span>人</td>
                                        </tr>
                                        <tr>
                                            <td>累计担保金额<br /><span>{{borrowNum}}</span>元</td>
                                            <td>累计担保笔数<br /><span>{{lendNum}}</span>笔</td>
                                            <td>累计担保人数<br /><span>{{guaranteeNum}}</span>人</td>
                                        </tr>
                                        <tr>
                                            <td colspan="2">当天借入当天还占比<br /><span>{{borrow1dayPct}}</span></td>
                                            <td>当天借出当天收占比<br /><span>{{lend1dayPct}}</span></td>
                                        </tr>
                                    </table>
                                    <table width="100%" border="0" class="table_gj_1" id="mifang2">
                                        <tr>
                                            <td colspan="4" style="background: #4D87B9;text-align: left;text-indent: 20px;border: none;border: 1px solid #ddd;color: #fff;">逾期概要</td>
                                        </tr>
                                        <tr>
                                            <td>逾期金额占比<br /><span>{{overdueAmtPct}}</span></td>
                                            <td>逾期次数占比<br /><span>{{overdueCntPct}}</span></td>
                                            <td>累计逾期金额<br /><span>{{overdueAmt}}</span>元</td>
                                            <td>累计逾期次数<br /><span>{{overdueCnt}}</span>次</td>
                                        </tr>
                                        <tr>
                                            <td>大于7天逾期金额<br /><span>{{overdue7dayAmt}}</span>元</td>
                                            <td>大于7天逾期次数<br /><span>{{overdue7dayCnt}}</span>次</td>
                                            <td>当前逾期金额<br /><span>{{curOverdueAmt}}</span>元</td>
                                            <td>当前逾期次数<br /><span>{{curOoverdueCnt}}</span>次</td>
                                        </tr>
                                    </table>
                                    <table width="100%" border="0" class="table2" style="margin-bottom: 10px;" id="mifang3">
                                        <tr style="height: 80px;border: none;">
                                            <td colspan="4" style="color: #000;text-align: center;border: none;font-size: 24px;background: #fff;">借入详情</td>
                                        </tr>
                                        <tr>
                                            <td colspan="4" style="background: #4D87B9;text-align: left;text-indent: 20px;border: none;border: 1px solid #ddd;color: #fff;">借入信息</td>
                                        </tr>
                                        <tr>
                                            <td>最大待还金额</td>
                                            <td>{{amt}}</td>
                                            <td>总利息</td>
                                            <td>{{interest}}</td>
                                        </tr>
                                        <tr>
                                            <td>借入次数</td>
                                            <td colspan="3">{{count}}</td>
                                        </tr>
                                    </table>
                                    <table width="100%" border="0" class="table_gj_2" id="mifang4">
                                        <tr>
                                            <td colspan="8" style="background: #4D87B9;text-align: left;text-indent: 20px;border: none;border: 1px solid #ddd;color: #fff;">借入明细</td>
                                        </tr>
                                        <tr class="table_tr">
                                            <td>借款人</td>
                                            <td>出借人</td>
                                            <td>借款金额</td>
                                            <td>状态</td>
                                            <td>借款日期</td>
                                            <td>还款日期</td>
                                        </tr>
                                        <tr v-for="site in sites">
                                            <td>{{site.borrowerName}}</td>
                                            <td>{{site.lenderName}}</td>
                                            <td>{{site.amt}}</td>
                                            <td>
                                                <span v-if="site.status==1">还款中</span>
                                                <span v-else-if="site.status==2">已还清</span>
                                                <span v-else-if="site.status==3">已逾期</span>
                                                <span v-else-if="site.status==4">有争议</span>
                                                <span v-else-if="site.status==0">其他</span>
                                            </td>
                                            <td>{{site.borrowDate}}</td>
                                            <td>{{site.repayDate}}</td>
                                        </tr>

                                    </table>
                                    <table width="100%" border="0" class="table2" style="margin-bottom: 10px;" id="mifang5">
                                        <tr style="height: 80px;border: none;">
                                            <td colspan="4" style="color: #000;text-align: center;border: none;font-size: 24px;background: #fff;">借出详情</td>
                                        </tr>
                                        <tr>
                                            <td colspan="4" style="background: #4D87B9;text-align: left;text-indent: 20px;border: none;border: 1px solid #ddd;color: #fff;">借出信息</td>
                                        </tr>
                                        <tr>
                                            <td>最大待还金额</td>
                                            <td>{{amt}}</td>
                                            <td>总利息</td>
                                            <td>{{interest}}</td>
                                        </tr>
                                        <tr>
                                            <td>借入次数</td>
                                            <td colspan="3">{{count}}</td>
                                        </tr>
                                    </table>
                                    <table width="100%" border="0" class="table_gj_2" id="mifang6">
                                        <tr>
                                            <td colspan="8" style="background: #4D87B9;text-align: left;text-indent: 20px;border: none;border: 1px solid #ddd;color: #fff;">借出明细</td>
                                        </tr>
                                        <tr class="table_tr">
                                            <td>借款人</td>
                                            <td>出借人</td>
                                            <td>借款金额</td>
                                            <td>状态</td>
                                            <td>借款日期</td>
                                            <td>还款日期</td>
                                        </tr>
                                        <tr  v-for="site in sites">
                                            <td>{{site.borrowerName}}</td>
                                            <td>{{site.lenderName}}</td>
                                            <td>{{site.amt}}</td>
                                            <td>
                                                <span v-if="site.status==1">还款中</span>
                                                <span v-else-if="site.status==2">已还清</span>
                                                <span v-else-if="site.status==3">已逾期</span>
                                                <span v-else-if="site.status==4">有争议</span>
                                                <span v-else-if="site.status==0">其他</span>
                                            </td>
                                            <td>{{site.borrowDate}}</td>
                                            <td>{{site.repayDate}}</td>
                                        </tr>

                                    </table>
                                    <table width="100%" border="0" class="table2" style="margin-bottom: 10px;" id="mifang7">
                                        <tr style="height: 80px;border: none;">
                                            <td colspan="4" style="color: #000;text-align: center;border: none;font-size: 24px;background: #fff;">担保详情</td>
                                        </tr>
                                        <tr>
                                            <td colspan="4" style="background: #4D87B9;text-align: left;text-indent: 20px;border: none;border: 1px solid #ddd;color: #fff;">担保信息</td>
                                        </tr>
                                        <tr>
                                            <td>最大担保金额</td>
                                            <td>{{amt}}</td>
                                            <td>总收益</td>
                                            <td>{{interest}}</td>
                                        </tr>
                                        <tr>
                                            <td>担保笔数</td>
                                            <td>{{count}}</td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                    </table>
                                    <table width="100%" border="0" class="table_gj_2" id="mifang8">
                                        <tr>
                                            <td colspan="8" style="background: #4D87B9;text-align: left;text-indent: 20px;border: none;border: 1px solid #ddd;color: #fff;">担保明细</td>
                                        </tr>
                                        <tr class="table_tr">
                                            <td>借款人</td>
                                            <td>担保金额</td>
                                            <td>借款日期</td>
                                            <td>状态</td>
                                        </tr>
                                        <tr  v-for="site in sites">
                                            <td>{{site.borrowerName}}</td>
                                            <td>{{site.amt}}</td>
                                            <td>{{site.borrowDate}}</td>
                                            <td>
                                                <span v-if="site.status==1">还款中</span>
                                                <span v-else-if="site.status==2">已还清</span>
                                                <span v-else-if="site.status==3">已逾期</span>
                                                <span v-else-if="site.status==4">有争议</span>
                                                <span v-else-if="site.status==0">其他</span>
                                            </td>
                                        </tr>

                                    </table>
                                    <table width="100%" border="0" class="table2" style="margin-bottom: 10px;" id="mifang9">
                                        <tr style="height: 80px;border: none;">
                                            <td colspan="4" style="color: #000;text-align: center;border: none;font-size: 24px;background: #fff;">逾期信息</td>
                                        </tr>
                                        <tr>
                                            <td colspan="4" style="background: #4D87B9;text-align: left;text-indent: 20px;border: none;border: 1px solid #ddd;color: #fff;">逾期信息</td>
                                        </tr>
                                        <tr>
                                            <td style="width: 36%">累计逾期次数</td>
                                            <td style="width: 25%">{{count}}</td>
                                            <td style="width: 22%">最大逾期金额</td>
                                            <td>{{amt}}</td>
                                        </tr>
                                    </table>
                                    <table width="100%" border="0" class="table_gj_2" id="mifang10">
                                        <tr>
                                            <td colspan="8" style="background: #4D87B9;text-align: left;text-indent: 20px;border: none;border: 1px solid #ddd;color: #fff;">逾期明细</td>
                                        </tr>
                                        <tr class="table_tr">
                                            <td>逾期时间</td>
                                            <td>逾期金额</td>
                                            <td>逾期天数</td>
                                            <td>逾期状态</td>
                                        </tr>
                                        <tr  v-for="site in sites">
                                            <td>{{site.overdueDate}}</td>
                                            <td>{{site.overdueAmt}}</td>
                                            <td>{{site.overdueDays}}</td>
                                            <td>
                                                <span v-if="site.status==1">还款中</span>
                                                <span v-else-if="site.status==2">已还清</span>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                                <div id="mifang_no_data"  class="data_info_">
                                    扫码方未认证米房服务
                                </div>
                            </div>
                            <div class="tab_box_box" style="display: none;">
                                <div id="youpingzheng_have_data" class="data_info">
                                    <table width="100%" border="0" class="table_gj_3">
                                        <tr style="height: 100px;border: none;">
                                            <td width="40%" style="text-align: left;">
                                                <div style="width: 60px;height: 60px;position:relative;left: 10px"><img src="<%=path%>/static/img/a2.jpg" alt="" class="wx_img" style="width: 100%;"></div>
                                                <span style="margin-top: 5px;padding-left: 11px">扫码方手机型号:<span style ="color: red;" class="device">iphone6</span></span>
                                                报告获取时间：<span class="createTime">2018-07-09 10:32:53</span>
                                            </td>
                                            <td style="color: #000;text-align: right;border: none;font-size: 24px;background: #fff;" width="15%" rowspan="2">有凭证数据</td>
                                            <td style="color: #000;text-align: left;border: none;font-size:14px;background: #fff;width: 60px" rowspan="2">&nbsp;</td>
                                            <td style="color: #0583CE;text-align: left;border: none;font-size: 18px;background: #fff;" rowspan="2"><img src="<%=path%>/static/dwpcpage/images/dingwei.png" alt="" style="width: 20px;height: 20px;" /><span style="box-sizing:border-box;padding:5px;font-weight: 700;" class="address">武汉</span>（ip粗略定位）
                                                <a data-toggle="modal" data-target="#phone_dinwei" style="font-size: 14px;color: red;text-decoration: underline;">精准手机定位</a>
                                            </td>
                                        </tr>
                                    </table>
                                    <table width="100%" border="0" class="table2" style="margin-bottom: 10px;" id="youpingzheng">
                                        <tr>
                                            <td colspan="4" style="background: #4D87B9;text-align: left;text-indent: 20px;border: none;border: 1px solid #ddd;color: #fff;">基本信息</td>
                                        </tr>
                                        <tr>
                                            <td width="23%">姓名</td>
                                            <td width="32%">{{name}}</td>
                                            <td width="19%">用户名</td>
                                            <td>{{mobile}}</td>
                                        </tr>
                                        <tr>
                                            <td width="23%">身份证号</td>
                                            <td width="32%">{{identityNo}}</td>
                                            <td width="19%">银行卡号</td>
                                            <td>{{bankCard}}</td>
                                        </tr>
                                        <tr>
                                            <td width="23%">email</td>
                                            <td width="32%">{{email}}</td>
                                            <td width="19%">qq</td>
                                            <td>{{qq}}</td>
                                        </tr>
                                        <tr>
                                            <td width="23%">认证状态</td>
                                            <td width="32%">
                                                <span v-if="identityStatus==1">实名认证</span>
                                                <span v-else>未实名认证</span>
                                            </td>
                                            <td width="19%">余额</td>
                                            <td>{{balance}}</td>
                                        </tr>
                                        <tr>
                                            <td width="23%">待还金额</td>
                                            <td width="32%">{{toRepayAmt}}</td>
                                            <td width="19%">代收金额</td>
                                            <td>{{receiveAmt}}</td>
                                        </tr>
                                    </table>
                                    <table width="100%" border="0" class="table2" style="margin-bottom: 10px;" id="youpingzheng1">
                                        <tr>
                                            <td colspan="4" style="background: #4D87B9;text-align: left;text-indent: 20px;border: none;border: 1px solid #ddd;color: #fff;">我的信用</td>
                                        </tr>
                                        <tr>
                                            <td width="23%">借款逾期率</td>
                                            <td width="32%">{{loanOverdueRate}}</td>
                                            <td width="19%">待还金额</td>
                                            <td>{{toRepayAmt}}</td>
                                        </tr>
                                        <tr>
                                            <td width="23%">已还金额</td>
                                            <td width="32%">{{repayedAmt}}</td>
                                            <td width="19%">凭证总数</td>
                                            <td>{{pzCnt}}</td>
                                        </tr>
                                        <tr>
                                            <td width="23%">凭证总金额</td>
                                            <td width="32%">{{pzAmt}}</td>
                                            <td width="19%">已生效张数</td>
                                            <td>{{executedCnt}}</td>
                                        </tr>
                                        <tr>
                                            <td width="23%">已生效总金额</td>
                                            <td width="32%">{{executedAmt}}</td>
                                            <td width="19%">已冻结张数</td>
                                            <td>{{frozenCnt}}</td>
                                        </tr>
                                        <tr>
                                            <td width="23%">已冻结金额</td>
                                            <td width="32%">{{frozenAmt}}</td>
                                            <td width="19%">已撕毁张数</td>
                                            <td>{{toreUpCnt}}</td>
                                        </tr>
                                        <tr>
                                            <td width="23%">已撕毁金额</td>
                                            <td width="32%">{{toreUpAmt}}</td>
                                            <td width="19%">应还未还张数</td>
                                            <td>{{unpaidCnt}}</td>
                                        </tr>
                                        <tr>
                                            <td width="23%">应还未还金额</td>
                                            <td width="32%">{{unpaidAmt}}</td>
                                            <td width="19%">确认逾期张数 </td>
                                            <td>{{overdueCnt}}</td>
                                        </tr>
                                        <tr>
                                            <td width="23%">确认逾期金额</td>
                                            <td width="32%">{{overdueAmt}}</td>
                                            <td width="19%"></td>
                                            <td></td>
                                        </tr>
                                    </table>
                                    <table width="100%" border="0" class="table2" id="youpingzheng2">
                                    <tr>
                                        <td colspan="4" style="background: #B0CEFD;text-align: left;text-indent: 20px;border: none;border: 1px solid #ddd;">我的凭证</td>
                                    </tr>
                                    <tbody v-for="site in sites">
                                        <tr>
                                            <td width="23%">协议编号</td>
                                            <td width="32%">{{site.loanId}}</td>
                                            <td width="19%">创建时间</td>
                                            <td>{{site.createTime}}</td>
                                        </tr>
                                        <tr>
                                            <td width="23%">借款人</td>
                                            <td width="32%">{{site.borrowerName}}</td>
                                            <td width="19%">出借人</td>
                                            <td>{{site.lenderName}}</td>
                                        </tr>
                                        <tr>
                                            <td width="23%">借款金额</td>
                                            <td width="32%">{{site.amt}}</td>
                                            <td width="19%">利息</td>
                                            <td>{{site.interest}}</td>
                                        </tr>
                                        <tr>
                                            <td width="23%">本息合计</td>
                                            <td width="32%">{{site.principalInterestAmt}}</td>
                                            <td width="19%">状态</td>
                                            <td>
                                                <span v-if="site.status == 1">待确认</span>
                                                <span v-if="site.status == 2">已生效</span>
                                                <span v-if="site.status == 3">已冻结</span>
                                                <span v-if="site.status == 4">已撕毁</span>
                                                <span v-if="site.status == 5">已拒绝</span>
                                                <span v-if="site.status == 6">已删除</span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>类型</td>
                                            <td>
                                                <span v-if="site.type ==1">借入</span>
                                                <span v-else>借出</span>
                                            </td>
                                            <td>借款日期</td>
                                            <td>{{site.borrowDate}}</td>
                                        </tr>
                                        <tr>
                                            <td>还款日期</td>
                                            <td>{{site.repayDate}}</td>
                                            <td>年利率</td>
                                            <td>{{site.rate}}</td>
                                        </tr>
                                        <tr>
                                            <td>实际还款日期</td>
                                            <td>{{site.realRepayDate}}</td>
                                            <td>是否逾期</td>
                                            <td>{{site.isOverdue}}</td>
                                        </tr>
                                        <tr>
                                            <td>逾期天数</td>
                                            <td>{{site.overdueDays}}</td>
                                            <td>逾期罚息</td>
                                            <td>{{site.overdueInterest}}</td>
                                        </tr>
                                        <tr>
                                            <td>是否上传征信</td>
                                            <td>
                                                <span v-if="site.isUploadCredit==0">未上传</span>
                                                <span v-else>已上传</span>
                                            </td>
                                            <td>服务费</td>
                                            <td>{{site.serviceAmt}}</td>
                                        </tr>
                                        <tr>
                                            <td>借款用途</td>
                                            <td>{{site.usage}}</td>
                                            <td>补充说明</td>
                                            <td>{{site.memo}}</td>
                                        </tr>
                                        <tr style="height: 10px;border: none">
                                        </tr>
                                    </tbody>
                                </table>
                                </div>
                                <div id="youpingzheng_no_data"  class="data_info_">
                                    扫码方未认证有凭证
                                </div>
                            </div>
                            <div class="tab_box_box" style="display: none;">
                                <div id="jinjiedao_have_data" class="data_info">
                                    <table width="100%" border="0" class="table_gj_3">
                                        <tr style="height: 100px;border: none;">
                                            <td width="40%" style="text-align: left;">
                                                <div style="width: 60px;height: 60px;position:relative;left: 10px"><img src="<%=path%>/static/img/a2.jpg" class="wx_img" alt="" style="width: 100%;"></div>
                                                <span style="margin-top: 5px;padding-left: 11px">扫码方手机型号:<span style ="color: red;" class="device">iphone6</span></span>
                                                报告获取时间：<span class="createTime">2018-07-09 10:32:53</span>
                                            </td>
                                            <td style="color: #000;text-align: right;border: none;font-size: 24px;background: #fff;" width="15%" rowspan="2">今借到数据</td>
                                            <td style="color: #000;text-align: left;border: none;font-size:14px;background: #fff;width: 60px" rowspan="2">&nbsp;</td>
                                            <td style="color: #0583CE;text-align: left;border: none;font-size: 18px;background: #fff;" rowspan="2"><img src="<%=path%>/static/dwpcpage/images/dingwei.png" alt="" style="width: 20px;height: 20px;" /><span style="box-sizing:border-box;padding:5px;font-weight: 700;" class="address">武汉</span>（ip粗略定位）
                                                <a data-toggle="modal" data-target="#phone_dinwei" style="font-size: 14px;color: red;text-decoration: underline;">精准手机定位</a>
                                            </td>
                                        </tr>
                                    </table>
                                    <table width="100%" border="0" class="table2" style="margin-bottom: 10px;" id="jinjiedaobaseinfo">
                                        <tr>
                                            <td colspan="4" style="background: #4D87B9;text-align: left;text-indent: 20px;border: none;border: 1px solid #ddd;color: #fff;">基本信息</td>
                                        </tr>
                                        <tr>
                                            <td width="23%">用户名</td>
                                            <td width="32%">{{username}}</td>
                                            <td width="19%">姓名</td>
                                            <td>{{name}}</td>
                                        </tr>
                                        <tr>
                                            <td width="23%">手机号</td>
                                            <td width="32%">{{mobile}}</td>
                                            <td width="19%">钱包余额</td>
                                            <td>{{walletBalance}}</td>
                                        </tr>
                                        <tr>
                                            <td width="23%">待还金额</td>
                                            <td width="32%">{{toRepayAmt}}</td>
                                            <td width="19%">代收金额</td>
                                            <td>{{receiveAmt}}</td>
                                        </tr>
                                        <tr>
                                            <td width="23%">借款额度</td>
                                            <td width="32%">{{creditLimit}}</td>
                                            <td width="19%">风险等级测评</td>
                                            <td>{{riskAssessment}}</td>
                                        </tr>
                                    </table>
                                    <table width="100%" border="0" class="table2" style="margin-bottom: 10px;" id="jinjiedaobaseinfo1">
                                        <tr>
                                            <td colspan="4" style="background: #4D87B9;text-align: left;text-indent: 20px;border: none;border: 1px solid #ddd;color: #fff;">信用报告</td>
                                        </tr>
                                        <tr>
                                            <td colspan="4" style="background:#B0CEFD;text-align: left;text-indent: 20px;border: none;border: 1px solid #ddd;">借贷概要</td>
                                        </tr>
                                        <tr>
                                            <td width="23%">当前借入金额</td>
                                            <td width="32%">{{borrowSummary.curBorrowAmt}}</td>
                                            <td width="19%">当前借出金额</td>
                                            <td>{{borrowSummary.curLendAmt}}</td>
                                        </tr>
                                        <tr>
                                            <td width="23%">当前担保金额</td>
                                            <td width="32%">{{borrowSummary.curGuaranteeAmt}}</td>
                                            <td width="19%">最大借入金额</td>
                                            <td>{{borrowSummary.maxBorrowAmt}}</td>
                                        </tr>
                                        <tr>
                                            <td width="23%">最大借出金额</td>
                                            <td width="32%">{{borrowSummary.maxLendAmt}}</td>
                                            <td width="19%">最大担保金额</td>
                                            <td>{{borrowSummary.maxGuaranteeAmt}}</td>
                                        </tr>
                                        <tr>
                                            <td width="23%">累计借入笔数</td>
                                            <td width="32%">{{borrowSummary.borrowCnt}}</td>
                                            <td width="19%">累计借出笔数</td>
                                            <td>{{borrowSummary.lendCnt}}</td>
                                        </tr>
                                        <tr>
                                            <td width="23%">累计担保笔数</td>
                                            <td width="32%">{{borrowSummary.guaranteeCnt}}</td>
                                            <td width="19%">累计借入人数</td>
                                            <td>{{borrowSummary.borrowNum}}</td>
                                        </tr>
                                        <tr>
                                            <td width="23%">累计借出人数</td>
                                            <td width="32%">{{borrowSummary.lendNum}}</td>
                                            <td width="19%">累计担保人数</td>
                                            <td>{{borrowSummary.guaranteeNum}}</td>
                                        </tr>
                                        <tr>
                                            <td width="23%">当天借入当天还占比</td>
                                            <td width="32%">{{borrowSummary.borrow1dayPct}}</td>
                                            <td width="19%">当天借出当天收占比</td>
                                            <td>{{borrowSummary.lend1dayPct}}</td>
                                        </tr>
                                        <tr>
                                            <td colspan="4" style="background:#B0CEFD;text-align: left;text-indent: 20px;border: none;border: 1px solid #ddd;">逾期概要</td>
                                        </tr>
                                        <tr>
                                            <td width="23%">逾期金额占比</td>
                                            <td width="32%">{{overdueSummary.overdueAmtPct}}</td>
                                            <td width="19%">逾期次数占比</td>
                                            <td>{{overdueSummary.overdueCntPct}}</td>
                                        </tr>
                                        <tr>
                                            <td width="23%">最大逾期金额</td>
                                            <td width="32%">{{overdueSummary.maxOverdueAmt}}</td>
                                            <td width="19%">最大逾期次数</td>
                                            <td>{{overdueSummary.maxOverdueCnt}}</td>
                                        </tr>
                                        <tr>
                                            <td width="23%">大于7天逾期金额</td>
                                            <td width="32%">{{overdueSummary.overdue7dayAmt}}</td>
                                            <td width="19%">大于7天逾期次数</td>
                                            <td>{{overdueSummary.overdue7dayCnt}}</td>
                                        </tr>
                                        <tr>
                                            <td width="23%">当前逾期金额</td>
                                            <td width="32%">{{overdueSummary.curOverdueAmt}}</td>
                                            <td width="19%">当前逾期次数</td>
                                            <td>{{overdueSummary.curOoverdueCnt}}</td>
                                        </tr>
                                    </table>
                                    <table width="100%" border="0" class="table2" id="jinjiedaobaseinfo2">
                                        <tr>
                                            <td colspan="6" style="background: #4D87B9;text-align: left;text-indent: 20px;border: none;border: 1px solid #ddd;color: #fff;">借入详情</td>
                                        </tr>
                                        <tr>
                                            <td width="23%">最大待还金额</td>
                                            <td width="32%">{{amt}}</td>
                                            <td width="19%">总利息</td>
                                            <td>{{interest}}</td>
                                        </tr>
                                        <tr>
                                            <td width="23%">借入次数</td>
                                            <td width="32%">{{count}}</td>
                                            <td width="19%"></td>
                                            <td></td>
                                        </tr>
                                    </table>
                                    <table width="100%" border="0" class="table_gj_2" style="margin-bottom: 10px;" id="jinjiedaobaseinfo3" >
                                        <tr>
                                            <td colspan="8" style="background: #B0CEFD;text-align: left;text-indent: 20px;border: none;border: 1px solid #ddd;">借入明细</td>
                                        </tr>
                                        <tr class="table_tr">
                                            <td>借款人</td>
                                            <td>出借人</td>
                                            <td>借款金额</td>
                                            <td>待还金额</td>
                                            <td>已还金额</td>
                                            <td>状态</td>
                                            <td>借款日期</td>
                                            <td>还款日期</td>
                                        </tr>
                                        <tr  v-for="site in sites">
                                            <td>{{site.borrowerName}}</td>
                                            <td>{{site.lenderName}}</td>
                                            <td>{{site.amt}}</td>
                                            <td>{{site.toRepayAmt}}</td>
                                            <td>{{site.repayedAmt}}</td>
                                            <td>
                                                <span v-if="site.status==1">还款中</span>
                                                <span v-else-if="site.status==2">已还清</span>
                                                <span v-else-if="site.status==3">已逾期</span>
                                                <span v-else-if="site.status==4">有争议</span>
                                                <span v-else-if="site.status==0">其他</span>
                                            </td>
                                            <td>{{site.borrowDate}}</td>
                                            <td>{{site.repayDate}}</td>
                                        </tr>
                                    </table>
                                    <table width="100%" border="0" class="table2" id="jinjiedaobaseinfo4">
                                        <tr>
                                            <td colspan="4" style="background: #4D87B9;text-align: left;text-indent: 20px;border: none;border: 1px solid #ddd;color: #fff;">借出详情</td>
                                        </tr>
                                        <tr>
                                            <td width="23%">借出总金额</td>
                                            <td width="32%">{{amt}}</td>
                                            <td width="19%">总利息</td>
                                            <td>{{interest}}</td>
                                        </tr>
                                        <tr>
                                            <td width="23%">借出次数</td>
                                            <td width="32%">{{count}}</td>
                                            <td width="19%"></td>
                                            <td></td>
                                        </tr>
                                    </table>
                                    <table width="100%" border="0" class="table_gj_2" style="margin-bottom: 10px;" id="jinjiedaobaseinfo5">
                                        <tr>
                                            <td colspan="8" style="background: #B0CEFD;text-align: left;text-indent: 20px;border: none;border: 1px solid #ddd;">借出明细</td>
                                        </tr>
                                        <tr class="table_tr">
                                            <td>借款人</td>
                                            <td>出借人</td>
                                            <td>借款金额</td>
                                            <td>待还金额</td>
                                            <td>已还金额</td>
                                            <td>状态</td>
                                            <td>借款日期</td>
                                            <td>还款日期</td>
                                        </tr>
                                        <tr  v-for="site in sites">
                                            <td>{{site.borrowerName}}</td>
                                            <td>{{site.lenderName}}</td>
                                            <td>{{site.amt}}</td>
                                            <td>{{site.toRepayAmt}}</td>
                                            <td>{{site.repayedAmt}}</td>
                                            <td>
                                                <span v-if="site.status==1">还款中</span>
                                                <span v-else-if="site.status==2">已还清</span>
                                                <span v-else-if="site.status==3">已逾期</span>
                                                <span v-else-if="site.status==4">有争议</span>
                                                <span v-else-if="site.status==0">其他</span>
                                            </td>
                                            <td>{{site.borrowDate}}</td>
                                            <td>{{site.repayDate}}</td>
                                        </tr>
                                    </table>
                                    <table width="100%" border="0" class="table2" id="jinjiedaobaseinfo6">
                                        <tr>
                                            <td colspan="4" style="background: #4D87B9;text-align: left;text-indent: 20px;border: none;border: 1px solid #ddd;color: #fff;">担保详情</td>
                                        </tr>
                                        <tr>
                                            <td width="23%">最大担保金额</td>
                                            <td width="32%">{{amt}}</td>
                                            <td width="19%">总收益</td>
                                            <td>{{interest}}</td>
                                        </tr>
                                        <tr>
                                            <td width="23%">担保笔数</td>
                                            <td width="32%">{{count}}</td>
                                            <td width="19%"></td>
                                            <td></td>
                                        </tr>
                                    </table>
                                    <table width="100%" border="0" class="table_gj_2" style="margin-bottom: 10px;" id="jinjiedaobaseinfo7">
                                        <tr>
                                            <td colspan="8" style="background: #B0CEFD;text-align: left;text-indent: 20px;border: none;border: 1px solid #ddd;">担保明细</td>
                                        </tr>
                                        <tr class="table_tr">
                                            <td>借款人</td>
                                            <td>担保金额</td>
                                            <td>借款日期</td>
                                            <td>状态</td>
                                        </tr>
                                        <tr  v-for="site in sites">
                                            <td>{{site.borrowerName}}</td>
                                            <td>{{site.amt}}</td>
                                            <td>{{site.borrowDate}}</td>
                                            <td>
                                                <span v-if="site.status==1">还款中</span>
                                                <span v-else-if="site.status==2">已还清</span>
                                                <span v-else-if="site.status==3">已逾期</span>
                                                <span v-else-if="site.status==4">有争议</span>
                                                <span v-else-if="site.status==0">其他</span>
                                            </td>
                                        </tr>
                                    </table>
                                    <table width="100%" border="0" class="table2" id="jinjiedaobaseinfo8">
                                        <tr>
                                            <td colspan="4" style="background: #4D87B9;text-align: left;text-indent: 20px;border: none;border: 1px solid #ddd;color: #fff;">逾期信息</td>
                                        </tr>
                                        <tr>
                                            <td width="23%">累计逾期次数</td>
                                            <td width="32%">{{count}}</td>
                                            <td width="19%">最大逾期金额</td>
                                            <td>{{amt}}</td>
                                        </tr>
                                    </table>
                                    <table width="100%" border="0" class="table_gj_2" style="margin-bottom: 10px;" id="jinjiedaobaseinfo9">
                                    <tr>
                                        <td colspan="8" style="background: #B0CEFD;text-align: left;text-indent: 20px;border: none;border: 1px solid #ddd;">逾期明细</td>
                                    </tr>
                                    <tr class="table_tr">
                                        <td>逾期时间</td>
                                        <td>逾期金额</td>
                                        <td>逾期天数</td>
                                        <td>逾期状态</td>
                                    </tr>
                                    <tr  v-for="site in sites">
                                        <td>{{site.overdueDate}}</td>
                                        <td>{{site.overdueAmt}}</td>
                                        <td>{{site.overdueDays}}</td>
                                        <td>
                                            <span v-if="site.status==1">还款中</span>
                                            <span v-else-if="site.status==2">已还清</span>
                                        </td>
                                    </tr>
                                </table>
                                </div>
                                <div id="jinjiedao_no_data" class="data_info_">
                                    扫码方未认证今借到
                                </div>
                            </div>
                            <div class="tab_box_box" style="display: none;">
                                <div id="xiaocai_have_data" class="data_info">
                                    <table width="100%" border="0" class="table_gj_3">
                                        <tr style="height: 100px;border: none;">
                                            <td width="40%" style="text-align: left;">
                                                <div style="width: 60px;height: 60px;position:relative;left: 10px"><img src="<%=path%>/static/img/a2.jpg" class="wx_img" alt="" style="width: 100%;"></div>
                                                <span style="margin-top: 5px;padding-left: 11px">扫码方手机型号:<span style ="color: red;" class="device">iphone6</span></span>
                                                报告获取时间：<span class="createTime">2018-07-09 10:32:53</span>
                                            </td>
                                            <td style="color: #000;text-align: right;border: none;font-size: 24px;background: #fff;" width="15%" rowspan="2">小财管家</td>
                                            <td style="color: #000;text-align: left;border: none;font-size:14px;background: #fff;width:60px" rowspan="2"></td>
                                            <td style="color: #0583CE;text-align: left;border: none;font-size: 18px;background: #fff;" rowspan="2"><img src="<%=path%>/static/dwpcpage/images/dingwei.png" alt="" style="width: 20px;height: 20px;" /><span style="box-sizing:border-box;padding:5px;font-weight: 700;" class="address">武汉</span>（ip粗略定位）
                                                <a data-toggle="modal" data-target="#phone_dinwei" style="font-size: 14px;color: red;text-decoration: underline;">精准手机定位</a>
                                            </td>
                                        </tr>
                                    </table>
                                    <table width="100%" border="0" class="table2" style="margin-bottom: 10px;" id="xiaocaibasedata">
                                        <tr>
                                            <td>姓名</td>
                                            <td>{{member.name}}</td>
                                            <td>手机号</td>
                                            <td>{{member.phone}}</td>
                                        </tr>
                                        <tr>
                                            <td>身份证号</td>
                                            <td>{{member.idCard}}</td>
                                            <td>银行卡号</td>
                                            <td>{{member.bankCardId}}</td>
                                        </tr>
                                    </table>
                                    <table width="100%" border="0" class="table_gj_1" style="margin-bottom: 10px;" id="xiaocaidata">
                                        <tr>
                                            <td colspan="3" style="background: #4D87B9;text-align: left;text-indent: 20px;border: none;border: 1px solid #ddd;color: #fff;">数据总览</td>
                                        </tr>
                                        <tr>
                                            <td width="33.33%">待还金额<br /><span>{{summary.total}}</span>元</td>
                                            <td width="33.33%">已完结<br /><span>{{summary.finish}}</span>元</td>
                                            <td width="33.33%">今日待还<br /><span>{{summary.today}}</span>元</td>
                                        </tr>
                                    </table>
                                    <table width="100%" border="0" class="table_gj_2" id="xiaocaidata1">
                                    <tr>
                                        <td colspan="8" style="background: #4D87B9;text-align: left;text-indent: 20px;border: none;border: 1px solid #ddd;color: #fff;">借入明细</td>
                                    </tr>
                                    <tr class="table_tr">
                                        <td>借款人</td>
                                        <td>出借人</td>
                                        <td>借款金额</td>
                                        <td>借款日期</td>
                                        <td>还款日期</td>
                                        <td>状态</td>
                                    </tr>
                                    <tr v-for="site in sites">
                                        <td>{{site.borrowerName}}</td>
                                        <td>{{site.lenderName}}</td>
                                        <td>{{site.money}}</td>
                                        <td>{{site.timeBorrow | time}}</td>
                                        <td>{{site.timeReturn | time}}</td>
                                        <td>
                                            <span v-if="site.status<3">待确认</span>
                                            <span v-else-if="site.status==3">生效中</span>
                                            <span v-else-if="site.status==8">系统关单</span>
                                            <span v-else>已完结</span>
                                        </td>
                                    </tr>

                                </table>
                                </div>
                                <div id="xiaocai_no_data" class="data_info_">
                                    扫码方未认证小财管家
                                </div>
                            </div>
                            <div class="tab_box_box" style="display: none;">555</div>
                            <div class="tab_box_box" style="display: none;">666</div>
                            <div class="tab_box_box" style="display: none;">777</div>
                        </div>
                    </div>
                    <div class="tab_box" style="display: none;">
                        <div id="no_datas" style="display: none;">
                            <div style="text-align: center;height: 70px;line-height: 30px;font-size: 20px">
                                网贷、小贷、分期、多手机号-<span style="color: red;">部分样例</span>
                            </div>
                            <img src="" id="no_data_img" alt="">
                        </div>
                        <div id="datas">
                            <table width="100%" border="0" class="table1">
                                <tr>
                                    <td colspan="2" style="background: #4D87B9;color: #fff;text-align: left;text-indent: 20px;border: none;border: 1px solid #ddd;">个人风险评估报告</td>
                                </tr>
                                <tr>
                                    <td style="text-align: center;border: 1px solid #ddd;" width="30%">
                                        <canvas id="process" width="200" height="200"></canvas>
                                    </td>
                                    <td style="border: 1px solid #ddd;text-indent: 20px;" width="70%" id="msg_val">申请用户未出现高危风险，建议通过</td>
                                </tr>
                            </table>
                            <table width="100%" border="0" class="table2" id="app">
                                <tr>
                                    <td colspan="4" style="background: #4D87B9;color: #fff;text-align: left;text-indent: 20px;border: none;border: 1px solid #ddd;">基本信息</td>
                                </tr>
                                <tr>
                                    <td>姓名</td>
                                    <td id="pg_name">${kehuVo.kehuName}</td>
                                    <td>性别</td>
                                    <td id="pg_sex">${kehuVo.sexVal}</td>
                                </tr>
                                <tr>
                                    <td>身份证号</td>
                                    <td>${kehuVo.kehuIdcard}</td>
                                    <td>年龄</td>
                                    <td>${kehuVo.kehuAge}</td>
                                </tr>
                                <tr>
                                    <td>身份证号归属地</td>
                                    <td>${kehuVo.kehuAddress}</td>
                                    <td>手机号</td>
                                    <td>${kehuVo.kehuPhone}</td>
                                </tr>
                                <tr>
                                    <td>手机号归属地</td>
                                    <td colspan="3" id="phone_address">湖北省武汉市</td>
                                </tr>
                            </table>
                            <table border="0" width="100%" class="table3">
                                <tr>
                                    <td colspan="4" style="background: #4D87B9;color: #fff;text-align: left;text-indent: 20px;border: none;border: 1px solid #ddd;">风险情况</td>
                                </tr>
                                <tr>
                                    <td colspan="2" style="background: #E2EAFB;text-align: center;text-indent: 0;" width="45%">检测项</td>
                                    <td style="background: #E2EAFB;text-align: center;text-indent: 0;" width="10%">风险等级</td>
                                    <td style="background: #E2EAFB;text-align: center;text-indent: 0;" width="45%">详情</td>
                                </tr>
                                <tr>
                                    <td width="20%">个人基本信息核查</td>
                                    <td colspan="3"><img src="<%=path%>/static/dwpcpage/images/right.png" alt="" style="width: 30px;height: 30px;" /></td>
                                </tr>
                                <tr>
                                    <td width="20%">多借贷负债检测</td>
                                    <td colspan="3"><img src="<%=path%>/static/dwpcpage/images/right.png" alt="" style="width: 30px;height: 30px;" /></td>
                                </tr>
                            </table>
                            <table border="0" width="100%" class="table3" id="xinwei">
                            </table>
                            <table border="0" width="100%" class="table3" id="duopingtai">
                            </table>
                            <table border="0" width="100%" class="table3" id="fenxianxinxi">
                            </table>
                        </div>
                    </div>
                </div>
                <div style="height: 50px;width: 100%;">
                    &nbsp;
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
<div class="modal fade" id="qrimg" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true">
    <div class="modal-dialog" style="width: 500px;border-radius: 0px;height: 400px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="qrimg1">
                    扫描二维码开始认证
                </h4>
            </div>
            <div class="modal-body">
                <div style="text-align: center;margin: auto">
                    <p style="text-align:center;font-size: 14px">请<span style="color: red">截图</span>分享给被查询人，完成认证后，点击平台logo即可查看数据</p>
                    <img src="<%=path%>/static/dwpcpage/images/kf.jpg" id="qrimgs" alt="地网数据客服" style="height: 258px;width: 258px">
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
                <h4 class="modal-title" id="addgiveModule">
                    修改手机号
                </h4>
            </div>
            <form class="form-horizontal" id="checkform" >
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
<%--网站信息的修改--%>
<div class="modal fade" id="msg" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 360px;margin-left: 130px">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" >
                    提示
                </h4>
            </div>
            <div class="modal-body">
                <p style="color: red">完成认证后，点击平台logo即可查看数据</p>
            </div>
            <div class="modal-footer">
                <button type="button"  class="btn btn-primary" style="border-top: 0px solid #e5e5e5;" data-dismiss="modal">
                    确认
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="phone_dinwei" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true">
    <div class="modal-dialog" style="width: 500px;border-radius: 0px;height: 400px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="phone_dinwei1">
                    扫描二维码开始认证
                </h4>
            </div>
            <div class="modal-body">
                <div style="text-align: center;margin: auto">
                    <p style="text-align:center;font-size: 14px">客服私查优惠价<span style="color: red">9.9</span>元/次：<span style="color: red">详情地址+精准地图定位</span></p>
                    <img src="<%=path%>/static/dwpcpage/images/kf.jpg"  alt="地网数据客服" style="height: 258px;width: 258px">
                    <p style="text-align:center;font-size: 12px;color: #797979">扫一扫，加好友，地网小姐姐耐心为您服务</p>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<input type="hidden" value="" id="init">
</body>
<script src="<%=path%>/static/dwpcpage/js/jquery.min.js"></script>
<script src="<%=path%>/static/dwpcpage/js/fuzhai_01.js"></script>
<script src="<%=path%>/static/dwpcpage/js/bootstrap.min.js"></script>
<script src="<%=path%>/static/js/vue.min.js"></script>
<script src="<%=path%>/static/js/plugins/layer/layer.js"></script>
<script >
    var phone = "${userPCVo.phone}";
    if (!phone) {
        location.href = "/pcUser/exit";
    }
    var mphone = phone.substr(0, 3) + '****' + phone.substr(7);
    $('.ul_right_span3').html(mphone);
    $('#qrimg').on('hide.bs.modal',
        function() {
            $("#msg").modal("show");
        })
    refushInfo();
    function refushInfo() {
        $.post(
            "/pcUser/refushInfo",
            function (data) {
                $("#kehujifen").html(data.jifen);
            },"json"
        );
    }
    function info(val) {
        location.href = "/pcUser/info/" + val;
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
    var vm = new Vue({
        el: '#remarkList',
        data: {
            sites: ''
        }
    });
    refush_mark();
    function refush_mark() {
        $.post(
            "/pcUserRemark/getAllList",
            {
                "kehuId":"${kehuVo.id}"
            },
            function (msg) {
                vm.$data.sites = msg;
                document.getElementById("remarkList").style.visibility="visible";
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
                refush_mark();
            },"json"
        );
    });
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
    //风险评估
    $("#fxbt").click(function () {
        layer.msg("玩命获取数据中...");
        getFxpgbg(0);
    });
    getFxpgbg(1);
    function getFxpgbg(val) {
        /*
        * st(
            "/yongxun/getFenXianPingGu",
            {
                "kehuName":$("#kehuname").text(),
                "kehuIdcard":$("#kehucard").text(),
                "kehuPhone":$("#phone").text()
            }
        * */
        var khxwjc = '<tr>\n' +
            '                                <td rowspan="~" width="20%">~</td>\n' +
            '                                <td width="25%">~</td>\n' +
            '                                <td width="10%" style="text-align: center;text-indent: 0;">~</td>\n' +
            '                                <td width="45%">\n' +
            '                                    <dl type="disc">~\n' +
            '                                    </dl>\n' +
            '                                </td>\n' +
            '                            </tr>';
        var khxwjc1 = '<tr>\n' +
            '                                <td width="25%">~</td>\n' +
            '                                <td width="10%" style="text-align: center;text-indent: 0;">~</td>\n' +
            '                                <td width="45%">\n' +
            '                                    <dl type="disc">~\n' +
            '                                    </dl>\n' +
            '                                </td>\n' +
            '                            </tr>';
        $.post(
            "/yongxun/getFenXianPingGu",
            {
                "kehuName":$("#kehuname").text(),
                "kehuIdcard":$("#kehucard").text(),
                "kehuPhone":$("#phone").text(),
                "id":"${kehuVo.id}",
                "handle":val
            },function (msg) {
                if(msg.result=="success") {
                    var data = JSON.parse(msg.message);
                    if(!data){
                        $("#datas").hide();
                        $("#no_data_img").attr("src","<%=path%>/static/img/fx.png");
                        $("#no_datas").show();
                    }else{
                        $("#datas").show();
                        $("#no_datas").hide();
                        $("#phone_address").html(data.data.addressDetect.mobileAddress);
                        $("#init").val(data.data.riskScore);
                        animate();
                        var status= data.data.riskDecision;
                        if(status=="accept"){
                            $("#msg_val").html("申请用户未出现高危风险，建议通过");
                        }else if(status=="review"){
                            $("#msg_val").html("申请用户可能出现高危风险，建议审核");
                        }else{
                            $("#msg_val").html("申请用户出现高危风险，建议拒绝");
                        }
                        if(!data){
                            //隐藏数据部分
                            $("#datas").hide();
                            $("#no_datas").show();
                            $("#no_data_img").attr("src","<%=path%>/static/img/fx.png");
                        }else{
                            $("#datas").show();
                            $("#no_datas").hide();
                            var items = data.data.riskItems;
                            var kehuxingweijiance = new Array();
                            var duopingtaijiedai = new Array();
                            var fengxianxinxi = new Array();
                            for (var i = 0; i < items.length; i++) {
                                if (items[i].group.indexOf("客户行为检测") > -1) {
                                    kehuxingweijiance.push(items[i]);
                                } else if (items[i].group.indexOf("多平台") > -1) {
                                    duopingtaijiedai.push(items[i]);
                                } else if (items[i].group.indexOf("风险信息扫描") > -1) {
                                    fengxianxinxi.push(items[i]);
                                }
                            }
                            var html = '';
                            for (var f = 0; f < kehuxingweijiance.length; f++) {
                                var info = new Array();
                                var $html = '';
                                var $ddhtml = '';
                                info = kehuxingweijiance[f].itemDetail.details;
                                for (var d1 = 0; d1 < info.length; d1++) {
                                    for (d2 = 0; d2 < info[d1].length; d2++) {
                                        if (info[d1][d2].key == "data") {
                                            var phone_list = new Array();
                                            phone_list = info[d1][d2].value.replace("[", "").replace("]", "").split(",");
                                            for (var p = 0; p < phone_list.length; p++) {
                                                $ddhtml = $ddhtml + '<dd>' + phone_list[p] + '</dd>';
                                            }
                                        } else if (info[d1][d2].key == "detail") {
                                            $html = $html + '<dt>' + info[d1][d2].value + '</dt>';
                                        }

                                    }
                                    $html = $html + $ddhtml;
                                }
                                if (f == 0) {
                                    html = html + khxwjc.replace("~", kehuxingweijiance.length).replace("~", kehuxingweijiance[f].group).replace("~", kehuxingweijiance[f].itemName).replace("~", getLevel(kehuxingweijiance[f].riskLevel)).replace("~", $html);
                                } else {
                                    html = html + khxwjc1.replace("~", kehuxingweijiance[f].itemName).replace("~", getLevel(kehuxingweijiance[f].riskLevel)).replace("~", $html);
                                }
                            }
                            $("#xinwei").html(html);
                            //--------------- 多借贷申请检测------------------
                            var html2 = '';
                            for (var d = 0; d < duopingtaijiedai.length; d++) {
                                var info = new Array();
                                var $html = '';
                                var $ddhtml = '';
                                info = duopingtaijiedai[d].itemDetail.details;
                                for (var d1 = 0; d1 < info.length; d1++) {
                                    for (d2 = 0; d2 < info[d1].length; d2++) {
                                        if (info[d1][d2].key == "detail") {
                                            var phone_list = new Array();
                                            phone_list = info[d1][d2].value.replace("[", "").replace("]", "").split(",");
                                            for (var p = 0; p < phone_list.length; p++) {
                                                $ddhtml = $ddhtml + '<dd>' + phone_list[p] + '</dd>';
                                            }
                                        } else if (info[d1][d2].key == "dimension") {
                                            $html = $html + '<dt>' + info[d1][d2].value + '</dt>';
                                        }

                                    }
                                    $html = $html + $ddhtml;
                                }
                                if (d == 0) {
                                    html2 = html2 + khxwjc.replace("~", duopingtaijiedai.length).replace("~", duopingtaijiedai[d].group).replace("~", duopingtaijiedai[d].itemName).replace("~", getLevel(duopingtaijiedai[d].riskLevel)).replace("~", $html);
                                } else {
                                    html2 = html2 + khxwjc1.replace("~", duopingtaijiedai[d].itemName).replace("~", getLevel(duopingtaijiedai[d].riskLevel)).replace("~", $html);
                                }
                            }
                            $("#duopingtai").html(html2);
                            //--------------- 风险信息------------------
                            var html3 = '';
                            for (var x = 0; x < fengxianxinxi.length; x++) {
                                var info = new Array();
                                var $html = '';
                                var $ddhtml = '';
                                info = fengxianxinxi[x].itemDetail.details;
                                for (var d1 = 0; d1 < info.length; d1++) {
                                    for (d2 = 0; d2 < info[d1].length; d2++) {
                                        if (info[d1][d2].key == "fraud_type") {
                                            $ddhtml = $ddhtml + '<dd><b>风险类型:</b>' + info[d1][d2].value.replace("[", "").replace("]", "") + '</dd>';
                                        } else if (info[d1][d2].key == "description") {
                                            $html = $html + '<dt><b>规则描述:</b>' + info[d1][d2].value + '</dt>';
                                        } else if (info[d1][d2].key == "hit_type_displayname") {
                                            $ddhtml = $ddhtml + '<dd><b>匹配字段:</b>' + info[d1][d2].value.replace("[", "").replace("]", "") + '</dd>';
                                        }

                                    }
                                    $html = $html + $ddhtml;
                                }
                                if (x == 0) {
                                    html3 = html3 + khxwjc.replace("~", fengxianxinxi.length).replace("~", fengxianxinxi[x].group).replace("~", fengxianxinxi[x].itemName).replace("~", getLevel(fengxianxinxi[x].riskLevel)).replace("~", $html);
                                } else {
                                    html3 = html3 + khxwjc1.replace("~", fengxianxinxi[x].itemName).replace("~", getLevel(fengxianxinxi[x].riskLevel)).replace("~", $html);
                                }
                            }
                            $("#fenxianxinxi").html(html3);

                        }
                    }
                }else{
                    layer.alert(msg.message);
                }
            },"json"
        );

    }
    function getLevel(val) {
        if(val=="low"){
            return "低";
        }else if(val=="medium"){
            return "中";
        }else if(val=="high"){
            return "高";
        }else{
            return "未知";
        }
    }
    getPtcx(0);
    //普通查询
    $("#ptcx").click(function () {
        layer.msg("玩命获取数据中...");
        getPtcx(1);
    });
    function getPtcx(val) {
        $.post(
            "/search/searchData",
            {
                "kehuName":"${kehuVo.kehuName}",
                "kehuPhone":"${kehuVo.kehuPhone}",
                "kehuIdcard":"${kehuVo.kehuIdcard}",
                "handle":val
            },function (msg) {
                if(msg.result=="fail"){
                    layer.alert(msg.message);
                }else{
                    var msg_val = msg.message;
                    if(msg_val!=-1){
                        $.post(
                            "/generalQuery/findGeneralQuery/"+msg.message,
                            function (msg) {
                                setHtml(msg);
                            },"json"
                        );
                    }else{
                    }

                }
            },"json"

        );
    }
    $.post(
        "/generalQuery/getInfo",
        {
          "kehuId":"${kehuVo.id}"
        },
        function (msg) {
            setHtml(msg);
        },"json"

    );
    function setHtml(msg) {
        $("#qlm_data").show();
        $("#qlm_no_data").hide();
        $("#jsd_data").hide();
        $("#jsd_no_data_").show();
        $("#jjy_data").hide();
        $("#jjy_no_data_").show();
        var info = "";
        if(!msg.wyjybaoData){
            info = JSON.parse(msg.wyjybaoData);
        }
        var info1 = "";
        if(msg.sxyData){
            info1 = JSON.parse(msg.sxyData);
        }
        var info2 = JSON.parse(msg.zcjData);
        var info3 = "";
        if(msg.xysData){
            info3 = JSON.parse(msg.xysData);
        }
        var info4 = msg.pzyData;
        $(".p2").html("更新时间:"+formartTime1(msg.createTime));
        if(!info.data ||info.data.trade.length==0){
            $("#wyjyb").hide();
            $("#wyjyb_no_data").hide();
            $("#wyjyb_no_data_").show();
        }else{
            $("#wyjyb_no_data").hide();
            $("#wyjyb").show();
            new Vue({
                el: '#wyjyb',
                data: {
                    wyjybs: info.data.trade
                }
            })
        }
        if(!info1 || !info1.lists){
            $("#sxy").hide();
            $("#sxy_no_data").hide();
            $("#sxy_no_data_").show();
        }else{
            $("#sxy_no_data").hide();
            $("#sxy").show();
            new Vue({
                el: '#sxy',
                data: {
                    sxys: info1.lists
                }
            })
        }
        if(!info2.data){
            $("#zcj").hide();
            $("#zcj_no_data").hide();
            $("#zcj_no_data_").show();
        }else{
            $("#zcj_no_data").hide();
            $("#zcj").show();
            new Vue({
                el: '#zcj',
                data: {
                    zcjs: info2.data
                }
            })
        }
        if(!info3){
            $("#xys_data").hide();
            $("#xys_no_data").hide();
            $("#xys_no_data_").show();
        }else{
            $("#xys_no_data").hide();
            $("#xys_data").show();
            $("#xys_data").html();
        }
        if(!info4){
            $("#pzy").hide();
            $("#pzy_no_data").hide();
            $("#pzy_html").hide();
            $("#pzy_no_data_").show();
        }else{
            if(info4.indexOf(".html")>-1){
                $("#pzy_no_data").hide();
                $("#pzy").show();
                $("#pzy_html").show();
                setTimeout(function(){$("#pzy_html").attr("src",info4);},5000);
            }else{
                $("#pzy").hide();
                $("#pzy_no_data").hide();
                $("#pzy_html").hide();
                $("#pzy_no_data_").show();
            }
        }

    }
    function formartTime1(value) {
        var date = new Date(value);
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        var d = date.getDate();
        var h = date.getHours();
        var mi = date.getMinutes();
        var ss = date.getSeconds();
        return y + '年' + (parseInt(m)<10?"0"+m:m) + '月' + (parseInt(d)<10?"0"+d:d) + '日'+(parseInt(h)<10?"0"+h:h) + ':'+(parseInt(mi)<10?"0"+mi:mi);
    }
    function getDate(value) {
        var date = new Date(value);
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        var d = date.getDate();
        var h = date.getHours();
        var mi = date.getMinutes();
        var ss = date.getSeconds();
        return y + '-' + (parseInt(m)<10?"0"+m:m) + '-' + (parseInt(d)<10?"0"+d:d) + '-'+(parseInt(h)<10?"0"+h:h) + ':'+(parseInt(mi)<10?"0"+mi:mi);
    }
    $("#find_btn").click(function () {
        var hekuId = "${kehuVo.id}"
        $.post(
            "/startHighFind/getQrImg",
            {
                "kehuId":hekuId
            },function (data) {
                $("#qrimgs").attr("src",data.message);
                $("#qrimg").modal("show");
            },"json"
        );
    });
    highSearch();
    //高级查询
    function highSearch() {
        $.post(
            "/highFind/getBaseInfo",
            {
                "kehuId":${kehuVo.id}
            },function (data) {
                if(data.code=="1000"){
                    $(".data_info").hide();
                    $(".data_info_").show();
                }else{
                    $(".device").html(data.device);
                    $(".createTime").html(getDate(data.createTime));
                    $(".address").html(data.address);
                    $(".wx_img").attr("src",data.wxImg);
                    //小才管家基本数据显示
                    if(data.xiaocaiguanjiaStatus==0){
                        $("#xiaocai_no_data").hide();
                        $("#xiaocai_have_data").show();
                        xiaocaibasedata(data.xiaocaiguanjiaBaseData);
                        xiaocaidata(data.xiaocaiguanjiaData);
                        xiaocaidata1(data.xiaocaiguanjiaData);
                    }else{
                        $("#xiaocai_no_data").show();
                        $("#xiaocai_have_data").hide();
                    }
                    if(data.jinjiedaoStatus==0){
                        $("#jinjiedao_no_data").hide();
                        $("#jinjiedao_have_data").show();
                        jinjiedaoBaseInfo(data.jinjiedaoData);
                        jinjiedaoBaseInfo1(data.jinjiedaoData);
                        jinjiedaoBaseInfo2(data.jinjiedaoData);
                        jinjiedaoBaseInfo3(data.jinjiedaoData);
                        jinjiedaoBaseInfo4(data.jinjiedaoData);
                        jinjiedaoBaseInfo5(data.jinjiedaoData);
                        jinjiedaoBaseInfo6(data.jinjiedaoData);
                        jinjiedaoBaseInfo7(data.jinjiedaoData);
                        jinjiedaoBaseInfo8(data.jinjiedaoData);
                        jinjiedaoBaseInfo9(data.jinjiedaoData);
                    }else{
                        $("#jinjiedao_no_data").show();
                        $("#jinjiedao_have_data").hide();
                    }
                    if(data.youpingzhengStatus==0){
                        $("#youpingzheng_no_data").hide();
                        $("#youpingzheng_have_data").show();
                        youpingzhengData(data.youpingzhengData);
                        youpingzhengData1(data.youpingzhengData);
                        youpingzhengData2(data.youpingzhengData);
                    }else{
                        $("#youpingzheng_no_data").show();
                        $("#youpingzheng_have_data").hide();
                    }
                    if(data.mifangStatus==0){
                        $("#mifang_no_data").hide();
                        $("#mifang_have_data").show();
                        mifangData(data.mifangData);
                        mifangData1(data.mifangData);
                        mifangData2(data.mifangData);
                        mifangData3(data.mifangData);
                        mifangData4(data.mifangData);
                        mifangData5(data.mifangData);
                        mifangData6(data.mifangData);
                        mifangData7(data.mifangData);
                        mifangData8(data.mifangData);
                        mifangData9(data.mifangData);
                        mifangData10(data.mifangData);
                    }else{
                        $("#mifang_no_data").show();
                        $("#mifang_have_data").hide();
                    }
                }

            },"json"
        );
    }
    function xiaocaibasedata(val) {
        var data = JSON.parse(val);
        new Vue({
            el: '#xiaocaibasedata',
            data: data.msg
        })
    }
    function xiaocaidata(val) {
        var data = JSON.parse(val);
        new Vue({
            el: '#xiaocaidata',
            data: data.msg
        })
    }
    function xiaocaidata1(val) {
        var data = JSON.parse(val);
        var datainfo = data.msg.contractList;
        new Vue({
            el: '#xiaocaidata1',
            data: {
                sites: datainfo
            }
        })
    }
    function jinjiedaoBaseInfo(val) {
        var data = JSON.parse(val);
        var datainfo = data.data.basicInfo;
        new Vue({
            el: '#jinjiedaobaseinfo',
            data: datainfo
        })
    }
    function jinjiedaoBaseInfo1(val) {
        var data = JSON.parse(val);
        var datainfo = data.data.creditReport;
        new Vue({
            el: '#jinjiedaobaseinfo1',
            data: datainfo
        })
    }
    function jinjiedaoBaseInfo2(val) {
        var data = JSON.parse(val);
        var datainfo = data.data.borrow;
        new Vue({
            el: '#jinjiedaobaseinfo2',
            data: datainfo
        })
    }
    function jinjiedaoBaseInfo3(val) {
        var data = JSON.parse(val);
        var datainfo = data.data.borrow;
        if(datainfo.details.length==0){
            $("#jinjiedaobaseinfo3").remove();
        }else{
            new Vue({
                el: '#jinjiedaobaseinfo3',
                data: {
                    sites:datainfo.details
                }
            })
        }
    }
    function jinjiedaoBaseInfo4(val) {
        var data = JSON.parse(val);
        var datainfo = data.data.lend;
        new Vue({
            el: '#jinjiedaobaseinfo4',
            data: datainfo
        })
    }
    function jinjiedaoBaseInfo5(val) {
        var data = JSON.parse(val);
        var datainfo = data.data.lend;
        if(datainfo.details.length==0){
            $("#jinjiedaobaseinfo5").remove();
        }else{
            new Vue({
                el: '#jinjiedaobaseinfo5',
                data: {
                    sites:datainfo.details
                }
            })
        }
    }
    function jinjiedaoBaseInfo6(val) {
        var data = JSON.parse(val);
        var datainfo = data.data.guarantee;
        if(datainfo==null){
            $("#jinjiedaobaseinfo6").remove();
        }else{
            new Vue({
                el: '#jinjiedaobaseinfo6',
                data: datainfo
            })
        }
    }
    function jinjiedaoBaseInfo7(val) {
        var data = JSON.parse(val);
        var datainfo = data.data.guarantee;
        if(datainfo==null){
            $("#jinjiedaobaseinfo7").remove();
        }else{
            new Vue({
                el: '#jinjiedaobaseinfo7',
                data: {
                    sites:datainfo.details
                }
            })
        }
    }
    function jinjiedaoBaseInfo8(val) {
        var data = JSON.parse(val);
        var datainfo = data.data.overdue;
        if(datainfo==null){
            $("#jinjiedaobaseinfo8").remove();
        }else{
            new Vue({
                el: '#jinjiedaobaseinfo8',
                data: datainfo
            })
        }
    }
    function jinjiedaoBaseInfo9(val) {
        var data = JSON.parse(val);
        var datainfo = data.data.overdue;
        if(datainfo==null || datainfo.details.length==0){
            $("#jinjiedaobaseinfo9").remove();
        }else{
            new Vue({
                el: '#jinjiedaobaseinfo9',
                data: {
                    sites:datainfo.details
                }
            })
        }
    }
    function youpingzhengData(val) {
        var data = JSON.parse(val);
        new Vue({
            el: '#youpingzheng',
            data: data.data.basicInfo
        })
    }
    function youpingzhengData1(val) {
        var data = JSON.parse(val);
        new Vue({
            el: '#youpingzheng1',
            data: data.data.myCredit
        })
    }
    function youpingzhengData2(val) {
        var data = JSON.parse(val);
        var datainfo = data.data.mypzs;
        if(datainfo==null || datainfo.length==0){
            $("#youpingzheng2").remove();
        }else{
            new Vue({
                el: '#youpingzheng2',
                data: {
                    sites:datainfo
                }
            })
        }
    }
    function mifangData(val) {
        var data = JSON.parse(val);
        new Vue({
            el: '#mifang',
            data: data.data.basicInfo
        })
    }
    function mifangData1(val) {
        var data = JSON.parse(val);
        var datainfo = data.data.creditReport;
        new Vue({
            el: '#mifang1',
            data: data.data.creditReport.borrowSummary
        })
    }
    function mifangData2(val) {
        var data = JSON.parse(val);
        new Vue({
            el: '#mifang2',
            data: data.data.creditReport.overdueSummary
        })
    }
    function mifangData3(val) {
        var data = JSON.parse(val);
        new Vue({
            el: '#mifang3',
            data: data.data.borrow
        })
    }
    function mifangData4(val) {
        var data = JSON.parse(val);
        var datainfo = data.data.borrow;
        if(datainfo.details.length==0){
            $("#mifangData4").remove();
        }else{
            new Vue({
                el: '#mifang4',
                data: {
                    sites:datainfo.details
                }
            })
        }
    }
    function mifangData5(val) {
        var data = JSON.parse(val);
        new Vue({
            el: '#mifang5',
            data: data.data.lend
        })
    }
    function mifangData6(val) {
        var data = JSON.parse(val);
        var datainfo = data.data.lend;
        if(datainfo.details.length==0){
            $("#mifangData4").remove();
        }else{
            new Vue({
                el: '#mifang6',
                data: {
                    sites:datainfo.details
                }
            })
        }
    }
    function mifangData7(val) {
        var data = JSON.parse(val);
        if(data.data.guarantee==null){
            $("#mifang7").remove();
        }else{
            new Vue({
                el: '#mifang7',
                data: data.data.guarantee
            })
        }

    }
    function mifangData8(val) {
        var data = JSON.parse(val);
        var datainfo = data.data.guarantee;
        if(datainfo==null || datainfo.details.length==0){
            $("#mifang8").remove();
        }else{
            new Vue({
                el: '#mifang8',
                data: {
                    sites:datainfo.details
                }
            })
        }
    }
    function mifangData9(val) {
        var data = JSON.parse(val);
        if(data.data.overdue==null){
            $("#mifang9").remove();
        }else{
            new Vue({
                el: '#mifang9',
                data: data.data.overdue
            })
        }

    }
    function mifangData10(val) {
        var data = JSON.parse(val);
        var datainfo = data.data.overdue;
        if(datainfo==null || datainfo.details.length==0){
            $("#mifang10").remove();
        }else{
            new Vue({
                el: '#mifang10',
                data: {
                    sites:datainfo.details
                }
            })
        }
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
        var c = document.getElementById('process'),
            process = 0,
            ctx = c.getContext('2d');
        // 画灰色的圆
        ctx.beginPath();
        ctx.arc(100, 100, 80, 0, Math.PI * 2);
        ctx.closePath();
        ctx.fillStyle = '#F6F6F6';
        ctx.fill();

        function animate() {
            requestAnimationFrame(function() {
                process = process + 1;
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
            ctx.arc(100, 100, 80, Math.PI * 1.5, Math.PI * (1.5 + 2 * percent / 100));
            ctx.closePath();
            ctx.fillStyle = '#FF9600';
            ctx.fill();

            // 画内填充圆
            ctx.beginPath();
            ctx.arc(100, 100, 75, 0, Math.PI * 2);
            ctx.closePath();
            ctx.fillStyle = '#fff';
            ctx.fill();

            // 填充文字
            ctx.font = "bold 20pt Microsoft YaHei";
            ctx.fillStyle = '#333';
            ctx.textAlign = 'center';
            ctx.textBaseline = 'middle';
            ctx.moveTo(100, 100);
            ctx.fillText(process, 100, 100);
        }

</script>

</html>