package com.uwaytech.supplierResource.service;

import com.github.pagehelper.Page;
import com.uwaytech.cool.common.enums.ResTypeEnum;
import com.uwaytech.supplierCourse.ctrl.dto.CategoryInfo;
import com.uwaytech.supplierResource.ctrl.dto.SupplierResourceInfo;

import java.util.List;

/**
 * Created by rtyui on 2016/6/16.
 */
public interface SupplierResourceService {

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
	Page<SupplierResourceInfo> queryResourceList(String name, Long categoryId, Integer type, Integer schoolId,
	                                             Long mediaType, Long useType, ResTypeEnum resType, Integer pageNum,
	                                             Integer pageSize);

	/**
	 * 资源分类统计
	 *
	 * @param name       资源名称
	 * @param categoryId 资源分类ID
	 * @return
	 */
	List<CategoryInfo> queryCategoryList(String name, Long categoryId, Long mediaType, Long useType,
	                                     ResTypeEnum resType, Integer schoolId);

	/**
	 * 查询分类列表
	 * @param type 1、应用类型 2、媒体类型
	 * @return
	 */
	List<CategoryInfo> queryCommCategoryList(Integer type);
}
