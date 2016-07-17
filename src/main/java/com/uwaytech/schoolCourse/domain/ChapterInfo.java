package com.uwaytech.schoolCourse.domain;

import java.util.List;

/**
 * Created by zeng on 2016/6/21.
 */
public class ChapterInfo {
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
