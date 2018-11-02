package com.dwsj.service;

import com.dwsj.vo.FindPhoneVo;
import com.dwsj.vo.FindPoliceVo;

public interface FindPhoneService extends BaseService<FindPhoneVo>{
    FindPhoneVo getFindPhoneInfo(FindPhoneVo findPhoneVo);
    int getCnt(FindPhoneVo findPhoneVo);
}
