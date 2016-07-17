package com.uwaytech.financeLog.ctrl.dto;

import com.github.pagehelper.Page;
import com.uwaytech.financeLog.domain.FinanceLog;
import com.uwaytech.cool.common.enums.PayTypeEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * FinanceLogDto
 *
 * @author lyfang
 * @date 2016/6/7
 */
public class FinanceLogDto {

	private long total;

	private Integer pageNum;

	private Integer pageSize;

	private Long eAmount;

	private List<FinanceRechargeLog> rows;

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

	public Long geteAmount() {
		return eAmount;
	}

	public void seteAmount(Long eAmount) {
		this.eAmount = eAmount;
	}

	public List<FinanceRechargeLog> getRows() {
		return rows;
	}

	public void setRows(List<FinanceRechargeLog> rows) {
		this.rows = rows;
	}

	public static FinanceLogDto financeLogDto(Page<FinanceLog> page, Long ecoins) {
		FinanceLogDto dto = new FinanceLogDto();
		if (null != page) {
			dto.setTotal(new Long(page.getTotal()));
			dto.setPageSize(page.getPageSize());
			dto.setPageNum(page.getPageNum());
			dto.seteAmount(ecoins);
			if (null != page.getResult() && page.getResult().size() > 0) {
				List<FinanceRechargeLog> list = new ArrayList<FinanceRechargeLog>();
				for (FinanceLog financeLog : page.getResult()) {
					FinanceRechargeLog finance = new FinanceRechargeLog();
					finance.setDealDate(financeLog.getCreateTime());
					finance.setOrderCode(financeLog.getOrderCode());
					finance.seteCoin(financeLog.geteCoin());
					finance.setBankNumber(financeLog.getBankNumber());
					finance.setMoney(financeLog.getMoney());
					if (null != financeLog.getPayType()) {
						finance.setPayType(PayTypeEnum.valueOf(financeLog.getPayType()).getName());
					}
					list.add(finance);
				}
				dto.setRows(list);
			}
		}
		return dto;
	}
}
