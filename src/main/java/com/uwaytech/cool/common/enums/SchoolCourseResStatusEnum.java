package com.uwaytech.cool.common.enums;

/**
 * SchoolCourseResStatusEnum
 *
 * @author lyfang
 * @date 2016/6/17
 */
public enum SchoolCourseResStatusEnum {

	/**
	 * 正常资源
	 */
	VALID((byte) 1),

	/**
	 * 无效资源
	 */
	INVALID((byte) -1);

	private byte value;

	SchoolCourseResStatusEnum(byte value) {
		this.value = value;
	}

	public byte getValue() {
		return value;
	}

	public void setValue(byte value) {
		this.value = value;
	}
}
