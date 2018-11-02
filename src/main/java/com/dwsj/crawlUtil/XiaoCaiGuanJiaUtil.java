package com.dwsj.crawlUtil;

import com.dwsj.util.ExecPythonUtils;
import com.dwsj.util.GsonUtils;
import com.dwsj.util.StringUtil;
import com.dwsj.util.system.SysConf;

import java.io.IOException;

public class XiaoCaiGuanJiaUtil {
    public static void main(String[] args) throws IOException {
        String phone = "18522179401";//18522179401
        String realName = "冉华松";//冉华松
        //System.out.println(GsonUtils.translateToJson(getYxyData(realName, phone,"JSESSIONID=113822DDF514FB8B8A6C41C94B98C71A")));/
        System.out.println(GsonUtils.translateToJson(getXcgjListData("165656","357044")));
//		ZhongChengJieUtil.getZCJData("刘少怪","421087199408258213","JSESSIONID=113822DDF514FB8B8A6C41C94B98C71A");
    }
    public static String getXcgjData(String account,String password) {
        String[] param = new String[] { account, password };
        String jsonStr = ExecPythonUtils.execPythonShell(SysConf.PYTHON_LOCAL_PATH + "xiaocai/", "xiaocai_index_page.py", param);
        //String jsonStr = ExecPythonUtils.execPythonShell("F:/python/dwsjshuju/cdxinqu/", "run.py", param);
        return StringUtil.ascii2native(jsonStr);
    }
    public static String getXcgjListData(String account,String password) {
        String[] param = new String[] { account, password };
        //xiaocai_list_detail.py
        String jsonStr = ExecPythonUtils.execPythonShell(SysConf.PYTHON_LOCAL_PATH + "xiaocai/", "xiaocai_list_detail.py", param);
        return StringUtil.ascii2native(jsonStr);
    }
}
