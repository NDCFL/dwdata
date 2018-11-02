package com.dwsj.dao;

import com.dwsj.vo.PcUserConsumeVo;
import org.springframework.stereotype.Repository;

@Repository
public interface PcUserConsumeDAO extends BaseDAO<PcUserConsumeVo>{
    void updateStatusByOrderId(PcUserConsumeVo pcUserConsumeVo);
    PcUserConsumeVo getInfo(PcUserConsumeVo pcUserConsumeVo);
}
