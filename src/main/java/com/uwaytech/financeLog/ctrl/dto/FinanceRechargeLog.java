package com.uwaytech.financeLog.ctrl.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * FinanceRechargeLog
 *
 * @author lyfang
 * @date 2016/6/7
 */
public class FinanceRechargeLog {
	private String orderCode;

	private String bankNumber;

	private String payType;

	private BigDecimal money;

	private Long eCoin;

	private Date dealDate;

	public Date getDealDate() {
		return dealDate;
	}

	public void setDealDate(Date dealDate) {
		this.dealDate = dealDate;
	}

	public Long geteCoin() {
		return eCoin;
	}

	public void seteCoin(Long eCoin) {
		this.eCoin = eCoin;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getBankNumber() {
		return bankNumber;
	}

	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
}
