package com.dwsj.controller;

import com.dwsj.service.KeHuService;
import com.dwsj.vo.KeHuVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("pc")
public class PcController {
    @Resource
    private KeHuService keHuService;
    @RequestMapping("personl")
    public String personal(){
        return  "pc/personl";
    }
    @RequestMapping("pcUserMain")
    public String pcUserMain(){
        return "pc/homepage";
    }
    @RequestMapping("homepage")
    public String homepage(){
        return "pc/homepage";
    }
    @RequestMapping("exit")
    public String exit(HttpSession session) {
        session.invalidate();
        return "pc/loginPage";
    }
    @RequestMapping("detailpage/{id}")
    public ModelAndView detailpage(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pc/detailpage");
        modelAndView.addObject("kehuid",id);
        //修改上次查询时间
        keHuService.updateTime(id);
        return modelAndView;
    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
