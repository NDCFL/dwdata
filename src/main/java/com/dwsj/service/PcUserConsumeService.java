package com.dwsj.service;

import com.dwsj.vo.PcUserConsumeVo;

public interface  PcUserConsumeService extends BaseService<PcUserConsumeVo>{
    void updateStatusByOrderId(PcUserConsumeVo pcUserConsumeVo);
    PcUserConsumeVo getInfo(PcUserConsumeVo pcUserConsumeVo);
}
