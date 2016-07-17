package com.uwaytech.cool.common.enums;

import com.uwaytech.common.enums.EnumInterface;

/**
 * PayTypeEnum
 *
 * @author lyfang
 * @date 2016/6/7
 */
public enum FinanceTypeEnum implements EnumInterface {
    SCHOOL_ACCOUNTS(1,"学校账号"),
    SUPPLIER_ACCOUNTS(2,"供应商账号");

    private Integer id;

    private String name;

    private FinanceTypeEnum(Integer id, String name){
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

    public static FinanceTypeEnum valueOf(Integer type) {
        for (FinanceTypeEnum e : FinanceTypeEnum.values()) {
            if (e.getId() == type) {
                return e;
            }
        }
        return null;
    }
}
