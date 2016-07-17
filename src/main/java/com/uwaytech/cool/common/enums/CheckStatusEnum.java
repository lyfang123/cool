package com.uwaytech.cool.common.enums;

/**
 * Created by zeng on 2016/6/7.
 */
public enum CheckStatusEnum {

    /**
     * 未审核
     */
    NOT_REVIEW((byte)1),
    /**
     * 通过
     */
    PASS((byte)2),
    /**
     * 不通过
     */
    REFUSE((byte)3);

    private byte value;

    CheckStatusEnum(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }

    public void setValue(byte value) {
        this.value = value;
    }
}
