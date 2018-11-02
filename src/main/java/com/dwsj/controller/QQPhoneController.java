package com.dwsj.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dwsj.common.*;
import com.dwsj.controller.websocket.MessageHandler;
import com.dwsj.service.*;
import com.dwsj.vo.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;
import sun.security.provider.MD5;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("qqPhone")
public class QQPhoneController {

    @Resource
    private UserPCService userPCService;
    @Resource
    private FixedPriceService fixedPriceService;
    @Resource
    private ConsumeHistoryService consumeHistoryService;
    @Resource
    private QQPhoneService qqPhoneService;
    @Resource
    private QQPhoneListService qqPhoneListService;
    //获取当前的时间戳
    private static Long time = System.currentTimeMillis();
    //得到当前的标识
    private static String sign = new Md5Hash("appKey=c230eec160332603cdbfc76a2a5cf82e&appSecret=mLdZuffv&currentTime="+time+"&nonce=diwangshuju666").toString();
    @RequestMapping("qqPhoneList")
    @ResponseBody
    public PagingBean jiFenList(int pageSize, int pageIndex, String searchVal,Long kehuId, HttpSession session) throws  Exception{
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
            pagingBean.setTotal(qqPhoneService.count(pageQuery));
            pagingBean.setrows(qqPhoneService.listPage(pageQuery));
            return pagingBean;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @RequestMapping("getQR")
    @ResponseBody
    public Message getQRCode(HttpSession session){
        UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
        if(userPCVo==null){
            return  Message.fail("请先登录");
        }
        FixedPriceVo fixedPriceVo = fixedPriceService.getById(6l);
        if(userPCVo.getJifen()-fixedPriceVo.getPrice()<0){
            return Message.fail("积分不足,请先充值");
        }
        try{
            String url = "https://zhixin.kilcreapark.com/qq/getQQCode";
            String result = httpURLConnectionPOST(url,"appKey=c230eec160332603cdbfc76a2a5cf82e&nonce=diwangshuju666&currentTime="+time+"&signature="+sign);
            JSONObject jsonObject = JSON.parseObject(result);
            JSONObject jsonObject1 = jsonObject.getJSONObject("result");
            String img_path = jsonObject1.get("imgUrl").toString();
            String hash_val = jsonObject1.get("hash").toString();
            if(hash_val!=null && img_path!=null){
                return Message.success(img_path+"++"+hash_val);
            }else{
                return Message.fail("获取二维码失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Message.fail("获取二维码失败");
        }
    }
    @RequestMapping("checkStatus")
    @ResponseBody
    public Message checkStatus(String hashVal){
        System.out.println(hashVal+"==================");
        String qr_status = "https://zhixin.kilcreapark.com/qq/checkQRCodeStatus";
        for(int i = 0;i<40;i++){
            try {
                Thread.sleep(3000);
                String result = httpURLConnectionPOST(qr_status,"hash="+hashVal+"&appKey=c230eec160332603cdbfc76a2a5cf82e&nonce=diwangshuju666&currentTime="+time+"&signature="+sign);
                System.out.println(result);
                JSONObject jsonObject = JSON.parseObject(result);
                String code = jsonObject.get("code").toString();
                System.out.println(code+"========================");
                if(code.equals("0")){
                    return Message.success(result);
                }else if(code.equals("20049")){
                    return Message.fail("二维码失效");
                }else if(code.equals("30000")){
                    return Message.fail(jsonObject.get("msg").toString());
                }else if(code.equals("20045")){
                    return Message.fail(jsonObject.get("msg").toString());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                return Message.fail("二维码失效");
            }
        }
        return Message.fail("二维码失效");
    }

    /**
     * 验证短信验证码，并拉取数据
     * @param hashVal
     * @return
     */
    @RequestMapping("checkPhoneName")
    @ResponseBody
    public Message checkPhoneName(String hashVal, String linkNameJson,HttpSession session) throws Exception {
        UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
        if(userPCVo==null){
            return Message.fail("请先登录");
        }
        String qr_status = "https://zhixin.kilcreapark.com/qq/checkContacts";
        String result = httpURLConnectionPOST(qr_status,"hash="+hashVal+"&linkNameJson="+URLEncoder.encode(linkNameJson,"utf-8")+"&appKey=c230eec160332603cdbfc76a2a5cf82e&nonce=diwangshuju666&currentTime="+time+"&signature="+sign);
        System.out.println(result);
        JSONObject jsonObject = JSON.parseObject(result);
        String code = jsonObject.get("code").toString();
        if(code.equals("0")){
            return Message.success(result);
        }else{
            return Message.fail(jsonObject.get("msg").toString());
        }
    }

    /**
     * 验证短信验证码，并拉取数据
     * @param hashVal
     * @return
     */
    @RequestMapping("checkSMS")
    @ResponseBody
    public Message checkSMS(String hashVal, String verifyCode,QQPhoneVo qqPhoneVo,HttpSession session){
        UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
        if(userPCVo==null){
            return Message.fail("请先登录");
        }
        String qr_status = "https://zhixin.kilcreapark.com/qq/submitVerifyCode";
        String result = httpURLConnectionPOST(qr_status,"hash="+hashVal+"&verifyCode="+verifyCode+"&appKey=c230eec160332603cdbfc76a2a5cf82e&nonce=diwangshuju666&currentTime="+time+"&signature="+sign+"&callbackUrl=http://192.168.3.18:8080/qqPhone/success");
        System.out.println(result);
        JSONObject jsonObject = JSON.parseObject(result);
        String code = jsonObject.get("code").toString();
        if(code.equals("0")){
            JSONObject jsonObject1 = jsonObject.getJSONObject("result");
            qqPhoneVo.setHashVal(hashVal);
            qqPhoneVo.setIsActive((byte)1);
            qqPhoneVo.setPcUserId(userPCVo.getId());
            qqPhoneService.save(qqPhoneVo);
            return Message.success(jsonObject.get("msg").toString());
        }else{
            return Message.fail(jsonObject.get("msg").toString());
        }
    }
    /**
     * 验证短信验证码，并拉取数据
     * @return
     */
    @RequestMapping("success")
    @ResponseBody
    public String success(@RequestBody String data){
        try{
            System.out.println(data+"======获取的参数===========");
            JSONObject jsonObject = JSON.parseObject(data);
            String code = jsonObject.get("status").toString();
            String qq = jsonObject.get("qq").toString();
            QQPhoneVo qqPhoneVo = qqPhoneService.getInfoByQQ(jsonObject.get("qq").toString());
            UserPCVo userPCVo = userPCService.getById(qqPhoneVo.getPcUserId());
            FixedPriceVo fixedPriceVo = fixedPriceService.getById(6l);
            ConsumeHistoryVo consumeHistoryVo = new ConsumeHistoryVo();
            if(code.equals("5")){
                String result = getData(qq);
                System.out.println(result+"====================");
                userPCVo.setJifen(userPCVo.getJifen()-fixedPriceVo.getPrice());
                userPCService.updateJiFen(userPCVo);
                qqPhoneVo.setData(result);
                consumeHistoryVo.setIsActive((byte)0);
                qqPhoneVo.setIsActive((byte)0);
                JSONObject jsonObject1 = JSON.parseObject(result);
                JSONObject jsonObject2 = jsonObject1.getJSONObject("result");
                qqPhoneVo.setNickName(jsonObject2.get("qqNickName").toString());
                qqPhoneVo.setPhone(jsonObject2.get("qqMobilePhone").toString());
                qqPhoneVo.setQq(jsonObject2.get("qq").toString());
                JSONArray jsonArray = jsonObject2.getJSONArray("teleInfo");
                List<QQPhoneListVo> qqPhoneListVos = new ArrayList<>();
                int cnt = jsonArray.size();
                if (cnt>0){
                    for(int i=0;i<cnt;i++){
                        JSONObject jsonObject3 = jsonArray.getJSONObject(i);
                        QQPhoneListVo qqPhoneListVo = new QQPhoneListVo();
                        try{
                            qqPhoneListVo.setTelename(jsonObject3.get("telename").toString());
                        }catch (Exception e){
                            qqPhoneListVo.setTelename(null);
                        }
                        try{
                            qqPhoneListVo.setTelephone(jsonObject3.get("telephone").toString());
                        }catch (Exception e){
                            qqPhoneListVo.setTelephone(null);
                        }
                        qqPhoneListVo.setCreateTime(new Date());
                        qqPhoneListVo.setPcUserId(userPCVo.getId());
                        qqPhoneListVo.setQqPhoneId(qqPhoneVo.getId());
                        qqPhoneListVos.add(qqPhoneListVo);
                    }
                    qqPhoneListService.saveList(qqPhoneListVos);
                }
            }else{
                qqPhoneVo.setIsActive((byte)1);
                consumeHistoryVo.setIsActive((byte)1);
            }
            consumeHistoryVo.setBcr(qqPhoneVo.getBcr());
            consumeHistoryVo.setAmount(fixedPriceVo.getPrice());
            consumeHistoryVo.setPcUserId(userPCVo.getId());
            consumeHistoryVo.setRemark("通讯录拉取");
            consumeHistoryVo.setCreateTime(new Date());
            consumeHistoryService.save(consumeHistoryVo);
            //修改数据
            qqPhoneService.update(qqPhoneVo);
            new MessageHandler().sendMessageToUser(userPCVo.getId()+"","通讯录采集成功");
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "success";
        }
     }
    /**
     * 验证短信验证码，并拉取数据
     * @return
     */
    public String getData(String qq){
        String url = "https://zhixin.kilcreapark.com/qq/getQQBookInfo";
        String result = httpURLConnectionPOST(url,"qq="+qq+"&appKey=c230eec160332603cdbfc76a2a5cf82e&nonce=diwangshuju666&currentTime="+time+"&signature="+sign);
        return result;
    }
    /**
     * 接口调用  POST
     */
    public static String httpURLConnectionPOST(String path, String param) {
        try {
            //传递参数
            URL url = new URL(path);
            // 将url 以 open方法返回的urlConnection  连接强转为HttpURLConnection连接  (标识一个url所引用的远程对象连接)
            // 此时cnnection只是为一个连接对象,待连接中
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 设置连接输出流为true,默认false (post 请求是以流的方式隐式的传递参数)
            connection.setConnectTimeout(30000);
            connection.setReadTimeout(30000);
            connection.setDoOutput(true);
            // 设置连接输入流为true
            connection.setDoInput(true);
            // 设置请求方式为post
            connection.setRequestMethod("POST");
            // post请求缓存设为false
            connection.setUseCaches(false);
            // 设置该HttpURLConnection实例是否自动执行重定向
            connection.setInstanceFollowRedirects(true);
            InetAddress address = InetAddress.getLocalHost();
            String ip = address.getHostAddress();//获得本机IP
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            // 建立连接 (请求未开始,直到connection.getInputStream()方法调用时才发起,以上各个参数设置需在此方法之前进行)
            connection.connect();
            // 创建输入输出流,用于往连接里面输出携带的参数,(输出内容为?后面的内容)
            DataOutputStream dataout = new DataOutputStream(connection.getOutputStream());
            // 格式 parm = aaa=111&bbb=222&ccc=333&ddd=444
            System.out.println("传递参数：" + param);
            // 将参数输出到连接
            dataout.writeBytes(param);
            // 输出完成后刷新并关闭流
            dataout.flush();
            dataout.close(); // 重要且易忽略步骤 (关闭流,切记!)
            //System.out.println(connection.getResponseCode());
            // 连接发起请求,处理服务器响应  (从连接获取到输入流并包装为bufferedReader)
            BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line;
            StringBuilder sb = new StringBuilder(); // 用来存储响应数据
            // 循环读取流,若不到结尾处
            while ((line = bf.readLine()) != null) {
                //sb.append(bf.readLine());
                sb.append(line).append(System.getProperty("line.separator"));
            }
            bf.close();    // 重要且易忽略步骤 (关闭流,切记!)
            connection.disconnect(); // 销毁连接
            return  sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String arg[]){
        try{
            String url = "https://zhixin.kilcreapark.com/qq/getQQCode";
            String result = httpURLConnectionPOST(url,"appKey=c230eec160332603cdbfc76a2a5cf82e&nonce=diwangshuju666&currentTime="+time+"&signature="+sign);
            JSONObject jsonObject = JSON.parseObject(result);
            JSONObject jsonObject1 = jsonObject.getJSONObject("result");
            String img_path = jsonObject1.get("imgUrl").toString();
            Base64ToImage.base64ToFile(img_path);
            String hash_val = jsonObject1.get("hash").toString();
            String qr_status = "https://zhixin.kilcreapark.com/qq/checkQRCodeStatus";
            System.out.println(QRCodeUtil.decode("d://a.jpg"));
            for(int i = 0;i<40;i++) {
                Thread.sleep(3000);
                String results = httpURLConnectionPOST(qr_status, "hash=" + hash_val + "&appKey=c230eec160332603cdbfc76a2a5cf82e&nonce=diwangshuju666&currentTime=" + time + "&signature=" + sign);
                System.out.println(results);
                JSONObject jsonObjects= JSON.parseObject(results);
                String code = jsonObject.get("code").toString();
                System.out.println(code + "========================");
                if (code.equals("0")) {
                    System.out.println(results);
                } else if (code.equals("20049")) {
                    System.out.println("二维码失效");
                } else if (code.equals("30000")) {
                    System.out.println(jsonObjects.get("msg").toString());
                } else if (code.equals("20045")) {
                    System.out.println(jsonObjects.get("msg").toString());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
