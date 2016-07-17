package com.uwaytech.downloadLog;

import com.github.pagehelper.Page;
import com.uwaytech.JunitTestConfig;
import com.uwaytech.downloadLog.domain.DownloadLog;
import com.uwaytech.downloadLog.service.DownloadLogService;
import com.uwaytech.finance.ctrl.dto.SchoolFinanceDto;
import com.uwaytech.financeLog.domain.FinanceLog;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * DownloadLogServiceTest
 *
 * @author lyfang
 * @date 2016/6/6
 */
public class DownloadLogServiceTest extends JunitTestConfig {

	@Autowired
	private DownloadLogService downloadLogService;


	/**
	 * 查询下载E币总额
	 */
	@Test
	public void queryDownloadEcoins() {
		Long schoolId = null;
		Date startTime = null;
		Date endTime = null;
		String keyWord = "";
		Long ecoins = downloadLogService.queryDownloadEcoins(schoolId,
				startTime, endTime, keyWord);
		System.out.println("ecoins =" + ecoins);
		Assert.assertEquals(null != ecoins, true);
	}

	/**
	 * 查询下载记录
	 */
	@Test
	public void queryDownloadList() {
		Long schoolId = null;
		Date startTime = null;
		Date endTime = null;
		String keyWord = "";
		Page<DownloadLog> downloadList = downloadLogService.queryDownloadList(schoolId, 1, 10,
				startTime, endTime, keyWord);
		Assert.assertEquals(downloadList != null, true);
		Assert.assertEquals(downloadList.getResult() != null, true);
		for (DownloadLog log : downloadList.getResult()) {
			System.out.println(log.toString());
		}
	}
}
