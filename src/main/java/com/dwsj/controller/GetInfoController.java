package com.dwsj.controller;

import com.dwsj.common.Message;
import com.dwsj.common.YongXunUtil;
import com.dwsj.vo.KeHuVo;
import com.dwsj.yongxun.ZhengXin98Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@RequestMapping("getInfo")
public class GetInfoController {
    private static final String IDCARD_KEY = "011121803291300413N7T1G6A0L7D";
    private static final String IDCARD_CODE = "e2454aa17ae66728840587394e9f9057";
    @ResponseBody
    @RequestMapping("login")
    public Message login(String account,String password){
        if(account.equals("55104964") && password.equals("drnew")){
            return  Message.success("登录成功");
        }else{
            return Message.fail("无效账号");
        }
    }
    @ResponseBody
    @RequestMapping("getPhoneList")
    public Message getPhoneList(String name,String card){
        String result = null;
        try {
            result = ZhengXin98Util.get("https://vip.98zhengxin.com/pre/prevention?name="+URLEncoder.encode(name,"utf-8")+"&idcard="+card,IDCARD_KEY,IDCARD_CODE);
            return  Message.success(result);
        } catch (Exception e) {
            e.printStackTrace();
            return Message.fail("查询失败");
        }
    }
}
