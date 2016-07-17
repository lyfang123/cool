package com.uwaytech.schoolCourse.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.uwaytech.common.exception.ParamErrorException;
import com.uwaytech.cool.common.constant.Constant;
import com.uwaytech.cool.common.enums.RecommendEnum;
import com.uwaytech.cool.common.enums.RoleTypeEnum;
import com.uwaytech.cool.common.enums.SchoolCourseResStatusEnum;
import com.uwaytech.cool.common.util.Calculator;
import com.uwaytech.httpclient.bo.UserTypeEnum;
import com.uwaytech.schoolCourse.ctrl.dto.CourseRecommendInfo;
import com.uwaytech.schoolCourse.dao.ExtendSchoolCourseMapper;
import com.uwaytech.schoolCourse.dao.SchoolCourseMapper;
import com.uwaytech.schoolCourse.domain.SchoolCourse;
import com.uwaytech.schoolCourse.domain.SchoolCourseExample;
import com.uwaytech.schoolCourse.domain.SchoolCourseParam;
import com.uwaytech.schoolCourse.service.SchoolCourseService;
import com.uwaytech.supplierCourse.ctrl.dto.CategoryInfo;
import com.uwaytech.supplierCourse.dao.SupplierCourseMapper;
import com.uwaytech.supplierCourse.domain.SupplierCourse;
import com.uwaytech.supplierCourse.domain.SupplierCourseExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * SchoolCourseServiceImpl
 * 前台学校课程查询
 *
 * @author lyfang
 * @date 2016/6/15
 */

@Service
public class SchoolCourseServiceImpl implements SchoolCourseService {

	@Resource
	private ExtendSchoolCourseMapper extendSchoolCourseMapper;

	@Resource
	private SchoolCourseMapper schoolCourseMapper;

	@Resource
	private SupplierCourseMapper supplierCourseMapper;

	/**
	 * 查询学校课程，包括课程搜索、课程列表查询
	 *
	 * @param name       课程名称
	 * @param categoryId 课程分类ID
	 * @param type       排序方式：热门、最新
	 * @param userType
	 * @param schoolId
	 * @param pageNum    当前页
	 * @param pageSize   每页条数   @return 返回课程列表
	 */
	@Override
	public Page<SchoolCourse> querySchoolCourseList(String name, Long categoryId, Integer type, UserTypeEnum userType,
	                                                Integer schoolId, Integer pageNum, Integer pageSize) {
		SchoolCourseParam param = new SchoolCourseParam();
		param.setCategoryId(categoryId);
		param.setType(type);
		if(StringUtils.isNotEmpty(name)){
			name = name.trim().toLowerCase();
		}
		param.setName(name);
		//查询有效课程
		param.setStatus(SchoolCourseResStatusEnum.VALID.getValue());
		param.setUserType(userType.byteValue());
		param.setSchoolId(schoolId);
		PageHelper.startPage(pageNum, pageSize);
		Page<SchoolCourse> page = (Page<SchoolCourse>) extendSchoolCourseMapper.querySchoolCourseList(param);
		return page;
	}

	/**
	 * 查询课程分类统计
	 *
	 * @param name       课程名称
	 * @param categoryId 分类ID
	 * @param userType   用户类型
	 * @param schoolId
	 * @return 返回查询统计结果
	 */
	@Override
	public List<CategoryInfo> queryCategoryList(String name, Long categoryId, UserTypeEnum userType, Integer schoolId) {
		SchoolCourseParam param = new SchoolCourseParam();
		param.setCategoryId(categoryId);
		param.setName(name);
		//查询有效课程
		param.setStatus(SchoolCourseResStatusEnum.VALID.getValue());
		param.setUserType(userType.byteValue());
		param.setSchoolId(schoolId);
		//查询二级分类
		param.setLevel(Constant.LEVEL_TWO);
		return extendSchoolCourseMapper.queryCategoryList(param);
	}

	@Override
	public CourseRecommendInfo queryCourseRecommends(Long courseId, Integer pageNum, Integer pageSize) {
		Calculator cal = new Calculator(courseId);
		CourseRecommendInfo dto = null;
		if(cal.getType().equals(RoleTypeEnum.SCHOOL)){
			//学校课程
			SchoolCourse course = schoolCourseMapper.selectByPrimaryKey(courseId);
			if(null != course){
				Long categoryId = course.getCategoryId();
				//查询学校推荐课程
				SchoolCourseExample example = new SchoolCourseExample();
				example.createCriteria().andCategoryIdEqualTo(categoryId).
						andRecommendEqualTo(new Integer(RecommendEnum.RECOMMEND.getValue()));
				PageHelper.startPage(pageNum,pageSize);
				Page<SchoolCourse> page = (Page<SchoolCourse>)schoolCourseMapper.selectByExample(example);
				dto = CourseRecommendInfo.toSchoolCourse(page);
			}else{
				throw new ParamErrorException("参数课程Id不存在");
			}
		}else if(cal.getType().equals(RoleTypeEnum.SUPPLIER)){
			//供应商课程
			SupplierCourse course = supplierCourseMapper.selectByPrimaryKey(courseId);
			if(null != course){
				Long categoryId = course.getCategoryId();
				//查询供应商推荐课程
				SupplierCourseExample example = new SupplierCourseExample();
				example.createCriteria().andCategoryIdEqualTo(categoryId).
						andRecommendEqualTo(new Integer(RecommendEnum.RECOMMEND.getValue()));
				PageHelper.startPage(pageNum,pageSize);
				Page<SupplierCourse> page = (Page<SupplierCourse>)supplierCourseMapper.selectByExample(example);
				dto = CourseRecommendInfo.toSupplierCourse(page);
			}else{
				throw new ParamErrorException("参数课程Id不存在");
			}
		}
		return dto;
	}
}
