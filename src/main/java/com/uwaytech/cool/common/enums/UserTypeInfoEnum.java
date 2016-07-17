package com.uwaytech.cool.common.enums;

/**
 * Created by lyf on 2016/7/15.
 */

import com.uwaytech.common.enums.EnumInterface;
import com.uwaytech.common.exception.ParamErrorException;
import java.util.HashMap;
import java.util.Map;

public enum UserTypeInfoEnum implements EnumInterface {
    //学生
    STUDENT(Byte.valueOf((byte)1)),
    //老师
    TEACHER(Byte.valueOf((byte)2)),
    //普通用户
    TOURIST(Byte.valueOf((byte)3)),
    //供应商
    SUPPLIER(Byte.valueOf((byte)4)),
    //管理员
    PLATFORM_ADMIN(Byte.valueOf((byte)6)),
    //名师
    SUPPER_TEACHER(Byte.valueOf((byte)7));

    private Byte byteValue;
    private static final Map<Byte, UserTypeInfoEnum> byteToEnum;

    private UserTypeInfoEnum(Byte b) {
        this.byteValue = b;
    }

    public static UserTypeInfoEnum valueOf(Byte b) {
        return checkResult((UserTypeInfoEnum)byteToEnum.get(b), b);
    }

    public static UserTypeInfoEnum valueOf(Integer b) {
        return checkResult((UserTypeInfoEnum)byteToEnum.get(Byte.valueOf(b.byteValue())), Byte.valueOf(b.byteValue()));
    }

    public Byte byteValue() {
        return this.byteValue;
    }

    public Boolean compare(Byte b) {
        return this.byteValue.equals(b)?Boolean.TRUE:Boolean.FALSE;
    }

    private static UserTypeInfoEnum checkResult(UserTypeInfoEnum typeEnum, Byte b) {
        if(null == typeEnum) {
            throw new ParamErrorException(b + "不是UserTypeEnum枚举类型");
        } else {
            return typeEnum;
        }
    }

    static {
        byteToEnum = new HashMap();
        UserTypeInfoEnum[] arr$ = values();
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            UserTypeInfoEnum typeEnum = arr$[i$];
            byteToEnum.put(typeEnum.byteValue(), typeEnum);
        }

    }
}
