package com.uwaytech.schoolResource.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.uwaytech.material.domain.Material;

import java.util.Date;

/**
 * Created by zeng on 2016/6/22.
 */
public class SchoolResourceDetail {
	private Long id;
	private String name;
	private String category;
	private Long categoryId;
	private String imgUrl;
	private String keyword;
	private String description;
	@JSONField(format = "yyyy-MM-dd")
	private Date createTime;
	private Material material;

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

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public static SchoolResourceDetail toSchoolResource(SupplierResourceDetail resourceDetail) {
		SchoolResourceDetail resource = new SchoolResourceDetail();
		resource.setId(resourceDetail.getResourceId());
		resource.setName(resourceDetail.getName());
		resource.setCreateTime(resourceDetail.getCreateTime());
		resource.setDescription(resourceDetail.getDescription());
		resource.setKeyword(resourceDetail.getKeyword());
		resource.setImgUrl(resourceDetail.getImgUrl());
		return resource;
	}
}
