package com.uwaytech.schoolCourse.dao;

import com.uwaytech.schoolCourse.domain.SchoolCourse;
import com.uwaytech.schoolCourse.domain.SchoolCourseParam;
import com.uwaytech.supplierCourse.ctrl.dto.CategoryInfo;
import com.uwaytech.supplierCourse.domain.SupplierCourseParam;

import java.util.List;

/**
 * Created by rtyui on 2016/6/15.
 */
public interface ExtendSchoolCourseMapper {

	List<CategoryInfo> queryCategoryList(SchoolCourseParam param);

	List<SchoolCourse> querySchoolCourseList(SchoolCourseParam param);
}
