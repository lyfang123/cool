package com.uwaytech.finance.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Finance implements Serializable {
    private Long id;

    private Long grouping;

    private Long eBalance;

    private BigDecimal rmbAmounts;

    private Long eAmounts;

    private Byte status;

    private Integer type;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGrouping() {
        return grouping;
    }

    public void setGrouping(Long grouping) {
        this.grouping = grouping;
    }

    public Long geteBalance() {
        return eBalance;
    }

    public void seteBalance(Long eBalance) {
        this.eBalance = eBalance;
    }

    public BigDecimal getRmbAmounts() {
        return rmbAmounts;
    }

    public void setRmbAmounts(BigDecimal rmbAmounts) {
        this.rmbAmounts = rmbAmounts;
    }

    public Long geteAmounts() {
        return eAmounts;
    }

    public void seteAmounts(Long eAmounts) {
        this.eAmounts = eAmounts;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        Finance other = (Finance) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getGrouping() == null ? other.getGrouping() == null : this.getGrouping().equals(other.getGrouping()))
            && (this.geteBalance() == null ? other.geteBalance() == null : this.geteBalance().equals(other.geteBalance()))
            && (this.getRmbAmounts() == null ? other.getRmbAmounts() == null : this.getRmbAmounts().equals(other.getRmbAmounts()))
            && (this.geteAmounts() == null ? other.geteAmounts() == null : this.geteAmounts().equals(other.geteAmounts()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getGrouping() == null) ? 0 : getGrouping().hashCode());
        result = prime * result + ((geteBalance() == null) ? 0 : geteBalance().hashCode());
        result = prime * result + ((getRmbAmounts() == null) ? 0 : getRmbAmounts().hashCode());
        result = prime * result + ((geteAmounts() == null) ? 0 : geteAmounts().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
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
        sb.append(", grouping=").append(grouping);
        sb.append(", eBalance=").append(eBalance);
        sb.append(", rmbAmounts=").append(rmbAmounts);
        sb.append(", eAmounts=").append(eAmounts);
        sb.append(", status=").append(status);
        sb.append(", type=").append(type);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}