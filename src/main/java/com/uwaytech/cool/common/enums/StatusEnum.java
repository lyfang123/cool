package com.uwaytech.cool.common.enums;

/**
 * Created by moyi on 2016-06-07.
 */
public enum StatusEnum {

    /**
     * 有效
     */
    Valid((byte) 1),

    /**
     * 无效
     */
    Invalid((byte)-1)

    ;

    private byte value;

    StatusEnum(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }

    public void setValue(byte value) {
        this.value = value;
    }
}
