package com.uwaytech.financeLog.dao;

import com.uwaytech.financeLog.domain.FinanceLog;
import com.uwaytech.financeLog.domain.FinanceLogExample;
import com.uwaytech.financeLog.domain.FinanceLogParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtendFinanceLogMapper {

    Long queryFinanceLogEcoins(FinanceLogParam param);

    List<FinanceLog> queryFinanceLogList(FinanceLogParam param);
}