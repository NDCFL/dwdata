package com.dwsj.controller;

import com.dwsj.common.Message;
import com.dwsj.common.QRCodeUtil;
import com.dwsj.service.DianShangService;
import com.dwsj.vo.DianShangVo;
import com.dwsj.vo.UserPCVo;
import com.dwsj.vo.UserVo;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RequestMapping("startDianShang")
@Controller
public class StartDianShangController {
    @Resource
    private DianShangService dianShangService;

    @RequestMapping("getQrImg")
    @ResponseBody
    public Message getQrImg(DianShangVo dianShangVo, HttpServletRequest request, HttpSession session) {
        try{
            UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            if(userPCVo==null && userVo==null){
                return Message.fail("请先登录");
            }
            dianShangVo.setPcUserId(userPCVo.getId());
            //查询是否有过认证记录
//            DianShangVo h = dianShangService.getInfo(dianShangVo);
            Long time = System.currentTimeMillis();
            String path = request.getSession().getServletContext().getRealPath("upload/tb/");
            String rootpath = request.getSession().getServletContext().getRealPath("upload/");
            String root_path = request.getSession().getServletContext().getRealPath("/");
            QRCodeUtil.encode("http://192.168.3.18:8080/startDianShang/coming?kehuId=" + dianShangVo.getKehuId() + "&pcUserId=" + userPCVo.getId() + "&time=" + time, rootpath + "logo_module.png", path, time + "", true);
            dianShangVo.setQrImg("/upload/tb/" + time + ".jpg");
            dianShangVo.setIsActive((byte)1);
            dianShangVo.setTime(time);
            dianShangVo.setFind("淘宝/京东/支付宝");
            dianShangService.save(dianShangVo);
            return Message.success("/upload/tb/" + time + ".jpg");
//            if (h == null) {
//                dianShangVo.setQrImg("/upload/tb/" + time + ".jpg");
//                dianShangVo.setIsActive((byte)1);
//                dianShangVo.setTime(time);
//                dianShangVo.setFind("淘宝/京东/支付宝");
//                dianShangService.save(dianShangVo);
//                return Message.success("/upload/tb/" + time + ".jpg");
//            } else {
//                System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=");
//                //如果能查询到此人的基本信息，则说明已经开始认证或者是重新认证
//                File file = new File(root_path + h.getQrImg());
//                //删除原来的二维码
//                file.delete();
//                System.out.println("-=-=-=-=-=-=-=dfsssss-=-=-=-=-=");
//                DianShangVo dianShangVo1 = new DianShangVo();
//                dianShangVo1.setTime(time);
//                dianShangVo1.setQrImg("/upload/tb/" + time + ".jpg");
//                dianShangVo1.setIsActive((byte)1);
//                dianShangVo1.setTbStatus((byte)1);
//                dianShangVo1.setJdStatus((byte)1);
//                dianShangVo1.setZfbStatus((byte)1);
//                dianShangVo1.setId(h.getId());
//                dianShangService.update(dianShangVo1);
//                return Message.success("/upload/tb/" + time + ".jpg");
//            }
        }catch (Exception e){
            e.printStackTrace();
            return Message.fail("失败");
        }
    }

    @RequestMapping("coming")
    public ModelAndView coming(DianShangVo dianShangVo, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        DianShangVo h = dianShangService.getInfo(dianShangVo);
        System.out.println("数据库取值："+h.getTime()+"=====地址栏传递参数===="+dianShangVo.getTime());
        if (!h.getTime().equals(dianShangVo.getTime())) {
            modelAndView.setViewName("dwmobilepage/error");
        } else {
            modelAndView.addObject("dianShangVo", h);
            modelAndView.setViewName("dwmobilepage/main");
        }
        return modelAndView;
    }

    @RequestMapping("/info/{kehuId}/{name}/{time}")
    public ModelAndView info(HttpSession session, @PathVariable("kehuId") Long kehuId,@PathVariable("name") String name,@PathVariable("time") Long time) throws  Exception{
        ModelAndView modelAndView = new ModelAndView();
        UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        if(userPCVo==null && userVo==null){
            modelAndView.setViewName("user/loginPage");
        }else{
            modelAndView.setViewName("dwpcpage/"+name);
        }
        DianShangVo dianShangVo = new DianShangVo();
        dianShangVo.setPcUserId(userPCVo.getId());
        dianShangVo.setKehuId(kehuId);
        dianShangVo.setTime(time);
        modelAndView.addObject("dianShangVo",dianShangService.getInfo(dianShangVo));
        return modelAndView;
    }
    @RequestMapping("tb")
    public String tb() {
        return "dwmobilepage/tb";
    }
    @RequestMapping("jd")
    public String jd() {
        return "dwmobilepage/jd";
    }
    @RequestMapping("zfb")
    public String zfb() {
        return "dwmobilepage/zfb";
    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
