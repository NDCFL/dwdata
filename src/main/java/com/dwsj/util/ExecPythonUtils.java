package com.dwsj.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/**
 * 执行python脚本，并获取其最后输出的字符串
 * @author YuDong
 *
 */
public class ExecPythonUtils {
	
	public static void main(String[] args) {
		String scriptName = "run";
		String[] param = new String[] {"17702718868", "452935", "390"};
//		String scriptName = "test";
//		String[] param = new String[] {"afdsafds","143432"};
//		execPythonShell("C:\\Users\\YuDong\\Desktop",scriptName,param);
		System.out.println(execPythonShell("C:/python/diwangshuju/cunnar/",scriptName,param));
	}



	public static String execPythonShell(String path,String scriptName,String... args) {
		//String[] func = new String[] { "python", "C:\\Users\\YuDong\\Desktop\\test.py",param1,param2,param3};
		if (!scriptName.endsWith(".py")) {
			scriptName = scriptName+".py";
		}
		String[] func = null;
		path = path+File.separator+scriptName;
		//String path = "E:\\QQ_resv"+File.separator+scriptName;
		if (args == null) {
			func = new String[] { "python", path};
		}else {
			func = new String[args.length+2];
			func[0] = "python";
			func[1] = path;
			for (int i = 2; i < func.length; i++) {
				func[i] = args[i-2];
			}
		}
		System.out.println(func[1]);
		StringBuffer result = new StringBuffer();
		try {
			System.out.println("=== 执行python脚本START ===");
			//设置命令行传入参数
			Process pr = Runtime.getRuntime().exec(func);
			BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream(),"gb2312"));

			String line = null;
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
			System.out.println(result.toString());
			in.close();
			pr.waitFor();
			System.out.println("=== 执行python脚本END ===");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result.toString();
	}


}
