package com.uwaytech.supplierResource.dao;

import com.uwaytech.supplierCourse.ctrl.dto.CategoryInfo;
import com.uwaytech.supplierResource.ctrl.dto.SupplierResourceInfo;
import com.uwaytech.supplierResource.domain.SupplierResourceParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtendSupplierResourceMapper {

    List<SupplierResourceInfo> querySchoolResourceList(SupplierResourceParam param);

    List<SupplierResourceInfo> querySupplierResourceList(SupplierResourceParam param);

    List<CategoryInfo> querySchoolCategoryList(SupplierResourceParam param);

    List<CategoryInfo> querySupplierCategoryList(SupplierResourceParam param);

    List<CategoryInfo> queryCommCategoryList(@Param("type")Integer type);
}