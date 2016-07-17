package com.uwaytech.commentScore.ctrl.dto;

import com.github.pagehelper.Page;
import com.uwaytech.commentScore.domain.CommentScore;
import com.uwaytech.downloadLog.ctrl.dto.ResourceDownload;
import com.uwaytech.downloadLog.domain.DownloadLog;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * CommentScoreDto
 *
 * @author lyfang
 * @date 2016/6/13
 */
public class CommentScoreDto {
	private long total;

	private Integer pageNum;

	private Integer pageSize;

	private Double avgScore;

	private List<Object> rows;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List<Object> getRows() {
		return rows;
	}

	public void setRows(List<Object> rows) {
		this.rows = rows;
	}

	public Double getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(Double avgScore) {
		this.avgScore = avgScore;
	}

	public static CommentScoreDto commentScoreDto(Page<CommentScore> page, Double avgScore) {
		CommentScoreDto dto = new CommentScoreDto();
		//保留一位小数
		BigDecimal bg = new BigDecimal(avgScore);
		avgScore = bg.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
		dto.setAvgScore(avgScore);
		if (null != page) {
			dto.setTotal(new Long(page.getTotal()));
			dto.setPageSize(page.getPageSize());
			dto.setPageNum(page.getPageNum());
			if (null != page.getResult() && page.getResult().size() > 0) {
				List<Object> list = new ArrayList<Object>();
				for (CommentScore commentScore : page.getResult()) {
					CommentScoreInfo commentScoreInfo = new CommentScoreInfo();
					commentScoreInfo.setComments(commentScore.getComments());
					commentScoreInfo.setScore(commentScore.getScore());
					commentScoreInfo.setCreateTime(commentScore.getCreateTime());
					commentScoreInfo.setPhoto(commentScore.getPhoto());
					commentScoreInfo.setUserName(commentScore.getUserName());
					list.add(commentScoreInfo);
				}
				dto.setRows(list);
			}
		}
		return dto;
	}

	public static Object commentDto(Page<CommentScore> page) {
		CommentScoreDto dto = new CommentScoreDto();
		if (null != page) {
			dto.setTotal(new Long(page.getTotal()));
			dto.setPageSize(page.getPageSize());
			dto.setPageNum(page.getPageNum());
			List<Object> list = new ArrayList<Object>();
			for (CommentScore commentScore : page.getResult()) {
				list.add(commentScore);
			}
			dto.setRows(list);
		}
		return dto;
	}
}
