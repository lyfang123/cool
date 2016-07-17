package com.uwaytech.material.domain;

import com.uwaytech.material.ctrl.dto.MaterialDto;

import java.util.List;

/**
 * Created by zeng on 2016/5/30.
 */
public class MaterialInfo {
    private List<MaterialDto> rows;
    private Integer pageNum;
    private Integer pageSize;
    private Long total;

    public List<MaterialDto> getRows() {
        return rows;
    }

    public void setRows(List<MaterialDto> rows) {
        this.rows = rows;
    }

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
}
