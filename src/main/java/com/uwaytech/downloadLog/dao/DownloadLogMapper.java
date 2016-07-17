package com.uwaytech.downloadLog.dao;

import com.uwaytech.downloadLog.domain.DownloadLog;
import com.uwaytech.downloadLog.domain.DownloadLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DownloadLogMapper {
    int countByExample(DownloadLogExample example);

    int deleteByExample(DownloadLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DownloadLog record);

    int insertSelective(DownloadLog record);

    List<DownloadLog> selectByExample(DownloadLogExample example);

    DownloadLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DownloadLog record, @Param("example") DownloadLogExample example);

    int updateByExample(@Param("record") DownloadLog record, @Param("example") DownloadLogExample example);

    int updateByPrimaryKeySelective(DownloadLog record);

    int updateByPrimaryKey(DownloadLog record);
}