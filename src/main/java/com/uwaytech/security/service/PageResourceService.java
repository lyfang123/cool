package com.uwaytech.security.service;


import com.alibaba.fastjson.JSONArray;
import com.uwaytech.httpclient.bo.UserTypeEnum;

public interface PageResourceService {
    JSONArray getMenu(Long roleId);

    /**
     * 根据用户角色获取用户菜单
     *
     * @param userType 用户类型
     * @param extend   用户扩展字段
     * @param parentId
     *@param userId  @return
     */
    Object getMenuByGroupId(UserTypeEnum userType, String extend, Long userId, Long parentId);
}
