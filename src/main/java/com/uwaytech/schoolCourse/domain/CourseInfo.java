package com.uwaytech.schoolCourse.domain;

import com.uwaytech.common.exception.ParamMissException;
import com.uwaytech.supplierCourse.domain.SupplierCourse;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * Created by zeng on 2016/6/6.
 */
public class CourseInfo {
    private Long id;
    private String name;
    private List<CourseCategory> category;
    private String imgUrl;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
    private Integer type;
    private String description;
    private Integer autoType;
    private List<MaterialEntity> material;
    private List<ChapterInfo> chapter;
    private List<ChapterInfo> resource;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CourseCategory> getCategory() {
        return category;
    }

    public void setCategory(List<CourseCategory> category) {
        this.category = category;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAutoType() {
        return autoType;
    }

    public void setAutoType(Integer autoType) {
        this.autoType = autoType;
    }

    public List<MaterialEntity> getMaterial() {
        return material;
    }

    public void setMaterial(List<MaterialEntity> material) {
        this.material = material;
    }

    public List<ChapterInfo> getChapter() {
        return chapter;
    }

    public void setChapter(List<ChapterInfo> chapter) {
        this.chapter = chapter;
    }

    public List<ChapterInfo> getResource() {
        return resource;
    }

    public void setResource(List<ChapterInfo> resource) {
        this.resource = resource;
    }

    public static SupplierCourse toSupplierCourse(CourseInfo courseInfo) {
        SupplierCourse supplierCourse = new SupplierCourse();
        supplierCourse.setId(courseInfo.getId());
        supplierCourse.setName(courseInfo.getName());
        supplierCourse.setImgUrl(courseInfo.getImgUrl());
        supplierCourse.setCategoryId(courseInfo.getCategory().get(0).getId());
        supplierCourse.setEndTime(courseInfo.getEndTime());
        supplierCourse.setDescription(courseInfo.getDescription());
        supplierCourse.setAutoType(courseInfo.getAutoType());
        return supplierCourse;
    }

    public static SchoolCourse toSchoolCourse(CourseInfo courseInfo) {
        SchoolCourse schoolCourse = new SchoolCourse();
        schoolCourse.setId(courseInfo.getId());
        schoolCourse.setName(courseInfo.getName());
        if(courseInfo.getCategory().isEmpty()) {
            throw new ParamMissException("课程分类不能为空");
        } else {
            schoolCourse.setCategoryId(courseInfo.getCategory().get(0).getId());
        }
        schoolCourse.setImgUrl(courseInfo.getImgUrl());
        schoolCourse.setType(Long.valueOf(courseInfo.getType()));
        schoolCourse.setDescription(courseInfo.getDescription());
        schoolCourse.setEndTime(courseInfo.getEndTime());
        return schoolCourse;
    }
}
