package com.dwsj.service;
import com.dwsj.vo.AreaVo;
import com.dwsj.vo.Select2Vo;

import java.util.List;

public interface AreaService extends BaseService<AreaVo>{
    List<Select2Vo> getArea();
    AreaVo getAreaInfo(String code);
}
