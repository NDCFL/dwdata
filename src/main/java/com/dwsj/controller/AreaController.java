package com.dwsj.controller;

import com.dwsj.common.Message;
import com.dwsj.common.PageQuery;
import com.dwsj.common.PagingBean;
import com.dwsj.common.StatusQuery;
import com.dwsj.enums.ActiveStatusEnum;
import com.dwsj.service.AreaService;
import com.dwsj.vo.AreaVo;
import com.dwsj.vo.Select2Vo;
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
@RequestMapping("area")
public class AreaController {

    @Resource
    private AreaService areaService;
    @RequestMapping("areaList")
    @ResponseBody
    public PagingBean areaList(int pageSize, int pageIndex, String searchVal, HttpSession session) throws  Exception{
        try{
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            PagingBean pagingBean = new PagingBean();
            pagingBean.setPageSize(pageSize);
            pagingBean.setCurrentPage(pageIndex);
            PageQuery pageQuery = new PageQuery();
            pageQuery.setSearchVal(searchVal);
            pageQuery.setPageNo(pagingBean.getStartIndex());
            pageQuery.setPageSize(pagingBean.getPageSize());
            pagingBean.setTotal(areaService.count(pageQuery));
            pagingBean.setrows(areaService.listPage(pageQuery));
            return pagingBean;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping("/areaAddSave")
    @ResponseBody
    public Message addSavearea(AreaVo area, HttpSession session) throws  Exception {
        try{
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            areaService.save(area);
            return  Message.success("新增成功!");
        }catch (Exception E){
            return Message.fail("新增失败!");
        }

    }
    @RequestMapping("/findArea/{id}")
    @ResponseBody
    public AreaVo findarea(@PathVariable("id") long id){
        AreaVo area = areaService.getById(id);
        return area;
    }
    @RequestMapping("/areaUpdateSave")
    @ResponseBody
    public Message updatearea(AreaVo area) throws  Exception{
        try{
            areaService.update(area);
            return  Message.success("修改成功!");
        }catch (Exception e){
            return Message.fail("修改失败!");
        }
    }
    @RequestMapping("/getArea")
    @ResponseBody
    public List<Select2Vo> getArea() throws  Exception{
       return areaService.getArea();
    }
    @RequestMapping("/deleteManyArea")
    @ResponseBody
    public Message deleteManyarea(@Param("manyId") String manyId,Integer status) throws  Exception{
        try{
            String str[] = manyId.split(",");
            for (String s: str) {
                areaService.updateStatus(new StatusQuery(Long.parseLong(s),status));
            }
            return Message.success("批量修改状态成功!");
        }catch (Exception e){
            e.printStackTrace();
            return  Message.fail("批量修改状态失败!");
        }
    }
    @RequestMapping("/deleteArea/{id}")
    @ResponseBody
    public Message deletearea(@PathVariable("id") long id) throws  Exception{
        try{
            areaService.removeById(id);
            return Message.success("删除成功!");
        }catch (Exception e){
            e.printStackTrace();
            return Message.fail("删除失败!");
        }
    }
    @RequestMapping("/areaPage")
    public String table() throws  Exception{
        return "jifen/areaList";
    }
    @RequestMapping("updateStatus/{id}/{status}")
    @ResponseBody
    public Message updateStatus(@PathVariable("id") long id,@PathVariable("status") int status) throws  Exception{
        try{
            areaService.updateStatus(new StatusQuery(id,status));
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