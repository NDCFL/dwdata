package com.dwsj.crawlUtil;

import com.alibaba.fastjson.JSONObject;
import com.dwsj.util.ExecPythonUtils;
import com.dwsj.util.HttpClientUtil;
import com.dwsj.util.StringUtil;
import com.dwsj.util.system.SysConf;
import com.dwsj.vo.Result;

import java.util.HashMap;
import java.util.Map;

public class JuJianYunUtil {
	
	public static void main(String[] args) {
		System.out.println(getJJYData("冉华松","421087199308108218"));
	}
	

	/**
	 * 调用脚本
	 * 
	 * @param name
	 * @param phone
	 * @return
	 * @throws Exception
	 */
	public static String getJJYData(String name, String phone) {
		String[] param = new String[] { name, phone };
		String jsonStr = ExecPythonUtils.execPythonShell(SysConf.PYTHON_LOCAL_PATH + "jujianyun/", "run.py", param);
		return jsonStr.replaceAll("./template", "http://www.jujianyun.com/template").replaceAll("./js",
				"http://www.jujianyun.com/js");
	}
	
	/**
	 * 居间云-调用py脚本
	 * @param name
	 * @param idCard
	 * @return
	 */
	public static String getJJYDataInApp(String name, String idCard) {
		String[] param = new String[]{name,idCard};
		if (StringUtil.hasEmptyValue(name,idCard)) {
			return Result.Err("姓名/身份证都不能为空").toString();
		}
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("pyPath", SysConf.PYTHON_LOCAL_PATH+"/app_jujianyun/");
		paramMap.put("pyName", "run.py");
		paramMap.put("params", param);
		String jsonStr = HttpClientUtil.httpPost("http://www.dwsjshuju.com/callpy/call", paramMap);
		jsonStr = jsonStr.replace("\r\n","");
		try {
			JSONObject json = JSONObject.parseObject(jsonStr);
			System.out.println(json.toString());
			return json.toString();
		} catch (Exception e){
			e.printStackTrace();
			return Result.Err("获取到的数据非json格式：\""+jsonStr+"\"").toString();
		}
	}

}
