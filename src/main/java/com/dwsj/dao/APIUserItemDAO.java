package com.dwsj.dao;

import com.dwsj.vo.APIUserItemVo;
import com.dwsj.vo.APIUserVo;
import org.springframework.stereotype.Repository;

@Repository
public interface APIUserItemDAO extends BaseDAO<APIUserItemVo>{
    APIUserItemVo getItem(String taskId);
    APIUserItemVo getInfoByPhone(String phone);
}
