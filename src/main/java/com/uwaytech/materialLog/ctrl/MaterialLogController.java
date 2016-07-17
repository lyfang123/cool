package com.uwaytech.materialLog.ctrl;

import com.uwaytech.common.exception.ParamMissException;
import com.uwaytech.common.json.SuccessResult;
import com.uwaytech.httpclient.SessionUtils;
import com.uwaytech.materialLog.domain.MaterialLog;
import com.uwaytech.materialLog.service.MaterialLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * MaterialLogController
 * 素材上传日志记录
 * @author lyfang
 * @date 2016/6/23
 */
@RestController
@RequestMapping(value = "/log")
public class MaterialLogController {

	@Resource
	private MaterialLogService materialLogService;

	/**
	 * 添加素材上传日志记录
	 * @param materialLog 素材上传日志记录对象
	 * @return
	 */
	@RequestMapping(value = "/v0.1/materialLog", method = RequestMethod.POST)
	public Object addMaterialLog(MaterialLog materialLog) {
		if (StringUtils.isBlank(materialLog.getQiniuKey())) {
			throw new ParamMissException("参数key不能為空");
		}
		if (StringUtils.isBlank(materialLog.getToken())) {
			throw new ParamMissException("参数token不能為空");
		}
		materialLog.setUserId(SessionUtils.getUserId());
		materialLogService.addMaterialLog(materialLog);
		return new SuccessResult();
	}
}
