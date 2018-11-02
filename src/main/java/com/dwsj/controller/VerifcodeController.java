package com.dwsj.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.dwsj.common.GetClientIpUtil;
import com.dwsj.common.Message;
import com.dwsj.common.SendMsg;
import com.dwsj.common.StatusQuery;
import com.dwsj.service.UserPCService;
import com.dwsj.service.VerifcodeService;
import com.dwsj.vo.Verifcode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Random;

@Controller
@RequestMapping("code")
public class VerifcodeController {

    @Resource
    private VerifcodeService verifcodeService;
    @Resource
    private UserPCService userPCService;
    /**
     * 发送手机短信验证码
     * @param verifcode
     * @return
     */
    @RequestMapping("sendCode")
    @ResponseBody
    public Message addCode(Verifcode verifcode, HttpServletRequest request){
        try{
            //查询改手机号在有效期5分钟之内是否还有未使用的短信，如果有则返回code如果没有则返回-1
            System.out.println(verifcode.getMobile());
            String dbCode = verifcodeService.queryByCode(verifcode.getMobile());
            verifcode.setIp(GetClientIpUtil.getIp(request));
            Integer cnts = verifcodeService.cnt(verifcode.getMobile(),verifcode.getIp());
            int cnt = userPCService.checkLogin(verifcode.getMobile());
            if(cnts>=10){
                return Message.fail("今天操作过于频繁");
            }
            if(dbCode==null || dbCode.equals("")){
                //生成4位数的验证码
                int code = new Random().nextInt(8888)+1000;
                verifcode.setCode(code+"");
                verifcode.setMsg("【地网数据】你的验证码是："+code);
                SendSmsResponse response = SendMsg.sendSms(verifcode);
                System.out.println(response.getMessage());
                verifcodeService.save(verifcode);
                return  Message.success("短信发送成功!");
            }else{
                //发送数据库原来就有的验证码dbcode
                //模拟接收验证码
                Verifcode verifcode1 = verifcodeService.getVerifcode(verifcode.getMobile());
                System.out.println(dbCode+"====来自于数据库的验证码====>>>");
                SendSmsResponse response = SendMsg.sendSms(verifcode1);
                System.out.println(response.getMessage());
                return  Message.success("短信发送成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            return  Message.fail("短信发送失败!");
        }
    }

    /**
     * 验证手机验证码是否输入正确
     * @param verifcode
     * @return
     *
     */
    @RequestMapping("validateCode")
    @ResponseBody
    public Message validateCode(Verifcode verifcode){
        try{
            //查询改手机号在有效期5分钟之内是否还有未使用的短信，如果有则返回code如果没有则返回-1
            String dbCode = verifcodeService.queryByCode(verifcode.getMobile());
            System.out.println(verifcode.getCode()+"==========>>>");
            if(!dbCode.equals(verifcode.getCode())){
                return  Message.fail("验证码输入错误!");
            }else{
                System.out.println("==================");
                verifcodeService.updateCodeStatus(new StatusQuery(1,verifcode.getMobile()));
                return  Message.success("验证码输入正确!");
            }
        }catch (Exception e){
            e.printStackTrace();
            return  Message.success("验证码已失效!");
        }
    }
    @RequestMapping("updateCodeStatus")
    @ResponseBody
    public Message updateCodeStatus(Verifcode verifcode){
        try{
            //状态为1代表使用过的或者已经作废的，0代表的是未使用过
            verifcodeService.updateCodeStatus(new StatusQuery(1,verifcode.getMobile()));
            return  Message.success("验证码校验成功!");
        }catch (Exception e){
            e.printStackTrace();
            return  Message.success("验证码校验失败!");
        }
    }
}
