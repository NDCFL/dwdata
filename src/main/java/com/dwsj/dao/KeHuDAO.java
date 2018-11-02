package com.dwsj.dao;

import com.dwsj.common.PageQuery;
import com.dwsj.vo.CountVo;
import com.dwsj.vo.KeHuVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeHuDAO extends BaseDAO<KeHuVo>{
    KeHuVo checkKeHu(KeHuVo keHuVo);
    KeHuVo getKeHuInfo(KeHuVo keHuVo);
    List<KeHuVo> listPages(@Param("pageQuery") PageQuery pageQuery, @Param("keHuVo") KeHuVo keHuVo);
    Long counts(@Param("pageQuery") PageQuery pageQuery, @Param("keHuVo") KeHuVo keHuVo);
    void updateTime(Long id);
    CountVo getCount(Long id);
    void updatePhone(@Param("phone") String phone,@Param("id") Long id);
    int cnt(KeHuVo keHuVo);
    void  updateXiaoDai(@Param("id") Long id,@Param("result") String result);
    void updateRemark(KeHuVo keHuVo);
}
