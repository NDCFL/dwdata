package com.dwsj.common.wechat;

import com.dwsj.common.WebUtil;

/**
 * Created by Wang Genshen on 2017-07-04.
 */
public class WechatAPI {

    public static final String APP_ID = "wx30eec4db9d73a76f";
    public static final String APP_KEY = "960c385e93aa96e7287223e265258992";
    public static final String MCH_ID = "1502625151";
    public static final String API_KEY = "gycx1ZtE1BRw3iPece4QgHaD7ioGaCmt";
    public static final String MD5 = "MD5";
    public static final String TRADE_JSAPI = "JSAPI";
    public static final String TRADE_MWEB= "MWEB";
    public static final String TRADE_NATIVE = "NATIVE";
    public static final String REDIRECT_URL = "http://192.168.3.18:8080/pay/srcpay";
    public static final String REDIRECT_DIANSHANG_URL = "http://192.168.3.18:8080/pay/dssrcpay";
    public static final String ACCESS_LOGIN_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + APP_ID + "&redirect_uri=" + WebUtil.encodeUrl(REDIRECT_URL) + "&response_type=code&scope=snsapi_userinfo&agentid=1000004&state=access#wechat_redirect";
    public static final String ACCESS_LOGIN_DIANSHANG_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + APP_ID + "&redirect_uri=" + WebUtil.encodeUrl(REDIRECT_DIANSHANG_URL) + "&response_type=code&scope=snsapi_userinfo&agentid=1000004&state=access#wechat_redirect";
    public static final String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+APP_ID+"&secret="+APP_KEY+"&code={CODE}&grant_type=authorization_code";
    public static final String GET_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?access_token={ACCESS_TOKEN}&openid={OPENID}&lang=zh_CN";
    public static final String ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    public static final String EMPINFO = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserdetail?access_token={ACCESS_TOKEN}";
    public static final String NOTIFY_URL = "http://192.168.3.18:8080/pay/result";
    public static final String NOTIFY_URL_PHONE = "http://192.168.3.18:8080/pay/phone_result";
    public static final String NOTIFY_URL_DIANGSHANG = "http://192.168.3.18:8080/pay/dianshang_result";
    public static final String GETOPENID = "https://qyapi.weixin.qq.com/cgi-bin/user/convert_to_openid?access_token={ACCESS_TOKEN}";
    public static final String NOTIFY_RESULT = "<xml>" +
            "  <return_code><![CDATA[SUCCESS]]></return_code>" +
            "  <return_msg><![CDATA[OK]]></return_msg>" +
            "</xml>";
}
