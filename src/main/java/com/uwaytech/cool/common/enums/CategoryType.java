package com.uwaytech.cool.common.enums;

/**
 * Created by moyi on 2016-06-08.
 */
public enum CategoryType {


    /**
     * 通用类型
     */
    CommonType(1)

    ;

    private Integer value;

    CategoryType(Integer value){
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
