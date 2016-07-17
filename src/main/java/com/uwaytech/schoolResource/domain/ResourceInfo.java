package com.uwaytech.schoolResource.domain;

import java.util.List;

/**
 * Created by zeng on 2016/5/31.
 */
public class ResourceInfo {
    private Integer pageNum;
    private Integer pageSize;
    private Long total;
    private List<ResourceListResult> rows;
    private Integer number;
    private Long eCoin;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<ResourceListResult> getRows() {
        return rows;
    }

    public void setRows(List<ResourceListResult> rows) {
        this.rows = rows;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Long geteCoin() {
        return eCoin;
    }

    public void seteCoin(Long eCoin) {
        this.eCoin = eCoin;
    }
}
