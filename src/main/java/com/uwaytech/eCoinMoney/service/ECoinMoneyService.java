package com.uwaytech.eCoinMoney.service;

import com.github.pagehelper.Page;
import com.uwaytech.eCoinMoney.domain.ECoinMoney;

import java.util.List;

/**
 * Created by rtyui on 2016/6/7.
 */
public interface ECoinMoneyService {
	Integer addECoinMoney(ECoinMoney eCoinMoney);

	Integer deleteECoinMoney(Long id);

	Page<ECoinMoney> queryECoinMoney(Integer pageNum, Integer pageSize);
}
