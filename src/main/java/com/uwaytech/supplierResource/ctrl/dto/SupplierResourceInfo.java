package com.uwaytech.supplierResource.ctrl.dto;

/**
 * SupplierResourceInfo
 *
 * @author lyfang
 * @date 2016/6/16
 */
public class SupplierResourceInfo {

	private String resourceId;

	private String resourceName;

	private String resourcePic;

	private String resourceDesc;

	private Integer readNum;

	private Integer downloadNum;

	private Integer collectionNum;

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourcePic() {
		return resourcePic;
	}

	public void setResourcePic(String resourcePic) {
		this.resourcePic = resourcePic;
	}

	public String getResourceDesc() {
		return resourceDesc;
	}

	public void setResourceDesc(String resourceDesc) {
		this.resourceDesc = resourceDesc;
	}

	public Integer getReadNum() {
		return readNum;
	}

	public void setReadNum(Integer readNum) {
		this.readNum = readNum;
	}

	public Integer getDownloadNum() {
		return downloadNum;
	}

	public void setDownloadNum(Integer downloadNum) {
		this.downloadNum = downloadNum;
	}

	public Integer getCollectionNum() {
		return collectionNum;
	}

	public void setCollectionNum(Integer collectionNum) {
		this.collectionNum = collectionNum;
	}

	@Override
	public String toString() {
		return "SupplierResourceInfo{" +
				"resourceId='" + resourceId + '\'' +
				", resourceName='" + resourceName + '\'' +
				", resourcePic='" + resourcePic + '\'' +
				", resourceDesc='" + resourceDesc + '\'' +
				", readNum=" + readNum +
				", downloadNum=" + downloadNum +
				", collectionNum=" + collectionNum +
				'}';
	}
}
