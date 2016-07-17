package com.uwaytech.browsing.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.uwaytech.browsing.dao.BrowsingMapper;
import com.uwaytech.browsing.dao.ExtendBrowsingMapper;
import com.uwaytech.browsing.domain.Browsing;
import com.uwaytech.browsing.domain.BrowsingExample;
import com.uwaytech.browsing.domain.BrowsingInfo;
import com.uwaytech.browsing.service.BrowsingService;
import com.uwaytech.httpclient.UserCenterSynProxy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by moyi on 2016-06-15.
 */
@Service
public class BrowsingServiceImpl implements BrowsingService {

    @Resource
    private BrowsingMapper browsingMapper;

    @Resource
    private ExtendBrowsingMapper extendBrowsingMapper;

    @Resource
    private UserCenterSynProxy UserCenterSynProxy;

    //存在做更新操作，不存在做插入操作
    @Override
    public int insertBrowsing(Long userId, int type, Long cellId, String point) {
        int result = 0;
        BrowsingExample example = new BrowsingExample();
        example.createCriteria().andCellIdEqualTo(cellId).andUserIdEqualTo(userId);
        List<Browsing> list = browsingMapper.selectByExample(example);
        //用户没有播放过此内容
        Browsing browsing = new Browsing();
        if (list.isEmpty()) {
            browsing.setUserId(userId);
            browsing.setType(type);
            browsing.setCellId(cellId);
            browsing.setPoint(point);
            browsing.setBrowseTime(new Date());
            result = browsingMapper.insert(browsing);
        } else {
            browsing.setPoint(point);
            browsing.setBrowseTime(new Date());
            result = browsingMapper.updateByExampleSelective(browsing, example);
        }
        return result;
    }

    @Override
    public int deleteBrowsing(Long id) {
        return browsingMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteAll(Long userId) {
        BrowsingExample example = new BrowsingExample();
        example.createCriteria().andUserIdEqualTo(userId);
        //TODO 播放记录是否有意义
        return browsingMapper.deleteByExample(example);
    }

    @Override
    public Page<BrowsingInfo> selectBrowsingInfo(int type, Long userId, Date startTime, Date endTime, int start, int pageSize) {
        PageHelper.startPage(start, pageSize);
        Map<String,Object> map = new HashMap<>();
        map.put("type",type);
        map.put("userId",userId);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        Page<BrowsingInfo>  browsingInfos =(Page<BrowsingInfo>)extendBrowsingMapper.selectBrowsingInfo(map);
        return browsingInfos;
    }

    @Override
    public Page<Browsing> queryCourseBrowse(Long courseId, Integer pageNum, Integer pageSize) {
        //查询课程播放记录
        BrowsingExample example = new BrowsingExample();
        example.createCriteria().andCellIdEqualTo(courseId);
        example.setOrderByClause("browse_time desc");
        PageHelper.startPage(pageNum,pageSize);
        Page<Browsing> page = (Page<Browsing>) browsingMapper.selectByExample(example);
        //查询uc用户头像
        if(null != page.getResult() && page.getResult().size() > 0){
            Long[] userIds = new Long[page.getResult().size()];
            int i = 0;
            for (Browsing browsing : page.getResult()){
                userIds[i] = browsing.getUserId();
                i++;
            }
            //TODO 调用UC接口获取用户头像

        }
        return page;
    }
}
