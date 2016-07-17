package com.uwaytech.schoolResource.ctrl.dto;

import com.uwaytech.schoolCourse.domain.MaterialEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zeng on 2016/7/7.
 */
public class MaterialEntityDto {
	private Long dataId;
	private String materialId;
	private String name;
	private String title;
	private Integer type;
	private String chapter;

	public Long getDataId() {
		return dataId;
	}

	public void setDataId(Long dataId) {
		this.dataId = dataId;
	}

	public String getMaterialId() {
		return materialId;
	}

	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getChapter() {
		return chapter;
	}

	public void setChapter(String chapter) {
		this.chapter = chapter;
	}

	public static List<MaterialEntityDto> toDto(List<MaterialEntity> list) {
		List<MaterialEntityDto> dtoList = new ArrayList<>();
		for (MaterialEntity materialEntity : list) {
			MaterialEntityDto dto = new MaterialEntityDto();
			dto.setDataId(materialEntity.getDataId());
			dto.setMaterialId(materialEntity.getMaterialId().toString());
			dto.setName(materialEntity.getName());
			dto.setChapter(materialEntity.getChapter());
			dto.setTitle(materialEntity.getTitle());
			dto.setType(materialEntity.getType());
			dtoList.add(dto);
		}
		return dtoList;
	}
}
