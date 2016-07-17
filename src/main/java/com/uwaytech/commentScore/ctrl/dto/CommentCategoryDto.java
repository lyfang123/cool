package com.uwaytech.commentScore.ctrl.dto;

import com.github.pagehelper.Page;
import com.uwaytech.commentScore.domain.CommentCategory;

import java.io.Serializable;
import java.util.List;

public class CommentCategoryDto implements Serializable {
    private long total;

    private Integer pageNum;

    private Integer pageSize;

    private List<CommentCategory> rows;

    public static CommentCategoryDto toDto(Page<CommentCategory> page) {
        CommentCategoryDto dto = new CommentCategoryDto();
        dto.setPageSize(page.getPageSize());
        dto.setPageNum(page.getPageNum());
        dto.setTotal(page.getTotal());
        dto.setRows(page.getResult());
        return dto;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
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

    public List<CommentCategory> getRows() {
        return rows;
    }

    public void setRows(List<CommentCategory> rows) {
        this.rows = rows;
    }
}