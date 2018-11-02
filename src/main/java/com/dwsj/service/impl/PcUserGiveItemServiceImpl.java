package com.dwsj.service.impl;

import com.dwsj.common.PageQuery;
import com.dwsj.common.StatusQuery;
import com.dwsj.dao.PcUserGiveItemDAO;
import com.dwsj.service.PcUserGiveItemService;
import com.dwsj.vo.PcUserGiveItemVo;
import com.dwsj.vo.PcUserGiveItemVo;
import com.dwsj.vo.Select2Vo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PcUserGiveItemServiceImpl implements PcUserGiveItemService {
    @Resource
    private PcUserGiveItemDAO pcUserGiveItemDAO;

    @Override
    public void save(PcUserGiveItemVo pcUserGiveItemVo) {
        pcUserGiveItemDAO.save(pcUserGiveItemVo);
    }

    @Override
    public void remove(PcUserGiveItemVo pcUserGiveItemVo) {
        pcUserGiveItemDAO.remove(pcUserGiveItemVo);
    }

    @Override
    public void removeById(Long id) {
        pcUserGiveItemDAO.removeById(id);
    }

    @Override
    public void update(PcUserGiveItemVo pcUserGiveItemVo) {
        pcUserGiveItemDAO.update(pcUserGiveItemVo);
    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {
        pcUserGiveItemDAO.updateStatus(statusQuery);
    }

    @Override
    public PcUserGiveItemVo getById(Long id) {
        return pcUserGiveItemDAO.getById(id);
    }

    @Override
    public List<PcUserGiveItemVo> listAll() {
        return pcUserGiveItemDAO.listAll();
    }

    @Override
    public List<PcUserGiveItemVo> listPage(PageQuery pageQuery) {
        return pcUserGiveItemDAO.listPage(pageQuery);
    }

    @Override
    public long count(PageQuery pageQuery) {
        return pcUserGiveItemDAO.count(pageQuery);
    }

}
