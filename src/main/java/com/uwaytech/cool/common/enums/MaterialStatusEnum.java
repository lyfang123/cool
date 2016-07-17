package com.uwaytech.cool.common.enums;

import com.uwaytech.common.enums.EnumInterface;

/**
 * Created by zeng on 2016/6/16.
 */
public enum MaterialStatusEnum implements EnumInterface {
	PUBLISH(2,"已发布"),
	NO_PUBLISH(1,"未发布");

	private Integer id;
	private String name;

	private MaterialStatusEnum(Integer id, String name){
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String value) {
		this.name = value;
	}

	public static MaterialStatusEnum valueOf(Integer type) {
		for (MaterialStatusEnum e : MaterialStatusEnum.values()) {
			if (e.getId() == type) {
				return e;
			}
		}
		return null;
	}
}
