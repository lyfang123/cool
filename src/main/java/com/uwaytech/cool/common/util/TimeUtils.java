package com.uwaytech.cool.common.util;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;
import org.springframework.util.Assert;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/1/30 0030.
 */
public class TimeUtils {



    private static PeriodFormatter formatter = new PeriodFormatterBuilder()
                .appendYears()
                .appendSuffix("年", "年")
                .appendMonths()
                .appendSuffix("个月", "个月")
                .appendWeeks()
                .appendSuffix("周", "周")
                .appendDays()
                .appendSuffix("天", "天")
                .appendHours()
                .appendSuffix("小时", "小时")
                .appendMinutes()
                .appendSuffix("分钟", "分钟")
                .toFormatter();

    /**
     * 获取SimpleDateFormat
     * @param parttern 日期格式
     * @return SimpleDateFormat对象
     * @throws RuntimeException 异常：非法日期格式
     */
    private static SimpleDateFormat getDateFormat(String parttern) throws RuntimeException {
        return new SimpleDateFormat(parttern);
    }

    /**
     *
     * @param start 开始时间
     * @param end 结束时间
     * @return 时间差
     */
    public static String calcLeaveDuration(Date start, Date end) {
        Assert.notNull(start, "start time is null");
        Assert.notNull(end, "end time is null");

        DateTime sdt = new DateTime(start);
        DateTime edt = new DateTime(end);
        Period period = new Period(sdt, edt);

        return formatter.print(period);
    }

    //获取下一天时间
    public static Date getNextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, +1);//+1今天的时间加一天
        date = calendar.getTime();
        return date;
    }

    //判断当前时间是否在给定时间区间内
    public static boolean isBetweenDateTime(Date start,Date end){
        Date now = new Date();
        return now.after(start) && now.before(end);
    }

    //比较两个时间大小
    public static boolean greater(Date end,Date start){
        return  end.getTime() > start.getTime();
    }

    //获得当天最后时间
    public static Date getDayEndTime(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    //获取上一周
    public static Date getUpWeekMorning(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getTimesWeekMorning());
        calendar.add(Calendar.WEEK_OF_MONTH,-1);
        return calendar.getTime();
    }

    //获取上一个月,月初
    public static Date getUpMonth(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getTimesMonthMorning());
        calendar.add(Calendar.MONTH,-1);
        return calendar.getTime();
    }

    // 获得当天0点时间
    public static Date getTimesMorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    // 获得本周一0点时间
    public static Date getTimesWeekMorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }

    // 获得本月第一天0点时间
    public static Date getTimesMonthMorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    /**
     * 将日期字符串转化为日期。失败返回null。
     * @param date 日期字符串
     * @param parttern 日期格式
     * @return 日期
     */
    public static Date StringToDate(String date, String parttern) {
        Date myDate = null;
        if (date != null) {
            try {
                myDate = getDateFormat(parttern).parse(date);
            } catch (Exception e) {
            }
        }
        return myDate;
    }

    /**
     * 获取分钟时间戳
     * @return
     */
    public static Long getTimeOnSecond(){
        return System.currentTimeMillis()/1000;
    }

}
