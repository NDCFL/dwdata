package com.dwsj.vo;

import java.io.Serializable;
import java.util.Date;

public class FindPhoneVo implements Serializable {
    private Long id;//查询手机号
    private Long pcUserId;//用户的编号
    private Long kehuId;//客户编号
    private String bcr;//被查人
    private String bcrPhone;//被查人身份证
    private Byte isActive;//查询状态
    private Date createTime;//创建时间’
    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Long getKehuId() {
        return kehuId;
    }

    public void setKehuId(Long kehuId) {
        this.kehuId = kehuId;
    }
}
