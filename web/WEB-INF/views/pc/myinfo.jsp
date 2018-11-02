<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/6/23 0023
  Time: 上午 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>个人中心</title>
    <link rel="stylesheet" href="<%=path%>/static/css/common.css">
    <style>
        .memu_active{
            background-color: #000;
        }
    </style>
</head>
<body>
<div style="width: 100%;background-color:rgba(228, 228, 228, 1);height: 100px;">
    <div style="width: 65%;text-align: center;margin: auto;height: 100%;">
        <div style="width: 16%;height: 100%;background-color: #0583ce;float: left">
            <div style="height: 100%;width: 40%;float: left;margin-left: 9%">
                <img src="<%=path%>/static/img/a1.jpg" alt="" style="width: 65px;height: 65px;border-radius: 100%;margin-top: 18px">
            </div>
            <div style="height: 100%;width: 48%;float: left;">
                <div style="height: 50%;width: 100%;line-height:50px;color: #FFFFFF;font-style: normal;font-weight: 700;font-family: '微软雅黑 Bold', '微软雅黑 Regular', '微软雅黑';text-align: left;margin-left: 10px">
                    地网数据
                </div>
                <div style="height: 50%;width: 100%;line-height: 30px;color: #FFFFFF;font-style: normal;font-weight: 700;font-family: '微软雅黑 Bold', '微软雅黑 Regular', '微软雅黑';font-size: 11px;text-align: left;margin-left: 10px" id="phone">
                    手机号
                </div>
            </div>
        </div>
        <div style="height: 100%;float: left;width: 84%;">
            <div style="width: 35%;float: left;height: 100%;clear: both;">
                <div style="width: 20%;float: left;height: 100%;margin-left: 13%;">
                    <div style="height: 50%;width: 100%;">
                        <img src="<%=path%>/static/img/cx.png" alt="" style="width: 30px;height: 30px;padding-top: 45%">
                    </div>
                    <div style="height: 50%;width: 100%;text-align: center;color: #666666;line-height: 50px;">
                        查询记录
                    </div>
                </div>
                <div style="width: 20%;float: left;height: 100%;">
                    <div style="height: 50%;width: 100%;">
                        <img src="<%=path%>/static/img/jg.png" alt="" style="width: 30px;height: 30px;padding-top: 45%">
                    </div>
                    <div style="height: 50%;width: 100%;text-align: center;color: #666666;line-height: 50px;">
                        价格
                    </div>
                </div>
                <div style="width: 20%;float: left;height: 100%;">
                    <div style="height: 50%;width: 100%;">
                        <img src="<%=path%>/static/img/api.png" alt="" style="width: 30px;height: 30px;padding-top: 45%">
                    </div>
                    <div style="height: 50%;width: 100%;text-align: center;color: #666666;line-height: 50px;">
                        API服务
                    </div>
                </div>
                <div style="width: 25%;float: left;height: 100%;">
                    <div style="height: 50%;width: 100%;">
                        <img src="<%=path%>/static/img/dw.png" alt="" style="width: 30px;height: 30px;padding-top:35%">
                    </div>
                    <div style="height: 50%;width: 100%;text-align: center;color: #666666;line-height: 50px;">
                        加入地网
                    </div>
                </div>

            </div>
            <div style="width: 20%;float: left;height: 100%;">
                <div style=";height: 50%;font-family: '微软雅黑 Bold', '微软雅黑 Regular', '微软雅黑';font-weight: 700;font-style: normal;font-size: 20px;color: #0583CE;line-height: 70px">
                    <span>${userPCVo.kehu}</span>人
                </div>
                <div style=";height: 50%;font-family: '微软雅黑 Bold', '微软雅黑 Regular', '微软雅黑';font-weight: 700;font-style: normal;font-size: 20px;color: #A1A1A1;">
                    我的客户
                </div>
            </div>
            <div style="width: 15%;float: left;height: 100%;">
                <div style="line-height: 100px;font-family: '微软雅黑 Bold', '微软雅黑 Regular', '微软雅黑';font-weight: 700;font-style: normal;font-size: 20px;color: #A1A1A1;height: 100%">
                    <span style="color: #999">可用积分:</span><span style="color: #E74C3C;">${userPCVo.jifen}</span>
                </div>
            </div>
            <div style="width: 10%;float: left;height: 100%;">
                <div href="" style="width: 100px;height: 50px;margin-top: 25px;background-color: #e74c3c;border-radius: 50px">
                    <img src="<%=path%>/static/img/cz.png" alt="" style="width: 40px;height: 40px;margin-top: 5px">
                </div>
            </div>
            <div style="width: 18%;float: right;height: 100%;">
                <div style="width: 48%;float: left;height: 100%;">
                    <div style="height: 50%;width: 100%;">
                        <img src="<%=path%>/static/img/updatepwd.png" alt="" style="width: 30px;height: 30px;padding-top: 35%">
                    </div>
                    <div style="height: 50%;width: 100%;text-align: center;color: #666666;line-height: 50px;">
                        修改密码
                    </div>
                </div>
                <div style="width:48%;float: left;height: 100%;" onmouseover="up_div();" onmouseout="down_div();">
                    <a href="/pcUser/exit">
                        <div style="height: 50%;width: 100%;">
                            <img src="<%=path%>/static/img/exit.png" id="exit_img" alt="" style="width: 30px;height: 30px;padding-top: 35%">
                        </div>
                        <div style="height: 50%;width: 100%;text-align: center;color: #666666;line-height: 50px;display: none;color: red" id="exit">
                            退出登录
                        </div>
                    </a>
                </div>
            </div>
        </div>
    </div>
    <div style="width: 65%;text-align: center;margin: auto;height: auto">
        <div style="width: 16%;height: 1000px;background-color: rgba(52, 73, 94, 1);float: left;">
            <div style="height: 90px;width: 100%;"  class="memu memu_active">
                <div style="height:50px;" class="">
                    <div style="width: 30%;height: 50px;float: left"><img src="<%=path%>/static/img/my.png" alt="" style="height: 35px;height: 35px;float: right;margin-top: 13px"></div>
                    <div style="width:60%;height: 50px;float: left;font-family: '微软雅黑 Bold', '微软雅黑 Regular', '微软雅黑';font-weight: 700;font-style: normal;color: #0583CE;font-size: 24px;line-height: 50px">个人中心</div>
                </div>
                <div style="margin-top: -10px;clear: both;;height: 40px;color: #fff;width: 95%;text-align: center;margin: auto;font-size: 15px;line-height: 20px">
                    加客服微信，送100积分
                </div>
            </div>
            <div style="height: 50px;width: 100%;margin-top:30px;" class="memu">
                <div style="height: 50px;">
                    <div style="width: 30%;height: 50px;float: left"><img src="<%=path%>/static/img/allkefu.png" alt="" style="height: 30px;height: 30px;float: right;margin-top: 13px"></div>
                    <div style="width:60%;height: 50px;float: left;font-family: '微软雅黑 Bold', '微软雅黑 Regular', '微软雅黑';font-weight: 700;font-style: normal;color: #93A3A4;font-size: 21px;line-height: 50px">全部客户</div>
                </div>
            </div>
            <div style="height: 50px;width: 100%;margin-top:30px;" class="memu">
                <div style="height: 50px;">
                    <div style="width: 30%;height: 50px;float: left"><img src="<%=path%>/static/img/gz.png" alt="" style="height: 30px;height: 30px;float: right;margin-top: 13px"></div>
                    <div style="width:60%;height: 50px;float: left;font-family: '微软雅黑 Bold', '微软雅黑 Regular', '微软雅黑';font-weight: 700;font-style: normal;color: #93A3A4;font-size: 21px;line-height: 50px">特别关注</div>
                </div>
            </div>
            <div style="height: 50px;width: 100%;margin-top:30px;" class="memu">
                <div style="height: 50px;">
                    <div style="width: 30%;height: 50px;float: left"><img src="<%=path%>/static/img/hz.png" alt="" style="height: 30px;height: 30px;float: right;margin-top: 13px"></div>
                    <div style="width:60%;height: 50px;float: left;font-family: '微软雅黑 Bold', '微软雅黑 Regular', '微软雅黑';font-weight: 700;font-style: normal;color: #93A3A4;font-size: 21px;line-height: 50px">已合作客服</div>
                </div>
            </div>
            <div style="height: 50px;width: 100%;margin-top:30px;" class="memu">
                <div style="height: 50px;">
                    <div style="width: 30%;height: 50px;float: left"><img src="<%=path%>/static/img/weihz.png" alt="" style="height: 30px;height: 30px;float: right;margin-top: 13px"></div>
                    <div style="width:60%;height: 50px;float: left;font-family: '微软雅黑 Bold', '微软雅黑 Regular', '微软雅黑';font-weight: 700;font-style: normal;color: #93A3A4;font-size: 21px;line-height: 50px">未合作客户</div>
                </div>
            </div>
            <div style="height: 140px;width: 100%;margin-top:60px;">
                <div style="height: 50px;margin-top: 15px">
                    <div style="width: 23%;height: 50px;float: left"><img src="<%=path%>/static/img/listen.png" alt="" style="height: 25px;height: 25px;float: right;margin-top: 15px"></div>
                    <div style="width:50%;height: 50px;float: left;font-family: '微软雅黑 Bold', '微软雅黑 Regular', '微软雅黑';font-weight: 700;font-style: normal;color: #fff;font-size: 18px;line-height: 50px;padding-left: 5px">扫一扫，领</div>
                    <div style="width: 20%;height: 50px;float: left"><img src="<%=path%>/static/img/money.png" alt="" style="height: 30px;height: 30px;float: right;margin-top: 15px"></div>
                </div>
                <div style="height: 150px;;color: #fff;width: 95%;text-align: center;margin: auto;font-size: 15px">
                    <img src="<%=path%>/static/img/qrcode.jpg" alt="" style="width: 150px;height: 150px">
                </div>
            </div>
        </div>
        <div style="width: 100%;height: 1000px">
            <iframe src="<%=path%>/myinfo_1.jsp" name="right" frameborder="0" style="width:84%;height: 1000px" scrolling="no"></iframe>
        </div>

    </div>
</div>
</body>
<script src="<%=path%>/static/js/jquery.min.js"></script>
<script>
    var phone = "${userPCVo.phone}";
    if(!phone){
        location.href = "/pcUser/exit";
    }
    var mphone = phone.substr(0, 3) + '****' + phone.substr(7);
    $('#phone').html(mphone)
    function up_div() {
        $("#exit").show();
        $('#exit_img').attr('src', '<%=path%>/static/img/exit_1.png');
    }
    function down_div() {
        $("#exit").hide();
        $('#exit_img').attr('src', '<%=path%>/static/img/exit.png');
    }
    $(".memu").click(function () {
        $(this).addClass("memu_active").siblings().removeClass("memu_active");
    });

</script>
</html>
