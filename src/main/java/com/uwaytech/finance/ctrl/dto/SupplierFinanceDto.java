package com.uwaytech.finance.ctrl.dto;

import com.github.pagehelper.Page;

import java.util.List;

/**
 * SupplierFinanceDto
 *
 * @author lyfang
 * @date 2016/6/8
 */
public class SupplierFinanceDto {

	private long total;

	private Integer pageNum;

	private Integer pageSize;

	private Long eAmount;

	private List<SupplierFinance> rows;

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

	public List<SupplierFinance> getRows() {
		return rows;
	}

	public void setRows(List<SupplierFinance> rows) {
		this.rows = rows;
	}

	public Long geteAmount() {
		return eAmount;
	}

	public void seteAmount(Long eAmount) {
		this.eAmount = eAmount;
	}

	public static SupplierFinanceDto supplierDownloadDto(Page<SupplierFinance> page, Long ecoins) {
		SupplierFinanceDto dto = new SupplierFinanceDto();
		if (null != page && null != page.getResult() && page.getResult().size() > 0) {
			dto.setRows(page.getResult());
			dto.setPageNum(page.getPageNum());
			dto.setPageSize(page.getPageSize());
			dto.setTotal(page.getTotal());
			dto.seteAmount(ecoins);
		}
		return dto;
	}
}
