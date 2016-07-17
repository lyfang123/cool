package com.uwaytech.supplierCourse.ctrl.dto;

import java.util.Date;

/**
 * SupplierCourseInfo
 *
 * @author lyfang
 * @date 2016/6/15
 */
public class SupplierCourseInfo {

	private String courseId;

	private String courseName;

	private String coursePic;

	private String courseDesc;

	private Integer viewNum;

	private String courseTime;

	private Integer chapterNum;

	private Integer collectionNum;

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCoursePic() {
		return coursePic;
	}

	public void setCoursePic(String coursePic) {
		this.coursePic = coursePic;
	}

	public String getCourseDesc() {
		return courseDesc;
	}

	public void setCourseDesc(String courseDesc) {
		this.courseDesc = courseDesc;
	}

	public Integer getViewNum() {
		return viewNum;
	}

	public void setViewNum(Integer viewNum) {
		this.viewNum = viewNum;
	}

	public String getCourseTime() {
		return courseTime;
	}

	public void setCourseTime(String courseTime) {
		this.courseTime = courseTime;
	}

	public Integer getChapterNum() {
		return chapterNum;
	}

	public void setChapterNum(Integer chapterNum) {
		this.chapterNum = chapterNum;
	}

	public Integer getCollectionNum() {
		return collectionNum;
	}

	public void setCollectionNum(Integer collectionNum) {
		this.collectionNum = collectionNum;
	}
}
