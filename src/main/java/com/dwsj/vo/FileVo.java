package com.dwsj.vo;

import java.io.Serializable;
import java.util.Map;

public class FileVo implements Serializable{
    private int code;
    private String msg;
    private Map<String,String> data;
    public int getCode() {
        return code;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
