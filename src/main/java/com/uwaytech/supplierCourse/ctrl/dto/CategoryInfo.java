package com.uwaytech.supplierCourse.ctrl.dto;

/**
 * CategoryInfo
 *
 * @author lyfang
 * @date 2016/6/15
 */
public class CategoryInfo {

	private String categoryId;

	private String categoryName;

	private Long counts;

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Long getCounts() {
		return counts;
	}

	public void setCounts(Long counts) {
		this.counts = counts;
	}

	@Override
	public String toString() {
		return "CategoryInfo{" +
				"categoryId='" + categoryId + '\'' +
				", categoryName='" + categoryName + '\'' +
				", counts=" + counts +
				'}';
	}
}
