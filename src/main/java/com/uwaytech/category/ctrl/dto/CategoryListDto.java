package com.uwaytech.category.ctrl.dto;

import com.uwaytech.category.domain.Category;

import java.util.List;

/**
 * Created by zeng on 2016/7/12.
 */
public class CategoryListDto {
	private Long total;
	private Integer pageSize;
	private Integer pageNum;
	private List<Category> rows;

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

	public List<Category> getRows() {
		return rows;
	}

	public void setRows(List<Category> rows) {
		this.rows = rows;
	}
}
