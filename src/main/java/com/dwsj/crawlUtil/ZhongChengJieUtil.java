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
 * 中诚介
 * @author MC-l
 * @date 2018年5月23日
 */
public class ZhongChengJieUtil {

	public static String getZCJData(String name,String idCard,String cookie){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		if (StringUtil.hasEmptyValue(name,idCard)) {
			return Result.Err("姓名/身份证都不能为空").toString();
		}
		if (StringUtil.isEmpty(cookie)) {
			return Result.Err("cookie为空").toString();
		}
		String[] params = new String[]{name,idCard,cookie};
		paramMap.put("pyPath", SysConf.PYTHON_LOCAL_PATH+"/zhongchengjie/");
		paramMap.put("pyName", "run.py");
		paramMap.put("params", params);
		String jsonStr = HttpClientUtil.httpPost("http://www.dwsjshuju.com/callpy/call", paramMap);
		jsonStr = jsonStr.replace("\r\n","");
		System.out.println(jsonStr); // {"code":"1011","message":"登录超时","data":""}
		try {
			JSONObject json = JSONObject.parseObject(jsonStr);
			System.out.println(json.toString());
			return GsonUtils.translateToJson(json);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("不是个标准json格式:"+jsonStr);
			return Message.fail("不是个标准json字符串").toString();
		}
	}
	/**
	 * 获取中承接json
	 * @param name
	 * @return
	 */
	public static String getZcjData(String name,String idCard,String cookie) {
		String[] param = new String[] { name, idCard,cookie };
		String jsonStr = ExecPythonUtils.execPythonShell(SysConf.PYTHON_LOCAL_PATH + "zhongchengjie/", "run.py", param);
		//String jsonStr = ExecPythonUtils.execPythonShell("F:/python/dwsjshuju/cdxinqu/", "run.py", param);
		return StringUtil.ascii2native(jsonStr);
	}
	public static void main(String[] args) throws Exception {
		String zcjData = getZcjData("冉华松","421087199308108218","1.BwAHB1VSB1cGUA9VBxUFCVhTBVUBCAJXBwxZDFFSUV0xMDNhYzU1.6551bc1b0f6a4078aa2d394d38a8aacd.C1648e087526");
		System.out.println(zcjData);
	}
	
}
