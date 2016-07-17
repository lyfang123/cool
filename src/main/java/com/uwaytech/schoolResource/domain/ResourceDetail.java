package com.uwaytech.schoolResource.domain;

import com.uwaytech.common.exception.ParamMissException;
import com.uwaytech.schoolCourse.domain.ChapterInfo;
import com.uwaytech.schoolCourse.domain.CourseCategory;
import com.uwaytech.supplierResource.domain.SupplierResource;

import java.util.List;

/**
 * Created by zeng on 2016/7/1.
 */
public class ResourceDetail {
	private Long id;
	private String name;
	private List<CourseCategory> category;
	private List<CourseCategory> mediaType;
	private List<CourseCategory> useType;
	private Long eCoin;
	private String imgUrl;
	private String description;
	private String keyword;
	private Integer autoType;
	private List<ChapterInfo> resource;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CourseCategory> getCategory() {
		return category;
	}

	public void setCategory(List<CourseCategory> category) {
		this.category = category;
	}

	public List<CourseCategory> getMediaType() {
		return mediaType;
	}

	public void setMediaType(List<CourseCategory> mediaType) {
		this.mediaType = mediaType;
	}

	public List<CourseCategory> getUseType() {
		return useType;
	}

	public void setUseType(List<CourseCategory> useType) {
		this.useType = useType;
	}

	public Long geteCoin() {
		return eCoin;
	}

	public void seteCoin(Long eCoin) {
		this.eCoin = eCoin;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getAutoType() {
		return autoType;
	}

	public void setAutoType(Integer autoType) {
		this.autoType = autoType;
	}

	public List<ChapterInfo> getResource() {
		return resource;
	}

	public void setResource(List<ChapterInfo> resource) {
		this.resource = resource;
	}

	public static SupplierResource toSupplierResource(ResourceDetail resource) {
		SupplierResource supplierResource = new SupplierResource();
		supplierResource.setId(resource.getId());
		supplierResource.setName(resource.getName());
		if(null == resource.getCategory()) {
			throw new ParamMissException("课程分类不能为空");
		} else {
			supplierResource.setCategoryId(resource.getCategory().get(0).getId());
		}
		if (null != resource.getMediaType()) {
			supplierResource.setMediaType(resource.getMediaType().get(0).getId());
		}
		if (null != resource.getUseType()) {
			supplierResource.setUseType(resource.getUseType().get(0).getId());
		}
		supplierResource.seteCoin(resource.geteCoin());
		supplierResource.setImgUrl(resource.getImgUrl());
		supplierResource.setDescription(resource.getDescription());
		supplierResource.setKeyword(resource.getKeyword());
		if (null == resource.getResource()) {
			throw new ParamMissException("资源不能为空");
		} else {
			supplierResource.setMaterialId(resource.getResource().get(0).getMaterials().get(0).getMaterialId());
		}
		supplierResource.setAutoType(resource.getAutoType());
		return supplierResource;
	}
}
