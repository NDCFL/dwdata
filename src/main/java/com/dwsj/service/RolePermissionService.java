package com.dwsj.service;


import com.dwsj.dao.BaseDAO;
import com.dwsj.vo.RolePermissionVo;

import java.util.List;

/**
 * Created by chenfeilong on 2017/10/27.
 */
public interface RolePermissionService extends BaseDAO<RolePermissionVo> {
    List<String> pageListByRoleId(String roleId);
}
