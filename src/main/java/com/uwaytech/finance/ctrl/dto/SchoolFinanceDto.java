package com.uwaytech.finance.ctrl.dto;

import java.util.List;

/**
 * SchoolFinanceDto
 *
 * @author lyfang
 * @date 2016/6/6
 */
public class SchoolFinanceDto {

	private Long total;

	private Integer pageNum;

	private Integer pageSize;

	private List<SchoolFinance> rows;

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

	public List<SchoolFinance> getRows() {
		return rows;
	}

	public void setRows(List<SchoolFinance> rows) {
		this.rows = rows;
	}
}
