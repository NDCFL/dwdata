package com.dwsj.service.impl;

import com.dwsj.common.PageQuery;
import com.dwsj.common.StatusQuery;
import com.dwsj.dao.CarrieroperatorDAO;
import com.dwsj.service.CarrieroperatorService;
import com.dwsj.vo.CarrieroperatorVo;
import com.dwsj.vo.CarrieroperatorVo;
import com.dwsj.vo.Select2Vo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class CarrieroperatorServiceImpl implements CarrieroperatorService {
    @Resource
    private CarrieroperatorDAO carrieroperatorDAO;

    @Override
    public void save(CarrieroperatorVo carrieroperatorVo) {
        carrieroperatorDAO.save(carrieroperatorVo);
    }

    @Override
    public void remove(CarrieroperatorVo carrieroperatorVo) {
        carrieroperatorDAO.remove(carrieroperatorVo);
    }

    @Override
    public void removeById(Long id) {
        carrieroperatorDAO.removeById(id);
    }

    @Override
    public void update(CarrieroperatorVo carrieroperatorVo) {
        carrieroperatorDAO.update(carrieroperatorVo);
    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {
        carrieroperatorDAO.updateStatus(statusQuery);
    }

    @Override
    public CarrieroperatorVo getById(Long id) {
        return carrieroperatorDAO.getById(id);
    }

    @Override
    public List<CarrieroperatorVo> listAll() {
        return carrieroperatorDAO.listAll();
    }

    @Override
    public List<CarrieroperatorVo> listPage(PageQuery pageQuery) {
        return carrieroperatorDAO.listPage(pageQuery);
    }

    @Override
    public long count(PageQuery pageQuery) {
        return carrieroperatorDAO.count(pageQuery);
    }

    @Override
    public void updateUrl(Long id,String url) {
        carrieroperatorDAO.updateUrl(id, url);
    }

    @Override
    public void updateStatusBySid(String sid, Integer status) {
        carrieroperatorDAO.updateStatusBySid(sid, status);
    }

    @Override
    public CarrieroperatorVo getInfoBySid(String sid) {
        return carrieroperatorDAO.getInfoBySid(sid);
    }

    @Override
    public void updateStatusBySid(CarrieroperatorVo carrieroperatorVo) {
        carrieroperatorDAO.updateStatusBySid(carrieroperatorVo);
    }
}
