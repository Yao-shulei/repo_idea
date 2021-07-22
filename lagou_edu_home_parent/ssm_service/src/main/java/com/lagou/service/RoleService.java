package com.lagou.service;

import com.lagou.domain.ResourceCategory;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVO;
import com.lagou.domain.RoleResourceVo;

import java.util.List;

public interface RoleService {

    /**
     * 查询所有角色&根据条件进行查询
     */
    public List<Role> findAllRole(Role role);


    /**
     * 根据角色ID查询关联菜单ID
     */
    public List<Integer> findMenuByRoleId(Integer roleid);

    /*
        为角色分配菜单
     */
    public void roleContextMenu(RoleMenuVO roleMenuVO);

    /*
        删除角色
     */
    public void deleteRole(Integer roleid);

    /**
     * 获取当前角色拥有的资源信息
     * @param id
     * @return
     */
    public List<ResourceCategory> findResourceListByRoleId(Integer id);

    /**
     * 为角色分配资源
     * @param roleResourceVo
     */
    public void roleContextResource(RoleResourceVo roleResourceVo);
}
