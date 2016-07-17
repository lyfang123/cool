package com.uwaytech.supplierCourse.dao;

import com.uwaytech.supplierCourse.ctrl.dto.CategoryInfo;
import com.uwaytech.supplierCourse.domain.SupplierCourse;
import com.uwaytech.supplierCourse.domain.SupplierCourseExample;
import com.uwaytech.supplierCourse.domain.SupplierCourseParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtendSupplierCourseMapper {
	List<SupplierCourse> queryCourseList(SupplierCourseParam param);

	List<CategoryInfo> queryCategoryList(SupplierCourseParam param);
}