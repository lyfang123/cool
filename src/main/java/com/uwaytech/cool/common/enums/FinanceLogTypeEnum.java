package com.uwaytech.cool.common.enums;

import com.uwaytech.common.enums.EnumInterface;

/**
 * PayTypeEnum
 *
 * @author lyfang
 * @date 2016/6/7
 */
public enum FinanceLogTypeEnum implements EnumInterface {
    RECHARGE(1,"充值"),
    CONSUME(2,"消费"),
    INCOME(3,"收入"),
    MINUTE(4,"分账"),
    TRANSFER_ACCOUNT(5,"转账");

    private Integer id;

    private String name;

    private FinanceLogTypeEnum(Integer id, String name){
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

    public static FinanceLogTypeEnum valueOf(Integer type) {
        for (FinanceLogTypeEnum e : FinanceLogTypeEnum.values()) {
            if (e.getId() == type) {
                return e;
            }
        }
        return null;
    }
}
