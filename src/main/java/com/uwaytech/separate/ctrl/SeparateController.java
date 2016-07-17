package com.uwaytech.separate.ctrl;

import com.github.pagehelper.Page;
import com.uwaytech.cool.common.constant.Constant;
import com.uwaytech.financeLog.ctrl.dto.FinanceLogDto;
import com.uwaytech.financeLog.domain.FinanceLog;
import com.uwaytech.httpclient.SessionUtils;
import com.uwaytech.separate.ctrl.dto.SeparateDto;
import com.uwaytech.separate.domain.Separate;
import com.uwaytech.separate.service.SeparateService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * SeparateController
 * 供应商分账记录ctrl
 *
 * @author lyfang
 * @date 2016/6/8
 */

@RestController
@RequestMapping(value = "/finance")
public class SeparateController {

	@Resource
	private SeparateService separateService;

	/**
	 * 查询供应商分账记录列表
	 *
	 * @param pageNum   当前页
	 * @param pageSize  每页条数
	 * @param startTime 查询开始时间
	 * @param endTime   查询结束时间
	 * @return 返回供应商分账记录列表
	 */
	@RequestMapping(value = "/v0.1/separate", method = RequestMethod.GET)
	public Object querySeparateList(@RequestParam(value = "supplierId", required = false) Long supplierId,
	                                @DateTimeFormat(pattern = "yyyy-MM")
	                                @RequestParam(value = "startTime", required = false) Date startTime,
	                                @DateTimeFormat(pattern = "yyyy-MM")
	                                @RequestParam(value = "endTime", required = false) Date endTime,
	                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
	                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
		Byte userType = SessionUtils.getUserType().byteValue();
		if (userType == Constant.SUPPLIER) {
			supplierId = SessionUtils.getUserId();
		}
		//分页查询供应商分账记录
		Page<Separate> page = separateService.querySeparateList(supplierId, startTime, endTime, pageNum, pageSize);
		SeparateDto separateDto = SeparateDto.separateDto(page);
		return separateDto;
	}


}
