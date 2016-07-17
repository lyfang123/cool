package com.uwaytech.category.ctrl;

import com.github.pagehelper.Page;
import com.uwaytech.category.ctrl.dto.CategoryListDto;
import com.uwaytech.category.domain.Category;
import com.uwaytech.category.service.CategoryService;
import com.uwaytech.common.exception.ParamMissException;
import com.uwaytech.common.json.SuccessResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by moyi on 2016-05-26.
 */
@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    /**
     * 新增课程/资源分类
     *
     * @param name     分类名字
     * @param parentId 上级ID
     * @param level    级别
     * @return 是否成功
     */
    @RequestMapping(value = "/v0.1", method = RequestMethod.POST)
    public Object addCategory(@RequestParam("name") String name, @RequestParam("parentId") Long parentId,
                              @RequestParam("level") Integer level) {

        if (StringUtils.isEmpty(name)) {
            new ParamMissException("缺少分类名称");
        }

        categoryService.addCategory(name, parentId, level);

        return new SuccessResult();
    }

    /**
     * 删除分类
     *
     * @param categoryId
     * @return
     */
    @RequestMapping(value = "/v0.1/{id}", method = RequestMethod.DELETE)
    public Object deleteCategory(@PathVariable("id") Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return new SuccessResult();
    }

    /**
     * 修改分类
     *
     * @param categorys
     * @return
     */
    @RequestMapping(value = "/v0.1", method = RequestMethod.PUT)
    public Object updateCategory(@RequestBody Category[] categorys) {
        categoryService.updateCategory(categorys);
        return new SuccessResult();
    }

    /**
     * 修改分类
     *
     * @param categorys
     * @return
     */
    @RequestMapping(value = "/v0.1/{id}", method = RequestMethod.PUT)
    public Object updateCategory(@PathVariable("id") Long id, @RequestParam("name") String name,
                                 @RequestParam(value = "parentId",defaultValue = "0") Long parentId) {
        categoryService.updateCategory(id, name, parentId);
        return new SuccessResult();
    }

    /**
     * 获取分类列表
     *
     * @param categoryId
     * @param level
     * @return
     */
    @RequestMapping(value = "/v0.1/list", method = RequestMethod.GET)
    public Object categoryList(@RequestParam(value = "categoryId", defaultValue = "0") Long categoryId,
                               @RequestParam(value = "level", defaultValue = "0") Integer level) {
        if (level != 0) {
            return categoryService.queryCategory(level);
        }
        if (categoryId != 0) {
            return categoryService.queryChildCategory(categoryId);
        }
        return categoryService.queryCategory(1);
    }

    @RequestMapping(value = "/v0.1/pid/{parentId}", method = RequestMethod.GET)
    public Object queryCysCategoryList(@PathVariable("parentId") Long parentId,
                                       @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                       @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum) {
        Page<Category> page = categoryService.queryCategoryByParentId(parentId, pageSize, pageNum);
        CategoryListDto dto = new CategoryListDto();
        dto.setTotal(page.getTotal());
        dto.setPageSize(page.getPageSize());
        dto.setPageNum(page.getPageNum());
        dto.setRows(page.getResult());
        return dto;
    }
}
