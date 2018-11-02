package com.dwsj.controller;

import com.dwsj.common.Message;
import com.dwsj.common.PageQuery;
import com.dwsj.common.PagingBean;
import com.dwsj.common.StatusQuery;
import com.dwsj.enums.ActiveStatusEnum;
import com.dwsj.service.APIUserItemService;
import com.dwsj.vo.APIUserItemVo;
import com.dwsj.vo.UserPCVo;
import com.dwsj.vo.UserVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
@RequestMapping("apiUserItem")
public class APIUserItemController {

    @Resource
    private APIUserItemService apiUserItemService;
    @RequestMapping("apiUserItemList")
    @ResponseBody
    public PagingBean apiUserItemList(int pageSize, int pageIndex, String searchVal, HttpSession session) throws  Exception{
        try{
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            if(userVo==null){
                return null;
            }
            PagingBean pagingBean = new PagingBean();
            pagingBean.setPageSize(pageSize);
            pagingBean.setCurrentPage(pageIndex);
            PageQuery pageQuery = new PageQuery();
            pageQuery.setSearchVal(searchVal);
            pageQuery.setPageNo(pagingBean.getStartIndex());
            pageQuery.setPageSize(pagingBean.getPageSize());
            pagingBean.setTotal(apiUserItemService.count(pageQuery));
            pagingBean.setrows(apiUserItemService.listPage(pageQuery));
            return pagingBean;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping("/apiUserItemAddSave")
    @ResponseBody
    public Message addSaveapiUserItem(APIUserItemVo apiUserItem, HttpSession session) throws  Exception {
        try{
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            if(userVo==null){
                return Message.fail("请先登录");
            }
            apiUserItem.setIsActive(ActiveStatusEnum.ACTIVE.getValue().byteValue());
            apiUserItemService.save(apiUserItem);
            return  Message.success("新增成功!");
        }catch (Exception E){
            return Message.fail("新增失败!");
        }

    }
    @RequestMapping("/findAPIUserItem/{id}")
    @ResponseBody
    public APIUserItemVo findapiUserItem(@PathVariable("id") long id,HttpSession session){
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        if(userVo==null){
            return null;
        }
        APIUserItemVo apiUserItem = apiUserItemService.getById(id);
        return apiUserItem;
    }
    @RequestMapping("/apiUserItemUpdateSave")
    @ResponseBody
    public Message updateapiUserItem(APIUserItemVo apiUserItem,HttpSession session) throws  Exception{
        try{
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            if(userVo==null){
                return Message.fail("请先登录");
            }
            apiUserItemService.update(apiUserItem);
            return  Message.success("修改成功!");
        }catch (Exception e){
            e.printStackTrace();
            return Message.fail("修改失败!");
        }
    }
    @RequestMapping("/deleteManyAPIUserItem")
    @ResponseBody
    public Message deleteManyapiUserItem(@Param("manyId") String manyId,Integer status,HttpSession session) throws  Exception{
        try{
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            if(userVo==null){
                return Message.fail("请先登录");
            }
            String str[] = manyId.split(",");
            for (String s: str) {
                apiUserItemService.updateStatus(new StatusQuery(Long.parseLong(s),status));
            }
            return Message.success("批量修改状态成功!");
        }catch (Exception e){
            e.printStackTrace();
            return  Message.fail("批量修改状态失败!");
        }
    }
    @RequestMapping("/deleteAPIUserItem/{id}")
    @ResponseBody
    public Message deleteapiUserItem(@PathVariable("id") long id,HttpSession session) throws  Exception{
        try{
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            if(userVo==null){
                return Message.fail("请先登录");
            }
            apiUserItemService.removeById(id);
            return Message.success("删除成功!");
        }catch (Exception e){
            e.printStackTrace();
            return Message.fail("删除失败!");
        }
    }
    @RequestMapping("/apiUserItemPage")
    public String table(HttpSession session) throws  Exception{
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        if(userVo==null){
            return null;
        }
        return "api/apiUserItemList";
    }
    @RequestMapping("updateStatus/{id}/{status}")
    @ResponseBody
    public Message updateStatus(@PathVariable("id") long id,@PathVariable("status") int status,HttpSession session) throws  Exception{
        try{
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            if(userVo==null){
                return Message.fail("请先登录");
            }
            apiUserItemService.updateStatus(new StatusQuery(id,status));
            return Message.success("ok");
        }catch (Exception e){
            return  Message.fail("fail");
        }
    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}