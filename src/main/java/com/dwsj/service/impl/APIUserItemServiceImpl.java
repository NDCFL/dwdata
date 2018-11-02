package com.dwsj.service.impl;

import com.dwsj.common.PageQuery;
import com.dwsj.common.StatusQuery;
import com.dwsj.dao.APIUserItemDAO;
import com.dwsj.service.APIUserItemService;
import com.dwsj.vo.APIUserVo;
import com.dwsj.vo.APIUserItemVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class APIUserItemServiceImpl implements APIUserItemService {
    @Resource
    private APIUserItemDAO apiUserItemDAO;

    @Override
    public void save(APIUserItemVo apiUserItemVo) {
        apiUserItemDAO.save(apiUserItemVo);
    }

    @Override
    public void remove(APIUserItemVo apiUserItemVo) {
        apiUserItemDAO.remove(apiUserItemVo);
    }

    @Override
    public void removeById(Long id) {
        apiUserItemDAO.removeById(id);
    }

    @Override
    public void update(APIUserItemVo apiUserItemVo) {
        apiUserItemDAO.update(apiUserItemVo);
    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {
        apiUserItemDAO.updateStatus(statusQuery);
    }

    @Override
    public APIUserItemVo getById(Long id) {
        return apiUserItemDAO.getById(id);
    }

    @Override
    public List<APIUserItemVo> listAll() {
        return apiUserItemDAO.listAll();
    }

    @Override
    public List<APIUserItemVo> listPage(PageQuery pageQuery) {
        return apiUserItemDAO.listPage(pageQuery);
    }

    @Override
    public long count(PageQuery pageQuery) {
        return apiUserItemDAO.count(pageQuery);
    }

    @Override
    public APIUserItemVo getItem(String taskId) {
        return apiUserItemDAO.getItem(taskId);
    }

    @Override
    public APIUserItemVo getInfoByPhone(String phone) {
        return apiUserItemDAO.getInfoByPhone(phone);
    }
}
