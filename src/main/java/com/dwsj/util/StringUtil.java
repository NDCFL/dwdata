package com.dwsj.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.util.StringUtils;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author cgw
 */
public final class StringUtil {

	/**
	 * 判断str是否是空串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.trim().equals("");
	}
	
	/**
	 * 判断str是否是空串("null"字符串也算作空) 
	 * @param str
	 * @return
	 */
	public static boolean isEmptyCareStrNull(String str) {
		return str == null || str.trim().equals("") || str.equalsIgnoreCase("null");
	}

	/**
	 * 判断是否有空串
	 * 
	 * @return
	 */
	public static boolean hasEmptyValue(String... strings) {

		if (strings == null || strings.length == 0) {
			return true;
		}

		for (String str : strings) {
			if (isEmpty(str)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 处理GET请求中有中文参数乱码的问题
	 */
	public static String dealEncode(String str) {
		try {
			if (!StringUtils.isEmpty(str)) {
				byte[] bytes = str.getBytes("ISO-8859-1");
				str = new String(bytes, "utf-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return str;
	}

	/**
	 * 字符串异或
	 * 
	 * @param strs
	 *            e.g: str1,str2,...
	 * @return
	 */
	public static String xor(String... strs) {

		if (strs == null || strs.length < 1) {
			return null;
		}

		int minAscII = 33;
		int maxAscII = 122;

		String str1 = strs[0];
		for (int i = 1; i < strs.length; i++) {
			String str2 = strs[i];
			if (str2 == null)
				continue;
			String minStr = null;
			String maxStr = null;
			if (str2.length() > str1.length()) {
				minStr = str1;
				maxStr = str2;
			} else {
				minStr = str2;
				maxStr = str1;
			}
			char[] temp = new char[maxStr.length()];

			for (int j = 0; j < minStr.length(); j++) {
				int ch = minStr.charAt(j) ^ maxStr.charAt(j);

				if (ch < minAscII) {
					ch = ch + minAscII;
				} else if (ch > maxAscII) {
					ch = maxAscII - ch;
				}

				temp[j] = (char) ch;
			}

			for (int z = minStr.length(); z < maxStr.length(); z++) {
				temp[z] = maxStr.charAt(z);
			}

			str1 = new String(temp);
		}
		return str1;
	}

	/**
	 * 是否是电子邮件
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		if (email == null)
			return false;
		String reg = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$";
		return email.matches(reg);
	}

	/**
	 * 是否是手机号码
	 * 
	 * @param phone
	 * @return
	 */
	public static boolean isPhone(String phone) {
		if (phone == null)
			return false;
		String reg = "^(\\+){0,1}(0|86|17951){0,1}(\\s){0,1}(13[0-9]|15[012356789]|16[0-9]|17[0-9]|18[0-9]|19[0-9]|14[57])[0-9]{8}$";
		return phone.matches(reg);
	}

	/**
	 * 是否是身份证
	 * 
	 * @param idCard
	 * @return
	 */
	public static boolean isIDCard(String idCard) {
		if (idCard == null)
			return false;
		String idcard_15 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
		String idcard_18 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|x|X)$";
		return Pattern.matches(idcard_15, idCard) || Pattern.matches(idcard_18, idCard);
	}

	/**
	 * @Title: subStr
	 * @Description: 富文本编辑器只获取第一个p里面的内容
	 * @param @param
	 *            string 截取的内容
	 * @return String
	 * @author 彭河川
	 * @date 2017年8月7日 下午5:54:36
	 */
	public static String findFirstPFromStr(String string) {
		String result = "";
		String regex = "<p.*?>(.*?)</p>";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(string);
		while (m.find()) {
			result = m.group(1);
			break;
		}
		if (result.length() > 50) {
			result = result.substring(0, 50) + "......";
		}
		return result;
	}

	/**
	 * 隐藏手机号部分数码
	 * 
	 * @param phone
	 * @return null if phone is not a phone number
	 */
	public static String hidePhone(String phone) {
		if (!isPhone(phone))
			return null;
		return phone.substring(0, 3) + "****" + phone.substring(phone.length()-4, phone.length());
	}
	/**
	 * 隐藏身份证号部分数码
	 * 
	 * @return null if idCard is not an IDCard
	 */
	public static String hideIDCard(String idCard) {
		if (!isIDCard(idCard))
			return null;
		String hided = idCard.substring(0, 3) + "**********" + idCard.substring(idCard.length()-4, idCard.length());
		return hided;
	}
	
	/**
	 * 隐藏真实姓名部分字符
	 * 
	 * @param realName
	 * @return
	 */
	public static String hideRealName(String realName) {
		if (isEmpty(realName))
			return null;
		return "**" + realName.substring(realName.length() - 1, realName.length());
	}

	/**
	 * jsonStr2JSON JSON字符串转JSON对象
	 */
	public static List<JSONObject> jsonStr2JSONArray(String jsonStr) {
		List<JSONObject> list = new ArrayList<JSONObject>();
		if (isEmpty(jsonStr)) {
			return list;
		}
		JSONArray array = JSONArray.parseArray(jsonStr);
		for(int i=0;i<array.size();i++) {
			list.add(array.getJSONObject(i));
		}
		return list;
	}
	
	
	/**
	 * 判断字符串是否是boolean字面量
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBoolean(String str) {
		if (isEmpty(str))
			return false;
		return str.equalsIgnoreCase("true") || str.equalsIgnoreCase("false") || str.equals("1") || str.equals("0");
	}

	/**
	 * 如果是boolean类型则返回封装类型,否则原样返回
	 * 
	 * @param str
	 * @return
	 */
	public static Object returnBooleanIfIsBooleanType(String str) {

		if (isBoolean(str)) {
			if (str.equalsIgnoreCase("true") || str.equals("1")) {
				return true;
			} else {
				return false;
			}
		}

		return str;
	}

	/**
	 * 判断base64Str是否只是base64头部
	 * 
	 * @param base64Str
	 * @return
	 */
	public static boolean isBase64Header(String base64Str) {
		// base64Str : data:image/jpeg;base64, /9j/4AAQS....
		// header : data:image/jpeg;base64
		String reg = "^data:(.*?);base64$";
		return base64Str == null ? false : base64Str.matches(reg);
	}

	/**
	 * 判断base64Str是否包含base64头部
	 * 
	 * @param base64Str
	 * @return
	 */
	public static boolean containBase64Header(String base64Str) {
		String reg = "^data:(.*?);base64(.*)";
		return base64Str == null ? false : base64Str.matches(reg);
	}

	/**
	 * 对价格格式化，去掉小数点后面多余的0
	 */
	public static String fmtBigdecimal(Object bigDecimal) {
		BigDecimal aa = new BigDecimal(bigDecimal.toString());
		NumberFormat nf = NumberFormat.getInstance();
		String result = "0";
		try {
			result = nf.format(aa).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 计算折扣
	 *
	 * @return
	 */
	public static String discounted(Object a, Object b) {
		BigDecimal aa = new BigDecimal(a.toString());
		BigDecimal bb = new BigDecimal(b.toString());
		BigDecimal d;
		try {
			BigDecimal c = aa.divide(bb, 3, BigDecimal.ROUND_HALF_EVEN);
			d = c.multiply(new BigDecimal(10));
		} catch (Exception e) {
			e.printStackTrace();
			return "0.0";
		}
		return fmtBigdecimal(d);
	}

	/**
	 * 反斜杠转成正斜杠
	 */
	public static String reverseSymbol(String str) {
		if (!StringUtils.isEmpty(str)) {
			System.out.println(str.replace("\\", "/"));
			return str.replace("\\", "/");
		} else {
			return "";
		}
	}

	/**
	 * 将搜索出来的结果中的商品多张商品图片做切割
	 * 
	 * @param str
	 * @return
	 */
	public static String[] switchProStrToArray(String str) {
		if (!StringUtils.isEmpty(str)) {
			String[] temp = str.split("@@");
			String[] arr = new String[temp.length];
			for (int i = 0; i < temp.length; i++) {
				arr[i] = temp[i];
			}
			return arr;
		} else {
			return null;
		}
	}

	/**
	 * 将Long[] 切割成空格分割的字符串
	 * 
	 * @param arr
	 * @return
	 */
	public static String spiltLongArray(Long[] arr) {
		if (arr != null && arr.length > 0) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < arr.length; i++) {
				sb.append(arr[i]+" ");
			}
			return sb.toString();
		}else{
			return "";
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static Map<String,Object> parseXmlStringToMap(String strxml) {
		if (null == strxml || "".equals(strxml)) {
			return null;
		}

		HashMap<String,Object> m = new HashMap<String,Object>();
		StringReader sr = new StringReader(strxml);
		InputSource is = new InputSource(sr);
		Document doc = null;
		try {
			doc = (new SAXBuilder()).build(is);
		} catch (Exception e1) {
			e1.printStackTrace();
			return Collections.emptyMap();
		} 
		Element root = doc.getRootElement();
		List list = root.getChildren();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			Element e = (Element) it.next();
			String k = e.getName();
			String v = "";
			List children = e.getChildren();
			if (children.isEmpty()) {
				v = e.getTextNormalize();
			} else {
				v = getXmlChildrenText(children);
			}

			m.put(k, v);
		}

		return m;
	}
	
	/**
	 * 获取子结点的xml
	 * 
	 * @param children
	 * @return String
	 */
	@SuppressWarnings("rawtypes")
	public static String getXmlChildrenText(List children) {
		StringBuffer sb = new StringBuffer();
		if (!children.isEmpty()) {
			Iterator it = children.iterator();
			while (it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List list = e.getChildren();
				sb.append("<" + name + ">");
				if (!list.isEmpty()) {
					sb.append(getXmlChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}

		return sb.toString();
	}
	
	/**
	 * 获取当前毫秒数的string类型
	 */
	public static synchronized String currentTimeMillisStr() {
		String str = String.valueOf(System.currentTimeMillis());
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	/**
	 * 给过长的字符串加省略号
	 * @param str
	 * @return
	 */
	public static String strOmitted(String str){
		if (!StringUtils.isEmpty(str)) {
			if (str.length() > 10) {
				return str.substring(0,5)+"...";
			}else{
				return str;
			}
		}
		return "";
	}
	
	
	/**
	 * 处理视频广告的的内容json字符串
	 */
	public static JSONArray dealAdsJSONStr(String str) {
		if (!StringUtils.isEmpty(str)) {
			JSONArray array = null;
			try {
				array = JSONArray.parseArray(str);
				/*JSONObject obj = null;
				for(int i=0;i<array.size();i++) {
					obj = array.getJSONObject(i);
				}*/
				return array;
			} catch (Exception e) {
				e.printStackTrace();
				return new JSONArray();
			}
		}
		return new JSONArray();
	}
	
	/**
	 * 获取字符编码
	 * @param str
	 * @return
	 */
	public static String getEncoding(String str) {  
	    String encode[] = new String[]{  
	            "UTF-8",  
	            "ISO-8859-1",  
	            "GBK",  
	            "GB2312",  
	            "GB18030",  
	            "Big5",  
	            "Unicode",  
	            "ASCII"  
	    };  
	    for (int i = 0; i < encode.length; i++){  
	        try {  
	            if (str.equals(new String(str.getBytes(encode[i]), encode[i]))) {  
	                return encode[i];  
	            }  
	        } catch (Exception ex) {  
	        }  
	    }  
	      
	    return "";  
	}  
	
	
	/**
	 * 将字符串中含有unicode编码的文字转成中文
	 * @param asciicode
	 * @return
	 */
	public static String ascii2native ( String asciicode ){
        String[] asciis = asciicode.split ("\\\\u");
        String nativeValue = asciis[0];
        try {
            for ( int i = 1; i < asciis.length; i++ ){
                String code = asciis[i];
                nativeValue += (char) Integer.parseInt (code.substring (0, 4), 16);
                if (code.length () > 4){
                    nativeValue += code.substring (4, code.length ());
                }
            }
        }
        catch (NumberFormatException e) {
            return asciicode;
        }
        return nativeValue;
    }
	
	public static String subString(String src,String begin,String end) {
		int b = src.indexOf(begin);
		if (isEmpty(end)) {
			return src.substring(b);
		}
		int e = src.indexOf(end)+end.length();
		return src.substring(b, e);
	}
	
//	public static void main(String[] args) {
//		System.out.println(subString("aabbccddeeff","bb","ee"));
//	}
}
