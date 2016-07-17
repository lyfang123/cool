package com.uwaytech.security.ctrl;

import com.github.pagehelper.Page;
import com.uwaytech.common.exception.ParamMissException;
import com.uwaytech.common.exception.PermissionDeniedException;
import com.uwaytech.common.json.KVResult;
import com.uwaytech.common.json.SuccessResult;
import com.uwaytech.cool.common.constant.Constant;
import com.uwaytech.httpclient.SessionUtils;
import com.uwaytech.security.ctrl.dto.RoleInfoDetail;
import com.uwaytech.security.ctrl.dto.RoleInfoDto;
import com.uwaytech.security.ctrl.dto.UserRoleDto;
import com.uwaytech.security.dao.ExtendPagePermissionMapper;
import com.uwaytech.security.domain.UserRole;
import com.uwaytech.security.service.PageResourceService;
import com.uwaytech.security.service.PermissionService;
import com.uwaytech.security.service.RoleInfoService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.uwaytech.httpclient.bo.UserTypeEnum;
import com.uwaytech.security.domain.RoleInfo;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by chenyubo on 2015/10/19.
 */
@RestController
@RequestMapping("/security/v0.1")
public class RoleController {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(RoleController.class);

    @Resource
    private PermissionService permissionService;

    @Resource
    private ExtendPagePermissionMapper extendPagePermissionMapper;

    @Resource
    private PageResourceService pageResourceService;

    @Resource
    private RoleInfoService roleInfoService;

    /**
     * 获取角色对应的菜单(懒加载)
     * @param parentId 父菜单ID
     * @return
     */
    @RequestMapping(value = "/groups/menus", method = {RequestMethod.GET})
    public Object getMenu(@RequestParam(value = "parentId", required = false) Long parentId) {
        UserTypeEnum userType = SessionUtils.getUserType();
        String extend = SessionUtils.getUserInfoWrapper().getUserInfo().getExtend();
        Long userId = SessionUtils.getUserId();
        return pageResourceService.getMenuByGroupId(userType, extend, userId, parentId);
    }

    /**
     * 获取组管理端菜单详情
     */
    @RequestMapping(value = "/groups/{groupId}/details", method = {RequestMethod.GET})
    public Object getGroupPermission(@PathVariable Long groupId) {
        return pageResourceService.getMenu(groupId);
    }

    /**
     * 修改组管理端菜单
     */
    @RequestMapping(value = "/groups/{groupId}", method = {RequestMethod.PUT})
    public Object updateGroupPermission(@PathVariable Long groupId,
                                        @RequestParam(value = "rIds") Long[] rIds) {
        permissionService.updateRolePermission(groupId, rIds);
        return new SuccessResult();
    }

    /**
     * 获取角色列表
     *
     * @return
     */
    @RequestMapping(value = "/roles", method = {RequestMethod.GET})
    public Object queryRoles(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<RoleInfo> page = roleInfoService.queryRoles(pageNum, pageSize);
        return RoleInfoDto.roleInfoDto(page);
    }

    /**
     * 添加角色
     *
     * @param roleInfo 角色对象
     * @return
     */
    @RequestMapping(value = "/roles", method = {RequestMethod.POST})
    public Object addRole(@RequestBody RoleInfo roleInfo) {
        if (StringUtils.isBlank(roleInfo.getRoleName())) {
            throw new ParamMissException("参数roleName不能为空");
        }
        Long id = roleInfoService.addRoleInfo(roleInfo);
        return new KVResult("dataId", id);
    }

    /**
     * 修改角色
     *
     * @param roleInfo 角色对象
     * @return
     */
    @RequestMapping(value = "/roles", method = {RequestMethod.PUT})
    public Object updateRole(@RequestBody RoleInfo roleInfo) {
        if (StringUtils.isBlank(roleInfo.getRoleName())) {
            throw new ParamMissException("参数roleName不能为空");
        }
        roleInfoService.updateRoleInfo(roleInfo);
        return new SuccessResult();
    }

    /**
     * 删除角色
     *
     * @param roleId 角色ID
     * @return
     */
    @RequestMapping(value = "/roles/{roleId}", method = {RequestMethod.DELETE})
    public Object deleteRole(@PathVariable("roleId") Long roleId) {
        roleInfoService.deleteRoleInfo(roleId);
        return new SuccessResult();
    }

    /**
     * 删除角色
     *
     * @param roleId 角色ID
     * @return
     */
    @RequestMapping(value = "/roles/{roleId}", method = {RequestMethod.GET})
    public Object getRoleDetail(@PathVariable("roleId") Long roleId) {
        RoleInfo roleInfo = roleInfoService.getRoleInfoDetail(roleId);
        List<UserRole> userRoles = roleInfoService.queryUserRoles(roleId);
        RoleInfoDetail dto = RoleInfoDetail.roleDetailDto(roleInfo, userRoles);
        return dto;
    }
}
