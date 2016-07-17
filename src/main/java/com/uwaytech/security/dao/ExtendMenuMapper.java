package com.uwaytech.security.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ExtendMenuMapper {
    List<Map<String, Object>> getMenu(@Param("roleId") Long roleId);
}