package com.uwaytech.supplierResource.ctrl;

import com.github.pagehelper.Page;
import com.uwaytech.cool.common.enums.ResTypeEnum;
import com.uwaytech.httpclient.SessionUtils;
import com.uwaytech.supplierCourse.ctrl.dto.CategoryInfo;
import com.uwaytech.supplierResource.ctrl.dto.SupplierResourceDto;
import com.uwaytech.supplierResource.ctrl.dto.SupplierResourceInfo;
import com.uwaytech.supplierResource.service.SupplierResourceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * SupplierResourceController
 *
 * @author lyfang
 * @date 2016/6/16
 */

@RestController
@RequestMapping("/resource")
public class SupplierResourceController {

	@Resource
	private SupplierResourceService supplierResourceService;

	/**
	 * 查询资源，包括资源搜索、资源列表查询
	 *
	 * @param name       资源名称
	 * @param categoryId 资源分类ID
	 * @param type       排序方式：热门、最新
	 * @param mediaType  媒体类型
	 * @param useType    使用类型
	 * @param resType    资源类型：1、精品资源 2、学校资源
	 * @param pageNum    当前页
	 * @param pageSize   每页条数
	 * @return 返回课程列表
	 */
	@RequestMapping(value = "/v0.1/front/list", method = RequestMethod.GET)
	public Object queryResourceList(@RequestParam(value = "name", required = false) String name,
	                                @RequestParam(value = "categoryId", required = false) Long categoryId,
	                                @RequestParam(value = "type", required = false) Integer type,
	                                @RequestParam(value = "mediaType", required = false) Long mediaType,
	                                @RequestParam(value = "useType", required = false) Long useType,
	                                @RequestParam(value = "resType") ResTypeEnum resType,
	                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
	                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

		Page<SupplierResourceInfo> page = supplierResourceService.queryResourceList(name, categoryId, type,
				SessionUtils.getSchoolId(), mediaType, useType, resType, pageNum, pageSize);

		SupplierResourceDto result = SupplierResourceDto.supplierResourceDto(page, resType);

		return result;
	}

	/**
	 * 资源分类统计
	 *
	 * @param name       资源名称
	 * @param categoryId 资源分类ID
	 * @param mediaType  媒体类型
	 * @param useType    使用类型
	 * @param resType    资源类型：1、精品资源 2、学校资源
	 * @return
	 */
	@RequestMapping(value = "/v0.1/categories/list", method = RequestMethod.GET)
	public Object queryCategoryList(@RequestParam(value = "name", required = false) String name,
	                                @RequestParam(value = "categoryId", required = false) Long categoryId,
	                                @RequestParam(value = "mediaType", required = false) Long mediaType,
	                                @RequestParam(value = "useType", required = false) Long useType,
	                                @RequestParam(value = "resType") ResTypeEnum resType) {

		List<CategoryInfo> list = supplierResourceService.queryCategoryList(name, categoryId, mediaType, useType,
				resType, SessionUtils.getSchoolId());

		return list;
	}

	/**
	 * 查询分类列表
	 * @param type 1、应用类型 2、媒体类型
	 * @return
	 */
	@RequestMapping(value = "/v0.1/categories", method = RequestMethod.GET)
	public Object queryCusCategoryList(@RequestParam(value = "type") Integer type) {

		List<CategoryInfo> list = supplierResourceService.queryCommCategoryList(type);

		return list;
	}
}
