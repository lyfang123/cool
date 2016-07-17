package com.uwaytech.separate.service;

import com.github.pagehelper.Page;
import com.uwaytech.separate.domain.Separate;

import java.util.Date;

/**
 * Created by rtyui on 2016/6/8.
 */
public interface SeparateService {
	/**
	 * 查询供应商分账记录列表
	 *
	 * @param pageNum    当前页
	 * @param pageSize   每页条数
	 * @param startTime  查询开始时间
	 * @param endTime    查询结束时间
	 * @param supplierId
	 * @return 返回供应商分账记录列表
	 */
	Page<Separate> querySeparateList(Long supplierId, Date startTime, Date endTime,
	                                 Integer pageNum, Integer pageSize);

}
