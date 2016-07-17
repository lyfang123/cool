package com.uwaytech.material.ctrl;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.uwaytech.common.exception.ParamMissException;
import com.uwaytech.common.json.SuccessResult;
import com.uwaytech.common.utils.PropertiesProvider;
import com.uwaytech.cool.common.constant.Constant;
import com.uwaytech.cool.common.enums.ResStatusEnum;
import com.uwaytech.httpclient.SessionUtils;
import com.uwaytech.id.service.IdGeneratorService;
import com.uwaytech.material.ctrl.dto.MaterialDto;
import com.uwaytech.material.domain.Material;
import com.uwaytech.material.domain.MaterialInfo;
import com.uwaytech.material.service.IMaterialService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zeng on 2016/5/26.
 */
@RestController
@RequestMapping(value = "/material")
public class MaterialController {

	@Resource
	private IMaterialService materialService;
	@Resource
	private IdGeneratorService idGeneratorService;

	/**
	 * 添加素材
	 *
	 * @param materials
	 * @return
	 */
	@RequestMapping(value = "/v0.1", method = RequestMethod.POST, consumes = "application/json")
	public Object addMaterial(@RequestBody Material[] materials) {
		Byte type = getUserType();
		Long id = null;
		Long grouping = getGrouping(type);
		List<Material> list = new ArrayList<>();
		for (Material material : materials) {
			if (type == Constant.SUPPLIER) {
				id = idGeneratorService.generatorId(Constant.SUPPLIER_NUMBER);
			} else {
				id = idGeneratorService.generatorId(grouping.intValue());
			}
			if (null == material.getUrl()) {
				throw new ParamMissException(material.getName() + "：url地址不能为空");
			}
			material = toMaterialInfo(material);
			material.setGrouping(grouping);
			material.setId(id);
			//素材未发布状态
			material.setStatus(ResStatusEnum.OFF_SHELVE.getValue());
			material.setCreateTime(new Date());
			list.add(material);
		}
		materialService.insertMaterial(list);
		return new SuccessResult();
	}

	/**
	 * 修改素材
	 *
	 * @param materialId
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/v0.1/{materialId}", method = RequestMethod.PUT)
	public Object updateMaterial(@PathVariable(value = "materialId") Long materialId,
	                             @RequestParam String name) {
		Material material = new Material();
		material.setId(materialId);
		material.setName(name);
		materialService.updateMaterial(material);
		return new SuccessResult();
	}

	/**
	 * 删除素材
	 *
	 * @param materialId
	 * @return
	 */
	@RequestMapping(value = "/v0.1/{materialId}", method = RequestMethod.DELETE)
	public Object deleteMaterial(@PathVariable(value = "materialId") Long materialId) {
		materialService.deleteMaterial(materialId);
		return new SuccessResult();
	}

	/**
	 * 获取素材列表
	 *
	 * @param type
	 * @param categoryId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/v0.1/list", method = RequestMethod.GET)
	public Object findMaterial(@RequestParam(value = "type") Integer type,
	                           @RequestParam(value = "cusCateId") Long categoryId,
	                           @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
	                           @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
		Byte userType = getUserType();
		Long grouping = null;
		//获取用户类型
		if (userType == Constant.SUPPLIER) {
			grouping = SessionUtils.getUserId();
		} else {
			grouping = SessionUtils.getSchoolId().longValue();
		}
		Page<Material> page = materialService.findMaterials(type, categoryId, grouping, pageNum, pageSize);
		List<MaterialDto> materialDto = MaterialDto.toMaterialDto(page.getResult());
		MaterialInfo materialInfo = new MaterialInfo();
		materialInfo.setPageSize(pageSize);
		materialInfo.setPageNum(pageNum);
		materialInfo.setTotal(page.getTotal());
		materialInfo.setRows(materialDto);
		return materialInfo;
	}

	/**
	 * 移动素材分组
	 *
	 * @param materialId
	 * @param categoryId
	 * @return
	 */
	@RequestMapping(value = "/v0.1/category", method = RequestMethod.PUT)
	public Object resMaterialCate(@RequestParam(value = "materialId") Long materialId,
	                              @RequestParam(value = "categoryId") Long categoryId) {
		Material material = new Material();
		material.setId(materialId);
		material.setCusCateId(categoryId);
		materialService.updateMaterial(material);
		return new SuccessResult();
	}

	/**
	 * 获取素材详细信息
	 *
	 * @param material
	 * @return
	 */
	public Material toMaterialInfo(Material material) {
		String fileType = material.getUrl().substring(material.getUrl().lastIndexOf('.') + 1, material.getUrl().length());
		material.setMediaType(fileType);
		if(!StringUtils.isEmpty(material.getUrl()) && !material.getUrl().startsWith("http://")){
			material.setUrl(PropertiesProvider.getProperty("domain_url") + "/" + material.getUrl());
		}
		if (material.getUrl().toLowerCase().endsWith("mp4")) {
			//获取MP4视频文件元数据
			String jsonStr = getJsonString(material.getUrl().trim() + "?avinfo");
			JSONObject jsonTime = JSONObject.parseObject(jsonStr);
			//获取MP4课程文件时长
			Integer duration = jsonTime.getJSONObject("format").getFloat("duration").intValue();
			material.setLength(duration);
		}
		return material;
	}

	/**
	 * 读取视频时长
	 *
	 * @param urlPath
	 * @return
	 */
	public static String getJsonString(String urlPath) {
		try {
			URL url = new URL(urlPath);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("referer", "http://portal.veducloud.com/");
			connection.connect();
			InputStream inputStream = connection.getInputStream();
			//对应的字符编码转换
			Reader reader = new InputStreamReader(inputStream, "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(reader);
			String str = null;
			StringBuffer sb = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				sb.append(str);
			}
			reader.close();
			connection.disconnect();
			return sb.toString();
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 获取用户类型
	 *
	 * @return
	 */
	public static Byte getUserType() {
		Byte type = SessionUtils.getUserType().byteValue();
		return type;
	}

	/**
	 * 获取供应商id或学校id
	 * @param type
	 * @return
	 */
	private Long getGrouping(Byte type) {
		if (type == Constant.SUPPLIER) {
			return SessionUtils.getUserId();
		} else {
			return SessionUtils.getSchoolId().longValue();
		}
	}
}
