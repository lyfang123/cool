package com.uwaytech.schoolResource.ctrl.dto;

import java.util.List;

/**
 * Created by zeng on 2016/7/7.
 */
public class ChapterInfoDto {
	private String chapter;
	private List<MaterialEntityDto> materials;

	public String getChapter() {
		return chapter;
	}

	public void setChapter(String chapter) {
		this.chapter = chapter;
	}

	public List<MaterialEntityDto> getMaterials() {
		return materials;
	}

	public void setMaterials(List<MaterialEntityDto> materials) {
		this.materials = materials;
	}
}
