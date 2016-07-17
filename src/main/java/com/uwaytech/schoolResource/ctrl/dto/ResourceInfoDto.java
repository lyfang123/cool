package com.uwaytech.schoolResource.ctrl.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.uwaytech.schoolResource.domain.ResourceListResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zeng on 2016/7/6.
 */
public class ResourceInfoDto {
	private String resourceId;
	private String name;
	private String category;
	private String imgUrl;
	private Long eCoin;
	@JSONField(format = "yyyy-MM-dd")
	private Date createTime;
	private Integer downloadNum;
	private Long eCoins;
	private Integer checkStatus;
	private Integer status;
	private Integer recommend;
	private String reason;

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	public static List<ResourceInfoDto> toDto(List<ResourceListResult> rows) {
		List<ResourceInfoDto> list = new ArrayList<>();
		for (ResourceListResult detail : rows) {
			ResourceInfoDto dto = new ResourceInfoDto();
			dto.setResourceId(detail.getResourceId().toString());
			dto.setName(detail.getName());
			dto.setCategory(detail.getCategory());
			dto.setImgUrl(detail.getImgUrl());
			dto.seteCoin(detail.geteCoin());
			dto.setStatus(detail.getStatus());
			dto.setCheckStatus(detail.getCheckStatus());
			dto.setCreateTime(detail.getCreateTime());
			dto.setDownloadNum(detail.getDownloadNum());
			dto.seteCoins(detail.geteCoins());
			dto.setReason(detail.getReason());
			dto.setRecommend(detail.getRecommend());
			list.add(dto);
		}
		return list;
	}
}
