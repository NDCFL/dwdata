package com.dwsj.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import sun.misc.BASE64Decoder;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class GetPhotoUtil {
    public static String get(String link) throws IOException {
        URL url = new URL(link);
        InputStream stream = null;
        HttpURLConnection connection = null;
        String result = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(10000);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("COMPANY_KEY", "00351805291807267K6E5J6R4T0P");
            connection.setRequestProperty("COMPANY_CODE", "68489950b04fcd3fbb2be06a9ecee6b1");
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

    public static String imgUpload(String name,String idcard,HttpServletRequest request){
        try {
            String imgdata = get("http://vip.98zhengxin.com/idcard/card1?name=" + URLEncoder.encode(name, "utf-8") + "&idcard="+idcard);
            JSONObject accessorJSON = JSON.parseObject(imgdata);
            JSONObject accessorJSON1 = JSON.parseObject(accessorJSON.get("data").toString());
            String imgBase64Data =accessorJSON1.get("photo").toString();
            if(imgBase64Data == null || "".equals(imgBase64Data)){
                return  "0";
            }
            String projectPath = request.getSession().getServletContext().getRealPath("/");
            String imgFilePath ="/upload/pcuser/";
            File uploadPathFile = new File(projectPath+imgFilePath);
            //创建父类文件
            if(!uploadPathFile.exists() && !uploadPathFile.isDirectory()){
                uploadPathFile.mkdirs();
            }
            File imgeFile = new File(projectPath+imgFilePath,new Date().getTime()+".jpg");
            if(!imgeFile.exists()){
                imgeFile.createNewFile();
            }
            //对base64进行解码
            byte[] result = decodeBase64(imgBase64Data);
            //使用Apache提供的工具类将图片写到指定路径下
            FileUtils.writeByteArrayToFile(imgeFile,result);
            String path=imgFilePath+imgeFile.getName();
            return path;
        }catch (Exception e){
            e.printStackTrace();
            return  "1";
        }
    }
    /**
     * Base64解码.
     */
    public static byte[] decodeBase64(String input) {
        return Base64.decodeBase64(input.getBytes());
    }
    public static void base64ToFile(String base64)throws Exception{
        BASE64Decoder decoder = new sun.misc.BASE64Decoder();
        byte[] bytes1 = decoder.decodeBuffer(base64);
        OutputStream out=new FileOutputStream("d://a.jpg");
        out.write(bytes1);
        out.flush();
        out.close();
    }
    //获取IP地址对应的区域
    public static void main(String arg[]) {
        try {
            String result = get("http://vip.98zhengxin.com/idcard/card1?name=" + URLEncoder.encode("杨宗波", "utf-8") + "&idcard=220822199507260412");
            System.out.println(result);
            JSONObject accessorJSON = JSON.parseObject(result);
            JSONObject accessorJSON1 = JSON.parseObject(accessorJSON.get("data").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
