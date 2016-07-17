package com.uwaytech.pageInfo.dao;

import com.uwaytech.pageInfo.domain.PageInfo;
import com.uwaytech.pageInfo.domain.PageInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PageInfoMapper {
    int countByExample(PageInfoExample example);

    int deleteByExample(PageInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PageInfo record);

    int insertSelective(PageInfo record);

    List<PageInfo> selectByExample(PageInfoExample example);

    PageInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PageInfo record, @Param("example") PageInfoExample example);

    int updateByExample(@Param("record") PageInfo record, @Param("example") PageInfoExample example);

    int updateByPrimaryKeySelective(PageInfo record);

    int updateByPrimaryKey(PageInfo record);
}