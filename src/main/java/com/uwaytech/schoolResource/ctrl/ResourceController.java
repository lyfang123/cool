package com.uwaytech.schoolResource.ctrl;

import com.github.pagehelper.Page;
import com.uwaytech.common.exception.PermissionDeniedException;
import com.uwaytech.common.json.SuccessResult;
import com.uwaytech.cool.common.constant.Constant;
import com.uwaytech.httpclient.SessionUtils;
import com.uwaytech.id.service.IdGeneratorService;
import com.uwaytech.schoolCourse.domain.MaterialEntity;
import com.uwaytech.schoolResource.ctrl.dto.ResourceDetailDto;
import com.uwaytech.schoolResource.ctrl.dto.ResourceListDto;
import com.uwaytech.schoolResource.domain.*;
import com.uwaytech.schoolResource.service.IResourceService;
import com.uwaytech.supplierResource.domain.SupplierResource;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by zeng on 2016/5/31.
 */
@RestController
@RequestMapping(value = "/resource")
public class ResourceController {

	@Resource
	private IResourceService resourceService;
	@Resource
	private IdGeneratorService idGeneratorService;

	/**
	 * 新增资源
	 *
	 * @return
	 */
	@RequestMapping(value = "/v0.1", method = RequestMethod.POST)
	public Object addResource(@RequestBody ResourceDetail resource) {
		//获取用户类型
		Byte type = getUserType();
		SupplierResource supplierResource = ResourceDetail.toSupplierResource(resource);
		MaterialEntity materialEntity = resource.getResource().get(0).getMaterials().get(0);
		//供应商资源
		if (type == Constant.SUPPLIER) {
			//生成资源id
			Long id = idGeneratorService.generatorId(Constant.SUPPLIER_NUMBER);
			Long grouping = SessionUtils.getUserId();
			resourceService.insertSupplierResource(supplierResource, id, grouping, materialEntity);
		} else {
			//学校资源
			Long id = idGeneratorService.generatorId(SessionUtils.getSchoolId());
			Long grouping = Long.valueOf(SessionUtils.getSchoolId());
			resourceService.insertSchoolResource(supplierResource, id, grouping, materialEntity);
		}
		return new SuccessResult();
	}

	/**
	 * 修改资源
	 *
	 * @return
	 */
	@RequestMapping(value = "/v0.1/{resourceId}", method = RequestMethod.PUT)
	public Object updateResource(@PathVariable(value = "resourceId") Long resourceId,
	                             @RequestBody ResourceDetail resource) {
		//获取用户类型
		Byte type = getUserType();
		SupplierResource supplierResource = ResourceDetail.toSupplierResource(resource);
		MaterialEntity materialEntity = resource.getResource().get(0).getMaterials().get(0);
		resourceService.updateResource(supplierResource, type, resourceId, materialEntity);
		return new SuccessResult();
	}

	/**
	 * 资源删除
	 *
	 * @param resourceId
	 * @return
	 */
	@RequestMapping(value = "/v0.1/{resourceId}", method = RequestMethod.DELETE)
	public Object deleteResource(@PathVariable(value = "resourceId") Long resourceId) {
		//获取用户类型
		Byte type = getUserType();
		resourceService.deleteResource(resourceId, type);
		return new SuccessResult();
	}

	/**
	 * 资源列表
	 * @param resourceTerm
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/v0.1/list", method = RequestMethod.GET)
	public Object findResources(ResourceTermInfo resourceTerm,
	                            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
	                            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
		//获取用户类型
		Byte type = getUserType();
		if (type == Constant.TEACHER) {
			resourceTerm.setGroupId(SessionUtils.getSchoolId().longValue());
		} else if (type == Constant.SUPPLIER) {
			resourceTerm.setGroupId(SessionUtils.getUserId());
		}
		ResourceInfo resourceInfo = resourceService.findResource(resourceTerm, type, pageNum, pageSize);
		ResourceListDto dto = ResourceListDto.toResourceListDto(resourceInfo);
		return dto;
	}



	/**
	 * 获取资源详情
	 *
	 * @param resourceId
	 * @return
	 */
	@RequestMapping(value = "/v0.1/{resourceId}/detail", method = RequestMethod.GET)
	public Object findResourceDetail(@PathVariable(value = "resourceId") Long resourceId) {
		//获取用户类型
		Byte type = getUserType();
		SupplierResourceDetail resourceDetail = resourceService.findResourceDetail(resourceId, type);
		ResourceDetailDto dto = ResourceDetailDto.toDetailDto(resourceDetail);
		return dto;
	}

	/**
	 * 资源审核
	 *
	 * @param resourceId
	 * @param checkStatus
	 * @return
	 */
	@RequestMapping(value = "/v0.1/review", method = RequestMethod.PUT)
	public Object reviewResource(@RequestParam(value = "resourceId") Long resourceId,
	                             @RequestParam(value = "checkStatus") Integer checkStatus,
	                             @RequestParam(value = "reason") String reason) {
		Byte type = getUserType();
		if (type == Constant.ADMIN) {
			resourceService.reviewResource(resourceId, checkStatus, reason);
		} else {
			throw new PermissionDeniedException("不是管理员，不能进行此操作");
		}
		return new SuccessResult();
	}

	/**
	 * 资源上架、下架
	 *
	 * @param resourceId
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/v0.1/shelve", method = RequestMethod.PUT)
	public Object shelveResource(@RequestParam(value = "resourceId") Long resourceId,
								 @RequestParam(value = "status") Integer status) {
		resourceService.shelveResource(resourceId, status);
		return new SuccessResult();
	}

	/**
	 * 供应商资源统计列表
	 *
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/v0.1/supplier", method = RequestMethod.GET)
	public Object findSupplierResource(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
	                                   @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
		Page<SupplierResourceResult> page = resourceService.findSupplierResource(pageNum, pageSize);
		SupplierResourceInfo resourceList = new SupplierResourceInfo();
		resourceList.setTotal(page.getTotal());
		resourceList.setPageNum(pageNum);
		resourceList.setPageSize(pageSize);
		resourceList.setRows(page.getResult());
		return resourceList;
	}

	/**
	 * 资源推荐
	 * @param resourceId
	 * @param recommend
	 * @return
	 */
	@RequestMapping(value = "/v0.1/recommend", method = RequestMethod.PUT)
	public Object resourceRecommend(@RequestParam(value = "resourceId") Long resourceId,
	                                @RequestParam(value = "recommend") Integer recommend) {
		Byte type = getUserType();
		if ((type == Constant.SUPPLIER) || (type == Constant.ADMIN)) {
			//供应商课程推荐
			resourceService.recommendSupplierResource(resourceId, recommend);
		} else {
			//学校课程推荐
			resourceService.recommendSchoolResource(resourceId, recommend);
		}
		return new SuccessResult();
	}

	/**
	 * 添加资源阅读次数
	 * @param resourceId
	 * @return
	 */
	@RequestMapping(value = "/v0.1/readNumber", method = RequestMethod.PUT)
	public Object updateViewNumber(@RequestParam(value = "resourceId") Long resourceId) {
		resourceService.updateViewNumber(resourceId);
		return new SuccessResult();
	}

	/**
	 * 获取用户类型
	 *
	 * @return
	 */
	public static Byte getUserType() {
		Byte type = SessionUtils.getUserType().byteValue();
		return type;
	}
}
