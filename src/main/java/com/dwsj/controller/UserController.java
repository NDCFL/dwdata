package com.dwsj.controller;

import com.dwsj.common.Message;
import com.dwsj.common.StatusQuery;
import com.dwsj.common.UserAccountPasswordQuery;
import com.dwsj.service.UserService;
import com.dwsj.vo.Select2Vo;
import com.dwsj.vo.UserPCVo;
import com.dwsj.vo.UserRoleVo;
import com.dwsj.vo.UserVo;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController  {

    /**
     *
     */
    @Resource
    private UserService userService;
    @RequestMapping("loginPage")
    public String loginPage(){
        return  "login/loginPage";
    }
    @RequestMapping("getInfo")
    @ResponseBody
    public Message getInfo(String phone,String password){
        try{
            UserVo userVo = userService.getByAccountPassword(new UserAccountPasswordQuery(phone, new Md5Hash(password).toString()));
            if((userVo.getPhone().equals(phone) || userVo.getRealname().equals(phone) || userVo.getName().equals(phone) ) && userVo.getPassword().equals(new Md5Hash(password).toString())){
                Subject subject = SecurityUtils.getSubject();
                subject.login(new UsernamePasswordToken(phone, new Md5Hash(password).toString()));
                Session session = subject.getSession();
                session.setAttribute("userVo", userVo);
                return  Message.success("验证成功");
            }else{
                return  Message.fail("账号或密码输入有误,或已被禁用");
            }
        }catch (Exception e){
            e.printStackTrace();
            return  Message.fail("账号或密码输入有误,或已被禁用");
        }
    }
    @ResponseBody
    @RequestMapping("getImgCode/{code}")
    public Message getImgCode(@PathVariable("code") String code, HttpSession session){
        String realCode = (String) session.getAttribute("rand");
        if(!code.equals(realCode)){
            return  Message.fail("验证码输入错误");
        }else{
            return  Message.success("验证码输入成功");
        }
    }
    @RequestMapping("main")
    public String main(HttpSession session){
        UserRoleVo userRoleVo = (UserRoleVo) session.getAttribute("userRole");
        if(userRoleVo.getRoleVo().getTitle().equals("合作商")){
            return "user/updateBaseInfo";
        }else{
            return "main";
        }
    }
    @RequestMapping("exit")
    public String exit(HttpSession session) {
        session.invalidate();
        return "login/loginPage";
    }
    @RequestMapping("bossInfoPage")
    public String bossInfoPage() {
        return "user/bossInfoPage";
    }
    @RequestMapping("bossInfo")
    @ResponseBody
    public UserVo bossInfo(HttpSession session) {
        UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        if(userPCVo==null && userVo==null){
            return null;
        }
        return userService.getById(userVo.getId());
    }
    @RequestMapping("getUserIdAndName")
    @ResponseBody
    public List<Select2Vo> getUserIdAndName(HttpSession session) {
        UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        if(userPCVo==null && userVo==null){
            return null;
        }
        return userService.getUserIdAndName();
    }
    @RequestMapping("updateBossInfo")
    @ResponseBody
    public Message updateBossInfo(UserVo userVo,HttpSession session ) {
        try{
            UserVo userVo1 = (UserVo) session.getAttribute("userVo");
            if(userVo1==null){
                return Message.fail("请先登录");
            }
            userService.update(userVo);
            return Message.success("资料修改成功！");
        }catch (Exception e){
            return Message.success("资料修改失败！");
        }
    }
    @RequestMapping("checkPwd")
    @ResponseBody
    public Map<String, Boolean> checkPwd(String password, HttpSession session) {
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        if( userVo==null){
            return null;
        }
        Map<String, Boolean> result = new HashMap<String, Boolean>();
        try {
            Subject subject = SecurityUtils.getSubject();
            String pwd = userService.getPassword(userVo.getId());
            if (pwd.equals(new Md5Hash(password).toString())) {
                result.put("valid", true);
            } else {
                result.put("valid", false);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.put("valid", false);
            return result;
        }
    }

    @RequestMapping("updatePassword")
    @ResponseBody
    public Message updatePassword(HttpSession session, String newpassword) {
        try{
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            if(userVo==null){
                return Message.fail("请先登录");
            }
            userService.updatePwd(userVo.getId(), new Md5Hash(newpassword).toString());
            return  Message.success("密码修改成功!");
        }catch (Exception e){
            return  Message.success("密码修改失败!");
        }
    }
    @RequestMapping("changePhone")
    public Message changePhone(HttpSession session, String phone) {
        try{
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            if(userVo==null){
                return Message.fail("请先登录");
            }
            userService.updatePhone(userVo.getId(), phone);
            return  Message.success("修改手机绑定成功!");
        }catch (Exception e){
            return  Message.success("修改手机绑定失败!");
        }
    }
    @RequestMapping("updateStatus/{id}/{status}")
    @ResponseBody
    public Message updateStatus(@PathVariable("id") long id, @PathVariable("status") int status,HttpSession session) throws Exception {
        try {
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            if(userVo==null){
                return Message.fail("请先登录");
            }
            userService.updateStatus(new StatusQuery(id, status));
            return Message.success("ok");
        } catch (Exception e) {
            return Message.fail("fail");
        }
    }
    @RequestMapping("/deleteManyUser")
    @ResponseBody
    public Message deleteManycashSubject(@Param("manyId") String manyId,HttpSession session) throws Exception {
        try {
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            if(userVo==null){
                return Message.fail("请先登录");
            }
            String str[] = manyId.split(",");
            for (String s : str) {
                userService.removeById(Long.parseLong(s));
            }
            return Message.success("删除成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return Message.fail("删除失败!");
        }
    }

    @RequestMapping("/deleteUser/{id}")
    @ResponseBody
    public Message deletecashSubject(@PathVariable("id") long id,HttpSession session) throws Exception {
        try {
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            if(userVo==null){
                return Message.fail("请先登录");
            }
            userService.removeById(id);
            return Message.success("删除成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return Message.fail("删除失败!");
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
