package com.uwaytech.collection;

import com.github.pagehelper.Page;
import com.uwaytech.JunitTestConfig;
import com.uwaytech.collection.domain.CollectionInfo;
import com.uwaytech.collection.service.ICollectionService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;

/**
 * Created by zeng on 2016/6/13.
 */
public class CollectionServiceTest extends JunitTestConfig {
    @Resource
    private ICollectionService collectionService;

    @Test
    @Rollback(false)
    public void add(){
        Long userId = 1L;
        Long cellId = 61581810043352L;
        Integer type = 1;
        int result = collectionService.addCollection(userId, cellId, type);
        Assert.assertEquals(result == 1, true);
    }

    @Test
    public void query() {
        Long userId = 1L;
        Integer type = 1;
        Integer pageNum = 1;
        Integer pageSize = 10;
        Page<CollectionInfo> page = collectionService.getCollection(userId, type, pageNum, pageSize);
        Assert.assertEquals(page.getResult().size()>0,true);
        for (CollectionInfo collectionInfo : page.getResult()) {
            System.out.println(collectionInfo.getCellName());
        }
    }

    @Test
    public void delete() {
        Long id = 1L;
        Long userId = 1L;
        int result = collectionService.deleteCollection(id, userId);
        Assert.assertEquals(result == 1, true);
    }

}
