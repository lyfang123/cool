package com.uwaytech.financeLog.service;

import com.github.pagehelper.Page;
import com.uwaytech.financeLog.domain.FinanceLog;

import java.util.Date;

/**
 * Created by rtyui on 2016/6/6.
 */
public interface FinanceLogService {
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
	Page<FinanceLog> queryFinanceLogList(Long schoolId, Integer pageNum, Integer pageSize, Date startTime, Date endTime);

	/**
	 * 查询学校入账总额
	 *
	 * @param schoolId  学校ID
	 * @param startTime 查询开始时间
	 * @param endTime   查询结束时间
	 * @return 返回查询结果
	 */
	Long queryFinanceLogEcoins(Long schoolId, Date startTime, Date endTime);

	/**
	 * 添加学校入账
	 *
	 * @param schoolId
	 * @param financeLog
	 */
	Integer addFinanceLog(Long schoolId, FinanceLog financeLog);

}
