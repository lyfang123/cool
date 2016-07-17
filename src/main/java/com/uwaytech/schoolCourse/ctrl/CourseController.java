package com.uwaytech.schoolCourse.ctrl;

import com.uwaytech.common.exception.PermissionDeniedException;
import com.uwaytech.common.json.SuccessResult;
import com.uwaytech.cool.common.constant.Constant;
import com.uwaytech.httpclient.SessionUtils;
import com.uwaytech.id.service.IdGeneratorService;
import com.uwaytech.schoolCourse.ctrl.dto.CourseDetailDto;
import com.uwaytech.schoolCourse.domain.CourseInfo;
import com.uwaytech.schoolCourse.domain.CourseTermInfo;
import com.uwaytech.schoolCourse.service.ICourseService;
import com.uwaytech.schoolCourse.service.SchoolCourseService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by zeng on 2016/6/6.
 */
@RestController
@RequestMapping(value = "/course")
public class CourseController {

	@Resource
	private ICourseService courseService;
	@Resource
	private IdGeneratorService idGeneratorService;

	@Resource
	private SchoolCourseService schoolCourseService;

	/**
	 * 新增课程
	 *
	 * @param courseInfo
	 * @return
	 */
	@RequestMapping(value = "/v0.1", method = RequestMethod.POST)
	public Object addCourse(@RequestBody CourseInfo courseInfo) {
		//获取用户类型
		Byte type = getUserType();
		Long id = null;
		Long grouping = null;
		if (type == Constant.SUPPLIER) {
			id = idGeneratorService.generatorId(Constant.SUPPLIER_NUMBER);
			grouping = SessionUtils.getUserId();
		} else {
			id = idGeneratorService.generatorId(SessionUtils.getSchoolId());
			grouping = SessionUtils.getSchoolId().longValue();
		}
		courseService.addCourse(courseInfo, type, id, grouping);
		return new SuccessResult();
	}

	/**
	 * 删除课程
	 *
	 * @param courseId
	 * @return
	 */
	@RequestMapping(value = "/v0.1/{courseId}", method = RequestMethod.DELETE)
	public Object deleteCourse(@PathVariable(value = "courseId") Long courseId) {
		//获取用户类型
		Byte type = getUserType();
		courseService.deleteCourse(courseId, type);
		return new SuccessResult();
	}

	/**
	 * 修改课程
	 *
	 * @param courseInfo
	 * @return
	 */
	@RequestMapping(value = "/v0.1/{courseId}", method = RequestMethod.PUT)
	public Object updateCourse(@PathVariable(value = "courseId") Long courseId,
	                           @RequestBody CourseInfo courseInfo) {
		//获取用户类型
		Byte type = getUserType();
		courseService.updateCourse(courseInfo, type);
		return new SuccessResult();
	}

	/**
	 * 课程详情
	 *
	 * @param courseId
	 * @return
	 */
	@RequestMapping(value = "/v0.1/{courseId}/detail", method = RequestMethod.GET)
	public Object findCourseDetail(@PathVariable(value = "courseId") Long courseId) {
		//获取用户类型
		Byte type = getUserType();
		CourseInfo courseInfo = courseService.findCourseDetail(courseId, type);
		CourseDetailDto dto = CourseDetailDto.toCourseDetailDto(courseInfo);
		return dto;
	}

	/**
	 * 课程列表
	 * @param courseTermInfo
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/v0.1/list", method = RequestMethod.GET)
	public Object findCourseList(CourseTermInfo courseTermInfo,
	                             @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
	                             @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
		//获取用户类型
		Byte userType = getUserType();
		if (userType == Constant.SUPPLIER) {
			courseTermInfo.setId(SessionUtils.getUserId());
		} else if (userType == Constant.TEACHER) {
			courseTermInfo.setId(SessionUtils.getSchoolId().longValue());
		}
		return courseService.findCourseList(courseTermInfo, userType, pageNum, pageSize);
	}

	/**
	 * 供应商课程审核
	 *
	 * @param courseId
	 * @param checkStatus
	 * @param reason
	 * @return
	 */
	@RequestMapping(value = "/v0.1/review", method = {RequestMethod.PUT, RequestMethod.POST})
	public Object courseReview(@RequestParam(value = "courseId") Long courseId,
	                           @RequestParam(value = "checkStatus") Integer checkStatus,
	                           @RequestParam(value = "reason",required = false) String reason) {
		Byte type = getUserType();
		//判断用户是否为管理员
		if (type == Constant.ADMIN) {
			courseService.courseReview(courseId, checkStatus, reason);
		} else {
			throw new PermissionDeniedException("不是管理员，不能进行此操作");
		}
		return new SuccessResult();
	}

	/**
	 * 供应商上、下架
	 *
	 * @param courseId
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/v0.1/shelve", method = RequestMethod.PUT)
	public Object courseShelve(@RequestParam(value = "courseId") Long courseId,
	                           @RequestParam(value = "status") Integer status) {
		courseService.setShelve(courseId, status);
		return new SuccessResult();
	}

	/**
	 * 课程推荐
	 * @param courseId
	 * @param recommend
	 * @return
	 */
	@RequestMapping(value = "/v0.1/recommend", method = RequestMethod.PUT)
	public Object courseRecommend(@RequestParam(value = "courseId") Long courseId,
	                              @RequestParam(value = "recommend") Integer recommend) {
		Byte type = getUserType();
		if ((type == Constant.SUPPLIER) || (type == Constant.ADMIN)) {
			courseService.recommendSupplierCourse(courseId, recommend);
		} else {
			courseService.recommendSchoolCourse(courseId, recommend);
		}
		return new SuccessResult();
	}

	/**
	 * 添加课程播放次数
	 * @param courseId
	 * @return
	 */
	@RequestMapping(value = "/v0.1/viewNumber", method = RequestMethod.PUT)
	public Object updateViewNumber(@RequestParam(value = "courseId") Long courseId) {
		courseService.updateViewNumber(courseId);
		return new SuccessResult();
	}

	/**
	 * 获取用户类型
	 *
	 * @return
	 */
	public static Byte getUserType() {
		Byte type = SessionUtils.getUserType().byteValue();
		return type;
	}

	/**
	 * 查询课程相关推荐列表
	 * @param courseId 课程Id
	 * @return
	 */
	@RequestMapping(value = "/v0.1/{courseId}/list", method = RequestMethod.PUT)
	public Object queryCourseRecommends(@RequestParam(value = "courseId") Long courseId,
										@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
										@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
		return schoolCourseService.queryCourseRecommends(courseId,pageNum,pageSize);
	}
}
