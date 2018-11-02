package com.dwsj.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.dwsj.common.Message;
import com.dwsj.common.SendMsg;
import com.dwsj.service.UserPCService;
import com.dwsj.vo.UserPCVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("test")
public class TestController {

    @Resource
    private UserPCService userPCService;
    @RequestMapping("test")
    @ResponseBody
    public Message test() {
        return Message.success("成功!");
    }

    @RequestMapping("send")
    @ResponseBody
    public Message send() throws Exception{
        List<UserPCVo> userPCVoList = userPCService.listAll();
        for(int i=0;i<userPCVoList.size();i++){
            UserPCVo userPCVo = userPCVoList.get(i);
            SendSmsResponse response = SendMsg.sendSms1(userPCVo.getPhone());
            System.out.println("短信接口返回的数据----------------"+userPCVo.getPhone());
            System.out.println("Message=" + response.getMessage());
        }
        return Message.success("成功!");
    }
    public static void main1(String arg[]) {
        int year = Integer.parseInt("360730199712200617".substring(6, 10));
        Calendar date = Calendar.getInstance();
        int currentyear = date.get(Calendar.YEAR);
        System.out.println(currentyear-year+"==="+currentyear);
    }
    public static void main(String arg[]){

        String cnt = null;
        while (cnt==null){
            cnt = "afas";
        }
        System.out.println(cnt);
    }
}
