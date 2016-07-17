package com.uwaytech.security.service;

import com.uwaytech.cool.common.enums.UserTypeInfoEnum;
import com.uwaytech.httpclient.bo.UserTypeEnum;

/**
 * Created by lyf on 2016/7/15.
 */
public interface LoginService {
    /**
     * 用户登录
     * @param account 账号
     * @param password 密码
     * @return
     */
    Object login(String account, String password);

    /**
     * 获取用户类型
     * @param extend 用户扩展字段
     * @param userTypeEnum uc用户类型
     * @return
     */
    UserTypeInfoEnum getUserType(String extend, UserTypeEnum userTypeEnum);

    /**
     * 用户注册
     * @param account 账号
     * @param password 密码
     */
    void register(String account, String password);
}
