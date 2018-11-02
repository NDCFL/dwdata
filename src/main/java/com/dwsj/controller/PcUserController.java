package com.dwsj.controller;

import com.dwsj.common.Message;
import com.dwsj.common.PageQuery;
import com.dwsj.common.PagingBean;
import com.dwsj.common.StatusQuery;
import com.dwsj.enums.ActiveStatusEnum;
import com.dwsj.service.PcUserGiveItemService;
import com.dwsj.service.UserPCService;
import com.dwsj.vo.PcUserGiveItemVo;
import com.dwsj.vo.UserPCVo;
import com.dwsj.vo.UserVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
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

@Controller
@RequestMapping("userPC")
public class PcUserController {
    @Resource
    private UserPCService userPCService;
    @Resource
    private PcUserGiveItemService pcUserGiveItemService;
    @RequestMapping("userPCList")
    @ResponseBody
    public PagingBean userPCList(int pageSize, int pageIndex, String searchVal, HttpSession session) throws  Exception{
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
            pageQuery.setSearchVal(searchVal);
            pageQuery.setPageNo(pagingBean.getStartIndex());
            pageQuery.setPageSize(pagingBean.getPageSize());
            pagingBean.setTotal(userPCService.count(pageQuery));
            pagingBean.setrows(userPCService.listPage(pageQuery));
            return pagingBean;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping("/userPCAddSave")
    @ResponseBody
    public Message addSaveuserPC(UserPCVo userPC, HttpSession session) throws  Exception {
        try{
            UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            if(userPCVo==null && userVo==null){
                return Message.fail("请先登录");
            }
            userPC.setIsActive(ActiveStatusEnum.ACTIVE.getValue().byteValue());
            userPCService.save(userPC);
            return  Message.success("新增成功!");
        }catch (Exception E){
            return Message.fail("新增失败!");
        }

    }
    @RequestMapping("/findUserPC/{id}")
    @ResponseBody
    public UserPCVo finduserPC(@PathVariable("id") long id,HttpSession session){
        UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        if(userPCVo==null && userVo==null){
            return null;
        }
        UserPCVo userPC = userPCService.getById(id);
        return userPC;
    }
    @RequestMapping("/userPCUpdateSave")
    @ResponseBody
    public Message updateuserPC(UserPCVo userPC,HttpSession session) throws  Exception{
        try{
            UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            if(userPCVo==null && userVo==null){
                return null;
            }
            userPCService.updateJiFen(userPC);
            return  Message.success("修改成功!");
        }catch (Exception e){
            return Message.fail("修改失败!");
        }
    }
    @RequestMapping("/userPCUpdateGive")
    @ResponseBody
    public Message userPCUpdateGive(UserPCVo userPC,HttpSession session) throws  Exception{
        try{
            UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            if(userPCVo==null && userVo==null){
                return Message.fail("请先登录");
            }
            if(userPC.getGiveJifen()==null){
                userPC.setGiveJifen(0);
            }
            //新增赠送记录
            PcUserGiveItemVo pcUserGiveItemVo = new PcUserGiveItemVo();
            pcUserGiveItemVo.setJifen(userPC.getGiveJifen());
            pcUserGiveItemVo.setPcUserId(userPC.getId());
            pcUserGiveItemVo.setType(0);
            pcUserGiveItemVo.setIsActive((byte)0);
            pcUserGiveItemVo.setCreateTime(new Date());
            pcUserGiveItemService.save(pcUserGiveItemVo);
            userPCService.updateGive(userPC);
            return  Message.success("赠送成功");
        }catch (Exception e){
            return Message.fail("赠送失败");
        }
    }
    @RequestMapping("/deleteManyUserPC")
    @ResponseBody
    public Message deleteManyuserPC(@Param("manyId") String manyId, Integer status,HttpSession session) throws  Exception{
        try{
            UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            if(userPCVo==null && userVo==null){
                return Message.fail("请先登录");
            }
            String str[] = manyId.split(",");
            for (String s: str) {
                userPCService.updateStatus(new StatusQuery(Long.parseLong(s),status));
            }
            return Message.success("批量修改状态成功!");
        }catch (Exception e){
            e.printStackTrace();
            return  Message.fail("批量修改状态失败!");
        }
    }
    @RequestMapping("/deleteUserPC/{id}")
    @ResponseBody
    public Message deleteuserPC(@PathVariable("id") long id,HttpSession session) throws  Exception{
        try{
            UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            if(userPCVo==null && userVo==null){
                return null;
            }
            userPCService.removeById(id);
            return Message.success("删除成功!");
        }catch (Exception e){
            e.printStackTrace();
            return Message.fail("删除失败!");
        }
    }
    @RequestMapping("/userPCPage")
    public String table(HttpSession session) throws  Exception{
        UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        if(userPCVo==null && userVo==null){
            return "user/loginPage";
        }
        return "user/userPCList";
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
            userPCService.updateStatus(new StatusQuery(id,status));
            return Message.success("ok");
        }catch (Exception e){
            return  Message.fail("fail");
        }
    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
