package com.dwsj.vo;

import java.io.Serializable;
import java.util.Date;

public class UserPCVo implements Serializable {
    private Long id;// '用户编号',
    private String phone;//'用户联系方式',
    private String password;//'用户登录密码',
    private Long jifen;//'积分,
    private Integer kehu;//客户总数
    private Date createTime;//'创建时间'
    private Date endTime;//积分使用到期时间
    private Integer giveJifen;//赠送的积分
    private Byte isActive;//状态
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getJifen() {
        return jifen;
    }

    public void setJifen(Long jifen) {
        this.jifen = jifen;
    }

    public Integer getKehu() {
        return kehu;
    }

    public void setKehu(Integer kehu) {
        this.kehu = kehu;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getGiveJifen() {
        return giveJifen;
    }

    public void setGiveJifen(Integer giveJifen) {
        this.giveJifen = giveJifen;
    }

    public Byte getIsActive() {
        return isActive;
    }

    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
