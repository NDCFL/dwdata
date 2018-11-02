package com.dwsj.service.impl;

import com.dwsj.common.PageQuery;
import com.dwsj.common.StatusQuery;
import com.dwsj.common.UserAccountPasswordQuery;
import com.dwsj.dao.UserDAO;
import com.dwsj.dao.UserPCDAO;
import com.dwsj.service.UserPCService;
import com.dwsj.service.UserService;
import com.dwsj.vo.Select2Vo;
import com.dwsj.vo.UserPCVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserPCServiceImpl implements UserPCService {

    @Resource
    private UserPCDAO userPCDAO;
    public void save(UserPCVo userPCVo) {
        userPCDAO.save(userPCVo);
    }

    public void remove(UserPCVo userPCVo) {
        userPCDAO.remove(userPCVo);
    }

    public void removeById(Long id) {
        userPCDAO.removeById(id);
    }

    public void update(UserPCVo userPCVo) {
        userPCDAO.update(userPCVo);
    }

    public void updateStatus(StatusQuery statusQuery) {
        userPCDAO.updateStatus(statusQuery);
    }

    public UserPCVo getById(Long id) {
        return userPCDAO.getById(id);
    }

    public List<UserPCVo> listAll() {
        return userPCDAO.listAll();
    }

    public List<UserPCVo> listPage(PageQuery pageQuery) {
        return userPCDAO.listPage(pageQuery);
    }

    public long count(PageQuery pageQuery) {
        return userPCDAO.count(pageQuery);
    }

    public UserPCVo getByAccountPassword(UserAccountPasswordQuery userAccountPasswordQuery) {
        return userPCDAO.getByAccountPassword(userAccountPasswordQuery);
    }

    public int checkReg(String phone) {
        return userPCDAO.checkReg(phone);
    }

    public int checkLogin(String account) {
        return userPCDAO.checkLogin(account);
    }

    public void updatePwd(long id, String pwd) {
        userPCDAO.updatePwd(id, pwd);
    }

    public void updatePhone(long id, String phone) {
        userPCDAO.updatePhone(id, phone);
    }

    public String getPassword(long id) {
        return userPCDAO.getPassword(id);
    }

    public List<Select2Vo> getUser() {
        return userPCDAO.getUser();
    }

    public UserPCVo findByPhone(String phone) {
        return userPCDAO.findByPhone(phone);
    }

    @Override
    public void setPwd(String phone, String pwd) {
        userPCDAO.setPwd(phone, pwd);
    }

    @Override
    public void addKeHu(UserPCVo userPCVo) {
        userPCDAO.addKeHu(userPCVo);
    }

    @Override
    public void updateJiFen(UserPCVo userPCVo) {
        userPCDAO.updateJiFen(userPCVo);
    }

    @Override
    public void updateGive(UserPCVo userPCVo) {
        userPCDAO.updateGive(userPCVo);
    }
}
