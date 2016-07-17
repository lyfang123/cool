package com.uwaytech.scheduler.job;

import com.uwaytech.qiniu.service.QiNiuService;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by moyi on 2016-06-21.
 */
public class QiNiuJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(QiNiuJob.class);

    int i = 0;

    @Resource
    QiNiuService qiNiuService;

    public void execute() throws JobExecutionException {
        try{
            LOGGER.info(">>>>>>>>>>>>>>七牛文件处理JOB开始");
            qiNiuService.execute();
            LOGGER.info("七牛文件处理JOB结束>>>>>>>>>>>>>>");
        }catch (Exception e){
            LOGGER.error("七牛JOB 发生异常：",e);
        }
    }

}
