package com.uwaytech.financeLog.ctrl;

import com.github.pagehelper.Page;
import com.uwaytech.common.exception.ParamMissException;
import com.uwaytech.common.exception.PermissionDeniedException;
import com.uwaytech.common.json.SuccessResult;
import com.uwaytech.cool.common.constant.Constant;
import com.uwaytech.financeLog.ctrl.dto.FinanceLogDto;
import com.uwaytech.financeLog.domain.FinanceLog;
import com.uwaytech.financeLog.service.FinanceLogService;
import com.uwaytech.httpclient.SessionUtils;
import com.uwaytech.id.service.IdGeneratorService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * FinanceLogController
 * 入账记录
 *
 * @author lyfang
 * @date 2016/6/6
 */

@RestController
@RequestMapping(value = "/finance")
public class FinanceLogController {

	@Resource
	private FinanceLogService financeLogService;

	@Resource
	private IdGeneratorService idGeneratorService;

	/**
	 * 获取学校入账记录列表
	 *
	 * @param schoolId  学校ID
	 * @param pageNum   当前页
	 * @param pageSize  每页条数
	 * @param startTime 查询开始时间
	 * @param endTime   查询结束时间
	 * @return 学校入账记录列表
	 */
	@RequestMapping(value = "/v0.1/recharge/list", method = RequestMethod.GET)
	public Object queryFinanceLogList(@RequestParam(value = "schoolId", required = false) Long schoolId,
	                                  @DateTimeFormat(pattern = "yyyy-MM-dd")
	                                  @RequestParam(value = "startTime", required = false) Date startTime,
	                                  @DateTimeFormat(pattern = "yyyy-MM-dd")
	                                  @RequestParam(value = "endTime", required = false) Date endTime,
	                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
	                                  @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
		//分页查询学校入账记录
		Page<FinanceLog> page = financeLogService.queryFinanceLogList(schoolId, pageNum, pageSize, startTime,
				endTime);
		//查询学校入账总额
		Long ecoins = financeLogService.queryFinanceLogEcoins(schoolId, startTime,
				endTime);
		FinanceLogDto financeLogDto = FinanceLogDto.financeLogDto(page, ecoins);
		return financeLogDto;
	}

	/**
	 * 添加学校入账
	 *
	 * @param schoolId   学校ID
	 * @param financeLog 入账记录
	 * @return
	 */
	@RequestMapping(value = "/v0.1/recharge", method = RequestMethod.POST)
	public Object addFinanceLog(@RequestParam("schoolId") Long schoolId,
	                            FinanceLog financeLog) {
		if(SessionUtils.getUserType().byteValue() != Constant.ADMIN.intValue()){
			throw new PermissionDeniedException("您没有充值权限");
		}
		if (StringUtils.isBlank(financeLog.getBankNumber())) {
			throw new ParamMissException("银行流水号不能為空");
		}
		if (StringUtils.isBlank(financeLog.getOrderCode())) {
			throw new ParamMissException("入账单号不能為空");
		}
		if (null == financeLog.getMoney()) {
			throw new ParamMissException("入账金额不能為空");
		}
		if (null == schoolId) {
			throw new ParamMissException("学校不能為空");
		}
		Long userId = SessionUtils.getUserId();
		financeLog.setCreateUser(userId);
		long id = idGeneratorService.generatorId(SessionUtils.getSchoolId());
		financeLog.setId(id);
		financeLogService.addFinanceLog(schoolId, financeLog);
		return new SuccessResult();
	}
}
