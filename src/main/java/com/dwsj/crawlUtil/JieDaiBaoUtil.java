package com.dwsj.crawlUtil;

import com.alibaba.fastjson.JSONObject;
import com.dwsj.common.Message;
import com.dwsj.util.GsonUtils;
import com.dwsj.util.HttpClientUtil;
import com.dwsj.util.StringUtil;
import com.dwsj.util.system.SysConf;

import java.util.HashMap;
import java.util.Map;

/**
 * 借贷宝
 * @author cgw
 * @date 2018年6月8日
 */
public class JieDaiBaoUtil {

	/**
	 * 获取借贷宝数据
	 * @param idCard
	 * @return
	 * 失败: {"code":非100,msg:"xxx"}
	 * 成功:{"code": 100, "data": {"data": {}, "version": "", "status": 0, "message": "\u67e5\u65e0\u7ed3\u679c", "timestamp": "2018-06-08 11:19:36.801", "host": "100.105.20.99", "acrossTime": 4}}
	 */
	public static String getJdbData(String idCard) {
		
		if (StringUtil.isEmpty(idCard)) {
			return Message.fail("身份证不能为空").toString();
		}
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		String[] params = new String[]{idCard};
		paramMap.put("pyPath", SysConf.PYTHON_LOCAL_PATH+"/jiedaibao/");
		paramMap.put("pyName", "run.py");
		paramMap.put("params", params);
		String jsonStr = HttpClientUtil.httpPost("http://www.192.168.3.18:8080/callpy/call", paramMap);
		jsonStr = jsonStr.replace("\r\n","");
		System.out.println(jsonStr);
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
	
	public static void main(String[] args) {
		String data = getJdbData("430381199210283324");
		System.out.println(data);
		
	}
}
