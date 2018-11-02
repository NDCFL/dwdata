package com.dwsj.dao;

import com.dwsj.vo.RoleVo;
import com.dwsj.vo.Select2Vo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * RoleDAO接口<br />
 * 创建于2017-09-11
 *
 * @author 陈飞龙
 * @version 1.0
 */
@Repository
public interface RoleDAO extends BaseDAO<RoleVo> {

    /**
     * 根据用户账户查找对应的所有角色
     * @param account 用户账户，可能是邮箱，手机号或账户名
     * @return 指定用户账户对应的所有角色组成的列表
     */
    List<RoleVo> listByAccount(String account);
    List<Select2Vo> getRoleIdAndTitle();
    RoleVo findByName(String title);
}
