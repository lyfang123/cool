package com.uwaytech.separate.dao;

import com.uwaytech.separate.domain.Separate;
import com.uwaytech.separate.domain.SeparateExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SeparateMapper {
    int countByExample(SeparateExample example);

    int deleteByExample(SeparateExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Separate record);

    int insertSelective(Separate record);

    List<Separate> selectByExample(SeparateExample example);

    Separate selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Separate record, @Param("example") SeparateExample example);

    int updateByExample(@Param("record") Separate record, @Param("example") SeparateExample example);

    int updateByPrimaryKeySelective(Separate record);

    int updateByPrimaryKey(Separate record);
}