package com.uwaytech.collection.dao;

import com.github.pagehelper.Page;
import com.uwaytech.collection.domain.CollectionInfo;
import org.apache.ibatis.annotations.Param;

/**
 * Created by zeng on 2016/6/13.
 */
public interface ExtendCollectionMapper {

	Page<CollectionInfo> getResourceCollection(@Param("userId") Long userId, @Param("type") Integer type);

	Page<CollectionInfo> getCourseCollection(@Param("userId") Long userId, @Param("type") Integer type);
}
