package com.uwaytech.security.service;

import com.github.pagehelper.Page;
import com.uwaytech.security.ctrl.dto.RoleInfoDetail;
import com.uwaytech.security.domain.RoleInfo;
import com.uwaytech.security.domain.UserRole;

import java.util.List;

/**
 * Created by lyf on 2016/6/30.
 */
public interface RoleInfoService {
    /**
     *  查询角色列表
     * @return
     * @param pageNum
     * @param pageSize
     */
    Page<RoleInfo> queryRoles(Integer pageNum, Integer pageSize);

    /**
     * 添加角色
     * @param roleInfo
     * @param userList
     * @return
     */
    Long addRoleInfo(RoleInfo roleInfo);

    /**
     * 删除角色
     * @param roleId
     */
    void deleteRoleInfo(Long roleId);

    /**
     * 修改角色信息
     * @param roleInfo
     * @param userList
     */
    void updateRoleInfo(RoleInfo roleInfo);

    /**
     * 查询角色对应的用户
     * @param roleId 角色Id
     * @return
     */
    List<UserRole> queryUserRoles(Long roleId);

    /**
     * 删除角色对应的用户
     * @param id 用户角色Id
     */
    void deleteUserRole(Long id);

    /**
     * 添加角色对应的用户
     * @param userRole 用户角色对象
     * @return
     */
    Long addUserRole(UserRole userRole);

    /**
     * 查询角色详情
     * @param roleId 角色Id
     * @return
     */
    RoleInfo getRoleInfoDetail(Long roleId);
}
