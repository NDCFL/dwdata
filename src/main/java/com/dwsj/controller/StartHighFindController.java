package com.dwsj.controller;

import com.dwsj.common.Message;
import com.dwsj.common.QRCodeUtil;
import com.dwsj.common.ZxingKit;
import com.dwsj.service.HighFindService;
import com.dwsj.vo.HighFindVo;
import com.dwsj.vo.UserPCVo;
import com.dwsj.vo.UserVo;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;

@RequestMapping("startHighFind")
@Controller
public class StartHighFindController {
    @Resource
    private HighFindService highFindService;

    @RequestMapping("getQrImg")
    @ResponseBody
    public Message getQrImg(HighFindVo highFindVo, HttpServletRequest request, HttpSession session) {
        try{
            UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            if(userPCVo==null && userVo==null){
                return Message.fail("请先登录");
            }
            highFindVo.setPcUserId(userPCVo.getId());
            //查询是否有过认证记录
            HighFindVo h = highFindService.getInfo(highFindVo);
            Long time = System.currentTimeMillis();
            String path = request.getSession().getServletContext().getRealPath("upload/qrimg/");
            String rootpath = request.getSession().getServletContext().getRealPath("upload/");
            String root_path = request.getSession().getServletContext().getRealPath("/");
            QRCodeUtil.encode("http://192.168.3.18:8080/startHighFind/coming?kehuId=" + highFindVo.getKehuId() + "&pcUserId=" + userPCVo.getId() + "&time=" + time, rootpath + "logo_module.png", path, time + "", true);
            if (h == null) {
                highFindVo.setQrImg("/upload/qrimg/" + time + ".jpg");
                highFindVo.setIsActive(1);
                highFindVo.setTime(time);
                highFindService.save(highFindVo);
                return Message.success("/upload/qrimg/" + time + ".jpg");
            } else {
                System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=");
                //如果能查询到此人的基本信息，则说明已经开始认证或者是重新认证
                File file = new File(root_path + h.getQrImg());
                //删除原来的二维码
                file.delete();
                System.out.println("-=-=-=-=-=-=-=dfsssss-=-=-=-=-=");
                HighFindVo highFindVo1 = new HighFindVo();
                highFindVo1.setTime(time);
                highFindVo1.setQrImg("/upload/qrimg/" + time + ".jpg");
                highFindVo1.setIsActive(1);
                highFindVo1.setMifangStatus(1);
                highFindVo1.setYoupingzhengStatus(1);
                highFindVo1.setJinjiedaoStatus(1);
                highFindVo1.setXiaocaiguanjiaStatus(1);
                highFindVo1.setId(h.getId());
                highFindService.update(highFindVo1);
                return Message.success("/upload/qrimg/" + time + ".jpg");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Message.fail("失败");
        }
    }

    @RequestMapping("coming")
    public ModelAndView coming(HighFindVo highFindVo, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        HighFindVo h = highFindService.getInfo(highFindVo);
        System.out.println("数据库取值："+h.getTime()+"=====地址栏传递参数===="+highFindVo.getTime());
        if (!h.getTime().equals(highFindVo.getTime())) {
            modelAndView.setViewName("dwmobilepage/error");
        } else {
            modelAndView.addObject("highFindVo", h);
            modelAndView.setViewName("dwmobilepage/index");
        }
        return modelAndView;
    }

    @RequestMapping("mifang")
    public String mifang() {
        return "dwmobilepage/mifang";
    }

    @RequestMapping("youpingzheng")
    public String youpingzheng() {
        return "dwmobilepage/youpingzheng";
    }

    @RequestMapping("jinjiedao")
    public String jinjiedao() {
        return "dwmobilepage/jinjiedao";
    }

    @RequestMapping("xiaocai")
    public String xiaocai() {
        return "dwmobilepage/xiaocai";
    }
}
