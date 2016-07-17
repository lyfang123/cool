package com.uwaytech.downloadLog.domain;

import java.io.Serializable;
import java.util.Date;

public class DownloadLog implements Serializable {
    private Long id;

    private Long grouping;

    private Long userId;

    private String userName;

    private String userDept;

    private Long resouseId;

    private Long financeLogId;

    private Date createTime;

    private String schoolName;

    private Long eCoin;

    private String resourceName;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserDept() {
        return userDept;
    }

    public void setUserDept(String userDept) {
        this.userDept = userDept == null ? null : userDept.trim();
    }

    public Long getResouseId() {
        return resouseId;
    }

    public void setResouseId(Long resouseId) {
        this.resouseId = resouseId;
    }

    public Long getFinanceLogId() {
        return financeLogId;
    }

    public void setFinanceLogId(Long financeLogId) {
        this.financeLogId = financeLogId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName == null ? null : schoolName.trim();
    }

    public Long geteCoin() {
        return eCoin;
    }

    public void seteCoin(Long eCoin) {
        this.eCoin = eCoin;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
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
        DownloadLog other = (DownloadLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getGrouping() == null ? other.getGrouping() == null : this.getGrouping().equals(other.getGrouping()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getUserDept() == null ? other.getUserDept() == null : this.getUserDept().equals(other.getUserDept()))
            && (this.getResouseId() == null ? other.getResouseId() == null : this.getResouseId().equals(other.getResouseId()))
            && (this.getFinanceLogId() == null ? other.getFinanceLogId() == null : this.getFinanceLogId().equals(other.getFinanceLogId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getSchoolName() == null ? other.getSchoolName() == null : this.getSchoolName().equals(other.getSchoolName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getGrouping() == null) ? 0 : getGrouping().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getUserDept() == null) ? 0 : getUserDept().hashCode());
        result = prime * result + ((getResouseId() == null) ? 0 : getResouseId().hashCode());
        result = prime * result + ((getFinanceLogId() == null) ? 0 : getFinanceLogId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getSchoolName() == null) ? 0 : getSchoolName().hashCode());
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
        sb.append(", userId=").append(userId);
        sb.append(", userName=").append(userName);
        sb.append(", userDept=").append(userDept);
        sb.append(", resouseId=").append(resouseId);
        sb.append(", financeLogId=").append(financeLogId);
        sb.append(", createTime=").append(createTime);
        sb.append(", schoolName=").append(schoolName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}