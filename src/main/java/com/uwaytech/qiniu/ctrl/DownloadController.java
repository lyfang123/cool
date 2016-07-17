package com.uwaytech.qiniu.ctrl;

import com.uwaytech.httpclient.SessionUtils;
import com.uwaytech.material.domain.Material;
import com.uwaytech.material.service.IMaterialService;
import com.uwaytech.qiniu.service.QiNiuService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by moyi on 2016-07-15.
 */
@RestController
@RequestMapping(value = "/download")
public class DownloadController {

	@Resource
	private QiNiuService qiNiuService;

	@Resource
	private IMaterialService materialService;


	@RequestMapping(value = "/v0.1/res/{id}", method = RequestMethod.GET)
	public Object download(@PathVariable Long id){
		Long userId = SessionUtils.getUserId();
		//TODO 判断规则有待思考

		Material material = materialService.getMaterialById(id);
		String key = material.getQiniuKey();
		Map<String,String> map = new HashMap<>();
		map.put("downloadUrl",qiNiuService.downloadToken(key));
		return map;
	}
}
