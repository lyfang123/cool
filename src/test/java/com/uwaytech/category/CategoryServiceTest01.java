package com.uwaytech.category;

import com.uwaytech.JunitTestConfig;
import com.uwaytech.category.domain.Category;
import com.uwaytech.category.service.CategoryService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.List;

/**
 * Created by moyi on 2016-06-01.
 */
public class CategoryServiceTest01 extends JunitTestConfig {


    @Autowired
    private CategoryService categoryService;


    @Test
    public void add(){
        String name = "专业技能";
        Long parentId = 0L;
        Integer level = 1;
        int result = categoryService.addCategory(name,parentId,level);
        Assert.assertEquals(result == 1,true);

    }

    @Test
    public void delete() {
        int result = categoryService.deleteCategory(2L);
        Assert.assertEquals(result == 1, true);
    }

    @Test
    public void update() {
        List<Category> list = categoryService.queryCategory(1);
        Assert.assertEquals(list.size() > 0, true);
        Category cate = list.get(0);
        cate.setDescription("更新");
        categoryService.updateCategory(new Category[]{cate});
    }

    @Test
    public void query() {
        List<Category> list = categoryService.queryCategory(1);
        Assert.assertEquals(list.size() > 0, true);
        for (Category cate : list) {
            System.out.println(cate.getName() + "\t" + cate.getDescription());
        }
    }

}
