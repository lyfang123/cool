package com.uwaytech.supplier;

import com.uwaytech.JunitTestConfig;
import com.uwaytech.supplier.domain.Supplier;
import com.uwaytech.supplier.service.SupplierService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by moyi on 2016-06-08.
 */
public class SupplierServiceTest01 extends JunitTestConfig {

    @Autowired
    private SupplierService supplierService;

    @Test
    public void add(){
        Supplier supplier = new Supplier();
        supplier.setAccount("h100");
        supplier.setName("小明");
        supplier.setAccountName("王叔叔");
        supplier.setPhone("110");
        supplier.setAddress("大石坝");
        supplier.setBusinessType("视频资源");
        supplier.setWebUrl("http://baidu.com");
        supplier.setBankName("工商银行");
        supplier.setBankAccount("ICBC100");
        supplier.setContacts("李");
        supplier.seteExchange(1.2);
        supplier.setProportion(0.4);
        int result = supplierService.insertSupplier(supplier);
        Assert.assertEquals(result >= 1,true);
    }

    @Test
    public void delete(){
        int result = supplierService.deleteSupplier(1L);
        Assert.assertEquals(result == 1,true);
    }

    @Test
    public void query(){
        Integer pageNum = 1;
        Integer pageSize = 10;
        List<Supplier> list = supplierService.querySupplier("test", pageNum, pageSize);
        Assert.assertEquals(list.isEmpty(),false);
    }

    @Test
    public void update(){
        Supplier supplier = new Supplier();
        supplier.setId(1L);
        supplier.setName("小王");
        int result = supplierService.updateSupplier(supplier);
        Assert.assertEquals(result == 1,true);
    }
}
