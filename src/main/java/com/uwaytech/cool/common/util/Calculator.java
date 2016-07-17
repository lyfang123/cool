package com.uwaytech.cool.common.util;

import com.uwaytech.cool.common.constant.Constant;
import com.uwaytech.cool.common.enums.RoleTypeEnum;

/**
 * Calculator
 *
 * @author lyfang
 * @date 2016/6/16
 */
public class Calculator {
	private Long primaryVal;
	private RoleTypeEnum type;

	/**
	 * 根据一个值构造Calculator
	 *
	 * @param val 一个被组装的值
	 */
	public Calculator(Long val) {
		if (val % 2 == 0) //偶数
		{
			this.primaryVal = val / 2;
			this.type = RoleTypeEnum.SCHOOL; //学校资源
		} else {
			this.primaryVal = (val - 1) / 2; //奇数
			this.type = RoleTypeEnum.SUPPLIER; //供应商资源
		}
	}

	/**
	 * 把id和类型封装成一个数值
	 *
	 * @param val  课程或资源id
	 * @param type 资源或课程类型
	 * @return 封装后的值
	 * @author qiuyunbo
	 */
	public static Long getCalcValue(Long val, Integer type) {
		switch (type) {
			case Constant.SCHOOL_CODE:
				return new Long(val * 2);
			case Constant.SUPPLIER_CODE:
				return new Long(val * 2 + 1);
			default:
				throw new RuntimeException("type is not allowed");
		}
	}

	/**
	 * 资源或课程id
	 *
	 * @return
	 */
	public Long getPrimaryVal() {
		return primaryVal;
	}

	public void setPrimaryVal(Long primaryVal) {
		this.primaryVal = primaryVal;
	}

	public RoleTypeEnum getType() {
		return type;
	}

	public void setType(RoleTypeEnum type) {
		this.type = type;
	}
}