package com.uwaytech.schoolCourse.ctrl.dto;

import com.github.pagehelper.Page;
import com.uwaytech.cool.common.enums.SchoolCourseTypeEnum;
import com.uwaytech.cool.common.enums.RoleTypeEnum;
import com.uwaytech.cool.common.util.Calculator;
import com.uwaytech.cool.common.util.DateUtil;
import com.uwaytech.schoolCourse.domain.SchoolCourse;

import java.util.ArrayList;
import java.util.List;

/**
 * SchoolCourseDto
 *
 * @author lyfang
 * @date 2016/6/15
 */
public class SchoolCourseDto {
	private long total;

	private Integer pageNum;

	private Integer pageSize;

	private List<SchoolCourseInfo> rows;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List<SchoolCourseInfo> getRows() {
		return rows;
	}

	public void setRows(List<SchoolCourseInfo> rows) {
		this.rows = rows;
	}

	public static SchoolCourseDto schoolCourseDto(Page<SchoolCourse> page) {
		SchoolCourseDto dto = new SchoolCourseDto();
		if (null != page) {
			dto.setTotal(new Long(page.getTotal()));
			dto.setPageSize(page.getPageSize());
			dto.setPageNum(page.getPageNum());
			if (null != page.getResult() && page.getResult().size() > 0) {
				List<SchoolCourseInfo> list = new ArrayList<SchoolCourseInfo>();
				for (SchoolCourse schoolCourse : page.getResult()) {
					SchoolCourseInfo schoolCourseInfo = new SchoolCourseInfo();
					schoolCourseInfo.setChapterNum(schoolCourse.getChapterNum());
					schoolCourseInfo.setCollectionNum(schoolCourse.getCollectionNum());
					schoolCourseInfo.setCourseDesc(schoolCourse.getDescription());
					schoolCourseInfo.setCourseId(Calculator.getCalcValue(schoolCourse.getId(),
							RoleTypeEnum.SCHOOL.getValue()).toString());
					schoolCourseInfo.setCourseName(schoolCourse.getName());
					schoolCourseInfo.setCoursePic(schoolCourse.getImgUrl());
					schoolCourseInfo.setCourseTime(schoolCourse.getCourseTime());
					schoolCourseInfo.setEndTime(DateUtil.dateToStr(schoolCourse.getEndTime()));
					schoolCourseInfo.setViewNum(schoolCourse.getViewNum());
					schoolCourseInfo.setType(SchoolCourseTypeEnum.valueOf(schoolCourse.getType().intValue()).getName());
					list.add(schoolCourseInfo);
				}
				dto.setRows(list);
			}
		}
		return dto;
	}
}
