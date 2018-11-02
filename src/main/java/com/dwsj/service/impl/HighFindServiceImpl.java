package com.dwsj.service.impl;

import com.dwsj.common.PageQuery;
import com.dwsj.common.StatusQuery;
import com.dwsj.dao.HighFindDAO;
import com.dwsj.service.HighFindService;
import com.dwsj.vo.HighFindVo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class HighFindServiceImpl implements HighFindService {
    @Resource
    private HighFindDAO highFindDAO;
    @Override
    public void save(HighFindVo highFindVo) {
        highFindDAO.save(highFindVo);
    }

    @Override
    public void remove(HighFindVo highFindVo) {
        highFindDAO.remove(highFindVo);
    }

    @Override
    public void removeById(Long id) {
        highFindDAO.removeById(id);
    }

    @Override
    public void update(HighFindVo highFindVo) {
        highFindDAO.update(highFindVo);
    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {
        highFindDAO.updateStatus(statusQuery);
    }

    @Override
    public HighFindVo getById(Long id) {
        return highFindDAO.getById(id);
    }

    @Override
    public List<HighFindVo> listAll() {
        return highFindDAO.listAll();
    }

    @Override
    public List<HighFindVo> listPage(PageQuery pageQuery) {
        return highFindDAO.listPage(pageQuery);
    }

    @Override
    public long count(PageQuery pageQuery) {
        return highFindDAO.count(pageQuery);
    }

    @Override
    public HighFindVo getInfo(HighFindVo highFindVo) {
        return highFindDAO.getInfo(highFindVo);
    }

    @Override
    public HighFindVo getStatus(Long id) {
        return highFindDAO.getStatus(id);
    }

    @Override
    public HighFindVo getBaseInfo(Long pcUserId, Long kehuId) {
        return highFindDAO.getBaseInfo(pcUserId, kehuId);
    }

    @Override
    public HighFindVo getInfoByOrderId(String orderId) {
        return highFindDAO.getInfoByOrderId(orderId);
    }
}
