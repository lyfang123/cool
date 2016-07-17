package com.uwaytech.material.service;

import com.github.pagehelper.Page;
import com.uwaytech.material.domain.Material;

import java.util.List;

/**
 * Created by zeng on 2016/5/26.
 */
public interface IMaterialService {

	/**
	 * 添加素材
	 * @param material
	 * @return
	 */
	int insertMaterial(List<Material> material);

	/**
	 * 获取素材列表
	 * @param type  素材类型
	 * @param categoryId 素材分类id
	 * @param grouping 学校或供应商id
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	Page<Material> findMaterials(Integer type, Long categoryId, Long grouping, Integer pageNum, Integer pageSize);

	/**
	 * 修改素材
	 * @param material
	 * @return
	 */
	int updateMaterial(Material material);

	/**
	 * 删除素材
	 * @param id 素材id
	 */
	void deleteMaterial(Long id);

	/**
	 * 下载
	 * @param id
	 */
	Material getMaterialById(Long id);
}
