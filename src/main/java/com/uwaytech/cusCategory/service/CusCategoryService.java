package com.uwaytech.cusCategory.service;

import com.github.pagehelper.Page;
import com.uwaytech.cusCategory.domain.CusCategory;
import com.uwaytech.httpclient.bo.UserTypeEnum;

import java.util.List;

/**
 * Created by moyi on 2016-06-02.
 */
public interface CusCategoryService {
    /**
     * 查询用户自定义分类列表
     *
     * @param type  分类枚举类型
     * @param group 供应商ID 或者 用户ID
     * @return 返回对应用户的自定义的分类列表
     */
    List<CusCategory> queryCusCategory(Integer type, Long group);

    /**
     * 新增自定义分类
     * @param name 名字
     * @param type 类型
     * @param group 用户ID或者供应商ID
     * @param parentId 上级ID
     * @return 是否成功
     */
    int addCusCategory(String name, Integer type, Long group, Long parentId);

    /**
     * 删除自定义分类
     *
     * @param id       分类ID
     * @param userType 用户类型
     * @param type     分类类型
     * @return
     */
    int deleteCusCategory(Long id, Byte userType, Integer type);

    /**
     * 更新分类
     *
     * @param cusCategory
     * @return
     */
    int updateCusCategory(Integer type, CusCategory[] cusCategory);

    /**
     * 更新分类
     * @return
     */
    int updateCusCategory(Integer type, Long id, String name, Long parentId);

    /**
     * 根据level查询分类列表
     *
     * @param level 级别
     * @param type  分类类型
     * @param group 用户ID,或者供应商ID
     * @param pageNum
     *@param pageSize @return
     */
    Page<CusCategory> queryCusCategoryByLevel(Integer level, Integer type, Long group, Integer pageNum, Integer pageSize);

    /**
     * 根据上级ID查找分类列表
     *
     * @param groupId
     * @param parentId 上级ID
     * @param pageSize
     * @param pageNum @return
     */
    Page<CusCategory> queryCusCategoryByParentId(Long groupId, Integer type, Long parentId, Integer pageSize, Integer pageNum);
}
