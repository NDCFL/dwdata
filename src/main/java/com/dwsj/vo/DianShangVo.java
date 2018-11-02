package com.dwsj.vo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class DianShangVo implements Serializable {
    private Long id;
    private Long pcUserId;//'用户编号',
    private Long kehuId;//'客户编号',
    private String tbTaskId;//'淘宝认证的任务编号',
    private String tbPhone;//'淘宝认证手机号',
    private String tbPassword;//'淘宝认证密码',
    private Byte tbStatus;//'淘宝认证状态',
    private String tbData;//'淘宝的数据',

    private String jdTaskId;//'京东认证的任务编号',
    private String jdPhone;//'京东认证手机号',
    private String jdPassword;//'京东认证密码',
    private Byte jdStatus;//'京东认证状态',
    private String jdData;//'京东的数据',

    private String zfbTaskId;//'支付宝认证的任务编号',
    private String zfbPhone;//'支付宝认证手机号',
    private String zfbPassword;//'支付宝认证密码',
    private Byte zfbStatus;//'支付宝认证状态',
    private String zfbData;//'支付宝的数据',

    private String ip;//'认证使用的IP地址',
    private String device;//'认证所用的设备号',
    private String wxImg;//'微信头像',
    private String openId;//'微信openid',
    private String qrImg;//'二维码地址',
    private String address;//'所在地址',
    private Long time;//'失效时间',
    private String orderItem;//'订单号',
    private Byte isActive;//'状态',
    private Date createTime;//'创建时间'
    private String code;//状态码
    private Float money;//金额
    private String bcr;//被查人
    private String find;//查询的项目
    private Long times=0l;
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

    public String getTbTaskId() {
        return tbTaskId;
    }

    public void setTbTaskId(String tbTaskId) {
        this.tbTaskId = tbTaskId;
    }

    public String getTbPhone() {
        return tbPhone;
    }

    public void setTbPhone(String tbPhone) {
        this.tbPhone = tbPhone;
    }

    public String getTbPassword() {
        return tbPassword;
    }

    public void setTbPassword(String tbPassword) {
        this.tbPassword = tbPassword;
    }

    public Byte getTbStatus() {
        return tbStatus;
    }

    public void setTbStatus(Byte tbStatus) {
        this.tbStatus = tbStatus;
    }

    public String getTbData() {
        return tbData;
    }

    public void setTbData(String tbData) {
        this.tbData = tbData;
    }

    public String getJdTaskId() {
        return jdTaskId;
    }

    public void setJdTaskId(String jdTaskId) {
        this.jdTaskId = jdTaskId;
    }

    public String getJdPhone() {
        return jdPhone;
    }

    public void setJdPhone(String jdPhone) {
        this.jdPhone = jdPhone;
    }

    public String getJdPassword() {
        return jdPassword;
    }

    public void setJdPassword(String jdPassword) {
        this.jdPassword = jdPassword;
    }

    public Byte getJdStatus() {
        return jdStatus;
    }

    public void setJdStatus(Byte jdStatus) {
        this.jdStatus = jdStatus;
    }

    public String getJdData() {
        return jdData;
    }

    public void setJdData(String jdData) {
        this.jdData = jdData;
    }

    public String getZfbTaskId() {
        return zfbTaskId;
    }

    public void setZfbTaskId(String zfbTaskId) {
        this.zfbTaskId = zfbTaskId;
    }

    public String getZfbPhone() {
        return zfbPhone;
    }

    public void setZfbPhone(String zfbPhone) {
        this.zfbPhone = zfbPhone;
    }

    public String getZfbPassword() {
        return zfbPassword;
    }

    public void setZfbPassword(String zfbPassword) {
        this.zfbPassword = zfbPassword;
    }

    public Byte getZfbStatus() {
        return zfbStatus;
    }

    public void setZfbStatus(Byte zfbStatus) {
        this.zfbStatus = zfbStatus;
    }

    public String getZfbData() {
        return zfbData;
    }

    public void setZfbData(String zfbData) {
        this.zfbData = zfbData;
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

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
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

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(String orderItem) {
        this.orderItem = orderItem;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public String getBcr() {
        return bcr;
    }

    public void setBcr(String bcr) {
        this.bcr = bcr;
    }

    public String getFind() {
        return find;
    }

    public void setFind(String find) {
        this.find = find;
    }

    public Long getTimes() {
        if(createTime==null){
           createTime = new Date();
        }else{
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(createTime);
        }
        return createTime.getTime();
    }

}
