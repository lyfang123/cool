package com.uwaytech.schoolCourse.dao;

import com.uwaytech.schoolCourse.domain.SchoolCourse;
import com.uwaytech.schoolCourse.domain.SchoolCourseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchoolCourseMapper {
    int countByExample(SchoolCourseExample example);

    int deleteByExample(SchoolCourseExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SchoolCourse record);

    int insertSelective(SchoolCourse record);

    List<SchoolCourse> selectByExample(SchoolCourseExample example);

    SchoolCourse selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SchoolCourse record, @Param("example") SchoolCourseExample example);

    int updateByExample(@Param("record") SchoolCourse record, @Param("example") SchoolCourseExample example);

    int updateByPrimaryKeySelective(SchoolCourse record);

    int updateByPrimaryKey(SchoolCourse record);
}