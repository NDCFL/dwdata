package com.dwsj.vo;

import java.io.Serializable;
import java.util.Date;

public class FindPoliceVo implements Serializable {
    private Long id;//查询手机号
    private Long pcUserId;//用户的编号
    private Long kehuId;//客户编号
    private String bcr;//被查人
    private String bcrIdcard;//被查人身份证
    private Byte isActive;//查询状态
    private Date createTime;//创建时间’
    private String data;
    private String headImg;//用户的头像
    private int code;

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

    public String getBcrIdcard() {
        return bcrIdcard;
    }

    public void setBcrIdcard(String bcrIdcard) {
        this.bcrIdcard = bcrIdcard;
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

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Long getKehuId() {
        return kehuId;
    }

    public void setKehuId(Long kehuId) {
        this.kehuId = kehuId;
    }
}
