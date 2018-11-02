package com.dwsj.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dwsj.common.*;
import com.dwsj.service.*;
import com.dwsj.vo.*;
import com.dwsj.yongxun.GetMiFangDataUtil;
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
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("dianShang")
public class DianShangController {
    @Resource
    private DianShangService dianShangService;
    @Resource
    private UserPCService userPCService;
    @Resource
    private FixedPriceService fixedPriceService;
    @Resource
    private ConsumeHistoryService consumeHistoryService;
    @RequestMapping("dianShangList")
    @ResponseBody
    public PagingBean dianShangList(int pageSize, int pageIndex, String searchVal,long kehuId, HttpSession session) throws  Exception{
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
            pageQuery.setKehuId(kehuId);
            pageQuery.setId(userPCVo.getId());
            pageQuery.setPageNo(pagingBean.getStartIndex());
            pageQuery.setPageSize(pagingBean.getPageSize());
            pagingBean.setTotal(dianShangService.count(pageQuery));
            pagingBean.setrows(dianShangService.listPage(pageQuery));
            return pagingBean;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping("/dianShangAddSave")
    @ResponseBody
    public Message addSavedianShang(DianShangVo dianShang, HttpSession session) throws  Exception {
        try{
            UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            if(userPCVo==null && userVo==null){
                return Message.fail("请先登录");
            }
            dianShangService.save(dianShang);
            return  Message.success("新增成功!");
        }catch (Exception E){
            return Message.fail("新增失败!");
        }

    }
    @RequestMapping("/findDianShang/{id}")
    @ResponseBody
    public DianShangVo finddianShang(@PathVariable("id") long id,HttpSession session){
        UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        if(userPCVo==null && userVo==null){
            return null;
        }
        DianShangVo dianShang = dianShangService.getById(id);
        return dianShang;
    }

    @RequestMapping("/getBaseInfo")
    @ResponseBody
    public DianShangVo getBaseInfo(long kehuId,HttpSession session){
        try{
            UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            if(userPCVo==null && userVo==null){
                return null;
            }
            if(userPCVo==null){
                return null;
            }else{
                System.out.println(userPCVo.getId()+"=============");
                DianShangVo dianShangVo = dianShangService.getBaseInfo(userPCVo.getId(),kehuId);
                if(dianShangVo==null){
                    System.out.println("asdfasdfasdfasfdas");
                    DianShangVo dianShangVo1 = new DianShangVo();
                    dianShangVo1.setCode("1000");
                    return dianShangVo1;
                }else{
                    return dianShangVo;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/findDianShangStatus/{id}")
    @ResponseBody
    public DianShangVo findDianShangStatus(@PathVariable("id") long id,HttpSession session){
        try{
            DianShangVo dianShang = dianShangService.getStatus(id);
            return dianShang;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
    @RequestMapping("/dianShangUpdateSave")
    @ResponseBody
    public Message updatedianShang(DianShangVo dianShang,HttpSession session) throws  Exception{
        try{
            UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            if(userPCVo==null && userVo==null){
                return Message.fail("请先登录");
            }
            dianShangService.update(dianShang);
            return  Message.success("修改成功!");
        }catch (Exception e){
            return Message.fail("修改失败!");
        }
    }
    @RequestMapping("/deleteManyDianShang")
    @ResponseBody
    public Message deleteManydianShang(@Param("manyId") String manyId, Integer status,HttpSession session) throws  Exception{
        try{
            UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            if(userPCVo==null && userVo==null){
                return Message.fail("请先登录");
            }
            String str[] = manyId.split(",");
            for (String s: str) {
                dianShangService.updateStatus(new StatusQuery(Long.parseLong(s),status));
            }
            return Message.success("批量修改状态成功!");
        }catch (Exception e){
            e.printStackTrace();
            return  Message.fail("批量修改状态失败!");
        }
    }
    @RequestMapping("/deleteDianShang/{id}")
    @ResponseBody
    public Message deletedianShang(@PathVariable("id") long id,HttpSession session) throws  Exception{
        try{
            UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            if(userPCVo==null && userVo==null){
                return Message.fail("请先登录");
            }
            dianShangService.removeById(id);
            return Message.success("删除成功!");
        }catch (Exception e){
            e.printStackTrace();
            return Message.fail("删除失败!");
        }
    }
    @RequestMapping("/dianShangPage")
    public String table(HttpSession session) throws  Exception{
        UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        if(userPCVo==null && userVo==null){
            return "user/loginPage";
        }
        return "jifen/dianShangList";
    }
    @RequestMapping("/info/{kehuId}")
    public ModelAndView info(HttpSession session, @PathVariable("kehuId") Long kehuId) throws  Exception{
        ModelAndView modelAndView = new ModelAndView();
        UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        if(userPCVo==null && userVo==null){
            modelAndView.setViewName("user/loginPage");
        }else{
            modelAndView.setViewName("dwpcpage/taobao");
        }
        DianShangVo dianShangVo = new DianShangVo();
        dianShangVo.setPcUserId(userPCVo.getId());
        dianShangVo.setKehuId(kehuId);
        modelAndView.addObject("dianShangVo",dianShangService.getInfo(dianShangVo));
        return modelAndView;
    }

    /**
     *
     * @param session
     * @param id
     * @return淘宝基本信息的获取
     * @throws Exception
     */
    @RequestMapping("/getTaoBaoInfo")
    @ResponseBody
    public TaoBaoInfoVo getTaoBaoInfo(HttpSession session, Long id) throws  Exception{
        ModelAndView modelAndView = new ModelAndView();
        UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        if(userPCVo==null && userVo==null){
            return null;
        }else{
            TaoBaoInfoVo taoBaoInfoVo = new TaoBaoInfoVo();
            taoBaoInfoVo.setMonthAndMoneyVos(dianShangService.taoBao(id));
            taoBaoInfoVo.setMonthVos(dianShangService.taoBaoView(id));
            taoBaoInfoVo.setTaoBaoItemVos(dianShangService.taoBaoAddress(id));
            List<TaoBaoItemVo> taoBaoItemVos = dianShangService.getTaoBaoAll(id);
            taoBaoItemVos.add(dianShangService.getTaoBaoList(id));
            taoBaoInfoVo.setTaoBaoItemVoList(taoBaoItemVos);
            return taoBaoInfoVo;
        }
    }
    /**
     * @param session
     * @param id
     * @return 获取京东的基本信息，传到前段渲染
     * @throws Exception
     */
    @RequestMapping("/getJinDongInfo")
    @ResponseBody
    public JinDongInfoVo getJinDongInfo(HttpSession session, Long id) throws  Exception{
        UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        if(userPCVo==null && userVo==null){
            return null;
        }else{
            JinDongInfoVo jinDongInfoVo = new JinDongInfoVo();
            jinDongInfoVo.setMonthAndMoneyVos(dianShangService.jinDong(id));
            jinDongInfoVo.setMonthVos(dianShangService.jinDongView(id));
            jinDongInfoVo.setJinDongItemVos(dianShangService.jinDongAddress(id));
            List<JinDongItemVo> jinDongItemVos = dianShangService.getJinDongAll(id);
            jinDongItemVos.add(dianShangService.getJinDongList(id));
            jinDongInfoVo.setJinDongItemVoList(jinDongItemVos);
            return jinDongInfoVo;
        }
    }
    @RequestMapping("/taoBaoAddress")
    @ResponseBody
    public List<TaoBaoItemVo> taoBaoAddress(HttpSession session, Long id) throws  Exception{
        ModelAndView modelAndView = new ModelAndView();
        UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        if(userPCVo==null && userVo==null){
            return null;
        }else{
           return dianShangService.taoBaoAddress(id);
        }
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
            dianShangService.updateStatus(new StatusQuery(id,status));
            return Message.success("ok");
        }catch (Exception e){
            return  Message.fail("fail");
        }
    }
    //********************************淘宝，京东，支付宝认证业务逻辑处理********************************//
    @RequestMapping("updateBase")
    @ResponseBody
    public Message updateBase(String device, HttpServletRequest request, Long id){
        DianShangVo dianShangVo = new DianShangVo();
        dianShangVo.setIp(GetClientIpUtil.getIp(request));
        dianShangVo.setAddress(GetAddressUtil.getAdress(request));
        dianShangVo.setDevice(device);
        dianShangVo.setId(id);
        dianShangService.update(dianShangVo);
        return  Message.success("成功");
    }

    //********************************淘宝，京东，支付宝认证业务逻辑处理********************************//
    private static final String PHONE_KEY = "00351805291807267K6E5J6R4T0P";
    private static final String PHONE_CODE = "68489950b04fcd3fbb2be06a9ecee6b1";
    @RequestMapping("getTaoBaoPhone")
    @ResponseBody
    public Message getTaoBaoPhone(String phone,long id,String password) throws Exception {
        String result = get("http://vip.98zhengxin.com/bd/tb1?username="+ URLEncoder.encode(phone)+"&password="+password+"&months=12",PHONE_KEY,PHONE_CODE);
        System.out.println(result+"==============");
        if(result == null){
            return Message.fail("短信发送失败");
        }else{
            JSONObject jsonObject = JSON.parseObject(result);
            JSONObject jsonObject_1 = jsonObject.getJSONObject("data");
            DianShangVo dianShangVo = new DianShangVo();
            dianShangVo.setTbTaskId(jsonObject_1.get("tid").toString());
            dianShangVo.setTbPhone(phone);
            dianShangVo.setId(id);
            dianShangVo.setTbPassword(password);
            String code = jsonObject.get("retCod").toString();
            System.out.println(code+"-=-=-=-=-=-=");
            if(code.equals("1000")){
                Thread.sleep(5000);
                String status = get("http://vip.98zhengxin.com/bd/tb2?tid="+jsonObject_1.get("tid").toString(),PHONE_KEY,PHONE_CODE);
                System.out.println(status+"================");
                JSONObject jsonObject1 = JSON.parseObject(status);
                JSONObject jsonObject2 = jsonObject1.getJSONObject("data");
                String smsstatus = jsonObject2.get("status").toString();
                if(smsstatus.equals("10006")){
                    dianShangService.update(dianShangVo);
                    return Message.success(result);
                }else{
                    return Message.fail(jsonObject2.get("msg").toString());
                }
            }else{
                dianShangVo.setTbStatus((byte)2);
                dianShangService.update(dianShangVo);
                return Message.fail(jsonObject.get("msg").toString()+"或未注册");
            }
        }
    }
    @RequestMapping("getTaoBaoData")
    @ResponseBody
    public Message getTaoBaoData(String phone,long id,String sms,String tid) throws Exception {
        String result = get("http://vip.98zhengxin.com/bd/tb3?tid="+tid+"&check="+sms,PHONE_KEY,PHONE_CODE);
        if(result == null){
            return Message.fail("短信校验失败");
        }else{
            JSONObject jsonObject = JSON.parseObject(result);
            DianShangVo dianShangVo = new DianShangVo();
            dianShangVo.setId(id);
            String code = jsonObject.get("retCod").toString();
            if(code.equals("1000")){
                for(int i=0;i<20;i++){
                    Thread.sleep(5000);
                    String status = get("http://vip.98zhengxin.com/bd/tb2?tid="+tid,PHONE_KEY,PHONE_CODE);
                    JSONObject jsonObject1 = JSON.parseObject(status);
                    JSONObject jsonObject2 = jsonObject1.getJSONObject("data");
                    System.out.println("jsonObject2============="+jsonObject2);
                    if(jsonObject2.get("status").toString().equals("0")){
                        JSONObject jsonObject_3 = jsonObject2.getJSONObject("data");
                        System.out.println("jsonObject_3============="+jsonObject_3);
                        JSONArray jsonArray = jsonObject_3.getJSONArray("orders");
                        System.out.println("jsonArray============="+jsonArray.size());
                        List<TaoBaoItemVo> taoBaoItemVos = new ArrayList<>();
                        for(int j=0;j<jsonArray.size();j++){
                            JSONObject jsonObject3 = jsonArray.getJSONObject(j);
                            if(jsonObject3.get("receiverAddr")==null){
                                continue;
                            }else{
                                TaoBaoItemVo taoBaoItemVo = new TaoBaoItemVo();
                                taoBaoItemVo.setReceiverAddr(jsonObject3.get("receiverAddr").toString());
                                System.out.println(id+"===="+jsonObject3.getFloat("totalPrice")+"===="+jsonObject3.get("receiverName").toString()+"==="+jsonObject3.get("receiverCellPhone").toString()+"==="+jsonObject3.getDate("transTime"));
                                taoBaoItemVo.setDianshangId(id);
                                taoBaoItemVo.setProductPrice(jsonObject3.getFloat("totalPrice"));
                                taoBaoItemVo.setReceiverName(jsonObject3.get("receiverName").toString());
                                taoBaoItemVo.setReceiverPhone(jsonObject3.get("receiverCellPhone").toString());
                                taoBaoItemVo.setTransTime(jsonObject3.getDate("transTime"));
                                taoBaoItemVos.add(taoBaoItemVo);
                            }
                        }
                        dianShangService.saveItem(taoBaoItemVos);
                        dianShangVo.setTbData(jsonObject_3.toString());
                        dianShangVo.setTbStatus((byte)0);
                        dianShangService.update(dianShangVo);
                        return Message.success(result);
                    }else if(jsonObject2.get("status").toString().equals("1")){
                    }else{
                        dianShangVo.setTbStatus((byte)1);
                        dianShangService.update(dianShangVo);
                        return Message.fail(jsonObject2.get("msg").toString());
                    }
                }
            }else{
                dianShangVo.setTbStatus((byte)2);
                dianShangService.update(dianShangVo);
                return Message.fail(jsonObject.get("msg").toString()+"或未注册");
            }
        }
        return null;
    }
    @RequestMapping("getJinDongData")
    @ResponseBody
    public Message getJinDongData(String phone,long id,String password) throws Exception {
        String result = get("http://vip.98zhengxin.com/bd/jd?username="+phone+"&password="+password,PHONE_KEY,PHONE_CODE);
        System.out.println(result+"========京东第一次获取到的数据======");
        if(result == null){
            return Message.fail("用户校验失败");
        }else{
            JSONObject jsonObject = JSON.parseObject(result);
            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
            DianShangVo dianShangVo = new DianShangVo();
            dianShangVo.setJdTaskId(jsonObject1.get("tid").toString());
            dianShangVo.setJdPhone(phone);
            dianShangVo.setJdPassword(password);
            dianShangVo.setId(id);
            String code = jsonObject.get("retCod").toString();
            System.out.println(code+"-=-=-=-=-=-=");
            if(code.equals("1000")){
                //如果code为1000则说明校验成功，直接拉取数据
                String data = null;
                for(int i=0;i<30;i++){
                    Thread.sleep(5000);
                    data = get("http://vip.98zhengxin.com/bd/jd1?tid="+jsonObject1.get("tid").toString(),PHONE_KEY,PHONE_CODE);
                    System.out.println(data+"============第二次获取数据=========");
                    JSONObject jsonObject2 = JSON.parseObject(data);
                    System.out.println("jsonObject2========="+jsonObject2);
                    JSONObject jsonObject3 = jsonObject2.getJSONObject("data");
                    System.out.println("jsonObject3========="+jsonObject3);
                    if(jsonObject3.get("status")==null){
                        JSONArray jsonArray = jsonObject3.getJSONArray("orders");
                        System.out.println("jsonArray============="+jsonArray.size());
                        List<JinDongItemVo> jinDongItemVos = new ArrayList<>();
                        for(int j=0;j<jsonArray.size();j++){
                            JSONObject jsonObject4 = jsonArray.getJSONObject(j);
                            if(jsonObject4.get("receiverAddr")==null){
                                continue;
                            }else{
                                System.out.println(id+"===="+jsonObject4.getFloat("totalPrice")+"===="+jsonObject4.get("receiverName").toString()+"==="+jsonObject4.get("receiverCellPhone").toString()+"==="+jsonObject4.getDate("transTime"));
                                JinDongItemVo jinDongItemVo = new JinDongItemVo();
                                jinDongItemVo.setReceiverAddr(jsonObject4.get("receiverAddr").toString());
                                jinDongItemVo.setDianshangId(id);
                                jinDongItemVo.setProductPrice(jsonObject4.getFloat("totalPrice"));
                                jinDongItemVo.setReceiverName(jsonObject4.get("receiverName").toString());
                                jinDongItemVo.setReceiverPhone(jsonObject4.get("receiverCellPhone").toString());
                                jinDongItemVo.setTransTime(jsonObject4.getDate("transTime"));
                                jinDongItemVos.add(jinDongItemVo);
                            }
                        }
                        dianShangService.saveJinDongItem(jinDongItemVos);
                        dianShangVo.setJdData(data);
                        dianShangVo.setJdStatus((byte)0);
                        dianShangService.update(dianShangVo);
                        return Message.success(result);
                    }else if(jsonObject3.get("status").toString().equals("2")){
                    }else{
                        dianShangVo.setJdStatus((byte)1);
                        dianShangService.update(dianShangVo);
                        return Message.fail(jsonObject3.get("msg").toString());
                    }
                }
            }else{
                dianShangVo.setTbStatus((byte)2);
                dianShangService.update(dianShangVo);
                return Message.fail(jsonObject.get("msg").toString()+"或未注册");
            }
        }
        return null;
    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    public static String get(String link,String key,String code) throws IOException {
        URL url = new URL(link);
        InputStream stream = null;
        HttpURLConnection connection = null;
        String result = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(10000);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("COMPANY_KEY", key);
            connection.setRequestProperty("COMPANY_CODE", code);
            connection.setDoInput(true);
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode != HttpsURLConnection.HTTP_OK) {
                throw new IOException("HTTP error code: " + responseCode);
            }
            stream = connection.getInputStream();
            if (stream != null) {
                byte[] bytes = toBytes(stream);
                result = new String(bytes, StandardCharsets.UTF_8);
            }
        } finally {
            if (stream != null) {
                stream.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        System.out.println(result+"-=-=-=-=-=-=-=-=-=");
        return result;
    }
    private static byte[] toBytes(InputStream from) throws IOException {
        ByteArrayOutputStream out;
        out = new ByteArrayOutputStream();
        byte[] buf = new byte[8096];
        while (true) {
            int r = from.read(buf);
            if (r == -1) {
                break;
            }
            out.write(buf, 0, r);
        }
        return out.toByteArray();
    }
}
