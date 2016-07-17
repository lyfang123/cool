package com.uwaytech.security.service.impl;

import com.uwaytech.common.exception.ParamErrorException;
import com.uwaytech.common.exception.PermissionDeniedException;
import com.uwaytech.cool.common.constant.Constant;
import com.uwaytech.cool.common.enums.StatusEnum;
import com.uwaytech.cool.common.enums.UserTypeInfoEnum;
import com.uwaytech.cool.common.util.StringFilterUtil;
import com.uwaytech.httpclient.SessionUtils;
import com.uwaytech.httpclient.UserCenterSynProxy;
import com.uwaytech.httpclient.bo.LoginUserInfoDto;
import com.uwaytech.httpclient.bo.UserTypeEnum;
import com.uwaytech.security.service.LoginService;
import com.uwaytech.supplier.dao.SupplierMapper;
import com.uwaytech.supplier.domain.Supplier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lyf on 2016/7/15.
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserCenterSynProxy userCenterSynProxy;

    @Resource
    private SupplierMapper supplierMapper;

    @Override
    public Object login(String account, String password) {
        //uc登录验证
        LoginUserInfoDto userLogin = null;
        try {
            userLogin = userCenterSynProxy.login(account, password, 1);
            if (null != userLogin) {
                //判断供应商和普通用户类型
                if (null != userLogin.getExtend() && Constant.SUPPLIER_EXTEND.equals(userLogin.getExtend())) {
                    //供应商账号
                    Supplier supplier = supplierMapper.selectByPrimaryKey(Long.parseLong(userLogin.getAccountId()));
                    if (null != supplier) {
                        if (supplier.getStatus().byteValue() == StatusEnum.Invalid.getValue()) {
                            throw new PermissionDeniedException("账号无效");
                        } else {
                            userLogin.setAccountName(supplier.getAccountName());
                        }
                        userLogin.setAccountType(UserTypeInfoEnum.SUPPLIER.byteValue().intValue());
                    } else {
                        //账号无效
                        throw new PermissionDeniedException("账号无效");
                    }
                    //普通用户类型
                } else if (null != userLogin.getExtend() && Constant.SUPPLIER_TOURIST.equals(userLogin.getExtend())) {
                    userLogin.setAccountType(UserTypeInfoEnum.TOURIST.byteValue().intValue());
                }
            } else {
                throw new PermissionDeniedException("登录失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new PermissionDeniedException("登陆出错");
        }
        return userLogin;
    }

    public UserTypeInfoEnum getUserType(String extend, UserTypeEnum userTypeEnum) {
        UserTypeInfoEnum userTypeInfoEnum = null;
        //uc其他用户类型
        if (userTypeEnum.equals(UserTypeEnum.MEMBER)) {
            //供应商
            if (null != extend && Constant.SUPPLIER_EXTEND.equals(extend)) {
                userTypeInfoEnum = UserTypeInfoEnum.SUPPLIER;
                //普通用户
            } else if (null != extend && Constant.SUPPLIER_TOURIST.equals(extend)) {
                userTypeInfoEnum = UserTypeInfoEnum.TOURIST;
            }
            //老师
        } else if (userTypeEnum.equals(UserTypeEnum.TEACHER)) {
            userTypeInfoEnum = UserTypeInfoEnum.TEACHER;
            //学生
        } else if (userTypeEnum.equals(UserTypeEnum.STUDENT)) {
            userTypeInfoEnum = UserTypeInfoEnum.STUDENT;
            //管理员
        } else if (userTypeEnum.equals(UserTypeEnum.PLATFORM_ADMIN)) {
            userTypeInfoEnum = UserTypeInfoEnum.PLATFORM_ADMIN;
        }
        return userTypeInfoEnum;
    }

    @Override
    public void register(String account, String password) {
        //过滤特殊字符，只允许字母和数字
        password = StringFilterUtil.StringFilter(password);
        account = StringFilterUtil.StringFilter(account);
        //普通用户注册，扩展字段extend为tourist
        try{
            userCenterSynProxy.register(account, password, null, Constant.SUPPLIER_TOURIST);
        }catch (Exception e){
            throw new ParamErrorException("注册失败");
        }
    }
}
