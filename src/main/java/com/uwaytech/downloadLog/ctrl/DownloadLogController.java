package com.uwaytech.downloadLog.ctrl;

import com.github.pagehelper.Page;
import com.uwaytech.downloadLog.ctrl.dto.ResourceDownloadDto;
import com.uwaytech.downloadLog.domain.DownloadLog;
import com.uwaytech.downloadLog.service.DownloadLogService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * DownloadLogController
 * 学校下载记录
 *
 * @author lyfang
 * @date 2016/6/6
 */

@RestController
@RequestMapping(value = "/downloadLog")
public class DownloadLogController {

	@Resource
	private DownloadLogService downloadLogService;

	/**
	 * 获取学校下载记录列表
	 *
	 * @param schoolId  学校ID
	 * @param pageNum   当前页
	 * @param pageSize  每页条数
	 * @param startTime 查询开始时间
	 * @param endTime   查询结束时间
	 * @param keyword   搜索条件，资源名称/部门/用户
	 * @return 学校下载记录列表
	 */
	@RequestMapping(value = "/v0.1/consume/list", method = RequestMethod.GET)
	public Object queryDownloadList(@RequestParam(value = "schoolId", required = false) Long schoolId,
	                                @DateTimeFormat(pattern = "yyyy-MM-dd")
	                                @RequestParam(value = "startTime", required = false) Date startTime,
	                                @DateTimeFormat(pattern = "yyyy-MM-dd")
	                                @RequestParam(value = "endTime", required = false) Date endTime,
	                                @RequestParam(value = "keyword", required = false) String keyword,
	                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
	                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
		//分页查询学校资源下载记录列表
		Page<DownloadLog> page = downloadLogService.queryDownloadList(schoolId, pageNum, pageSize, startTime,
				endTime, keyword);
		//查询学校资源下载消费E币
		Long ecoins = downloadLogService.queryDownloadEcoins(schoolId, startTime,
				endTime, keyword);
		//转换成输出结果
		ResourceDownloadDto downloadDto = ResourceDownloadDto.downloadLogDto(page, ecoins);
		return downloadDto;
	}
}
