package com.dwsj.util;

/**
 * Author: YuDong
 * Date: 16/8/31
 * Time: 下午5:50
 * Describe: 封装Json返回信息
 */
public class JsonResult {
	/**
	 * 状态
	 */
    private int status;
    /**
     * 消息
     */
    private String msg;
    /**
     * 数据
     */
    private Object obj;


    public JsonResult() {
		super();
	}

    public JsonResult(String msg) {
		super();
		this.msg = msg;
	}
    
    public JsonResult(int status, String msg) {
  		super();
  		this.status = status;
  		this.msg = msg;
  	}
      
    
	public JsonResult(String msg, Object obj) {
		super();
		this.msg = msg;
		this.obj = obj;
	}



	public JsonResult(int status, String msg, Object obj) {
		super();
		this.status = status;
		this.msg = msg;
		this.obj = obj;
	}

	public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
