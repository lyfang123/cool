package com.uwaytech.finance.domain;

/**
 * FinanceResult
 *
 * @author lyfang
 * @date 2016/6/7
 */
public class FinanceResult {
    private Long eBalance;

    private Long eAmounts;

    private Long downloadNum;

    public Long geteBalance() {
        return eBalance;
    }

    public void seteBalance(Long eBalance) {
        this.eBalance = eBalance;
    }

    public Long geteAmounts() {
        return eAmounts;
    }

    public void seteAmounts(Long eAmounts) {
        this.eAmounts = eAmounts;
    }

    public Long getDownloadNum() {
        return downloadNum;
    }

    public void setDownloadNum(Long downloadNum) {
        this.downloadNum = downloadNum;
    }

    @Override
    public String toString() {
        return "FinanceResult{" +
                "eBalance=" + eBalance +
                ", eAmounts=" + eAmounts +
                ", downloadNum=" + downloadNum +
                '}';
    }
}
