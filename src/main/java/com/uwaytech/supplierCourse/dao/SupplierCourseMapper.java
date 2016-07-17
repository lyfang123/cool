package com.uwaytech.supplierCourse.dao;

import com.uwaytech.supplierCourse.domain.SupplierCourse;
import com.uwaytech.supplierCourse.domain.SupplierCourseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SupplierCourseMapper {
    int countByExample(SupplierCourseExample example);

    int deleteByExample(SupplierCourseExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SupplierCourse record);

    int insertSelective(SupplierCourse record);

    List<SupplierCourse> selectByExample(SupplierCourseExample example);

    SupplierCourse selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SupplierCourse record, @Param("example") SupplierCourseExample example);

    int updateByExample(@Param("record") SupplierCourse record, @Param("example") SupplierCourseExample example);

    int updateByPrimaryKeySelective(SupplierCourse record);

    int updateByPrimaryKey(SupplierCourse record);
}