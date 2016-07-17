package com.uwaytech.eCoinMoney.ctrl.dto;

import java.math.BigDecimal;

/**
 * Created by zeng on 2016/7/14.
 */
public class ECoinInfoDto {
	private Long ruleId;
	private Long eCoin;
	private BigDecimal rmb;
	private String description;

	public Long getRuleId() {
		return ruleId;
	}

	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}

	public Long geteCoin() {
		return eCoin;
	}

	public void seteCoin(Long eCoin) {
		this.eCoin = eCoin;
	}

	public BigDecimal getRmb() {
		return rmb;
	}

	public void setRmb(BigDecimal rmb) {
		this.rmb = rmb;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
