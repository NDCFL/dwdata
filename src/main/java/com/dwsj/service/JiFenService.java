package com.dwsj.service;

import com.dwsj.vo.JiFenVo;
import com.dwsj.vo.Select2Vo;
import org.springframework.stereotype.Service;

import java.util.List;

public interface JiFenService extends BaseService<JiFenVo>{
    List<JiFenVo> getJiFen();
    JiFenVo getJiFenInfo(Integer amount);
}
