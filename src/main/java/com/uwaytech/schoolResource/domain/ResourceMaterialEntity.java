package com.uwaytech.schoolResource.domain;

import com.uwaytech.schoolCourse.domain.MaterialEntity;

import java.util.List;

/**
 * Created by zeng on 2016/7/6.
 */
public class ResourceMaterialEntity {
	private String chapter;
	private List<MaterialEntity> materials;

	public String getChapter() {
		return chapter;
	}

	public void setChapter(String chapter) {
		this.chapter = chapter;
	}

	public List<MaterialEntity> getMaterials() {
		return materials;
	}

	public void setMaterials(List<MaterialEntity> materials) {
		this.materials = materials;
	}
}
