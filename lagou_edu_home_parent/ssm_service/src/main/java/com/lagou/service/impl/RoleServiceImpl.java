package com.lagou.service.impl;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.*;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAllRole(Role role) {
        List<Role> allRole = roleMapper.findAllRole(role);
        return allRole;
    }

    @Override
    public List<Integer> findMenuByRoleId(Integer roleid) {
        List<Integer> menuByRoleId = roleMapper.findMenuByRoleId(roleid);
        return menuByRoleId;
    }

    @Override
    public void roleContextMenu(RoleMenuVO roleMenuVO) {

        //1、清空中间表的关联关系
        roleMapper.deleteRoleContextMenu(roleMenuVO.getRoleId());

        //2、为角色分配菜单
        for (Integer mid : roleMenuVO.getMenuIdList()){

            Role_menu_relation role_menu_relation = new Role_menu_relation();
            role_menu_relation.setMenuId(mid);
            role_menu_relation.setRoleId(roleMenuVO.getRoleId());

            //封装数据
            Date date = new Date();
            role_menu_relation.setCreatedTime(date);
            role_menu_relation.setUpdatedTime(date);

            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedby("system");

            roleMapper.RoleContextMenu(role_menu_relation);
        }

    }

    @Override
    public void deleteRole(Integer roleid) {

        //调用根据roleid清空中间表关联关系
        roleMapper.deleteRoleContextMenu(roleid);

        roleMapper.deleteRole(roleid);

    }

    /**
     * 作业2.3
     * 获取当前角色拥有的资源信息
     * @param id
     * @return
     */
    @Override
    public List<ResourceCategory> findResourceListByRoleId(Integer id) {
        //调用findAllResourceByRoleId方法获得全部的用户拥有的资源信息
        List<Resource> resourceList = roleMapper.findAllResourceByRoleId(id);

        //将资源信息中的resourcecategoryid添加到信息的list集合中
        List<Integer> resourceCategoryIdList = new ArrayList<>();
        for (Resource resource : resourceList) {
            resourceCategoryIdList.add(resource.getCategoryId());
        }

        //去重
        resourceCategoryIdList = resourceCategoryIdList.stream().distinct().collect(Collectors.toList());

        //调用findAllResourceCategoryById方法获取资源所对应的资源分类
        List<ResourceCategory> allResourceCategory = roleMapper.findAllResourceCategoryById(resourceCategoryIdList);

        //将得到的资源信息存放到对应的资源分类中
        for (ResourceCategory resourceCategory : allResourceCategory) {

            List<Resource> midstResource = new ArrayList<>();

            for (Resource resource : resourceList) {
                if (resource.getCategoryId() == resourceCategory.getId()){
                    midstResource.add(resource);
                }
            }
            resourceCategory.setResourceList(midstResource);
        }

        return allResourceCategory;
    }

    /**
     * 为角色分配资源
     * @param roleResourceVo
     */
    @Override
    public void roleContextResource(RoleResourceVo roleResourceVo) {

        //1、清空中间表的关联关系
        roleMapper.deleteRoleResourceRelationByRoleId(roleResourceVo.getRoleId());

        //2、为角色分配角色
        for (Integer resourceId : roleResourceVo.getResourceIdList()){
            //封装数据
            RoleResourceRelation roleResourceRelation = new RoleResourceRelation();
            roleResourceRelation.setResourceId(resourceId);
            roleResourceRelation.setRoleId(roleResourceVo.getRoleId());

            Date date = new Date();
            roleResourceRelation.setCreatedTime(date);
            roleResourceRelation.setUpdatedTime(date);

            roleResourceRelation.setCreatedBy("system");
            roleResourceRelation.setUpdatedBy("system");

            roleMapper.saveRoleResourceRelation(roleResourceRelation);
        }
    }
}
