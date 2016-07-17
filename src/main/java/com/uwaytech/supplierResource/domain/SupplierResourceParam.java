package com.uwaytech.supplierResource.domain;

/**
 * SupplierResourceParam
 *
 * @author lyfang
 * @date 2016/6/16
 */
public class SupplierResourceParam {
	private String name;

	private Long categoryId;

	private Integer type;

	private Integer schoolId;

	private Long mediaType;

	private Long useType;

	private Integer resType;

	private Byte checkStatus;

	private Byte status;

	private Integer level;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public Long getMediaType() {
		return mediaType;
	}

	public void setMediaType(Long mediaType) {
		this.mediaType = mediaType;
	}

	public Long getUseType() {
		return useType;
	}

	public void setUseType(Long useType) {
		this.useType = useType;
	}

	public Integer getResType() {
		return resType;
	}

	public void setResType(Integer resType) {
		this.resType = resType;
	}

	public Byte getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Byte checkStatus) {
		this.checkStatus = checkStatus;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
}
