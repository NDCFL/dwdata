package com.dwsj.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dwsj.common.GetAddressUtil;
import com.dwsj.common.GetClientIpUtil;
import com.dwsj.common.Message;
import com.dwsj.crawlUtil.MiFangUtil;
import com.dwsj.crawlUtil.XiaoCaiGuanJiaUtil;
import com.dwsj.service.*;
import com.dwsj.vo.HighFindVo;
import com.dwsj.yongxun.GetMiFangDataUtil;
import jdk.nashorn.internal.scripts.JO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@RequestMapping("highSearch")
public class HighSearchController {
    @Resource
    private ReplaceableCookieService replaceableCookieService;
    @Resource
    private UserPCService userPCService;
    @Resource
    private FixedPriceService fixedPriceService;
    @Resource
    private ConsumeHistoryService consumeHistoryService;
    @Resource
    private HighFindService highFindService;
    @RequestMapping("updateBase")
    @ResponseBody
    public Message updateBase(String device, HttpServletRequest request,Long id){
        HighFindVo highFindVo = new HighFindVo();
        highFindVo.setIp(GetClientIpUtil.getIp(request));
        highFindVo.setAddress(GetAddressUtil.getAdress(request));
        highFindVo.setDevice(device);
        highFindVo.setId(id);
        highFindService.update(highFindVo);
        return  Message.success("成功");
    }
    //米房验证================================================
    @RequestMapping("getMiFangPhone")
    @ResponseBody
    public Message getMiFangPhone(String phone,long id) throws Exception {
        String result = GetMiFangDataUtil.getMiFangTask(phone);
        System.out.println(result+"==============");
        if(result == null){
            return Message.fail("短信发送失败");
        }else{
            JSONObject jsonObject = JSON.parseObject(result);
            HighFindVo highFindVo = new HighFindVo();
            highFindVo.setMifangTaskId(jsonObject.get("taskId").toString());
            highFindVo.setMifangPhone(phone);
            highFindVo.setId(id);
            String code = jsonObject.get("code").toString();
            System.out.println(code+"-=-=-=-=-=-=");
            if(code.equals("0001") || code.equals("0002")){
                highFindService.update(highFindVo);
                return Message.success(result);
            }else{
                highFindVo.setMifangStatus(2);
                highFindService.update(highFindVo);
                return Message.fail(jsonObject.get("msg").toString()+"或未注册");
            }

        }
    }
    @RequestMapping("checkMiFangSms")
    @ResponseBody
    public Message checkMiFangSms(String taskId,String code,Long id) throws Exception {
        HighFindVo highFindVo = new HighFindVo();
        highFindVo.setId(id);
        highFindVo.setMifangTaskId(taskId);
        String result = GetMiFangDataUtil.checkMiFangSms(taskId,code);
        JSONObject data = JSON.parseObject(result);
        if(!data.get("code").toString().equals("0011")){
            return Message.fail("验证码验证失败");
        }else{
            //跟新米房数据，拉取报告
            //认证成功
            highFindVo.setMifangStatus(0);
            String datas = null;
            System.out.println(datas+"==========-=-=-=-=-=-=-=-=-===================");
            while (datas==null){
                //休眠一秒钟请求
                Thread.sleep(5000);
                System.out.println("请求请求=-=-=-=-==-=-=-=");
                datas = GetMiFangDataUtil.getMiFangData(taskId);
            }
            if(datas!=null){
                highFindVo.setMifangData(datas);
            }
            System.out.println(datas);
            JSONObject jsonObject = JSON.parseObject(datas);
            if(jsonObject.get("code").toString().equals("0000")){
                highFindService.update(highFindVo);
                return Message.success("认证成功");
            }else{
                return Message.fail(jsonObject.get("msg").toString());
            }
        }
    }
    @RequestMapping("mifangSuccess")
    @ResponseBody
    public String mifangSuccess(String taskId) throws Exception {
        System.out.println(taskId+"=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        return "success";
    }
    //有凭证==========================================
    //米房验证
    @RequestMapping("getYouPingZhengPhone")
    @ResponseBody
    public Message getYouPingZhengPhone(String phone,long id,String basePass) throws Exception {
        String result = GetMiFangDataUtil.getYouPingZhengTask(phone,basePass);
        System.out.println(result+"==============");
        if(result == null){
            return Message.fail("短信发送失败");
        }else{
            JSONObject jsonObject = JSON.parseObject(result);
            HighFindVo highFindVo = new HighFindVo();
            highFindVo.setYoupingzhengTaskId(jsonObject.get("taskId").toString());
            highFindVo.setYoupingzhengPhone(phone);
            highFindVo.setId(id);
            highFindVo.setYoupingzhengPassword(basePass);
            String code = jsonObject.get("code").toString();
            System.out.println(code+"==================");
            if(code.equals("0001") || code.equals("0002")){
                highFindService.update(highFindVo);
                return Message.success(result);
            }else{
                highFindVo.setYoupingzhengStatus(2);
                highFindService.update(highFindVo);
                return Message.fail(jsonObject.get("msg").toString()+"或未注册");
            }
        }
    }
    @RequestMapping("checkYouPingZhengSms")
    @ResponseBody
    public Message checkYouPingZhengSms(String taskId,String code,Long id) throws Exception {
        HighFindVo highFindVo = new HighFindVo();
        highFindVo.setId(id);
        highFindVo.setYoupingzhengTaskId(taskId);
        String result = GetMiFangDataUtil.checkYouPingZhengSms(taskId,code);
        if(result==null){
            return Message.success("信息有误");
        }
        JSONObject data = JSON.parseObject(result);
        if(!data.get("code").toString().equals("0011")){
            return Message.fail("验证码验证失败");
        }else{
            //跟新米房数据，拉取报告
            //认证成功
            highFindVo.setYoupingzhengStatus(0);
            String datas = null;
            while (datas==null){
                //休眠一秒钟请求
                Thread.sleep(5000);
                System.out.println("请求请求=-=-=-=-==-=-=-=");
                datas =GetMiFangDataUtil.getYouPingZhengData(taskId);
                System.out.println(datas+"-=-=-=获取的数据-=-=");
            }
            if(datas!=null){
                highFindVo.setYoupingzhengData(datas);
            }
            JSONObject jsonObject = JSON.parseObject(datas);
            if(jsonObject.get("code").toString().equals("0000")){
                highFindService.update(highFindVo);
                return Message.success("认证成功");
            }else{
                return Message.fail(jsonObject.get("msg").toString());
            }
        }
    }
    //今借到==========================================
    @RequestMapping("getJinJieDaoPhone")
    @ResponseBody
    public Message getJinJieDaoPhone(String phone,long id,String basePass) throws Exception {
        String result = GetMiFangDataUtil.getJinJieDaoTask(phone,basePass);
        System.out.println(result+"==============");
        if(result == null){
            return Message.fail("短信发送失败");
        }else{
            JSONObject jsonObject = JSON.parseObject(result);
            HighFindVo highFindVo = new HighFindVo();
            highFindVo.setJinjiedaoTaskId(jsonObject.get("taskId").toString());
            highFindVo.setJinjiedaoPhone(phone);
            highFindVo.setId(id);
            highFindVo.setJinjiedaoPassword(basePass);
            String code = jsonObject.get("code").toString();
            if(code.equals("0100")){
                highFindVo.setJinjiedaoStatus(0);
                String datas = null;
                while (datas==null){
                    //休眠一秒钟请求
                    Thread.sleep(5000);
                    System.out.println("请求请求=-=-=-=-==-=-=-="+jsonObject.get("taskId").toString());
                    datas =GetMiFangDataUtil.getJinJieDaoData(jsonObject.get("taskId").toString());
                }
                JSONObject jsonObject1 = JSON.parseObject(datas);
                if(jsonObject1.get("code").toString().equals("0000")){
                    highFindVo.setJinjiedaoData(datas);
                    highFindVo.setJinjiedaoPassword(basePass);
                    //修改米房的信息
                    highFindService.update(highFindVo);
                    return Message.success("认证成功");
                }else{
                    //修改米房的信息
                    return Message.fail(jsonObject1.get("msg").toString());
                }
            }else{
                return Message.fail(jsonObject.get("msg").toString());
            }

        }
    }
    //小才管家==========================================
    @RequestMapping("getXiaoCaiGuanJiaPhone")
    @ResponseBody
    public Message getXiaoCaiGuanJiaPhone(String phone,long id,String bassPass) throws Exception {
        String result = XiaoCaiGuanJiaUtil.getXcgjListData(phone,bassPass);
        JSONObject jsonObject = JSON.parseObject(result);
        String status = jsonObject.get("code").toString();
        if(!status.equals("200")){
            return Message.fail(jsonObject.get("msg").toString());
        }else{
            String result1 = XiaoCaiGuanJiaUtil.getXcgjData(phone,bassPass);
            JSONObject jsonObject1 = JSON.parseObject(result1);
            String status1 = jsonObject1.get("code").toString();
            if(!status1.equals("200")){
                return Message.fail(jsonObject1.get("msg").toString());
            }else{
                HighFindVo highFindVo = new HighFindVo();
                highFindVo.setXiaocaiguanjiaData(result);
                highFindVo.setXiaocaiguanjiaBaseData(result1);
                highFindVo.setXiaocaiguanjiaPassword(bassPass);
                highFindVo.setXiaocaiguanjiaPhone(phone);
                highFindVo.setXiaocaiguanjiaStatus(0);
                highFindVo.setId(id);
                highFindService.update(highFindVo);
                return Message.success("认证成功");
            }
        }
    }
}
