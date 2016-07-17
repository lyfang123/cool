package com.uwaytech.schoolCourse.ctrl.dto;

import com.github.pagehelper.Page;
import com.uwaytech.cool.common.enums.RoleTypeEnum;
import com.uwaytech.cool.common.util.Calculator;
import com.uwaytech.schoolCourse.domain.SchoolCourse;
import com.uwaytech.supplierCourse.domain.SupplierCourse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyf on 2016/7/14.
 */
public class CourseRecommendInfo {
    private long total;

    private Integer pageNum;

    private Integer pageSize;

    private List<CourseRecommend> rows;

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

    public List<CourseRecommend> getRows() {
        return rows;
    }

    public void setRows(List<CourseRecommend> rows) {
        this.rows = rows;
    }

    public static CourseRecommendInfo toSupplierCourse(Page<SupplierCourse> page) {
        CourseRecommendInfo dto = new CourseRecommendInfo();
        if(null != page){
            dto.setTotal(new Long(page.getTotal()));
            dto.setPageSize(page.getPageSize());
            dto.setPageNum(page.getPageNum());
            if (null != page.getResult() && page.getResult().size() > 0) {
                List<CourseRecommend> list = new ArrayList<CourseRecommend>();
                for (SupplierCourse supplierCourse : page.getResult()) {
                    CourseRecommend courseRecommend = new CourseRecommend();
                    courseRecommend.setCellId(Calculator.getCalcValue(supplierCourse.getId(),
                            RoleTypeEnum.SUPPLIER.getValue()).toString());
                    courseRecommend.setImgUrl(supplierCourse.getImgUrl());
                    courseRecommend.setName(supplierCourse.getName());
                    list.add(courseRecommend);
                }
                dto.setRows(list);
            }
        }
        return dto;
    }

    public static CourseRecommendInfo toSchoolCourse(Page<SchoolCourse> page) {
        CourseRecommendInfo dto = new CourseRecommendInfo();
        if(null != page){
            dto.setTotal(new Long(page.getTotal()));
            dto.setPageSize(page.getPageSize());
            dto.setPageNum(page.getPageNum());
            if (null != page.getResult() && page.getResult().size() > 0) {
                List<CourseRecommend> list = new ArrayList<CourseRecommend>();
                for (SchoolCourse schoolCourse : page.getResult()) {
                    CourseRecommend courseRecommend = new CourseRecommend();
                    courseRecommend.setCellId(Calculator.getCalcValue(schoolCourse.getId(),
                            RoleTypeEnum.SUPPLIER.getValue()).toString());
                    courseRecommend.setImgUrl(schoolCourse.getImgUrl());
                    courseRecommend.setName(schoolCourse.getName());
                    list.add(courseRecommend);
                }
                dto.setRows(list);
            }
        }
        return dto;
    }
}
