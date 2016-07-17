package com.uwaytech.eCoinMoney.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.uwaytech.eCoinMoney.dao.ECoinMoneyMapper;
import com.uwaytech.eCoinMoney.domain.ECoinMoney;
import com.uwaytech.eCoinMoney.service.ECoinMoneyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * ECoinMoneyServiceImpl
 * e币兑换规则service实现类
 *
 * @author lyfang
 * @date 2016/6/7
 */

@Service
public class ECoinMoneyServiceImpl implements ECoinMoneyService {

	@Resource
	private ECoinMoneyMapper eCoinMoneyMapper;

	@Override
	public Integer addECoinMoney(ECoinMoney eCoinMoney) {
		return eCoinMoneyMapper.insert(eCoinMoney);
	}

	@Override
	public Integer deleteECoinMoney(Long id) {
		return eCoinMoneyMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Page<ECoinMoney> queryECoinMoney(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return (Page<ECoinMoney>)eCoinMoneyMapper.selectByExample(null);
	}
}
