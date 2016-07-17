package com.uwaytech.cool.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期常用操作类
 *
 * @author wangyichao
 */
public class DateUtil {

    /**
     *
     */
    private static Object timeZoneLock = new Object();
    /**
     *
     */
    private static String timeZoneList[][] = null;
    /**
     *
     */
    @SuppressWarnings("rawtypes")
	private static Map dateFormatCache = new HashMap();

    /**
     *
     */
    public static final String NO_SEPARATOR_DATE = "yyyyMMdd";
    /**
     *
     */
    public static final String DATE = "yyyy-MM-dd";
    /**
     *
     */
    public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    /**
     * 定义 时间差类型枚举 TimeType
     */
    public enum TimeType {
        /**
         *
         */
        diffMillis, diffSecs, diffMins, diffHours, diffDays
    }

    /**
     * 获取当前日期
     *
     * @param format 日期格式
     * @return String
     */
    public static String getCurrentTime(String format) {
        SimpleDateFormat formatObj = new SimpleDateFormat(format);
        String date = formatObj.format(new Date());
        return date;
    }

    /**
     * 将日期分隔符去掉 支持 "/","-"字符
     *
     * @param date 有分隔符日期
     * @return 无分隔符日期
     */
    public static String toIndexDate(String date) {
        String indexDate = "19700101";
        if (date != null && date.trim().length() >= 10) {
            int i = date.indexOf(" ");
            if (i != -1) {
                indexDate = date.substring(0, i).replaceAll("-", "").replace(
                        "/", "");
            } else {
                indexDate = date.replaceAll("-", "").replace("/", "");
            }
        }
        return indexDate;
    }

    /**
     * 将无分隔符日期转换为普通分隔符日期 1970-01-01
     *
     * @param indexDate String
     * @return String
     */
    public static String toDate(String indexDate) {
        StringBuilder sb = new StringBuilder("1970-01-01");
        if (indexDate != null && indexDate.trim().length() == 8) {
            sb = new StringBuilder(indexDate);
            sb.insert(4, '-');
            sb.insert(7, '-');
        }
        return sb.toString();
    }

    /**
     * 对日期进行格式化
     *
     * @param format String
     * @return String
     */
    public static String dateFormat(String format) {
        Date date = new Date();
        SimpleDateFormat formatObj = new SimpleDateFormat(format);
        String formatTime = formatObj.format(date);
        return formatTime;
    }

    /**
     * 根据num获得距今的日期
     *
     * @param num    int
     * @param format 格式
     * @return 距今的日期
     */
    public static String getDate(int num, String format) {

        GregorianCalendar gc = new GregorianCalendar();
        gc.add(Calendar.DAY_OF_MONTH, num);
        Date time = gc.getTime();
        SimpleDateFormat formatObj = new SimpleDateFormat(format);
        String date = formatObj.format(time);
        return date;
    }

    /**
     * 计算分钟差
     *
     * @param data1    时间1 （yyyy-MM-dd hh:mm）or（yyyy/MM/dd hh:mm）等
     * @param data2    时间2 （yyyy-MM-dd hh:mm）or（yyyy/MM/dd hh:mm）等
     * @param timeType 时间差类型枚举
     * @return long
     */
    public static long timeDiff(String data1, String data2, TimeType timeType) {

        Calendar c1 = new GregorianCalendar(Integer.parseInt(data1.substring(0,
                4)), Integer.parseInt(data1.substring(5, 7)), Integer
                .parseInt(data1.substring(8, 10)), Integer.parseInt(data1
                .substring(11, 13)), Integer.parseInt(data1.substring(14, 16)));
        Calendar c2 = new GregorianCalendar(Integer.parseInt(data2.substring(0,
                4)), Integer.parseInt(data2.substring(5, 7)), Integer
                .parseInt(data2.substring(8, 10)), Integer.parseInt(data2
                .substring(11, 13)), Integer.parseInt(data2.substring(14, 16)));

        long diffMillis = 0;

        if (c1.after(c2)) {
            diffMillis = c1.getTimeInMillis() - c2.getTimeInMillis();
        } else {
            diffMillis = c2.getTimeInMillis() - c1.getTimeInMillis();
        }
        if (timeType == TimeType.diffMillis) {
            return diffMillis;
        } else if (timeType == TimeType.diffSecs) {
            return diffMillis / (1000);
        } else if (timeType == TimeType.diffMins) {
            return diffMillis / (60 * 1000);
        } else if (timeType == TimeType.diffHours) {
            return diffMillis / (60 * 60 * 1000);
        } else {
            return diffMillis / (24 * 60 * 60 * 1000);
        }
    }

    /**
     * 取得指定日期增加/减少（n为负数时）n天后的日期
     *
     * @param date Date
     * @param n    int
     * @return Date
     */
    public static Date add(Date date, int n) {
        if (date == null)
            return null;
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(Calendar.DATE, n);
        return gc.getTime();
    }

