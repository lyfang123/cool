package com.uwaytech.separate.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.uwaytech.separate.dao.SeparateMapper;
import com.uwaytech.separate.domain.Separate;
import com.uwaytech.separate.domain.SeparateExample;
import com.uwaytech.separate.service.SeparateService;
import com.uwaytech.supplier.dao.SupplierMapper;
import com.uwaytech.supplier.domain.Supplier;
import com.uwaytech.supplier.domain.SupplierExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * SeparateServiceImpl
 *
 * @author lyfang
 * @date 2016/6/8
 */

@Service
public class SeparateServiceImpl implements SeparateService {

	@Resource
	private SeparateMapper separateMapper;

	@Resource
	private SupplierMapper supplierMapper;

	/**
	 * 查询供应商分账记录列表
	 *
	 * @param pageNum    当前页
	 * @param pageSize   每页条数
	 * @param startTime  查询开始时间
	 * @param endTime    查询结束时间
	 * @param supplierId 供应商ID
	 * @return 返回供应商分账记录列表
	 */
	@Override
	public Page<Separate> querySeparateList(Long supplierId, Date startTime, Date endTime,
	                                        Integer pageNum, Integer pageSize) {
		SeparateExample example = new SeparateExample();
		SeparateExample.Criteria criteria = example.createCriteria();
		//查询指定时间段的分账纪录
		if (null != supplierId) {
			criteria.andSupplierIdEqualTo(supplierId);
		}
		if (null != startTime && null != endTime) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(endTime);
			cal.add(Calendar.MONTH,+1);
			criteria.andCreateTimeGreaterThanOrEqualTo(startTime).andCreateTimeLessThan(cal.getTime());
		}
		//按时间降序排序
		example.setOrderByClause("create_time desc");
		PageHelper.startPage(pageNum, pageSize);
		Page<Separate> page = (Page<Separate>) separateMapper.selectByExample(example);
		//查询供应商名称
		if (!page.getResult().isEmpty()) {
			List<Long> supplierIds = new ArrayList<>();
			if (null != supplierId) {
				supplierIds.add(supplierId);
			} else {
				for (Separate separate : page.getResult()) {
					supplierIds.add(separate.getSupplierId());
				}
			}
			SupplierExample supplierExample = new SupplierExample();
			supplierExample.createCriteria().andIdIn(supplierIds);
			List<Supplier> list = supplierMapper.selectByExample(supplierExample);
			if(!list.isEmpty()){
				Map<Long, Supplier> map = Maps.uniqueIndex(list, new Function<Supplier, Long>() {
					public Long apply(Supplier from) {
						return from.getId();
					}
				});
				for (Separate separate : page.getResult()) {
					if (null != map.get(separate.getSupplierId())) {
						separate.setName(map.get(separate.getSupplierId()).getName());
					}
				}
			}
		}
		return page;
	}

}
