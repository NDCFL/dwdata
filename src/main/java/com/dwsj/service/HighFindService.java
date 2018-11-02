package com.dwsj.service;

import com.dwsj.vo.HighFindVo;
import org.apache.ibatis.annotations.Param;

public interface HighFindService extends BaseService<HighFindVo>{
    HighFindVo getInfo(HighFindVo highFindVo);
    HighFindVo getStatus(Long id);
    HighFindVo getBaseInfo(Long pcUserId, Long kehuId);
    HighFindVo getInfoByOrderId(String orderId);
}
