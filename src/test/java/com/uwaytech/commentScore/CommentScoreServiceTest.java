package com.uwaytech.commentScore;

import com.github.pagehelper.Page;
import com.uwaytech.JunitTestConfig;
import com.uwaytech.commentScore.domain.CommentScore;
import com.uwaytech.commentScore.service.CommentScoreService;
import com.uwaytech.downloadLog.domain.DownloadLog;
import com.uwaytech.downloadLog.service.DownloadLogService;
import com.uwaytech.id.service.IdGeneratorService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;
import java.util.Date;

/**
 * CommentScoreServiceTest
 *
 * @author lyfang
 * @date 2016/6/13
 */
public class CommentScoreServiceTest extends JunitTestConfig {

	@Autowired
	private CommentScoreService commentScoreService;

	@Resource
	private IdGeneratorService idGeneratorService;

	/**
	 * 添加评论
	 */
	@Test
	@Rollback(false)
	public void addCommentScore() {
		CommentScore commentScore = new CommentScore();
		commentScore.setScore(3.5D);
		commentScore.setComments("资源挺不错的");
		commentScore.setUserId(2L);
		commentScore.setMaterialId(1L);
		long id = idGeneratorService.generatorId(1);
		commentScore.setId(id);
		Integer result = commentScoreService.addCommentScore(commentScore);
		Assert.assertEquals(1 == result, true);
		System.out.println("插入结果：" + result);
	}

	/**
	 * 查询评论
	 */
	@Test
	public void queryDownloadList() {
		Long materialId = 1L;
		Page<CommentScore> page = commentScoreService.queryCommentScores(materialId, 1, 10);
		Assert.assertEquals(page != null, true);
		Assert.assertEquals(page.getResult() != null, true);
		for (CommentScore commentScore : page.getResult()) {
			System.out.println(commentScore.toString());
		}
	}

	/**
	 * 查询评论平均分
	 */
	@Test
	public void queryAvgScore() {
		Long materialId = 1L;
		Double avgScore = commentScoreService.queryAvgScore(materialId);
		Assert.assertEquals(avgScore != null, true);
		System.out.println("评论平均分： " + avgScore);
	}
}
