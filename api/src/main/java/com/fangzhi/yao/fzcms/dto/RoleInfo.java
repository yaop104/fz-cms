package com.fangzhi.yao.fzcms.dto;

import com.fangzhi.yao.fzcms.entity.Permission;
import com.fangzhi.yao.fzcms.entity.Role;

import java.io.Serializable;
import java.util.List;

public class RoleInfo extends Role implements Serializable {

    private List<Permission> permissions;

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}