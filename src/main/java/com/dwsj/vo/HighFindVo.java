package com.dwsj.vo;

import java.io.Serializable;
import java.util.Date;
public class HighFindVo implements Serializable{
    private Long id ;//'高级查询编号',
    private Long pcUserId ;//'用户的编号',
    private Long kehuId ;//'客户编号',
    private String mifangTaskId;//'米房任务编号',
    private String mifangPhone;//'米房认证的手机号',
    private String mifangPassword;// '米房认证的密码',
    private String mifangData;// '米房数据报告',
    private Integer mifangStatus;// '米房认证状态:0:认证完成，1:认证中',
    private String  youpingzhengTaskId;// '有凭证任务编号',
    private String  youpingzhengPhone;// '有凭证认证的手机号',
    private String  youpingzhengPassword;// '有凭证认证的密码',
    private String  youpingzhengData;// '有凭证数据报告',
    private Integer youpingzhengStatus;// '有凭证认证状态:0:认证完成，1:认证中',
    private String  jinjiedaoTaskId;// '今借到任务编号',
    private String  jinjiedaoPhone;// '今借到认证的手机号',
    private String  jinjiedaoPassword;// '今借到认证的密码',
    private String  jinjiedaoData;// '今借到数据报告',
    private Integer jinjiedaoStatus;// '今借到认证状态:0:认证完成，1:认证中',
    private String xiaocaiguanjiaPhone;// '小财管家认证的手机号',
    private String xiaocaiguanjiaPassword;// '小财管家认证的密码',
    private String xiaocaiguanjiaData;// '小财管家数据报告',
    private String xiaocaiguanjiaBaseData;//基础数据
    private Integer xiaocaiguanjiaStatus;// '小财管家认证状态:0:认证完成，1:认证中',
    private String ip;// '认证所用的ip地址',
    private String device;// '设备号',
    private String wxImg;// '微信头像',
    private String openid;// '微信openid',
    private Integer isActive;//是否付钱了
    private Date createTime;// '更新时间'
    private String qrImg;
    private String address;
    private String code;
    private Long time;
    private Float money;
    private String orderitem;
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

    public String getMifangTaskId() {
        return mifangTaskId;
    }

    public void setMifangTaskId(String mifangTaskId) {
        this.mifangTaskId = mifangTaskId;
    }

    public String getMifangPhone() {
        return mifangPhone;
    }

    public void setMifangPhone(String mifangPhone) {
        this.mifangPhone = mifangPhone;
    }

    public String getMifangPassword() {
        return mifangPassword;
    }

    public void setMifangPassword(String mifangPassword) {
        this.mifangPassword = mifangPassword;
    }

    public String getMifangData() {
        return mifangData;
    }

    public void setMifangData(String mifangData) {
        this.mifangData = mifangData;
    }

    public Integer getMifangStatus() {
        return mifangStatus;
    }

    public void setMifangStatus(Integer mifangStatus) {
        this.mifangStatus = mifangStatus;
    }

    public String getYoupingzhengTaskId() {
        return youpingzhengTaskId;
    }

    public void setYoupingzhengTaskId(String youpingzhengTaskId) {
        this.youpingzhengTaskId = youpingzhengTaskId;
    }

    public String getYoupingzhengPhone() {
        return youpingzhengPhone;
    }

    public void setYoupingzhengPhone(String youpingzhengPhone) {
        this.youpingzhengPhone = youpingzhengPhone;
    }

    public String getYoupingzhengPassword() {
        return youpingzhengPassword;
    }

    public void setYoupingzhengPassword(String youpingzhengPassword) {
        this.youpingzhengPassword = youpingzhengPassword;
    }

    public String getYoupingzhengData() {
        return youpingzhengData;
    }

    public void setYoupingzhengData(String youpingzhengData) {
        this.youpingzhengData = youpingzhengData;
    }

    public Integer getYoupingzhengStatus() {
        return youpingzhengStatus;
    }

    public void setYoupingzhengStatus(Integer youpingzhengStatus) {
        this.youpingzhengStatus = youpingzhengStatus;
    }

    public String getJinjiedaoTaskId() {
        return jinjiedaoTaskId;
    }

    public void setJinjiedaoTaskId(String jinjiedaoTaskId) {
        this.jinjiedaoTaskId = jinjiedaoTaskId;
    }

    public String getJinjiedaoPhone() {
        return jinjiedaoPhone;
    }

    public void setJinjiedaoPhone(String jinjiedaoPhone) {
        this.jinjiedaoPhone = jinjiedaoPhone;
    }

    public String getJinjiedaoPassword() {
        return jinjiedaoPassword;
    }

    public void setJinjiedaoPassword(String jinjiedaoPassword) {
        this.jinjiedaoPassword = jinjiedaoPassword;
    }

    public String getJinjiedaoData() {
        return jinjiedaoData;
    }

    public void setJinjiedaoData(String jinjiedaoData) {
        this.jinjiedaoData = jinjiedaoData;
    }

    public Integer getJinjiedaoStatus() {
        return jinjiedaoStatus;
    }

    public void setJinjiedaoStatus(Integer jinjiedaoStatus) {
        this.jinjiedaoStatus = jinjiedaoStatus;
    }

    public String getXiaocaiguanjiaPhone() {
        return xiaocaiguanjiaPhone;
    }

    public void setXiaocaiguanjiaPhone(String xiaocaiguanjiaPhone) {
        this.xiaocaiguanjiaPhone = xiaocaiguanjiaPhone;
    }

    public String getXiaocaiguanjiaPassword() {
        return xiaocaiguanjiaPassword;
    }

    public void setXiaocaiguanjiaPassword(String xiaocaiguanjiaPassword) {
        this.xiaocaiguanjiaPassword = xiaocaiguanjiaPassword;
    }

    public String getXiaocaiguanjiaData() {
        return xiaocaiguanjiaData;
    }

    public void setXiaocaiguanjiaData(String xiaocaiguanjiaData) {
        this.xiaocaiguanjiaData = xiaocaiguanjiaData;
    }

    public Integer getXiaocaiguanjiaStatus() {
        return xiaocaiguanjiaStatus;
    }

    public void setXiaocaiguanjiaStatus(Integer xiaocaiguanjiaStatus) {
        this.xiaocaiguanjiaStatus = xiaocaiguanjiaStatus;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getWxImg() {
        return wxImg;
    }

    public void setWxImg(String wxImg) {
        this.wxImg = wxImg;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public String getQrImg() {
        return qrImg;
    }

    public void setQrImg(String qrImg) {
        this.qrImg = qrImg;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getXiaocaiguanjiaBaseData() {
        return xiaocaiguanjiaBaseData;
    }

    public void setXiaocaiguanjiaBaseData(String xiaocaiguanjiaBaseData) {
        this.xiaocaiguanjiaBaseData = xiaocaiguanjiaBaseData;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public String getOrderitem() {
        return orderitem;
    }

    public void setOrderitem(String orderitem) {
        this.orderitem = orderitem;
    }
}
