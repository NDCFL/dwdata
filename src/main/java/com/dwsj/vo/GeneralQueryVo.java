package com.dwsj.vo;

import java.util.Date;

public class GeneralQueryVo {

	private Long id;//普通查询编号

	private Date createTime;//查询时间

	private Long kehuId;//借款人id

	private Long pcUserId;//用户id

	private String pzyData;//凭证云页面html

	private String jsdData;//今收到json

	private String qlmData;//签了么页面

	private String grxData;//果然信页面

	private String mifangData;//米房逾期列表

	private String jjyData;//居间云html

	private String yxyData;//易信缘html

	private String wyjybaoData;//无忧交易宝json数据

	private String sxyData;//速信云/速信记账服务
	
	private String zcjData;//中诚介json数据
	
	private String jdbData;//借贷宝json数据
	
	private String xysData;//信誉搜

	private String result;

	private String message;

	public String getXysData() {
		return xysData;
	}

	public void setXysData(String xysData) {
		this.xysData = xysData;
	}

	public String getSxyData() {
		return sxyData;
	}

	public void setSxyData(String sxyData) {
		this.sxyData = sxyData;
	}

	public String getYxyData() {
		return yxyData;
	}

	public void setYxyData(String yxyData) {
		this.yxyData = yxyData;
	}

	public String getJjyData() {
		return jjyData;
	}

	public void setJjyData(String jjyData) {
		this.jjyData = jjyData;
	}

	public String getMifangData() {
		return mifangData;
	}

	public void setMifangData(String mifangData) {
		this.mifangData = mifangData;
	}

	public String getGrxData() {
		return grxData;
	}

	public void setGrxData(String grxData) {
		this.grxData = grxData;
	}

	public String getQlmData() {
		return qlmData;
	}

	public void setQlmData(String qlmData) {
		this.qlmData = qlmData;
	}

	public String getPzyData() {
		return pzyData;
	}

	public void setPzyData(String pzyData) {
		this.pzyData = pzyData;
	}

	public String getJsdData() {
		return jsdData;
	}

	public void setJsdData(String jsdData) {
		this.jsdData = jsdData;
	}

	/**
	 * 黑爬虫所返回的22个平台数据
	 * 
	 * @return id 黑爬虫所返回的22个平台数据
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 黑爬虫所返回的22个平台数据
	 * 
	 * @param id
	 *            黑爬虫所返回的22个平台数据
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getKehuId() {
		return kehuId;
	}

	public void setKehuId(Long kehuId) {
		this.kehuId = kehuId;
	}

	public Long getPcUserId() {
		return pcUserId;
	}

	public void setPcUserId(Long pcUserId) {
		this.pcUserId = pcUserId;
	}

	public String getWyjybaoData() {
		return wyjybaoData;
	}

	public void setWyjybaoData(String wyjybaoData) {
		this.wyjybaoData = wyjybaoData;
	}

	public String getZcjData() {
		return zcjData;
	}

	public void setZcjData(String zcjData) {
		this.zcjData = zcjData;
	}

	public String getJdbData() {
		return jdbData;
	}

	public void setJdbData(String jdbData) {
		this.jdbData = jdbData;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}