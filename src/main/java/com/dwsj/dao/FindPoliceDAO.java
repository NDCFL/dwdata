package com.dwsj.dao;

import com.dwsj.vo.FindPoliceVo;
import org.springframework.stereotype.Repository;

@Repository
public interface FindPoliceDAO extends BaseDAO<FindPoliceVo>{
    FindPoliceVo getFindPoliceInfo(FindPoliceVo findPoliceVo);
    int getCnt(FindPoliceVo findPoliceVo);
}
