package com.uwaytech.supplierCourse.service;

import com.github.pagehelper.Page;
import com.uwaytech.supplierCourse.ctrl.dto.CategoryInfo;
import com.uwaytech.supplierCourse.domain.SupplierCourse;

import java.util.List;

/**
 * Created by rtyui on 2016/6/15.
 */
public interface SupplierCourseService {
	/**
	 * 查询前台页面课程，包括课程搜索、课程列表查询
	 *
	 * @param name       课程名称
	 * @param categoryId 课程分类ID
	 * @param type       排序方式：1、热门 2、最新
	 * @param pageNum    当前页
	 * @param pageSize   每页条数
	 * @return 返回课程列表
	 */
	Page<SupplierCourse> queryCourseList(String name, Long categoryId, Integer type, Integer pageNum, Integer pageSize);

	/**
	 * 查询前台页面课程分类统计
	 *
	 * @param name       课程名称
	 * @param categoryId 课程分类ID
	 * @return
	 */
	List<CategoryInfo> queryCategoryList(String name, Long categoryId);
}
