package com.uwaytech.cool.common.enums;

/**
 * Created by moyi on 2016-06-07.
 */
public enum  FinanceType {


    /**
     * 学校帐户类型
     */
    SchoolType(1),

    /**
     * 供应商帐户类型
     */
    SupplierType(2)

    ;

    private Integer value;

    FinanceType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
