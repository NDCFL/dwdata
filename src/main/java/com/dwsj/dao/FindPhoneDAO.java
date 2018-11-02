package com.dwsj.dao;

import com.dwsj.vo.FindPhoneVo;
import com.dwsj.vo.FindPoliceVo;
import org.springframework.stereotype.Repository;

@Repository
public interface FindPhoneDAO extends BaseDAO<FindPhoneVo>{
    FindPhoneVo getFindPhoneInfo(FindPhoneVo findPhoneVo);
    int getCnt(FindPhoneVo findPhoneVo);
}
