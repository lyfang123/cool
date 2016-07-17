package com.uwaytech.schoolCourse.ctrl;

import com.github.pagehelper.Page;
import com.uwaytech.common.json.SuccessResult;
import com.uwaytech.cool.common.constant.Constant;
import com.uwaytech.httpclient.SessionUtils;
import com.uwaytech.id.service.IdGeneratorService;
import com.uwaytech.schoolCourse.ctrl.dto.SchoolCourseDto;
import com.uwaytech.schoolCourse.domain.CourseInfo;
import com.uwaytech.schoolCourse.domain.CourseListInfo;
import com.uwaytech.schoolCourse.domain.SchoolCourse;
import com.uwaytech.schoolCourse.service.ICourseService;
import com.uwaytech.schoolCourse.service.SchoolCourseService;
import com.uwaytech.supplierCourse.ctrl.dto.CategoryInfo;
import com.uwaytech.supplierCourse.ctrl.dto.SupplierCourseDto;
import com.uwaytech.supplierCourse.domain.SupplierCourse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by zeng on 2016/6/6.
 */
@RestController
@RequestMapping(value = "/school")
public class SchoolCourseController {

	@Resource
	private SchoolCourseService schoolCourseService;


	/**
	 * 查询学校课程，包括课程搜索、课程列表查询
	 *
	 * @param name       课程名称
	 * @param categoryId 课程分类ID
	 * @param type       排序方式：1、热门、2、最新
	 * @param pageNum    当前页
	 * @param pageSize   每页条数
	 * @return 返回课程列表
	 */
	@RequestMapping(value = "/v0.1/courses/list", method = RequestMethod.GET)
	public Object queryCourseList(@RequestParam(value = "name", required = false) String name,
	                              @RequestParam(value = "categoryId", required = false) Long categoryId,
	                              @RequestParam(value = "type", required = false) Integer type,
	                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
	                              @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

		Page<SchoolCourse> page = schoolCourseService.querySchoolCourseList(name, categoryId, type,
				SessionUtils.getUserType(), SessionUtils.getSchoolId(), pageNum, pageSize);

		SchoolCourseDto result = SchoolCourseDto.schoolCourseDto(page);

		return result;
	}

	/**
	 * 学校课程分类统计
	 *
	 * @param name       课程名称
	 * @param categoryId 课程分类ID
	 * @return
	 */
	@RequestMapping(value = "/v0.1/courses/categories", method = RequestMethod.GET)
	public Object queryCategoryList(@RequestParam(value = "name", required = false) String name,
	                                @RequestParam(value = "categoryId", required = false) Long categoryId) {

		List<CategoryInfo> list = schoolCourseService.queryCategoryList(name, categoryId, SessionUtils.getUserType(),
				SessionUtils.getSchoolId());

		return list;
	}

}
