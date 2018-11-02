package com.dwsj.vo;

import java.io.Serializable;
import java.util.Date;

public class PcUserGiveItemVo implements Serializable {
    private Long id;//'编号',
    private Long pcUserId;//
    private Integer jifen;//'赠送的积分',
    private Integer type;//'积分来源',
    private Byte isActive;//'状态',
    private Date createTime;//'赠送时间'

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

    public Integer getJifen() {
        return jifen;
    }

    public void setJifen(Integer jifen) {
        this.jifen = jifen;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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
