package com.uwaytech.browsing.dao;

import com.uwaytech.browsing.domain.Browsing;
import com.uwaytech.browsing.domain.BrowsingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BrowsingMapper {
    int countByExample(BrowsingExample example);

    int deleteByExample(BrowsingExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Browsing record);

    int insertSelective(Browsing record);

    List<Browsing> selectByExample(BrowsingExample example);

    Browsing selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Browsing record, @Param("example") BrowsingExample example);

    int updateByExample(@Param("record") Browsing record, @Param("example") BrowsingExample example);

    int updateByPrimaryKeySelective(Browsing record);

    int updateByPrimaryKey(Browsing record);
}