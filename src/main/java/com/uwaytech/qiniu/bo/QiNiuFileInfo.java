package com.uwaytech.qiniu.bo;

import java.io.Serializable;

/**
 * Created by moyi on 2016-06-22.
 */
public class QiNiuFileInfo implements Serializable{
    private Integer fsize;

    private String hash;

    private String mimeType;

    private Long putTime;

    private String error;

    public Integer getFsize() {
        return fsize;
    }

    public void setFsize(Integer fsize) {
        this.fsize = fsize;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public Long getPutTime() {
        return putTime;
    }

    public void setPutTime(Long putTime) {
        this.putTime = putTime;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
