package com.uwaytech.schoolResource.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * Created by zeng on 2016/7/1.
 */
public class ResourceListResult {
	private Long resourceId;
	private String name;
	private String category;
	private String imgUrl;
	private Long eCoin;
	private Integer downloadNum;
	private Long eCoins;
	@JSONField(format = "yyyy-MM-dd")
	private Date createTime;
	private Integer checkStatus;
	private Integer status;
	private Integer recommend;
	private String reason;

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Long geteCoin() {
		return eCoin;
	}

	public void seteCoin(Long eCoin) {
		this.eCoin = eCoin;
	}

	public Integer getDownloadNum() {
		return downloadNum;
	}

	public void setDownloadNum(Integer downloadNum) {
		this.downloadNum = downloadNum;
	}

	public Long geteCoins() {
		return eCoins;
	}

	public void seteCoins(Long eCoins) {
		this.eCoins = eCoins;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getRecommend() {
		return recommend;
	}

	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}
