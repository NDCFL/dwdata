package com.dwsj.service;

import com.dwsj.vo.QueryHistoryVo;
import com.dwsj.vo.UserPCVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QueryHistoryService  extends BaseService<QueryHistoryVo>{
    /**
     * 获取用户对该借款人查询的过借贷/运营商等数据
     * @param lenderId
     * @return
     */
    List<QueryHistoryVo> getQueryLenderHistory(Long userId, long lenderId);
    /**
     * 创建查询记录并对本次查询进行扣费
     * @param kehuid 当本次查询为淘宝、通讯录等不涉及借款人基本信息的类型时，lenderId可为空
     * @param type type为定价表中该种查询所对应的id
     * @param typeId 则为本次查询的id,如黑爬虫表的id或京东查询id
     */
    Boolean createQueryHistory(Long kehuid, int type, Long typeId, Long userPcId);

    /**
     * 只检验余额
     * @param type
     * @return
     */
    Boolean checkBalance(UserPCVo userPCVo, int type);
}
