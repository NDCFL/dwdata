package com.dwsj.service;

import com.dwsj.common.UserAccountPasswordQuery;
import com.dwsj.vo.Select2Vo;
import com.dwsj.vo.UserPCVo;
import com.dwsj.vo.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserPCService extends BaseService<UserPCVo>{
    UserPCVo getByAccountPassword(UserAccountPasswordQuery userAccountPasswordQuery);
    int checkReg(String phone);
    int checkLogin(String account);
    void updatePwd(long id,String pwd);
    void updatePhone(long id,String phone);
    String getPassword(long id);
    List<Select2Vo> getUser();
    UserPCVo findByPhone(String phone);
    void setPwd(String phone,String pwd);
    void addKeHu(UserPCVo userPCVo);
    void updateJiFen(UserPCVo userPCVo);
    void updateGive(UserPCVo userPCVo);
}
