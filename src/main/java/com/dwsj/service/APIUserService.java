package com.dwsj.service;

import com.dwsj.vo.APIUserVo;
import com.dwsj.vo.UserPCVo;
import com.dwsj.vo.UserVo;

public interface APIUserService extends BaseService<APIUserVo>{
    //验证用户
    APIUserVo checkUser(APIUserVo apiUserVo);
    //消费减去金额
    void reduceMoney(APIUserVo apiUserVo);
    //充值新增金额
    void addMoney(APIUserVo apiUserVo);
}
