package com.uwaytech.browsing.ctrl.dto;

import com.github.pagehelper.Page;
import com.uwaytech.browsing.domain.BrowsingInfo;

import java.util.List;

/**
 * Created by lyf on 2016/7/14.
 */
public class BrowsingInfoDto {
    private long total;

    private Integer pageNum;

    private Integer pageSize;

    private List<BrowsingInfo> rows;

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

    public List<BrowsingInfo> getRows() {
        return rows;
    }

    public void setRows(List<BrowsingInfo> rows) {
        this.rows = rows;
    }

    public static BrowsingInfoDto dto(Page<BrowsingInfo> page) {
        BrowsingInfoDto dto = new BrowsingInfoDto();
        if(null != page){
            dto.setTotal(new Long(page.getTotal()));
            dto.setPageSize(page.getPageSize());
            dto.setPageNum(page.getPageNum());
            dto.setRows(page.getResult());
        }
        return dto;
    }
}
