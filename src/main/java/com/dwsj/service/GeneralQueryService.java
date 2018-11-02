package com.dwsj.service;

import com.dwsj.vo.GeneralQueryVo;
import org.apache.ibatis.annotations.Param;

public interface GeneralQueryService extends BaseService<GeneralQueryVo>{
    /**
     * 根据用户id和借款人id获取其最后一次查询的id
     * @return
     */
    GeneralQueryVo selectByUserIdAndKeHuId(Long pcUserId, Long kehuId);
    Long getInfoById(Long pcUserId,Long kehuId);
}