package com.uwaytech.browsing.ctrl;

import com.github.pagehelper.Page;
import com.uwaytech.browsing.ctrl.dto.BrowsingDto;
import com.uwaytech.browsing.ctrl.dto.BrowsingInfoDto;
import com.uwaytech.browsing.domain.Browsing;
import com.uwaytech.browsing.domain.BrowsingInfo;
import com.uwaytech.browsing.service.BrowsingService;
import com.uwaytech.common.json.SuccessResult;
import com.uwaytech.httpclient.SessionUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by moyi on 2016-06-16.
 */
@RestController
@RequestMapping(value = "/browse")
public class BrowsingController {

    @Resource
    private BrowsingService browsingService;

    @RequestMapping(value = "/v0.1", method = RequestMethod.POST)
    public Object addBrowse(@RequestParam("type") Integer type,
                            @RequestParam("cellId") Long cellId, @RequestParam("point") String point) {
        Long userId = SessionUtils.getUserId();
        browsingService.insertBrowsing(userId, type, cellId, point);
        return new SuccessResult();
    }

    @RequestMapping(value = "/v0.1/{id}", method = RequestMethod.DELETE)
    public Object deleteBrowse(@PathVariable("id") Long id) {
        browsingService.deleteBrowsing(id);
        return new SuccessResult();
    }


    @RequestMapping(value = "/v0.1/user", method = RequestMethod.DELETE)
    public Object deleteAllBrowse() {
        Long userId = SessionUtils.getUserId();
        browsingService.deleteAll(userId);
        return new SuccessResult();
    }

    @RequestMapping(value = "/v0.1/list", method = RequestMethod.GET)
    public Object queryBrowse(@RequestParam(value = "type", defaultValue = "2") Integer type,
                              @DateTimeFormat(pattern = "yyyy-MM-dd")
                              @RequestParam("startTime") Date startTime,
                              @DateTimeFormat(pattern = "yyyy-MM-dd")
                              @RequestParam("endTime") Date endTime,
                              @RequestParam("pageNum") Integer pageNum,
                              @RequestParam("pageSize") Integer pageSize) {
        Long userId = SessionUtils.getUserId();
        Page<BrowsingInfo> page = browsingService.selectBrowsingInfo(type, userId, startTime, endTime, pageNum, pageSize);
        return BrowsingInfoDto.dto(page);
    }

    /**
     * 查询课程播放用户统计
     * @param courseId 课程id
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/v0.1/{courseId}/list", method = RequestMethod.GET)
    public Object queryCourseBrowse(@PathVariable(value = "courseId") Long courseId,
                              @RequestParam("pageNum") Integer pageNum,
                              @RequestParam("pageSize") Integer pageSize) {
        Page<Browsing> page = browsingService.queryCourseBrowse(courseId, pageNum, pageSize);
        return BrowsingDto.dto(page);
    }
}
