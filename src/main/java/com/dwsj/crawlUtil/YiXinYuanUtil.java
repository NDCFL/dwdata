package com.dwsj.crawlUtil;

import com.dwsj.util.ExecPythonUtils;
import com.dwsj.util.StringUtil;
import com.dwsj.util.system.SysConf;
import com.dwsj.util.GsonUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * 易信缘
 * @author YuDong
 *
 */
public class YiXinYuanUtil {

	public static void main(String[] args) throws IOException {
		String phone = "18522179401";//18522179401
		String realName = "冉华松";//冉华松
		//System.out.println(GsonUtils.translateToJson(getYxyData(realName, phone,"JSESSIONID=113822DDF514FB8B8A6C41C94B98C71A")));/
//		ZhongChengJieUtil.getZCJData("刘少怪","421087199408258213","JSESSIONID=113822DDF514FB8B8A6C41C94B98C71A");
	}

	/**
	 * 获取易信缘json
	 * @param name
	 * @param phone
	 * @return
	 */
	public static String getYxyData(String name,String phone) {
		String[] param = new String[] { name, phone };
		String jsonStr = ExecPythonUtils.execPythonShell(SysConf.PYTHON_LOCAL_PATH + "cdxinqu/", "run.py", param);
		//String jsonStr = ExecPythonUtils.execPythonShell("F:/python/dwsjshuju/cdxinqu/", "run.py", param);
		return StringUtil.ascii2native(jsonStr);
	}
	
	/**
	 * 获取易信缘json，带cookie
	 * @param name
	 * @param phone
	 * @param cookie
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String getYxyData(String name,String phone,String cookie) throws UnsupportedEncodingException {
		String[] param = new String[] { name, phone,cookie };
		String jsonStr = ExecPythonUtils.execPythonShell(SysConf.PYTHON_LOCAL_PATH + "cdxinqu/", "run", param);
//		String jsonStr = ExecPythonUtils.execPythonShell("c:/python/dwsjshuju/cdxinqu/", "run.py", param);
		return StringUtil.ascii2native(jsonStr);
	}

}
