package com.dwsj.vo;

import java.math.BigDecimal;

/**
 * 描述:tb_fixed_price表的实体类
 * 
 * @version
 * @author: win7 虚拟机
 * @创建时间: 2018-05-07
 */
public class FixedPriceVo {
	/**
	 * 
	 */
	private Long id;

	/**
	 * 功能名称
	 */
	private String name;

	/**
	 * 价格
	 */
	private Integer price;

	/**
	 * 功能介绍
	 */
	private String introduce;

	/**
	 * 备注
	 */
	private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}