package com.uwaytech.cool.common.enums;

/**
 * Created by moyi on 2016-06-22.
 */
public enum FileTypeEnum {

    VIDEO_TYPE(1, "视频"),
    IMAGE_TYPE(2, "图片"),
    DOCUMENT_TYPE(3, "文档"),
    NONSUPPORT_TYPE(99,"暂时不支持");

    private String message;
    private Integer type;

    FileTypeEnum(int type, String message) {
        this.type = type;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
