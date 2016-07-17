package com.uwaytech.schoolResource.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.uwaytech.common.exception.NotDataFoundException;
import com.uwaytech.common.exception.PermissionDeniedException;
import com.uwaytech.cool.common.constant.Constant;
import com.uwaytech.cool.common.enums.CheckStatusEnum;
import com.uwaytech.cool.common.enums.RecommendEnum;
import com.uwaytech.cool.common.enums.ResStatusEnum;
import com.uwaytech.cool.common.enums.StatusEnum;
import com.uwaytech.material.dao.MaterialMapper;
import com.uwaytech.material.domain.Material;
import com.uwaytech.schoolCourse.domain.MaterialEntity;
import com.uwaytech.schoolResource.dao.ExtendResourceMapper;
import com.uwaytech.schoolResource.dao.SchoolResourceMapper;
import com.uwaytech.schoolResource.domain.*;
import com.uwaytech.schoolResource.service.IResourceService;
import com.uwaytech.supplierResource.dao.SupplierResourceMapper;
import com.uwaytech.supplierResource.domain.SupplierResource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by zeng on 2016/5/31.
 */
@Service("resourceService")
public class ResourceServiceImpl implements IResourceService {

	@Resource
	private SchoolResourceMapper schoolResourceMapper;
	@Resource
	private SupplierResourceMapper supplierResourceMapper;
	@Resource
	private ExtendResourceMapper extendResourceMapper;
	@Resource
	private MaterialMapper materialMapper;

	@Override
	public int insertSupplierResource(SupplierResource supplierResource, Long id, Long grouping,
	                                  MaterialEntity materialEntity) {
		//判断资源或素材是否存在
		Material material = materialMapper.selectByPrimaryKey(supplierResource.getMaterialId());
		if (null != material) {
			//资源是否自动上架
			if (supplierResource.getAutoType() == Constant.AUTO) {
				//资源自动上架
				supplierResource.setStatus(Integer.valueOf(ResStatusEnum.AUTO_SHELVE.getValue()));
			} else {
				//资源下架
				supplierResource.setStatus(Integer.valueOf(ResStatusEnum.OFF_SHELVE.getValue()));
			}
			//资源未审核
			supplierResource.setCheckStatus(Integer.valueOf(CheckStatusEnum.NOT_REVIEW.getValue()));
			supplierResource.setRecommend(Integer.valueOf(RecommendEnum.NOT_RECOMMEND.getValue()));
			supplierResource.setId(id);
			supplierResource.setSupplierId(grouping);
			supplierResource.setCreateTime(new Date());
			//添加资源
			supplierResourceMapper.insertSelective(supplierResource);
			//素材发布
			updateMaterialStatus(supplierResource.getMaterialId());
			//素材与资源关联关系
			return setMaterialResource(id, materialEntity);
		} else {
			throw new NotDataFoundException("素材不存在");
		}
	}

	@Override
	public int insertSchoolResource(SupplierResource supplierResource, Long id, Long grouping,
	                                MaterialEntity materialEntity) {
		//判断资源或素材是否存在
		Material material = materialMapper.selectByPrimaryKey(supplierResource.getMaterialId());
		if (null != material) {
			SchoolResource schoolResource = SupplierResourceDetail.toSchoolResource(supplierResource);
			schoolResource.setSchoolId(grouping);
			schoolResource.setId(id);
			//学校资源未推荐
			schoolResource.setRecommend(Integer.valueOf(RecommendEnum.NOT_RECOMMEND.getValue()));
			schoolResource.setStatus(Integer.valueOf(StatusEnum.Valid.getValue()));
			schoolResource.setCreateTime(new Date());
			//学校自己课程
			schoolResource.setType(Constant.SCHOOL_OWEN);
			schoolResourceMapper.insertSelective(schoolResource);
			//素材发布
			updateMaterialStatus(supplierResource.getMaterialId());
			//素材与资源关联关系
			return setMaterialResource(id, materialEntity);
		} else {
			throw new NotDataFoundException("素材不存在");
		}
	}


