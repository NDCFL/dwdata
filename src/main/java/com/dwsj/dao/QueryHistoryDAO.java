package com.dwsj.dao;

import com.dwsj.vo.QueryHistoryVo;
import com.dwsj.vo.UserPCVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QueryHistoryDAO extends BaseDAO<QueryHistoryVo>{
    /**
     * 获取用户对该借款人查询的过借贷/运营商等数据
     * @param kehuId
     * @return
     */
    List<QueryHistoryVo> getQueryLenderHistory(@Param("pcUserId") Long pcUserId, @Param("kehuId") long kehuId);
    /**
     * 创建查询记录并对本次查询进行扣费
     * @param  //当本次查询为淘宝、通讯录等不涉及借款人基本信息的类型时，lenderId可为空
     * @param type type为定价表中该种查询所对应的id
     * @param typeId 则为本次查询的id,如黑爬虫表的id或京东查询id
     */
    Boolean createQueryHistory(@Param("kehuId") Long kehuId,@Param("type") int type,@Param("typeId") Long typeId,@Param("pcUserId") Long pcUserId);

    /**
     * 只检验余额
     * @param type
     * @return
     */
    Boolean checkBalance(@Param("userPCVo") UserPCVo userPCVo, @Param("type") int type);
}
