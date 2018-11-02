package com.dwsj.controller;

import com.dwsj.common.*;
import com.dwsj.service.*;
import com.dwsj.util.DateUtil;
import com.dwsj.vo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("keHu")
public class KeHuController {

    @Resource
    private KeHuService keHuService;
    @Resource
    private AreaService areaService;
    @Resource
    private UserPCService userPCService;
    @Resource
    private ConsumeHistoryService consumeHistoryService;
    @Resource
    private FixedPriceService fixedPriceService;
    @RequestMapping("/getData")
    @ResponseBody
    public KeHuVo getData(KeHuVo keHuVo,HttpSession session) throws Exception{
        UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        if(userPCVo==null && userVo==null){
            return null;
        }
        return keHuService.getKeHuInfo(keHuVo);
    }
    @RequestMapping("keHuList")
    @ResponseBody
    public PagingBean keHuList(int pageSize, int pageIndex, KeHuVo keHuVo, HttpSession session,String serchVal) throws  Exception{
        UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
        System.out.println(userPCVo);
        if(userPCVo==null){
            return null;
        }else{
            try{
                PagingBean pagingBean = new PagingBean();
                pagingBean.setPageSize(pageSize);
                pagingBean.setCurrentPage(pageIndex);
                PageQuery pageQuery = new PageQuery();
                pageQuery.setPageNo(pagingBean.getStartIndex());
                pageQuery.setPageSize(pagingBean.getPageSize());
                pageQuery.setSearchVal(serchVal);
                keHuVo.setPcUserId(userPCVo.getId());
                if(keHuVo.getIds()!=null || !keHuVo.equals("")){
                    keHuVo.setCommodityIds(keHuVo.getIds().split(","));
                }
                pagingBean.setTotal(keHuService.counts(pageQuery,keHuVo));
                pagingBean.setrows(keHuService.listPages(pageQuery,keHuVo));
                return pagingBean;
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }
    }
    @RequestMapping("/updatePhone")
    @ResponseBody
    public Message updatePhone(String phone,Long id,HttpSession session) throws Exception{
        UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        if(userPCVo==null && userVo==null){
            return null;
        }
        try{
            keHuService.updatePhone(phone,id);
            session.setAttribute("userPCVo",userPCService.getById(id));
            return Message.success("修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return Message.fail("修改失败");
        }
    }
    @RequestMapping("/keHuAddSave")
    @ResponseBody
    public Message addSavekeHu(KeHuVo keHu, HttpSession session, HttpServletRequest request) throws  Exception {
        try{
            UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            if(userPCVo==null && userVo==null){
                return Message.fail("请先登录");
            }
            String projectPath = request.getSession().getServletContext().getRealPath("/");
            keHu.setPcUserId(userPCVo.getId());
            //首先检测是否查询过该用户
            KeHuVo kh = keHuService.checkKeHu(keHu);
            int cnt = keHuService.cnt(keHu);
            //扣除相应的积分
            FixedPriceVo fixedPriceVo = fixedPriceService.getById(1l);
            Integer jifen = fixedPriceVo.getPrice();
            if(userPCVo.getJifen()-jifen<0){
                return Message.fail("积分不足，请充值");
            }
            if(kh!=null){
                if(cnt>0){
                    return Message.success("客户已存在");
                }
                KeHuVo k = keHuService.getById(kh.getId());
                k.setPcUserId(userPCVo.getId());
                k.setCreateTime(new Date());
                k.setKehuType(-1);
                k.setUpTime(new Date());
                keHuService.save(k);
                userPCService.addKeHu(userPCVo);
            }else{
                KeHuVo kh1 = keHuService.getKeHuInfo(keHu);
                //获取区号
                String code = keHu.getKehuIdcard().substring(0,6);
                //解析出生日
                String birthday = keHu.getKehuIdcard().substring(6, 10)+"-"+keHu.getKehuIdcard().substring(10, 12)+"-"+keHu.getKehuIdcard().substring(12, 14);
                //通过身份证解析出地址
                AreaVo areaVo = areaService.getAreaInfo(code);
                //设置地区
                keHu.setKehuAddress(areaVo==null?"未知户籍地":areaVo.getName());
                //设置用户的生日
                keHu.setKehuBirthday(DateUtil.parse(birthday, "yyyy-MM-dd"));
                //设置性别
                int sex = Integer.parseInt(keHu.getKehuIdcard().substring(16,17))%2;
                //计算客户的性别
                keHu.setKehuSex(sex==0?0:1);
                keHu.setKehuType(-1);
                //如果y用户名称和身份证号都能匹配上则直接从数据库中拿照片，否则请求接口获取
                if(kh1==null){
                    String headImg = GetPhotoUtil.imgUpload(keHu.getKehuName(),keHu.getKehuIdcard(),request);
                    if(headImg.equals("0") || headImg.equals("1")){
                        //设置默认图片
                        headImg = "/upload/pcuser/default.png";
                        keHu.setKehuHeadImg(headImg);
                    }else{
                        keHu.setKehuHeadImg(headImg);
                    }
                }else{
                    keHu.setKehuHeadImg(kh1.getKehuHeadImg());
                }
                //如果keHu为空则用户不存在，没有查询过，否则为已经查询过一次
                //根据姓名身份证号获取相片
                //把上次查询的时间设置为当前时间
                keHu.setUpTime(new Date());
                keHu.setRemark("未添加备注");
                userPCService.addKeHu(userPCVo);
                keHuService.save(keHu);
            }
            ConsumeHistoryVo consumeHistoryVo = new ConsumeHistoryVo();
            consumeHistoryVo.setAmount(jifen);
            consumeHistoryVo.setPcUserId(userPCVo.getId());
            consumeHistoryVo.setRemark(fixedPriceVo.getName());
            consumeHistoryVo.setCreateTime(new Date());
            consumeHistoryVo.setBcr(keHu.getKehuName());
            consumeHistoryVo.setIsActive((byte)0);
            //添加消费记录
            consumeHistoryService.save(consumeHistoryVo);
            userPCVo.setJifen(userPCVo.getJifen()-jifen);
            //扣除积分
            userPCService.updateJiFen(userPCVo);
            return  Message.success("新增成功");
        }catch (Exception E){
            E.printStackTrace();
            return Message.fail("新增失败");
        }
    }
    @RequestMapping("/findKeHu/{id}")
    @ResponseBody
    public KeHuVo findkeHu(@PathVariable("id") long id,HttpSession session){
        UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        if(userPCVo==null && userVo==null){
            return null;
        }
        KeHuVo keHu = keHuService.getById(id);
        return keHu;
    }
    @RequestMapping("/keHuUpdateSave")
    @ResponseBody
    public Message updatekeHu(KeHuVo keHu,HttpSession session) throws  Exception{
        try{
            UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            if(userPCVo==null && userVo==null){
                return Message.fail("请先登录");
            }
            keHuService.update(keHu);
            return  Message.success("修改成功!");
        }catch (Exception e){
            return Message.fail("修改失败!");
        }
    }
    @RequestMapping("/deleteManyKeHu")
    @ResponseBody
    public Message deleteManykeHu(@Param("manyId") String manyId, Integer status,HttpSession session) throws  Exception{
        try{
            UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            if(userPCVo==null && userVo==null){
                return Message.fail("请先登录");
            }
            String str[] = manyId.split(",");
            for (String s: str) {
                keHuService.updateStatus(new StatusQuery(Long.parseLong(s),status));
            }
            return Message.success("批量修改状态成功!");
        }catch (Exception e){
            e.printStackTrace();
            return  Message.fail("批量修改状态失败!");
        }
    }
    @RequestMapping("/deleteKeHu/{id}")
    @ResponseBody
    public Message deletekeHu(@PathVariable("id") long id,HttpSession session) throws  Exception{
        try{
            UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            if(userPCVo==null && userVo==null){
                return Message.fail("请先登录");
            }
            keHuService.removeById(id);
            return Message.success("删除成功!");
        }catch (Exception e){
            e.printStackTrace();
            return Message.fail("删除失败!");
        }
    }
    @RequestMapping("/keHuPage")
    public String table(HttpSession session) throws  Exception{
        UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        if(userPCVo==null && userVo==null){
            return "user/loginPage";
        }
        return "jifen/keHuList";
    }
    @RequestMapping("/keHuCount")
    @ResponseBody
    public CountVo keHuCount(HttpSession session){
        UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        if(userPCVo==null && userVo==null){
            return null;
        }
        return keHuService.getCount(userPCVo.getId());
    }
    @RequestMapping("updateStatus")
    @ResponseBody
    public Message updateStatus(long id,int status,Integer isactive,HttpSession session) throws  Exception{
        UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        if(userPCVo==null && userVo==null){
            return Message.fail("请先登录");
        }
//        0：特别关注用户，1：已合作用户，2：未合作用户',
        try{
            KeHuVo keHuVo = keHuService.getById(id);
            //isactive如果为0的话，则说明是进行关注
            if(isactive==0){
                if(keHuVo.getKehuType()==0 && status==1){
                    //如果该客户原本就是特别关注用户，并且现在要改成合作客户，则直接改状态为3
                    status = 3;
                }else if(keHuVo.getKehuType()==1 && status==0){
                    //如果该客户原本就是已合作用户，并且现在要改成特别关注用户，则直接改状态为3
                    status = 3;
                }
            }else{
                //则是取消关注
                if(keHuVo.getKehuType()==3 && status==1){
                    //如果该客户原本就是特别关注用户，并且现在要改成合作客户，则直接改状态为3
                    status = 0;
                }else if(keHuVo.getKehuType()==3 && status==0){
                    //如果该客户原本就是已合作用户，并且现在要改成特别关注用户，则直接改状态为3
                    status = 1;
                }else{
                    status =2;
                }
            }
            keHuService.updateStatus(new StatusQuery(id,status));
            return Message.success("ok");
        }catch (Exception e){
            e.printStackTrace();
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
