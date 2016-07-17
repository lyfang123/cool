package com.uwaytech.cool.common.enums;

import com.uwaytech.common.enums.EnumInterface;
import com.uwaytech.common.exception.ParamErrorException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zeng on 2016/6/7.
 */
public enum ResTypeEnum implements EnumInterface {

	/**
	 * 供应商资源
	 */
	SUPPLIER_RES((byte) 1),
	/**
	 * 学校资源
	 */
	SCHOOL_RES((byte) 2);

	private Byte value;

	ResTypeEnum(Byte value) {
		this.value = value;
	}

	public byte getValue() {
		return value;
	}

	public void setValue(Byte value) {
		this.value = value;
	}
	private static final Map<Byte, ResTypeEnum> byteToEnum = new HashMap<>();

	public static ResTypeEnum valueOf(Byte b) {
		return checkResult(byteToEnum.get(b));
	}

	public Byte value() {
		return value;
	}

	public Boolean compare(Byte b) {
		if (value.equals(b)) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	static {
		for(ResTypeEnum resTypeEnum: values()) {
			byteToEnum.put(resTypeEnum.value(), resTypeEnum);
		}
	}

	/**
	 * 检查是否生成正确的枚举类型
	 * @param courseDetailTypeEnum
	 * @return
	 */
	private static ResTypeEnum checkResult(ResTypeEnum courseDetailTypeEnum) {
		if (null == courseDetailTypeEnum) {
			throw new ParamErrorException("CarouselPosEnum 枚举类型不对");
		}
		return courseDetailTypeEnum;
	}
}
