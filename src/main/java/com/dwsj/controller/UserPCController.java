package com.dwsj.controller;

import com.dwsj.common.Message;
import com.dwsj.common.StatusQuery;
import com.dwsj.common.UserAccountPasswordQuery;
import com.dwsj.service.KeHuService;
import com.dwsj.service.UserPCService;
import com.dwsj.service.VerifcodeService;
import com.dwsj.vo.UserPCVo;
import com.dwsj.vo.UserVo;
import com.dwsj.vo.Verifcode;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
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
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("pcUser")
public class UserPCController  {

    /**
     *
     */
    @Resource
    private UserPCService userPCService;
    @Resource
    private VerifcodeService verifcodeService;
    @Resource
    private KeHuService keHuService;
    @RequestMapping("loginPage")
    public String loginPage(){
        return "dwpcpage/loginPage";
    }
    @RequestMapping("getInfo")
    @ResponseBody
    public Message getInfo(String phone,String password,HttpSession session){
        try{
            UserPCVo userPCVo = userPCService.getByAccountPassword(new UserAccountPasswordQuery(phone, new Md5Hash(password).toString()));
            if(userPCVo==null){
                return  Message.fail("账号或密码输入有误");
            }
            if(userPCVo.getPhone().equals(phone) && userPCVo.getPassword().equals(new Md5Hash(password).toString())){
                session.setAttribute("userPCVo", userPCVo);
                return  Message.success("验证成功");
            }else{
                return  Message.fail("账号或密码输入有误");
            }
        }catch (Exception e){
            return  Message.fail("账号或密码输入有误");
        }
    }

