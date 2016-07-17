package com.uwaytech.finance.ctrl.dto;

/**
 * FinanceConsume
 *
 * @author lyfang
 * @date 2016/6/8
 */
public class FinanceConsume {
	private String date;

	private Long eCoin;

	private Long downloadCount;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Long geteCoin() {
		return eCoin;
	}

	public void seteCoin(Long eCoin) {
		this.eCoin = eCoin;
	}

	public Long getDownloadCount() {
		return downloadCount;
	}

	public void setDownloadCount(Long downloadCount) {
		this.downloadCount = downloadCount;
	}

	@Override
	public String toString() {
		return "FinanceConsume{" +
				"date='" + date + '\'' +
				", eCoin=" + eCoin +
				", downloadCount=" + downloadCount +
				'}';
	}
}
