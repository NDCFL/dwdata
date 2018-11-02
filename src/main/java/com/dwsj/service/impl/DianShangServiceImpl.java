package com.dwsj.service.impl;

import com.dwsj.common.PageQuery;
import com.dwsj.common.StatusQuery;
import com.dwsj.dao.DianShangDAO;
import com.dwsj.service.DianShangService;
import com.dwsj.vo.*;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class DianShangServiceImpl implements DianShangService {
    @Resource
    private DianShangDAO dianShangDAO;

    @Override
    public void save(DianShangVo dianShangVo) {
        dianShangDAO.save(dianShangVo);
    }

    @Override
    public void remove(DianShangVo dianShangVo) {
        dianShangDAO.remove(dianShangVo);
    }

    @Override
    public void removeById(Long id) {
        dianShangDAO.removeById(id);
    }

    @Override
    public void update(DianShangVo dianShangVo) {
        dianShangDAO.update(dianShangVo);
    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {
        dianShangDAO.updateStatus(statusQuery);
    }

    @Override
    public DianShangVo getById(Long id) {
        return dianShangDAO.getById(id);
    }

    @Override
    public List<DianShangVo> listAll() {
        return dianShangDAO.listAll();
    }

    @Override
    public List<DianShangVo> listPage(PageQuery pageQuery) {
        return dianShangDAO.listPage(pageQuery);
    }

    @Override
    public long count(PageQuery pageQuery) {
        return dianShangDAO.count(pageQuery);
    }

    @Override
    public DianShangVo getInfo(DianShangVo dianShangVo) {
        return dianShangDAO.getInfo(dianShangVo);
    }

    @Override
    public DianShangVo getStatus(Long id) {
        return dianShangDAO.getStatus(id);
    }

    @Override
    public DianShangVo getBaseInfo(Long pcUserId, Long kehuId) {
        return dianShangDAO.getBaseInfo(pcUserId, kehuId);
    }

    @Override
    public DianShangVo getInfoByOrderId(String orderId) {
        return dianShangDAO.getInfoByOrderId(orderId);
    }

    @Override
    public List<MonthAndMoneyVo> taoBao(Long id) {
        return dianShangDAO.taoBao(id);
    }

    @Override
    public List<MoneyVo> taoBaoView(Long id) {
        return dianShangDAO.taoBaoView(id);
    }

    @Override
    public List<TaoBaoItemVo> taoBaoAddress(Long id) {
        return dianShangDAO.taoBaoAddress(id);
    }

    @Override
    public List<TaoBaoItemVo> getTaoBaoAll(Long id) {
        return dianShangDAO.getTaoBaoAll(id);
    }

    @Override
    public TaoBaoItemVo getTaoBaoList(Long id) {
        return dianShangDAO.getTaoBaoList(id);
    }

    @Override
    public void saveItem(List<TaoBaoItemVo> taoBaoItemVos) {
        dianShangDAO.saveItem(taoBaoItemVos);
    }

    @Override
    public List<MonthAndMoneyVo> jinDong(Long id) {
        return dianShangDAO.jinDong(id);
    }

    @Override
    public List<MoneyVo> jinDongView(Long id) {
        return dianShangDAO.jinDongView(id);
    }

    @Override
    public List<JinDongItemVo> jinDongAddress(Long id) {
        return dianShangDAO.jinDongAddress(id);
    }

    @Override
    public List<JinDongItemVo> getJinDongAll(Long id) {
        return dianShangDAO.getJinDongAll(id);
    }

    @Override
    public JinDongItemVo getJinDongList(Long id) {
        return dianShangDAO.getJinDongList(id);
    }

    @Override
    public void saveJinDongItem(List<JinDongItemVo> jinDongItemVos) {
        dianShangDAO.saveJinDongItem(jinDongItemVos);
    }
}