    /**
     * 进入个人中心页面
     * @return
     */
    @RequestMapping("pcUserMain")
    public String pcUserMain(){
        return "dwpcpage/main";
    }
    @RequestMapping("homepage")
    public String homepage(){
        return "dwpcpage/main";
    }
    //个人中心
    @RequestMapping("personl")
    public String personl(){
        return "dwpcpage/personl";
    }
    //功能定价
    @RequestMapping("price")
    public String price(){
        return "dwpcpage/price";
    }
    //功能定价
    @RequestMapping("find")
    public String find(){
        return "dwpcpage/find";
    }
    //api服务
    @RequestMapping("api")
    public String api(){
        return "dwpcpage/api";
    }
    //负债查询
    @RequestMapping("info/{id}")
    public ModelAndView info(@PathVariable("id") Long id,HttpSession session) {
        UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
        if(userPCVo==null){
            return null;
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("dwpcpage/fuzhai");
        modelAndView.addObject("kehuVo",keHuService.getById(id));
        //修改上次查询时间
        keHuService.updateTime(id);
        return modelAndView;
    }
    //手机通讯录
    @RequestMapping("phone/{id}")
    public ModelAndView phone(@PathVariable("id") Long id,HttpSession session) {
        UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
        if(userPCVo==null){
            return null;
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("dwpcpage/phone");
        modelAndView.addObject("kehuVo",keHuService.getById(id));
        //修改上次查询时间
        return modelAndView;
    }
    //公安局
    @RequestMapping("police/{id}")
    public ModelAndView police(@PathVariable("id") Long id,HttpSession session) {
        UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
        if(userPCVo==null){
            return null;
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("dwpcpage/police");
        modelAndView.addObject("kehuVo",keHuService.getById(id));
        //修改上次查询时间
        return modelAndView;
    }
    //电商
    @RequestMapping("dianshang/{id}")
    public ModelAndView dianshang(@PathVariable("id") Long id,HttpSession session) {
        UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
        if(userPCVo==null){
            return null;
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("dwpcpage/dianshang");
        modelAndView.addObject("kehuVo",keHuService.getById(id));
        //修改上次查询时间
        return modelAndView;
    }
    //社保
    @RequestMapping("shebao/{id}")
    public ModelAndView shebao(@PathVariable("id") Long id,HttpSession session) {
        UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
        if(userPCVo==null){
            return null;
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("dwpcpage/shebao");
        modelAndView.addObject("kehuVo",keHuService.getById(id));
        //修改上次查询时间
        return modelAndView;
    }
    //文字影像
    @RequestMapping("font/{id}")
    public ModelAndView font(@PathVariable("id") Long id,HttpSession session) {
        UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
        if(userPCVo==null){
            return null;
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("dwpcpage/font");
        modelAndView.addObject("kehuVo",keHuService.getById(id));
        //修改上次查询时间
        return modelAndView;
    }
    @RequestMapping("refushInfo")
    @ResponseBody
    public UserPCVo refushInfo(HttpSession session){
        UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
        if(userPCVo==null){
            return null;
        }
        UserPCVo u = userPCService.getById(userPCVo.getId());
        session.setAttribute("userPCVo",u);
        return u;
    }
    @RequestMapping("register")
    @ResponseBody
    public Message register(Verifcode verifcode,HttpSession session){
        try{
            Verifcode v = verifcodeService.getVerifcode(verifcode.getMobile());
            if(v==null){
                return Message.fail("验证码已失效");
            }
            //判断验证码是否正确
            if(verifcode.getCode().equals(v.getCode())){
                //修改短信的状态
                verifcodeService.updateCodeStatus(new StatusQuery(1,verifcode.getMobile()));
            }else{
                return Message.fail("短信验证码输入错误");
            }
            int cnt = userPCService.checkLogin(verifcode.getMobile());
            if(cnt==0){
                UserPCVo userPCVo = new UserPCVo();
                userPCVo.setJifen(0l);
                userPCVo.setKehu(0);
                userPCVo.setPassword(new Md5Hash(verifcode.getPassword()).toString());
                userPCVo.setPhone(verifcode.getMobile());
                userPCVo.setIsActive((byte)0);
                userPCVo.setGiveJifen(0);
                userPCService.save(userPCVo);
                session.setAttribute("userPCVo",userPCService.getById(userPCVo.getId()));
                return Message.success("注册成功!");
            }else{
                //如果账号已存在则是进行修改密码
                userPCService.setPwd(verifcode.getMobile(),new Md5Hash(verifcode.getPassword()).toString());
                session.setAttribute("userPCVo",userPCService.findByPhone(verifcode.getMobile()));
                return Message.success("密码修改成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            return  Message.fail("服务器繁忙");
        }
    }
    @RequestMapping("setPwd")
    @ResponseBody
    public Message setPwd(Verifcode verifcode,HttpSession session){
        try{
            int cnt = userPCService.checkLogin(verifcode.getMobile());
            if(cnt!=0){
                Verifcode v = verifcodeService.getVerifcode(verifcode.getMobile());
                //判断验证码是否正确
                if(verifcode.getCode().equals(v.getCode())){
                    //修改短信的状态
                    verifcodeService.updateCodeStatus(new StatusQuery(1,verifcode.getMobile()));
                    //修改密码，
                    userPCService.setPwd(verifcode.getMobile(),new Md5Hash(verifcode.getPassword()).toString());
                    //获取到用户的基本信息，保存到session中
                    session.setAttribute("userPCVo",userPCService.findByPhone(verifcode.getMobile()));
                    return Message.success("注册成功!");
                }else{
                    return Message.fail("验证码输入错误!");
                }
            }else{
                return Message.fail("账号不存在!");
            }
        }catch (Exception e){
            e.printStackTrace();
            return  Message.fail("服务器繁忙");
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
    public String main(){
        return "main";
    }
    @RequestMapping("exit")
    public String exit(HttpSession session) {
        session.removeAttribute("userPCVo");
        return "dwpcpage/loginPage";
    }
    @RequestMapping("bossInfoPage")
    public String bossInfoPage() {
        return "user/bossInfoPage";
    }

    @RequestMapping("detailpage")
    public String detailpage() {

        return "pc/detailpage";
    }

    @RequestMapping("bossInfo")
    @ResponseBody
    public UserPCVo bossInfo(HttpSession session) {
        UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
        return userPCService.getById(userPCVo.getId());
    }
    @RequestMapping("updateBossInfo")
    @ResponseBody
    public Message updateBossInfo(UserPCVo userPCVo) {
        try{
            userPCService.update(userPCVo);
            return Message.success("资料修改成功！");
        }catch (Exception e){
            return Message.success("资料修改失败！");
        }
    }
    @RequestMapping("checkPwd")
    @ResponseBody
    public Map<String, Boolean> checkPwd(String password, HttpSession session) {
        UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
        Map<String, Boolean> result = new HashMap<String, Boolean>();
        try {
            Subject subject = SecurityUtils.getSubject();
            String pwd = userPCService.getPassword(userPCVo.getId());
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
            UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
            userPCService.updatePwd(userPCVo.getId(), new Md5Hash(newpassword).toString());
            return  Message.success("密码修改成功!");
        }catch (Exception e){
            return  Message.success("密码修改失败!");
        }
    }
    @RequestMapping("changePhone")
    public Message changePhone(HttpSession session, String phone) {
        try{
            UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
            userPCService.updatePhone(userPCVo.getId(), phone);
            return  Message.success("修改手机绑定成功!");
        }catch (Exception e){
            return  Message.success("修改手机绑定失败!");
        }
    }
    @RequestMapping("updateStatus/{id}/{status}")
    @ResponseBody
    public Message updateStatus(@PathVariable("id") long id, @PathVariable("status") int status) throws Exception {
        try {
            userPCService.updateStatus(new StatusQuery(id, status));
            return Message.success("ok");
        } catch (Exception e) {
            return Message.fail("fail");
        }
    }
    @RequestMapping("/deleteManyUser")
    @ResponseBody
    public Message deleteManycashSubject(@Param("manyId") String manyId) throws Exception {
        try {
            String str[] = manyId.split(",");
            for (String s : str) {
                userPCService.removeById(Long.parseLong(s));
            }
            return Message.success("删除成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return Message.fail("删除失败!");
        }
    }

    @RequestMapping("/deleteUser/{id}")
    @ResponseBody
    public Message deletecashSubject(@PathVariable("id") long id) throws Exception {
        try {
            userPCService.removeById(id);
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
