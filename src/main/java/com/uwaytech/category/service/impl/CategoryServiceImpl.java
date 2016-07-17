package com.uwaytech.category.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.uwaytech.category.dao.CategoryMapper;
import com.uwaytech.category.domain.Category;
import com.uwaytech.category.domain.CategoryExample;
import com.uwaytech.category.service.CategoryService;
import com.uwaytech.common.exception.ParamMissException;
import com.uwaytech.common.exception.PermissionDeniedException;
import com.uwaytech.cool.common.enums.CategoryType;
import com.uwaytech.cool.common.enums.StatusEnum;
import com.uwaytech.supplierCourse.dao.SupplierCourseMapper;
import com.uwaytech.supplierCourse.domain.SupplierCourseExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by moyi on 2016-05-26.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private SupplierCourseMapper supplierCourseMapper;

    @Override
    public List<Category> queryCategory(int level) {
        CategoryExample example = new CategoryExample();
        example.or().andLevelEqualTo(level);
        example.setOrderByClause("sort DESC");
        return categoryMapper.selectByExample(example);
    }

    @Override
    public List<Category> queryChildCategory(long categoryId) {
        CategoryExample example = new CategoryExample();
        example.or().andParentIdEqualTo(categoryId);
        example.setOrderByClause("sort DESC");
        return categoryMapper.selectByExample(example);
    }

    @Override
    public int updateCategory(Category[] categorys) {
        //判断是否需要交换排序位置
        Long tempSort = 0L;
        int result = 0;
        if (categorys.length > 1 && categorys[0].getSort() != null && categorys[1].getSort() != null) {
            tempSort = categorys[0].getSort();
            categorys[0].setSort(categorys[1].getSort());
            categorys[1].setSort(tempSort);
        }
        //循环更新数据，小量适用
        if (categorys.length > 0) {
            for (Category cate : categorys) {
                result += categoryMapper.updateByPrimaryKeySelective(cate);
            }
        } else {
            throw new ParamMissException("传入参数不对");
        }
        return result;
    }

    @Override
    public int updateCategory(Long id, String name, Long parentId) {
        //判断是否需要交换排序位置
        Category category = new Category();
        category.setId(id);
        category.setName(name);
        if (parentId != null || parentId != 0){
            category.setParentId(parentId);
        }
        return categoryMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    public int deleteCategory(Long categoryId) {
        //判断是否有子分类
        CategoryExample example = new CategoryExample();
        example.createCriteria().andParentIdEqualTo(categoryId);
        List<Category> list = categoryMapper.selectByExample(example);
        //判断供应商课程中是否有引用，有则不能删除
        SupplierCourseExample supplierCourseExample = new SupplierCourseExample();
        supplierCourseExample.or().andCategoryIdEqualTo(categoryId);
        if (!supplierCourseMapper.selectByExample(supplierCourseExample).isEmpty()) {
            throw new PermissionDeniedException("该分类含有课程不能删除");
        } else if(!list.isEmpty()) {
            throw new PermissionDeniedException("该分类含有子分类不能删除");
        }
        return categoryMapper.deleteByPrimaryKey(categoryId);
    }

    @Override
    public int addCategory(String name, Long parentId, Integer level) {
        Category category = new Category();
        category.setName(name);
        category.setSort(System.currentTimeMillis());
        category.setParentId(parentId);
        category.setLevel(level);
        category.setType(CategoryType.CommonType.getValue());
        category.setCreateTime(new Date());
        category.setStatus(StatusEnum.Valid.getValue());
        return categoryMapper.insert(category);
    }

    @Override
    public int batchUpdateCategory(List<Category> list) {
        //不适用过多插入
        int result = 0;
        for (int i = 0; i < list.size(); i++) {
            result += categoryMapper.insert(list.get(i));
        }
        return result;
    }

    @Override
    public Page<Category> queryCategoryByParentId(Long parentId, Integer pageSize, Integer pageNum) {
        CategoryExample example = new CategoryExample();
        example.createCriteria().andParentIdEqualTo(parentId);
        example.setOrderByClause("sort desc");
        PageHelper.startPage(pageNum, pageSize);
        Page<Category> page = (Page<Category>)categoryMapper.selectByExample(example);
        return page;
    }

}
