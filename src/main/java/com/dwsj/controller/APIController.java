package com.dwsj.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dwsj.common.Message;
import com.dwsj.service.APIUserItemService;
import com.dwsj.service.APIUserService;
import com.dwsj.vo.*;
import com.dwsj.yongxun.JinJieDaoUtil;
import com.dwsj.yongxun.YongXunUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Date;

@Controller
@RequestMapping("api")
public class APIController {

    @Resource
    private APIUserService apiUserService;
    @Resource
    private APIUserItemService apiUserItemService;
    @RequestMapping("getInfoByPhone")
    @ResponseBody
    public APIUserItemVo getInfoByPhone(String phone){
        return apiUserItemService.getInfoByPhone(phone);
    }
    @RequestMapping("task")
    @ResponseBody
    public Message checkUser(HttpServletRequest request, APIUserVo a){
        try{
            APIUserVo apiUserVo = new APIUserVo();
            String code = a.getCode();
            String key = a.getKey();
            if(code==null && code.equals("") && key==null && key.equals("")){
                return Message.fail("签名或密钥有误");
            }else{
                apiUserVo.setCode(code);
                apiUserVo.setKey(key);
                APIUserVo apiUserVo1 = apiUserService.checkUser(apiUserVo);
                if(apiUserVo1.getMoney()-1<0){
                    return Message.fail("余额不足，请充值");
                }
                if(apiUserVo1==null){
                    return Message.fail("签名或密钥有误");
                }else{
                    String result = httpURLConnectionPOST("https://api.yongxunzhengxin.com/qqBook/v1/task","username="+a.getPhone()+"&loginType=mb&password="+new BASE64Encoder().encode(a.getPassword().getBytes()));
                    System.out.println(result+"===============");
                    JSONObject jsonObject = JSON.parseObject(result);
                    System.out.println(jsonObject.get("taskId"));
                    for(int i=0;i<10;i++){
                        Thread.sleep(1000);
                        String result1 = httpURLConectionGET("https://api.yongxunzhengxin.com/qqBook/v1/task/"+jsonObject.get("taskId")+"/status");
                        JSONObject jsonObject1 = JSON.parseObject(result1);
                        String code1 = jsonObject1.get("code").toString();
                        System.out.println(result1+"=============");
                        if(code1.equals("0002")){
                            return Message.success(result);
                        }else if(code1.equals("0001")){

                        }else{
                            return Message.fail(jsonObject1.get("msg").toString());
                        }
                    }
                }
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return Message.fail("请求失败");
        }
    }
    @RequestMapping("input")
    @ResponseBody
    public Message input(String input,String task,String code,String key,String phone) throws Exception{
        APIUserVo apiUserVo = new APIUserVo();
        apiUserVo.setCode(code);
        apiUserVo.setKey(key);
        if(code==null && code.equals("") && key==null && key.equals("")){
            return Message.fail("签名或密钥有误");
        }else{
            String result = httpURLConnectionPOST("https://api.yongxunzhengxin.com/qqBook/v1/task/"+task+"/input","input="+input);
            JSONObject jsonObject = JSON.parseObject(result);
            String codes = jsonObject.get("code").toString();
            if(codes.equals("0011")){
                APIUserVo apiUserVo1 = apiUserService.checkUser(apiUserVo);
                for(int i=0;i<30;i++){
                    Thread.sleep(2000);
                    String result1 = httpURLConectionGET("https://api.yongxunzhengxin.com/qqBook/v1/task/"+task+"/data");
                    System.out.println(result1+"-=-=-=-=-=-=");
                    JSONObject jsonObject1 = JSON.parseObject(result1);
                    String codes1 = jsonObject1.get("code").toString();
                    if(codes1.equals("0000")){
                        APIUserItemVo apiUserItemVo = new APIUserItemVo();
                        apiUserItemVo.setContent("通讯录采集");
                        apiUserItemVo.setCreateTime(new Date());
                        apiUserItemVo.setIsActive((byte)0);
                        apiUserItemVo.setTaskId(task);
                        apiUserItemVo.setMoney(1);
                        apiUserItemVo.setUserId(apiUserVo1.getId());
                        apiUserItemVo.setData(result1);
                        apiUserItemVo.setPhone(phone);
                        //新增记录
                        apiUserItemService.save(apiUserItemVo);
                        apiUserVo.setId(apiUserVo1.getId());
                        apiUserVo.setMoney(1);
                        apiUserService.reduceMoney(apiUserVo);
                        return Message.success(result1);
                    }else{
                        return Message.fail(jsonObject1.get("msg").toString());
                    }
                }
            }else{
                return Message.fail(jsonObject.get("msg").toString());
            }
        }
        return Message.success("ok");
    }
    public static final String GET_URL = "http://vip.98zhengxin.com/loan/lend1";
    // 妙兜测试接口
    public static final String POST_URL = "https://api.yongxunzhengxin.com/risk/v1/creditRadar";
    /**
     * 接口调用 GET
     */
    public static String httpURLConectionGET(String path) {
        try {
            URL url = new URL(path);    //把字符串转换为URL请求地址
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();// 打开连接
            connection.setReadTimeout(60000);
            connection.setConnectTimeout(60000);
            connection.setRequestProperty("authorization-key", "1121688520430811");
            Long time = System.currentTimeMillis();//获取当前的时间戳
            connection.setRequestProperty("authorization-timestamp", time+"");
            String sign = sha1Hex("1121688520430811"+time+"DtMbOuHeeDSOUZPy2nY53HnDHPvoeB91");
            System.out.println(time+"===="+sign);
            connection.setRequestProperty("authorization-sign", sign);
            connection.connect();// 连接会话
            // 获取输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {// 循环读取流
                sb.append(line);
            }
            br.close();// 关闭流
            connection.disconnect();// 断开连接
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"code\":500,\"msg\":\"请求失败\",\"data\":\"\"}";
        }
    }

