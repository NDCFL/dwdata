package com.dwsj.vo;

import java.io.Serializable;
import java.util.List;

public class JinDongInfoVo implements Serializable {
    private List<MonthAndMoneyVo> monthAndMoneyVos;
    private List<MoneyVo> monthVos;
    private List<JinDongItemVo> taoBaoItemVos;
    private List<JinDongItemVo> taoBaoItemVoList;

    public List<MonthAndMoneyVo> getMonthAndMoneyVos() {
        return monthAndMoneyVos;
    }

    public void setMonthAndMoneyVos(List<MonthAndMoneyVo> monthAndMoneyVos) {
        this.monthAndMoneyVos = monthAndMoneyVos;
    }

    public List<MoneyVo> getMonthVos() {
        return monthVos;
    }

    public void setMonthVos(List<MoneyVo> monthVos) {
        this.monthVos = monthVos;
    }

    public List<JinDongItemVo> getJinDongItemVos() {
        return taoBaoItemVos;
    }

    public void setJinDongItemVos(List<JinDongItemVo> taoBaoItemVos) {
        this.taoBaoItemVos = taoBaoItemVos;
    }

    public List<JinDongItemVo> getJinDongItemVoList() {
        return taoBaoItemVoList;
    }

    public void setJinDongItemVoList(List<JinDongItemVo> taoBaoItemVoList) {
        this.taoBaoItemVoList = taoBaoItemVoList;
    }
}
