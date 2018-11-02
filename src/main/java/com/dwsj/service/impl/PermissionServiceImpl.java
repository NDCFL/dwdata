package com.dwsj.service.impl;

import com.dwsj.common.DozerMapperUtils;
import com.dwsj.common.PageQuery;
import com.dwsj.common.StatusQuery;
import com.dwsj.dao.PermissionDAO;
import com.dwsj.service.AbstractBaseService;
import com.dwsj.service.PermissionService;
import com.dwsj.vo.PermissionVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限Service实现类<br />
 * 创建于2017-09-11
 *
 * @author 陈飞龙
 * @version 1.0
 */
@Service
public class PermissionServiceImpl extends AbstractBaseService implements PermissionService {

    @Resource
    private PermissionDAO permissionDAO;

    @Override
    public List<PermissionVo> listByModuleId(String moduleId) {
        return null;
    }

    @Override
    public List<PermissionVo> listByRoleId(String roleId) {
        return null;
    }

    @Override
    public List<PermissionVo> listByRoleIds(List<Long> roleIds) {
        try {
            List<PermissionVo> permissionDOList = permissionDAO.listByRoleIds(roleIds);
            return DozerMapperUtils.map(getDozerMapper(), permissionDOList, PermissionVo.class);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @Override
    public List<PermissionVo> listByAccount(String account) {
        try {
            List<PermissionVo> permissionDOList = permissionDAO.listByAccount(account);
            return DozerMapperUtils.map(getDozerMapper(), permissionDOList, PermissionVo.class);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @Override
    public void save(PermissionVo permissionDTO) {

    }

    @Override
    public void remove(PermissionVo permissionDTO) {

    }

    @Override
    public void removeById(Long id) {

    }

    @Override
    public void update(PermissionVo permissionDTO) {

    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {

    }

    @Override
    public PermissionVo getById(Long id) {
        return null;
    }

    @Override
    public List<PermissionVo> listAll() {
        return null;
    }

    @Override
    public List<PermissionVo> listPage(PageQuery pageQuery) {
        return null;
    }

    @Override
    public long count(PageQuery pageQuery) {
        return 0;
    }
}
