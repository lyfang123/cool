package com.uwaytech.browsing.service;

import com.github.pagehelper.Page;
import com.uwaytech.browsing.domain.Browsing;
import com.uwaytech.browsing.domain.BrowsingInfo;

import java.util.Date;
import java.util.Map;

/**
 * Created by moyi on 2016-06-15.
 */
public interface BrowsingService {
    int insertBrowsing(Long userId, int type, Long cellId, String point);
    int deleteBrowsing(Long id);
    int deleteAll(Long userId);
    Page<BrowsingInfo> selectBrowsingInfo(int type, Long userId, Date startTime, Date endTime, int start, int pageSize);

    /**
     * 查询课程播放用户统计
     * @param courseId 课程Id
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<Browsing> queryCourseBrowse(Long courseId, Integer pageNum, Integer pageSize);
}
