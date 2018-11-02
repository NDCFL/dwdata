package com.dwsj.service.impl;

import com.dwsj.common.PageQuery;
import com.dwsj.common.StatusQuery;
import com.dwsj.dao.AreaDAO;
import com.dwsj.dao.KeHuDAO;
import com.dwsj.service.KeHuService;
import com.dwsj.vo.CountVo;
import com.dwsj.vo.KeHuVo;
import com.dwsj.vo.UserPCVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class KeHuServiceImpl implements KeHuService {
    @Resource
    private KeHuDAO keHuDAO;
    @Resource
    private AreaDAO areaDAO;
    @Override
    public void save(KeHuVo keHuVo) {
        keHuDAO.save(keHuVo);
    }
    @Override
    public void remove(KeHuVo keHuVo) {
        keHuDAO.remove(keHuVo);
    }

    @Override
    public void removeById(Long id) {
        keHuDAO.removeById(id);
    }

    @Override
    public void update(KeHuVo keHuVo) {
        keHuDAO.update(keHuVo);
    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {
        keHuDAO.updateStatus(statusQuery);
    }

    @Override
    public KeHuVo getById(Long id) {
        return keHuDAO.getById(id);
    }

    @Override
    public List<KeHuVo> listAll() {
        return keHuDAO.listAll();
    }

    @Override
    public List<KeHuVo> listPage(PageQuery pageQuery) {
        return keHuDAO.listPage(pageQuery);
    }

    @Override
    public long count(PageQuery pageQuery) {
        return keHuDAO.count(pageQuery);
    }

    @Override
    public KeHuVo checkKeHu(KeHuVo keHuVo) {
        System.out.println(keHuVo.getKehuIdcard()+"===="+keHuVo.getKehuName());
        return keHuDAO.checkKeHu(keHuVo);
    }

    @Override
    public KeHuVo getKeHuInfo(KeHuVo keHuVo) {
        return keHuDAO.getKeHuInfo(keHuVo);
    }

    @Override
    public List<KeHuVo> listPages(PageQuery pageQuery, KeHuVo keHuVo) {
        return keHuDAO.listPages(pageQuery, keHuVo);
    }

    @Override
    public Long counts(PageQuery pageQuery, KeHuVo keHuVo) {
        return keHuDAO.counts(pageQuery, keHuVo);
    }

    @Override
    public void updateTime(Long id) {
        keHuDAO.updateTime(id);
    }

    @Override
    public CountVo getCount(Long id) {
        return keHuDAO.getCount(id);
    }

    @Override
    public void updatePhone(String phone, Long id) {
        keHuDAO.updatePhone(phone, id);
    }

    @Override
    public int cnt(KeHuVo keHuVo) {
        return keHuDAO.cnt(keHuVo);
    }

    @Override
    public void updateXiaoDai(Long id, String result) {
        keHuDAO.updateXiaoDai(id, result);
    }

    @Override
    public void updateRemark(KeHuVo keHuVo) {
        keHuDAO.updateRemark(keHuVo);
    }

}
