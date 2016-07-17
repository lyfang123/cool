package com.uwaytech.schoolCourse.service;

import com.github.pagehelper.Page;
import com.uwaytech.httpclient.bo.UserTypeEnum;
import com.uwaytech.schoolCourse.ctrl.dto.CourseRecommendInfo;
import com.uwaytech.schoolCourse.domain.SchoolCourse;
import com.uwaytech.supplierCourse.ctrl.dto.CategoryInfo;

import java.util.List;

/**
 * Created by rtyui on 2016/6/15.
 */
public interface SchoolCourseService {

	/**
	 * 查询学校课程，包括课程搜索、课程列表查询
	 *
	 * @param name       课程名称
	 * @param categoryId 课程分类ID
	 * @param type       排序方式：1、热门  2、最新
	 * @param userType   用户类型
	 * @param schoolId   学校Id
	 * @param pageNum    当前页
	 * @param pageSize   每页条数   @return 返回课程列表
	 */
	Page<SchoolCourse> querySchoolCourseList(String name, Long categoryId, Integer type, UserTypeEnum userType,
	                                         Integer schoolId, Integer pageNum, Integer pageSize);

	/**
	 * 查询课程分类统计
	 *
	 * @param name       课程名称
	 * @param categoryId 分类ID
	 * @param userType   用户类型
	 * @param schoolId   学校Id
	 * @return 返回查询统计结果
	 */
	List<CategoryInfo> queryCategoryList(String name, Long categoryId, UserTypeEnum userType, Integer schoolId);

	/**
	 * 查询课程相关推荐列表
	 * @param courseId
	 * @param pageNum
	 *@param pageSize @return
	 */
	CourseRecommendInfo queryCourseRecommends(Long courseId, Integer pageNum, Integer pageSize);
}
