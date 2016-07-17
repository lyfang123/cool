package com.uwaytech.schoolResource.dao;

import com.github.pagehelper.Page;
import com.uwaytech.schoolResource.domain.*;

/**
 * Created by zeng on 2016/6/3.
 */
public interface ExtendResourceMapper {

	int insertResourceMaterial(ResourceMaterial resourceMaterial);

	Page<ResourceListResult> findSchoolResourceList(ResourceTermInfo resourceTermInfo);

	Page<ResourceListResult> findSupplierResourceList(ResourceTermInfo resourceTermInfo);

	Page<SupplierResourceResult> findSupplierResource();

	DownloadInfo findDownloadInfo(Long supplier);

	SupplierResourceDetail findSupplierResourceDetail(Long resourceId);

	SupplierResourceDetail findSchoolResourceDetail(Long resourceId);

	int updateResourceMaterial(ResourceMaterial resourceMaterial);

	void updateSupplierReadNum(Long resourceId);

	void updateSchoolReadNum(Long resourceId);

	void deleteResourceMaterial(Long resourceId);

	Page<ResourceListResult> findSupplierResourceListByKeyword(ResourceTermInfo resourceTermInfo);

	Page<ResourceListResult> findSchoolResourceListByKeyword(ResourceTermInfo resourceTermInfo);

	ResourceMaterial selectMaterialIdByResourceId(Long id);
}
