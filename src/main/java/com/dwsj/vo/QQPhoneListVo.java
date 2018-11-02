package com.dwsj.vo;

import java.io.Serializable;
import java.util.Date;

public class QQPhoneListVo implements Serializable{
    private Long id;
    private Long pcUserId;//用户编号
    private Long qqPhoneId;//qq通讯录的编号
    private String telename;//名称
    private String telephone;//手机号
    private String hmfx;//号码分析结果
    private String txjlfx;//通讯记录分析
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

    public Long getQqPhoneId() {
        return qqPhoneId;
    }

    public void setQqPhoneId(Long qqPhoneId) {
        this.qqPhoneId = qqPhoneId;
    }

    public String getTelename() {
        return telename;
    }

    public void setTelename(String telename) {
        this.telename = telename;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getHmfx() {
        return hmfx;
    }

    public void setHmfx(String hmfx) {
        this.hmfx = hmfx;
    }

    public String getTxjlfx() {
        return txjlfx;
    }

    public void setTxjlfx(String txjlfx) {
        this.txjlfx = txjlfx;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
