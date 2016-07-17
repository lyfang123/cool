package com.uwaytech.collection.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.uwaytech.collection.dao.CollectionMapper;
import com.uwaytech.collection.dao.ExtendCollectionMapper;
import com.uwaytech.collection.domain.Collection;
import com.uwaytech.collection.domain.CollectionExample;
import com.uwaytech.collection.domain.CollectionInfo;
import com.uwaytech.collection.service.ICollectionService;
import com.uwaytech.common.exception.PermissionDeniedException;
import com.uwaytech.cool.common.constant.Constant;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zeng on 2016/6/13.
 */
@Service("collectionService")
public class CollectionServiceImpl implements ICollectionService {

	@Resource
	private CollectionMapper collectionMapper;
	@Resource
	private ExtendCollectionMapper extendCollectionMapper;

	@Override
	public int addCollection(Long userId, Long cellId, Integer type) {
		CollectionExample example = new CollectionExample();
		example.createCriteria().andUserIdEqualTo(userId).andCellIdEqualTo(cellId);
		List<Collection> list = collectionMapper.selectByExample(example);
		if (list.isEmpty()) {
			Collection collection = new Collection();
			collection.setUserId(userId);
			collection.setCellId(cellId);
			collection.setType(type);
			return collectionMapper.insertSelective(collection);
		} else {
			throw new PermissionDeniedException("你已经收藏该课程或资源");
		}
	}

	@Override
	public int deleteCollection(Long collectionId, Long userId) {
		CollectionExample example = new CollectionExample();
		example.createCriteria().andUserIdEqualTo(userId).andIdEqualTo(collectionId);
		return collectionMapper.deleteByExample(example);
	}

	@Override
	public Page<CollectionInfo> getCollection(Long userId, Integer type, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		Page<CollectionInfo> page = new Page<>();
		if (type == Constant.ONE) {
			page = extendCollectionMapper.getResourceCollection(userId, type);
		} else if (type == Constant.TWO) {
			page = extendCollectionMapper.getCourseCollection(userId, type);
		}
		return page;
	}
}
