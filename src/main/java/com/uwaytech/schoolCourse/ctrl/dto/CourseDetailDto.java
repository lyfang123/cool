package com.uwaytech.schoolCourse.ctrl.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.uwaytech.schoolCourse.domain.*;
import com.uwaytech.schoolResource.ctrl.dto.ChapterInfoDto;
import com.uwaytech.schoolResource.ctrl.dto.MaterialEntityDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zeng on 2016/6/16.
 */
public class CourseDetailDto {
	private String id;
	private String name;
	private List<CourseCategory> category;
	private String imgUrl;
	@JSONField(format = "yyyy-MM-dd")
	private Date endTime;
	private Integer type;
	private String description;
	private List<ChapterInfoDto> chapter;
	private List<ChapterInfoDto> resource;

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ChapterInfoDto> getChapter() {
		return chapter;
	}

	public void setChapter(List<ChapterInfoDto> chapter) {
		this.chapter = chapter;
	}

	public List<ChapterInfoDto> getResource() {
		return resource;
	}

	public void setResource(List<ChapterInfoDto> resource) {
		this.resource = resource;
	}

	public static CourseDetailDto toCourseDetailDto(CourseInfo courseInfo) {
		CourseDetailDto dto = new CourseDetailDto();
		dto.setId(courseInfo.getId().toString());
		dto.setName(courseInfo.getName());
		dto.setCategory(courseInfo.getCategory());
		dto.setImgUrl(courseInfo.getImgUrl());
		dto.setEndTime(courseInfo.getEndTime());
		dto.setType(courseInfo.getType());
		dto.setDescription(courseInfo.getDescription());
		List<MaterialEntity> materials = courseInfo.getMaterial();
		List<MaterialEntityDto> materialEntityDtos = MaterialEntityDto.toDto(materials);
		List<ChapterInfo> chapters = courseInfo.getChapter();
		List<ChapterInfo> resource = courseInfo.getResource();
		List<ChapterInfoDto> chapterDto = new ArrayList<>();
		List<ChapterInfoDto> resourceDto = new ArrayList<>();
		for (ChapterInfo chapterInfo : chapters) {
			ChapterInfoDto chapterInfoDto = new ChapterInfoDto();
			List<MaterialEntityDto> chapterMaterial = new ArrayList<>();
			chapterInfoDto.setChapter(chapterInfo.getChapter());
			for (MaterialEntityDto material : materialEntityDtos) {
				if (material.getChapter().equalsIgnoreCase(chapterInfo.getChapter())) {
					chapterMaterial.add(material);
				}
				chapterInfoDto.setMaterials(chapterMaterial);
			}
			chapterDto.add(chapterInfoDto);
		}
		for (ChapterInfo chapterInfo : resource) {
			ChapterInfoDto resDto = new ChapterInfoDto();
			List<MaterialEntityDto> chapterMaterial = new ArrayList<>();
			resDto.setChapter(chapterInfo.getChapter());
			for (MaterialEntityDto material : materialEntityDtos) {
				if (material.getChapter().equalsIgnoreCase(chapterInfo.getChapter())) {
					chapterMaterial.add(material);
				}
				resDto.setMaterials(chapterMaterial);
			}
			resourceDto.add(resDto);
		}
		dto.setChapter(chapterDto);
		dto.setResource(resourceDto);
		return dto;
	}
}
