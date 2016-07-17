package com.uwaytech.schoolCourse.domain;

import java.util.List;

/**
 * Created by zeng on 2016/6/7.
 */
public class CourseListInfo {
    private Integer pageNum;
    private Integer pageSize;
    private Long total;
    private List<CourseDetail> rows;

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

    public List<CourseDetail> getRows() {
        return rows;
    }

    public void setRows(List<CourseDetail> rows) {
        this.rows = rows;
    }
}
