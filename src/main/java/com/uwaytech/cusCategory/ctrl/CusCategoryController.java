package com.uwaytech.cusCategory.ctrl;

import com.github.pagehelper.Page;
import com.uwaytech.common.json.SuccessResult;
import com.uwaytech.cool.common.constant.Constant;
import com.uwaytech.cusCategory.ctrl.dto.CusCateListDto;
import com.uwaytech.cusCategory.domain.CusCategory;
import com.uwaytech.cusCategory.service.CusCategoryService;
import com.uwaytech.httpclient.SessionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by moyi on 2016-06-02.
 */
@RestController
@RequestMapping(value = "/cus/category")
public class CusCategoryController {

    @Resource
    CusCategoryService cusCategoryService;

    @RequestMapping(value = "/v0.1/{type}", method = RequestMethod.POST)
    public Object addCusCategory(@PathVariable("type") Integer type, @RequestParam("name") String name,
                                 @RequestParam(value = "parentId", defaultValue = "0") Long parentId) {
        Byte userType = SessionUtils.getUserType().byteValue();
        Long groupId = null;
        if (userType == Constant.SUPPLIER) {
            groupId = SessionUtils.getUserId();
        } else {
            groupId = SessionUtils.getSchoolId().longValue();
        }
        cusCategoryService.addCusCategory(name, type, groupId, parentId);
        return new SuccessResult();
    }

    @RequestMapping(value = "/v0.1/{type}/{id}", method = RequestMethod.DELETE)
    public Object deleteCusCategory(@PathVariable("type") Integer type, @PathVariable("id") Long id) {
        Byte userType = SessionUtils.getUserType().byteValue();
        cusCategoryService.deleteCusCategory(id, userType, type);
        return new SuccessResult();
    }

    @RequestMapping(value = "/v0.1/{type}", method = RequestMethod.PUT)
    public Object updateCusCategory(@PathVariable("type") Integer type, @RequestBody CusCategory[] cusCategories) {
        cusCategoryService.updateCusCategory(type, cusCategories);
        return new SuccessResult();
    }

    @RequestMapping(value = "/v0.1/{type}/{id}", method = RequestMethod.PUT)
    public Object updateCusCategory(@PathVariable("type") Integer type, @PathVariable("id") Long id,
                                    @RequestParam("name") String name, @RequestParam(value = "parentId",defaultValue = "0")Long parentId) {
        cusCategoryService.updateCusCategory(type, id, name, parentId);
        return new SuccessResult();
    }

    @RequestMapping(value = "/v0.1/{type}/level/{level}", method = RequestMethod.GET)
    public Object queryCysCategoryList(@PathVariable("type") Integer type,
                                       @PathVariable("level")Integer level,
                                       @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                       @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum) {
        Long groupId = SessionUtils.getUserId();
        Page<CusCategory> page = cusCategoryService.queryCusCategoryByLevel(type, level, groupId, pageNum, pageSize);
        CusCateListDto dto = new CusCateListDto();
        dto.setTotal(page.getTotal());
        dto.setPageSize(page.getPageSize());
        dto.setPageNum(page.getPageNum());
        dto.setRows(page.getResult());
        return dto;
    }

    @RequestMapping(value = "/v0.1/{type}/pid/{parentId}", method = RequestMethod.GET)
    public Object queryCysCategoryList(@PathVariable("type") Integer type,
                                       @PathVariable("parentId") Long parentId,
                                       @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                       @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum) {
        Byte userType = SessionUtils.getUserType().byteValue();
        Long groupId= null;
        if (type == Constant.ONE || type == Constant.TWO) {
            groupId = Constant.ZERO;
        } else {
            if (userType == Constant.SUPPLIER) {
                groupId = SessionUtils.getUserId();
            } else {
                groupId = SessionUtils.getSchoolId().longValue();
            }
        }
        Page<CusCategory> page = cusCategoryService.queryCusCategoryByParentId(groupId, type, parentId,
                pageSize, pageNum);
        CusCateListDto dto = new CusCateListDto();
        dto.setTotal(page.getTotal());
        dto.setPageSize(page.getPageSize());
        dto.setPageNum(page.getPageNum());
        dto.setRows(page.getResult());
        return dto;
    }

}
