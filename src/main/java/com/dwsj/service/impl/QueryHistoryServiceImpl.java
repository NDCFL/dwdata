package com.dwsj.service.impl;

import com.dwsj.common.PageQuery;
import com.dwsj.common.StatusQuery;
import com.dwsj.dao.ConsumeHistoryDAO;
import com.dwsj.dao.FixedPriceDAO;
import com.dwsj.dao.QueryHistoryDAO;
import com.dwsj.dao.UserPCDAO;
import com.dwsj.service.ConsumeHistoryService;
import com.dwsj.service.QueryHistoryService;
import com.dwsj.vo.ConsumeHistoryVo;
import com.dwsj.vo.FixedPriceVo;
import com.dwsj.vo.QueryHistoryVo;
import com.dwsj.vo.UserPCVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class QueryHistoryServiceImpl implements QueryHistoryService {

    @Resource
    private QueryHistoryDAO queryHistoryDAO;
    @Resource
    private FixedPriceDAO fixedPriceDAO;
    @Resource
    private UserPCDAO userPCDAO;
    @Resource
    private ConsumeHistoryDAO consumeHistoryDAO;
    @Override
    public void save(QueryHistoryVo queryHistoryVo) {
        queryHistoryDAO.save(queryHistoryVo);
    }

    @Override
    public void remove(QueryHistoryVo queryHistoryVo) {
        queryHistoryDAO.remove(queryHistoryVo);
    }

    @Override
    public void removeById(Long id) {
        queryHistoryDAO.removeById(id);
    }

    @Override
    public void update(QueryHistoryVo queryHistoryVo) {
        queryHistoryDAO.update(queryHistoryVo);
    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {
        queryHistoryDAO.updateStatus(statusQuery);
    }

    @Override
    public QueryHistoryVo getById(Long id) {
        return queryHistoryDAO.getById(id);
    }

    @Override
    public List<QueryHistoryVo> listAll() {
        return queryHistoryDAO.listAll();
    }

    @Override
    public List<QueryHistoryVo> listPage(PageQuery pageQuery) {
        return queryHistoryDAO.listPage(pageQuery);
    }

    @Override
    public long count(PageQuery pageQuery) {
        return queryHistoryDAO.count(pageQuery);
    }

    @Override
    public List<QueryHistoryVo> getQueryLenderHistory(Long userId, long lenderId) {
        return queryHistoryDAO.getQueryLenderHistory(userId, lenderId);
    }

    @Override
    public Boolean createQueryHistory(Long kehuid, int type, Long typeId, Long userPcId) {
        QueryHistoryVo queryHistory  = new QueryHistoryVo();
        queryHistory.setCreateTime(new Date());
        queryHistory.setKehuId(kehuid);
        queryHistory.setType(type);//写死
        queryHistory.setTypeCorrespondId(typeId);
        queryHistory.setPcUserId(userPcId);
        queryHistoryDAO.save(queryHistory);
        FixedPriceVo fixedPrice  = fixedPriceDAO.getById(Long.parseLong(type+""));
        //判断费用是否够扣
        UserPCVo userPCVo = userPCDAO.getById(userPcId);
        if (fixedPrice == null || userPCVo.getJifen()-fixedPrice.getPrice()< 0) {
            return false;
        }
        ConsumeHistoryVo consumeHistory = new ConsumeHistoryVo();
        consumeHistory.setAmount(fixedPrice.getPrice());
        consumeHistory.setCreateTime(new Date());
        consumeHistory.setPcUserId(userPcId);
        consumeHistory.setRemark(fixedPrice.getName()+"-"+fixedPrice.getPrice().toString()+"积分");
        consumeHistoryDAO.save(consumeHistory);
        userPCVo.setJifen(userPCVo.getJifen()-fixedPrice.getPrice());
        //修改用户的积分
        userPCDAO.updateJiFen(userPCVo);
        return true;
    }

    @Override
    public Boolean checkBalance(UserPCVo userPCVo, int type) {
        FixedPriceVo fixedPrice  = fixedPriceDAO.getById(Long.parseLong(type+""));
        if (fixedPrice == null || userPCVo.getJifen()-fixedPrice.getPrice()< 0) {
            return false;
        }else {
            return true;
        }
    }
}
