package com.uwaytech.finance.service;

import com.github.pagehelper.Page;
import com.uwaytech.finance.ctrl.dto.FinanceConsume;
import com.uwaytech.finance.ctrl.dto.SchoolFinanceDto;
import com.uwaytech.finance.ctrl.dto.SupplierFinance;
import com.uwaytech.finance.domain.FinanceResult;
import com.uwaytech.finance.domain.FinanceSupplier;

import java.util.Date;

/**
 * 账号信息相关service实现类
 * Created by rtyui on 2016/6/6.
 */
public interface FinanceService {
	/**
	 * 查询学校账号列表
	 *
	 * @param schoolId    学校id
	 * @param pageNum     当前页
	 * @param pageSize    每页条数
	 * @param accessToken 用户验证token
	 * @return
	 */
	SchoolFinanceDto querySchoolList(String name, Integer pageNum, Integer pageSize, String accessToken);

	/**
	 * 资源下载扣E币，完成交易
	 *
	 * @param resourceId 资源ID
	 * @param schoolId   学校ID
	 * @param userId     用户ID
	 * @param userName   用户名
	 * @param userDept   用户所在部门
	 * @return
	 */
	Integer payResourceDownloadECoin(Long resourceId, Integer schoolId, Long userId, String userName, String userDept);

	/**
	 * 查询学校账号信息
	 *
	 * @param schoolId 学校ID
	 * @return
	 */
	FinanceResult queryFinanceInfos(Integer schoolId);

	/**
	 * 查询学校消费统计，按月份统计
	 *
	 * @param schoolId 学校ID
	 * @param pageNum  当前页
	 * @param pageSize 每页条数
	 * @return
	 */
	Page<FinanceConsume> queryFinanceConsumes(Integer schoolId, Integer pageNum, Integer pageSize);

	/**
	 * 查询供应商账号信息
	 *
	 * @param supplierId 供应商ID
	 * @return
	 */
	FinanceSupplier querySupplierFinanceInfos(Long supplierId);

	/**
	 * 查询供应商下载明细
	 *
	 * @param supplierId   供应商ID
	 * @param pageNum      当前页
	 * @param pageSize     每页条数
	 * @param resourceName 资源名称
	 * @return 返回供应商资源下载明细列表
	 */
	Page<SupplierFinance> querySupplierDownloads(Long supplierId, Integer pageNum, Integer pageSize, String resourceName,
	                                             Date startTime, Date endTime);

	/**
	 * 查询供应商下载e币收入
	 * @param supplierId
	 * @return
	 */
	Long querySupplierDownloadEcoins(Long supplierId);
}
