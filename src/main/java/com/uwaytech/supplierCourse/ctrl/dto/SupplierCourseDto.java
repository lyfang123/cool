package com.uwaytech.supplierCourse.ctrl.dto;

import com.github.pagehelper.Page;
import com.uwaytech.cool.common.enums.RoleTypeEnum;
import com.uwaytech.cool.common.util.Calculator;
import com.uwaytech.supplierCourse.domain.SupplierCourse;

import java.util.ArrayList;
import java.util.List;

/**
 * SupplierCourseDto
 *
 * @author lyfang
 * @date 2016/6/15
 */
public class SupplierCourseDto {

	private long total;

	private Integer pageNum;

	private Integer pageSize;

	private List<SupplierCourseInfo> rows;

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

	public List<SupplierCourseInfo> getRows() {
		return rows;
	}

	public void setRows(List<SupplierCourseInfo> rows) {
		this.rows = rows;
	}

	public static SupplierCourseDto supplierCourseDto(Page<SupplierCourse> page) {
		SupplierCourseDto dto = new SupplierCourseDto();
		if (null != page) {
			dto.setTotal(new Long(page.getTotal()));
			dto.setPageSize(page.getPageSize());
			dto.setPageNum(page.getPageNum());
			if (null != page.getResult() && page.getResult().size() > 0) {
				List<SupplierCourseInfo> list = new ArrayList<SupplierCourseInfo>();
				for (SupplierCourse supplierCourse : page.getResult()) {
					SupplierCourseInfo supplierCourseInfo = new SupplierCourseInfo();
					supplierCourseInfo.setChapterNum(supplierCourse.getChapterNum());
					supplierCourseInfo.setCollectionNum(supplierCourse.getCollectionNum());
					supplierCourseInfo.setCourseDesc(supplierCourse.getDescription());
					supplierCourseInfo.setCourseId(Calculator.getCalcValue(supplierCourse.getId(),
							RoleTypeEnum.SUPPLIER.getValue()).toString());
					supplierCourseInfo.setCourseName(supplierCourse.getName());
					supplierCourseInfo.setCoursePic(supplierCourse.getImgUrl());
					supplierCourseInfo.setCourseTime(supplierCourse.getCourseTime());
					supplierCourseInfo.setViewNum(supplierCourse.getViewNum());
					list.add(supplierCourseInfo);
				}
				dto.setRows(list);
			}
		}
		return dto;
	}
}
