package com.dwsj.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dwsj.common.Message;
import com.dwsj.common.YongXunUtil;
import com.dwsj.service.*;
import com.dwsj.vo.ConsumeHistoryVo;
import com.dwsj.vo.FixedPriceVo;
import com.dwsj.vo.KeHuVo;
import com.dwsj.vo.UserPCVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;

@Controller
@RequestMapping("yongxun")
public class YongXunController {
    @Resource
    private FixedPriceService fixedPriceService;
    @Resource
    private UserPCService userPCService;
    @Resource
    private ConsumeHistoryService consumeHistoryService;
    @Resource
    private KeHuService keHuService;
    @ResponseBody
    @RequestMapping("getFenXianPingGu")
    public Message getFenXianPingGu(KeHuVo keHuVo, HttpSession session,Integer handle){
        UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
        String result = null;
        try {
            if(handle==1){
                KeHuVo keHuVo1 = keHuService.getById(keHuVo.getId());
                return Message.success(keHuVo1.getXiaodai());
            }
            FixedPriceVo fixedPriceVo = fixedPriceService.getById(4l);
            Integer jifen = fixedPriceVo.getPrice();
            if(userPCVo.getJifen()-jifen<0){
                return Message.fail("积分不足，请充值");
            }
            KeHuVo keHuVo1 = keHuService.getById(keHuVo.getId());
            Calendar cal = Calendar.getInstance();
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(keHuVo1.getXiaodaitime());
            System.out.println(cal1.get(cal.DATE)+"========="+cal.get(cal.DATE));
            //如果更新时间是今天，则今天不允许在查询
            if(cal1.get(cal.DATE)!=cal.get(cal.DATE)){
                result = YongXunUtil.httpURLConnectionPOST("https://api.yongxunzhengxin.com/risk/v1/riskEvaluation","name="+ URLEncoder.encode(keHuVo.getKehuName(),"utf-8")+"&identityNo="+keHuVo.getKehuIdcard()+"&mobile="+keHuVo.getKehuPhone());
                JSONObject jsonObject = JSON.parseObject(result);
                String status = jsonObject.get("code").toString();
                if(status.equals("0000")){
                    keHuService.updateXiaoDai(keHuVo.getId(),result);
                    //新增消费记录
                    ConsumeHistoryVo consumeHistoryVo = new ConsumeHistoryVo();
                    consumeHistoryVo.setBcr(keHuVo.getKehuName());
                    consumeHistoryVo.setCreateTime(new Date());
                    consumeHistoryVo.setRemark("网贷、小贷、分期、多手机号");
                    consumeHistoryVo.setPcUserId(userPCVo.getId());
                    consumeHistoryVo.setAmount(fixedPriceVo.getPrice());
                    consumeHistoryVo.setIsActive((byte)0);
                    consumeHistoryService.save(consumeHistoryVo);
                    //修改积分
                    UserPCVo userPCVo1 = userPCService.getById(userPCVo.getId());
                    userPCVo.setJifen(userPCVo.getJifen()-fixedPriceVo.getPrice());
                    userPCService.updateJiFen(userPCVo);
                    return  Message.success(result);
                }else{
                    System.out.println(jsonObject.get("msg").toString());
                    return Message.fail("查询失败");
                }
            }else{
                return Message.success(keHuVo1.getXiaodai());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return  Message.fail("查询失败");
        }
    }
}
