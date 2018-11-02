package com.dwsj.vo;

import java.io.Serializable;
import java.util.List;

public class TaoBaoInfoVo implements Serializable {
    private List<MonthAndMoneyVo> monthAndMoneyVos;
    private List<MoneyVo> monthVos;
    private List<TaoBaoItemVo> taoBaoItemVos;
    private List<TaoBaoItemVo> taoBaoItemVoList;

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

    public List<TaoBaoItemVo> getTaoBaoItemVos() {
        return taoBaoItemVos;
    }

    public void setTaoBaoItemVos(List<TaoBaoItemVo> taoBaoItemVos) {
        this.taoBaoItemVos = taoBaoItemVos;
    }

    public List<TaoBaoItemVo> getTaoBaoItemVoList() {
        return taoBaoItemVoList;
    }

    public void setTaoBaoItemVoList(List<TaoBaoItemVo> taoBaoItemVoList) {
        this.taoBaoItemVoList = taoBaoItemVoList;
    }
}
