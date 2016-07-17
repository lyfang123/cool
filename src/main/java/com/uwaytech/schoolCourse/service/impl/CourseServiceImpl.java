package com.uwaytech.schoolCourse.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.uwaytech.common.exception.NotDataFoundException;
import com.uwaytech.common.exception.ParamMissException;
import com.uwaytech.common.exception.PermissionDeniedException;
import com.uwaytech.cool.common.constant.Constant;
import com.uwaytech.cool.common.enums.CheckStatusEnum;
import com.uwaytech.cool.common.enums.RecommendEnum;
import com.uwaytech.cool.common.enums.ResStatusEnum;
import com.uwaytech.cool.common.enums.StatusEnum;
import com.uwaytech.material.dao.MaterialMapper;
import com.uwaytech.material.domain.Material;
import com.uwaytech.material.domain.MaterialExample;
import com.uwaytech.schoolCourse.dao.ExtendCourseMapper;
import com.uwaytech.schoolCourse.dao.SchoolCourseMapper;
import com.uwaytech.schoolCourse.domain.*;
import com.uwaytech.schoolCourse.service.ICourseService;
import com.uwaytech.supplierCourse.dao.SupplierCourseMapper;
import com.uwaytech.supplierCourse.domain.SupplierCourse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zeng on 2016/6/6.
 */
@Service("courseService")
public class CourseServiceImpl implements ICourseService {

	@Resource
	private SupplierCourseMapper supplierCourseMapper;
	@Resource
	private SchoolCourseMapper schoolCourseMapper;
	@Resource
	private ExtendCourseMapper courseMapper;
	@Resource
	private MaterialMapper materialMapper;

	@Override
	public int addCourse(CourseInfo courseInfo, Byte type, Long id, Long grouping) {
		//判断素材是否存在
		MaterialExample example = new MaterialExample();
		List<Long> list = new ArrayList<>();
		for (ChapterInfo chapterInfo : courseInfo.getChapter()) {
			for (MaterialEntity materialEntity : chapterInfo.getMaterials()) {
				list.add(materialEntity.getMaterialId());
			}
		}
		example.createCriteria().andIdIn(list);
		int total = materialMapper.countByExample(example);
		if (list.size() != total) {
			throw new NotDataFoundException("素材不存在");
		} else {
			if (type == Constant.SUPPLIER) {
				//供应商课程
				SupplierCourse supplierCourse = CourseInfo.toSupplierCourse(courseInfo);
				if (supplierCourse.getAutoType() ==  Constant.AUTO) {
					//课程自动上架
					supplierCourse.setStatus(Integer.valueOf(ResStatusEnum.AUTO_SHELVE.getValue()));
				} else {
					//课程下架
					supplierCourse.setStatus(Integer.valueOf(ResStatusEnum.OFF_SHELVE.getValue()));
				}
				//课程未审核
				supplierCourse.setCheckStatus(Integer.valueOf(CheckStatusEnum.NOT_REVIEW.getValue()));
				//课程不推荐
				supplierCourse.setRecommend(Integer.valueOf(RecommendEnum.NOT_RECOMMEND.getValue()));
				supplierCourse.setId(id);
				supplierCourse.setSupplierId(grouping);
				supplierCourse.setType(Constant.SUPPLIER_TYPE);
				supplierCourse.setCreateTime(new Date());
				//新增课程
				supplierCourseMapper.insertSelective(supplierCourse);
				//新增课程素材关联关系
				if (courseInfo.getChapter().isEmpty()) {
					throw new ParamMissException("请添加课程");
				} else {
					setMaterialCourse(courseInfo.getChapter(), id);
				}
				//新增资源与课程关联关系
				if (!courseInfo.getResource().isEmpty()) {
					setMaterialCourse(courseInfo.getResource(), id);
				}
			} else {
				//学校课程
				SchoolCourse schoolCourse = CourseInfo.toSchoolCourse(courseInfo);
				schoolCourse.setId(id);
				schoolCourse.setSchoolId(grouping);
				//课程未推荐
				schoolCourse.setRecommend(Integer.valueOf(RecommendEnum.NOT_RECOMMEND.getValue()));
				schoolCourse.setCreateTime(new Date());
				//新增课程
				schoolCourseMapper.insertSelective(schoolCourse);
				//新增课程素材关联关系
				if (courseInfo.getChapter().isEmpty()) {
					throw new ParamMissException("请添加课程");
				} else {
					setMaterialCourse(courseInfo.getChapter(), id);
				}
				//新增资源与课程关联关系
				if (!courseInfo.getResource().isEmpty()) {
					setMaterialCourse(courseInfo.getResource(), id);
				}
			}
			//素材发布状态
			return updateMaterial(courseInfo.getChapter());
		}
	}

