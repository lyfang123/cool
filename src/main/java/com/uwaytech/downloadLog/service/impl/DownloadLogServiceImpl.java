package com.uwaytech.downloadLog.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.uwaytech.downloadLog.dao.ExtendDownloadLogMapper;
import com.uwaytech.downloadLog.domain.DownloadLog;
import com.uwaytech.downloadLog.domain.DownloadLogParam;
import com.uwaytech.downloadLog.service.DownloadLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * DownloadLogServiceImpl
 *
 * @author lyfang
 * @date 2016/6/6
 */

@Service
public class DownloadLogServiceImpl implements DownloadLogService {

	@Resource
	private ExtendDownloadLogMapper extendDownloadLogMapper;

	/**
	 * 查询学校资源下载列表
	 *
	 * @param schoolId  学校ID
	 * @param pageNum   当前页
	 * @param pageSize  每页条数
	 * @param startTime 查询开始时间
	 * @param endTime   查询结束时间
	 * @param keyword   搜索条件，资源名称/部门/用户
	 * @return
	 */
	@Override
	public Page<DownloadLog> queryDownloadList(Long schoolId, Integer pageNum, Integer pageSize, Date startTime,
	                                           Date endTime, String keyword) {
		DownloadLogParam download = new DownloadLogParam();
		download.setStartTime(startTime);
		download.setEndTime(endTime);
		download.setKeyword(keyword);
		download.setSchoolId(schoolId);
		//分页查询
		PageHelper.startPage(pageNum, pageSize);
		Page<DownloadLog> page = (Page<DownloadLog>) extendDownloadLogMapper.queryDownloadList(download);
		return page;
	}


	/**
	 * 查询学校资源下载花费e币
	 *
	 * @param schoolId  学校ID
	 * @param startTime 查询开始时间
	 * @param endTime   查询结束时间
	 * @param keyword   搜索条件，资源名称/部门/用户
	 * @return 搜索条件，资源名称/部门/用户
	 */
	@Override
	public Long queryDownloadEcoins(Long schoolId, Date startTime, Date endTime, String keyword) {
		DownloadLogParam download = new DownloadLogParam();
		download.setSchoolId(schoolId);
		return extendDownloadLogMapper.queryDownloadEcoins(download);
	}
}
