package com.uwaytech.schoolResource.ctrl.dto;

import com.uwaytech.schoolResource.domain.ResourceInfo;

import java.util.List;

/**
 * Created by zeng on 2016/7/6.
 */
public class ResourceListDto {
	private Long total;
	private Integer pageNum;
	private Integer pageSize;
	private List<ResourceInfoDto> rows;
	private Integer number;
	private Long eCoin;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List<ResourceInfoDto> getRows() {
		return rows;
	}

	public void setRows(List<ResourceInfoDto> rows) {
		this.rows = rows;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Long geteCoin() {
		return eCoin;
	}

	public void seteCoin(Long eCoin) {
		this.eCoin = eCoin;
	}

	public static ResourceListDto toResourceListDto(ResourceInfo resourceInfo) {
		ResourceListDto dto = new ResourceListDto();
		dto.setTotal(resourceInfo.getTotal());
		dto.setPageSize(resourceInfo.getPageSize());
		dto.setPageNum(resourceInfo.getPageNum());
		dto.seteCoin(resourceInfo.geteCoin());
		dto.setNumber(resourceInfo.getNumber());
		List<ResourceInfoDto> list = ResourceInfoDto.toDto(resourceInfo.getRows());
		dto.setRows(list);
		return dto;
	}
}
