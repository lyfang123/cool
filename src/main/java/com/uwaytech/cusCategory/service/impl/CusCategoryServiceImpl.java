package com.uwaytech.cusCategory.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.uwaytech.common.exception.ParamMissException;
import com.uwaytech.common.exception.PermissionDeniedException;
import com.uwaytech.cusCategory.dao.CusCategoryMapper;
import com.uwaytech.cusCategory.domain.CusCategory;
import com.uwaytech.cusCategory.domain.CusCategoryExample;
import com.uwaytech.cusCategory.service.CusCategoryService;
import com.uwaytech.schoolCourse.dao.SchoolCourseMapper;
import com.uwaytech.schoolCourse.domain.SchoolCourseExample;
import com.uwaytech.schoolResource.dao.SchoolResourceMapper;
import com.uwaytech.schoolResource.domain.SchoolResourceExample;
import com.uwaytech.supplierCourse.dao.SupplierCourseMapper;
import com.uwaytech.supplierCourse.domain.SupplierCourseExample;
import com.uwaytech.supplierResource.dao.SupplierResourceMapper;
import com.uwaytech.supplierResource.domain.SupplierResourceExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by moyi on 2016-06-02.
 */
@Service
public class CusCategoryServiceImpl implements CusCategoryService {

    @Resource
    private CusCategoryMapper cusCategoryMapper;

    @Resource
    private SchoolCourseMapper schoolCourseMapper;

    @Resource
    private SchoolResourceMapper schoolResourceMapper;

    @Resource
    private SupplierCourseMapper supplierCourseMapper;

    @Resource
    private SupplierResourceMapper supplierResourceMapper;

    @Override
    public List<CusCategory> queryCusCategory(Integer type, Long group) {
        CusCategoryExample example = new CusCategoryExample();
        example.or().andTypeEqualTo(type).andGroupingEqualTo(group);
        example.setOrderByClause("sort desc");
        return cusCategoryMapper.selectByExample(example);
    }

    @Override
    public int addCusCategory(String name, Integer type, Long group, Long parentId) {
        CusCategory cusCategory = new CusCategory();
        cusCategory.setType(type);
        cusCategory.setGrouping(group);
        cusCategory.setSort(System.currentTimeMillis());
        cusCategory.setName(name);
        cusCategory.setCreateTime(new Date());
        cusCategory.setParentId(parentId);
        return cusCategoryMapper.insertSelective(cusCategory);
    }

    @Override
    public int deleteCusCategory(Long id, Byte userType, Integer type) {
        //TODO 修改UC
        switch (userType) {
            case 2:
                checkSchoolExistCategory(id);
                break;
            case 5:
                checkSupplierExistCategory(id);
             break;
            case 6:
                checkSchoolExistCategory(id);
                checkSupplierExistCategory(id);
                break;

        }

        //如果有下级不让删除
        CusCategoryExample cusCategoryExample = new CusCategoryExample();
        cusCategoryExample.createCriteria().andParentIdEqualTo(id);
        List<CusCategory> list = cusCategoryMapper.selectByExample(cusCategoryExample);
        if(!list.isEmpty()){
            throw new PermissionDeniedException();
        }
        return cusCategoryMapper.deleteByPrimaryKey(id);
    }

public void checkSchoolExistCategory(Long id){
    //检查学校课程表是否引用了该分类，如果存在则不能删除
    SchoolCourseExample schoolCourseExample = new SchoolCourseExample();
    schoolCourseExample.createCriteria().andCategoryIdEqualTo(id);
    if (!schoolCourseMapper.selectByExample(schoolCourseExample).isEmpty()) {
        throw new PermissionDeniedException("分类被其他课程引用");
    }
    //检查学校资源表是否引用了该分类，如果存在则不能删除
    SchoolResourceExample schoolResourceExample = new SchoolResourceExample();
    schoolResourceExample.createCriteria().andCategoryIdEqualTo(id);
    if (!schoolResourceMapper.selectByExample(schoolResourceExample).isEmpty()) {
        throw new PermissionDeniedException("分类被其他资源引用");
    }
}


    public void checkSupplierExistCategory(Long id){
        //检查供应商课程表是否引用了该分类，如果存在则不能删除
        SupplierCourseExample supplierCourseExample = new SupplierCourseExample();
        supplierCourseExample.or().andCategoryIdEqualTo(id);
        if (!supplierCourseMapper.selectByExample(supplierCourseExample).isEmpty()) {
            throw new PermissionDeniedException("分类被其他课程引用");
        }
        //检查供应商资源表是否引用了该分类，如果存在则不能删除
        SupplierResourceExample supplierResourceExample = new SupplierResourceExample();
        supplierResourceExample.or().andCategoryIdEqualTo(id);
        if (!supplierResourceMapper.selectByExample(supplierResourceExample).isEmpty()) {
            throw new PermissionDeniedException("分类被其他资源引用");
        }
    }


    @Override
    public int updateCusCategory(Integer type, CusCategory[] cusCategorys) {
        //判断是否需要交换排序位置
        Long tempSort = 0L;
        int result = 0;
        if (cusCategorys.length > 1 && cusCategorys[0].getSort() != null && cusCategorys[1].getSort() != null) {
            tempSort = cusCategorys[0].getSort();
            cusCategorys[0].setSort(cusCategorys[1].getSort());
            cusCategorys[1].setSort(tempSort);
        }
        //循环更新数据，小量适用
        if (cusCategorys.length > 0) {
            for (CusCategory cate : cusCategorys) {
                result += cusCategoryMapper.updateByPrimaryKeySelective(cate);
            }
        } else {
            new ParamMissException("传入参数不对");
        }
        return result;
    }

    @Override
    public int updateCusCategory(Integer type, Long id, String name, Long parentId ) {
        //判断是否需要交换排序位置
        CusCategory cusCategory = new CusCategory();
        cusCategory.setName(name);
        cusCategory.setId(id);
        if(parentId != null || parentId != 0){
            cusCategory.setParentId(parentId);
        }
        cusCategory.setType(type);
        return cusCategoryMapper.updateByPrimaryKeySelective(cusCategory);
    }

    @Override
    public Page<CusCategory> queryCusCategoryByLevel(Integer level, Integer type, Long group, Integer pageNum,
                                                     Integer pageSize) {
        CusCategoryExample example = new CusCategoryExample();
        example.createCriteria().andLevelEqualTo(level).andTypeEqualTo(type).andGroupingEqualTo(group);
        example.setOrderByClause("sort desc");
        PageHelper.startPage(pageNum, pageSize);
        Page<CusCategory> page = (Page<CusCategory>)cusCategoryMapper.selectByExample(example);
        return page;
    }

    @Override
    public Page<CusCategory> queryCusCategoryByParentId(Long groupId, Integer type, Long parentId, Integer pageSize,
                                                        Integer pageNum) {
        CusCategoryExample example = new CusCategoryExample();
        example.createCriteria().andParentIdEqualTo(parentId).andTypeEqualTo(type).andGroupingEqualTo(groupId);
        example.setOrderByClause("sort desc");
        PageHelper.startPage(pageNum, pageSize);
        Page<CusCategory> page = (Page<CusCategory>)cusCategoryMapper.selectByExample(example);
        return page;
    }
}
