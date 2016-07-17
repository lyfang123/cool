package com.uwaytech.supplier.ctrl;

import com.github.pagehelper.Page;
import com.uwaytech.common.exception.ParamErrorException;
import com.uwaytech.common.exception.UnknownException;
import com.uwaytech.common.json.SuccessResult;
import com.uwaytech.finance.ctrl.dto.SupplierFinance;
import com.uwaytech.finance.ctrl.dto.SupplierFinanceDto;
import com.uwaytech.finance.service.FinanceService;
import com.uwaytech.httpclient.SessionUtils;
import com.uwaytech.supplier.ctrl.dto.SupplierDto;
import com.uwaytech.supplier.ctrl.dto.SupplierListDto;
import com.uwaytech.supplier.domain.Supplier;
import com.uwaytech.supplier.service.SupplierService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by moyi on 2016-06-07.
 */
@RestController
@RequestMapping(value = "/supplier")
public class SupplierController {

    @Resource
    private SupplierService supplierService;

    @Resource
    private FinanceService financeService;

    /**
     * 新增供应商
     * @return
     */
    @RequestMapping(value = "/v0.1", method = RequestMethod.POST)
    public Object addSupplier(@RequestBody Supplier supplier) {
        supplierService.insertSupplier(supplier);
        return new SuccessResult();
    }

    /**
     * 查询供应商列表
     * @param name
     * @return
     */
    @RequestMapping(value = "/v0.1/list", method = RequestMethod.GET)
    public Object querySupplierList(@RequestParam(value = "name", required = false) String name,
                                    @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                    @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        Page<Supplier> page = supplierService.querySupplier(name, pageNum, pageSize);
        SupplierListDto dto = new SupplierListDto();
        dto.setTotal(page.getTotal());
        dto.setPageNum(pageNum);
        dto.setPageSize(pageSize);
        List<SupplierDto> list = SupplierDto.toSupplierDtoList(page.getResult());
        dto.setRows(list);
       return dto;
    }

    /**
     * 验证供应商账号是否存在
     * @param account
     * @return
     */
    @RequestMapping(value = "/v0.1/valid", method = RequestMethod.GET)
    public Object checkValid(String account){
        if(supplierService.checkValid(account)){
            //TODO
            throw new UnknownException("账号已经存在");
        }
        return new SuccessResult();
    }

    /**
     * 删除供应商
     * @param id
     * @return
     */
    @RequestMapping(value = "/v0.1/{id}", method = RequestMethod.DELETE)
    public Object deleteSupplier(@PathVariable("id") Long id){
        supplierService.deleteSupplier(id);
        return new SuccessResult();
    }

    /**
     * 更新供应商账号信息
     * @param supplier
     * @return
     */
    @RequestMapping(value = "/v0.1", method = RequestMethod.PUT)
    public Object updateSupplier(@RequestBody Supplier supplier){
        if(supplier.getId() == null && supplier.getId() ==0 ){
            throw new ParamErrorException();
        }
        supplierService.updateSupplier(supplier);
        return new SuccessResult();
    }

    /**
     * 获取供应商详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/v0.1/{id}/detail", method = RequestMethod.GET)
    public Object detailSupplier(@PathVariable("id") Long id){
        return supplierService.getSupplier(id);
    }


    /**
     * 查询供应商下载明细
     * @param pageNum 当前页
     * @param pageSize 每页条数
     * @param resourceName 资源名称
     * @param startTime 查询开始时间
     * @param endTime 查询结束时间
     * @return 返回供应商下载明细
     */
    @RequestMapping(value = "/v0.1/download/statistics", method = RequestMethod.GET)
    public Object querySupplierFinanceList(@RequestParam(value = "resourceName", required = false) String resourceName,
                                           @DateTimeFormat(pattern = "yyyy-MM-dd")
                                           @RequestParam(value = "startTime", required = false) Date startTime,
                                           @DateTimeFormat(pattern = "yyyy-MM-dd")
                                           @RequestParam(value = "endTime", required = false) Date endTime,
                                           @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                           @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        Long supplierId = SessionUtils.getUserId();
        //分页查询供应商下载明细
        Page<SupplierFinance> page = financeService.querySupplierDownloads(supplierId, pageNum, pageSize, resourceName,
                startTime,endTime);
        Long ecoins = financeService.querySupplierDownloadEcoins(supplierId);
        SupplierFinanceDto result = SupplierFinanceDto.supplierDownloadDto(page,ecoins);
        return result;
    }
}
