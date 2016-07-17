package com.uwaytech.cool.common.enums;

import com.uwaytech.common.enums.EnumInterface;

/**
 * Created by zeng on 2016/6/6.
 */
public enum SchoolCourseEnum implements EnumInterface {
    STUDENT(1,"全体学生"),
    TEACHER(2,"全体教师");

    private Integer id;

    private String name;

    private SchoolCourseEnum(Integer id,String name){
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public static SchoolCourseEnum valueOf(Integer type) {
        for (SchoolCourseEnum e : SchoolCourseEnum.values()) {
            if (e.getId() == type) {
                return e;
            }
        }
        return null;
    }
}
