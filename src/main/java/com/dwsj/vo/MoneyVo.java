package com.dwsj.vo;

import java.io.Serializable;

public class MoneyVo implements Serializable {
    private String oneMoney;//第一月
    private String twoMoney;//第二月
    private String threeMoney;//第三月
    private String fourMoney;//第四月
    private String fiveMoney;//第五月
    private String sixMoney;//第6月
    private String sevenMoney;//第7月
    private String eightMoney;//第8月
    private String nineMoney;//第9月
    private String tenMoney;//第10月
    private String elevenMoney;//第11月
    private String twelveMoney;//第12月

    @Override
    public String toString() {
        return "MoneyVo{" +
                "oneMoney='" + oneMoney + '\'' +
                ", twoMoney='" + twoMoney + '\'' +
                ", threeMoney='" + threeMoney + '\'' +
                ", fourMoney='" + fourMoney + '\'' +
                ", fiveMoney='" + fiveMoney + '\'' +
                ", sixMoney='" + sixMoney + '\'' +
                ", sevenMoney='" + sevenMoney + '\'' +
                ", eightMoney='" + eightMoney + '\'' +
                ", nineMoney='" + nineMoney + '\'' +
                ", tenMoney='" + tenMoney + '\'' +
                ", elevenMoney='" + elevenMoney + '\'' +
                ", twelveMoney='" + twelveMoney + '\'' +
                '}';
    }

    public String getOneMoney() {
        return oneMoney;
    }

    public void setOneMoney(String oneMoney) {
        this.oneMoney = oneMoney;
    }

    public String getTwoMoney() {
        return twoMoney;
    }

    public void setTwoMoney(String twoMoney) {
        this.twoMoney = twoMoney;
    }

    public String getThreeMoney() {
        return threeMoney;
    }

    public void setThreeMoney(String threeMoney) {
        this.threeMoney = threeMoney;
    }

    public String getFourMoney() {
        return fourMoney;
    }

    public void setFourMoney(String fourMoney) {
        this.fourMoney = fourMoney;
    }

    public String getFiveMoney() {
        return fiveMoney;
    }

    public void setFiveMoney(String fiveMoney) {
        this.fiveMoney = fiveMoney;
    }

    public String getSixMoney() {
        return sixMoney;
    }

    public void setSixMoney(String sixMoney) {
        this.sixMoney = sixMoney;
    }

    public String getSevenMoney() {
        return sevenMoney;
    }

    public void setSevenMoney(String sevenMoney) {
        this.sevenMoney = sevenMoney;
    }

    public String getEightMoney() {
        return eightMoney;
    }

    public void setEightMoney(String eightMoney) {
        this.eightMoney = eightMoney;
    }

    public String getNineMoney() {
        return nineMoney;
    }

    public void setNineMoney(String nineMoney) {
        this.nineMoney = nineMoney;
    }

    public String getTenMoney() {
        return tenMoney;
    }

    public void setTenMoney(String tenMoney) {
        this.tenMoney = tenMoney;
    }

    public String getElevenMoney() {
        return elevenMoney;
    }

    public void setElevenMoney(String elevenMoney) {
        this.elevenMoney = elevenMoney;
    }

    public String getTwelveMoney() {
        return twelveMoney;
    }

    public void setTwelveMoney(String twelveMoney) {
        this.twelveMoney = twelveMoney;
    }

}
