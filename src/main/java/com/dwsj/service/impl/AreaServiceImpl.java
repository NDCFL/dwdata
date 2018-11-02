package com.dwsj.service.impl;

import com.dwsj.common.PageQuery;
import com.dwsj.common.StatusQuery;
import com.dwsj.dao.AreaDAO;
import com.dwsj.service.AreaService;
import com.dwsj.vo.AreaVo;
import com.dwsj.vo.Select2Vo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class AreaServiceImpl implements AreaService{
    @Resource
    private AreaDAO areaDAO;

    @Override
    public void save(AreaVo areaVo) {
        areaDAO.save(areaVo);
    }

    @Override
    public void remove(AreaVo areaVo) {
        areaDAO.remove(areaVo);
    }

    @Override
    public void removeById(Long id) {
        areaDAO.removeById(id);
    }

    @Override
    public void update(AreaVo areaVo) {
        areaDAO.update(areaVo);
    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {
        areaDAO.updateStatus(statusQuery);
    }

    @Override
    public AreaVo getById(Long id) {
        return areaDAO.getById(id);
    }

    @Override
    public List<AreaVo> listAll() {
        return areaDAO.listAll();
    }

    @Override
    public List<AreaVo> listPage(PageQuery pageQuery) {
        return areaDAO.listPage(pageQuery);
    }

    @Override
    public long count(PageQuery pageQuery) {
        return areaDAO.count(pageQuery);
    }

    @Override
    public List<Select2Vo> getArea() {
        return areaDAO.getArea();
    }

    @Override
    public AreaVo getAreaInfo(String code) {
        return areaDAO.getAreaInfo(code);
    }
}
