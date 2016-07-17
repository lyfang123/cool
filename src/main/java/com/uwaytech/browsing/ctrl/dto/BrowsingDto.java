package com.uwaytech.browsing.ctrl.dto;

import com.github.pagehelper.Page;
import com.uwaytech.browsing.domain.Browsing;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyf on 2016/7/14.
 */
public class BrowsingDto {
    private long total;

    private Integer pageNum;

    private Integer pageSize;

    private List<BrowsingInfos> rows;

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

    public List<BrowsingInfos> getRows() {
        return rows;
    }

    public void setRows(List<BrowsingInfos> rows) {
        this.rows = rows;
    }

    public static BrowsingDto dto(Page<Browsing> page) {
        BrowsingDto dto = new BrowsingDto();
        if(null != page){
            dto.setTotal(new Long(page.getTotal()));
            dto.setPageSize(page.getPageSize());
            dto.setPageNum(page.getPageNum());
            if (null != page.getResult() && page.getResult().size() > 0) {
                List<BrowsingInfos> list = new ArrayList<BrowsingInfos>();
                for (Browsing browsing : page.getResult()) {
                    BrowsingInfos browsingInfo = new BrowsingInfos();
                    browsingInfo.setUserId(browsing.getUserId().toString());
                    //TODO

                    list.add(browsingInfo);
                }
                dto.setRows(list);
            }
        }
        return dto;
    }
}
