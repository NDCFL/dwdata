package com.dwsj.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dwsj.controller.QQPhoneController;
import org.apache.shiro.crypto.hash.Md5Hash;

import java.io.*;

public class test {
    public static String readToString() {
        String encoding = "UTF-8";
        File file = new File("/upload/report/module.html");
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
    //获取当前的时间戳
    private static Long time = System.currentTimeMillis();
    //得到当前的标识
    private static String sign = new Md5Hash("appKey=c230eec160332603cdbfc76a2a5cf82e&appSecret=mLdZuffv&currentTime="+time+"&nonce=diwangshuju666").toString();

    public static void main(String arg[]){
        try{
            String url = "https://zhixin.kilcreapark.com/qq/getQQCode";
            String result = QQPhoneController.httpURLConnectionPOST(url,"appKey=c230eec160332603cdbfc76a2a5cf82e&nonce=diwangshuju666&currentTime="+time+"&signature="+sign);
            JSONObject jsonObject = JSON.parseObject(result);
            JSONObject jsonObject1 = jsonObject.getJSONObject("result");
            String img_path = jsonObject1.get("imgUrl").toString();
            Base64ToImage.base64ToFile(img_path);
            String hash_val = jsonObject1.get("hash").toString();
            String qr_status = "https://zhixin.kilcreapark.com/qq/checkQRCodeStatus";
            System.out.println(QRCodeUtil.decode("d://a.jpg"));
            for(int i = 0;i<40;i++) {
                Thread.sleep(3000);
                String results = QQPhoneController.httpURLConnectionPOST(qr_status, "hash=" + hash_val + "&appKey=c230eec160332603cdbfc76a2a5cf82e&nonce=diwangshuju666&currentTime=" + time + "&signature=" + sign);
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
