package com.uwaytech.materialLog.dao;

import com.uwaytech.materialLog.domain.MaterialLog;
import com.uwaytech.materialLog.domain.MaterialLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MaterialLogMapper {
    int countByExample(MaterialLogExample example);

    int deleteByExample(MaterialLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MaterialLog record);

    int insertSelective(MaterialLog record);

    List<MaterialLog> selectByExample(MaterialLogExample example);

    MaterialLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MaterialLog record, @Param("example") MaterialLogExample example);

    int updateByExample(@Param("record") MaterialLog record, @Param("example") MaterialLogExample example);

    int updateByPrimaryKeySelective(MaterialLog record);

    int updateByPrimaryKey(MaterialLog record);
}