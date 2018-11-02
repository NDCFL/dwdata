package com.dwsj.service;

import com.dwsj.common.PageQuery;
import com.dwsj.vo.CountVo;
import com.dwsj.vo.KeHuVo;
import com.dwsj.vo.UserPCVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface KeHuService extends BaseService<KeHuVo>{
    KeHuVo checkKeHu(KeHuVo keHuVo);
    KeHuVo getKeHuInfo(KeHuVo keHuVo);
    List<KeHuVo> listPages(PageQuery pageQuery, KeHuVo keHuVo);
    Long counts(PageQuery pageQuery,KeHuVo keHuVo);
    void updateTime(Long id);
    CountVo getCount(Long id);
    void updatePhone(String phone,Long id);
    int cnt(KeHuVo keHuVo);
    void  updateXiaoDai(Long id,String result);
    void updateRemark(KeHuVo keHuVo);

}
