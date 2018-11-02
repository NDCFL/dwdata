package com.dwsj.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dwsj.common.HttpSendUtil;
import com.dwsj.common.Message;
import com.dwsj.common.StatusQuery;
import com.dwsj.common.ZxingKit;
import com.dwsj.common.wechat.WechatAPI;
import com.dwsj.common.wechat.WechatUtil;
import com.dwsj.service.*;
import com.dwsj.vo.*;
import com.github.wxpay.sdk.WXPayUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.*;

@Controller
@RequestMapping("pay")
public class PayController {
    @Resource
    private UserPCService userPCService;
    @Resource
    private PcUserConsumeService pcUserConsumeService;
    @Resource
    private JiFenService jiFenService;
    @Resource
    private FixedPriceService fixedPriceService;
    @Resource
    private HighFindService highFindService;

    @Resource
    private DianShangService dianShangService;
    private static Long id = null;
    private static  Long dsId = null;
    /**
     * 验证Token
     * @param msgSignature 签名串，对应URL参数的signature
     * @param timeStamp 时间戳，对应URL参数的timestamp
     * @param nonce 随机串，对应URL参数的nonce
     *
     * @return 是否为安全签名
     */
    public static boolean verifyUrl(String msgSignature, String timeStamp, String nonce)
            throws Exception {
        // 这里的 WXPublicConstants.TOKEN 填写你自己设置的Token就可以了
        String signature = getSHA1("diwangshuju666", timeStamp, nonce);
        return true;
    }
    public static String getSHA1(String token, String timestamp, String nonce) throws Exception {
        try {
            String[] array = new String[]{token, timestamp, nonce};
            StringBuffer sb = new StringBuffer();
            // 字符串排序
            Arrays.sort(array);
            for (int i = 0; i < 3; i++) {
                sb.append(array[i]);
            }
            String str = sb.toString();
            // SHA1签名生成
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(str.getBytes());
            byte[] digest = md.digest();

            StringBuffer hexstr = new StringBuffer();
            String shaHex = "";
            for (int i = 0; i < digest.length; i++) {
                shaHex = Integer.toHexString(digest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexstr.append(0);
                }
                hexstr.append(shaHex);
            }
            return hexstr.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping("/checkTocken")
    @ResponseBody
    public String verifyWXToken(HttpServletRequest request) throws  Exception {
        String msgSignature = request.getParameter("signature");
        String msgTimestamp = request.getParameter("timestamp");
        String msgNonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        if (verifyUrl(msgSignature, msgTimestamp, msgNonce)) {
            return echostr;
        }
        return null;
    }
    //*******************************微信登录首链接****************************
    @RequestMapping("index")
    public void index(HttpServletRequest req, HttpServletResponse resp,HttpSession session) throws IOException, ServletException {
        id = Long.parseLong(req.getParameter("id"));
        resp.sendRedirect(WechatAPI.ACCESS_LOGIN_URL);
    }
    //回调地址
    @RequestMapping("srcpay")
    public ModelAndView srcpay(HttpServletRequest request) throws IOException, ServletException {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("session中的编号"+id+"===================================");
        HighFindVo highFindVo = new HighFindVo();
        highFindVo.setId(id);
        try {
            String code = request.getParameter("code");
            WechatUtil wechatUtil = new WechatUtil();
            //获取accessTocken
            String accesInfo = wechatUtil.authLogin(code);
            //解析tocken
            JSONObject accesTockenJSON = JSON.parseObject(accesInfo);
            String accesTocken = accesTockenJSON.get("access_token").toString();
            //获取到企业授权登录用户的基本信息
            String openIdInfo = wechatUtil.getUserInfo(accesTocken,accesTockenJSON.get("openid").toString());
            JSONObject object = JSON.parseObject(openIdInfo);
            highFindVo.setOpenid(object.get("openid").toString());
            highFindVo.setWxImg(object.get("headimgurl").toString());
            FixedPriceVo fixedPriceVo = fixedPriceService.getById(0l);
            //需要支付的金额
            highFindVo.setMoney(Float.parseFloat(fixedPriceVo.getPrice()/10+""));
            modelAndView.addObject("openid",object.get("openid").toString());
            highFindService.update(highFindVo);
            modelAndView.setViewName("pc/pay");
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelAndView.setViewName("pc/pay");
        return modelAndView;
    }
    @RequestMapping("pay")
    public String pay(HttpServletRequest req, HttpServletResponse resp,String openid,HttpSession session) throws IOException, ServletException {
        System.out.println("session中的编号"+id+"===================================");
        String uuid = WXPayUtil.generateUUID();
        FixedPriceVo fixedPriceVo = fixedPriceService.getById(0l);
        HighFindVo highFindVo = new HighFindVo();
        highFindVo.setOrderitem(uuid);
        System.out.println(uuid+"=====================");
        BigDecimal money = BigDecimal.valueOf(fixedPriceVo.getPrice()/10);
        highFindVo.setId(id);
        highFindService.update(highFindVo);
        //移除session中的参数
        session.removeAttribute("findId");
        if (money != BigDecimal.valueOf(0)) {
            WechatUtil wechatUtil = new WechatUtil();
            Map<String, String> prepayResult = wechatUtil.prepayResult(openid,req.getRemoteAddr(), "地网数据-高级认证支付",fixedPriceVo.getPrice(),uuid);
            // 正式付款需要提交的数据
            Map<String, String> payData = wechatUtil.payData(prepayResult);
            req.setAttribute("appId", WechatAPI.APP_ID);
            req.setAttribute("timeStamp", payData.get("timeStamp"));
            req.setAttribute("nonceStr", payData.get("nonceStr"));
            req.setAttribute("packages", payData.get("package"));
            req.setAttribute("paySign", payData.get("paySign"));
            req.setAttribute("userMoney", money);
        }
        return "pc/qrpay"; // 预支付数据转发到页面，调用js支付
    }
    @RequestMapping("phone_result")
    public void phone_result(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        System.out.println("***********************notify_url*************************");
        WechatUtil wechatUtil = new WechatUtil();
        HighFindVo highFindVo= new HighFindVo();
        Map<String, String> resultMap = wechatUtil.payResult(req);
        System.out.println(resultMap+"=====解析公众号支付之后的数据======");
        String resultCode = resultMap.get("result_code");
        if (resultCode != null && resultCode.equals("SUCCESS")) {
            String orderId = resultMap.get("out_trade_no");
            highFindVo= highFindService.getInfoByOrderId(orderId);
            //修改状态
            highFindService.updateStatus(new StatusQuery(highFindVo.getId(),0));
        }else{
            System.out.println("公众号支付失败。。。。。。。。。。。。。。。。。");
        }
        resp.sendRedirect("http://192.168.3.18:8080/startHighFind/coming?kehuId=" + highFindVo.getKehuId() + "&pcUserId=" + highFindVo.getPcUserId()+ "&time=" + highFindVo.getTime());
    }
    @RequestMapping("h5pay")
    @ResponseBody
    public Message h5pay(HttpServletRequest req, BigDecimal userMoney) throws IOException, ServletException {
        System.out.println(userMoney+"================");
        if (userMoney != BigDecimal.valueOf(0)) {
            WechatUtil wechatUtil = new WechatUtil();
//            Map<String, String> prepayResult = wechatUtil.prepayResult( req.getRemoteAddr(), "地网数据",userMoney);
//            System.out.println(prepayResult.get("mweb_url"));
//            return Message.success(prepayResult.get("mweb_url").toString()); // 预支付数据转发到页面，调用js支付
        }
        return Message.fail("金额有误");
    }
    //*********************************扫码登录*****************************
    @RequestMapping("rqcode")
    @ResponseBody
    public PayVo rqcode(HttpServletRequest request, BigDecimal userMoney, HttpSession session) throws IOException, ServletException {
        UserPCVo userPCVo = (UserPCVo)session.getAttribute("userPCVo");
        PayVo payVo = new PayVo();
        String orderId = WXPayUtil.generateUUID();
        if (userMoney != BigDecimal.valueOf(0)) {
            JiFenVo jiFenVo = jiFenService.getJiFenInfo(Integer.parseInt(userMoney+""));
            PcUserConsumeVo pcUserConsumeVo = new PcUserConsumeVo();
            pcUserConsumeVo.setCreateTime(new Date());
            pcUserConsumeVo.setIp(request.getRemoteAddr());
            pcUserConsumeVo.setIsActive((byte)1);
            pcUserConsumeVo.setJifen(jiFenVo.getJifen());
            pcUserConsumeVo.setOrderId(orderId);
            pcUserConsumeVo.setPcUserId(userPCVo.getId());
            pcUserConsumeVo.setMoney(Integer.parseInt(userMoney+""));
            pcUserConsumeService.save(pcUserConsumeVo);
            WechatUtil wechatUtil = new WechatUtil();
            Map<String, String> prepayResult = wechatUtil.prepayResults( request.getRemoteAddr(), "地网数据",userMoney,orderId);
            String path = request.getSession().getServletContext().getRealPath("upload/pay/");
            Long fileName = System.currentTimeMillis();
            String saveImgFilePath = path+fileName+".png";
            File dir = new File(saveImgFilePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            ZxingKit.encode(prepayResult.get("code_url").toString(), BarcodeFormat.QR_CODE, 3, ErrorCorrectionLevel.H, "png", 200, 200,saveImgFilePath);
            System.out.println(saveImgFilePath);
            payVo.setResult("success");
            payVo.setOrderId(orderId);
            payVo.setImgUrl("/upload/pay/"+fileName+".png");
            return payVo; // 预支付数据转发到页面，调用js支付
        }else{
            payVo.setResult("fail");
            return payVo;
        }
    }
    @RequestMapping("result")
    public void result(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("***********************notify_url*************************");
        WechatUtil wechatUtil = new WechatUtil();
        Map<String, String> resultMap = wechatUtil.payResult(req);
        try {
            System.out.println(resultMap+"=====解析之后的数据======");
            String resultCode = resultMap.get("result_code");
            if (resultCode != null && resultCode.equals("SUCCESS")) {
                System.out.println("支付成功。。。。。。。。。。。。。。。。。");
                PcUserConsumeVo pcUserConsumeVo = new PcUserConsumeVo();
                pcUserConsumeVo.setOrderId(resultMap.get("out_trade_no"));
                pcUserConsumeVo.setIsActive((byte)0);
                pcUserConsumeService.updateStatusByOrderId(pcUserConsumeVo);
                JiFenVo jiFenVo = jiFenService.getJiFenInfo(Integer.parseInt(resultMap.get("total_fee").toString())/100);
                PcUserConsumeVo p = pcUserConsumeService.getInfo(pcUserConsumeVo);
                UserPCVo userPCVo = userPCService.getById(p.getPcUserId());
                userPCVo.setJifen(userPCVo.getJifen()+jiFenVo.getJifen()+jiFenVo.getZsJiFen());
                //修改积分
                userPCService.updateJiFen(userPCVo);
                userPCVo.setGiveJifen(Integer.parseInt(jiFenVo.getZsJiFen()+""));
                //修改赠送的积分
                userPCService.updateGive(userPCVo);
            }else{
                System.out.println("支付失败。。。。。。。。。。。。。。。。。");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        wechatUtil.responsePayNotify(resp);
    }
    public static void main(String arg[]){
        HttpSendUtil.get("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+WechatAPI.APP_ID+"&redirect_uri="+WechatAPI.REDIRECT_URL+"&response_type=code&scope=SCOPE&state=STATE#wechat_redirect");
    }
    @RequestMapping("paySuccess")
    @ResponseBody
    public Message paySuccess() throws ServletException, IOException {
        return Message.success("支付成功");
    }
    @RequestMapping("qrPaySuccess")
    public String qrPaySuccess() throws ServletException, IOException {
        return "/dwpcpage/qrpay";
    }
    //********************************************电商支付**********************************
    @RequestMapping("dsindex")
    public void dsindex(HttpServletRequest req, HttpServletResponse resp,HttpSession session) throws IOException, ServletException {
        id = Long.parseLong(req.getParameter("id"));
        resp.sendRedirect(WechatAPI.ACCESS_LOGIN_DIANSHANG_URL);
    }
    //回调地址
    @RequestMapping("dssrcpay")
    public ModelAndView dssrcpay(HttpServletRequest request) throws IOException, ServletException {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("session中的编号"+id+"===================================");
        DianShangVo dianShangVo = new DianShangVo();
        dianShangVo.setId(id);
        try {
            String code = request.getParameter("code");
            WechatUtil wechatUtil = new WechatUtil();
            //获取accessTocken
            String accesInfo = wechatUtil.authLogin(code);
            //解析tocken
            JSONObject accesTockenJSON = JSON.parseObject(accesInfo);
            String accesTocken = accesTockenJSON.get("access_token").toString();
            //获取到企业授权登录用户的基本信息
            String openIdInfo = wechatUtil.getUserInfo(accesTocken,accesTockenJSON.get("openid").toString());
            JSONObject object = JSON.parseObject(openIdInfo);
            dianShangVo.setOpenId(object.get("openid").toString());
            dianShangVo.setWxImg(object.get("headimgurl").toString());
            FixedPriceVo fixedPriceVo = fixedPriceService.getById(-1l);
            //需要支付的金额
            dianShangVo.setMoney(Float.parseFloat(fixedPriceVo.getPrice()/10+""));
            modelAndView.addObject("openid",object.get("openid").toString());
            dianShangService.update(dianShangVo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelAndView.setViewName("pc/dspay");
        return modelAndView;
    }
    @RequestMapping("dspay")
    public String dspay(HttpServletRequest req, HttpServletResponse resp,String openid,HttpSession session) throws IOException, ServletException {
        System.out.println("session中的编号"+id+"===================================");
        String uuid = WXPayUtil.generateUUID();
        FixedPriceVo fixedPriceVo = fixedPriceService.getById(-1l);
        DianShangVo dianShangVo = new DianShangVo();
        dianShangVo.setOrderItem(uuid);
        System.out.println(uuid+"=====================");
        BigDecimal money = BigDecimal.valueOf(fixedPriceVo.getPrice()/10);
        dianShangVo.setId(id);
        dianShangService.update(dianShangVo);
        if (money != BigDecimal.valueOf(0)) {
            WechatUtil wechatUtil = new WechatUtil();
            Map<String, String> prepayResult = wechatUtil.prepayDSResult(openid,req.getRemoteAddr(), "地网数据-电商认证支付",fixedPriceVo.getPrice(),uuid);
            // 正式付款需要提交的数据
            Map<String, String> payData = wechatUtil.payData(prepayResult);
            req.setAttribute("appId", WechatAPI.APP_ID);
            req.setAttribute("timeStamp", payData.get("timeStamp"));
            req.setAttribute("nonceStr", payData.get("nonceStr"));
            req.setAttribute("packages", payData.get("package"));
            req.setAttribute("paySign", payData.get("paySign"));
            req.setAttribute("userMoney", money);
        }
        return "pc/qrpay"; // 预支付数据转发到页面，调用js支付
    }
    @RequestMapping("dianshang_result")
    public void dianshang_result(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        System.out.println("***********************notify_url*************************");
        WechatUtil wechatUtil = new WechatUtil();
        DianShangVo dianShangVo= new DianShangVo();
        Map<String, String> resultMap = wechatUtil.payResult(req);
        System.out.println(resultMap+"=====解析电商公众号支付之后的数据======");
        String resultCode = resultMap.get("result_code");
        if (resultCode != null && resultCode.equals("SUCCESS")) {
            String orderId = resultMap.get("out_trade_no");
            dianShangVo= dianShangService.getInfoByOrderId(orderId);
            //修改状态
            dianShangService.updateStatus(new StatusQuery(dianShangVo.getId(),0));
        }else{
            System.out.println("公众号支付失败。。。。。。。。。。。。。。。。。");
        }
        resp.sendRedirect("http://192.168.3.18:8080/startHighFind/coming?kehuId=" + dianShangVo.getKehuId() + "&pcUserId=" + dianShangVo.getPcUserId()+ "&time=" + dianShangVo.getTime());
    }
}
