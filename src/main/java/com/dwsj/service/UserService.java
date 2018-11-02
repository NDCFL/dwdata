package com.dwsj.service;

import com.dwsj.common.UserAccountPasswordQuery;
import com.dwsj.vo.Select2Vo;
import com.dwsj.vo.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService extends BaseService<UserVo>{
    UserVo getByAccountPassword(UserAccountPasswordQuery userAccountPasswordQuery);
    int checkReg(String phone);
    int checkLogin(String account);
    void updatePwd(@Param("id") long id, @Param("pwd") String pwd);
    void updatePhone(@Param("id") long id,@Param("phone") String phone);
    String getPassword(long id);
    List<Select2Vo> getUser();
    UserVo findByPhone(String phone);
    List<Select2Vo> getUserIdAndName();
}
