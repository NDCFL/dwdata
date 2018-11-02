package com.dwsj.dao;

import com.dwsj.vo.QQPhoneListVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QQPhoneListDAO extends BaseDAO<QQPhoneListVo>{
    void saveList(List<QQPhoneListVo> qqPhoneListVos);
    List<QQPhoneListVo> getList(Long id);
}
