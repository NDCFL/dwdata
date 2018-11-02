package com.dwsj.dao;

import com.dwsj.vo.AreaVo;
import com.dwsj.vo.Select2Vo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaDAO extends BaseDAO<AreaVo> {
    List<Select2Vo> getArea();
    AreaVo getAreaInfo(String code);
}
