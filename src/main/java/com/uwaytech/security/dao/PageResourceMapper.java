package com.uwaytech.security.dao;

import com.uwaytech.security.domain.PageResource;
import com.uwaytech.security.domain.PageResourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PageResourceMapper {
    int countByExample(PageResourceExample example);

    int deleteByExample(PageResourceExample example);

    int insert(PageResource record);

    int insertSelective(PageResource record);

    List<PageResource> selectByExample(PageResourceExample example);

    int updateByExampleSelective(@Param("record") PageResource record, @Param("example") PageResourceExample example);

    int updateByExample(@Param("record") PageResource record, @Param("example") PageResourceExample example);
}