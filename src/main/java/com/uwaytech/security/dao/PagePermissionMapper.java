package com.uwaytech.security.dao;

import com.uwaytech.security.domain.PagePermission;
import com.uwaytech.security.domain.PagePermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PagePermissionMapper {
    int countByExample(PagePermissionExample example);

    int deleteByExample(PagePermissionExample example);

    int deleteByPrimaryKey(@Param("pageResourceId") Long pageResourceId, @Param("roleId") Long roleId);

    int insert(PagePermission record);

    int insertSelective(PagePermission record);

    List<PagePermission> selectByExample(PagePermissionExample example);

    int updateByExampleSelective(@Param("record") PagePermission record, @Param("example") PagePermissionExample example);

    int updateByExample(@Param("record") PagePermission record, @Param("example") PagePermissionExample example);
}