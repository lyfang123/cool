package com.uwaytech.financeLog.domain;

import java.util.Date;

/**
 * FinanceLogParam
 *
 * @author lyfang
 * @date 2016/6/7
 */
public class FinanceLogParam {
    private Long schoolId;

    private Date startTime;

    private Date endTime;

    private Byte type;

    private Byte payType;

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getPayType() {
        return payType;
    }

    public void setPayType(Byte payType) {
        this.payType = payType;
    }
}
