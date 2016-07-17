package com.uwaytech.eCoinMoney.ctrl;

import com.github.pagehelper.Page;
import com.uwaytech.common.exception.ParamMissException;
import com.uwaytech.common.exception.PermissionDeniedException;
import com.uwaytech.common.json.SuccessResult;
import com.uwaytech.cool.common.constant.Constant;
import com.uwaytech.eCoinMoney.ctrl.dto.ECoinMoneyDto;
import com.uwaytech.eCoinMoney.domain.ECoinMoney;
import com.uwaytech.eCoinMoney.service.ECoinMoneyService;
import com.uwaytech.financeLog.domain.FinanceLog;
import com.uwaytech.httpclient.SessionUtils;
import com.uwaytech.id.service.IdGeneratorService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * ECoinMoneyController
 * E币兑换规则管理ctrl
 *
 * @author lyfang
 * @date 2016/6/7
 */
@RestController
@RequestMapping(value = "/rule")
public class ECoinMoneyController {

	@Resource
	private ECoinMoneyService eCoinMoneyService;

	@Resource
	private IdGeneratorService idGeneratorService;

	/**
	 * 添加E币兑换规则
	 *
	 * @param eCoinMoney e币规则对象
	 * @return
	 */
	@RequestMapping(value = "/v0.1/rule", method = RequestMethod.POST)
	public Object addECoinMoney(ECoinMoney eCoinMoney) {
		if(SessionUtils.getUserType().byteValue() != Constant.ADMIN.intValue()){
			throw new PermissionDeniedException("您没有操作权限");
		}
		if (null == eCoinMoney) {
			throw new ParamMissException("参数不能為空");
		}
		if (null == eCoinMoney.geteCoin()) {
			throw new ParamMissException("e币参数不能為空");
		}
		if (null == eCoinMoney.getRmb()) {
			throw new ParamMissException("充值金额不能為空");
		}
		long id = idGeneratorService.generatorId(SessionUtils.getSchoolId());
		eCoinMoney.setId(id);
		eCoinMoneyService.addECoinMoney(eCoinMoney);
		return new SuccessResult();
	}

	/**
	 * 删除E币兑换规则
	 *
	 * @param id e币规则ID
	 * @return
	 */
	@RequestMapping(value = "/v0.1/rule/{id}", method = RequestMethod.DELETE)
	public Object deleteECoinMoney(@PathVariable("id") Long id) {
		if(SessionUtils.getUserType().byteValue() != Constant.ADMIN.intValue()){
			throw new PermissionDeniedException("您没有操作权限");
		}
		eCoinMoneyService.deleteECoinMoney(id);
		return new SuccessResult();
	}

	/**
	 * 查询E币兑换规则列表
	 *
	 * @return
	 */
	@RequestMapping(value = "/v0.1/rule/list", method = RequestMethod.GET)
	public Object queryECoinMoney( @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
	                               @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
		ECoinMoneyDto dto = ECoinMoneyDto.dto(eCoinMoneyService.queryECoinMoney(pageNum, pageSize));
		return dto;
	}

}
