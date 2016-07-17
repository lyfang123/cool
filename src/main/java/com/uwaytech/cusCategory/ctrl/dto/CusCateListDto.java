package com.uwaytech.cusCategory.ctrl.dto;

import com.uwaytech.cusCategory.domain.CusCategory;

import java.util.List;

/**
 * Created by zeng on 2016/7/5.
 */
public class CusCateListDto {
	private Long total;
	private Integer pageSize;
	private Integer pageNum;
	private List<CusCategory> rows;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public List<CusCategory> getRows() {
		return rows;
	}

	public void setRows(List<CusCategory> rows) {
		this.rows = rows;
	}
}
