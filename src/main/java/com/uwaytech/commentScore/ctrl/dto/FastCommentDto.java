package com.uwaytech.commentScore.ctrl.dto;

import com.github.pagehelper.Page;
import com.uwaytech.commentScore.domain.CommentCategory;
import com.uwaytech.commentScore.domain.FastCommentInfo;

import java.util.List;

/**
 * Created by lyf on 2016/7/14.
 */
public class FastCommentDto {
    private long total;

    private Integer pageNum;

    private Integer pageSize;

    private List<FastCommentInfo> rows;
    public static Object convertDto(Page<FastCommentInfo> page) {
        FastCommentDto dto = new FastCommentDto();
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

    public List<FastCommentInfo> getRows() {
        return rows;
    }

    public void setRows(List<FastCommentInfo> rows) {
        this.rows = rows;
    }
}
