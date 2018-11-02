package com.dwsj.service.impl;

import com.dwsj.common.PageQuery;
import com.dwsj.common.StatusQuery;
import com.dwsj.dao.ReplaceableCookieDAO;
import com.dwsj.service.ReplaceableCookieService;
import com.dwsj.vo.ReplaceableCookieVo;
import com.dwsj.vo.ReplaceableCookieVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class ReplaceableCookieServiceImpl implements ReplaceableCookieService {
    @Resource
    private ReplaceableCookieDAO replaceableCookieDAO;
    @Override
    public void save(ReplaceableCookieVo replaceableCookieVo) {
        replaceableCookieDAO.save(replaceableCookieVo);
    }

    @Override
    public void remove(ReplaceableCookieVo replaceableCookieVo) {
        replaceableCookieDAO.remove(replaceableCookieVo);
    }

    @Override
    public void removeById(Long id) {
        replaceableCookieDAO.removeById(id);
    }

    @Override
    public void update(ReplaceableCookieVo replaceableCookieVo) {
        replaceableCookieDAO.update(replaceableCookieVo);
    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {
        replaceableCookieDAO.updateStatus(statusQuery);
    }

    @Override
    public ReplaceableCookieVo getById(Long id) {
        return replaceableCookieDAO.getById(id);
    }

    @Override
    public List<ReplaceableCookieVo> listAll() {
        return replaceableCookieDAO.listAll();
    }

    @Override
    public List<ReplaceableCookieVo> listPage(PageQuery pageQuery) {
        return replaceableCookieDAO.listPage(pageQuery);
    }

    @Override
    public long count(PageQuery pageQuery) {
        return replaceableCookieDAO.count(pageQuery);
    }
}
