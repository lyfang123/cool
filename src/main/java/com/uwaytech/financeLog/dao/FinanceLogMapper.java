package com.uwaytech.financeLog.dao;

import com.uwaytech.financeLog.domain.FinanceLog;
import com.uwaytech.financeLog.domain.FinanceLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FinanceLogMapper {
    int countByExample(FinanceLogExample example);

    int deleteByExample(FinanceLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FinanceLog record);

    int insertSelective(FinanceLog record);

    List<FinanceLog> selectByExample(FinanceLogExample example);

    FinanceLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FinanceLog record, @Param("example") FinanceLogExample example);

    int updateByExample(@Param("record") FinanceLog record, @Param("example") FinanceLogExample example);

    int updateByPrimaryKeySelective(FinanceLog record);

    int updateByPrimaryKey(FinanceLog record);
}