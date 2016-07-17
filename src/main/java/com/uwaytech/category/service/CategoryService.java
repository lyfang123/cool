package com.uwaytech.category.service;

import com.github.pagehelper.Page;
import com.uwaytech.category.domain.Category;

import java.util.List;

/**
 * Created by moyi on 2016-05-26.
 */
public interface CategoryService {
    List<Category> queryCategory(int level);
    List<Category> queryChildCategory(long categoryId);
    int updateCategory(Category[] category);
    int updateCategory(Long id, String name, Long parentId);
    int deleteCategory(Long categoryId);
    int addCategory(String name, Long parentId, Integer level);
    int batchUpdateCategory(List<Category> list);

    Page<Category> queryCategoryByParentId(Long parentId, Integer pageSize, Integer pageNum);
}
