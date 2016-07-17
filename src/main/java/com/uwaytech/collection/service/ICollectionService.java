package com.uwaytech.collection.service;

import com.github.pagehelper.Page;
import com.uwaytech.collection.domain.CollectionInfo;

/**
 * Created by zeng on 2016/6/13.
 */
public interface ICollectionService {
	/**
	 * 添加个人收藏
	 * @param userId 用户id
	 * @param cellId 收藏课或资源id
	 * @param type 收藏类型：资源或课程
	 * @return
	 */
	int addCollection(Long userId, Long cellId, Integer type);

	/**
	 * 删除个人收藏
	 * @param collectionId 收藏id
	 * @param userId
	 * @return
	 */
	int deleteCollection(Long collectionId, Long userId);

	/**
	 * 收藏列表
	 * @param userId 用户id
	 * @param type 收藏类型：资源或课程
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	Page<CollectionInfo> getCollection(Long userId, Integer type, Integer pageNum, Integer pageSize);
}
