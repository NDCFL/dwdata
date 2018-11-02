package com.dwsj.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dwsj.common.*;
import com.dwsj.service.ConsumeHistoryService;
import com.dwsj.service.FindPoliceService;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chenfeilong on 2017/10/21.
 */
@Controller
@RequestMapping("police")
public class FindPoliceController {
    private static final String PHONE_KEY = "00351805291807267K6E5J6R4T0P";
    private static final String PHONE_CODE = "68489950b04fcd3fbb2be06a9ecee6b1";
    @Resource
    private FindPoliceService policeService;
    @Resource
    private UserPCService userPCService;
    @Resource
    private FixedPriceService fixedPriceService;
    @Resource
    private ConsumeHistoryService consumeHistoryService;
    @RequestMapping("policeList")
    @ResponseBody
    public PagingBean policeList(int pageSize, int pageIndex, String searchVal,Long kehuId, HttpSession session) throws  Exception{
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
            pagingBean.setTotal(policeService.count(pageQuery));
            pagingBean.setrows(policeService.listPage(pageQuery));
            return pagingBean;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping("/policeAddSave")
    @ResponseBody
    public FindPoliceVo addSavepolice(FindPoliceVo police, HttpSession session, HttpServletRequest request) throws  Exception {
        try{
            //先判断用户余额是否够用
            UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            if(userPCVo==null && userVo==null){
                return null;
            }
            police.setPcUserId(userPCVo.getId());
            //判断是否余额
            FixedPriceVo fixedPriceVo = fixedPriceService.getById(10l);
            if(userPCVo.getJifen()-fixedPriceVo.getPrice()<0){
                return new FindPoliceVo();
            }
            Integer jifen = fixedPriceVo.getPrice();
            ConsumeHistoryVo consumeHistoryVo = new ConsumeHistoryVo();
            consumeHistoryVo.setPcUserId(userPCVo.getId());
            consumeHistoryVo.setAmount(jifen);
            consumeHistoryVo.setBcr(police.getBcr());
            consumeHistoryVo.setIsActive((byte)0);
            consumeHistoryVo.setCreateTime(new Date());
            consumeHistoryVo.setRemark("身份证认证-"+jifen+"积分");
            //新增消费记录
            consumeHistoryService.save(consumeHistoryVo);
            if(jifen!=null){
                //用总积分减去查询所需要的积分
                userPCVo.setJifen(userPCVo.getJifen()-jifen);
                //扣除余额
                userPCService.updateJiFen(userPCVo);
                FindPoliceVo f = policeService.getFindPoliceInfo(police);
                Integer cnt = policeService.getCnt(police);
                //如果f不为空，则说明已经查询过该人
                if(f!=null){
                    //该人已经查询过
                    if(cnt>0){
                        f.setCreateTime(new Date());
                        f.setCode(0);
                        policeService.save(f);
                    }else{
                        f.setCreateTime(new Date());
                        f.setCode(0);
                        f.setPcUserId(userPCVo.getId());
                        policeService.save(f);
                    }
                    return f;
                }else{
                    //获取到用户的身份证照片
                    String headImg = GetPhotoUtil.imgUpload(police.getBcr(),police.getBcrIdcard(),request);
                    if(headImg.equals("0") || headImg.equals("1")){
                        //用总积分减去查询所需要的积分
                        userPCVo.setJifen(userPCVo.getJifen()-jifen);
                        //扣除余额
                        userPCService.updateJiFen(userPCVo);
                        police.setIsActive((byte)1);
                        police.setCode(1);
                        policeService.save(police);
                        return police;
                    }else{
                        System.out.println(headImg);
                        police.setHeadImg(headImg);
                    }
                    //调用身份证接口查询信息
                    String result = IdCardUtil.get("http://vip.98zhengxin.com/idcard/idcardanalysis?idcard="+police.getBcrIdcard(),PHONE_KEY,PHONE_CODE);
                    if(result==null || result.equals("")){
                        police.setIsActive((byte)1);
                        policeService.save(police);
                        police.setCode(1);
                        return police;
                    }else{
                        JSONObject jsonObject = JSON.parseObject(result);
                        String code = jsonObject.get("retCod").toString();
                        if(code.equals("1000")){
                            police.setIsActive((byte)0);
                        }else{
                            police.setIsActive((byte)1);
                        }
                        police.setCode(0);
                        police.setData(result);
                        policeService.save(police);
                        return police;
                    }
                }
            }else{
                police.setCode(1);
                return police;
            }
        }catch (Exception e){
            e.printStackTrace();
            police.setCode(1);
            return police;
        }
    }
    @RequestMapping("/findFindPolice/{id}")
    @ResponseBody
    public FindPoliceVo findpolice(@PathVariable("id") long id,HttpSession session){
        UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        if(userPCVo==null && userVo==null){
            return null;
        }
        FindPoliceVo police = policeService.getById(id);
        return police;
    }
    @RequestMapping("/policeUpdateSave")
    @ResponseBody
    public Message updatepolice(FindPoliceVo police,HttpSession session) throws  Exception{
        try{
            UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            if(userPCVo==null && userVo==null){
                return Message.fail("请先登录");
            }
            policeService.update(police);
            return  Message.success("修改成功!");
        }catch (Exception e){
            return Message.fail("修改失败!");
        }
    }
    @RequestMapping("/deleteManyFindPolice")
    @ResponseBody
    public Message deleteManypolice(@Param("manyId") String manyId,Integer status,HttpSession session) throws  Exception{
        try{
            UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            if(userPCVo==null && userVo==null){
                return Message.fail("请先登录");
            }
            String str[] = manyId.split(",");
            for (String s: str) {
                policeService.updateStatus(new StatusQuery(Long.parseLong(s),status));
            }
            return Message.success("批量修改状态成功!");
        }catch (Exception e){
            e.printStackTrace();
            return  Message.fail("批量修改状态失败!");
        }
    }
    @RequestMapping("/deleteFindPolice/{id}")
    @ResponseBody
    public Message deletepolice(@PathVariable("id") long id,HttpSession session) throws  Exception{
        try{
            UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            if(userPCVo==null && userVo==null){
                return Message.fail("请先登录");
            }
            policeService.removeById(id);
            return Message.success("删除成功!");
        }catch (Exception e){
            e.printStackTrace();
            return Message.fail("删除失败!");
        }
    }
    @RequestMapping("/policePage")
    public String table(HttpSession session) throws  Exception{
        UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        if(userPCVo==null && userVo==null){
            return "user/loginPage";
        }
        return "jifen/policeList";
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
            policeService.updateStatus(new StatusQuery(id,status));
            return Message.success("ok");
        }catch (Exception e){
            return  Message.fail("fail");
        }
    }
    //判断用户月
    public Integer isHaveMoney(Long userPcId,int type){
        UserPCVo userPCVo = userPCService.getById(userPcId);
        FixedPriceVo fixedPriceVo = fixedPriceService.getById(11l);
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