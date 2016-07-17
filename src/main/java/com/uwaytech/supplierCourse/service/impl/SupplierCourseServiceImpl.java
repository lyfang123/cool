package com.uwaytech.supplierCourse.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.uwaytech.cool.common.constant.Constant;
import com.uwaytech.cool.common.enums.CheckStatusEnum;
import com.uwaytech.cool.common.enums.ResStatusEnum;
import com.uwaytech.cool.common.enums.StatusEnum;
import com.uwaytech.supplierCourse.ctrl.dto.CategoryInfo;
import com.uwaytech.supplierCourse.dao.ExtendSupplierCourseMapper;
import com.uwaytech.supplierCourse.dao.SupplierCourseMapper;
import com.uwaytech.supplierCourse.domain.SupplierCourse;
import com.uwaytech.supplierCourse.domain.SupplierCourseParam;
import com.uwaytech.supplierCourse.service.SupplierCourseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * SupplierCourseServiceImpl
 *
 * @author lyfang
 * @date 2016/6/15
 */

@Service
public class SupplierCourseServiceImpl implements SupplierCourseService {

	@Resource
	private ExtendSupplierCourseMapper extendSupplierCourseMapper;

	/**
	 * 查询前台页面课程，包括课程搜索、课程列表查询
	 *
	 * @param name       课程名称
	 * @param categoryId 课程分类ID
	 * @param type       排序方式：热门、最新
	 * @param pageNum    当前页
	 * @param pageSize   每页条数
	 * @return 返回课程列表
	 */
	@Override
	public Page<SupplierCourse> queryCourseList(String name, Long categoryId, Integer type, Integer pageNum,
	                                            Integer pageSize) {
		SupplierCourseParam param = new SupplierCourseParam();
		param.setCategoryId(categoryId);
		param.setType(type);
		if(StringUtils.isNotEmpty(name)){
			name = name.trim().toLowerCase();
		}
		param.setName(name);
		//查询审核通过且上架的课程
		param.setCheckStatus(CheckStatusEnum.PASS.getValue());
		param.setStatus(ResStatusEnum.ON_SHELVE.getValue());
		PageHelper.startPage(pageNum, pageSize);
		Page<SupplierCourse> page = (Page<SupplierCourse>) extendSupplierCourseMapper.queryCourseList(param);
		return page;
	}

	/**
	 * 查询前台页面课程分类统计
	 *
	 * @param name       课程名称
	 * @param categoryId 课程分类ID
	 * @return
	 */
	@Override
	public List<CategoryInfo> queryCategoryList(String name, Long categoryId) {
		SupplierCourseParam param = new SupplierCourseParam();
		param.setCategoryId(categoryId);
		param.setName(name);
		//查询审核通过且上架的课程统计
		param.setCheckStatus(CheckStatusEnum.PASS.getValue());
		param.setStatus(ResStatusEnum.ON_SHELVE.getValue());
		//查询二级分类
		param.setLevel(Constant.LEVEL_TWO);
		return extendSupplierCourseMapper.queryCategoryList(param);
	}
}
