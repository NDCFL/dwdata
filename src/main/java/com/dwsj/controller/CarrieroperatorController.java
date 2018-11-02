package com.dwsj.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dwsj.common.Message;
import com.dwsj.common.PageQuery;
import com.dwsj.common.PagingBean;
import com.dwsj.common.StatusQuery;
import com.dwsj.controller.websocket.MessageHandler;
import com.dwsj.enums.ActiveStatusEnum;
import com.dwsj.service.CarrieroperatorService;
import com.dwsj.service.ConsumeHistoryService;
import com.dwsj.service.FixedPriceService;
import com.dwsj.service.UserPCService;
import com.dwsj.vo.*;
import org.apache.ibatis.annotations.Param;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static sun.net.www.protocol.http.HttpURLConnection.userAgent;

@Controller
@RequestMapping("carrieroperator")
public class CarrieroperatorController {
    //appid
    public static final String APPID = "f4225c386db2cf1b5bfd8f2e065db5fb";
    //appkey值
    public static final String APPKEY = "f861118ea8126f1e9ce5df50e07157d6f402739d3eeac5864559d59ecde0e9ba";
    @Resource
    private CarrieroperatorService carrieroperatorService;
    @Resource
    private FixedPriceService fixedPriceService;
    @Resource
    private UserPCService userPCService;
    @Resource
    private ConsumeHistoryService consumeHistoryService;
    @RequestMapping("carrieroperatorList")
    @ResponseBody
    public PagingBean carrieroperatorList(int pageSize, int pageIndex, String searchVal,Long kehuId, HttpSession session) throws  Exception{
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
            pageQuery.setId(userPCVo.getId());
            pageQuery.setKehuId(kehuId);
            pagingBean.setTotal(carrieroperatorService.count(pageQuery));
            pagingBean.setrows(carrieroperatorService.listPage(pageQuery));
            return pagingBean;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping("/carrieroperatorAddSave")
    @ResponseBody
    public Message addSavecarrieroperator(CarrieroperatorVo carrieroperator, HttpSession session) throws  Exception {
        try{
            UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            if(userPCVo==null && userVo==null){
                return Message.fail("请先登录");
            }
            carrieroperator.setIsActive(ActiveStatusEnum.ACTIVE.getValue().byteValue());
            carrieroperatorService.save(carrieroperator);
            return  Message.success("新增成功!");
        }catch (Exception E){
            return Message.fail("新增失败!");
        }

    }
    @RequestMapping("/findCarrieroperator/{id}")
    @ResponseBody
    public CarrieroperatorVo findcarrieroperator(@PathVariable("id") long id,HttpSession session){
        UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        if(userPCVo==null && userVo==null){
            return null;
        }
        CarrieroperatorVo carrieroperator = carrieroperatorService.getById(id);
        return carrieroperator;
    }
    @RequestMapping("/carrieroperatorUpdateSave")
    @ResponseBody
    public Message updatecarrieroperator(CarrieroperatorVo carrieroperator,HttpSession session) throws  Exception{
        try{
            UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            if(userPCVo==null && userVo==null){
                return Message.fail("请先登录");
            }
            carrieroperatorService.update(carrieroperator);
            return  Message.success("修改成功!");
        }catch (Exception e){
            return Message.fail("修改失败!");
        }
    }
    @RequestMapping("/deleteManyCarrieroperator")
    @ResponseBody
    public Message deleteManycarrieroperator(@Param("manyId") String manyId, Integer status,HttpSession session) throws  Exception{
        try{
            UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            if(userPCVo==null && userVo==null){
                return Message.fail("请先登录");
            }
            String str[] = manyId.split(",");
            for (String s: str) {
                carrieroperatorService.updateStatus(new StatusQuery(Long.parseLong(s),status));
            }
            return Message.success("批量修改状态成功!");
        }catch (Exception e){
            e.printStackTrace();
            return  Message.fail("批量修改状态失败!");
        }
    }
    @RequestMapping("/deleteCarrieroperator/{id}")
    @ResponseBody
    public Message deletecarrieroperator(@PathVariable("id") long id,HttpSession session) throws  Exception{
        try{
            UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            if(userPCVo==null && userVo==null){
                return Message.fail("请先登录");
            }
            carrieroperatorService.removeById(id);
            return Message.success("删除成功!");
        }catch (Exception e){
            e.printStackTrace();
            return Message.fail("删除失败!");
        }
    }
    @RequestMapping("/carrieroperatorPage")
    public String table(HttpSession session) throws  Exception{
        UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        if(userPCVo==null && userVo==null){
            return null;
        }
        return "jifen/carrieroperatorList";
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
            carrieroperatorService.updateStatus(new StatusQuery(id,status));
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
    /**
     * 调用电话邦的接口
     */
    /**
     * 获取毫秒数
     * @param date
     * @return
     */
    public static int getSecondTimestamp(Date date){
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime());
        int length = timestamp.length();
        if (length > 3) {
            return Integer.valueOf(timestamp.substring(0,length-3));
        } else {
            return 0;
        }
    }

    /**
     * MD5加密
     * @param
     * @param
     * @return
     * @throws Exception
     */
    public String md5(String input) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] output = messageDigest.digest(input.getBytes());
        StringBuilder sb = new StringBuilder();
        String temp;
        for (byte aB : output) {
            temp = (Integer.toHexString(aB & 0XFF));
            if (temp.length() == 1) {
                sb.append("0").append(temp);
            } else {
                sb.append(temp);
            }
        }
        return sb.toString();
    }
    public String get(String link) throws IOException {
        URL url = new URL(link);
        InputStream stream = null;
        HttpsURLConnection connection = null;
        String result = null;
        try {
            connection = (HttpsURLConnection) url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(10000);
            connection.setRequestMethod("GET");
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
        return result;
    }
    private byte[] toBytes(InputStream from) throws IOException {
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

    /**
     * 获取令牌
     * @param appid
     * @param sign
     * @param time
     * @return
     * @throws IOException
     */
    public String getToken(String appid, String sign, String time) throws IOException {
        String url = "https://crs-api.dianhua.cn/token?appid=" + appid + "&sign=" + sign + "&time=" + time;
        System.out.println(url);
        String result = get(url);
        JSONObject jsonObject = JSONObject.parseObject(result);
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.get("data").toString());
        System.out.println("getToken: " + result);
        return jsonObject1.get("token").toString();
    }

    /**
     * 工具函数，map 转换 String
     *
     * @param params 要转换的 map
     * @return 转换结果
     * @throws IOException
     */
    public static String getParamsString(Map<String, String> params)
            throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            result.append("&");
        }

        String resultString = result.toString();
        return resultString.length() > 0
                ? resultString.substring(0, resultString.length() - 1)
                : resultString;
    }
    /**
     * 工具函数，post 请求
     *
     * @param link 请求链接
     * @param postDataParams 请求参数
     * @return 请求结果
     * @throws IOException
     */
    public String post(String link, HashMap<String, String> postDataParams) throws IOException {
        URL url = new URL(link);
        InputStream stream = null;
        HttpsURLConnection connection = null;
        String result = null;
        try {
            connection = (HttpsURLConnection) url.openConnection();
            connection.setReadTimeout(180000);
            connection.setConnectTimeout(10000);
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            if (!(postDataParams == null || postDataParams.isEmpty())) {
                DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                out.writeBytes(getParamsString(postDataParams));
            }
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
        return result;
    }
    /**
     * 发送短信验证码
     * @param tel
     * @param session
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("phoneCheck")
    public Message getRult(String tel, HttpSession session,String idcard,String name) throws Exception {
        UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        if(userPCVo==null && userVo==null){
            return Message.fail("请先登录");
        }
        FixedPriceVo fixedPriceVo = fixedPriceService.getById(5l);
        if(userPCVo.getJifen()-fixedPriceVo.getPrice()<0){
            return Message.fail("积分不足，请充值");
        }
        Integer time = getSecondTimestamp(new Date());
        String sign = md5(APPID+APPKEY+time);
        String tocken = getToken(APPID,sign,time+"");
        session.setAttribute("user1",tocken);
        String url = "https://crs-api.dianhua.cn/calls/flow?token=" + tocken + "&tel=" + tel;
        String result = get(url);
        JSONObject jsonObject = JSONObject.parseObject(result);
        String last = jsonObject.get("action").toString();
        if(last.equals("reset")){
            return Message.fail(jsonObject.get("msg").toString());
        }else{
            return Message.success(result);
        }
    }
    /**
     * 获取报告
     * @param tel
     * @param password
     * @param sid
     * @param sms_code
     * @param session
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("getLogin")
    public Message getLogin(String tel,String password,String sid,String sms_code,HttpSession session,String name,String headImg,Long kehuId){
        UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        if(userPCVo==null && userVo==null){
            return Message.fail("请先登录");
        }
        synchronized (this){
            long id = 0;
            try{
                HashMap<String,String> map  = new HashMap<>();
                map.put("tel",tel);
                map.put("pin_pwd",password);
                map.put("sid",sid);
                map.put("sms_code",sms_code);
                String url = "https://crs-api.dianhua.cn/calls/login?token=" + session.getAttribute("user1").toString();
                System.out.println(url+"===================");
                //http://bmp.dianhua.cn/report/detail?cid=218&no=SIDff368066fe194b5f8e8d8d31022595ca
                //https://bmp.dianhua.cn/report/detail?sid={sid}&appid={appid}
                String result = post(url,map);
                //休眠几秒钟，等待响应
                System.out.println(result+"=========getlogin=");
                JSONObject jsonObject = JSONObject.parseObject(result);
                String last = jsonObject.get("action").toString();
                Integer status = Integer.parseInt(jsonObject.get("status").toString());
                //如果已经结束了，则不需要进行二次验证
                if(status!=0){
                    return Message.fail(jsonObject.get("msg").toString());
                }
                if(last.equals("reset") && status==0){
                    return Message.fail(jsonObject.get("msg").toString());
                }
                System.out.println(name+"-=-=-=-=--------------------------");
                CarrieroperatorVo carrieroperatorVo = new CarrieroperatorVo();
                carrieroperatorVo.setBcr(name);//被查人
                carrieroperatorVo.setIsActive((byte) 1);//正在生成报告
                carrieroperatorVo.setBcrPhone(tel);
                carrieroperatorVo.setPcUserId(userPCVo.getId());
                carrieroperatorVo.setUrl("https://bmp.dianhua.cn/report/detail?sid="+sid+"&appid="+APPID);
                carrieroperatorVo.setSid(sid);
                carrieroperatorVo.setHeadImg(headImg);
                carrieroperatorVo.setKehuId(kehuId);
                //保存到数据库中
                carrieroperatorService.save(carrieroperatorVo);
                id = carrieroperatorVo.getId();
                System.out.println(tel+"===="+password+"======"+sid+"===="+sms_code);
                if(last.equals("done") && status==0){
                    //如果成功了，则直接把状态进行修改成，已经生成了报告
                    return Message.success(id+"");
                }else if(last.equals("processing") && status==0){
                    return Message.fail(carrieroperatorVo.getId()+"");
                }else{
                    return Message.fail("查询失败");
                }
            }catch (Exception e){
                e.printStackTrace();
                //3：授权失败
                carrieroperatorService.updateStatus(new StatusQuery(id,3));
                return Message.fail("授权失败");
            }
        }
    }
    @ResponseBody
    @RequestMapping("checkTwoLogin")
    public Message checkTwoLogin(String sid,String sms_code,HttpSession session){
        try{
            UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            if(userPCVo==null && userVo==null){
                return Message.fail("请先登录");
            }
            System.out.println(sid+"===="+sid+"===="+sms_code);
            HashMap<String,String> map  = new HashMap<>();
            map.put("sid",sid);
            map.put("sms_code",sms_code);
            String url = "https://crs-api.dianhua.cn/calls/verify?token=" + session.getAttribute("user1").toString();
            System.out.println(url+"===================");
            //http://bmp.dianhua.cn/report/detail?cid=218&no=SIDff368066fe194b5f8e8d8d31022595ca
            //https://bmp.dianhua.cn/report/detail?sid={sid}&appid={appid}
            String result = post(url,map);
            JSONObject jsonObject = JSONObject.parseObject(result);
            String last = jsonObject.get("action").toString();
            Integer status = Integer.parseInt(jsonObject.get("status").toString());
            if(status!=0){
                return Message.fail(jsonObject.get("msg").toString());
            }
            if(last.equals("done") && status==0){
                return Message.success(result);
            }else{
                return Message.fail(jsonObject.get("msg").toString());
            }
        }catch (Exception e){
            e.printStackTrace();
            return Message.fail("请进行二次验证");
        }
    }
    @ResponseBody
    @RequestMapping("success")
    public Message success(Long id,String sid,String headImg,HttpServletRequest request){
        try{
            Thread.sleep(60000);
            carrieroperatorService.updateUrl(id,getDatas("https://bmp.dianhua.cn/report/detail?sid="+sid+"&appid="+APPID,headImg));
            carrieroperatorService.updateStatus(new StatusQuery(id,0));
            return Message.success("采集成功");
        }catch (Exception e){
            e.printStackTrace();
            //2：采集失败
            carrieroperatorService.updateStatus(new StatusQuery(id,2));
            return Message.fail("采集失败");
        }
    }
    @ResponseBody
    @RequestMapping("callBack")
    public String callBack(@RequestBody String data){
        try{
            System.out.println(data+"=====开始回掉推送======");
            JSONObject jsonObject = JSON.parseObject(data);
            String sid = jsonObject.get("sid").toString();
            String status = jsonObject.get("status").toString();
            CarrieroperatorVo carrieroperatorVo = carrieroperatorService.getInfoBySid(sid);
            //这里是返回采集成功的推送
            carrieroperatorService.updateUrl(carrieroperatorVo.getId(),getDatas("https://bmp.dianhua.cn/report/detail?sid="+sid+"&appid="+APPID,carrieroperatorVo.getHeadImg()));
            carrieroperatorService.updateStatus(new StatusQuery(carrieroperatorVo.getId(),Integer.parseInt(status)));
            //新增消费记录
            ConsumeHistoryVo consumeHistoryVo = new ConsumeHistoryVo();
            consumeHistoryVo.setBcr(carrieroperatorVo.getBcr());
            consumeHistoryVo.setCreateTime(new Date());
            consumeHistoryVo.setRemark("运营商报告");
            consumeHistoryVo.setPcUserId(carrieroperatorVo.getPcUserId());
            FixedPriceVo fixedPriceVo = fixedPriceService.getById(5l);
            consumeHistoryVo.setAmount(fixedPriceVo.getPrice());
            if(status.equals("0")){
                consumeHistoryVo.setIsActive((byte)0);
                //修改积分
                UserPCVo userPCVo = userPCService.getById(carrieroperatorVo.getPcUserId());
                userPCVo.setJifen(userPCVo.getJifen()-fixedPriceVo.getPrice());
                userPCService.updateJiFen(userPCVo);
            }else{
                consumeHistoryVo.setIsActive((byte)1);
            }
            consumeHistoryService.save(consumeHistoryVo);
            System.out.println(carrieroperatorVo.getPcUserId()+"=============用户编号=========");
            new MessageHandler().sendMessageToUser(carrieroperatorVo.getPcUserId()+"","用户采集成功");
            return "ok";
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }
    public static String getDatas(String url,String userImg){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Long time = System.currentTimeMillis();
        Connection conn = Jsoup.connect(url);
        // 修改http包中的header,伪装成浏览器进行抓取
        conn.header("User-Agent", userAgent);
        Document doc = null;
        try {
            doc = conn.get();
            //解析源代码
            Document document = Jsoup.parse(doc.html().replace("电话邦","地网数据"));
            System.out.println(document);
            Element element = document.getElementsByTag("tr").get(0);
//            <td><img src="images/h1.png" /></td>
            element.html(element.html().replace("</td>","")+"<td rowspan=\"6\"><img src=\"http://192.168.3.18:8080"+userImg+"\" /></td>");
            document.getElementsByClass("report_tittle").get(0).getElementsByTag("img").attr("src","http://192.168.3.18:8080/upload/h1.png");
            document.getElementsByClass("report_tittle").get(0).getElementsByTag("a").removeAttr("href").attr("id","dc").attr("style","visibility:visible");
            document.getElementsByClass("report_tittle").attr("src");
            document.getElementsByClass("bot_logo").remove();
            String projectPath = request.getSession().getServletContext().getRealPath("/upload/report/"+time+".html");
            File f = new File(projectPath);
            if (!f.exists()){
                f.createNewFile();
            }
            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f),"utf-8");
            BufferedWriter writer=new BufferedWriter(write);
            String htmls = "";
            writer.write(document.outerHtml()+readToString());
            writer.close();
            return  "/upload/report/"+time+".html";
        } catch (IOException e) {
            e.printStackTrace();
            return "-1";
        }
    }
    public static String readToString() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String encoding = "UTF-8";
        String projectPath = request.getSession().getServletContext().getRealPath("/upload/report/module.html");
        File file = new File(projectPath);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }
}