    /**
     * 添加月份
     *
     * @author b2c shiwei
     * @date 2012-8-29
     */
    public static Date addMonth(Date date, int n) {
        if (date == null)
            return null;
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(Calendar.MONTH, n);
        return gc.getTime();
    }

    /**
     * 添加分钟
     *
     * @param
     * @return
     * @author Administrator
     * @date 2013-8-2
     */
    public static Date addMinutes(Date date, int n) {
        if (date == null)
            return null;
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(Calendar.MINUTE, n);
        return gc.getTime();
    }


    /**
     * 取得当前日期增加/减少（n为负数时）n天后的日期
     *
     * @param n int
     * @return Date
     */
    public static Date add(int n) {
        return add(new Date(), n);
    }

    /**
     * 根据指定日期格式将给出的日期字符串dateStr转换成一个日期对象
     *
     * @param dateStr String
     * @param pattern String
     * @return Date
     */
    public static Date parseDate(String dateStr, String pattern) {
        if (dateStr == null || dateStr.length() == 0 || pattern == null
                || pattern.length() == 0)
            return null;
        DateFormat fmt = new SimpleDateFormat(pattern);
        Date result = null;
        try {
            result = fmt.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Date parseAccuDate(String dateStr) {
        return parseDate(dateStr, "yyyy-MM-dd HH:mm:ss:SSS");
    }

    /**
     * 将特定格式（yyyy-MM-dd HH:mm:ss）的字符串转换成日期对象
     *
     * @param dateStr String
     * @return Date
     */
    public static Date parseDate(String dateStr) {
        return parseDate(dateStr, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 将特定格式（yyyy-MM-dd）的字符串转换成日期对象
     *
     * @param dateStr String
     * @return Date
     */
    public static Date parseShortDate(String dateStr) {
        return parseDate(dateStr, "yyyy-MM-dd");
    }

    /**
     * 将日期对象按默认时区转换成"yyyy-MM-dd HH:mm:ss"格式的字符串
     *
     * @param d Date
     * @return String
     */
    public static String asHtml(Date d) {
        return asHtml(d, TimeZone.getDefault());
    }

    /**
     * 将日期对象按指定时区转换成"yyyy-MM-dd HH:mm:ss"格式的字符串
     *
     * @param date     Date
     * @param timeZone TimeZone
     * @return String
     */
    @SuppressWarnings("unchecked")
	public static String asHtml(Date date, TimeZone timeZone) {
        String key = timeZone.getID();
        DateFormat formatter = (DateFormat) dateFormatCache.get(key);
        if (formatter == null) {
            formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            formatter.setTimeZone(timeZone);
            dateFormatCache.put(key, formatter);
        }
        synchronized (formatter) {
            String s = formatter.format(date);
            return s;
        }
    }

    /**
     * 将日期对象转换成"yyyy-MM-dd HH:mm:ss"格式的字符串
     *
     * @param date Date
     * @return String
     */
    @SuppressWarnings("unchecked")
	public static String asString(Date date) {
        if (date == null)
            return null;
        String key = "asString";
        DateFormat formatter = (DateFormat) dateFormatCache.get(key);
        if (formatter == null) {
            formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateFormatCache.put(key, formatter);
        }
        return formatter.format(date);
    }

    /**
     * 将data对象转换成"yyyy-MM-dd"格式的字符串
     *
     * @param date Date
     * @return String
     */
    public static String asShortString(Date date) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }

    /**
     * 将data对象按指定时区转换成"yyyy-MM-dd"格式的字符串
     *
     * @param date     Date
     * @param timeZone TimeZone
     * @return String
     */
    public static String asShortString(Date date, TimeZone timeZone) {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setTimeZone(timeZone);
        return formatter.format(date);
    }

    /**
     * 将date对象按指定时区转换成"yyyy_MM_dd"格式的字符串
     *
     * @param date     Date
     * @param timeZone TimeZone
     * @return String
     */
    public static String asNameSuffix(Date date, TimeZone timeZone) {
        DateFormat formatter = new SimpleDateFormat("yyyy_MM_dd");
        formatter.setTimeZone(timeZone);
        return formatter.format(date);
    }

    /**
     * 获取给定秒的Date
     *
     * @param time int
     * @return Date
     */
    public static Date getDate(int time) {
        return new Date(time * 1000L);
    }

    /**
     * 获取当前时间的秒
     *
     * @return int
     */
    public static int currentTimeSeconds() {
        return (int) (System.currentTimeMillis() / 1000L);
    }

    /**
     * 获取两日期的秒差
     *
     * @param a Date
     * @param b Date
     * @return int
     */
    public static int secondsAfter(Date a, Date b) {
        return (int) ((a.getTime() - b.getTime()) / 1000L);
    }

    /**
     * 获取两日期的秒差
     *
     * @param a Date
     * @param b Date
     * @return int
     */
    public static int secondsBefore(Date a, Date b) {
        return secondsAfter(b, a);
    }

    /**
     * 获取指定的Date对象
     *
     * @param yy int
     * @param mm int
     * @param dd int
     * @return Date
     */
    public static Date getDate(int yy, int mm, int dd) {
        return (new GregorianCalendar(yy, mm - 1, dd)).getTime();
    }

    /**
     * 获取当前时间的秒
     *
     * @return int
     */
    public static int unixTimeStamp() {
        return (int) (System.currentTimeMillis() / 1000L);
    }

    /**
     * 获取指定Date的秒
     *
     * @param date Date
     * @return int
     */
    public static int unixTimeStamp(Date date) {
        return (int) (date.getTime() / 1000L);
    }

    /**
     * 获取全部时区
     *
     * @return String[][]
     */
    public static String[][] getTimeZoneList() {
        synchronized (timeZoneLock) {
            if (timeZoneList == null) {
                Date now = new Date();
                String timeZoneIDs[] = TimeZone.getAvailableIDs();
                timeZoneList = new String[timeZoneIDs.length][2];
                for (int i = 0; i < timeZoneList.length; i++) {
                    String zoneID = timeZoneIDs[i];
                    timeZoneList[i][0] = zoneID;
                    timeZoneList[i][1] = getTimeZoneName(zoneID, now, Locale
                            .getDefault());
                }

            }
        }
        return timeZoneList;
    }

    /**
     * 获得当前时间
     *
     * @param zoneID String
     * @param now    Date
     * @param locale Locale
     * @return String
     */
    private static String getTimeZoneName(String zoneID, Date now, Locale locale) {
        TimeZone zone = TimeZone.getTimeZone(zoneID);
        StringBuffer buf = new StringBuffer();
        int offset = zone.getRawOffset();
        if (zone.inDaylightTime(now) && zone.useDaylightTime())
            offset = (int) (offset + 0x36ee80L);
        if (offset < 0)
            buf.append("(GMT-");
        else
            buf.append("(GMT+");
        offset = Math.abs(offset);
        int hours = offset / 0x36ee80;
        int minutes = (offset % 0x36ee80) / 60000;
        if (hours < 10)
            buf.append("0").append(hours).append(":");
        else
            buf.append(hours).append(":");
        if (minutes < 10)
            buf.append("0").append(minutes);
        else
            buf.append(minutes);
        buf.append(") ").append(zoneID).append(" ");
        buf.append(zone.getDisplayName(true, 0, locale));
        return buf.toString();
    }

    /**
     * 将日期类型转化为字符串，默认格式yyyy-MM-dd
     *
     * @param date Date
     * @return 返回结果
     */
    public static String dateToStr(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
        return newFormat.format(date);
    }

    public static String accuDateToStr(Date d) {
        return dateToStr(d, "yyyy-MM-dd HH:mm:ss:SSS");
    }

    /**
     * 将日期类型转化为字符串，格式yyyy-MM-dd HH:mm
     *
     * @param date Date
     * @return 返回结果
     */
    public static String dateTimeToStr(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return newFormat.format(date);
    }

    /**
     * 将日期类型转化为字符串,根据传入的格式输出日期（如YYYY年MM月DD日等）
     *
     * @param date    日期
     * @param sFormat 日期格式
     * @return 返回结果
     */
    public static String dateToStr(Date date, String sFormat) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat newFormat = new SimpleDateFormat(sFormat);
        return newFormat.format(date);
    }

    /**
     * 字符串转化为日期类型，默认格式yyyy-MM-dd
     *
     * @param strDate 字符串
     * @return 返回日期
     */
    public static Date strToDate(String strDate) {
        if (null == strDate || "".equals(strDate.trim())) {
            return null;
        }
        SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return newFormat.parse(strDate);
        } catch (ParseException ex) {
            return null;
        }
    }

    /**
     * 字符创转化成指定格式的日期类型(如yyyy年MM月DD日->date)
     *
     * @param strDate 字符串
     * @param sFormat 字符串
     * @return 返回日期
     */
    public static Date strToDate(String strDate, String sFormat) {
        if (null == strDate || "".equals(strDate.trim())) {
            return null;
        }
        SimpleDateFormat newFormat = new SimpleDateFormat(sFormat);
        try {
            return newFormat.parse(strDate);
        } catch (ParseException ex) {
            return null;
        }
    }

    /**
     * 将日期String转化成当前年龄(如1984年09月8日->24)
     *
     * @param date 日期
     * @return 返回结果
     */
    public static String dateToAge(String date) {
        if (null == date || "".equals(date.trim())) {
            return null;
        }
        String sReturn = "";
        try {
            Date dt = strToDate(date);
            String bMonDay = new SimpleDateFormat("MMdd").format(dt);
            String nMonDay = new SimpleDateFormat("MMdd").format(new Date());
            long md = Long.parseLong(nMonDay) - Long.parseLong(bMonDay);

            String sYear = date.trim().substring(0, 4);
            long lYear = Long.parseLong(sYear);
            SimpleDateFormat newFormat = new SimpleDateFormat("yyyy");
            long curYear = Long.parseLong(newFormat
                    .format(new Date()));
            if (md < 0) {
                sReturn = String.valueOf(curYear - lYear - 1);
            } else {
                sReturn = String.valueOf(curYear - lYear);
            }

            return sReturn;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 日期中本月月末的天
     *
     * @param date 日期
     * @return 返回结果
     */
    public static int getMonthEndDay(java.sql.Date date) {
        // 取 本月 月末的天
        if (date == null)
            return 0;
        int year = Integer.parseInt(date.toString().substring(0, 4));
        int month = Integer.parseInt(date.toString().substring(5, 7));
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONDAY, month - 1);
        cal.set(Calendar.DATE, 1);
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);

    }

    /**
     * 日期中本月月末的日期
     *
     * @param date 日期
     * @return 返回结果
     * @throws Exception 抛出异常
     */
    public static java.sql.Date getMonthEnd(java.sql.Date date)
            throws Exception {
        // 取 本月 月末
        if (date == null)
            return null;
        int year = Integer.parseInt(date.toString().substring(0, 4));
        int month = Integer.parseInt(date.toString().substring(5, 7));
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONDAY, month - 1);
        cal.set(Calendar.DATE, getMonthEndDay(date));

        java.sql.Date d = new java.sql.Date(cal.getTime().getTime());
        return d;
    }

