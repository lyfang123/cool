package com.uwaytech.material.ctrl.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.uwaytech.cool.common.enums.MaterialStatusEnum;
import com.uwaytech.material.domain.Material;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zeng on 2016/6/13.
 */
public class MaterialDto {

	private String materialId;
	private String name;
	private String status;
	@JSONField(format = "yyyy-MM-dd")
	private Date createTime;
	private Integer type;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public static List<MaterialDto> toMaterialDto(List<Material> result) {
		List<MaterialDto> list = new ArrayList<>();
		for (Material material : result) {
			MaterialDto materialDto = new MaterialDto();
			materialDto.setMaterialId(material.getId().toString());
			materialDto.setName(material.getName());
			materialDto.setType(material.getType());
			if (Integer.valueOf(material.getStatus()) == MaterialStatusEnum.NO_PUBLISH.getId()) {
				materialDto.setStatus(MaterialStatusEnum.NO_PUBLISH.getName());
			} else if (Integer.valueOf(material.getStatus()) == MaterialStatusEnum.PUBLISH.getId()) {
				materialDto.setStatus(MaterialStatusEnum.PUBLISH.getName());
			}
			materialDto.setCreateTime(material.getCreateTime());
			list.add(materialDto);
		}
		return list;
	}
}
