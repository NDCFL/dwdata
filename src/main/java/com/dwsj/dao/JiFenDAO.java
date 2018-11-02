package com.dwsj.dao;

import com.dwsj.vo.JiFenVo;
import com.dwsj.vo.Select2Vo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JiFenDAO extends BaseDAO<JiFenVo>{
    List<JiFenVo> getJiFen();
    JiFenVo getJiFenInfo(Integer amount);
}
