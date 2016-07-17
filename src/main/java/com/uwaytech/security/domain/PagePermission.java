package com.uwaytech.security.domain;

import java.io.Serializable;

public class PagePermission implements Serializable {
    private Long pageResourceId;

    private Long roleId;

    private static final long serialVersionUID = 1L;

    public Long getPageResourceId() {
        return pageResourceId;
    }

    public void setPageResourceId(Long pageResourceId) {
        this.pageResourceId = pageResourceId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
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
        PagePermission other = (PagePermission) that;
        return (this.getPageResourceId() == null ? other.getPageResourceId() == null : this.getPageResourceId().equals(other.getPageResourceId()))
            && (this.getRoleId() == null ? other.getRoleId() == null : this.getRoleId().equals(other.getRoleId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPageResourceId() == null) ? 0 : getPageResourceId().hashCode());
        result = prime * result + ((getRoleId() == null) ? 0 : getRoleId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pageResourceId=").append(pageResourceId);
        sb.append(", roleId=").append(roleId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}