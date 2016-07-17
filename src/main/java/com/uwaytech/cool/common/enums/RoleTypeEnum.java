package com.uwaytech.cool.common.enums;

/**
 * UserTypeEnum
 *
 * @author lyfang
 * @date 2016/6/16
 */
public enum RoleTypeEnum {

	/**
	 * 供应商资源
	 */
	SUPPLIER(1),
	/**
	 * 学校资源
	 */
	SCHOOL(2);

	private Integer value;

	RoleTypeEnum(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

}
