package com.dwsj.vo;

import java.io.Serializable;
import java.util.Date;

public class PcUserConsumeVo implements Serializable {
    private Long id;//订单编号
    private Long pcUserId;//用户的编号
    private String orderId;//订单编号
    private Integer money;//充值的金额
    private String ip;//充值所用的ip地址
    private Long jifen;//所得积分
    private Byte isActive;//状态
    private Date createTime;//创建时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPcUserId() {
        return pcUserId;
    }

    public void setPcUserId(Long pcUserId) {
        this.pcUserId = pcUserId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Long getJifen() {
        return jifen;
    }

    public void setJifen(Long jifen) {
        this.jifen = jifen;
    }

    public Byte getIsActive() {
        return isActive;
    }

    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
