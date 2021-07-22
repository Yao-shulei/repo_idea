package com.lagou.dao;

import com.lagou.domain.Role;
import com.lagou.domain.Role_menu_relation;

import java.util.List;

public interface RoleMapper {

    /**
     * 查询所有角色&根据条件进行查询
     */
    public List<Role> findAllRole(Role role);

    /**
     * 根据角色ID查询关联菜单ID
     */
    public List<Integer> findMenuByRoleId(Integer roleid);

    /**
     * 根据roleid清空中间表的关联关系
     */
    public void deleteRoleContextMenu(Integer rid);

    /**
     * 位角色分配菜单信息
     */
    public void RoleContextMenu(Role_menu_relation role_menu_relation);

    /*
        删除角色
     */
    public void deleteRole(Integer roleid);
}
