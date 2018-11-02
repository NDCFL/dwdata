package com.dwsj.service.impl;

import com.dwsj.common.PageQuery;
import com.dwsj.common.StatusQuery;
import com.dwsj.dao.GeneralQueryDAO;
import com.dwsj.service.GeneralQueryService;
import com.dwsj.vo.GeneralQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("")
public class GeneralQueryServiceImpl implements GeneralQueryService {

	@Resource
	private GeneralQueryDAO generalQueryDAO;

	@Override
	public void save(GeneralQueryVo generalQueryVo) {
		generalQueryDAO.save(generalQueryVo);
	}

	@Override
	public void remove(GeneralQueryVo generalQueryVo) {
		generalQueryDAO.remove(generalQueryVo);
	}

	@Override
	public void removeById(Long id) {
		generalQueryDAO.removeById(id);
	}

	@Override
	public void update(GeneralQueryVo generalQueryVo) {
		generalQueryDAO.update(generalQueryVo);
	}

	@Override
	public void updateStatus(StatusQuery statusQuery) {
		generalQueryDAO.updateStatus(statusQuery);
	}

	@Override
	public GeneralQueryVo getById(Long id) {
		return generalQueryDAO.getById(id);
	}

	@Override
	public List<GeneralQueryVo> listAll() {
		return generalQueryDAO.listAll();
	}

	@Override
	public List<GeneralQueryVo> listPage(PageQuery pageQuery) {
		return generalQueryDAO.listPage(pageQuery);
	}

	@Override
	public long count(PageQuery pageQuery) {
		return generalQueryDAO.count(pageQuery);
	}

	@Override
	public GeneralQueryVo selectByUserIdAndKeHuId(Long pcUserId, Long kehuId) {
		return generalQueryDAO.selectByUserIdAndKeHuId(pcUserId, kehuId);
	}

	@Override
	public Long getInfoById(Long pcUserId, Long kehuId) {
		return generalQueryDAO.getInfoById(pcUserId, kehuId);
	}
}
