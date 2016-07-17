package com.uwaytech.financeLog.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.uwaytech.cool.common.enums.StatusEnum;
import com.uwaytech.finance.dao.FinanceMapper;
import com.uwaytech.finance.domain.Finance;
import com.uwaytech.finance.domain.FinanceExample;
import com.uwaytech.financeLog.dao.ExtendFinanceLogMapper;
import com.uwaytech.financeLog.dao.FinanceLogMapper;
import com.uwaytech.financeLog.domain.FinanceLog;
import com.uwaytech.financeLog.domain.FinanceLogParam;
import com.uwaytech.cool.common.enums.FinanceLogTypeEnum;
import com.uwaytech.cool.common.enums.FinanceTypeEnum;
import com.uwaytech.financeLog.service.FinanceLogService;
import com.uwaytech.id.service.IdGeneratorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * FinanceLogServiceImpl
 *
 * @author lyfang
 * @date 2016/6/6
 */

@Service
public class FinanceLogServiceImpl implements FinanceLogService {

	@Resource
	private ExtendFinanceLogMapper extendFinanceLogMapper;

	@Resource
	private FinanceMapper financeMapper;

	@Resource
	private FinanceLogMapper financeLogMapper;

	@Resource
	private IdGeneratorService idGeneratorService;

	/**
	 * 查询学校入账记录
	 *
	 * @param schoolId  学校ID
	 * @param pageNum   当前页
	 * @param pageSize  每页条数
	 * @param startTime 查询开始时间
	 * @param endTime   查询结束时间
	 * @return 返回查询结果
	 */
	@Override
	public Page<FinanceLog> queryFinanceLogList(Long schoolId, Integer pageNum, Integer pageSize, Date startTime, Date endTime) {
		PageHelper.startPage(pageNum, pageSize);
		FinanceLogParam param = new FinanceLogParam();
		param.setStartTime(startTime);
		param.setEndTime(endTime);
		param.setSchoolId(schoolId);
		param.setType(FinanceLogTypeEnum.RECHARGE.getId().byteValue());
		Page<FinanceLog> page = (Page<FinanceLog>) extendFinanceLogMapper.queryFinanceLogList(param);
		return page;
	}

	/**
	 * 查询学校入账总额
	 *
	 * @param schoolId  学校ID
	 * @param startTime 查询开始时间
	 * @param endTime   查询结束时间
	 * @return 返回查询结果
	 */
	@Override
	public Long queryFinanceLogEcoins(Long schoolId, Date startTime, Date endTime) {
		FinanceLogParam param = new FinanceLogParam();
		param.setStartTime(startTime);
		param.setEndTime(endTime);
		param.setSchoolId(schoolId);
		param.setType(FinanceLogTypeEnum.RECHARGE.getId().byteValue());
		Long ecoins = extendFinanceLogMapper.queryFinanceLogEcoins(param);
		return ecoins;
	}

	/**
	 * 添加学校入账
	 *
	 * @param schoolId   学校ID
	 * @param financeLog 充值记录实体
	 */
	@Override
	public Integer addFinanceLog(Long schoolId, FinanceLog financeLog) {
		//判断学校账号是否已经存在
		FinanceExample financeExample = new FinanceExample();
		financeExample.createCriteria().andGroupingEqualTo(schoolId);
		List<Finance> finances = financeMapper.selectByExample(financeExample);
		Finance finance = null;
		if (null != finances && finances.size() > 0) {
			finance = finances.get(0);
		}
		//账号不存在，则添加学校账号，同时插入充值金额
		if (null == finance) {
			long financeId = idGeneratorService.generatorId(schoolId.intValue());
			finance = new Finance();
			finance.setId(financeId);
			finance.setCreateTime(new Date());
			finance.setGrouping(schoolId);
			finance.setRmbAmounts(financeLog.getMoney());
			finance.seteBalance(financeLog.geteCoin());
			finance.seteAmounts(financeLog.geteCoin());
			finance.setType(FinanceTypeEnum.SCHOOL_ACCOUNTS.getId());
			finance.setStatus(StatusEnum.Valid.getValue());
			financeMapper.insertSelective(finance);
		} else {
			//账号已经存在，更新账号金额
			finance.seteAmounts(finance.geteAmounts().longValue() + financeLog.geteCoin().longValue());
			finance.seteBalance(finance.geteBalance().longValue() + financeLog.geteCoin().longValue());
			finance.setRmbAmounts(finance.getRmbAmounts().add(financeLog.getMoney()));
			financeMapper.updateByPrimaryKeySelective(finance);
		}
		//添加充值记录
		financeLog.setCreateTime(new Date());
		financeLog.setFinanceId(finance.getId());
		financeLog.setDealDate(new Date());
		financeLog.setType(FinanceLogTypeEnum.RECHARGE.getId());
		Integer result = financeLogMapper.insert(financeLog);
		return result;
	}
}
