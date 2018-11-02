package com.dwsj.service.impl;

import com.dwsj.common.PageQuery;
import com.dwsj.common.StatusQuery;
import com.dwsj.dao.QQPhoneDAO;
import com.dwsj.service.QQPhoneService;
import com.dwsj.vo.QQPhoneVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class QQPhoneServiceImpl implements QQPhoneService{
    @Resource
    private QQPhoneDAO qqPhoneDAO;
    @Override
    public void save(QQPhoneVo qqPhoneVo) {
        qqPhoneDAO.save(qqPhoneVo);
    }

    @Override
    public void remove(QQPhoneVo qqPhoneVo) {
        qqPhoneDAO.remove(qqPhoneVo);
    }

    @Override
    public void removeById(Long id) {
        qqPhoneDAO.removeById(id);
    }

    @Override
    public void update(QQPhoneVo qqPhoneVo) {
        qqPhoneDAO.update(qqPhoneVo);
    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {
        qqPhoneDAO.updateStatus(statusQuery);
    }

    @Override
    public QQPhoneVo getById(Long id) {
        return qqPhoneDAO.getById(id);
    }

    @Override
    public List<QQPhoneVo> listAll() {
        return qqPhoneDAO.listAll();
    }

    @Override
    public List<QQPhoneVo> listPage(PageQuery pageQuery) {
        return qqPhoneDAO.listPage(pageQuery);
    }

    @Override
    public long count(PageQuery pageQuery) {
        return qqPhoneDAO.count(pageQuery);
    }

    @Override
    public QQPhoneVo getInfoByQQ(String qq) {
        return qqPhoneDAO.getInfoByQQ(qq);
    }

    @Override
    public QQPhoneVo getInfoByHash(String hash) {
        return qqPhoneDAO.getInfoByHash(hash);
    }

    @Override
    public QQPhoneVo getInfo(QQPhoneVo qqPhoneVo) {
        return qqPhoneDAO.getInfo(qqPhoneVo);
    }
}
