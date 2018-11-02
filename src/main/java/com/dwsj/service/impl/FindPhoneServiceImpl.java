package com.dwsj.service.impl;

import com.dwsj.common.PageQuery;
import com.dwsj.common.StatusQuery;
import com.dwsj.dao.FindPhoneDAO;
import com.dwsj.service.FindPhoneService;
import com.dwsj.vo.FindPhoneVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FindPhoneServiceImpl implements FindPhoneService {
    @Resource
    private FindPhoneDAO findPhoneDAO;
    @Override
    public void save(FindPhoneVo findPhoneVo) {
        findPhoneDAO.save(findPhoneVo);
    }

    @Override
    public void remove(FindPhoneVo findPhoneVo) {
        findPhoneDAO.remove(findPhoneVo);
    }
    @Override
    public void removeById(Long id) {
        findPhoneDAO.removeById(id);
    }

    @Override
    public void update(FindPhoneVo findPhoneVo) {
        findPhoneDAO.update(findPhoneVo);
    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {
        findPhoneDAO.updateStatus(statusQuery);
    }

    @Override
    public FindPhoneVo getById(Long id) {
        return findPhoneDAO.getById(id);
    }

    @Override
    public List<FindPhoneVo> listAll() {
        return findPhoneDAO.listAll();
    }

    @Override
    public List<FindPhoneVo> listPage(PageQuery pageQuery) {
        return findPhoneDAO.listPage(pageQuery);
    }

    @Override
    public long count(PageQuery pageQuery) {
        return findPhoneDAO.count(pageQuery);
    }

    @Override
    public FindPhoneVo getFindPhoneInfo(FindPhoneVo findPhoneVo) {
        return  findPhoneDAO.getFindPhoneInfo(findPhoneVo);
    }

    @Override
    public int getCnt(FindPhoneVo findPhoneVo) {
        return findPhoneDAO.getCnt(findPhoneVo);
    }
}
