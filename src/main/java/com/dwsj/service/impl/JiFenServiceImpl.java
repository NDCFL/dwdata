package com.dwsj.service.impl;


import com.dwsj.common.PageQuery;
import com.dwsj.common.StatusQuery;
import com.dwsj.dao.JiFenDAO;
import com.dwsj.service.JiFenService;
import com.dwsj.vo.JiFenVo;
import com.dwsj.vo.Select2Vo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class JiFenServiceImpl implements JiFenService{
    @Resource
    private JiFenDAO jiFenDAO;

    @Override
    public void save(JiFenVo jiFenVo) {
        jiFenDAO.save(jiFenVo);
    }

    @Override
    public void remove(JiFenVo jiFenVo) {
        jiFenDAO.remove(jiFenVo);
    }

    @Override
    public void removeById(Long id) {
        jiFenDAO.removeById(id);
    }

    @Override
    public void update(JiFenVo jiFenVo) {
        jiFenDAO.update(jiFenVo);
    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {
        jiFenDAO.updateStatus(statusQuery);
    }

    @Override
    public JiFenVo getById(Long id) {
        return jiFenDAO.getById(id);
    }

    @Override
    public List<JiFenVo> listAll() {
        return jiFenDAO.listAll();
    }

    @Override
    public List<JiFenVo> listPage(PageQuery pageQuery) {
        return jiFenDAO.listPage(pageQuery);
    }

    @Override
    public long count(PageQuery pageQuery) {
        return jiFenDAO.count(pageQuery);
    }

    @Override
    public List<JiFenVo> getJiFen() {
        return jiFenDAO.getJiFen();
    }

    @Override
    public JiFenVo getJiFenInfo(Integer amount) {
        return jiFenDAO.getJiFenInfo(amount);
    }
}
