package com.uwaytech.resource;

import com.uwaytech.JunitTestConfig;
import com.uwaytech.cool.common.constant.Constant;
import com.uwaytech.id.service.IdGeneratorService;
import com.uwaytech.schoolCourse.domain.MaterialEntity;
import com.uwaytech.schoolResource.domain.ResourceInfo;
import com.uwaytech.schoolResource.domain.ResourceTermInfo;
import com.uwaytech.schoolResource.domain.SupplierResourceDetail;
import com.uwaytech.schoolResource.service.IResourceService;
import com.uwaytech.supplierResource.domain.SupplierResource;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;

/**
 * Created by zeng on 2016/6/3.
 */
public class ResourceServiceTest01 extends JunitTestConfig {

    @Resource
    private IResourceService resourceService;
    @Resource
    private IdGeneratorService idGeneratorService;

    @Test
    @Rollback(false)
    public void add(){
        SupplierResource resource = new SupplierResource();
        resource.seteCoin(1L);
        resource.setName("测试资源16");
        resource.setStatus(2);
        resource.setCheckStatus(1);
        resource.setCategoryId(6L);
        resource.setImgUrl("192.168.1.100");
        resource.setMediaType(1L);
        resource.setDescription("汽修是...");
        resource.setType(1);
        resource.setUseType(1L);
        resource.setMaterialId(61581803932400L);
        resource.setAutoType(1);
        Byte type = 7;
        Long id = idGeneratorService.generatorId(Constant.SUPPLIER_NUMBER);
        Long grouping =1L;
        MaterialEntity materialEntity = new MaterialEntity();
        int result = resourceService.insertSupplierResource(resource, id, grouping, materialEntity);
        Assert.assertEquals(result == 1, true);
    }

    @Test
    public void update(){
        SupplierResource resource = new SupplierResource();
        resource.setId(61581810043352L);
        resource.setStatus(2);
        int result = resourceService.shelveResource(61581810043352L, 2);
        Assert.assertEquals(result == 1, true);
    }

    @Test
    public void findResourceList(){
        ResourceTermInfo term = new ResourceTermInfo();
        term.setGroupId(0L);
        term.setCategoryId(6L);
        term.setStatus(2);
        term.setCheckStatus(2);
        Integer pageNum = 1;
        Integer pageSize = 10;
        Byte type =7;
        ResourceInfo resourceInfo = resourceService.findResource(term, type, pageNum,pageSize);
        Assert.assertEquals(resourceInfo.getRows().size()>0,true);
//        for(SupplierResourceDetail resourceDetail : resourceInfo.getRows()){
//            System.out.println(resourceDetail.getName());
//        }
    }

    @Test
    public void findResourceDetail(){
        Long resourceId = 61581810043352L;
        Byte type =7;
        SupplierResourceDetail resourceDetail = resourceService.findResourceDetail(resourceId, type);
        System.out.println(resourceDetail.getName());
    }
}
