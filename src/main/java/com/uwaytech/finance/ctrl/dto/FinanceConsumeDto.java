package com.uwaytech.finance.ctrl.dto;

import com.github.pagehelper.Page;

import java.util.List;

/**
 * FinanceConsumeDto
 *
 * @author lyfang
 * @date 2016/6/8
 */
public class FinanceConsumeDto {
	private Long total;

	private Integer pageNum;

	private Integer pageSize;

	private List<FinanceConsume> rows;

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

	public List<FinanceConsume> getRows() {
		return rows;
	}

	public void setRows(List<FinanceConsume> rows) {
		this.rows = rows;
	}

	public static FinanceConsumeDto financeConsumeDto(Page<FinanceConsume> page) {
		FinanceConsumeDto dto = new FinanceConsumeDto();
		if (null != page && null != page.getResult() && page.getResult().size() > 0) {
			dto.setRows(page.getResult());
			dto.setPageNum(page.getPageNum());
			dto.setPageSize(page.getPageSize());
			dto.setTotal(page.getTotal());
		}
		return dto;
	}
}
