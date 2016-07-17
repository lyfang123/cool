package com.uwaytech.security.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.uwaytech.common.exception.ParamErrorException;
import com.uwaytech.common.exception.PermissionDeniedException;
import com.uwaytech.cool.common.constant.Constant;
import com.uwaytech.security.ctrl.dto.RoleInfoDetail;
import com.uwaytech.security.dao.RoleInfoMapper;
import com.uwaytech.security.dao.UserRoleMapper;
import com.uwaytech.security.domain.RoleInfo;
import com.uwaytech.security.domain.RoleInfoExample;
import com.uwaytech.security.domain.UserRoleExample;
import com.uwaytech.security.service.RoleInfoService;
import org.springframework.stereotype.Service;
import com.uwaytech.security.domain.UserRole;
import com.uwaytech.security.ctrl.dto.UserRoleDto;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 角色权限管理
 * Created by lyf on 2016/6/30.
 */
@Service
public class RoleInfoServiceImpl implements RoleInfoService {

    @Resource
    private RoleInfoMapper roleInfoMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public Page<RoleInfo> queryRoles(Integer pageNum, Integer pageSize) {
        RoleInfoExample example = new RoleInfoExample();
        PageHelper.startPage(pageNum,pageSize);
        return (Page<RoleInfo>)roleInfoMapper.selectByExample(example);
    }

    @Override
    public Long addRoleInfo(RoleInfo roleInfo) {
        //查询角色名称是否已经存在
        RoleInfoExample example = new RoleInfoExample();
        example.createCriteria().andRoleNameEqualTo(roleInfo.getRoleName().trim());
        List<RoleInfo> list = roleInfoMapper.selectByExample(example);
        if (!list.isEmpty()) {
            throw new ParamErrorException("角色名称已经存在！");
        }
        roleInfo.setCreateTime(new Date());
        //自定义角色类型2
        roleInfo.setType(Constant.ROLE_TYPE);
        roleInfoMapper.insertSelective(roleInfo);
        UserRole[] userRoles = roleInfo.getUserList();
        if(userRoles.length > 0){
            for (UserRole userRole:userRoles){
                userRole.setRoleId(roleInfo.getId());
                userRoleMapper.insertSelective(userRole);
            }
        }
        return roleInfo.getId();
    }

    @Override
    public void deleteRoleInfo(Long roleId) {
        if (Constant.PLATFORM_ADMIN.longValue() == roleId.longValue()) {
            throw new PermissionDeniedException("该角色为管理员角色，不能删除！");
        }
        if (Constant.SUPPLIER_ROLE.longValue() == roleId.longValue()) {
            throw new PermissionDeniedException("该角色为供应商角色，不能删除！");
        }
        //判断角色是否已经对应用户
        UserRoleExample example = new UserRoleExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        List<UserRole> list = userRoleMapper.selectByExample(example);
        if (!list.isEmpty()) {
            throw new PermissionDeniedException("该角色还有对应的用户，不能删除！");
        }
        roleInfoMapper.deleteByPrimaryKey(roleId);
    }

    @Override
    public void updateRoleInfo(RoleInfo roleInfo) {
        //查询角色名称是否已经存在
        RoleInfoExample example = new RoleInfoExample();
        example.createCriteria().andRoleNameEqualTo(roleInfo.getRoleName().trim()).andIdNotEqualTo(roleInfo.getId());
        List<RoleInfo> list = roleInfoMapper.selectByExample(example);
        if (!list.isEmpty()) {
            throw new ParamErrorException("角色名称已经存在！");
        }
        //删除原有角色用户关联数据
        UserRoleExample userRoleExample = new UserRoleExample();
        userRoleExample.createCriteria().andRoleIdEqualTo(roleInfo.getId());
        userRoleMapper.deleteByExample(userRoleExample);
        //添加新的角色用户关联数据
        UserRole[] userRoles = roleInfo.getUserList();
        if(userRoles.length > 0){
            for (UserRole userRole:userRoles){
                userRoleMapper.insertSelective(userRole);
            }
        }
        roleInfoMapper.updateByPrimaryKeySelective(roleInfo);
    }

    @Override
    public List<UserRole> queryUserRoles(Long roleId) {
        UserRoleExample example = new UserRoleExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        return userRoleMapper.selectByExample(example);
    }

    @Override
    public void deleteUserRole(Long id) {
        userRoleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Long addUserRole(UserRole userRole) {
        userRoleMapper.insertSelective(userRole);
        return userRole.getId();
    }

    @Override
    public RoleInfo getRoleInfoDetail(Long roleId) {
        RoleInfo roleInfo = roleInfoMapper.selectByPrimaryKey(roleId);
        RoleInfoDetail detail = new RoleInfoDetail();
        if(null == roleInfo){
            throw new ParamErrorException("角色不存在！");
        }
        return roleInfo;
    }
}
