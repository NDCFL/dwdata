package com.dwsj.service.impl;

import com.dwsj.common.PageQuery;
import com.dwsj.common.StatusQuery;
import com.dwsj.dao.APIUserDAO;
import com.dwsj.service.APIUserService;
import com.dwsj.vo.APIUserVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class APIUserServiceImpl implements APIUserService {
    @Resource
    private APIUserDAO apiUserDAO;
    @Override
    public void save(APIUserVo userVo) {

    }

    @Override
    public void remove(APIUserVo userVo) {

    }

    @Override
    public void removeById(Long id) {

    }

    @Override
    public void update(APIUserVo userVo) {
        apiUserDAO.update(userVo);
    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {

    }

    @Override
    public APIUserVo getById(Long id) {
        return apiUserDAO.getById(id);
    }

    @Override
    public List<APIUserVo> listAll() {
        return null;
    }

    @Override
    public List<APIUserVo> listPage(PageQuery pageQuery) {
        return null;
    }

    @Override
    public long count(PageQuery pageQuery) {
        return 0;
    }

    @Override
    public APIUserVo checkUser(APIUserVo apiUserVo) {
        return apiUserDAO.checkUser(apiUserVo);
    }

    @Override
    public void reduceMoney(APIUserVo apiUserVo) {
        apiUserDAO.reduceMoney(apiUserVo);
    }

    @Override
    public void addMoney(APIUserVo apiUserVo) {
        apiUserDAO.addMoney(apiUserVo);
    }
}
