package com.uwaytech.course;

import com.github.pagehelper.Page;
import com.uwaytech.JunitTestConfig;
import com.uwaytech.downloadLog.domain.DownloadLog;
import com.uwaytech.httpclient.bo.UserTypeEnum;
import com.uwaytech.schoolCourse.domain.SchoolCourse;
import com.uwaytech.schoolCourse.service.SchoolCourseService;
import com.uwaytech.supplierCourse.ctrl.dto.CategoryInfo;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * SchoolCourseServiceTest
 *
 * @author lyfang
 * @date 2016/6/16
 */
public class SchoolCourseServiceTest extends JunitTestConfig {

    @Resource
    private SchoolCourseService schoolCourseService;

    /**
     * 查询前台学校课程
     */
    @Test
    public void querySchoolCourseList() {
        String name = "java";
        Long categoryId = null;
        Integer type = 1;
        UserTypeEnum userType = UserTypeEnum.TEACHER;
        Integer schoolId = 1;
        Page<SchoolCourse> page =
                schoolCourseService.querySchoolCourseList(name, categoryId, type, userType, schoolId, 1, 10);
        Assert.assertEquals(page != null, true);
        Assert.assertEquals(page.getResult() != null, true);
        for (SchoolCourse schoolCourse : page.getResult()) {
            System.out.println(schoolCourse.toString());
        }
    }

    /**
     * 查询课程分类统计
     */
    @Test
    public void queryCategoryList() {
        String name = "";
        Long categoryId = null;
        UserTypeEnum userType = UserTypeEnum.TEACHER;
        Integer schoolId = 1;
        List<CategoryInfo> list = schoolCourseService.queryCategoryList(name, categoryId, userType, schoolId);
        Assert.assertEquals(list != null, true);
        Assert.assertEquals(list.size() >= 0, true);
        for (CategoryInfo categoryInfo : list) {
            System.out.println(categoryInfo.toString());
        }
    }
}
