package com.uwaytech.material.dao;

import com.github.pagehelper.Page;
import com.uwaytech.material.domain.Material;

import java.util.List;


/**
 * Created by zeng on 2016/6/3.
 */
public interface ExtendMaterialMapper {

    Page<Material> findMaterialByCateId(Material map);

    int insertMaterials(List<Material> material);
}
