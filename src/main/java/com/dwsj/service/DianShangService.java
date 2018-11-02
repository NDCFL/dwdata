package com.dwsj.service;

import com.dwsj.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DianShangService extends BaseService<DianShangVo>{
    DianShangVo getInfo(DianShangVo dianShangVo);
    DianShangVo getStatus(Long id);
    DianShangVo getBaseInfo(Long pcUserId,Long kehuId);
    DianShangVo getInfoByOrderId(String orderId);
    //淘宝操作
    List<MonthAndMoneyVo> taoBao(Long id);
    List<MoneyVo> taoBaoView(Long id);
    List<TaoBaoItemVo> taoBaoAddress(Long id);
    List<TaoBaoItemVo> getTaoBaoAll(Long id);
    TaoBaoItemVo getTaoBaoList(Long id);
    void saveItem(List<TaoBaoItemVo> taoBaoItemVos);
    //京东操作
    List<MonthAndMoneyVo> jinDong(Long id);
    List<MoneyVo> jinDongView(Long id);
    List<JinDongItemVo> jinDongAddress(Long id);
    List<JinDongItemVo> getJinDongAll(Long id);
    JinDongItemVo getJinDongList(Long id);
    void saveJinDongItem(List<JinDongItemVo> jinDongItemVos);
}
