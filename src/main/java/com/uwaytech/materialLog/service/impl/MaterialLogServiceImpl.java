package com.uwaytech.materialLog.service.impl;

import com.uwaytech.materialLog.dao.MaterialLogMapper;
import com.uwaytech.materialLog.domain.MaterialLog;
import com.uwaytech.materialLog.service.MaterialLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * MaterialLogServiceImpl
 * 素材上传日志记录service实现类
 * @author lyfang
 * @date 2016/6/23
 */
@Service
public class MaterialLogServiceImpl implements MaterialLogService {

	@Resource
	private MaterialLogMapper materialLogMapper;

	/**
	 * 添加素材上传日志记录
	 *
	 * @param materialLog 素材上传日志记录对象
	 */
	@Override
	public int addMaterialLog(MaterialLog materialLog) {
		materialLog.setCreateTime(new Date());
		return materialLogMapper.insert(materialLog);
	}
}
