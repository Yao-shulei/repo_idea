package com.lagou.controller;

import com.lagou.domain.*;
import com.lagou.service.MenuService;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 查询所有角色&根据条件进行查询
     */
    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){

        List<Role> allRole = roleService.findAllRole(role);

        return new ResponseResult(true, 200, "查询所有角色成功", allRole);

    }

    /**
     * 查询全部的父子菜单信息(分配菜单的第一个接口)
     * @return
     */
    @Autowired
    private MenuService menuService;

    @RequestMapping("/findAllMenu")
    public ResponseResult findSubMenuListByPid(){

        //-1表示查询所有的父子级菜单
        List<Menu> menuList = menuService.findSubMenuListByPid(-1);

        //响应数据
        Map<String, Object> map = new HashMap<>();
        map.put("parentMenuList", menuList);

        return new ResponseResult(true, 200, "查询所有的父子级菜单信息成功", map);

    }

    /**
     * 根据角色ID查询关联菜单ID
     */
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId){

        List<Integer> menuByRoleId = roleService.findMenuByRoleId(roleId);

        ResponseResult responseResult = new ResponseResult(true, 200, "查询角色关联的菜单信息成功", menuByRoleId);

        return responseResult;

    }

    /*
        为角色分配菜单
     */
    @RequestMapping("/RoleContextMenu")
    public ResponseResult RoleContextMenu(@RequestBody RoleMenuVO roleMenuVO){

        roleService.roleContextMenu(roleMenuVO);

        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", null);

        return responseResult;
    }

    /*
        删除角色
     */
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer id){

        roleService.deleteRole(id);

        ResponseResult responseResult = new ResponseResult(true, 200, "删除角色成功", null);

        return responseResult;

    }

    /**
     * 作业2.3
     * 获取当前角色拥有的资源信息
     * @param roleId
     * @return
     */
    @RequestMapping("/findResourceListByRoleId")
    public ResponseResult findResourceListByRoleId(Integer roleId) {
        List<ResourceCategory> resourceListByRoleId = roleService.findResourceListByRoleId(roleId);

        ResponseResult responseResult = new ResponseResult(true, 200, "获取当前角色拥有的资源信息成功", resourceListByRoleId);

        return responseResult;

    }

    /**
     * 作业2.4
     * 为角色分配资源
     * @param roleResourceVo
     * @return
     */
    @RequestMapping("/roleContextResource")
    public ResponseResult roleContextResource(@RequestBody RoleResourceVo roleResourceVo){

        roleService.roleContextResource(roleResourceVo);

        return new ResponseResult(true, 200, "为角色分配资源成功", null);

    }

}
