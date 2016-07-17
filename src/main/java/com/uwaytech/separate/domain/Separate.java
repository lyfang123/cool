package com.uwaytech.separate.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Separate implements Serializable {
    private Long id;

    private Long supplierId;

    private Date separateMonth;

    private Integer downloadCount;

    private BigDecimal rmbAmount;

    private Long eEarningAmount;

    private Double proportion;

    private Long eCoin;

    private Double eExchange;

    private BigDecimal rmbEarning;

    private Date createTime;

    private String name;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Date getSeparateMonth() {
        return separateMonth;
    }

    public void setSeparateMonth(Date separateMonth) {
        this.separateMonth = separateMonth;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Separate other = (Separate) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSupplierId() == null ? other.getSupplierId() == null : this.getSupplierId().equals(other.getSupplierId()))
            && (this.getSeparateMonth() == null ? other.getSeparateMonth() == null : this.getSeparateMonth().equals(other.getSeparateMonth()))
            && (this.getDownloadCount() == null ? other.getDownloadCount() == null : this.getDownloadCount().equals(other.getDownloadCount()))
            && (this.getRmbAmount() == null ? other.getRmbAmount() == null : this.getRmbAmount().equals(other.getRmbAmount()))
            && (this.geteEarningAmount() == null ? other.geteEarningAmount() == null : this.geteEarningAmount().equals(other.geteEarningAmount()))
            && (this.getProportion() == null ? other.getProportion() == null : this.getProportion().equals(other.getProportion()))
            && (this.geteCoin() == null ? other.geteCoin() == null : this.geteCoin().equals(other.geteCoin()))
            && (this.geteExchange() == null ? other.geteExchange() == null : this.geteExchange().equals(other.geteExchange()))
            && (this.getRmbEarning() == null ? other.getRmbEarning() == null : this.getRmbEarning().equals(other.getRmbEarning()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSupplierId() == null) ? 0 : getSupplierId().hashCode());
        result = prime * result + ((getSeparateMonth() == null) ? 0 : getSeparateMonth().hashCode());
        result = prime * result + ((getDownloadCount() == null) ? 0 : getDownloadCount().hashCode());
        result = prime * result + ((getRmbAmount() == null) ? 0 : getRmbAmount().hashCode());
        result = prime * result + ((geteEarningAmount() == null) ? 0 : geteEarningAmount().hashCode());
        result = prime * result + ((getProportion() == null) ? 0 : getProportion().hashCode());
        result = prime * result + ((geteCoin() == null) ? 0 : geteCoin().hashCode());
        result = prime * result + ((geteExchange() == null) ? 0 : geteExchange().hashCode());
        result = prime * result + ((getRmbEarning() == null) ? 0 : getRmbEarning().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", supplierId=").append(supplierId);
        sb.append(", separateMonth=").append(separateMonth);
        sb.append(", downloadCount=").append(downloadCount);
        sb.append(", rmbAmount=").append(rmbAmount);
        sb.append(", eEarningAmount=").append(eEarningAmount);
        sb.append(", proportion=").append(proportion);
        sb.append(", eCoin=").append(eCoin);
        sb.append(", eExchange=").append(eExchange);
        sb.append(", rmbEarning=").append(rmbEarning);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}