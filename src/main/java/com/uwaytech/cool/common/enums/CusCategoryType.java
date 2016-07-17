package com.uwaytech.cool.common.enums;

/**
 * Created by moyi on 2016-06-08.
 */
public enum CusCategoryType {


    /**
     * 通用类型
     */
    USE_TYPE(1),
    MEDIA_TYPE(2),
    BUY_TYPE(3),
    SCHOOL_TYPE(4);

    private Integer value;

    CusCategoryType(Integer value){
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
