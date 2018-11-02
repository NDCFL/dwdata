package com.dwsj.vo;

import com.dwsj.util.GsonUtils;

/**
 * 请求返回的结构
 * @author cgw
 * @date 2017年8月22日
 */
public class Result {

	public int code;
	public String msg;
	public Object data;




	public Result() {
		super();
	}

	public Result(int code) {
		this.code = code;
	}

	public Result(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Result(int code, Object data) {
		this.code = code;
		this.data = data;
	}

	public Result(int code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public static Result Err(){
		return new Result(ResultCode.ERROR);
	}
	public static Result Err(String msg){
		return new Result(ResultCode.ERROR,msg);
	}
	public static Result Ok(){
		return new Result(ResultCode.OK);
	}
	public static Result Ok(String msg){
		return new Result(ResultCode.OK,msg);
	}
	public static Result Ok(Object data){
		return new Result(ResultCode.OK,data);
	}
	public static Result OkData(Object data){
		return Ok(data);
	}
	public static Result Ok(String msg,Object data){
		return new Result(ResultCode.OK,msg,data);
	}

	public void setInfo(int code, String msg){
		this.code = code;
		this.msg = msg;
	}

	public void clear(){
		msg = null;
	}

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

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public boolean isOk() {
		return this.code == ResultCode.OK;
	}

	public boolean isNotOk() {
		return this.code != ResultCode.OK;
	}

	public boolean isError() {
		return this.code == ResultCode.ERROR;
	}

	@Override
	public String toString() {
		return GsonUtils.translateToJson(this);
	}


}