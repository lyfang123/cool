package com.uwaytech.security.ctrl.dto;

import com.github.pagehelper.Page;
import com.uwaytech.security.domain.RoleInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyf on 2016/6/30.
 */
public class RoleInfoDto {
    private long total;

    private Integer pageNum;

    private Integer pageSize;

    private List<RoleInfos> rows;
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

    public List<RoleInfos> getRows() {
        return rows;
    }

    public void setRows(List<RoleInfos> rows) {
        this.rows = rows;
    }

    public static RoleInfoDto roleInfoDto(Page<RoleInfo> page) {
        RoleInfoDto dto = new RoleInfoDto();
        dto.setPageNum(page.getPageNum());
        dto.setPageSize(page.getPageSize());
        dto.setTotal(page.getTotal());
        ArrayList<RoleInfos> list = new ArrayList<RoleInfos>();
        if(!page.getResult().isEmpty()){
            for (RoleInfo roleInfo:page.getResult()){
                RoleInfos role = new RoleInfos();
                role.setId(roleInfo.getId());
                role.setRoleName(roleInfo.getRoleName());
                role.setType(roleInfo.getType());
                role.setRoleDesc(roleInfo.getRoleDesc());
                list.add(role);
            }
            dto.setRows(list);
        }
        return dto;
    }
}
