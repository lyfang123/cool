package com.uwaytech.security.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.uwaytech.cool.common.constant.Constant;
import com.uwaytech.httpclient.bo.UserTypeEnum;
import com.uwaytech.security.dao.*;
import com.uwaytech.security.domain.*;
import com.uwaytech.security.service.PageResourceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class PageResourceServiceImpl implements PageResourceService {

    @Resource
    PageResourceMapper pageResourceMapper;

    @Resource
    PagePermissionMapper pagePermissionMapper;

    @Resource
    private ExtendPagePermissionMapper extendPagePermissionMapper;

    @Resource
    private ExtendUserRoleMapper extendUserRoleMapper;

    private List<PagePermission> getPagePermissionsByRoleId(Long roleId) {
        PagePermissionExample pagePermissionExample = new PagePermissionExample();
        pagePermissionExample.createCriteria().andRoleIdEqualTo(roleId);
        return pagePermissionMapper.selectByExample(pagePermissionExample);
    }


    public JSONArray getMenu(Long roleId) {
        PageResourceExample pageResourceExample = new PageResourceExample();
        pageResourceExample.setOrderByClause("sort asc");
        pageResourceExample.createCriteria().andTypeEqualTo(1);
        List<PageResource> pageResourceList = pageResourceMapper.selectByExample(pageResourceExample);
        List<Long> pageResourcIds = new ArrayList<Long>();
        if (roleId != null) {
            List<PagePermission> pagePermissionList = getPagePermissionsByRoleId(roleId);
            for (PagePermission pagePermission : pagePermissionList) {
                pageResourcIds.add(pagePermission.getPageResourceId());
            }
        }
        return getPermissionJsonTree(pageResourceList, null, pageResourcIds);
    }

    @Override
    public Object getMenuByGroupId(UserTypeEnum userType, String extend, Long userId, Long parentId) {
        List<Long> list = new ArrayList<Long>();
        //平台管理员
        if (userType == UserTypeEnum.PLATFORM_ADMIN) {
            list.add(Constant.PLATFORM_ADMIN);
            //供应商
        } else if (userType == UserTypeEnum.MEMBER) {
            if (null != extend && Constant.SUPPLIER_EXTEND.equals(extend)) {
                list.add(Constant.SUPPLIER_ROLE);
            }
            //其它用户
        } else {
            list = extendUserRoleMapper.getRoleIds(userId);
        }
        if (!list.isEmpty()) {
            return extendPagePermissionMapper.getMenuByGroupId(list, parentId);
        } else {
            return list;
        }
    }

    private JSONArray getPermissionJsonTree(List<PageResource> list, Long parentId, List<Long> pageResourcIds) {
        JSONArray tempPagePermissions = new JSONArray();
        JSONObject menu = null;
        List<PageResource> temp = getNodes(list, parentId);
        for (PageResource pageResource : temp) {
            menu = new JSONObject();
            menu.put("id", String.valueOf(pageResource.getId()));
            menu.put("title", pageResource.getName());
            if (pageResourcIds != null && pageResourcIds.contains(pageResource.getId())) {
                menu.put("checked", "checked");
            } else {
                menu.put("checked", "");
            }
            JSONArray childMenu = getPermissionJsonTree(list, pageResource.getId(), pageResourcIds);
            menu.put("children", childMenu);
            tempPagePermissions.add(menu);
        }
        return tempPagePermissions;
    }

    List<PageResource> getNodes(List<PageResource> list, Long id) {
        List<PageResource> nodes = new ArrayList<PageResource>();
        Iterator<PageResource> iterator = list.iterator();
        while (iterator.hasNext()) {
            PageResource pageResource = iterator.next();
            if (pageResource.getParentid() == id) {
                nodes.add(pageResource);
                iterator.remove();
            }
        }
        return nodes;
    }
}