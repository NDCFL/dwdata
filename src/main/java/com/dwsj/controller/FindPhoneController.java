package com.dwsj.controller;

import com.alibaba.fastjson.JSONObject;
import com.dwsj.common.*;
import com.dwsj.service.ConsumeHistoryService;
import com.dwsj.service.FindPhoneService;
import com.dwsj.service.FixedPriceService;
import com.dwsj.service.UserPCService;
import com.dwsj.vo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chenfeilong on 2017/10/21.
 */
@Controller
@RequestMapping("phone")
public class FindPhoneController {
    private static final String PHONE_KEY = "00351805291807267K6E5J6R4T0P";
    private static final String PHONE_CODE = "68489950b04fcd3fbb2be06a9ecee6b1";
    @Resource
    private FindPhoneService phoneService;
    @Resource
    private UserPCService userPCService;
    @Resource
    private FixedPriceService fixedPriceService;
    @Resource
    private ConsumeHistoryService consumeHistoryService;
    @RequestMapping("phoneList")
    @ResponseBody
    public PagingBean phoneList(int pageSize, int pageIndex, String searchVal,Long kehuId, HttpSession session) throws  Exception{
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
            pageQuery.setId(userPCVo.getId());
            pageQuery.setKehuId(kehuId);
            pageQuery.setPageNo(pagingBean.getStartIndex());
            pageQuery.setPageSize(pagingBean.getPageSize());
            pagingBean.setTotal(phoneService.count(pageQuery));
            pagingBean.setrows(phoneService.listPage(pageQuery));
            return pagingBean;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping("/phoneAddSave")
    @ResponseBody
    public Message addSavephone(FindPhoneVo phone, HttpSession session, HttpServletRequest request) throws  Exception {
        try{
            //先判断用户余额是否够用
            UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            if(userPCVo==null && userVo==null){
                return Message.fail("请先登录");
            }
            phone.setPcUserId(userPCVo.getId());
            //判断是否余额
            FixedPriceVo fixedPriceVo = fixedPriceService.getById(10l);
            System.out.println(userPCVo.getJifen()-fixedPriceVo.getPrice());
            if(userPCVo.getJifen()-fixedPriceVo.getPrice()<0){
                System.out.println("asdfasdfasd");
                return Message.fail("积分不足，请充值");
            }
            ConsumeHistoryVo consumeHistoryVo = new ConsumeHistoryVo();
            consumeHistoryVo.setPcUserId(userPCVo.getId());
            consumeHistoryVo.setAmount(fixedPriceVo.getPrice());
            consumeHistoryVo.setCreateTime(new Date());
            consumeHistoryVo.setBcr(phone.getBcr());
            consumeHistoryVo.setIsActive((byte)0);
            consumeHistoryVo.setRemark("手机号认证-"+fixedPriceVo.getPrice()+"积分");
            //新增消费记录
            consumeHistoryService.save(consumeHistoryVo);
            FindPhoneVo f = phoneService.getFindPhoneInfo(phone);
            if(fixedPriceVo.getPrice()!=null){
                //用总积分减去查询所需要的积分
                userPCVo.setJifen(userPCVo.getJifen()-fixedPriceVo.getPrice());
                //扣除余额
                userPCService.updateJiFen(userPCVo);
                Integer cnt = phoneService.getCnt(f);
                if(f==null) {
                    String result = IdCardUtil.get("http://vip.98zhengxin.com/mobile/mobilesimple1?name=" + URLEncoder.encode(phone.getBcr(), "utf-8") + "&mobile=" + phone.getBcrPhone(), PHONE_KEY, PHONE_CODE);
                    JSONObject jsonObject = JSONObject.parseObject(result);
                    System.out.println("http://vip.98zhengxin.com/mobile/mobilesimple1?name=" + URLEncoder.encode(phone.getBcr(), "utf-8") + "&mobile=" + phone.getBcrPhone());
                    JSONObject jsonObject1 = null;
                    try{
                        jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
                    }catch (Exception e){
                        phone.setData("不一致");
                        phone.setIsActive((byte) 1);
                        phoneService.save(phone);
                        return Message.fail(phone.getData());
                    }
                    System.out.println(jsonObject1.get("status"));
                    String status = jsonObject1.get("status").toString();
                    if (status.equals("1")) {
                        phone.setData("一致");
                        phone.setIsActive((byte) 0);
                        phoneService.save(phone);
                        return Message.success(phone.getData());
                    } else if (status.equals("2")) {
                        phone.setData("不一致");
                        phone.setIsActive((byte) 1);
                        phoneService.save(phone);
                        return Message.fail(phone.getData());
                    } else {
                        phone.setData("查无记录");
                        phone.setIsActive((byte) 1);
                        phoneService.save(phone);
                        return Message.fail(phone.getData());
                    }
                }else{
                    if(cnt>0){
                        f.setCreateTime(new Date());
                        phoneService.save(f);
                    }else{
                        f.setPcUserId(userPCVo.getId());
                        f.setCreateTime(new Date());
                        phoneService.save(f);
                    }
                    return Message.success(f.getData());
                }
            }else{
                return  Message.fail("积分不足");
            }
        }catch (Exception E){
            E.printStackTrace();
            return Message.fail("新增失败!");
        }
    }
    @RequestMapping("/findFindPhone/{id}")
    @ResponseBody
    public FindPhoneVo findphone(@PathVariable("id") long id,HttpSession session){
        UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        if(userPCVo==null && userVo==null){
            return null;
        }
        FindPhoneVo phone = phoneService.getById(id);
        return phone;
    }
    @RequestMapping("/phoneUpdateSave")
    @ResponseBody
    public Message updatephone(FindPhoneVo phone,HttpSession session) throws  Exception{
        try{
            UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            if(userPCVo==null && userVo==null){
                return null;
            }
            phoneService.update(phone);
            return  Message.success("修改成功!");
        }catch (Exception e){
            return Message.fail("修改失败!");
        }
    }
    @RequestMapping("/deleteManyFindPhone")
    @ResponseBody
    public Message deleteManyphone(@Param("manyId") String manyId,Integer status,HttpSession session) throws  Exception{
        try{
            UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            if(userPCVo==null && userVo==null){
                return Message.fail("请先登录");
            }
            String str[] = manyId.split(",");
            for (String s: str) {
                phoneService.updateStatus(new StatusQuery(Long.parseLong(s),status));
            }
            return Message.success("批量修改状态成功!");
        }catch (Exception e){
            e.printStackTrace();
            return  Message.fail("批量修改状态失败!");
        }
    }
    @RequestMapping("/deleteFindPhone/{id}")
    @ResponseBody
    public Message deletephone(@PathVariable("id") long id,HttpSession session) throws  Exception{
        try{
            UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            if(userPCVo==null && userVo==null){
                return Message.fail("请先登录");
            }
            phoneService.removeById(id);
            return Message.success("删除成功!");
        }catch (Exception e){
            e.printStackTrace();
            return Message.fail("删除失败!");
        }
    }
    @RequestMapping("/phonePage")
    public String table(HttpSession session) throws  Exception{
        UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        if(userPCVo==null && userVo==null){
            return "user/loginPage";
        }
        return "jifen/phoneList";
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
            phoneService.updateStatus(new StatusQuery(id,status));
            return Message.success("ok");
        }catch (Exception e){
            return  Message.fail("fail");
        }
    }
    //判断用户月
    public Integer isHaveMoney(Long userPcId,long type){
        UserPCVo userPCVo = userPCService.getById(userPcId);
        FixedPriceVo fixedPriceVo = fixedPriceService.getById(type);
        if(userPCVo!=null && fixedPriceVo!=null && userPCVo.getJifen()-fixedPriceVo.getPrice()>0){
            return fixedPriceVo.getPrice();
        }else{
            return null;
        }
    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}