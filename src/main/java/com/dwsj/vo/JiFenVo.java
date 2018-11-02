package com.dwsj.vo;

import java.io.Serializable;
import java.util.Date;

public class JiFenVo implements Serializable {
    private Long id;//'编号',
    private Integer money;//'金额',
    private Long jifen;//'对应的积分',
    private Long zsJiFen;//赠送积分
    private String remark;//积分充值备注
    private Integer days;//有效天数
    private Byte isActive;// '状态',
    private Date createTime;//'创建时间'

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
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

    public Long getZsJiFen() {
        return zsJiFen;
    }

    public void setZsJiFen(Long zsJiFen) {
        this.zsJiFen = zsJiFen;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }
}
