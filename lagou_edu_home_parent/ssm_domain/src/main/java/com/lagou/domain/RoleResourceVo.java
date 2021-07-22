package com.lagou.domain;

import java.util.List;

/**
 * 接收角色ID和资源ID集合
 */
public class RoleResourceVo {

    private Integer roleId;
    private List<Integer> resourceIdList;

    public List<Integer> getResourceIdList() {
        return resourceIdList;
    }

    public void setResourceIdList(List<Integer> resourceIdList) {
        this.resourceIdList = resourceIdList;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
