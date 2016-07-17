package com.uwaytech.downloadLog.dao;

import com.uwaytech.downloadLog.domain.DownloadLog;
import com.uwaytech.downloadLog.domain.DownloadLogParam;
import com.uwaytech.finance.ctrl.dto.FinanceConsume;
import com.uwaytech.finance.ctrl.dto.SupplierFinance;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtendDownloadLogMapper {

    List<DownloadLog> queryDownloadList(DownloadLogParam download);

    Long queryDownloadEcoins(DownloadLogParam download);

    Long queryDownloadNum(DownloadLogParam download);

    List<FinanceConsume> queryDownloadMonthList(@Param("schoolId")Integer schoolId);

    Long queryDownloadCounts(@Param("supplierId")Long supplierId);

    List<SupplierFinance> findSupplierResDownload(DownloadLogParam param);

    Long querySupplierDownloadEcoins(DownloadLogParam param);
}