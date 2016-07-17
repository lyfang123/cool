package com.uwaytech.cool.common.enums;

/**
 * UserTypeEnum
 *
 * @author lyfang
 * @date 2016/6/16
 */
public enum UserRoleTypeEnum {
	/**
	 * 学生
	 */
	STUDENT(1),
	/**
	 * 老师
	 */
	TEACHER(2),
	/**
	 * 供应商
	 */
	SUPPLIER(3),
	/**
	 * 管理员
	 */
	PLATFORM_ADMIN(4);

	private Integer value;

	UserRoleTypeEnum(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

}
