package com.uwaytech.security.service.impl;

import com.uwaytech.cool.common.constant.Constant;
import com.uwaytech.httpclient.bo.UserTypeEnum;
import com.uwaytech.security.dao.ExtendPagePermissionMapper;
import com.uwaytech.security.dao.PagePermissionMapper;
import com.uwaytech.security.dao.PageResourceMapper;
import com.uwaytech.security.domain.PagePermission;
import com.uwaytech.security.domain.PagePermissionExample;
import com.uwaytech.security.service.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenyubo on 2015/7/7.
 */
@Service
class PermissionServiceImpl implements PermissionService {
    @Resource
    ExtendPagePermissionMapper extendPagePermissionMapper;

    @Resource
    PagePermissionMapper pagePermissionMapper;

    @Resource
    PageResourceMapper pageResourceMapper;



    @Override
    public void insertRolePermission(Long roleId, Long[] rIds) {
        PagePermission pagePermission = new PagePermission();
        for (int i = 0; i < rIds.length; i++) {
            pagePermission.setRoleId(roleId);
            pagePermission.setPageResourceId(rIds[i]);
            pagePermissionMapper.insertSelective(pagePermission);
        }
    }

    @Override
    public void updateRolePermission(Long roleId, Long[] rIds) {
        deleteRolePermission(roleId);
        insertRolePermission(roleId, rIds);
    }

    @Override
    public void deleteRolePermission(Long roleId) {
        PagePermissionExample example = new PagePermissionExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        pagePermissionMapper.deleteByExample(example);
    }

    @Override
    public Map<String, Object> validateRole(UserTypeEnum userType, String extend) {
        Map<String,Object> result = new HashMap<String,Object>();
        //学校
        if(userType == UserTypeEnum.STUDENT){
            result.put("type",UserTypeEnum.STUDENT.byteValue());
            //老师
        }else if(userType == UserTypeEnum.TEACHER){
            result.put("type",UserTypeEnum.TEACHER.byteValue());
            //平台管理员
        }else if(userType == UserTypeEnum.PLATFORM_ADMIN){
            result.put("type",4);
            //供应商、普通用户
        }else if(userType == UserTypeEnum.MEMBER){
            if(null != extend && Constant.SUPPLIER_EXTEND.equals(extend)){
                result.put("type",3);
            }else{
                result.put("type",-1);
            }
            //其它用户
        }else{
            result.put("type",-1);
        }
        return result;
    }


}