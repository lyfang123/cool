package com.uwaytech.supplierResource.dao;

import com.uwaytech.supplierResource.domain.SupplierResource;
import com.uwaytech.supplierResource.domain.SupplierResourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SupplierResourceMapper {
    int countByExample(SupplierResourceExample example);

    int deleteByExample(SupplierResourceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SupplierResource record);

    int insertSelective(SupplierResource record);

    List<SupplierResource> selectByExample(SupplierResourceExample example);

    SupplierResource selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SupplierResource record, @Param("example") SupplierResourceExample example);

    int updateByExample(@Param("record") SupplierResource record, @Param("example") SupplierResourceExample example);

    int updateByPrimaryKeySelective(SupplierResource record);

    int updateByPrimaryKey(SupplierResource record);
}