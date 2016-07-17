package com.uwaytech.security.dao;

import com.uwaytech.security.domain.RoleInfo;
import com.uwaytech.security.domain.UserRole;
import com.uwaytech.security.domain.UserRoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtendUserRoleMapper {

    List<Long> getRoleIds(@Param("userId") Long userId);
}