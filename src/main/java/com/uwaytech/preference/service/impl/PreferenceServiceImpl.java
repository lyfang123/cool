package com.uwaytech.preference.service.impl;

import com.uwaytech.preference.dao.UserCategoryMapper;
import com.uwaytech.preference.domain.UserCategory;
import com.uwaytech.preference.domain.UserCategoryExample;
import com.uwaytech.preference.domain.UserCategoryInfo;
import com.uwaytech.preference.service.PreferenceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by lyf on 2016/7/14.
 */
@Service
public class PreferenceServiceImpl implements PreferenceService {

    @Resource
    private UserCategoryMapper userCategoryMapper;

    @Override
    public void addUserCategory(UserCategoryInfo userCategoryInfo, Long userId) {
        //删除原有的偏好设置
        UserCategoryExample example = new UserCategoryExample();
        example.createCriteria().andUserIdEqualTo(userId).andTypeEqualTo(userCategoryInfo.getType());
        userCategoryMapper.deleteByExample(example);
        //添加新的偏好设置
        for(Long categoryId : userCategoryInfo.getCategoryIds()){
            UserCategory userCategory = new UserCategory();
            userCategory.setType(userCategoryInfo.getType());
            userCategory.setCategoryId(categoryId);
            userCategory.setUserId(userId);
            userCategoryMapper.insert(userCategory);
        }
    }
}
