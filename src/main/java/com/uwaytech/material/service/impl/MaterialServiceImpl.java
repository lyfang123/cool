package com.uwaytech.material.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.uwaytech.common.exception.PermissionDeniedException;
import com.uwaytech.cool.common.constant.Constant;
import com.uwaytech.cool.common.enums.ResStatusEnum;
import com.uwaytech.httpclient.SessionUtils;
import com.uwaytech.material.dao.ExtendMaterialMapper;
import com.uwaytech.material.dao.MaterialMapper;
import com.uwaytech.material.domain.Material;
import com.uwaytech.material.domain.MaterialExample;
import com.uwaytech.material.service.IMaterialService;
import com.uwaytech.supplierCourse.dao.SupplierCourseMapper;
import com.uwaytech.supplierCourse.domain.SupplierCourseExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by zeng on 2016/5/26.
 */
@Service("materialService")
public class MaterialServiceImpl implements IMaterialService {

	@Resource
	private MaterialMapper materialMapper;
	@Resource
	private ExtendMaterialMapper extendMaterialMapper;


	@Override
	public int insertMaterial(List<Material> material) {
		return extendMaterialMapper.insertMaterials(material);
	}

	@Override
	public int updateMaterial(Material material) {
		int result = materialMapper.updateByPrimaryKeySelective(material);
		return result;
	}

	@Override
	public void deleteMaterial(Long materialId) {
		Integer status = materialMapper.selectByPrimaryKey(materialId).getStatus().intValue();
		if (status == Constant.ONE) {
			throw new PermissionDeniedException("素材已发布不能删除");
		} else {
			Material material = new Material();
			material.setId(materialId);
			material.setStatus(ResStatusEnum.DELETE.getValue());
			materialMapper.updateByPrimaryKeySelective(material);
		}
	}

	@Override
	public Material getMaterialById(Long id) {
		return materialMapper.selectByPrimaryKey(id);
	}

	@Override
	public Page<Material> findMaterials(Integer type, Long categoryId, Long grouping, Integer pageNum, Integer pageSize) {
		MaterialExample material = new MaterialExample();
		material.createCriteria().andGroupingEqualTo(grouping).andTypeEqualTo(type).andCusCateIdEqualTo(categoryId).andStatusNotEqualTo(Constant.DELETE);
		material.setOrderByClause("id DESC");
		PageHelper.startPage(pageNum, pageSize);
		Page<Material> page = (Page<Material>) materialMapper.selectByExample(material);
		return page;
	}

}
