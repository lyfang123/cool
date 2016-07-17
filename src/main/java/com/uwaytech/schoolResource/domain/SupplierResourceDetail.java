package com.uwaytech.schoolResource.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.uwaytech.cool.common.constant.Constant;
import com.uwaytech.schoolCourse.domain.ChapterInfo;
import com.uwaytech.schoolCourse.domain.CourseCategory;
import com.uwaytech.schoolCourse.domain.MaterialEntity;
import com.uwaytech.supplierResource.domain.SupplierResource;

import java.util.Date;
import java.util.List;

/**
 * Created by zeng on 2016/6/6.
 */
public class SupplierResourceDetail {
    private Long resourceId;
    private String name;
    private String keyword;
    private List<CourseCategory> category;
    private String imgUrl;
    private String description;
    private Long eCoin;
    @JSONField(format = "yyyy-MM-dd")
    private Date createTime;
    private Integer downloadNum;
    private Long eCoins;
    private List<ChapterInfo> resource;
    private List<CourseCategory> mediaType;
    private List<CourseCategory> useType;
    private List<MaterialEntity> material;

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long geteCoin() {
        return eCoin;
    }

    public void seteCoin(Long eCoin) {
        this.eCoin = eCoin;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getDownloadNum() {
        return downloadNum;
    }

    public void setDownloadNum(Integer downloadNum) {
        this.downloadNum = downloadNum;
    }

    public Long geteCoins() {
        return eCoins;
    }

    public void seteCoins(Long eCoins) {
        this.eCoins = eCoins;
    }

    public List<ChapterInfo> getResource() {
        return resource;
    }

    public void setResource(List<ChapterInfo> resource) {
        this.resource = resource;
    }

    public List<CourseCategory> getMediaType() {
        return mediaType;
    }

    public void setMediaType(List<CourseCategory> mediaType) {
        this.mediaType = mediaType;
    }

    public List<CourseCategory> getUseType() {
        return useType;
    }

    public void setUseType(List<CourseCategory> useType) {
        this.useType = useType;
    }

    public List<MaterialEntity> getMaterial() {
        return material;
    }

    public void setMaterial(List<MaterialEntity> material) {
        this.material = material;
    }

    public static SchoolResource toSchoolResource(SupplierResource supplierResource) {
        SchoolResource resource = new SchoolResource();
        resource.setId(supplierResource.getId());
        resource.setName(supplierResource.getName());
        resource.setCategoryId(supplierResource.getCategoryId());
        resource.setImgUrl(supplierResource.getImgUrl());
        resource.setUseType(Constant.SUPPLIER_TYPE);
        resource.setMediaType(Constant.SUPPLIER_TYPE);
        resource.setDescription(supplierResource.getDescription());
        resource.setKeyword(supplierResource.getKeyword());
        return resource;
    }
}
