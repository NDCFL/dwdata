package com.dwsj.service.impl;

import com.dwsj.common.PageQuery;
import com.dwsj.common.StatusQuery;
import com.dwsj.dao.FixedPriceDAO;
import com.dwsj.service.FixedPriceService;
import com.dwsj.vo.FixedPriceVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class FixedPriceServiceImpl implements FixedPriceService {
    @Resource
    private FixedPriceDAO fixedPriceDAO;
    @Override
    public void save(FixedPriceVo fixedPriceVo) {
        fixedPriceDAO.save(fixedPriceVo);
    }

    @Override
    public void remove(FixedPriceVo fixedPriceVo) {
        fixedPriceDAO.remove(fixedPriceVo);
    }

    @Override
    public void removeById(Long id) {
        fixedPriceDAO.removeById(id);
    }

    @Override
    public void update(FixedPriceVo fixedPriceVo) {
        fixedPriceDAO.update(fixedPriceVo);
    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {
        fixedPriceDAO.updateStatus(statusQuery);
    }

    @Override
    public FixedPriceVo getById(Long id) {
        return fixedPriceDAO.getById(id);
    }

    @Override
    public List<FixedPriceVo> listAll() {
        return fixedPriceDAO.listAll();
    }

    @Override
    public List<FixedPriceVo> listPage(PageQuery pageQuery) {
        return fixedPriceDAO.listPage(pageQuery);
    }

    @Override
    public long count(PageQuery pageQuery) {
        return fixedPriceDAO.count(pageQuery);
    }
}
