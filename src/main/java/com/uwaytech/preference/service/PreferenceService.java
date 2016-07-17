package com.uwaytech.preference.service;

import com.uwaytech.preference.domain.UserCategoryInfo;

/**
 * Created by lyf on 2016/7/14.
 */
public interface PreferenceService {
    /**
     * 添加用户偏好设置
     * @param userCategoryInfo
     * @param userId
     */
    void addUserCategory(UserCategoryInfo userCategoryInfo, Long userId);
}