	@Override
	public void deleteCourse(Long id, Byte type) {
		if (type == Constant.SUPPLIER) {
			//供应商课程
			SupplierCourse course = supplierCourseMapper.selectByPrimaryKey(id);
			if (null == course) {
				throw new NotDataFoundException("课程不存在");
			} else {
				//课程下架允许删除
				if (ResStatusEnum.OFF_SHELVE.getValue() == course.getStatus()) {
					SupplierCourse supplierCourse = new SupplierCourse();
					supplierCourse.setId(id);
					supplierCourse.setStatus(Integer.valueOf(ResStatusEnum.DELETE.getValue()));
					supplierCourseMapper.updateByPrimaryKeySelective(supplierCourse);
				} else {
					//课程上架不允许删除
					throw new PermissionDeniedException("该课程已上架，不能删除");
				}
			}
		} else {
			//学校课程
			SchoolCourse schoolCourse = new SchoolCourse();
			schoolCourse.setId(id);
			schoolCourse.setStatus(Integer.valueOf(ResStatusEnum.DELETE.getValue()));
			schoolCourseMapper.updateByPrimaryKeySelective(schoolCourse);
		}
	}

	@Override
	public int updateCourse(CourseInfo courseInfo, Byte type) {
		//供应商课程
		if (type == Constant.SUPPLIER) {
			SupplierCourse course = supplierCourseMapper.selectByPrimaryKey(courseInfo.getId());
			if (null == course) {
				throw new NotDataFoundException("课程不存在");
			} else {
				//课程下架允许修改
				if (ResStatusEnum.OFF_SHELVE.getValue() == course.getStatus()) {
					SupplierCourse supplierCourse = CourseInfo.toSupplierCourse(courseInfo);
					supplierCourse.setStatus(Integer.valueOf(ResStatusEnum.OFF_SHELVE.getValue()));
					supplierCourse.setCheckStatus(Integer.valueOf(CheckStatusEnum.NOT_REVIEW.getValue()));
					//更新课程
					supplierCourseMapper.updateByPrimaryKeySelective(supplierCourse);
					//删除素材与课程关联关系
					courseMapper.deleteCourseMaterial(courseInfo.getId());
					if (courseInfo.getChapter().isEmpty()) {
						throw new ParamMissException("请添加课程");
					} else {
						setMaterialCourse(courseInfo.getChapter(), courseInfo.getId());
					}
					//新增资源与课程关联关系
					if (!courseInfo.getResource().isEmpty()) {
						setMaterialCourse(courseInfo.getResource(), courseInfo.getId());
					}
				} else {
					//课程上架不允许修改
					throw new PermissionDeniedException("该课程已上架，不能修改");
				}
			}
		} else {
			SchoolCourse course = schoolCourseMapper.selectByPrimaryKey(courseInfo.getId());
			if (null == course) {
				throw new NotDataFoundException("课程不存在");
			}
			//学校课程
			SchoolCourse schoolCourse = CourseInfo.toSchoolCourse(courseInfo);
			//更新课程
			schoolCourseMapper.updateByPrimaryKeySelective(schoolCourse);
			//删除素材与课程关联关系
			courseMapper.deleteCourseMaterial(courseInfo.getId());
			if (courseInfo.getChapter().isEmpty()) {
				throw new ParamMissException("请添加课程");
			} else {
				setMaterialCourse(courseInfo.getChapter(), courseInfo.getId());
			}
			//新增资源与课程关联关系
			if (!courseInfo.getResource().isEmpty()) {
				setMaterialCourse(courseInfo.getResource(), courseInfo.getId());
			}
		}
		//素材发布状态
		return updateMaterial(courseInfo.getChapter());
	}

	@Override
	public CourseInfo findCourseDetail(Long courseId, Byte type) {
		CourseInfo courseInfo = new CourseInfo();
		if ((type == Constant.SUPPLIER) || (type == Constant.ADMIN)) {
			courseInfo = courseMapper.findSupplierCourseDetail(courseId);
		} else {
			courseInfo = courseMapper.findSchoolCourseDetail(courseId);
		}
		return courseInfo;
	}

	@Override
	public CourseListInfo findCourseList(CourseTermInfo term, Byte userType, Integer pageNum, Integer pageSize) {
		CourseListInfo courseListInfo = new CourseListInfo();
		courseListInfo.setPageNum(pageNum);
		courseListInfo.setPageSize(pageSize);
		Page<CourseDetail> page = new Page<>();
		//供应商和管理员查看供应商课程列表
		if ((userType == Constant.SUPPLIER) || (userType == Constant.ADMIN)) {
			//判断是否模糊搜索
			if (StringUtils.isBlank(term.getName())) {
				//精确搜索
				 PageHelper.startPage(pageNum, pageSize);
				 page = courseMapper.findSupplierCourseList(term);
			} else {
				//模糊搜索
				PageHelper.startPage(pageNum, pageSize);
				page = courseMapper.findSupplierCourseListByKeyword(term);
			}
			courseListInfo.setTotal(page.getTotal());
			courseListInfo.setRows(page.getResult());
		} else {
			//学校课程列表
			if (StringUtils.isBlank(term.getName())) {
				//精确搜索
				PageHelper.startPage(pageNum, pageSize);
				page = courseMapper.findSchoolCourseList(term);
			} else {
				//模糊搜索
				PageHelper.startPage(pageNum, pageSize);
				page = courseMapper.findSchoolCourseListByKeyword(term);
			}
			courseListInfo.setTotal(page.getTotal());
			courseListInfo.setRows(page.getResult());
		}
		return courseListInfo;
	}

