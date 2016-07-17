package com.uwaytech.browsing.dao;

import com.uwaytech.browsing.domain.BrowsingInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by moyi on 2016-06-16.
 */
public interface ExtendBrowsingMapper {
    List<BrowsingInfo> selectBrowsingInfo(@Param("map") Map<String, Object> map);
}
