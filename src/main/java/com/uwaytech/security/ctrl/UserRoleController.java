package com.uwaytech.security.ctrl;

import com.uwaytech.common.exception.ParamMissException;
import com.uwaytech.common.json.IdResult;
import com.uwaytech.common.json.KVResult;
import com.uwaytech.common.json.SuccessResult;
import com.uwaytech.security.ctrl.dto.UserRoleDto;
import com.uwaytech.security.domain.UserRole;
import com.uwaytech.security.service.RoleInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色对应的用户管理
 * Created by lyf on 2016/6/30.
 */
@RestController
@RequestMapping("/security/v0.1")
public class UserRoleController {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(UserRoleController.class);
    @Resource
    private RoleInfoService roleInfoService;

    /**
     * 查询角色对应的用户
     *
     * @param roleId 角色Id
     * @return
     */
    @RequestMapping(value = "/userRoles/{roleId}", method = {RequestMethod.GET})
    public Object queryUserRoles(@PathVariable("roleId") Long roleId) {
        List<UserRole> list = roleInfoService.queryUserRoles(roleId);
        return UserRoleDto.userRoleList(list);
    }

    /**
     * 删除角色对应的用户
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/userRoles/{id}", method = {RequestMethod.DELETE})
    public Object deleteUserRole(@PathVariable("id") Long id) {
        roleInfoService.deleteUserRole(id);
        return new SuccessResult();
    }

    /**
     * 添加用户角色关联
     * @param userRole
     * @return
     */
    @RequestMapping(value = "/userRoles", method = {RequestMethod.POST})
    public Object addUserRole(UserRole userRole) {
        if (null == userRole) {
            throw new ParamMissException("参数不能为空");
        }
        if (null == userRole.getUserId()) {
            throw new ParamMissException("参数userId不能为空");
        }
        if (null == userRole.getRoleId()) {
            throw new ParamMissException("参数roleId不能为空");
        }
        Long id = roleInfoService.addUserRole(userRole);
        return new IdResult(id);
    }
}
