package com.uwaytech.schoolCourse.dao;

import com.github.pagehelper.Page;
import com.uwaytech.material.domain.Material;
import com.uwaytech.schoolCourse.domain.*;

import java.util.List;

/**
 * Created by zeng on 2016/6/6.
 */
public interface ExtendCourseMapper {

    void insertCourseMaterial(List<MaterialEntity> materialEntities);

    void deleteCourseMaterial(Long id);

    CourseInfo findSchoolCourseDetail(Long courseId);

    CourseInfo findSupplierCourseDetail(Long courseId);

    Page<CourseDetail> findSupplierCourseList(CourseTermInfo term);

    Page<CourseDetail> findSchoolCourseList(CourseTermInfo term);

    void updateSupplierViewNum(Long courseId);

    void updateSchoolViewNum(Long courseId);

    int updateMaterials(List<Material> materials);

    void insertResourceMaterial(List<MaterialEntity> materials);

    Page<CourseDetail> findSupplierCourseListByKeyword(CourseTermInfo keyword);

    Page<CourseDetail> findSchoolCourseListByKeyword(CourseTermInfo keyword);
}
