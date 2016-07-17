package com.uwaytech.materialLog;

import com.uwaytech.JunitTestConfig;
import com.uwaytech.material.domain.Material;
import com.uwaytech.materialLog.domain.MaterialLog;
import com.uwaytech.materialLog.service.MaterialLogService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import javax.annotation.Resource;

/**
 * MaterialLogServiceTest
 *
 * @author lyfang
 * @date 2016/6/23
 */
public class MaterialLogServiceTest extends JunitTestConfig {
	
	@Resource
	private MaterialLogService materialLogService;

	@Test
	@Rollback(false)
	public void add(){
		MaterialLog materialLog = new MaterialLog();
		materialLog.setHash("123456");
		materialLog.setQiniuKey("123456");
		materialLog.setUserId(123L);
		Integer result = materialLogService.addMaterialLog(materialLog);
		Assert.assertEquals(result == 1, true);
	}
	
}
