package com.lagou.dao;

import com.lagou.domain.*;

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
     * 为角色分配菜单信息
     */
    public void RoleContextMenu(Role_menu_relation role_menu_relation);

    /*
        删除角色
     */
    public void deleteRole(Integer roleid);

    /**
     * 根据resource表中的category_id查询用户拥有的资源分类
     * @param ids
     * @return
     */
    public List<ResourceCategory> findAllResourceCategoryById(List<Integer> ids);

    /**
     * 根据roleID查询角色拥有的资源信息
     * @param roleId
     * @return
     */
    public List<Resource> findAllResourceByRoleId(Integer roleId);

    /*
        作业2.4   为角色分配资源
        步骤分析：
            1）、删除中间表role_resource_relation中与角色绑定的信息
            2）、在role_resource_relation表中插入最新的资源信息
     */

    /**
     * 根据角色ID删除中间表role_resource_relation中对应信息
     * @param roleId
     */
    public void deleteRoleResourceRelationByRoleId(Integer roleId);

    /**
     * 为角色分配资源
     * @param roleResourceRelation
     */
    public void saveRoleResourceRelation(RoleResourceRelation roleResourceRelation);
}
