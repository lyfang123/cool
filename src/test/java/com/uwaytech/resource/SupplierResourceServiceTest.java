package com.uwaytech.resource;

import com.github.pagehelper.Page;
import com.uwaytech.JunitTestConfig;
import com.uwaytech.cool.common.enums.ResTypeEnum;
import com.uwaytech.supplierCourse.ctrl.dto.CategoryInfo;
import com.uwaytech.supplierCourse.domain.SupplierCourse;
import com.uwaytech.supplierResource.ctrl.dto.SupplierResourceInfo;
import com.uwaytech.supplierResource.service.SupplierResourceService;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * SupplierResourceServiceTest
 *
 * @author lyfang
 * @date 2016/6/16
 */
public class SupplierResourceServiceTest extends JunitTestConfig {

	@Resource
	private SupplierResourceService supplierResourceService;

	/**
	 * 查询资源
	 */
	@Test
	public void queryResourceList() {
		String name = "";
		Long categoryId = null;
		Integer type = 1;
		Long mediaType = null;
		Long useType = null;
		Integer schoolId = 1;
		ResTypeEnum resType = ResTypeEnum.SCHOOL_RES;
		Page<SupplierResourceInfo> page = supplierResourceService.queryResourceList(name, categoryId, type, schoolId,
				mediaType, useType, resType, 1, 10);
		Assert.assertEquals(page != null, true);
		Assert.assertEquals(page.getResult() != null, true);
		for (SupplierResourceInfo supplierResourceInfo : page.getResult()) {
			System.out.println(supplierResourceInfo.toString());
		}
	}

	/**
	 * 查询资源分类统计
	 */
	@Test
	public void queryCategoryList() {
		String name = "";
		Long categoryId = 1L;
		Long mediaType = null;
		Long useType = null;
		Integer schoolId = 1;
		ResTypeEnum resType = ResTypeEnum.SUPPLIER_RES;
		List<CategoryInfo> list = supplierResourceService.queryCategoryList(name, categoryId,
				mediaType, useType, resType, schoolId);
		Assert.assertEquals(list != null, true);
		Assert.assertEquals(list.size() >= 0, true);
		for (CategoryInfo categoryInfo : list) {
			System.out.println(categoryInfo.toString());
		}
	}

}
