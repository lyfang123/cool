package com.uwaytech.preference.ctrl;

import com.uwaytech.common.exception.ParamMissException;
import com.uwaytech.common.json.SuccessResult;
import com.uwaytech.httpclient.SessionUtils;
import com.uwaytech.materialLog.domain.MaterialLog;
import com.uwaytech.preference.domain.UserCategoryInfo;
import com.uwaytech.preference.service.PreferenceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by lyf on 2016/7/14.
 */
@RestController
@RequestMapping(value = "/preference")
public class PreferenceController {

    @Resource
    private PreferenceService preferenceService;

    @RequestMapping(value = "/v0.1", method = RequestMethod.POST)
    public Object addUserCategory(@RequestBody UserCategoryInfo userCategoryInfo) {
        if (null == userCategoryInfo.getType()) {
            throw new ParamMissException("类型参数type不能为空");
        }
        if (null == userCategoryInfo.getCategoryIds() || userCategoryInfo.getCategoryIds().length == 0) {
            throw new ParamMissException("分类参数categoryIds不能为空");
        }
        preferenceService.addUserCategory(userCategoryInfo,SessionUtils.getUserId());
        return new SuccessResult();
    }

}
