package com.zhm.gen.common.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeUtil {

    /**
     * 秒转化时分秒
     *
     * @param s 秒数
     * @return
     * @author zhm
     * @since JDK 1.8
     */
    public static String formatTime(Long s) {

        Integer mi = 1 * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = s / dd;
        Long hour = (s - day * dd) / hh;
        Long minute = (s - day * dd - hour * hh) / mi;
        Long second = s - day * dd - hour * hh - minute * mi;

        StringBuilder sb = new StringBuilder();
        if (day > 0) {
            sb.append(day + "天");
        }
        if (hour > 0) {
            sb.append(hour + "小时");
        }
        if (minute > 0) {
            sb.append(minute + "分钟");
        }
        if (second > 0) {
            sb.append(second + "秒");
        }
        return sb.toString();
    }

    /**
     * @return
     * @Description: 判断两个时间是否相差一天
     * @create: 2020/5/4 16:20
     * @author zhm
     * @version 1.0
     */
    public static Integer reduceDayTime(Date date1, Date date2) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        return Integer.valueOf(formatter.format(date1)) - Integer.valueOf(formatter.format(date2)) == 1 ? 1 : 0;
    }
    /**
     * @return
     * @Description: 判断两个时间是否同一天
     * @create: 2020/5/4 16:20
     * @author zhm
     * @version 1.0
     */
    public static Integer compareDayTime(Date date1, Date date2) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        return Integer.valueOf(formatter.format(date1)) - Integer.valueOf(formatter.format(date2)) == 0 ? 0 : 1;
    }

    /**
     * @return
     * @Description: 获取当前day
     * @create: 2020/5/4 16:20
     * @author zhm
     * @version 1.0
     */
    public static String getDayTime(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        return formatter.format(date);
    }
    /**
     * 获取date2 大于date1的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static Integer differentDay(Date date1, Date date2) {
        int days;
        long time1 = date1.getTime() / 1000;
        long time2 = date2.getTime() / 1000;
        long diff = time2 - time1;
        days = (int) (diff / (24 * 60 * 60));
        return days;
    }

    /**
     * 获取指定年份第一周天数（xxxx年-1月-1日到当前周周六的天数）
     * @param year
     * @return
     */
    public static int getFirstWeekDay(int year) {
        //获取当前年第一周六的日期
        Calendar c1 = Calendar.getInstance(Locale.CHINA);
        c1.setWeekDate(year, 1, Calendar.SATURDAY);
        c1.setFirstDayOfWeek(Calendar.SUNDAY);
        c1.set(Calendar.HOUR, 0);
        c1.set(Calendar.MINUTE, 0);
        c1.set(Calendar.SECOND, 0);
        Date dateTime1 = c1.getTime();
        long timeInMillis1 = c1.getTimeInMillis() / 1000;
        //获取当前年1月1号的
        Calendar c2 = Calendar.getInstance(Locale.CHINA);
        c2.set(year, Calendar.JANUARY, 1);
        c2.setFirstDayOfWeek(Calendar.SUNDAY);
        c2.set(Calendar.HOUR, 0);
        c2.set(Calendar.MINUTE, 0);
        c2.set(Calendar.SECOND, 0);
        Date dateTime2 = c2.getTime();
        long timeInMillis2 = c2.getTimeInMillis() / 1000;
        int day;
        if (timeInMillis1 >= timeInMillis2) {
            day = differentDay(dateTime2, dateTime1) + 1;
        } else {

            day = 7 - differentDay(dateTime1, dateTime2);
        }
        return day;
    }

    /**
     * @param
     * @return
     * @description 返回周的时间格式
     * @author ls
     * @date 2020/6/3
     */
    public static int getWeekTime(Date date) {
        Calendar c = Calendar.getInstance();
        if (date == null) {
            date = new Date();
        }
        c.setTime(date);
        c.setMinimalDaysInFirstWeek(getFirstWeekDay(c.get(Calendar.YEAR)));
        c.setFirstDayOfWeek(Calendar.SUNDAY);
        int count = c.get(Calendar.WEEK_OF_YEAR);
        int year = c.get(Calendar.YEAR);
        int week = year * 10000 + count;
        return week;
    }

    /**
     * @return
     * @Description: 获取当前month
     * @create: 2020/5/4 16:20
     * @author zhm
     * @version 1.0
     */
    public static String getmonthTime(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
        return formatter.format(date);
    }

    /**
     * 判断当前时间距离第二天凌晨的秒数
     *
     * @return 返回值单位为[s:秒]
     */
    public Long getSecondsNextEarlyMorning() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (cal.getTimeInMillis() - System.currentTimeMillis()) / 1000;
    }

    /**
     * @return java.util.Date
     * @Param [date, n]
     * @description 时间加n天
     * @author hutiyong
     * @date 2020/5/12
     */
    public static Date addDay(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, n);
        return cal.getTime();
    }

    /**
     * @return java.util.Date
     * @Param '[date, n]'
     * @description 时间加n月
     * @author hutiyong
     * @date 2020/5/12
     */
    public static Date addMonth(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, n);
        return cal.getTime();
    }

    /**
     * @return java.util.Date
     * @Param [date, n]
     * @description 时间加n年
     * @author hutiyong
     * @date 2020/5/12
     */
    public static Date addYear(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, n);
        return cal.getTime();
    }

    /**
     * @return java.lang.String
     * @Param [date]
     * @description date转string
     * @author hutiyong
     * @date 2020/5/13
     */
    public static String formateTime(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }

    public static String formateTime(Date date, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    public static Date parseTime(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.parse(date);
    }

    public static Date parseTime(String date, String pattern) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.parse(date);
    }

    public static void main(String[] args) throws ParseException {

        System.out.println(getWeekTime(new Date()));

    }
}
