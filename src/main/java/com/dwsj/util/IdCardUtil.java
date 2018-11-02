package com.dwsj.util;

import com.alibaba.fastjson.JSONObject;
import com.dwsj.util.system.SysConf;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * 身份证工具类
 * @author YuDong
 *
 */
public class IdCardUtil {
	
	
	public static void main(String[] args) {
		IdCardUtil a = new IdCardUtil();
		//a.resolveIdCardInfo("421002199312082934");
		/*String idCard = "421002199312082934";
		System.out.println(idCard.substring(6,10));
		System.out.println(idCard.substring(10,12));
		System.out.println(idCard.substring(12,14));
		Date date = new Date(Integer.parseInt(idCard.substring(6,10))-1900,Integer.parseInt(idCard.substring(10,12))-1,Integer.parseInt(idCard.substring(12,14)));
		System.out.println(DateUtil.parse(date, "yyyy-MM-dd"));*/
		
		//a.checkIsSelfAndReturnHeadImg("刘少怪","421087199408258213");
		a.checkIsSelfPhone("a东", "18612345213");
	}
	
	
	/**
	 * 校验是否为本人并返回base64图片
	 * @return
	 */
	public static String checkIsSelfAndReturnHeadImg(String name,String idCard) {
		if (StringUtils.isEmpty(name) || StringUtils.isEmpty(idCard)) {
			return null;
		}
		String url = "http://vip.98zhengxin.com/idcard/card1?";
		String param = null;
		try {
			param = "name="+URLEncoder.encode(name.trim(),"UTF-8")+"&idcard="+idCard.trim();
			String result = sendGet(param, url, SysConf.CWX_VIP_KEY,SysConf.CWX_VIP_CODE);
			JSONObject json = JSONObject.parseObject(result);
			if (json.getString("retCod").equals("1000")) {
				JSONObject json2 = json.getJSONObject("data"); 
				if (json2.getString("status").equals("1")) {//1一致
					return json2.getString("photo");
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 校验是否为本人
	 * @return
	 */
	public static Boolean checkIsSelf(String name,String idCard) {
		if (StringUtils.isEmpty(name) || StringUtils.isEmpty(idCard)) {
			return null;
		}
		String url = "http://vip.98zhengxin.com/idcard/card1?";
		String param = null;
		try {
			param = "name="+URLEncoder.encode(name.trim(),"UTF-8")+"&idcard="+idCard.trim();
			String resp = sendGet(param, url,SysConf.CWX_VIP_KEY,SysConf.CWX_VIP_CODE);
			JSONObject json = JSONObject.parseObject(resp);
			if (json.getString("retCod").equals("1000")) {
				JSONObject json2 = json.getJSONObject("data"); 
				if (json2.getString("status").equals("1")) {//1一致
					return true;
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return false;
	}

	

	/**
	 * 校验是否为本人手机号
	 * @return
	 */
	public static Boolean checkIsSelfPhone(String name,String phone) {
		if (StringUtils.isEmpty(name) || StringUtils.isEmpty(phone)) {
			return null;
		}
		String url = "http://vip.98zhengxin.com/mobile/mobilesimple1?";
		String param = null;
		try {
			param = "name="+URLEncoder.encode(name.trim(),"UTF-8")+"&mobile="+phone.trim();
			String phoneResp = sendGet(param, url,SysConf.CWX_VIP_KEY,SysConf.CWX_VIP_CODE);
			JSONObject phoneJson = JSONObject.parseObject(phoneResp);
			if (phoneJson.getString("retCod").equals("1000")) {
				JSONObject phoneJson2 = phoneJson.getJSONObject("data"); 
				if (phoneJson2.getString("status").equals("1")) {//1一致
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 身份证解析
	 */
	public static String resolveIdCardInfo(String idCard) {
		if (StringUtils.isEmpty(idCard)) {
			return null;
		}
		String url = "http://fly.98zhengxin.com/API/census?";
		String param = "idcard="+idCard.trim();
		try {
			return sendGet(param, url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	/**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    private static String sendGet(String param,String url) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + param;
        	
            URL realUrl = new URL(urlNameString);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("COMPANY_KEY", SysConf.CWX_KEY);
            connection.setRequestProperty("COMPANY_CODE", SysConf.CWX_CODE);
            connection.connect();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    
    /**
     * 向指定URL发送GET方法的请求(更改key之后的请求，重载)
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    private static String sendGet(String param,String url,String key,String code) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + param;
        	
            URL realUrl = new URL(urlNameString);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("COMPANY_KEY", key);
            connection.setRequestProperty("COMPANY_CODE", code);
            connection.connect();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
}
