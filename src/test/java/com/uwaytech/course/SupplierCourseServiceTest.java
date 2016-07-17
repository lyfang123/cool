package com.uwaytech.course;

import com.github.pagehelper.Page;
import com.uwaytech.JunitTestConfig;
import com.uwaytech.httpclient.bo.UserTypeEnum;
import com.uwaytech.schoolCourse.domain.SchoolCourse;
import com.uwaytech.supplierCourse.ctrl.dto.CategoryInfo;
import com.uwaytech.supplierCourse.domain.SupplierCourse;
import com.uwaytech.supplierCourse.service.SupplierCourseService;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * SupplierCourseServiceTest
 *
 * @author lyfang
 * @date 2016/6/16
 */
public class SupplierCourseServiceTest extends JunitTestConfig {

	@Resource
	private SupplierCourseService supplierCourseService;

	/**
	 * 查询供应商课程
	 */
	@Test
	public void queryCourseList() {
		String name = "测试";
		Long categoryId = null;
		Integer type = 1;
		Page<SupplierCourse> page = supplierCourseService.queryCourseList(name, categoryId, type, 1, 10);
		Assert.assertEquals(page != null, true);
		Assert.assertEquals(page.getResult() != null, true);
		for (SupplierCourse supplierCourse : page.getResult()) {
			System.out.println(supplierCourse.toString());
		}
	}

	/**
	 * 查询课程分类统计
	 */
	@Test
	public void queryCategoryList() {
		String name = "";
		Long categoryId = null;
		Integer schoolId = 1;
		List<CategoryInfo> list = supplierCourseService.queryCategoryList(name, categoryId);
		Assert.assertEquals(list != null, true);
		Assert.assertEquals(list.size() >= 0, true);
		for (CategoryInfo categoryInfo : list) {
			System.out.println(categoryInfo.toString());
		}
	}
}
