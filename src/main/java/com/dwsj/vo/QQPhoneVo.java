package com.dwsj.vo;

import java.io.Serializable;
import java.util.Date;

public class QQPhoneVo implements Serializable{
    private Long id;//'编号',
    private Long pcUserId;//'用户编号',
    private Long kehuId;//'客户编号',
    private String bcr;//被查人
    private String qq;//'QQ号',
    private String hashVal;//'hash值，全局唯一标识',
    private String qrImg;//二维码图片地址
    private String data;//'获取到的用户的通讯录数据',
    private Byte isActive;//'获取的状态',
    private Date createTime;//'创建的时间'
    private String nickName;
    private String phone;

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

    public Long getKehuId() {
        return kehuId;
    }

    public void setKehuId(Long kehuId) {
        this.kehuId = kehuId;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getHashVal() {
        return hashVal;
    }

    public void setHashVal(String hashVal) {
        this.hashVal = hashVal;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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

    public String getBcr() {
        return bcr;
    }

    public void setBcr(String bcr) {
        this.bcr = bcr;
    }

    public String getQrImg() {
        return qrImg;
    }

    public void setQrImg(String qrImg) {
        this.qrImg = qrImg;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}