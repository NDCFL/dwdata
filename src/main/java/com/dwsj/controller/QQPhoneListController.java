package com.dwsj.controller;

import com.dwsj.common.Message;
import com.dwsj.common.PageQuery;
import com.dwsj.common.PagingBean;
import com.dwsj.common.StatusQuery;
import com.dwsj.enums.ActiveStatusEnum;
import com.dwsj.service.KeHuService;
import com.dwsj.service.QQPhoneListService;
import com.dwsj.service.QQPhoneService;
import com.dwsj.vo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by chenfeilong on 2017/10/21.
 */
@Controller
@RequestMapping("qqPhoneList")
public class QQPhoneListController {

    @Resource
    private QQPhoneListService qqPhoneListService;
    @Resource
    private KeHuService keHuService;
    @Resource
    private QQPhoneService qqPhoneService;
    @RequestMapping("qqPhoneListList")
    @ResponseBody
    public PagingBean qqPhoneListList(int pageSize, int pageIndex, String searchVal,Long id,HttpSession session) throws  Exception{
        try{
            UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            if(userPCVo==null && userVo==null){
                return null;
            }
            PagingBean pagingBean = new PagingBean();
            pagingBean.setPageSize(pageSize);
            pagingBean.setCurrentPage(pageIndex);
            PageQuery pageQuery = new PageQuery();
            pageQuery.setId(id);
            pageQuery.setSearchVal(searchVal);
            pageQuery.setPageNo(pagingBean.getStartIndex());
            pageQuery.setPageSize(pagingBean.getPageSize());
            pagingBean.setTotal(qqPhoneListService.count(pageQuery));
            pagingBean.setrows(qqPhoneListService.listPage(pageQuery));
            return pagingBean;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping("/qqPhoneListAddSave")
    @ResponseBody
    public Message addSaveqqPhoneList(QQPhoneListVo qqPhoneList, HttpSession session) throws  Exception {
        try{
            UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            if(userPCVo==null && userVo==null){
                return Message.fail("请先登录");
            }
            qqPhoneListService.save(qqPhoneList);
            return  Message.success("新增成功!");
        }catch (Exception E){
            return Message.fail("新增失败!");
        }

    }
    @RequestMapping("/findQQPhoneList/{id}")
    @ResponseBody
    public QQPhoneListVo findqqPhoneList(@PathVariable("id") long id,HttpSession session){
        UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        if(userPCVo==null && userVo==null){
            return null;
        }
        QQPhoneListVo qqPhoneList = qqPhoneListService.getById(id);
        return qqPhoneList;
    }
    @RequestMapping("/qqPhoneListUpdateSave")
    @ResponseBody
    public Message updateqqPhoneList(QQPhoneListVo qqPhoneList,HttpSession session) throws  Exception{
        try{
            UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            if(userPCVo==null && userVo==null){
                return Message.fail("请先登录");
            }
            qqPhoneListService.update(qqPhoneList);
            return  Message.success("修改成功!");
        }catch (Exception e){
            return Message.fail("修改失败!");
        }
    }
    @RequestMapping("/deleteManyQQPhoneList")
    @ResponseBody
    public Message deleteManyqqPhoneList(@Param("manyId") String manyId,Integer status,HttpSession session) throws  Exception{
        try{
            UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            if(userPCVo==null && userVo==null){
                return Message.fail("请先登录");
            }
            String str[] = manyId.split(",");
            for (String s: str) {
                qqPhoneListService.updateStatus(new StatusQuery(Long.parseLong(s),status));
            }
            return Message.success("批量修改状态成功!");
        }catch (Exception e){
            e.printStackTrace();
            return  Message.fail("批量修改状态失败!");
        }
    }
    @RequestMapping("/deleteQQPhoneList/{id}")
    @ResponseBody
    public Message deleteqqPhoneList(@PathVariable("id") long id,HttpSession session) throws  Exception{
        try{
            UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            if(userPCVo==null && userVo==null){
                return Message.fail("请先登录");
            }
            qqPhoneListService.removeById(id);
            return Message.success("删除成功!");
        }catch (Exception e){
            e.printStackTrace();
            return Message.fail("删除失败!");
        }
    }
    @RequestMapping("/qqPhoneListPage")
    public String table(HttpSession session) throws  Exception{
        UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        if(userPCVo==null && userVo==null){
            return null;
        }
        return "jifen/qqPhoneListList";
    }
    @RequestMapping("updateStatus/{id}/{status}")
    @ResponseBody
    public Message updateStatus(@PathVariable("id") long id,@PathVariable("status") int status,HttpSession session) throws  Exception{
        try{
            UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            if(userPCVo==null && userVo==null){
                return Message.fail("请先登录");
            }
            qqPhoneListService.updateStatus(new StatusQuery(id,status));
            return Message.success("ok");
        }catch (Exception e){
            return  Message.fail("fail");
        }
    }
    @RequestMapping("/getListInfo/{id}/{qqphoneid}")
    public ModelAndView getListInfo(@PathVariable("id") long id,@PathVariable("qqphoneid") long qqphoneid) throws  Exception{
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("dwpcpage/qqPhoneList");
        try{
            KeHuVo keHuVo = keHuService.getById(id);
            QQPhoneVo qqPhoneVo = qqPhoneService.getById(qqphoneid);
            modelAndView.addObject("qqPhoneVo",qqPhoneVo);
            modelAndView.addObject("kehuVo",keHuVo);
            return modelAndView;
        }catch (Exception e){
            e.printStackTrace();
            return modelAndView;
        }
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}