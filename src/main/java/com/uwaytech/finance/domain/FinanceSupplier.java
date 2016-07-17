package com.uwaytech.finance.domain;

/**
 * FinanceResult
 *
 * @author lyfang
 * @date 2016/6/7
 */
public class FinanceSupplier {
    private Long eBalance;

    private Long counts;

    private Long downloadNum;

    public Long geteBalance() {
        return eBalance;
    }

    public void seteBalance(Long eBalance) {
        this.eBalance = eBalance;
    }

    public Long getCounts() {
        return counts;
    }

    public void setCounts(Long counts) {
        this.counts = counts;
    }

    public Long getDownloadNum() {
        return downloadNum;
    }

    public void setDownloadNum(Long downloadNum) {
        this.downloadNum = downloadNum;
    }

    @Override
    public String toString() {
        return "FinanceSupplier{" +
                "eBalance=" + eBalance +
                ", counts=" + counts +
                ", downloadNum=" + downloadNum +
                '}';
    }
}
