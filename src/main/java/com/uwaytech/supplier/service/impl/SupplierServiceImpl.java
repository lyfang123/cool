package com.uwaytech.supplier.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.uwaytech.common.exception.InternalException;
import com.uwaytech.common.json.IdResult;
import com.uwaytech.cool.common.constant.Constant;
import com.uwaytech.cool.common.enums.FinanceType;
import com.uwaytech.cool.common.enums.StatusEnum;
import com.uwaytech.finance.dao.FinanceMapper;
import com.uwaytech.finance.domain.Finance;
import com.uwaytech.finance.domain.FinanceExample;
import com.uwaytech.httpclient.SessionUtils;
import com.uwaytech.httpclient.UserCenterSynProxy;
import com.uwaytech.id.service.IdGeneratorService;
import com.uwaytech.supplier.dao.SupplierMapper;
import com.uwaytech.supplier.domain.Supplier;
import com.uwaytech.supplier.domain.SupplierExample;
import com.uwaytech.supplier.service.SupplierService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by moyi on 2016-06-07.
 */
@Service
public class SupplierServiceImpl implements SupplierService {

    @Resource
    SupplierMapper supplierMapper;

    @Resource
    FinanceMapper financeMapper;

    @Resource
    IdGeneratorService idGeneratorService;


    public IdResult createUcUser(String account, String password, Integer schoolId, String extend) {
        UserCenterSynProxy proxy = new UserCenterSynProxy();
        IdResult result = proxy.register(account, password, schoolId, extend);
        return result;
    }


    @Override
    public int insertSupplier(Supplier supplier) {
        String account = supplier.getAccount();
        //创建UC用户
        IdResult id = createUcUser(account, "123456", null, Constant.SUPPLIER_EXTEND);
        if(null == id){
            throw new InternalException("UC 创建用户失败");
        }
        int result = 0;
        supplier.setId(Long.valueOf(id.getDataId()));
        supplier.setAccount(account);
        supplier.setCreateTime(new Date());
        supplier.setStatus(StatusEnum.Valid.getValue());
        result = supplierMapper.insert(supplier);
        //TODO
        Finance finance = new Finance();
        finance.setCreateTime(new Date());
        finance.setGrouping(Long.valueOf(id.getDataId()));
        finance.setStatus(StatusEnum.Valid.getValue());
        finance.setType(FinanceType.SupplierType.getValue());
        result += financeMapper.insertSelective(finance);
        return result;
    }

    @Override
    public int deleteSupplier(Long id) {
        //TODO 删除UC上的帐户

        //修改供应商账户状态为不可用
        Finance finance = new Finance();
        finance.setStatus(StatusEnum.Invalid.getValue());
        FinanceExample example = new FinanceExample();
        example.or().andGroupingEqualTo(id);
        financeMapper.updateByExampleSelective(finance, example);
        //TODO
        //修改供应商状态为可用
        Supplier supplier= new Supplier();
        supplier.setId(id);
        supplier.setStatus(StatusEnum.Invalid.getValue());
        int result = supplierMapper.updateByPrimaryKeySelective(supplier);
        return result;
    }

    @Override
    public Supplier getSupplier(long id) {
        return supplierMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<Supplier> querySupplier(String name, Integer pageNum, Integer pageSize) {
        SupplierExample example = new SupplierExample();
        if (!StringUtils.isBlank(name)) {
            example.or().andNameLike("%"+name+"%").andStatusEqualTo(StatusEnum.Valid.getValue());
        } else {
            example.or().andStatusEqualTo(StatusEnum.Valid.getValue());
        }
        PageHelper.startPage(pageNum, pageSize);
        Page<Supplier> page = (Page<Supplier>)supplierMapper.selectByExample(example);
        return page;
    }

    @Override
    public int updateSupplier(Supplier supplier) {
        return supplierMapper.updateByPrimaryKeySelective(supplier);
    }

    @Override
    public boolean checkValid(String account) {
        SupplierExample example = new SupplierExample();
        example.or().andAccountEqualTo(account);
        List<Supplier> list = supplierMapper.selectByExample(example);
        return list.size() > 0;
    }
}
