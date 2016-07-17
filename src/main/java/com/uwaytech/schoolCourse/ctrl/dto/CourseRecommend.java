package com.uwaytech.schoolCourse.ctrl.dto;

/**
 * Created by lyf on 2016/7/14.
 */
public class CourseRecommend {
    private String cellId;
    private String name;
    private String imgUrl;

    public String getCellId() {
        return cellId;
    }

    public void setCellId(String cellId) {
        this.cellId = cellId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
