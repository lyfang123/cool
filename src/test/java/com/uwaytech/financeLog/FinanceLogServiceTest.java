package com.uwaytech.financeLog;

import com.github.pagehelper.Page;
import com.uwaytech.JunitTestConfig;
import com.uwaytech.financeLog.domain.FinanceLog;
import com.uwaytech.cool.common.enums.PayTypeEnum;
import com.uwaytech.financeLog.service.FinanceLogService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;
import java.util.Date;

/**
 * FinanceServiceTest
 *
 * @author lyfang
 * @date 2016/6/6
 */
public class FinanceLogServiceTest extends JunitTestConfig {

	@Autowired
	private FinanceLogService financeLogService;

	/**
	 * 添加学校入账
	 */
	@Test
	@Rollback(false)
	public void addFinanceLog() {
		FinanceLog financeLog = new FinanceLog();
		financeLog.setCreateTime(new Date());
		financeLog.setDealDate(new Date());
		financeLog.setType(1);
		financeLog.setBankNumber("c01");
		financeLog.seteCoin(new Long(1000));
		financeLog.setMoney(new BigDecimal(1000));
		financeLog.setOrderCode("c0001");
		financeLog.setCreateUser(1L);
		financeLog.setPayType(PayTypeEnum.TRANSFER_ACCOUNTS.getId());
		financeLog.setId(111L);
		Integer result = financeLogService.addFinanceLog(new Long(1), financeLog);
		Assert.assertEquals(result == 1, true);
	}

	/**
	 * 查询学校入账记录
	 */
	@Test
	public void queryFinanceLogList() {
		Long schoolId = null;
		Date startTime = null;
		Date endTime = null;
		Page<FinanceLog> financeList = financeLogService.queryFinanceLogList(schoolId, 1, 10,
				startTime, endTime);
		Assert.assertEquals(financeList != null, true);
		Assert.assertEquals(financeList.getResult() != null, true);
		for (FinanceLog log : financeList.getResult()) {
			System.out.println(log.toString());
		}
	}

	/**
	 * 查询学校入账总额
	 */
	@Test
	public void queryFinanceLogEcoins() {
		Long schoolId = null;
		Date startTime = null;
		Date endTime = null;
		Long ecoins = financeLogService.queryFinanceLogEcoins(schoolId,
				startTime, endTime);
		Assert.assertEquals(ecoins != null, true);
		System.out.println("ecoins = " + ecoins);
	}

}
