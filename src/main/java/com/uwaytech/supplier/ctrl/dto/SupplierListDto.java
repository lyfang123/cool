package com.uwaytech.supplier.ctrl.dto;

import java.util.List;

/**
 * Created by zeng on 2016/7/12.
 */
public class SupplierListDto {
	private Long total;
	private Integer pageNum;
	private Integer pageSize;
	private List<SupplierDto> rows;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
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

	public List<SupplierDto> getRows() {
		return rows;
	}

	public void setRows(List<SupplierDto> rows) {
		this.rows = rows;
	}
}
