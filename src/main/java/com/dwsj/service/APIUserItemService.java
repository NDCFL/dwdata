package com.dwsj.service;

import com.dwsj.vo.APIUserItemVo;
import com.dwsj.vo.APIUserVo;

public interface APIUserItemService extends BaseService<APIUserItemVo>{
    APIUserItemVo getItem(String taskId);
    APIUserItemVo getInfoByPhone(String phone);
}
