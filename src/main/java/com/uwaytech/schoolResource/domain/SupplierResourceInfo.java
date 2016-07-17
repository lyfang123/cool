package com.uwaytech.schoolResource.domain;

import java.util.List;

/**
 * Created by zeng on 2016/6/1.
 */
public class SupplierResourceInfo {
    private Integer pageNum;
    private Integer pageSize;
    private Long total;
    private List<SupplierResourceResult> rows;

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

    public List<SupplierResourceResult> getRows() {
        return rows;
    }

    public void setRows(List<SupplierResourceResult> rows) {
        this.rows = rows;
    }
}
