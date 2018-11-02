package com.dwsj.dao;

import com.dwsj.vo.CarrieroperatorVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrieroperatorDAO extends BaseDAO<CarrieroperatorVo>{
    void updateUrl(@Param("id") Long id, @Param("url") String url);
    void updateStatusBySid(@Param("sid")String sid,@Param("status")Integer status);
    CarrieroperatorVo getInfoBySid(String sid);
    void updateStatusBySid(CarrieroperatorVo carrieroperatorVo);
}
