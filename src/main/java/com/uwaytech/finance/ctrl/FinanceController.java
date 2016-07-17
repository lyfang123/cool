package com.uwaytech.finance.ctrl;

import com.github.pagehelper.Page;
import com.uwaytech.common.exception.ParamMissException;
import com.uwaytech.common.json.SuccessResult;
import com.uwaytech.downloadLog.domain.DownloadLog;
import com.uwaytech.finance.ctrl.dto.*;
import com.uwaytech.finance.domain.FinanceResult;
import com.uwaytech.finance.domain.FinanceSupplier;
import com.uwaytech.finance.service.FinanceService;
import com.uwaytech.httpclient.SessionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * FinanceController
 * 账号信息相关ctrl
 *
 * @author lyfang
 * @date 2016/6/6
 */

@RestController
@RequestMapping(value = "/finance")
public class FinanceController {

	@Resource
	private FinanceService financeService;

	/**
	 * 获取学校账号列表
	 *
	 * @param schoolId 学校ID
	 * @return 学校账号列表
	 */
	@RequestMapping(value = "/v0.1/school/list", method = RequestMethod.GET)
	public Object querySchoolList(@RequestParam(value = "name", required = false) String name,
	                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
	                              @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
		//获取accessToken，用于UC验证凭证
		String accessToken = SessionUtils.getAccessToken();
		//查询学校列表
		SchoolFinanceDto result = financeService.querySchoolList(name, pageNum, pageSize, accessToken);
		return result;
	}

	/**
	 * 下载资源，支付E币完成交易
	 *
	 * @param resourceId 资源ID
	 * @return
	 */
	@RequestMapping(value = "/v0.1/pay", method = RequestMethod.PUT)
	public Object downloadResourcePay(@RequestParam("resourceId") Long resourceId) {
		//获取用户信息
		Integer schoolId = SessionUtils.getSchoolId();
		Long userId = SessionUtils.getUserId();
		String userName = SessionUtils.getUserInfoWrapper().getUserInfo().getName();
		String userDept = SessionUtils.getUserInfoWrapper().getUserInfo().getDepartmentName();
		//完成下载交易过程
		financeService.payResourceDownloadECoin(resourceId, schoolId, userId, userName, userDept);
		return new SuccessResult();
	}

	/**
	 * 查询学校账号信息
	 *
	 * @return
	 */
	@RequestMapping(value = "/v0.1/school", method = RequestMethod.GET)
	public Object querySchoolFinance() {
		Integer schoolId = SessionUtils.getSchoolId();
		//查询学校账号余额、消费e币、下载资源数量
		FinanceResult result = financeService.queryFinanceInfos(schoolId);
		return result;
	}

	/**
	 * 查询供应商账号信息
	 *
	 * @return
	 */
	@RequestMapping(value = "/v0.1/supplier", method = RequestMethod.GET)
	public Object querySupplierFinance() {
		Long supplierId = SessionUtils.getUserId();
		//查询供应商账号余额，资源总数、下载次数
		FinanceSupplier result = financeService.querySupplierFinanceInfos(supplierId);
		return result;
	}

	/**
	 * 查询学校下载统计，按月份统计
	 *
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/v0.1/statistics", method = RequestMethod.GET)
	public Object queryFinanceList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
	                               @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
		Integer schoolId = SessionUtils.getSchoolId();
		//按月统计学校下载资源消费e币
		Page<FinanceConsume> page = financeService.queryFinanceConsumes(schoolId, pageNum, pageSize);
		FinanceConsumeDto result = FinanceConsumeDto.financeConsumeDto(page);
		return result;
	}

}
