package com.uwaytech.cool.common.enums;

import com.uwaytech.common.enums.EnumInterface;
import com.uwaytech.qiniu.bo.QiNiuResourceItem;

/**
 * Created by moyi on 2016-06-17.
 */
public enum QiNiuStatusEnum implements EnumInterface {

    UNTREATED(1, "未处理"),
    PROCESSING(2, "正在处理中"),
    FINISH(3, "处理完成"),
    USELESS(99,"无效数据");

    private Integer status;
    private String name;

    QiNiuStatusEnum(Integer status, String name) {
        this.status = status;
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
