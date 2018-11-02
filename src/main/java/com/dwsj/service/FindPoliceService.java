package com.dwsj.service;

import com.dwsj.vo.FindPoliceVo;

public interface FindPoliceService extends BaseService<FindPoliceVo>{
    FindPoliceVo getFindPoliceInfo(FindPoliceVo findPoliceVo);
    int getCnt(FindPoliceVo findPoliceVo);
}
