package com.uwaytech.cool.common.enums;

import com.uwaytech.common.enums.EnumInterface;

/**
 * PayTypeEnum
 *
 * @author lyfang
 * @date 2016/6/7
 */
public enum PayTypeEnum implements EnumInterface {
    TRANSFER_ACCOUNTS(1,"转账"),
    CASH(2,"现金"),
    ALIPAY(3,"支付宝"),
    WECHAT(4,"微信"),
    OTHER(5,"其它");

    private Integer id;

    private String name;

    private PayTypeEnum(Integer id, String name){
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

    public static PayTypeEnum valueOf(Integer type) {
        for (PayTypeEnum e : PayTypeEnum.values()) {
            if (e.getId() == type) {
                return e;
            }
        }
        return null;
    }
}
