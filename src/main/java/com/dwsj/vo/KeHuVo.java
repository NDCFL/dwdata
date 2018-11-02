package com.dwsj.vo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class KeHuVo implements Serializable {
    private Long id;//'我的客户编号',
    private Long pcUserId;//'用户的编号',
    private String kehuName;//'客户的姓名',
    private Integer kehuAge;//'客户的年龄',
    private Integer kehuSex;//客户的性别，0：男，1：女
    private String kehuHeadImg;//客户的头像
    private String kehuPhone;//'客户的手机号',
    private String kehuIdcard;//'客户身份证号',
    private Integer kehuType;//'客户的类型：0：特别关注用户，1：已合作用户，2：未合作用户',
    private String kehuAddress;//'客户的地址',
    private Date kehuBirthday;//客户生日
    private String remark;//'说明',
    private Date createTime;//'创建时间'
    private Date upTime;//上次查询时间
    private String commodityIds[];
    private String ids;
    private String sexVal;
    private String xiaodai;
    private Long qqPhoneId;
    private String qq;
    private Date xiaodaitime;//小贷更新时间
    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String[] getCommodityIds() {
        return commodityIds;
    }

    public void setCommodityIds(String[] commodityIds) {
        this.commodityIds = commodityIds;
    }

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

    public String getKehuName() {
        return kehuName;
    }

    public void setKehuName(String kehuName) {
        this.kehuName = kehuName;
    }

    public Integer getKehuSex() {
        return kehuSex;
    }

    public void setKehuSex(Integer kehuSex) {
        this.kehuSex = kehuSex;
    }

    public Integer getKehuAge() {
        //获取到用户的年份
        int year = Integer.parseInt(kehuIdcard.substring(6, 10));
        //获取到当前的年份
        Calendar date = Calendar.getInstance();
        int currentyear = date.get(Calendar.YEAR);
        return currentyear-year;
    }

    public void setKehuAge(Integer kehuAge) {
        this.kehuAge = kehuAge;
    }

    public String getKehuHeadImg() {
        return kehuHeadImg;
    }

    public void setKehuHeadImg(String kehuHeadImg) {
        this.kehuHeadImg = kehuHeadImg;
    }

    public String getKehuPhone() {
        return kehuPhone;
    }

    public void setKehuPhone(String kehuPhone) {
        this.kehuPhone = kehuPhone;
    }

    public String getKehuIdcard() {
        return kehuIdcard;
    }

    public void setKehuIdcard(String kehuIdcard) {
        this.kehuIdcard = kehuIdcard;
    }

    public Integer getKehuType() {
        return kehuType;
    }

    public void setKehuType(Integer kehuType) {
        this.kehuType = kehuType;
    }

    public String getKehuAddress() {
        return kehuAddress;
    }

    public void setKehuAddress(String kehuAddress) {
        this.kehuAddress = kehuAddress;
    }

    public Date getKehuBirthday() {
        return kehuBirthday;
    }

    public void setKehuBirthday(Date kehuBirthday) {
        this.kehuBirthday = kehuBirthday;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpTime() {
        return upTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

    public String getSexVal() {
        if(kehuSex==0){
            return "女";
        }else{
            return "男";
        }
    }

    public void setSexVal(String sexVal) {
        this.sexVal = sexVal;
    }

    public String getXiaodai() {
        return xiaodai;
    }

    public void setXiaodai(String xiaodai) {
        this.xiaodai = xiaodai;
    }

    public Long getQqPhoneId() {
        return qqPhoneId;
    }

    public void setQqPhoneId(Long qqPhoneId) {
        this.qqPhoneId = qqPhoneId;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Date getXiaodaitime() {
        return xiaodaitime;
    }

    public void setXiaodaitime(Date xiaodaitime) {
        this.xiaodaitime = xiaodaitime;
    }
}
