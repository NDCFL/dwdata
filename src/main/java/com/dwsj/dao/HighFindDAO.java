package com.dwsj.dao;

import com.dwsj.vo.HighFindVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HighFindDAO  extends BaseDAO<HighFindVo>{
//    void update
    HighFindVo getInfo(HighFindVo highFindVo);
    HighFindVo getStatus(Long id);
    HighFindVo getBaseInfo(@Param("pcUserId") Long pcUserId, @Param("kehuId") Long kehuId);
    HighFindVo getInfoByOrderId(String orderId);
}
