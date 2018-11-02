package com.dwsj.service;

import com.dwsj.vo.PcUserRemarkVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PcUserRemarkService extends BaseService<PcUserRemarkVo>{
    List<PcUserRemarkVo> getAllList(Long id,Long kehuId);
}
