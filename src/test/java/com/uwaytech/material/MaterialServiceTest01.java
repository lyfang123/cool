package com.uwaytech.material;

import com.github.pagehelper.Page;
import com.uwaytech.JunitTestConfig;
import com.uwaytech.material.domain.Material;
import com.uwaytech.material.service.IMaterialService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zeng on 2016-06-03.
 */
public class MaterialServiceTest01 extends JunitTestConfig {

    @Resource
    private IMaterialService materialService;

    @Test
    @Rollback(false)
    public void add(){
        Material material = new Material();
        material.setId(1L);
        material.setName("测试素材");
        material.setSize("1MB");
        material.setMediaType("word");
        material.setLength(1);
        material.setStatus((byte) 1);
        material.setType(1);
        material.setUrl("/192.168.1.100");
        material.setGrouping(1L);
        Material material1 = new Material();
        material1.setId(2L);
        material1.setName("测试素材");
        material1.setSize("1MB");
        material1.setMediaType("word");
        material1.setLength(1);
        material1.setStatus((byte) 1);
        material1.setType(1);
        material1.setUrl("/192.168.1.100");
        material1.setGrouping(1L);
        List<Material> list = new ArrayList<>();
        list.add(material);
        list.add(material1);
        int result = materialService.insertMaterial(list);
        Assert.assertEquals(result == 1 ,true);
    }

    @Test
    public void update(){
        Material material = new Material();
        material.setId(61581803932400L);
        material.setName("素材修改");
        int result = materialService.updateMaterial(material);
        Assert.assertEquals(result == 1, true);
    }

    @Test
    public void findMaterials(){
        Integer type =1;
        Long grouping = 1L;
        Long categoryId = 1L;
        Integer pageNum =1;
        Integer pageSize = 9;
        Page<Material> page = materialService.findMaterials(type,categoryId, grouping, pageNum,pageSize);
        Assert.assertEquals(page.size()>0,true);
        for(Material material:page.getResult()){
            System.out.println(material.getName());
        }
    }

}