package com.uwaytech.collection.ctrl;

import com.github.pagehelper.Page;
import com.uwaytech.collection.domain.CollectionInfo;
import com.uwaytech.collection.service.ICollectionService;
import com.uwaytech.common.json.SuccessResult;
import com.uwaytech.httpclient.SessionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by zeng on 2016/6/13.
 */
@RestController
@RequestMapping(value = "/collection")
public class CollectionController {
	@Resource
	private ICollectionService collectionService;

	/**
	 * 添加个人收藏
	 *
	 * @param cellId
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/v0.1", method = RequestMethod.POST)
	public Object addCollection(@RequestParam(value = "cellId") Long cellId,
	                            @RequestParam(value = "type") Integer type) {
		Long userId = SessionUtils.getUserId();
		collectionService.addCollection(userId, cellId, type);
		return new SuccessResult();
	}

	/**
	 * 删除个人收藏
	 *
	 * @param collectionId
	 * @return
	 */
	@RequestMapping(value = "/v0.1/{collectionId}", method = RequestMethod.DELETE)
	public Object deleteCollection(@PathVariable(value = "collectionId") Long collectionId) {
		Long userId = SessionUtils.getUserId();
		collectionService.deleteCollection(collectionId, userId);
		return new SuccessResult();
	}

	/**
	 * 个人收藏列表
	 *
	 * @param type
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/v0.1/list", method = RequestMethod.GET)
	public Object getCollection(@RequestParam(value = "type") Integer type,
	                            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
	                            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
		Long userId = SessionUtils.getUserId();
		Page<CollectionInfo> page = collectionService.getCollection(userId, type, pageNum, pageSize);
		return page;
	}
}
