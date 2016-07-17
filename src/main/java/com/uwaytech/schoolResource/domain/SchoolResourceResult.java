package com.uwaytech.schoolResource.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * Created by zeng on 2016/6/23.
 */
public class SchoolResourceResult {
	private Long id;
	private String name;
	private String category;
	private String imgUrl;
	@JSONField(format = "yyyy-MM-dd")
	private Date createTime;

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
