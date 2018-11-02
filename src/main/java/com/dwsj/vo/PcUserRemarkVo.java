package com.dwsj.vo;

import java.io.Serializable;
import java.util.Date;

public class PcUserRemarkVo implements Serializable {
    private Long id;//用户的备注编号
    private Long pcUserId;//用户编号
    private Long kehuId;//客户编号
    private String content;//备注的内容
    private Date createTime;//备注创建的时间

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
