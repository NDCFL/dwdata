package com.dwsj.service;

import com.dwsj.vo.QQPhoneListVo;

import java.util.List;

public interface QQPhoneListService extends BaseService<QQPhoneListVo>{
    void saveList(List<QQPhoneListVo> qqPhoneListVos);
    List<QQPhoneListVo> getList(Long id);
}
