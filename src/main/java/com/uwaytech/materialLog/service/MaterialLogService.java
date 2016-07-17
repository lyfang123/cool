package com.uwaytech.materialLog.service;

import com.uwaytech.materialLog.domain.MaterialLog;

/**
 * Created by rtyui on 2016/6/23.
 */
public interface MaterialLogService {
	/**
	 * 添加素材上传日志记录
	 * @param materialLog 素材上传日志记录对象
	 */
	int addMaterialLog(MaterialLog materialLog);
}