	@Override
	public void courseReview(Long courseId, Integer checkStatus, String reason) {
		SupplierCourse course = supplierCourseMapper.selectByPrimaryKey(courseId);
		if (null == course) {
			throw new NotDataFoundException("课程不存在");
		} else {
			if (course.getStatus() == ResStatusEnum.AUTO_SHELVE.getValue()) {
				course.setStatus(Integer.valueOf(ResStatusEnum.ON_SHELVE.getValue()));
			}
		}
		course.setCheckStatus(checkStatus);
		course.setReason(reason);
		supplierCourseMapper.updateByPrimaryKeySelective(course);
	}

	@Override
	public void setShelve(Long courseId, Integer status) {
		//获取课程的详情
		SupplierCourse course = supplierCourseMapper.selectByPrimaryKey(courseId);
		if (null == course) {
			throw new NotDataFoundException("课程不存在");
		} else {
			if (CheckStatusEnum.PASS.getValue() == course.getCheckStatus()) {
				//审核通过
				SupplierCourse supplierCourse = new SupplierCourse();
				supplierCourse.setId(courseId);
				supplierCourse.setStatus(status);
				supplierCourseMapper.updateByPrimaryKeySelective(supplierCourse);
			} else if (CheckStatusEnum.NOT_REVIEW.getValue() == course.getCheckStatus()) {
				//未审核
				throw new PermissionDeniedException("该课程还未审核");
			} else if (CheckStatusEnum.REFUSE.getValue() == course.getCheckStatus()) {
				//审核未通过
				throw new PermissionDeniedException("该课程未通过审核");
			}
		}
	}

	@Override
	public void recommendSchoolCourse(Long courseId, Integer recommend) {
		SchoolCourse course = new SchoolCourse();
		course.setId(courseId);
		course.setRecommend(recommend);
		schoolCourseMapper.updateByPrimaryKeySelective(course);
	}

	@Override
	public void recommendSupplierCourse(Long courseId, Integer recommend) {
		SupplierCourse supplierCourse = supplierCourseMapper.selectByPrimaryKey(courseId);
		if (null == supplierCourse) {
			throw new NotDataFoundException("课程不存在");
		} else {
			if (supplierCourse.getCheckStatus() == CheckStatusEnum.NOT_REVIEW.getValue()) {
				throw new PermissionDeniedException("该课程还未审核");
			} else if (CheckStatusEnum.REFUSE.getValue() == supplierCourse.getCheckStatus()) {
				throw new PermissionDeniedException("该课程未通过审核");
			} else if (CheckStatusEnum.PASS.getValue() == supplierCourse.getCheckStatus()) {
				if (supplierCourse.getStatus() == ResStatusEnum.OFF_SHELVE.getValue()) {
					throw new PermissionDeniedException("该课还未上架");
				} else if (supplierCourse.getStatus() == ResStatusEnum.ON_SHELVE.getValue()) {
					SupplierCourse course = new SupplierCourse();
					course.setId(courseId);
					course.setRecommend(recommend);
					supplierCourseMapper.updateByPrimaryKeySelective(course);
				}
			}
		}
	}

	@Override
	public void updateViewNumber(Long courseId) {
		if (courseId%2 == Constant.ONE) {
			//供应商课程
			courseMapper.updateSupplierViewNum(courseId);
		} else {
			//学校课程
			courseMapper.updateSchoolViewNum(courseId);
		}
	}

	/**
	 * 素材发布
	 *
	 * @param chapterInfoList
	 * @return
	 */
	public int updateMaterial(List<ChapterInfo> chapterInfoList) {
		List<Material> materials = new ArrayList<>();
		for (ChapterInfo chapterInfo : chapterInfoList) {
			for (MaterialEntity materialEntity : chapterInfo.getMaterials()) {
				Material material = new Material();
				material.setId(materialEntity.getMaterialId());
				material.setStatus(ResStatusEnum.ON_SHELVE.getValue());
				materials.add(material);
			}
		}
		return courseMapper.updateMaterials(materials);
	}

	/**
	 * 添加素材与课程关联关系
	 *  @param chapterInfoList
	 * @param id
	 */
	public void setMaterialCourse(List<ChapterInfo> chapterInfoList, Long id) {
		List<MaterialEntity> materials = new ArrayList<>();
		for (ChapterInfo chapterInfo : chapterInfoList) {
			String chapter = chapterInfo.getChapter();
			for (MaterialEntity materialEntity : chapterInfo.getMaterials()) {
				materialEntity.setCourseId(id);
				materialEntity.setChapter(chapter);
				materials.add(materialEntity);
			}
		}
		courseMapper.insertCourseMaterial(materials);
	}

}
