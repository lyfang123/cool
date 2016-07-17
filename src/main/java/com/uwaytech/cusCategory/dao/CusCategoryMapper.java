package com.uwaytech.cusCategory.dao;

import com.github.pagehelper.Page;
import com.uwaytech.cusCategory.domain.CusCategory;
import com.uwaytech.cusCategory.domain.CusCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CusCategoryMapper {
    int countByExample(CusCategoryExample example);

    int deleteByExample(CusCategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CusCategory record);

    int insertSelective(CusCategory record);

    List<CusCategory> selectByExample(CusCategoryExample example);

    CusCategory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CusCategory record, @Param("example") CusCategoryExample example);

    int updateByExample(@Param("record") CusCategory record, @Param("example") CusCategoryExample example);

    int updateByPrimaryKeySelective(CusCategory record);

    int updateByPrimaryKey(CusCategory record);
}