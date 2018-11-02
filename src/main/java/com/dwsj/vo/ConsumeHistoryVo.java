package com.dwsj.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 描述:tb_consume_history表的实体类
 * 
 * @version
 * @author: YuDong
 * @创建时间: 2018-06-21
 */
public class ConsumeHistoryVo {
	private Long id;//消费记录表
	private Long pcUserId;//用户id
	private String bcr;//被查人
	private Integer amount;//消费金额
	private String remark;//本次消费备注说明
	private Date createTime;//消费时间
	private Byte isActive;//查询的状态

	public String getBcr() {
		return bcr;
	}

	public void setBcr(String bcr) {
		this.bcr = bcr;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getPcUserId() {
		return pcUserId;
	}

	public void setPcUserId(Long pcUserId) {
		this.pcUserId = pcUserId;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Byte getIsActive() {
		return isActive;
	}

	public void setIsActive(Byte isActive) {
		this.isActive = isActive;
	}
}