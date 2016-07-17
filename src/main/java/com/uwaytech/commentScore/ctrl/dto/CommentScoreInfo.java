package com.uwaytech.commentScore.ctrl.dto;

import java.util.Date;

/**
 * CommentScoreInfo
 *
 * @author lyfang
 * @date 2016/6/13
 */
public class CommentScoreInfo {
	private String comments;

	private Double score;

	private Date createTime;

	private String userName;

	private String photo;

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
}
