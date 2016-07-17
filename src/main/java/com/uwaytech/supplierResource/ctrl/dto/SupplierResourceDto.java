package com.uwaytech.supplierResource.ctrl.dto;

import com.github.pagehelper.Page;
import com.uwaytech.cool.common.enums.ResTypeEnum;
import com.uwaytech.cool.common.enums.RoleTypeEnum;
import com.uwaytech.cool.common.util.Calculator;

import java.util.List;

/**
 * SupplierResourceDto
 *
 * @author lyfang
 * @date 2016/6/16
 */
public class SupplierResourceDto {
	private long total;

	private Integer pageNum;

	private Integer pageSize;

	private List<SupplierResourceInfo> rows;

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

	public List<SupplierResourceInfo> getRows() {
		return rows;
	}

	public void setRows(List<SupplierResourceInfo> rows) {
		this.rows = rows;
	}

	public static SupplierResourceDto supplierResourceDto(Page<SupplierResourceInfo> page, ResTypeEnum resType) {
		SupplierResourceDto dto = new SupplierResourceDto();
		if (null != page) {
			dto.setTotal(new Long(page.getTotal()));
			dto.setPageSize(page.getPageSize());
			dto.setPageNum(page.getPageNum());
			if (null != page.getResult() && page.getResult().size() > 0) {
				for (SupplierResourceInfo supplierResourceInfo : page.getResult()) {
					supplierResourceInfo.setResourceId(Calculator.getCalcValue(Long.parseLong(
									supplierResourceInfo.getResourceId()),
							RoleTypeEnum.SCHOOL.getValue()).toString());
				}
				dto.setRows(page.getResult());
			}
		}
		return dto;
	}
}
