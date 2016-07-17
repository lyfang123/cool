package com.uwaytech.cusCategory;

import com.uwaytech.JunitTestConfig;
import com.uwaytech.cusCategory.domain.CusCategory;
import com.uwaytech.cusCategory.service.CusCategoryService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.List;

/**
 * Created by moyi on 2016-06-02.
 */
public class CusCategoryServiceTest01 extends JunitTestConfig {

    @Autowired
    private CusCategoryService cusCategoryService;

    @Test
    public void add(){
        String name = "小白兔";
        Integer type = 1;
        Long group = 1L;
        Long parentId = 1L;
        int result = cusCategoryService.addCusCategory(name,type,group,parentId);
        Assert.assertEquals(result == 1 ,true);
    }

    @Test
    public void query(){
        List<CusCategory> cusCategory =cusCategoryService.queryCusCategory(1,1L);
        Assert.assertEquals(cusCategory.size() > 0 ,true);
        for (CusCategory cusCategory1 : cusCategory){
            System.out.println(cusCategory1.getName());
        }
    }

    @Test
    public void update(){
        CusCategory cusCategory1 = new CusCategory();
        cusCategory1.setName("小灰兔");
        cusCategory1.setId(1L);
        int result = cusCategoryService.updateCusCategory(1, new CusCategory[]{cusCategory1});
        Assert.assertEquals(result == 1 , true);
    }

    @Test
    public void delete(){
        int result = cusCategoryService.deleteCusCategory(1L,new Byte("1"),1);
        Assert.assertEquals(result == 1,true);
    }
}
