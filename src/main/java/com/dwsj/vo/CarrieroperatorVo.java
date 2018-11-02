package com.dwsj.vo;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Date;

public class CarrieroperatorVo implements Serializable {
    private Long id ;//'编号',
    private Long pcUserId ;//'查询人编号',
    private Long kehuId;//客户编号
    private String bcr;//'被查人',
    private String bcrPhone;//'被查人手机号',
    private String url;//'报告url',
    private Byte isActive;//'查询状态',
    private Date createTime;//'查询时间'
    private String sid;//查询的sid
    private String headImg;//头像

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

    public String getBcr() {
        return bcr;
    }

    public void setBcr(String bcr) {
        this.bcr = bcr;
    }

    public String getBcrPhone() {
        return bcrPhone;
    }

    public void setBcrPhone(String bcrPhone) {
        this.bcrPhone = bcrPhone;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public Long getKehuId() {
        return kehuId;
    }

    public void setKehuId(Long kehuId) {
        this.kehuId = kehuId;
    }
}
