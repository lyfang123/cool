package com.uwaytech.supplierCourse.ctrl;

import com.github.pagehelper.Page;
import com.uwaytech.category.service.CategoryService;
import com.uwaytech.schoolCourse.domain.CourseListInfo;
import com.uwaytech.spring_interceptors.UnAccessTokenAuth;
import com.uwaytech.supplierCourse.ctrl.dto.CategoryInfo;
import com.uwaytech.supplierCourse.ctrl.dto.SupplierCourseDto;
import com.uwaytech.supplierCourse.domain.SupplierCourse;
import com.uwaytech.supplierCourse.service.SupplierCourseService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * SupplierCourseController
 *
 * @author lyfang
 * @date 2016/6/15
 */

@RestController
@RequestMapping("/index")
public class SupplierCourseController {

	@Resource
	private SupplierCourseService supplierCourseService;

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
	@RequestMapping(value = "/v0.1/courses/search", method = RequestMethod.GET)
	@UnAccessTokenAuth
	public Object queryCourseList(@RequestParam(value = "name", required = false) String name,
	                              @RequestParam(value = "categoryId", required = false) Long categoryId,
	                              @RequestParam(value = "type", required = false) Integer type,
	                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
	                              @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

		Page<SupplierCourse> page = supplierCourseService.queryCourseList(name, categoryId, type, pageNum, pageSize);

		SupplierCourseDto result = SupplierCourseDto.supplierCourseDto(page);

		return result;
	}

	/**
	 * 查询前台页面课程分类统计
	 *
	 * @param name       课程名称
	 * @param categoryId 课程分类ID
	 * @return
	 */
	@RequestMapping(value = "/v0.1/courses/categories", method = RequestMethod.GET)
	public Object queryCategoryList(@RequestParam(value = "name", required = false) String name,
	                                @RequestParam(value = "categoryId", required = false) Long categoryId) {

		List<CategoryInfo> list = supplierCourseService.queryCategoryList(name, categoryId);

		return list;
	}
}

