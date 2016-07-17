package com.uwaytech.separate.ctrl.dto;

import com.github.pagehelper.Page;
import com.uwaytech.cool.common.util.DateUtil;
import com.uwaytech.separate.domain.Separate;
import org.apache.commons.lang3.time.DateUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * SeparateDto
 *
 * @author lyfang
 * @date 2016/6/8
 */
public class SeparateDto {
	private long total;

	private Integer pageNum;

	private Integer pageSize;

	private List<SeparateInfo> rows;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List<SeparateInfo> getRows() {
		return rows;
	}

	public void setRows(List<SeparateInfo> rows) {
		this.rows = rows;
	}

	public static SeparateDto separateDto(Page<Separate> page) {
		SeparateDto dto = new SeparateDto();
		if (null != page) {
			dto.setTotal(new Long(page.getTotal()));
			dto.setPageSize(page.getPageSize());
			dto.setPageNum(page.getPageNum());
			if (null != page.getResult() && page.getResult().size() > 0) {
				List<SeparateInfo> list = new ArrayList<SeparateInfo>();
				for (Separate separate : page.getResult()) {
					SeparateInfo separateInfo = new SeparateInfo();
					separateInfo.setName(separate.getName());
					separateInfo.setDownloadCount(separate.getDownloadCount());
					separateInfo.seteCoin(separate.geteCoin());
					separateInfo.seteEarningAmount(separate.geteEarningAmount());
					separateInfo.seteExchange(separate.geteExchange());
					separateInfo.setProportion(separate.getProportion());
					separateInfo.setRmbEarning(separate.getRmbEarning());
					separateInfo.setRmbAmount(separate.getRmbAmount());
					separateInfo.setDate(DateUtil.dateToStr(separate.getCreateTime(), "yyyy-MM"));
					list.add(separateInfo);
				}
				dto.setRows(list);
			}
		}
		return dto;
	}
}
