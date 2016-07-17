package com.uwaytech.eCoinMoney.ctrl.dto;

import com.github.pagehelper.Page;
import com.uwaytech.eCoinMoney.domain.ECoinMoney;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zeng on 2016/7/12.
 */
public class ECoinMoneyDto {
	private long total;

	private Integer pageNum;

	private Integer pageSize;

	private List<ECoinInfoDto> rows;

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

	public List<ECoinInfoDto> getRows() {
		return rows;
	}

	public void setRows(List<ECoinInfoDto> rows) {
		this.rows = rows;
	}

	public static ECoinMoneyDto dto(Page<ECoinMoney> page) {
		ECoinMoneyDto dto = new ECoinMoneyDto();
		dto.setTotal(page.getTotal());
		dto.setPageNum(page.getPageNum());
		dto.setPageSize(page.getPageSize());
		List<ECoinMoney> list = page.getResult();
		List<ECoinInfoDto> dtoList = ECoinMoneyDto.toDto(list);
		dto.setRows(dtoList);
		return dto;
	}

	private static List<ECoinInfoDto> toDto(List<ECoinMoney> list) {
		List<ECoinInfoDto> dtoList = new ArrayList<>();
		for (ECoinMoney eCoin : list) {
			ECoinInfoDto dto = new ECoinInfoDto();
			dto.setRuleId(eCoin.getId());
			dto.seteCoin(eCoin.geteCoin());
			dto.setRmb(eCoin.getRmb());
			dto.setDescription(eCoin.getDescription());
			dtoList.add(dto);
		}
		return dtoList;
	}
}
