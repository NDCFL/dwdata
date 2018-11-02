/**
 * 代号:隐无为 2018 
 */
package com.dwsj.crawlUtil;

import java.io.Serializable;
import java.util.Map;

/**
 * json 响应返回的数据
 */
@SuppressWarnings("all")
public class JsonReturnData<T> implements Serializable{

	/**
	 * 响应吗
	 */
	private int code;
	/**
	 * 响应消息
	 */
	private String msg;
	
	
	/**
	 * json 对象
	 */
	private T data;
	
	/**
	 * json数据map  额外数据
	 */

	private Map<String, Object> jsonMap;

	public int getCode() {
		return code;
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

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}

	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}

	public JsonReturnData() {
	}

	
	public JsonReturnData(Integer code,String msg) {
		this.code=code;
		this.msg=msg; 
	}
	
}

