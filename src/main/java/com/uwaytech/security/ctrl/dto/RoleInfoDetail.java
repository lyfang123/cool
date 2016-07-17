package com.uwaytech.security.ctrl.dto;

import com.uwaytech.security.domain.RoleInfo;
import com.uwaytech.security.domain.UserRole;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyf on 2016/7/4.
 */
public class RoleInfoDetail {

    private Long id;

    private String roleName;

    private String roleDesc;

    private List<UserRoleDto> userList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public List<UserRoleDto> getUserList() {
        return userList;
    }

    public void setUserList(List<UserRoleDto> userList) {
        this.userList = userList;
    }

    public static RoleInfoDetail roleDetailDto(RoleInfo roleInfo, List<UserRole> userRoles) {
        RoleInfoDetail dto = new RoleInfoDetail();
        dto.setRoleDesc(roleInfo.getRoleDesc());
        dto.setId(roleInfo.getId());
        dto.setRoleName(roleInfo.getRoleName());
        List<UserRoleDto> userList = new ArrayList<UserRoleDto>();
        if (!userRoles.isEmpty()) {
            for (UserRole userRole : userRoles) {
                UserRoleDto userRoleDto = new UserRoleDto();
                userRoleDto.setName(userRole.getName());
                userRoleDto.setAccountId(userRole.getUserId());
                userRoleDto.setPhone(userRole.getPhone());
                userRoleDto.setPhoto(userRole.getPhoto());
                userList.add(userRoleDto);
            }
        }
        dto.setUserList(userList);
        return dto;
    }
}
