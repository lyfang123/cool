package com.uwaytech.finance.ctrl.dto;

import java.math.BigDecimal;

/**
 * SchoolFinance
 *
 * @author lyfang
 * @date 2016/6/6
 */
public class SchoolFinance {

	private Long schoolId;

	private String name;

	private Long eAmounts;

	private BigDecimal rmbAmounts;

	private Long eBalance;

	private Long availEcoin;

	public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long geteAmounts() {
		return eAmounts;
	}

	public void seteAmounts(Long eAmounts) {
		this.eAmounts = eAmounts;
	}

	public BigDecimal getRmbAmounts() {
		return rmbAmounts;
	}

	public void setRmbAmounts(BigDecimal rmbAmounts) {
		this.rmbAmounts = rmbAmounts;
	}

	public Long geteBalance() {
		return eBalance;
	}

	public void seteBalance(Long eBalance) {
		this.eBalance = eBalance;
	}

	public Long getAvailEcoin() {
		return availEcoin;
	}

	public void setAvailEcoin(Long availEcoin) {
		this.availEcoin = availEcoin;
	}

	@Override
	public String toString() {
		return "SchoolFinance{" +
				"schoolId=" + schoolId +
				", name='" + name + '\'' +
				", eAmounts=" + eAmounts +
				", rmbAmounts=" + rmbAmounts +
				", eBalance=" + eBalance +
				", availEcoin=" + availEcoin +
				'}';
	}
}
