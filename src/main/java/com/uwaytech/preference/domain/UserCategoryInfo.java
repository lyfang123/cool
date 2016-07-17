package com.uwaytech.preference.domain;

import java.io.Serializable;

public class UserCategoryInfo implements Serializable {

    private Long[] categoryIds;

    private Integer type;

    private static final long serialVersionUID = 1L;

    public Long[] getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(Long[] categoryIds) {
        this.categoryIds = categoryIds;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}