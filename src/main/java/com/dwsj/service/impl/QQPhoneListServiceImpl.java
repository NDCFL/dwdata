package com.dwsj.service.impl;

import com.dwsj.common.PageQuery;
import com.dwsj.common.StatusQuery;
import com.dwsj.dao.QQPhoneListDAO;
import com.dwsj.service.QQPhoneListService;
import com.dwsj.vo.QQPhoneListVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class QQPhoneListServiceImpl implements QQPhoneListService {
    @Resource
    private QQPhoneListDAO qqPhoneListDAO;

    @Override
    public void save(QQPhoneListVo qqPhoneListVo) {
        qqPhoneListDAO.save(qqPhoneListVo);
    }

    @Override
    public void remove(QQPhoneListVo qqPhoneListVo) {
        qqPhoneListDAO.remove(qqPhoneListVo);
    }

    @Override
    public void removeById(Long id) {
        qqPhoneListDAO.removeById(id);
    }

    @Override
    public void update(QQPhoneListVo qqPhoneListVo) {
        qqPhoneListDAO.update(qqPhoneListVo);
    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {
        qqPhoneListDAO.updateStatus(statusQuery);
    }

    @Override
    public QQPhoneListVo getById(Long id) {
        return qqPhoneListDAO.getById(id);
    }

    @Override
    public List<QQPhoneListVo> listAll() {
        return qqPhoneListDAO.listAll();
    }

    @Override
    public List<QQPhoneListVo> listPage(PageQuery pageQuery) {
        return qqPhoneListDAO.listPage(pageQuery);
    }

    @Override
    public long count(PageQuery pageQuery) {
        return qqPhoneListDAO.count(pageQuery);
    }

    @Override
    public void saveList(List<QQPhoneListVo> qqPhoneListVos) {
        qqPhoneListDAO.saveList(qqPhoneListVos);
    }

    @Override
    public List<QQPhoneListVo> getList(Long id) {
        return qqPhoneListDAO.getList(id);
    }
}
