package com.dwsj.service.impl;

import com.dwsj.common.PageQuery;
import com.dwsj.common.StatusQuery;
import com.dwsj.dao.PcUserConsumeDAO;
import com.dwsj.service.PcUserConsumeService;
import com.dwsj.vo.PcUserConsumeVo;
import com.dwsj.vo.PcUserConsumeVo;
import com.dwsj.vo.Select2Vo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PcUserConsumeServiceImpl implements PcUserConsumeService {

    @Resource
    private PcUserConsumeDAO pcUserConsumeDAO;

    @Override
    public void save(PcUserConsumeVo pcUserConsumeVo) {
        pcUserConsumeDAO.save(pcUserConsumeVo);
    }

    @Override
    public void remove(PcUserConsumeVo pcUserConsumeVo) {
        pcUserConsumeDAO.remove(pcUserConsumeVo);
    }

    @Override
    public void removeById(Long id) {
        pcUserConsumeDAO.removeById(id);
    }

    @Override
    public void update(PcUserConsumeVo pcUserConsumeVo) {
        pcUserConsumeDAO.update(pcUserConsumeVo);
    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {
        pcUserConsumeDAO.updateStatus(statusQuery);
    }

    @Override
    public PcUserConsumeVo getById(Long id) {
        return pcUserConsumeDAO.getById(id);
    }

    @Override
    public List<PcUserConsumeVo> listAll() {
        return pcUserConsumeDAO.listAll();
    }

    @Override
    public List<PcUserConsumeVo> listPage(PageQuery pageQuery) {
        return pcUserConsumeDAO.listPage(pageQuery);
    }

    @Override
    public long count(PageQuery pageQuery) {
        return pcUserConsumeDAO.count(pageQuery);
    }

    @Override
    public void updateStatusByOrderId(PcUserConsumeVo pcUserConsumeVo) {
        pcUserConsumeDAO.updateStatusByOrderId(pcUserConsumeVo);
    }

    @Override
    public PcUserConsumeVo getInfo(PcUserConsumeVo pcUserConsumeVo) {
        return pcUserConsumeDAO.getInfo(pcUserConsumeVo);
    }
}