	@Override
	public int updateResource(SupplierResource supplierResource, Byte type, Long resourceId,
	                          MaterialEntity materialEntity) {
		if (type == Constant.SUPPLIER) {
			SupplierResource resource = supplierResourceMapper.selectByPrimaryKey(supplierResource.getId());
			if (null == resource) {
				throw new NotDataFoundException("资源不存在");
			} else {
				//资源下架
				if (ResStatusEnum.OFF_SHELVE.getValue() == resource.getStatus()) {
					//资源是否自动上架
					if (supplierResource.getAutoType() == Constant.AUTO) {
						//资源自动上架
						supplierResource.setStatus(Integer.valueOf(ResStatusEnum.AUTO_SHELVE.getValue()));
					}
					//资源未审核状态
					supplierResource.setCheckStatus(Integer.valueOf(CheckStatusEnum.NOT_REVIEW.getValue()));
					supplierResourceMapper.updateByPrimaryKeySelective(supplierResource);
				} else {
					//资源上架
					throw new PermissionDeniedException("该资源已上架，不能修改");
				}
			}
		} else {
			SchoolResource schoolResource = SupplierResourceDetail.toSchoolResource(supplierResource);
			schoolResourceMapper.updateByPrimaryKeySelective(schoolResource);
		}
		//素材发布
		updateMaterialStatus(supplierResource.getMaterialId());
		//删除资源与素材关联关系
		extendResourceMapper.deleteResourceMaterial(resourceId);
		//素材与资源关联关系
		return setMaterialResource(resourceId, materialEntity);
	}

	@Override
	public ResourceInfo findResource(ResourceTermInfo term, Byte type, Integer pageNum, Integer pageSize) {
		ResourceInfo resourceInfo = new ResourceInfo();
		resourceInfo.setPageNum(pageNum);
		resourceInfo.setPageSize(pageSize);
		Page<ResourceListResult> page = new Page<>();
		PageHelper.startPage(pageNum, pageSize);
		if ((type == Constant.SUPPLIER) || (type == Constant.ADMIN)) {
			if (StringUtils.isBlank(term.getKw())) {
				page = extendResourceMapper.findSupplierResourceList(term);
			} else {
				page = extendResourceMapper.findSupplierResourceListByKeyword(term);
			}
			DownloadInfo downloadInfo = extendResourceMapper.findDownloadInfo(term.getGroupId());
			if (null != downloadInfo) {
				resourceInfo.setNumber(downloadInfo.getNumber());
				resourceInfo.seteCoin(downloadInfo.geteCoin());
			}
			resourceInfo.setTotal(page.getTotal());
			resourceInfo.setRows(page.getResult());
		} else {
			if (StringUtils.isBlank(term.getKw())) {
				page = extendResourceMapper.findSchoolResourceList(term);
			} else {
				page = extendResourceMapper.findSchoolResourceListByKeyword(term);
			}
			resourceInfo.setTotal(page.getTotal());
			resourceInfo.setRows(page.getResult());
		}
		return resourceInfo;
	}

	@Override
	public SupplierResourceDetail findResourceDetail(Long resourceId, Byte type) {
		SupplierResourceDetail resource = new SupplierResourceDetail();
		if ((type == Constant.SUPPLIER) || (type == Constant.ADMIN)) {
			resource = extendResourceMapper.findSupplierResourceDetail(resourceId);
		} else {
			resource = extendResourceMapper.findSchoolResourceDetail(resourceId);
		}
		return resource;
	}

	@Override
	public Page<SupplierResourceResult> findSupplierResource(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		Page<SupplierResourceResult> page = extendResourceMapper.findSupplierResource();
		return page;
	}

	@Override
	public int shelveResource(Long resourceId, Integer status) {
		int result = 0;
		SupplierResource resource = supplierResourceMapper.selectByPrimaryKey(resourceId);
		if (null == resource) {
			throw new NotDataFoundException("资源不存在");
		} else {
			if (CheckStatusEnum.PASS.getValue() == resource.getCheckStatus()) {
				//通过审核
				resource.setStatus(status);
				result = supplierResourceMapper.updateByPrimaryKeySelective(resource);
			} else if (CheckStatusEnum.NOT_REVIEW.getValue() == resource.getCheckStatus()) {
				//未审核
				throw new PermissionDeniedException("该资源还未审核");
			} else if (CheckStatusEnum.REFUSE.getValue() == resource.getCheckStatus()) {
				//未通过审核
				throw new PermissionDeniedException("该资源未通过审核");
			}
		}
		return result;
	}

