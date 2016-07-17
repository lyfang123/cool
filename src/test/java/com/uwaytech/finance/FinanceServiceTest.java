package com.uwaytech.finance;

import com.github.pagehelper.Page;
import com.uwaytech.JunitTestConfig;
import com.uwaytech.finance.ctrl.dto.FinanceConsume;
import com.uwaytech.finance.ctrl.dto.SchoolFinance;
import com.uwaytech.finance.ctrl.dto.SchoolFinanceDto;
import com.uwaytech.finance.ctrl.dto.SupplierFinance;
import com.uwaytech.finance.domain.FinanceResult;
import com.uwaytech.finance.domain.FinanceSupplier;
import com.uwaytech.finance.service.FinanceService;
import com.uwaytech.separate.domain.Separate;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;

/**
 * FinanceServiceTest
 *
 * @author lyfang
 * @date 2016/6/6
 */
public class FinanceServiceTest extends JunitTestConfig {

	@Autowired
	private FinanceService financeService;

	/**
	 * 查询学校账号列表
	 */
	@Test
	public void querySchoolList() {
		SchoolFinanceDto financeDto = financeService.querySchoolList(null, 1, 10, "27a94f540c9b9f666ccea2893b4e8708");
		Assert.assertEquals(financeDto != null, true);
		Assert.assertEquals(financeDto.getRows() != null, true);
		Assert.assertEquals(financeDto.getRows().size() >= 0, true);
		for (SchoolFinance finance : financeDto.getRows()) {
			System.out.println(finance.toString());
		}
	}

	/**
	 * 资源下载交易
	 */
	@Test
	public void pay() {
		Integer result = financeService.payResourceDownloadECoin(1L, 1, 1L, "lisi", "ceshi");
		Assert.assertEquals(result == 1, true);
	}

	/**
	 * 查询学校账号信息
	 */
	@Test
	public void queryFinanceInfo() {
		FinanceResult result = financeService.queryFinanceInfos(1);
		Assert.assertEquals(result != null, true);
		System.out.println(result.toString());
	}

	/**
	 * 查询学校资源下载月份统计
	 */
	@Test
	public void queryFinanceConsumes() {
		Page<FinanceConsume> page = financeService.queryFinanceConsumes(1, 1, 10);
		Assert.assertEquals(page != null, true);
		Assert.assertEquals(page.getResult() != null, true);
		Assert.assertEquals(page.getResult().size() >= 0, true);
		for (FinanceConsume financeConsume : page.getResult()) {
			System.out.println(financeConsume.toString());
		}
	}

	/**
	 * 查询供应商資源下载明细
	 */
	@Test
	public void querySupplierDownloads() {
		Date endTime = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(endTime);
		cal.add(Calendar.MONTH, -1);
		Date startTime = cal.getTime();
		Page<SupplierFinance> page = financeService.querySupplierDownloads(0L, 1, 10, null, startTime, endTime);
		Assert.assertEquals(page != null, true);
		Assert.assertEquals(page.getResult() != null, true);
		Assert.assertEquals(page.getResult().size() >= 0, true);
		for (SupplierFinance supplierFinance : page.getResult()) {
			System.out.println(supplierFinance.toString());
		}
	}

	/**
	 * 查询供应商账号信息
	 */
	@Test
	public void querySupplierFinanceInfos() {
		FinanceSupplier result = financeService.querySupplierFinanceInfos(0L);
		Assert.assertEquals(result != null, true);
		System.out.println(result.toString());
	}
}
