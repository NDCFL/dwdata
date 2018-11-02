package com.dwsj.service.impl;

import com.dwsj.common.PageQuery;
import com.dwsj.common.StatusQuery;
import com.dwsj.dao.PcUserRemarkDAO;
import com.dwsj.service.PcUserRemarkService;
import com.dwsj.vo.PcUserRemarkVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PcUserRemarkServiceImpl implements PcUserRemarkService {
    @Resource
    private PcUserRemarkDAO pcUserRemarkDAO;
    @Override
    public void save(PcUserRemarkVo pcUserRemarkVo) {
        pcUserRemarkDAO.save(pcUserRemarkVo);
    }

    @Override
    public void remove(PcUserRemarkVo pcUserRemarkVo) {
        pcUserRemarkDAO.remove(pcUserRemarkVo);
    }

    @Override
    public void removeById(Long id) {
        pcUserRemarkDAO.removeById(id);
    }

    @Override
    public void update(PcUserRemarkVo pcUserRemarkVo) {
        pcUserRemarkDAO.update(pcUserRemarkVo);
    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {
        pcUserRemarkDAO.updateStatus(statusQuery);
    }

    @Override
    public PcUserRemarkVo getById(Long id) {
        return pcUserRemarkDAO.getById(id);
    }

    @Override
    public List<PcUserRemarkVo> listAll() {
        return pcUserRemarkDAO.listAll();
    }

    @Override
    public List<PcUserRemarkVo> listPage(PageQuery pageQuery) {
        return pcUserRemarkDAO.listPage(pageQuery);
    }

    @Override
    public long count(PageQuery pageQuery) {
        return pcUserRemarkDAO.count(pageQuery);
    }

    @Override
    public List<PcUserRemarkVo> getAllList(Long id,Long kehuId) {
        return pcUserRemarkDAO.getAllList(id,kehuId);
    }
}
