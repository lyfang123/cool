package com.uwaytech.downloadLog.service;

import com.github.pagehelper.Page;
import com.uwaytech.downloadLog.domain.DownloadLog;

import java.util.Date;

/**
 * Created by rtyui on 2016/6/6.
 */
public interface DownloadLogService {

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
	Page<DownloadLog> queryDownloadList(Long schoolId, Integer pageNum, Integer pageSize,
	                                    Date startTime, Date endTime, String keyword);

	/**
	 * 查询学校资源下载花费e币
	 *
	 * @param schoolId  学校ID
	 * @param startTime 查询开始时间
	 * @param endTime   查询结束时间
	 * @param keyword   搜索条件，资源名称/部门/用户
	 * @return 搜索条件，资源名称/部门/用户
	 */
	Long queryDownloadEcoins(Long schoolId, Date startTime, Date endTime, String keyword);
}
