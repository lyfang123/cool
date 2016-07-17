package com.uwaytech.security.service;


import com.uwaytech.httpclient.bo.UserTypeEnum;

import java.util.Map;

/**
 * Created by chenyubo on 2015/7/7.
 */
public interface PermissionService {
    /**
     * 添加角色及权限
     */
    void insertRolePermission(Long roleId, Long[] rIds);

    /**
     * 更新角色及权限
     */
    void updateRolePermission(Long roleId, Long[] rIds);

    /**
     * 更新角色及权限
     * @param roleId
     * @return
     */
    public void deleteRolePermission(Long roleId);

    /**
     * 验证用户类型：1、学生 2、老师 3、供应商 4、平台管理员
     * @param userType
     * @param extend
     * @return
     */
    Map<String,Object> validateRole(UserTypeEnum userType, String extend);
}
