package com.uwaytech.schoolCourse.service;

import com.uwaytech.schoolCourse.domain.CourseInfo;
import com.uwaytech.schoolCourse.domain.CourseListInfo;
import com.uwaytech.schoolCourse.domain.CourseTermInfo;

/**
 * Created by zeng on 2016/6/6.
 */
public interface ICourseService {

	/**
	 * 添加课程
	 * @param courseInfo 课程详情
	 * @param type 课程类型
	 * @param id 课程id
	 * @param grouping 供应商或学校id
	 * @return
	 */
	int addCourse(CourseInfo courseInfo, Byte type, Long id, Long grouping);

	/**
	 * 删除课程
	 * @param id 课程id
	 * @param type 课程类型
	 */
	void deleteCourse(Long id, Byte type);

	/**
	 * 课程更新
	 * @param courseInfo 课程详细
	 * @param type 课程类型
	 * @return
	 */
	int updateCourse(CourseInfo courseInfo, Byte type);

	/**
	 * 课程详情
	 * @param courseId 课程id
	 * @param type 课程类型
	 * @return
	 */
	CourseInfo findCourseDetail(Long courseId, Byte type);

	/**
	 * 课程列表
	 * @param courseTermInfo 查询条件
	 * @param userType 用户类型
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	CourseListInfo findCourseList(CourseTermInfo courseTermInfo, Byte userType, Integer pageNum, Integer pageSize);

	/**
	 * 课程上架
	 * @param courseId 课程id
	 * @param status 上下架状态
	 */
	void setShelve(Long courseId, Integer status);

	/**
	 * 学校课程推荐
	 * @param courseId 课程id
	 * @param recommend 推荐状态
	 */
	void recommendSchoolCourse(Long courseId, Integer recommend);

	/**
	 * 供应商课程推荐
	 * @param courseId 课程id
	 * @param recommend 推荐状态
	 */
	void recommendSupplierCourse(Long courseId, Integer recommend);

	/**
	 * 添加课程播放记录
	 * @param courseId 课程id
	 */
	void updateViewNumber(Long courseId);

	/**
	 * 课程审核
	 * @param courseId 课程id
	 * @param checkStatus 审核状态
	 * @param reason 不通过理由
	 */
	void courseReview(Long courseId, Integer checkStatus, String reason);
}
