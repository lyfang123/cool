package com.uwaytech.scheduler;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Map;

import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by cyb on 2016/6/7 0007.
 */
@Component
public class SchedulerFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerFactory.class);
    private static org.quartz.SchedulerFactory sf = new StdSchedulerFactory();
    private static Scheduler scheduler;
    private static String schedulerId;

    private SchedulerFactory() {
    }

    public static void addJob(String jobId, Map<String, String> jobData, Class<? extends Job> jobClass, Date jobStartDate) {
        removeJob(jobId);
        JobDetail job = JobBuilder.newJob(jobClass).setJobData(new JobDataMap(jobData)).withIdentity(createJobKey(jobId, schedulerId)).requestRecovery().build();
        Trigger trigger = newTrigger().withIdentity(createTriggerKey(jobId, schedulerId))
                .startAt(jobStartDate).build();
        try {
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public static void removeJob(String jobId) {
        JobKey jobKey = createJobKey(jobId, schedulerId);
        try {
            if (scheduler.checkExists(createJobKey(jobId, schedulerId))) {
                // 删除之前的计划任务
                scheduler.deleteJob(jobKey);
            }
        } catch (SchedulerException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private static JobKey createJobKey(String id, String schedId) {
        return JobKey.jobKey("job_" + id, schedId);
    }

    private static TriggerKey createTriggerKey(String id, String schedId) {
        return TriggerKey.triggerKey("trigger_" + id, schedId);
    }

    @PostConstruct
    public void init() {
        try {
            scheduler = sf.getScheduler();
            schedulerId = scheduler.getSchedulerInstanceId();
            scheduler.start();
        } catch (SchedulerException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
