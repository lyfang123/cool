package com.uwaytech.schoolResource.ctrl.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.uwaytech.schoolCourse.domain.ChapterInfo;
import com.uwaytech.schoolCourse.domain.CourseCategory;
import com.uwaytech.schoolCourse.domain.MaterialEntity;
import com.uwaytech.schoolResource.domain.SupplierResourceDetail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zeng on 2016/7/7.
 */
public class ResourceDetailDto {
	private String id;
	private String name;
	private String keyword;
	private List<CourseCategory> category;
	private String imgUrl;
	private String description;
	private List<CourseCategory> mediaType;
	private List<CourseCategory> useType;
	private Long eCoin;
	@JSONField(format = "yyyy-MM-dd")
	private Date createTime;
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

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<ChapterInfoDto> getResource() {
		return resource;
	}

	public void setResource(List<ChapterInfoDto> resource) {
		this.resource = resource;
	}

	public static ResourceDetailDto toDetailDto(SupplierResourceDetail resourceDetail) {
		ResourceDetailDto dto = new ResourceDetailDto();
		dto.setId(resourceDetail.getResourceId().toString());
		dto.setName(resourceDetail.getName());
		dto.setCategory(resourceDetail.getCategory());
		dto.setKeyword(resourceDetail.getKeyword());
		dto.setMediaType(resourceDetail.getMediaType());
		dto.setUseType(resourceDetail.getUseType());
		dto.setCreateTime(resourceDetail.getCreateTime());
		dto.setDescription(resourceDetail.getDescription());
		dto.seteCoin(resourceDetail.geteCoin());
		dto.setImgUrl(resourceDetail.getImgUrl());
		List<MaterialEntity> list = resourceDetail.getMaterial();
		List<MaterialEntityDto> materialEntityDtos = MaterialEntityDto.toDto(list);
		ChapterInfoDto chapterInfoDto = new ChapterInfoDto();
		chapterInfoDto.setMaterials(materialEntityDtos);
		List<ChapterInfoDto> chapterInfoList = new ArrayList<>();
		chapterInfoList.add(chapterInfoDto);
		dto.setResource(chapterInfoList);
		return dto;
	}
}