    /**
     * 日期中本月月中的日期
     *
     * @param date 日期
     * @return 返回结果
     * @throws Exception 抛出异常
     */
    public static java.sql.Date getMonthMid(java.sql.Date date)
            throws Exception {
        // 取 本月 月中
        if (date == null)
            return null;
        int year = Integer.parseInt(date.toString().substring(0, 4));
        int month = Integer.parseInt(date.toString().substring(5, 7));
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONDAY, month - 1);
        cal.set(Calendar.DATE, 15);

        java.sql.Date d = new java.sql.Date(cal.getTime().getTime());
        return d;

    }

    /**
     * 日期中下过月月初的日期
     *
     * @param date 日期
     * @return 返回结果
     * @throws Exception 抛出异常
     */
    public static java.sql.Date getNextMonthFirst(java.sql.Date date)
            throws Exception {
        // 取 次月 月初
        if (date == null)
            return null;
        int year = Integer.parseInt(date.toString().substring(0, 4));
        int month = Integer.parseInt(date.toString().substring(5, 7));

        if (month == 12) {
            year = year + 1;
            month = 1;
        } else
            month = month + 1;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONDAY, month - 1);
        cal.set(Calendar.DATE, 1);

        java.sql.Date d = new java.sql.Date(cal.getTime().getTime());
        return d;
    }

    /**
     * 日期中上月月末的日期
     *
     * @param date 日期
     * @return 返回结果
     * @throws Exception 抛出异常
     */
    public static java.sql.Date getPreviousMonthEnd(java.sql.Date date)
            throws Exception {
        // 取 上一月末
        if (date == null)
            return null;
        int year = Integer.parseInt(date.toString().substring(0, 4));
        int month = Integer.parseInt(date.toString().substring(5, 7));

        if (month == 1) {
            year = year - 1;
            month = 12;
        } else
            month = month - 1;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONDAY, month - 1);
        cal.set(Calendar.DATE, 1);
        java.sql.Date d = new java.sql.Date(cal.getTime().getTime());
        cal.set(Calendar.DATE, getMonthEndDay(d));
        d = new java.sql.Date(cal.getTime().getTime());
        return d;
    }

    /**
     * 传入日期上一天的日期
     *
     * @param date 日期
     * @return 返回结果
     * @throws Exception 抛出异常
     */
    public static java.sql.Date getPreviousDay(java.sql.Date date)
            throws Exception {
        // 取 上一天
        if (date == null)
            return null;
        int year = Integer.parseInt(date.toString().substring(0, 4));
        int month = Integer.parseInt(date.toString().substring(5, 7));
        int day = Integer.parseInt(date.toString().substring(8, 10));
        if (day == 1) {
            if (month == 1) {
                year = year - 1;
                month = 12;
                day = 31;
            } else {
                month = month - 1;
                Calendar c = Calendar.getInstance();
                c.set(Calendar.YEAR, year);
                c.set(Calendar.MONTH, month - 1);
                c.set(Calendar.DATE, 1);
                java.sql.Date d = new java.sql.Date(c.getTime().getTime());
                day = getMonthEndDay(d);
            }
        } else
            day = day - 1;
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DATE, day);
        java.sql.Date d = new java.sql.Date(c.getTime().getTime());
        // 可以这样 d = java.sql.Date.valueOf("2004-01-01");
        return d;
    }

    /**
     * 传入日期下一天的日期
     *
     * @param date 日期
     * @return 返回结果
     * @throws Exception 抛出异常
     */
    public static java.sql.Date getNextDay(java.sql.Date date) throws Exception {
        // 取 下一天
        if (date == null)
            return null;
        int year = Integer.parseInt(date.toString().substring(0, 4));
        int month = Integer.parseInt(date.toString().substring(5, 7));
        int day = Integer.parseInt(date.toString().substring(8, 10));

        if (day == getMonthEndDay(date)) {
            if (month == 12) {
                year = year + 1;
                month = 1;
            } else
                month = month + 1;
            day = 1;
        } else
            day = day + 1;
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DATE, day);
        java.sql.Date d = new java.sql.Date(c.getTime().getTime());

        return d;
    }

    /**
     * 获得日期的下一个星期一的日期
     *
     * @param date 日期
     * @return java.sql.Date
     */
    public static java.sql.Date getNextMonday(java.sql.Date date) {
        Calendar d1 = Calendar.getInstance();
        d1.setTime(date);
        java.sql.Date result = date;
        do {
            d1 = (Calendar) d1.clone();
            d1.add(Calendar.DATE, 1);
        } while (d1.get(Calendar.DAY_OF_WEEK) != 2);

        result = new java.sql.Date(d1.getTime().getTime());
        return result;
    }

    /**
     * 日期加天数计算结果
     *
     * @param date SQL日期
     * @param days 增加天数
     * @return 返回结果
     */
    public static String getAddDaysDate(Date date, int days) {
        long das = date.getTime() / (3600 * 24 * 1000) + days + 1;

        return dateToStr(new Date(das * (3600 * 24 * 1000)));
    }

    /**
     * 日期加天数计算结果(包括开始时间)
     *
     * @param date 日期
     * @param days 增加天数
     * @return 返回结果
     */
    public static Date getDateAsDays(Date date, int days) {
        long das = date.getTime() / (3600 * 24 * 1000) + days - 1;
        Date newDate = new Date(das * (3600 * 24 * 1000));
        return newDate;
    }

    /**
     * 计算日期加天数后的日期
     *
     * @param date 日期
     * @param days 增加天数
     * @return 返回结果
     */
    public static String getAddDaysDate(String date, int days) {
        Date d = strToDate(date);
        long das = d.getTime() / (3600 * 24 * 1000) + days + 1;

        return dateToStr(new Date(das * (3600 * 24 * 1000)));
    }

    /**
     * 两个日期进行计算相差天数
     *
     * @param sd 开始日期
     * @param ed 结束日期
     * @return long 返回结果
     */
    public static long getXcDays(Date sd, Date ed) {
        return (ed.getTime() - sd.getTime()) / (3600 * 24 * 1000);
    }

    public static Integer getXcSecs(Date d1, Date d2) {

        return (int) ((d2.getTime() - d1.getTime()) / 1000);
    }

    public static Integer getXcMins(Date d1, Date d2) {

        return getXcSecs(d1, d2) / 60;
    }

    public static Integer minsToSecs(Integer answerTime) {
        return answerTime * 60;
    }


    /**
     * 计算传入两个日期相差月数，同月返回0
     *
     * @param mind 开始日期
     * @param maxd 结束日期
     * @return int 返回结果
     */
    public static int getXcyf(java.sql.Date mind, java.sql.Date maxd) {
        // 月份 相差 同月为 0
        int year = Integer.parseInt(maxd.toString().substring(0, 4));
        int month = Integer.parseInt(maxd.toString().substring(5, 7));
        int year1 = Integer.parseInt(mind.toString().substring(0, 4));
        int month1 = Integer.parseInt(mind.toString().substring(5, 7));
        int xc = (year - year1) * 12 + (month - month1);
        if (xc < 0)
            xc = 0;
        return xc;
    }

    /**
     * 计算2个日期之间的工作日(去除周六、日)天数
     *
     * @param sd 开始日期
     * @param ed 结束日期
     * @return int
     */
    public static int getWorkingDays(java.sql.Date sd, java.sql.Date ed) {
        Calendar d1 = Calendar.getInstance();
        Calendar d2 = Calendar.getInstance();
        d1.setTime(sd);
        d2.setTime(ed);

        int result = -1;
        if (d1.after(d2)) { // swap dates so that d1 is start and d2 is end
            Calendar swap = d1;
            d1 = d2;
            d2 = swap;
        }

        int charge_start_date = 0;// 开始日期的日期偏移量
        int charge_end_date = 0;// 结束日期的日期偏移量
        // 日期不在同一个日期内
        int stmp;
        int etmp;
        stmp = 7 - d1.get(Calendar.DAY_OF_WEEK);
        etmp = 7 - d2.get(Calendar.DAY_OF_WEEK);

        if (stmp != 0 && stmp != 6) {
            // 开始日期为星期六和星期日时偏移量为0
            charge_start_date = stmp;
        }
        if (etmp != 0 && etmp != 6) {
            // 结束日期为星期六和星期日时偏移量为0
            charge_end_date = etmp - 1;
        }
        result = (((int) DateUtil.getXcDays(getNextMonday(sd),
                getNextMonday(ed)) + 1) / 7)
                * 5 + charge_start_date - charge_end_date;
        return result;
    }

    /**
     * 计算2个日期之间的休息日(周六、日)天数
     *
     * @param sd 开始日期
     * @param ed 结束日期
     * @return int
     */
    public static int getHolidays(java.sql.Date sd, java.sql.Date ed) {
        return (int) DateUtil.getXcDays(sd, ed) + 1 - getWorkingDays(sd, ed);

    }

    /**
     * 获取当前日期，并返回java.sql.Date数据类型
     *
     * @return 返回java.sql.Date数据类型
     */
    public static java.sql.Date getNowSqlDate() {
        java.sql.Date cd = null;
        try {
            Date d = new Date();
            cd = new java.sql.Date(d.getTime());
        } catch (Exception e) {
        }
        return cd;
    }

    /**
     * 取得该日期的年
     *
     * @param mdate 日期
     * @return int
     */
    public static int getYear(Date mdate) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy");
        String syear = sd.format(mdate);
        return Integer.parseInt(syear);
    }

    /**
     * 取得该字符串的年
     *
     * @param mdate 日期形字符串，如“2002-10-11” "2002-9-2"
     * @return int
     */
    public static int getYear(String mdate) {
        String syear = mdate.substring(0, 4);
        return Integer.parseInt(syear);
    }

    /**
     * 取得该日期的月
     *
     * @param mdate 日期形字符串，如“2002-10-11” "2002-9-2"
     * @return int
     */
    public static int getMonth(Date mdate) {
        SimpleDateFormat sm = new SimpleDateFormat("MM");
        String smon = sm.format(mdate);
        return Integer.parseInt(smon);
    }

    /**
     * 取得该字符串的月
     *
     * @param mdate 日期形字符串
     * @return int
     */
    public static int getMonth(String mdate) {
        String smon1, smon2;
        StringBuffer smon = new StringBuffer();
        smon1 = mdate.substring(5, 6);
        smon2 = mdate.substring(6, 7);
        if (smon2.equals("-")) {
            smon.append(smon1);
        } else {
            smon.append(smon1);
            smon.append(smon2);
        }
        return Integer.parseInt(smon.toString());
    }

    /**
     * 取得该日期的天
     *
     * @param mdate 日期
     * @return int
     */
    public static int getDay(Date mdate) {

        SimpleDateFormat sd = new SimpleDateFormat("dd");
        String sday = sd.format(mdate);
        return Integer.parseInt(sday);
    }

    /**
     * 取得该日期的小时
     *
     * @param mdate 日期
     * @return int
     */
    public static int getHour(Date mdate) {

        SimpleDateFormat sd = new SimpleDateFormat("HH");
        String sday = sd.format(mdate);
        return Integer.parseInt(sday);
    }

    /**
     * 取得该日期的分钟
     *
     * @param mdate 日期
     * @return int
     */
    public static int getMinute(Date mdate) {

        SimpleDateFormat sd = new SimpleDateFormat("mm");
        String sday = sd.format(mdate);
        return Integer.parseInt(sday);
    }

    /**
     * 取得该字符串的天
     *
     * @param mdate 日期形字符串，如“2002-10-11” "2002-9-2"
     * @return int
     */
    public static int getDay(String mdate) {
        int i = 0, j;
        int l = mdate.length();
        String s = "", sday1, sday2;
        StringBuffer sday = new StringBuffer();
        for (j = 0; j < 2; j++, s = "") {
            while (!(s.equals("-"))) {
                s = mdate.substring(i, i + 1);
                i++;
            }
        }
        sday1 = mdate.substring(i, i + 1);
        sday.append(sday1);
        if (!(i == l - 1)) {
            sday2 = mdate.substring(i + 1, i + 2);
            sday.append(sday2);
        }
        return Integer.parseInt(sday.toString());
    }

    /**
     * 获得当前年-月最大天数
     *
     * @param year  年度
     * @param month 月份
     * @return int
     */

    public static int getMonMDay(int year, int month) {
        if ((month == 1) || (month == 3) || (month == 5) || (month == 7)
                || (month == 8) || (month == 10) || (month == 12)) {
            return 31;
        } else {
            if (month != 2) {
                return 30;
            } else {
                if (ifLeap(year)) {
                    return 29;
                } else {
                    return 28;
                }
            }
        }

    }

    /**
     * 如果是闰年，返回true 否则返回false
     *
     * @param year 传入的年份
     * @return boolean 返回结果
     */
    public static boolean ifLeap(int year) {
        if (!(year % 4 == 0)) {
            return false;
        } else {
            if (year % 100 != 0) {
                return true;
            } else {
                if (year % 400 == 0) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    /**
     * 判断是否日期类型，如果是日期类型，返回true else 返回false
     *
     * @param mdate 字符串
     * @return boolean
     */
    public static boolean IsDate(String mdate) {
        String s;
        int syear_int, smon_int, sday_int;
        int k, m = mdate.indexOf("-");
        if (m == -1) {
            return false;
        } else {
            s = mdate.substring(0, m);
            try {
                k = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                return false;
            }
            if (k > 1900) {
                syear_int = getYear(mdate);
            } else {
                return false;
            }
        }

        if (syear_int < 9999) {
            smon_int = getMonth(mdate);

            if ((smon_int > 0) && (smon_int < 13)) {
                sday_int = getDay(mdate);
                if ((sday_int > 0) && (sday_int < 32)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 两个日期进行比较
     *
     * @param rq1 日期
     * @param rq2 日期
     * @return 如果rq1小于rq2, 返回-1;rq1等于rq2,返回0;rq1大于rq2,返回1;
     */
    public static int compareDate(Date rq1, Date rq2) {
        int flag = 0;
        try {
            flag = rq1.compareTo(rq2);
        } catch (Exception e) {
        }
        return flag;
    }

    /**
     * 判断字符串是否是日期时间 例"1981-12-12 12:12:12"
     *
     * @param str
     * @return
     */
    public static boolean isDateTime(String str) {
        if (str == null)
            return true;
        return str
                .matches("^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29)) ([0-1][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$");
    }

    /**
     * 判断字符串是否是日期 例如"1981-12-12"
     *
     * @param str
     * @return
     */
    public static boolean isDate(String str) {
        if (str == null)
            return true;
        return str
                .matches("^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29))$");
    }

    /**
     * 将日期类型转化为字符串，格式yyyy-MM-dd HH:mm
     *
     * @param strDate
     * @return 返回结果
     */
    public static Date strToDateTime(String strDate) {
        if (strDate == null) {
            return null;
        }
        SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            return newFormat.parse(strDate);
        } catch (ParseException ex) {
            return strToDate(strDate);
        }
    }

    /**
     * 判断字符串是否是时间 例"12:12:12"
     *
     * @param str
     * @return
     */
    public static boolean isTime(String str) {
        if (str == null)
            return true;
        return str.matches("^([0-1][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$");
    }

    /**
     * 显示时间，如果与当前时间差别小于一天，则自动用**秒(分，小时)前，如果大于一天则用format规定的格式显示
     *
     * @param ctime  时间
     * @param format 格式 格式描述:例如:yyyy-MM-dd yyyy-MM-dd HH:mm:ss
     * @return
     * @author chenwenyi
     */
    public static String showTime(Date ctime, String format) {
        String r = "";
        if (ctime == null)
            return r;
        if (format == null)
            format = "yyyy-MM-dd";

        long nowtimelong = System.currentTimeMillis();
        long ctimelong = ctime.getTime();
        //若比当前时间大， 应用服务器和数据库服务器时间不同步，显示为刚刚
        if (ctimelong > nowtimelong) {
            nowtimelong = ctimelong;
        }
        long result = nowtimelong - ctimelong;

        int day = getDay(ctime);
        int nowDay = getDay(new Date());

        if ((result / 1000) <= 0) {
            r = "刚刚";
        } else if (result > 0 && result < 60000) {// 一分钟内
            long seconds = result / 1000;
            r = seconds + "秒钟前";
        } else if (result >= 60000 && result < 3600000) {// 一小时内
            long seconds = result / 60000;
            r = seconds + "分钟前";
        } else if (result >= 3600000 && result < 86400000) {// 一天内
            long seconds = result / 3600000;
            r = seconds + "小时前";
        } else if (result < 172800000 && Math.abs(nowDay - day) == 1) {// 两天内 result >= 86400000 && result < 172800000
            r = "昨天 " + DateUtil.dateToStr(ctime, "HH:mm");
        } else if (result < 259200000 && Math.abs(nowDay - day) == 2) {// 三天内 result >= 172800000 && result < 259200000
            r = "前天 " + DateUtil.dateToStr(ctime, "HH:mm");
        } else if (getYear(getCurrentTime("yyyy-MM-dd HH:mm:ss")) == getYear(DateUtil.dateToStr(ctime, "yyyy-MM-dd"))) {// 今年
            r = DateUtil.dateToStr(ctime, "MM-dd HH:mm");
        } else {// 日期格式
            r = DateUtil.dateToStr(ctime, format);
        }
        return r;
    }

    /**
     * 按条件得到查询时间 年度 季度 月
     *
     * @param
     * @return Date[]
     * @date Dec 30, 2011
     * @author lijian
     */
    public static Date[] getMyTime(String year, Integer quarter, Integer month) {
        Date[] mydate = new Date[2];
        Date startTime = null;
        Date endTime = null;
        // 查询条件年份是否为空或不存在
        if (null != year && !year.equals("-1")) {
            startTime = DateUtil.parseShortDate(year + "-01-01");
            endTime = DateUtil.parseShortDate(year + "-12-31");
            // 查询条件季度是否为空或不存在
            if (quarter != null && quarter != -1) {
                switch (quarter) {
                    case 1:
                        startTime = DateUtil.parseShortDate(year + "-01-01");
                        endTime = DateUtil.parseShortDate(year + "-03-31");
                        break;
                    case 2:
                        startTime = DateUtil.parseShortDate(year + "-04-01");
                        endTime = DateUtil.parseShortDate(year + "-06-31");
                        break;
                    case 3:
                        startTime = DateUtil.parseShortDate(year + "-07-01");
                        endTime = DateUtil.parseShortDate(year + "-09-31");
                        break;
                    case 4:
                        startTime = DateUtil.parseShortDate(year + "-10-01");
                        endTime = DateUtil.parseShortDate(year + "-12-31");
                        break;
                }
            }

            if (month != null && month != -1) {
                startTime = DateUtil.parseShortDate(year + "-" + month + "-01");
                if (month == 2) {
                    endTime = DateUtil.parseShortDate(year + "-" + month + "-29");
                } else {
                    endTime = DateUtil.parseShortDate(year + "-" + month + "-31");
                }
            }
        }
        mydate[0] = startTime;
        mydate[1] = endTime;
        return mydate;
    }


    /**
     * 计算剩余时间
     *
     * @param
     * @return
     * @date Sep 13, 2011
     * @author chenwenyi
     * @see
     */
    public static String showTimeRemaining(Date ctime, String format, String type) {
        String r = "";
        if (format == null)
            format = "yyyy-MM-dd";

        long nowtimelong = System.currentTimeMillis();
        long ctimelong = ctime.getTime();
        long result = ctimelong - nowtimelong;

        int day = 0, hour = 0, minute = 0, second = 0;

        if ("day".equalsIgnoreCase(type)) {
            long d = result % 86400000;
            r = String.valueOf(d > 0 ? (result / 86400000) + 1 : result / 86400000);
            return r;
        }

        if (result > 0) {
            day = (int) (result / 86400000);
            hour = (int) (result % 86400000 / 3600000);
            minute = (int) (result % 86400000 % 3600000 / 60000);
            second = (int) (result % 86400000 % 3600000 % 60000 / 1000);
            if (day > 0) {
                r = day + "天 " + (hour > 0 ? hour + "小时 " : "");
            } else if (hour > 0) {
                r = hour + "小时 " + (minute > 0 ? minute + "分钟 " : "");
            } else {
                r = (minute > 0 ? minute + "分钟 " : "") + (second > 0 ? second : 1) + "秒 ";
            }
            //r = (day != 0 ? day + "天 " : "") + (hour > 0 ? hour +"小时 " : "") + (minute > 0 ? minute +"分钟 " : "") + (second > 0 ? second : 1) +"秒 ";
        } else {
            r = "false";
        }

        return r;
    }

    /**
     * 获取当前日期是星期几
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }

    /**
     * 获得当前日期周日-周六的时间
     *
     * @param dt
     * @return
     * @author lijian
     * @date 2013-4-2
     */
    public static Date[] getOneWeekDate(Date dt) {
        Date[] date = new Date[2];
        Date startTime = new Date();
        Date endTime = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        int i = w;
        i = i == 0 ? 6 : i == 1 ? 5 : i == 2 ? 4 : i == 3 ? 3 : i == 4 ? 2 : i == 5 ? 1 : i == 6 ? 0 : 0;
        endTime = DateUtil.add(dt, i);
        if (w > 0) {
            w = -w;
        }
        startTime = DateUtil.add(dt, w);
        date[0] = startTime;
        date[1] = endTime;
        return date;
    }

    /**
     * 当前时间是否已超过time,
     * 若time为空返回false
     *
     * @param
     * @return
     * @date Oct 24, 2011
     * @author Administrator
     */
    public static boolean isTimeOut(Date time) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -1);
        return null != time && compareDate(time, c.getTime()) < 0;

    }

    /**
     * 当前时间是否在startTime和endTime之间
     *
     * @param
     * @return
     * @date 2012-3-28
     * @author hufeng
     */
    public static boolean during(Date startTime, Date endTime) {
        Date c = new Date();
        if (null != startTime && c.before(startTime)) {
            //如果当前时间在开始时间之前
            return false;
        } else if (null != endTime && c.after(endTime)) {
            return false;
        }
        return true;
    }

    public static String getGMTString(Date d) {
        SimpleDateFormat sdf = new SimpleDateFormat(
                "EEE, dd MMM yyyy HH:mm:ss zZ", Locale.US);
        sdf.setTimeZone(new java.util.SimpleTimeZone(8 * 1000 * 60 * 60, "GMT"));
        return sdf.format(d);
    }

    /**
     * @param
     * @return
     * @date 2012-10-25
     * @author Administrator
     */
    public static int minus(Date openTime, Date applyTime) {
        return (int) ((openTime.getTime() - applyTime.getTime()) / 1000 / 60 / 60 / 24);
    }
}
