package com.dwsj.dao;

import com.dwsj.vo.PcUserRemarkVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PcUserRemarkDAO extends BaseDAO<PcUserRemarkVo>{
    List<PcUserRemarkVo> getAllList(@Param("id") Long id, @Param("kehuId") Long kehuId);
}
