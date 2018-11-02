package com.dwsj.service.impl;

import com.dwsj.common.PageQuery;
import com.dwsj.common.StatusQuery;
import com.dwsj.dao.ConsumeHistoryDAO;
import com.dwsj.service.ConsumeHistoryService;
import com.dwsj.vo.ConsumeHistoryVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ConsumeHistoryServiceImpl implements ConsumeHistoryService {
    @Resource
    private ConsumeHistoryDAO consumeHistoryDAO;
    @Override
    public void save(ConsumeHistoryVo consumeHistoryVo) {
        consumeHistoryDAO.save(consumeHistoryVo);
    }

    @Override
    public void remove(ConsumeHistoryVo consumeHistoryVo) {
        consumeHistoryDAO.remove(consumeHistoryVo);
    }

    @Override
    public void removeById(Long id) {
        consumeHistoryDAO.removeById(id);
    }

    @Override
    public void update(ConsumeHistoryVo consumeHistoryVo) {
        consumeHistoryDAO.update(consumeHistoryVo);
    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {
        consumeHistoryDAO.updateStatus(statusQuery);
    }

    @Override
    public ConsumeHistoryVo getById(Long id) {
        return consumeHistoryDAO.getById(id);
    }

    @Override
    public List<ConsumeHistoryVo> listAll() {
        return consumeHistoryDAO.listAll();
    }

    @Override
    public List<ConsumeHistoryVo> listPage(PageQuery pageQuery) {
        return consumeHistoryDAO.listPage(pageQuery);
    }

    @Override
    public long count(PageQuery pageQuery) {
        return consumeHistoryDAO.count(pageQuery);
    }
}
