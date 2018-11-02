package com.dwsj.dao;

import com.dwsj.vo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DianShangDAO extends BaseDAO<DianShangVo>{
    DianShangVo getInfo(DianShangVo highFindVo);
    DianShangVo getStatus(Long id);
    DianShangVo getBaseInfo(@Param("pcUserId") Long pcUserId, @Param("kehuId") Long kehuId);
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
