package com.uwaytech.eCoinMoney.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class ECoinMoney implements Serializable {
    private Long id;

    private Long eCoin;

    private BigDecimal rmb;

    private String description;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        this.description = description == null ? null : description.trim();
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
        ECoinMoney other = (ECoinMoney) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.geteCoin() == null ? other.geteCoin() == null : this.geteCoin().equals(other.geteCoin()))
            && (this.getRmb() == null ? other.getRmb() == null : this.getRmb().equals(other.getRmb()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((geteCoin() == null) ? 0 : geteCoin().hashCode());
        result = prime * result + ((getRmb() == null) ? 0 : getRmb().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", eCoin=").append(eCoin);
        sb.append(", rmb=").append(rmb);
        sb.append(", description=").append(description);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}