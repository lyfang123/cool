package com.uwaytech.cool.common.enums;

import com.uwaytech.common.enums.EnumInterface;

/**
 * Created by zeng on 2016/6/7.
 */
public enum ResStatusEnum  implements EnumInterface {

    /**
     * 下架状态
     */
    OFF_SHELVE((byte)1),
    /**
     * 上架状态
     */
    ON_SHELVE((byte)2),
    /**
     * 自动上架
     */
    AUTO_SHELVE((byte)3),
    /**
     * 删除状态
     */
    DELETE((byte)-1);

    private byte value;

    ResStatusEnum(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }

    public void setValue(byte value) {
        this.value = value;
    }

}
