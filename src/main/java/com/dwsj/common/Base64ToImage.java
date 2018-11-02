package com.dwsj.common;
import java.io.*;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;

public class Base64ToImage{
    public static String imgUpload(String imgBase64Data,HttpServletRequest request){
        try {
            if(imgBase64Data == null || "".equals(imgBase64Data)){
                return  null;
            }
            String projectPath = request.getSession().getServletContext().getRealPath("/");
            String imgFilePath ="/upload/qqphone/";
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
            return  null;
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
}
