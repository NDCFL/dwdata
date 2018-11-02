package com.dwsj.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dwsj.common.Message;
import com.dwsj.vo.FileVo;
import com.dwsj.vo.ImgVo;
import com.dwsj.vo.UserPCVo;
import com.dwsj.vo.UserVo;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("file")
@Controller
public class FileUpController {
    @RequestMapping("up")
    @ResponseBody
    public FileVo fileUp(MultipartFile file, HttpServletRequest request,HttpSession session) {
        UserPCVo userPCVo = (UserPCVo) session.getAttribute("userPCVo");
        UserVo userVo = (UserVo) session.getAttribute("userVo");
        if(userPCVo==null && userVo==null){
            return null;
        }
        FileVo fileVo = new FileVo();
        try {
            //使用原始文件名称
            String fileName = file.getOriginalFilename();
            //重新格式化文件名称
            //String fileName = getFileName(file.getOriginalFilename());
            String path = request.getSession().getServletContext().getRealPath("upload");
            File dir = new File(path, fileName);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            file.transferTo(dir);
            Map<String, String> data = new HashMap<String, String>();
            data.put("src", "/upload/" + fileName);
            fileVo.setData(data);
            System.out.println("保存到数据库的图片地址:/upload/" + fileName);
            fileVo.setCode(0);
        } catch (Exception e) {
            e.printStackTrace();
            fileVo.setCode(1);
        }
        fileVo.setMsg("上传成功!");
        return fileVo;
    }

    /**
     * 把base64转出成图片
     *
     * @param imgBase64Data
     * @param request
     * @return
     */
    @RequestMapping("base64Up")
    @ResponseBody
    public Message base64Up(String imgBase64Data, HttpServletRequest request) {
        try {
            if (imgBase64Data == null || "".equals(imgBase64Data)) {
                return Message.fail("数据为空");
            }
            //去除前缀
            imgBase64Data.replace("data:image/png;base64,", "");
            System.out.println(imgBase64Data);
            String projectPath = request.getSession().getServletContext().getRealPath("/");
            String imgFilePath = "/upload/userReport/";
            File uploadPathFile = new File(projectPath + imgFilePath);
            //创建父类文件
            if (!uploadPathFile.exists() && !uploadPathFile.isDirectory()) {
                uploadPathFile.mkdirs();
            }
            File imgeFile = new File(projectPath + imgFilePath, new Date().getTime() + ".png");
            if (!imgeFile.exists()) {
                imgeFile.createNewFile();
            }
            //对base64进行解码
            byte[] result = decodeBase64(imgBase64Data);
            //使用Apache提供的工具类将图片写到指定路径下
            FileUtils.writeByteArrayToFile(imgeFile, result);
            String path = imgFilePath + imgeFile.getName();
            return Message.success(path);
        } catch (Exception e) {
            e.printStackTrace();
            return Message.fail("保存失败");
        }
    }

    /**
     * Base64解码.
     */
    public static byte[] decodeBase64(String input) {
        return Base64.decodeBase64(input.getBytes());
    }
}