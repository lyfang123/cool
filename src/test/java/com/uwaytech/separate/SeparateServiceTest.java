package com.uwaytech.separate;

import com.github.pagehelper.Page;
import com.uwaytech.JunitTestConfig;
import com.uwaytech.finance.ctrl.dto.SchoolFinance;
import com.uwaytech.separate.domain.Separate;
import com.uwaytech.separate.service.SeparateService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;

/**
 * SeparateServiceTest
 *
 * @author lyfang
 * @date 2016/6/8
 */
public class SeparateServiceTest extends JunitTestConfig {

	@Autowired
	private SeparateService separateService;

	/**
	 * 查询供应商分账记录列表
	 */
	@Test
	public void querySeparateList() {
		Long supplierId = 1L;
		Date endTime = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(endTime);
		cal.add(Calendar.MONTH, -1);
		Date startTime = cal.getTime();
		Page<Separate> page = separateService.querySeparateList(supplierId, startTime, endTime, 1, 10);
		Assert.assertEquals(page != null, true);
		Assert.assertEquals(page.getResult() != null, true);
		Assert.assertEquals(page.getResult().size() >= 0, true);
		for (Separate separate : page.getResult()) {
			System.out.println(separate.toString());
		}
	}

}
