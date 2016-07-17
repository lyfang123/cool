package com.uwaytech.cool.common.enums;

/**
 * Created by zeng on 2016/7/14.
 */
public enum RecommendEnum {
	RECOMMEND((byte )2),
	NOT_RECOMMEND((byte)1);

	private byte value;

	RecommendEnum(byte value) {
		this.value = value;
	}

	public byte getValue() {
		return value;
	}

	public void setValue(byte value) {
		this.value = value;
	}
}
