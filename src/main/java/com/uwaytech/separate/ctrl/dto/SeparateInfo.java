package com.uwaytech.separate.ctrl.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SeparateInfo implements Serializable {

	private String name;

	private String date;

	private Integer downloadCount;

	private BigDecimal rmbAmount;

	private Long eEarningAmount;

	private Double proportion;

	private Long eCoin;

	private Double eExchange;

	private BigDecimal rmbEarning;

	private static final long serialVersionUID = 1L;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDownloadCount() {
		return downloadCount;
	}

	public void setDownloadCount(Integer downloadCount) {
		this.downloadCount = downloadCount;
	}

	public BigDecimal getRmbAmount() {
		return rmbAmount;
	}

	public void setRmbAmount(BigDecimal rmbAmount) {
		this.rmbAmount = rmbAmount;
	}

	public Long geteEarningAmount() {
		return eEarningAmount;
	}

	public void seteEarningAmount(Long eEarningAmount) {
		this.eEarningAmount = eEarningAmount;
	}

	public Double getProportion() {
		return proportion;
	}

	public void setProportion(Double proportion) {
		this.proportion = proportion;
	}

	public Long geteCoin() {
		return eCoin;
	}

	public void seteCoin(Long eCoin) {
		this.eCoin = eCoin;
	}

	public Double geteExchange() {
		return eExchange;
	}

	public void seteExchange(Double eExchange) {
		this.eExchange = eExchange;
	}

	public BigDecimal getRmbEarning() {
		return rmbEarning;
	}

	public void setRmbEarning(BigDecimal rmbEarning) {
		this.rmbEarning = rmbEarning;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}