    /**
     * 接口调用  POST
     */
    public static String httpURLConnectionPOST(String path,String param) {
        try {
            //传递参数

            URL url = new URL(path);
            // 将url 以 open方法返回的urlConnection  连接强转为HttpURLConnection连接  (标识一个url所引用的远程对象连接)
            // 此时cnnection只是为一个连接对象,待连接中
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(60000);
            connection.setConnectTimeout(60000);
            // 设置连接输出流为true,默认false (post 请求是以流的方式隐式的传递参数)
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
            connection.setRequestProperty("ip","58.152.48.48:443");  //请求来源IP
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            connection.setRequestProperty("authorization-key", "1121688520430811");
            Long time = System.currentTimeMillis();//获取当前的时间戳
            connection.setRequestProperty("authorization-timestamp", time+"");
            String sign = sha1Hex("1121688520430811"+time+"DtMbOuHeeDSOUZPy2nY53HnDHPvoeB91");
            System.out.println(time+"===="+sign);
            connection.setRequestProperty("authorization-sign", sign);
            // 建立连接 (请求未开始,直到connection.getInputStream()方法调用时才发起,以上各个参数设置需在此方法之前进行)
            connection.connect();
            // 创建输入输出流,用于往连接里面输出携带的参数,(输出内容为?后面的内容)
            DataOutputStream dataout = new DataOutputStream(connection.getOutputStream());
            // 格式 parm = aaa=111&bbb=222&ccc=333&ddd=444
            String parm = param;
            System.out.println("传递参数：" + parm);
            // 将参数输出到连接
            dataout.writeBytes(parm);
            // 输出完成后刷新并关闭流
            dataout.flush();
            dataout.close(); // 重要且易忽略步骤 (关闭流,切记!)
            //System.out.println(connection.getResponseCode());
            // 连接发起请求,处理服务器响应  (从连接获取到输入流并包装为bufferedReader)
            BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            StringBuilder sb = new StringBuilder(); // 用来存储响应数据

            // 循环读取流,若不到结尾处
            while ((line = bf.readLine()) != null) {
                //sb.append(bf.readLine());
                sb.append(line).append(System.getProperty("line.separator"));
            }
            bf.close();    // 重要且易忽略步骤 (关闭流,切记!)
            connection.disconnect(); // 销毁连接
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"code\":500,\"msg\":\"请求有误\",\"data\":\"\"}";
        }
    }
    private static String sha1Hex(String data) {
        if (null == data || 0 == data.length()) {
            return null;
        }
        char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(data.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] buf = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    @RequestMapping("updateBaseInfoPage")
    public String updateBaseInfoPage(){
        return "user/updateBaseInfo";
    }
    @RequestMapping("getApiInfo")
    @ResponseBody
    public APIUserVo getApiInfo(HttpSession session){
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        return apiUserService.getById(userVo.getId());
    }
    @RequestMapping("updateBaseInfo")
    @ResponseBody
    public Message updateBaseInfo(APIUserVo apiUserVo,HttpSession session){
        try{
            UserVo userVo = (UserVo) session.getAttribute("userVo");
            apiUserVo.setId(userVo.getId());
            apiUserService.update(apiUserVo);
            return Message.success("修改成功");
        }catch (Exception e){
            e.printStackTrace();
            return  Message.fail("修改失败");
        }
    }
}
