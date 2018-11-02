package com.dwsj.crawlUtil;

import com.alibaba.fastjson.JSONObject;
import com.dwsj.common.Message;
import com.dwsj.util.ExecPythonUtils;
import com.dwsj.util.GsonUtils;
import com.dwsj.util.HttpClientUtil;
import com.dwsj.util.StringUtil;
import com.dwsj.util.system.SysConf;
import com.dwsj.vo.Result;

import java.util.HashMap;
import java.util.Map;

/**
 * 无忧交易宝
 * @author cgw
 * @date 2018年6月1日
 */
public class WyjyBaoUtil {
	
	public static String getWyjyBaoData(String name, String idCard, String phone, String cookie) {
		
		String[] param = new String[]{name,idCard,phone,cookie};
		if (StringUtil.hasEmptyValue(name,idCard,phone)) {
			return Message.fail("姓名/身份证/手机号都不能为空").toString();
		}
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("pyPath", SysConf.PYTHON_LOCAL_PATH+"/wujiaoyibao/");
		paramMap.put("pyName", "run.py");
		paramMap.put("params", param);
		String jsonStr = HttpClientUtil.httpPost("http://www.dwsjshuju.com/callpy/call", paramMap);
		jsonStr = jsonStr.replace("\r\n","");
		try {
			JSONObject json = JSONObject.parseObject(jsonStr);
			System.out.println(json.toString());
			return GsonUtils.translateToJson(json);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("不是个标准json格式:"+jsonStr);
			return Result.Err("不是个标准json字符串").toString();
		}
	}
	public static String getWyjyBaoData1(String name, String idCard, String phone, String cookie) {
		String[] param = new String[] { name, idCard,phone,cookie };
		String jsonStr = ExecPythonUtils.execPythonShell(SysConf.PYTHON_LOCAL_PATH + "wujiaoyibao/", "run.py", param);
		return StringUtil.ascii2native(jsonStr);
	}
	public static void main(String arg[]){
		getWyjyBaoData1("刘少怪","421087199408258213","17786506171","0f1b42a7fc28471492d1cadb77ec1c40");
	}
}
