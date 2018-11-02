package com.dwsj.dao;

import com.dwsj.common.UserAccountPasswordQuery;
import com.dwsj.vo.Select2Vo;
import com.dwsj.vo.UserPCVo;
import com.dwsj.vo.UserVo;
import org.apache.ibatis.annotations.Param;
import org.h2.engine.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPCDAO extends BaseDAO<UserPCVo>{
    UserPCVo getByAccountPassword(UserAccountPasswordQuery userAccountPasswordQuery);
    int checkReg(String phone);
    int checkLogin(String account);
    void updatePwd(@Param("id") long id, @Param("pwd") String pwd);
    void updatePhone(@Param("id") long id, @Param("phone") String phone);
    String getPassword(long id);
    List<Select2Vo> getUser();
    UserPCVo findByPhone(String phone);
    void setPwd(@Param("phone") String phone, @Param("pwd") String pwd);
    void addKeHu(UserPCVo userPCVo);
    void updateJiFen(UserPCVo userPCVo);
    void updateGive(UserPCVo userPCVo);
}
