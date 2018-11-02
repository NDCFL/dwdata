package com.dwsj.dao;

import com.dwsj.vo.GeneralQueryVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneralQueryDAO extends BaseDAO<GeneralQueryVo> {

    /**
     * 根据用户id和借款人id获取其最后一次查询的id
     * @return
     */
    GeneralQueryVo selectByUserIdAndKeHuId(@Param("pcUserId") Long pcUserId, @Param("kehuId") Long kehuId);
    Long getInfoById(@Param("pcUserId") Long pcUserId, @Param("kehuId") Long kehuId);
}