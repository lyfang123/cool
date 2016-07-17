package com.uwaytech.material.domain;

import java.io.Serializable;
import java.util.Date;

public class Material implements Serializable {
    private Long id;

    private Long grouping;

    private String persistId;

    private String hash;

    private String qiniuKey;

    private String name;

    private String mediaType;

    private String size;

    private Long cusCateId;

    private Integer length;

    private String url;

    private String normUrl;

    private String highUrl;

    private Integer type;

    private Integer qiniuStatus;

    private Byte status;

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

    public String getPersistId() {
        return persistId;
    }

    public void setPersistId(String persistId) {
        this.persistId = persistId == null ? null : persistId.trim();
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash == null ? null : hash.trim();
    }

    public String getQiniuKey() {
        return qiniuKey;
    }

    public void setQiniuKey(String qiniuKey) {
        this.qiniuKey = qiniuKey == null ? null : qiniuKey.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType == null ? null : mediaType.trim();
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size == null ? null : size.trim();
    }

    public Long getCusCateId() {
        return cusCateId;
    }

    public void setCusCateId(Long cusCateId) {
        this.cusCateId = cusCateId;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getNormUrl() {
        return normUrl;
    }

    public void setNormUrl(String normUrl) {
        this.normUrl = normUrl == null ? null : normUrl.trim();
    }

    public String getHighUrl() {
        return highUrl;
    }

    public void setHighUrl(String highUrl) {
        this.highUrl = highUrl == null ? null : highUrl.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getQiniuStatus() {
        return qiniuStatus;
    }

    public void setQiniuStatus(Integer qiniuStatus) {
        this.qiniuStatus = qiniuStatus;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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
        Material other = (Material) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getGrouping() == null ? other.getGrouping() == null : this.getGrouping().equals(other.getGrouping()))
            && (this.getPersistId() == null ? other.getPersistId() == null : this.getPersistId().equals(other.getPersistId()))
            && (this.getHash() == null ? other.getHash() == null : this.getHash().equals(other.getHash()))
            && (this.getQiniuKey() == null ? other.getQiniuKey() == null : this.getQiniuKey().equals(other.getQiniuKey()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getMediaType() == null ? other.getMediaType() == null : this.getMediaType().equals(other.getMediaType()))
            && (this.getSize() == null ? other.getSize() == null : this.getSize().equals(other.getSize()))
            && (this.getCusCateId() == null ? other.getCusCateId() == null : this.getCusCateId().equals(other.getCusCateId()))
            && (this.getLength() == null ? other.getLength() == null : this.getLength().equals(other.getLength()))
            && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()))
            && (this.getNormUrl() == null ? other.getNormUrl() == null : this.getNormUrl().equals(other.getNormUrl()))
            && (this.getHighUrl() == null ? other.getHighUrl() == null : this.getHighUrl().equals(other.getHighUrl()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getQiniuStatus() == null ? other.getQiniuStatus() == null : this.getQiniuStatus().equals(other.getQiniuStatus()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getGrouping() == null) ? 0 : getGrouping().hashCode());
        result = prime * result + ((getPersistId() == null) ? 0 : getPersistId().hashCode());
        result = prime * result + ((getHash() == null) ? 0 : getHash().hashCode());
        result = prime * result + ((getQiniuKey() == null) ? 0 : getQiniuKey().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getMediaType() == null) ? 0 : getMediaType().hashCode());
        result = prime * result + ((getSize() == null) ? 0 : getSize().hashCode());
        result = prime * result + ((getCusCateId() == null) ? 0 : getCusCateId().hashCode());
        result = prime * result + ((getLength() == null) ? 0 : getLength().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
        result = prime * result + ((getNormUrl() == null) ? 0 : getNormUrl().hashCode());
        result = prime * result + ((getHighUrl() == null) ? 0 : getHighUrl().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getQiniuStatus() == null) ? 0 : getQiniuStatus().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
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
        sb.append(", persistId=").append(persistId);
        sb.append(", hash=").append(hash);
        sb.append(", qiniuKey=").append(qiniuKey);
        sb.append(", name=").append(name);
        sb.append(", mediaType=").append(mediaType);
        sb.append(", size=").append(size);
        sb.append(", cusCateId=").append(cusCateId);
        sb.append(", length=").append(length);
        sb.append(", url=").append(url);
        sb.append(", normUrl=").append(normUrl);
        sb.append(", highUrl=").append(highUrl);
        sb.append(", type=").append(type);
        sb.append(", qiniuStatus=").append(qiniuStatus);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}