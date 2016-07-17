package com.uwaytech.schoolResource.service;

import com.github.pagehelper.Page;
import com.uwaytech.schoolCourse.domain.MaterialEntity;
import com.uwaytech.schoolResource.domain.ResourceInfo;
import com.uwaytech.schoolResource.domain.ResourceTermInfo;
import com.uwaytech.schoolResource.domain.SupplierResourceDetail;
import com.uwaytech.schoolResource.domain.SupplierResourceResult;
import com.uwaytech.supplierResource.domain.SupplierResource;

/**
 * Created by zeng on 2016/5/31.
 */
public interface IResourceService {

	/**
	 * 新增供应商资源
	 * @param supplierResource 资源详情
	 * @param id 资源id
	 * @param grouping 供应商id
	 * @param materialEntity
	 * @return
	 */
	int insertSupplierResource(SupplierResource supplierResource, Long id, Long grouping, MaterialEntity materialEntity);

	/**
	 * 资源更新
	 * @param supplierResource 资源详情
	 * @param type 用户类型
	 * @param resourceId 资源id
	 * @param materialEntity
	 * @return
	 */
	int updateResource(SupplierResource supplierResource, Byte type, Long resourceId, MaterialEntity materialEntity);

	/**
	 * 资源列表
	 * @param resourceTerm 查询条件
	 * @param type 用户类型
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	ResourceInfo findResource(ResourceTermInfo resourceTerm, Byte type, Integer pageNum, Integer pageSize);

	/**
	 * 资源详情
	 * @param resourceId 资源id
	 * @param type 用户类型
	 * @return
	 */
	SupplierResourceDetail findResourceDetail(Long resourceId, Byte type);

	/**
	 * 供应商资源列表
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	Page<SupplierResourceResult> findSupplierResource(Integer pageNum, Integer pageSize);

	/**
	 * 资源上架
	 * @param resourceId 资源id
	 * @param status 上下架状态
	 * @return
	 */
	int shelveResource(Long resourceId, Integer status);

	/**
	 * 资源删除
	 * @param id 资源id
	 * @param type 用户类型
	 * @return
	 */
	int deleteResource(Long id, Byte type);

	/**
	 * 资源审核
	 * @param resourceId 资源id
	 *@param checkStatus 审核状态
	 * @param reason 未通过理由
	 */
	int reviewResource(Long resourceId, Integer checkStatus, String reason);

	/**
	 * 学校资源推荐
	 * @param resourceId 资源id
	 * @param recommend 推荐状态
	 */
	void recommendSchoolResource(Long resourceId, Integer recommend);

	/**
	 * 新增学校资源
	 * @param supplierResource 资源详情
	 * @param id 资源id
	 * @param grouping 学校id
	 * @param materialEntity
	 * @return
	 */
	int insertSchoolResource(SupplierResource supplierResource, Long id, Long grouping, MaterialEntity materialEntity);

	/**
	 * 供应商资源推荐
	 * @param resourceId 资源id
	 * @param recommend 推荐状态
	 */
	void recommendSupplierResource(Long resourceId, Integer recommend);

	/**
	 * 更新阅读次数
	 * @param resourceId 资源id
	 */
	void updateViewNumber(Long resourceId);
}
