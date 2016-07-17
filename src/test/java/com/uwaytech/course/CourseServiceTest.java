package com.uwaytech.course;

import com.uwaytech.JunitTestConfig;
import com.uwaytech.cool.common.constant.Constant;
import com.uwaytech.id.service.IdGeneratorService;
import com.uwaytech.schoolCourse.domain.*;
import com.uwaytech.schoolCourse.service.ICourseService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zeng on 2016/6/7.
 */
public class CourseServiceTest extends JunitTestConfig {

    @Resource
    private ICourseService courseService;
    @Resource
    private IdGeneratorService idGeneratorService;

    @Test
    @Rollback(false)
    public void add(){
        CourseInfo courseInfo = new CourseInfo();
        courseInfo.setName("测试课程16");
        courseInfo.setImgUrl("/192.168.1.100");
        courseInfo.setEndTime(new Date());
        courseInfo.setDescription("测试供应商课程");
        MaterialEntity material1 = new MaterialEntity();
        material1.setMaterialId(61581803932400L);
        material1.setType(1);
        MaterialEntity material2 = new MaterialEntity();
        material2.setMaterialId(2313385093122056L);
        material2.setType(2);
        List<MaterialEntity> list = new ArrayList<>();
        list.add(material1);
        list.add(material2);
        Byte type =7;
        Long  id = idGeneratorService.generatorId(Constant.SUPPLIER_NUMBER);
        Long grouping = 1L;
        int result = courseService.addCourse(courseInfo, type, id, grouping);
        Assert.assertEquals(result == 0, true);
    }

    @Test
    public void update(){
        CourseInfo courseInfo = new CourseInfo();
        courseInfo.setId(2313387195497424L);
        courseInfo.setName("测试课程修改");
        courseInfo.setImgUrl("/192.168.1.100");
        courseInfo.setEndTime(new Date());
        courseInfo.setDescription("测试供应商课程");
        MaterialEntity material = new MaterialEntity();
        material.setMaterialId(61581803932400L);
        material.setType(1);
        List<MaterialEntity> list = new ArrayList<>();
        list.add(material);
        Byte type =7;
        int result = courseService.updateCourse(courseInfo, type);
        Assert.assertEquals(result == 1, true);
    }

    @Test
    public void findCourseDetail(){
        Long id = 2251820010823160L;
        Byte type =6;
        CourseInfo course = courseService.findCourseDetail(id, type);
        System.out.println(course.getName());
    }

    @Test
    public void list(){
        CourseTermInfo term = new CourseTermInfo();
        Integer pageNum = 1;
        Integer pageSize = 9;
        Byte userType =7;
        CourseListInfo course = courseService.findCourseList(term, userType, pageNum,pageSize);
        Assert.assertEquals(course.getRows().size()>0,true);
        for (CourseDetail courseDetail : course.getRows()) {
            System.out.println(courseDetail.getName());
        }
    }
}
