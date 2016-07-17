package com.uwaytech.security.dao;

import com.uwaytech.security.ctrl.dto.MenuDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtendPagePermissionMapper {
    List<MenuDto> getMenuByGroupId(@Param(value = "roleIds") List<Long> roleIds, @Param(value = "parentId") Long parentId);
}