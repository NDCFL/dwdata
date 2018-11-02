package com.dwsj.service.impl;

import com.dwsj.common.PageQuery;
import com.dwsj.common.StatusQuery;
import com.dwsj.dao.FindPoliceDAO;
import com.dwsj.service.FindPoliceService;
import com.dwsj.vo.FindPoliceVo;
import com.dwsj.vo.FindPoliceVo;
import com.dwsj.vo.Select2Vo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FindPoliceServiceImpl implements FindPoliceService {
    @Resource
    private FindPoliceDAO findPoliceDAO;
    @Override
    public void save(FindPoliceVo findPoliceVo) {
        findPoliceDAO.save(findPoliceVo);
    }

    @Override
    public void remove(FindPoliceVo findPoliceVo) {
        findPoliceDAO.remove(findPoliceVo);
    }
    @Override
    public void removeById(Long id) {
        findPoliceDAO.removeById(id);
    }

    @Override
    public void update(FindPoliceVo findPoliceVo) {
        findPoliceDAO.update(findPoliceVo);
    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {
        findPoliceDAO.updateStatus(statusQuery);
    }

    @Override
    public FindPoliceVo getById(Long id) {
        return findPoliceDAO.getById(id);
    }

    @Override
    public List<FindPoliceVo> listAll() {
        return findPoliceDAO.listAll();
    }

    @Override
    public List<FindPoliceVo> listPage(PageQuery pageQuery) {
        return findPoliceDAO.listPage(pageQuery);
    }

    @Override
    public long count(PageQuery pageQuery) {
        return findPoliceDAO.count(pageQuery);
    }

    @Override
    public FindPoliceVo getFindPoliceInfo(FindPoliceVo findPoliceVo) {
        return findPoliceDAO.getFindPoliceInfo(findPoliceVo);
    }

    @Override
    public int getCnt(FindPoliceVo findPoliceVo) {
        return findPoliceDAO.getCnt(findPoliceVo);
    }
}
