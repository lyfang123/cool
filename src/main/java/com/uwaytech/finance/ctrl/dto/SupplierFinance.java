package com.uwaytech.finance.ctrl.dto;

/**
 * SupplierFinance
 * 供应商下载明细
 *
 * @author lyfang
 * @date 2016/6/8
 */
public class SupplierFinance {
	private String name;

	private Long downloadCount;

	private Long eCoin;

	private Long eCoins;

	public Long geteCoins() {
		return eCoins;
	}

	public void seteCoins(Long eCoins) {
		this.eCoins = eCoins;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getDownloadCount() {
		return downloadCount;
	}

	public void setDownloadCount(Long downloadCount) {
		this.downloadCount = downloadCount;
	}

	public Long geteCoin() {
		return eCoin;
	}

	public void seteCoin(Long eCoin) {
		this.eCoin = eCoin;
	}

	@Override
	public String toString() {
		return "SupplierFinance{" +
				"name='" + name + '\'' +
				", downloadCount=" + downloadCount +
				", eCoin=" + eCoin +
				", eCoins=" + eCoins +
				'}';
	}
}
