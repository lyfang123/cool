package com.uwaytech.downloadLog.ctrl.dto;

import java.util.Date;

/**
 * ResourceDownload
 *
 * @author lyfang
 * @date 2016/6/6
 */
public class ResourceDownload {

	private String orderCode;

	private Long resouseId;

	private String userName;

	private String userDept;

	private Long userId;

	private String resourceName;

	private Long eCoin;

	private Date dealTime;

	private String schoolName;

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Long getResouseId() {
		return resouseId;
	}

	public void setResouseId(Long resouseId) {
		this.resouseId = resouseId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserDept() {
		return userDept;
	}

	public void setUserDept(String userDept) {
		this.userDept = userDept;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public Long geteCoin() {
		return eCoin;
	}

	public void seteCoin(Long eCoin) {
		this.eCoin = eCoin;
	}

	public Date getDealTime() {
		return dealTime;
	}

	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
}
