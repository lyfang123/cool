package com.uwaytech.eCoinMoney;

import com.uwaytech.JunitTestConfig;
import com.uwaytech.eCoinMoney.domain.ECoinMoney;
import com.uwaytech.eCoinMoney.service.ECoinMoneyService;
import com.uwaytech.id.service.IdGeneratorService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * ECoinMoneyServiceTest
 *
 * @author lyfang
 * @date 2016/6/7
 */
public class ECoinMoneyServiceTest extends JunitTestConfig {

	@Autowired
	private ECoinMoneyService eCoinMoneyService;

	@Resource
	private IdGeneratorService idGeneratorService;

	/**
	 * 添加
	 */
	@Test
	@Rollback(false)
	public void add() {
		ECoinMoney eCoinMoney = new ECoinMoney();
		eCoinMoney.seteCoin(new Long(1000));
		eCoinMoney.setRmb(new BigDecimal(1000));
		long id = idGeneratorService.generatorId(1);
		eCoinMoney.setId(id);
		Integer result = eCoinMoneyService.addECoinMoney(eCoinMoney);
		Assert.assertEquals(result == 1, true);
	}

	/**
	 * 查询
	 */
	@Test
	public void queryList() {
		List<ECoinMoney> list = eCoinMoneyService.queryECoinMoney(1, 10);
		Assert.assertEquals(null != list, true);
		Assert.assertEquals(list.size() >= 0, true);
		for (ECoinMoney eCoinMoney : list) {
			System.out.println(eCoinMoney.toString());
		}
	}

	/**
	 * 删除
	 */
	@Test
	@Rollback(false)
	public void delete() {
		Integer result = eCoinMoneyService.deleteECoinMoney(2313381707839848L);
		Assert.assertEquals(result == 1, true);
	}
}
