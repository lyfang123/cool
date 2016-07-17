package com.uwaytech.downloadLog.ctrl.dto;

import com.github.pagehelper.Page;
import com.uwaytech.downloadLog.domain.DownloadLog;
import com.uwaytech.finance.ctrl.dto.SchoolFinance;

import java.util.ArrayList;
import java.util.List;

/**
 * ResourceDownloadDto
 *
 * @author lyfang
 * @date 2016/6/6
 */
public class ResourceDownloadDto {
	private long total;

	private Integer pageNum;

	private Integer pageSize;

	private Long eAmount;

	private List<ResourceDownload> rows;

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

	public Long geteAmount() {
		return eAmount;
	}

	public void seteAmount(Long eAmount) {
		this.eAmount = eAmount;
	}

	public List<ResourceDownload> getRows() {
		return rows;
	}

	public void setRows(List<ResourceDownload> rows) {
		this.rows = rows;
	}

	public static ResourceDownloadDto downloadLogDto(Page<DownloadLog> page, Long ecoins) {
		ResourceDownloadDto dto = new ResourceDownloadDto();
		if (null != page) {
			dto.setTotal(new Long(page.getTotal()));
			dto.setPageSize(page.getPageSize());
			dto.setPageNum(page.getPageNum());
			dto.seteAmount(ecoins);
			if (null != page.getResult() && page.getResult().size() > 0) {
				List<ResourceDownload> list = new ArrayList<ResourceDownload>();
				for (DownloadLog downloadLog : page.getResult()) {
					ResourceDownload download = new ResourceDownload();
					download.setDealTime(downloadLog.getCreateTime());
					download.seteCoin(downloadLog.geteCoin());
					download.setOrderCode(downloadLog.getId() + "");
					download.setResourceName(downloadLog.getResourceName());
					download.setUserDept(downloadLog.getUserDept());
					download.setResouseId(downloadLog.getResouseId());
					download.setUserName(downloadLog.getUserName());
					download.setUserId(downloadLog.getUserId());
					download.setSchoolName(downloadLog.getSchoolName());
					list.add(download);
				}
				dto.setRows(list);
			}
		}
		return dto;
	}
}
