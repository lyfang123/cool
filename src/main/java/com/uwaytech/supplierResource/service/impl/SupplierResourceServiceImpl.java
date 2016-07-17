package com.uwaytech.supplierResource.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.uwaytech.cool.common.constant.Constant;
import com.uwaytech.cool.common.enums.CheckStatusEnum;
import com.uwaytech.cool.common.enums.ResStatusEnum;
import com.uwaytech.cool.common.enums.ResTypeEnum;
import com.uwaytech.cool.common.enums.SchoolCourseResStatusEnum;
import com.uwaytech.supplierCourse.ctrl.dto.CategoryInfo;
import com.uwaytech.supplierResource.ctrl.dto.SupplierResourceInfo;
import com.uwaytech.supplierResource.dao.ExtendSupplierResourceMapper;
import com.uwaytech.supplierResource.domain.SupplierResourceParam;
import com.uwaytech.supplierResource.service.SupplierResourceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * SupplierResourceServiceImpl
 *
 * @author lyfang
 * @date 2016/6/16
 */
@Service
public class SupplierResourceServiceImpl implements SupplierResourceService {

	@Resource
	private ExtendSupplierResourceMapper extendSupplierResourceMapper;

	/**
	 * 查询资源，包括资源搜索、资源列表查询
	 *
	 * @param name       资源名称
	 * @param categoryId 资源分类ID
	 * @param type       排序方式：热门、最新
	 * @param schoolId
	 * @param mediaType  媒体类型
	 * @param useType    使用类型
	 * @param resType    资源类型：1、精品资源 2、学校资源
	 * @param pageNum    当前页
	 * @param pageSize   每页条数      @return 返回课程列表
	 */
	@Override
	public Page<SupplierResourceInfo> queryResourceList(String name, Long categoryId, Integer type, Integer schoolId,
	                                                    Long mediaType, Long useType, ResTypeEnum resType,
	                                                    Integer pageNum,
	                                                    Integer pageSize) {

		SupplierResourceParam param = new SupplierResourceParam();
		param.setCategoryId(categoryId);
		param.setType(type);
		if(StringUtils.isNotEmpty(name)){
			name = name.trim().toLowerCase();
		}
		param.setName(name);
		param.setMediaType(mediaType);
		param.setUseType(useType);
		Page<SupplierResourceInfo> page = null;
		//学校资源
		if (ResTypeEnum.SCHOOL_RES.getValue() == resType.getValue()) {
			param.setSchoolId(schoolId);
			//查询学校有效资源
			param.setStatus(SchoolCourseResStatusEnum.VALID.getValue());
			PageHelper.startPage(pageNum, pageSize);
			page = (Page<SupplierResourceInfo>) extendSupplierResourceMapper.querySchoolResourceList(param);
			//供应商资源（精品资源）
		} else if (ResTypeEnum.SUPPLIER_RES.getValue() == resType.getValue()) {
			param.setStatus(ResStatusEnum.ON_SHELVE.getValue());
			param.setCheckStatus(CheckStatusEnum.PASS.getValue());
			PageHelper.startPage(pageNum, pageSize);
			page = (Page<SupplierResourceInfo>) extendSupplierResourceMapper.querySupplierResourceList(param);
		}
		return page;
	}

	/**
	 * 资源分类统计
	 *
	 * @param name       资源名称
	 * @param categoryId 资源分类ID
	 * @param mediaType
	 * @param useType
	 * @param resType
	 * @param schoolId   @return
	 */
	@Override
	public List<CategoryInfo> queryCategoryList(String name, Long categoryId, Long mediaType, Long useType,
	                                            ResTypeEnum resType, Integer schoolId) {
		SupplierResourceParam param = new SupplierResourceParam();
		param.setCategoryId(categoryId);
		param.setName(name);
		param.setMediaType(mediaType);
		param.setUseType(useType);
		List<CategoryInfo> list = null;
		//查询二级分类
		param.setLevel(Constant.LEVEL_TWO);
		//学校资源
		if (ResTypeEnum.SCHOOL_RES.getValue() == resType.getValue()) {
			param.setSchoolId(schoolId);
			//查询学校有效资源
			param.setStatus(SchoolCourseResStatusEnum.VALID.getValue());
			list = extendSupplierResourceMapper.querySchoolCategoryList(param);
			//供应商资源（精品资源）
		} else if (ResTypeEnum.SUPPLIER_RES.getValue() == resType.getValue()) {
			param.setStatus(ResStatusEnum.ON_SHELVE.getValue());
			param.setCheckStatus(CheckStatusEnum.PASS.getValue());
			list = extendSupplierResourceMapper.querySupplierCategoryList(param);
		}
		return list;
	}

	/**
	 * 查询分类列表
	 * @param type 1、应用类型 2、媒体类型
	 * @return
	 */
	@Override
	public List<CategoryInfo> queryCommCategoryList(Integer type) {
		return extendSupplierResourceMapper.queryCommCategoryList(type);
	}
}
