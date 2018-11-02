package com.dwsj.yongxun;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import sun.misc.BASE64Encoder;

public class GetMiFangDataUtil {
    //米房操作========================
    //获取任务受理,同时发送短信验证码
    public static String getMiFangTask(String phone) throws Exception{
        String result = YongXunUtil.httpURLConnectionPOST("https://api.yongxunzhengxin.com/mifang/v1/task","username="+phone);
        System.out.println(result+"-=-=获取任务-=-");
        JSONObject jsonObject = JSON.parseObject(result);
        String status = jsonObject.get("code").toString();
        Thread.sleep(3000);
        //如果状态是0010则说明是受理成功
        if(status.equals("0010")){
            String getCode = YongXunUtil.httpURLConectionGET("https://api.yongxunzhengxin.com/mifang/v1/task/"+jsonObject.get("taskId").toString()+"/status");
            return getCode;
        }else{
            return null;
        }
    }
    //验证码用户的短信验证码是否输入正确
    public static String checkMiFangSms(String taskId,String code) {
        String result = YongXunUtil.httpURLConnectionPOST("https://api.yongxunzhengxin.com/mifang/v1/task/"+taskId+"/input","input="+code);
        JSONObject jsonObject = JSON.parseObject(result);
        String status = jsonObject.get("code").toString();
        return result;
    }
    //拉取报告数据
    public static String getMiFangData(String taskId) {
        String result = YongXunUtil.httpURLConectionGET("https://api.yongxunzhengxin.com/mifang/v1/task/"+taskId+"/data");
        JSONObject jsonObject = JSON.parseObject(result);
        String status = jsonObject.get("code").toString();
        if(status.equals("0001")){
            return null;
        }
        //如果状态是0010则说明是受理成功
        return result;
    }
    //有凭证操作=================================================
    //获取任务受理,同时发送短信验证码
    public static String getYouPingZhengTask(String phone,String basePassWord) throws Exception{
        String result = YongXunUtil.httpURLConnectionPOST("https://api.yongxunzhengxin.com/ypz/v1/task","username="+phone+"&password="+new BASE64Encoder().encode(basePassWord.getBytes()));
        System.out.println(result+"-=-=获取任务-=-");
        JSONObject jsonObject = JSON.parseObject(result);
        String status = jsonObject.get("code").toString();
        Thread.sleep(3000);
        //如果状态是0010则说明是受理成功
        if(status.equals("0010")){
            String getCode = YongXunUtil.httpURLConectionGET("https://api.yongxunzhengxin.com/ypz/v1/task/"+jsonObject.get("taskId").toString()+"/status");
            return getCode;
        }else{
            return null;
        }
    }
    //验证码用户的短信验证码是否输入正确
    public static String checkYouPingZhengSms(String taskId,String code) {
        String result = YongXunUtil.httpURLConnectionPOST("https://api.yongxunzhengxin.com/ypz/v1/task/"+taskId+"/input","input="+code);
        JSONObject jsonObject = JSON.parseObject(result);
        String status = jsonObject.get("code").toString();
        if(status.equals("0001")){
            return null;
        }
        return result;
    }
    //拉取报告数据
    public static String getYouPingZhengData(String taskId) {
        String result = YongXunUtil.httpURLConectionGET("https://api.yongxunzhengxin.com/ypz/v1/task/"+taskId+"/data");
        JSONObject jsonObject = JSON.parseObject(result);
        String status = jsonObject.get("code").toString();
        if(status.equals("0001")){
            return null;
        }
        return result;
    }
    //今借到==========================
    //获取任务受理,同时发送短信验证码
    public static String getJinJieDaoTask(String phone,String basePassWord) throws Exception{
        String result = YongXunUtil.httpURLConnectionPOST("https://api.yongxunzhengxin.com/jinjiedao/v1/task","username="+phone+"&password="+new BASE64Encoder().encode(basePassWord.getBytes()));
        JSONObject jsonObject = JSON.parseObject(result);
        String status = jsonObject.get("code").toString();
        Thread.sleep(3000);
        //如果状态是0010则说明是受理成功
        if(status.equals("0010")){
            String getCode = YongXunUtil.httpURLConectionGET("https://api.yongxunzhengxin.com/jinjiedao/v1/task/"+jsonObject.get("taskId").toString()+"/status");
            return getCode;
        }else{
            return null;
        }
    }
    //验证码用户的短信验证码是否输入正确
    public static String checkJinJieDaoSms(String taskId,String code) {
        String result = YongXunUtil.httpURLConnectionPOST("https://api.yongxunzhengxin.com/jinjiedao/v1/task/"+taskId+"/input","input="+code);
        JSONObject jsonObject = JSON.parseObject(result);
        String status = jsonObject.get("code").toString();
        //如果状态是0010则说明是受理成功
        if(status.equals("0011")){
            return result;
        }else{
            return null;
        }
    }
    //拉取报告数据
    public static String getJinJieDaoData(String taskId) {
        String result = YongXunUtil.httpURLConectionGET("https://api.yongxunzhengxin.com/jinjiedao/v1/task/"+taskId+"/data");
        JSONObject jsonObject = JSON.parseObject(result);
        String status = jsonObject.get("code").toString();
        //如果状态是0010则说明是受理成功
        if(status.equals("0001")){
            return null;
        }
        return result;
    }
}
