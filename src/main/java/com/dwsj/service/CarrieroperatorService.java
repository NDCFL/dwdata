package com.dwsj.service;

import com.dwsj.vo.CarrieroperatorVo;
import org.apache.ibatis.annotations.Param;

public interface CarrieroperatorService extends BaseService<CarrieroperatorVo>{
    void updateUrl(Long id,String url);
    void updateStatusBySid(String sid,Integer status);
    CarrieroperatorVo getInfoBySid(String sid);
    void updateStatusBySid(CarrieroperatorVo carrieroperatorVo);
}
