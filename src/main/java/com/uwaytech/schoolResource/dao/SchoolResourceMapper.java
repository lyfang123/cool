package com.uwaytech.schoolResource.dao;

import com.uwaytech.schoolResource.domain.SchoolResource;
import com.uwaytech.schoolResource.domain.SchoolResourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchoolResourceMapper {
    int countByExample(SchoolResourceExample example);

    int deleteByExample(SchoolResourceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SchoolResource record);

    int insertSelective(SchoolResource record);

    List<SchoolResource> selectByExample(SchoolResourceExample example);

    SchoolResource selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SchoolResource record, @Param("example") SchoolResourceExample example);

    int updateByExample(@Param("record") SchoolResource record, @Param("example") SchoolResourceExample example);

    int updateByPrimaryKeySelective(SchoolResource record);

    int updateByPrimaryKey(SchoolResource record);
}