	@Override
	public int deleteResource(Long id, Byte type) {
		if (type == Constant.SUPPLIER) {
			SupplierResource resource = supplierResourceMapper.selectByPrimaryKey(id);
			if (null == resource) {
				throw new NotDataFoundException("资源不存在");
			} else {
				if (ResStatusEnum.OFF_SHELVE.getValue() == resource.getStatus()) {
					//资源下架
					resource.setStatus(Integer.valueOf(ResStatusEnum.DELETE.getValue()));
					return supplierResourceMapper.updateByPrimaryKeySelective(resource);
				} else {
					//资源上架
					throw new PermissionDeniedException("该资源已上架，不能删除");
				}
			}
		} else {
			SchoolResource schoolResource = new SchoolResource();
			schoolResource.setId(id);
			schoolResource.setStatus(Integer.valueOf(ResStatusEnum.DELETE.getValue()));
			return schoolResourceMapper.updateByPrimaryKeySelective(schoolResource);
		}
	}

	@Override
	public int reviewResource(Long resourceId, Integer checkStatus, String reason) {
		SupplierResource resource = supplierResourceMapper.selectByPrimaryKey(resourceId);
		if (null == resource) {
			throw new NotDataFoundException("资源不存在");
		} else {
			resource.setCheckStatus(checkStatus);
			resource.setReason(reason);
			if (resource.getStatus() == ResStatusEnum.AUTO_SHELVE.getValue()) {
				resource.setStatus(Integer.valueOf(ResStatusEnum.ON_SHELVE.getValue()));
			}
		}
		return supplierResourceMapper.updateByPrimaryKeySelective(resource);
	}

	@Override
	public void recommendSchoolResource(Long resourceId, Integer recommend) {
		SchoolResource resource = new SchoolResource();
		resource.setId(resourceId);
		resource.setRecommend(recommend);
		schoolResourceMapper.updateByPrimaryKeySelective(resource);
	}

	@Override
	public void recommendSupplierResource(Long resourceId, Integer recommend) {
		SupplierResource supplierResource = supplierResourceMapper.selectByPrimaryKey(resourceId);
		if (null == supplierResource) {
			throw new NotDataFoundException("资源不存在");
		} else {
			if (supplierResource.getCheckStatus() == CheckStatusEnum.NOT_REVIEW.getValue()) {
				throw new PermissionDeniedException("该课程还未审核");
			} else if (CheckStatusEnum.REFUSE.getValue() == supplierResource.getCheckStatus()) {
				throw new PermissionDeniedException("该课程未通过审核");
			} else if (CheckStatusEnum.PASS.getValue() == supplierResource.getCheckStatus()) {
				if (supplierResource.getStatus() == ResStatusEnum.OFF_SHELVE.getValue()) {
					throw new PermissionDeniedException("该课程还未上架");
				} else if (supplierResource.getStatus() == ResStatusEnum.ON_SHELVE.getValue()) {
					SupplierResource resource = new SupplierResource();
					resource.setId(resourceId);
					resource.setRecommend(recommend);
					supplierResourceMapper.updateByPrimaryKeySelective(resource);
				}
			}
		}
	}


	@Override
	public void updateViewNumber(Long resourceId) {
		if (resourceId%2 == Constant.ONE) { //供应商课程
			extendResourceMapper.updateSupplierReadNum(resourceId);
		} else { //学校课程
			extendResourceMapper.updateSchoolReadNum(resourceId);
		}
	}

	/**
	 * 素材发布
	 * @param id
	 */
	public void updateMaterialStatus(Long id) {
		Material material = new Material();
		material.setId(id);
		material.setStatus(ResStatusEnum.ON_SHELVE.getValue());
		materialMapper.updateByPrimaryKeySelective(material);
	}

	/**
	 * 素材与资源关联关系
	 * @param resourceId
	 * @param entity
	 * @return
	 */
	public int setMaterialResource(Long resourceId, MaterialEntity entity) {
		ResourceMaterial resourceMaterial = new ResourceMaterial();
		resourceMaterial.setMaterialId(entity.getMaterialId());
		resourceMaterial.setResourceId(resourceId);
		resourceMaterial.setTitle(entity.getTitle());
		resourceMaterial.setName(entity.getName());
		return extendResourceMapper.insertResourceMaterial(resourceMaterial);
	}
}
