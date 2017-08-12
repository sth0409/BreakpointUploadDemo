/*
 * Copyright (C) 2015 Drakeet <drakeet.me@gmail.com>
 *
 * This file is part of Meizhi
 *
 * Meizhi is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Meizhi is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Meizhi.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.example.sth0409.breakpointuploaddemo;

/*
 * Copyright (C) 2015 Drakeet <drakeet.me@gmail.com>
 *
 * This file is part of Meizhi
 *
 * Meizhi is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Meizhi is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Meizhi.  If not, see <http://www.gnu.org/licenses/>.
 */

import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

    // 默认日期格式
    public static final String DATE_DEFAULT_FORMAT = "yyyy-MM-dd";
    public static final String DATE_DEFAULT_NOFORMAT = "yyyyMMdd";
    public static final String DATE_MONTHDAY_FORMAT = "M月d日";
    public static final String DATE_MONTHDAYHM_FORMAT = "yyyy年M月d日 HH:mm";

    // 默认时间格式
    public static final String DATETIME_DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATETIME_DEFAULT_NOYEAR_FORMAT = "MM-dd HH:mm:ss";
    public static final String DATETIME_DEFAULTHM_FORMAT = "yyyy/MM/dd HH:mm";

    public static final String TIME_DEFAULT_FORMAT = "HH:mm:ss";
    public static final String DATE_FORMAT_MMDD = "MM-dd";
    public static final String DATE_FORMAT_HHMM = "HH:mm";
    public static final String DATE_FORMAT_MDHHMM = "MM-dd HH:mm";

    // 日期格式化
    private static DateFormat dateFormat = null;

    private static int[] day_31 = {1, 3, 5, 7, 8, 10, 12};
    private static int[] day_30 = {4, 6, 9, 11};

    public static DateFormat ymdFormat = new SimpleDateFormat("yyyy年M月d日");
    public static DateFormat ymdFormat2 = new SimpleDateFormat("yyyy.MM.dd");
    public static DateFormat mdFormat = new SimpleDateFormat("M月d日");
    public static DateFormat mdHmFormat = new SimpleDateFormat("M月d日HH:mm");
    public static DateFormat ymdHmFormat = new SimpleDateFormat("yyyy年M月d日HH:mm");
    public static DateFormat hmFormat = new SimpleDateFormat("HH:mm");
    public static DateFormat ddFormat2 = new SimpleDateFormat("dd");
    public static DateFormat mmFormat = new SimpleDateFormat("MM");

    public static String toDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return dateFormat.format(date);
    }

    public static String toDate_v1(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dateFormat.format(date);
    }

    public static Date toDate(String date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date parseDate = null;
        try {
            parseDate = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parseDate;
    }

    public static Date toDate_v1(String date, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date parseDate = null;
        try {
            parseDate = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parseDate;
    }

    public static Date toDate_v1(String date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parseDate = null;
        try {
            parseDate = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parseDate;
    }

    public static String toDate(Date date, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    public static String toDate(long times, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(new Date(times));
    }

    public static Date toDate(Date date, int add) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, add);
        return calendar.getTime();
    }

    /*
     * 毫秒转化时分秒毫秒
     */
    public static String toDate_v1(Long ms) {
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = ms / dd;
        Long hour = (ms - day * dd) / hh;
        Long minute = (ms - day * dd - hour * hh) / mi;
        Long second = (ms - day * dd - hour * hh - minute * mi) / ss;
//        Long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        StringBuffer sb = new StringBuffer();
        if (day > 0) {
            sb.append(day + "天");
        }
        if (hour > 0) {
            sb.append(hour + "小时");
        }
        if (minute > 0) {
            sb.append(minute + "分");
        }
        if (second > 0) {
            sb.append(second + "秒");
        }
        /*if(milliSecond > 0) {
            sb.append(milliSecond+"毫秒");
        }*/
        return sb.toString();
    }


    /*
 * 毫秒转化时分秒毫秒
 */
    public static String minuteToHour(Long mms) {
        long ms = mms * 60 * 1000;
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;

        Long day = ms / dd;
        Long hour = (ms - day * dd) / hh;
        Long minute = (ms - day * dd - hour * hh) / mi;

        StringBuffer sb = new StringBuffer();
        if (hour > 0) {
            sb.append(hour + "小时");
        }
        if (minute > 0) {
            sb.append(minute + "分");
        } else {
            sb.append(minute + "分");
        }
        return sb.toString();
    }

    public static Date getLastdayDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }


    public static Date getNextdayDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }

    //获取这个月的天数
    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static Date getAgodayDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    public static String toCurrentDate(Date nowDate, DateFormat ymdFormat) {
        return ymdFormat.format(nowDate);
    }

    public static Date getCurrentDate() {
        return new Date();
    }

    public static Date getCurrentDate(long time) {
        return new Date(time);
    }

    public static boolean isTheSameDay(Date one, Date another) {
        Calendar _one = Calendar.getInstance();
        _one.setTime(one);
        Calendar _another = Calendar.getInstance();
        _another.setTime(another);
        int oneDay = _one.get(Calendar.DAY_OF_YEAR);
        int anotherDay = _another.get(Calendar.DAY_OF_YEAR);

        return oneDay == anotherDay;
    }

    /**
     * 获得当前时间
     *
     * @return
     */
    public static long getCurrentTime() {
        Date nowDate = new Date();
        Calendar nowCalendar = Calendar.getInstance();
        nowCalendar.setTime(nowDate);
        long nowTime = nowDate.getTime();
        return nowTime;
    }

    public static boolean isOvertime(long time, long intervalTime) {
        // 当前时间
        long nowTime = getCurrentTime();
        Date igoneDate = new Date(time);
        Calendar igoneCalendar = Calendar.getInstance();
        igoneCalendar.setTime(igoneDate);
        long delta = (nowTime - time) / 1000;
        return delta > intervalTime;

    }

    /**
     * 转换标签页收藏时间戳为友好的格式
     *
     * @param collectedtime 收藏时间戳
     * @return
     */
    public static String markContentDetailedTime(long collectedtime) {

        // 当前时间
        Date nowDate = new Date();
        Calendar nowCalendar = Calendar.getInstance();
        nowCalendar.setTime(nowDate);
        long nowTime = nowDate.getTime();
        int nowYear = nowCalendar.get(Calendar.YEAR);

        // 收藏时间
        Date collectedDate = new Date(collectedtime);
        Calendar collectedCalendar = Calendar.getInstance();
        collectedCalendar.setTime(collectedDate);

        // 时间差秒数
        long delta = (nowTime - collectedtime) / 1000;

        if (delta <= 3 * 60) { // 3分钟以内，返回刚刚
            return "刚刚";
        }
        if (delta <= 60 * 60) { // 几分钟以前，3分钟到1小时之内
            return Math.round(((double) delta) / 60) + "分钟前";
        }

        String collectedDateStr = ymdFormat.format(collectedDate);
        String todayStr = ymdFormat.format(nowDate);// 今天年月日字符串
        nowCalendar.add(Calendar.DAY_OF_MONTH, -1);
        String yesterdayStr = ymdFormat.format(nowCalendar.getTime()); // 昨天年月日字符串
        nowCalendar.add(Calendar.DAY_OF_MONTH, -1);
        String beforeYesterdayStr = ymdFormat.format(nowCalendar.getTime()); // 前天年月日字符串
        nowCalendar.add(Calendar.DAY_OF_MONTH, -1);
        int collectedHour = collectedCalendar.get(Calendar.HOUR_OF_DAY);
        if (delta <= 60 * 60 * 24 && (todayStr.endsWith(collectedDateStr)) && collectedHour > 2) { // 几小时前，24点之内
            return Math.round((double) delta / (60 * 60)) + "小时前";
        }
        if (yesterdayStr.equals(collectedDateStr) && (collectedHour >= 2 && collectedHour < 5)) {
            return "昨天凌晨";
        }
        if (yesterdayStr.equals(collectedDateStr) && (collectedHour >= 5 && collectedHour < 9)) {
            return "昨天早晨";
        }
        if (yesterdayStr.equals(collectedDateStr) && (collectedHour >= 9 && collectedHour < 12)) {
            return "昨天上午";
        }
        if (yesterdayStr.equals(collectedDateStr) && (collectedHour >= 12 && collectedHour < 18)) {
            return "昨天下午";
        }
        if (yesterdayStr.equals(collectedDateStr) && (collectedHour >= 18 && collectedHour < 23)) {
            return "昨天晚上";
        }
        if ((todayStr.equals(collectedDateStr) && collectedHour < 2)
                || (yesterdayStr.equals(collectedDateStr) && collectedHour >= 23)) {
            return "昨天深夜";
        }

        if (beforeYesterdayStr.equals(collectedDateStr) && (collectedHour >= 2 && collectedHour < 5)) {
            return "前天凌晨";
        }
        if (beforeYesterdayStr.equals(collectedDateStr) && (collectedHour >= 5 && collectedHour < 9)) {
            return "前天早晨";
        }
        if (beforeYesterdayStr.equals(collectedDateStr) && (collectedHour >= 9 && collectedHour < 12)) {
            return "前天上午";
        }
        if (beforeYesterdayStr.equals(collectedDateStr) && (collectedHour >= 12 && collectedHour < 18)) {
            return "前天下午";
        }
        if (beforeYesterdayStr.equals(collectedDateStr) && (collectedHour >= 18 && collectedHour < 23)) {
            return "前天晚上";
        }
        if ((yesterdayStr.equals(collectedDateStr) && collectedHour < 2)
                || (beforeYesterdayStr.equals(collectedDateStr) && collectedHour >= 23)) {
            return "前天深夜";
        }

        int collectedYear = collectedCalendar.get(Calendar.YEAR);
        if (collectedYear == nowYear) {
            return mdFormat.format(collectedDate);
        }
        return ymdFormat.format(collectedDate);
    }

    public static String markNoteDetailedTime(long collectedtime) {

        // 当前时间
        Date nowDate = new Date();
        Calendar nowCalendar = Calendar.getInstance();
        nowCalendar.setTime(nowDate);
        int nowYear = nowCalendar.get(Calendar.YEAR);
        String todayStr = ymdFormat.format(nowDate);// 今天年月日字符串

        nowCalendar.add(Calendar.DAY_OF_MONTH, -1);
        String yesterdayStr = ymdFormat.format(nowCalendar.getTime()); // 昨天年月日字符串
        nowCalendar.add(Calendar.DAY_OF_MONTH, -1);
        String beforeYesterdayStr = ymdFormat.format(nowCalendar.getTime()); // 前天年月日字符串
        nowCalendar.add(Calendar.DAY_OF_MONTH, -1);

        // 收藏时间
        Date collectedDate = new Date(collectedtime);
        Calendar collectedCalendar = Calendar.getInstance();
        collectedCalendar.setTime(collectedDate);
        String collectedDateStr = ymdFormat.format(collectedDate);


        if (todayStr.equals(collectedDateStr)) {
            return "今天" + hmFormat.format(collectedDate);
        }
        if (yesterdayStr.equals(collectedDateStr)) {
            return "昨天" + hmFormat.format(collectedDate);
        }

        if (beforeYesterdayStr.equals(collectedDateStr)) {
            return "前天" + hmFormat.format(collectedDate);
        }
        int collectedYear = collectedCalendar.get(Calendar.YEAR);
        if (collectedYear == nowYear) {
            return mdHmFormat.format(collectedDate);
        }
        return ymdHmFormat.format(collectedDate);
    }

    /**
     * 转换标签页收藏时间戳为友好的格式,用于分组显示
     *
     * @param collectedtime 收藏时间戳
     * @return
     */
    public static String markGroupDetailedTime(long collectedtime) {

        // 当前时间
        Date nowDate = new Date();
        Calendar nowCalendar = Calendar.getInstance();
        nowCalendar.setTime(nowDate);
        int nowYear = nowCalendar.get(Calendar.YEAR);
        String todayStr = ymdFormat.format(nowDate);// 今天年月日字符串

        nowCalendar.add(Calendar.DAY_OF_MONTH, -1);
        String yesterdayStr = ymdFormat.format(nowCalendar.getTime()); // 昨天年月日字符串
        nowCalendar.add(Calendar.DAY_OF_MONTH, -1);
        String beforeYesterdayStr = ymdFormat.format(nowCalendar.getTime()); // 前天年月日字符串
        nowCalendar.add(Calendar.DAY_OF_MONTH, -1);

        // 收藏时间
        Date collectedDate = new Date(collectedtime);
        Calendar collectedCalendar = Calendar.getInstance();
        collectedCalendar.setTime(collectedDate);
        String collectedDateStr = ymdFormat.format(collectedDate);


        if (todayStr.equals(collectedDateStr)) {
            return "今天";
        }
        if (yesterdayStr.equals(collectedDateStr)) {
            return "昨天";
        }

        if (beforeYesterdayStr.equals(collectedDateStr)) {
            return "前天";
        }
        int collectedYear = collectedCalendar.get(Calendar.YEAR);
        if (collectedYear == nowYear) {
            return mdFormat.format(collectedDate);
        }
        return ymdFormat.format(collectedDate);
    }


    /**
     * 转换标签页收藏时间戳为友好的格式,用于分组显示
     *
     * @param collectedtime 收藏时间戳
     * @return
     */
    public static String DaymarkGroupDetailedTime(long collectedtime) {

        // 当前时间
        Date nowDate = new Date();
        Calendar nowCalendar = Calendar.getInstance();
        nowCalendar.setTime(nowDate);
        int nowYear = nowCalendar.get(Calendar.YEAR);
        String todayStr = ymdFormat.format(nowDate);// 今天年月日字符串

        nowCalendar.add(Calendar.DAY_OF_MONTH, -1);
        String yesterdayStr = ymdFormat.format(nowCalendar.getTime()); // 昨天年月日字符串
        nowCalendar.add(Calendar.DAY_OF_MONTH, -1);
        String beforeYesterdayStr = ymdFormat.format(nowCalendar.getTime()); // 前天年月日字符串
        nowCalendar.add(Calendar.DAY_OF_MONTH, -1);

        // 收藏时间
        Date collectedDate = new Date(collectedtime);
        Calendar collectedCalendar = Calendar.getInstance();
        collectedCalendar.setTime(collectedDate);
        String collectedDateStr = ymdFormat.format(collectedDate);


        if (todayStr.equals(collectedDateStr)) {
            return "今日";
        }
        if (yesterdayStr.equals(collectedDateStr)) {
            return "昨日";
        }
//
//        if (beforeYesterdayStr.equals(collectedDateStr)) {
//            return "前天";
//        }
        int collectedYear = collectedCalendar.get(Calendar.YEAR);
        if (collectedYear == nowYear) {
            return mdFormat.format(collectedDate);
        }
        return ymdFormat.format(collectedDate);
    }

    public static String getMonth(int month) {
        String monthstr = "";
        switch (month) {
            case 1:
                monthstr = "一月";
                break;
            case 2:
                monthstr = "二月";
                break;
            case 3:
                monthstr = "三月";
                break;
            case 4:
                monthstr = "四月";
                break;
            case 5:
                monthstr = "五月";
                break;
            case 6:
                monthstr = "六月";
                break;
            case 7:
                monthstr = "七月";
                break;
            case 8:
                monthstr = "八月";
                break;
            case 9:
                monthstr = "九月";
                break;
            case 10:
                monthstr = "十月";
                break;
            case 11:
                monthstr = "十一月";
                break;
            case 12:
                monthstr = "十二月";
                break;
        }
        return monthstr;

    }

    /**
     * 字符串的日期格式的计算
     */
    public static int daysBetween(String smdate, String bdate) {
        if (TextUtils.isEmpty(smdate) || TextUtils.isEmpty(bdate)) {
            return 0;
        }
        long between_days = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(smdate));
            long time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(bdate));
            long time2 = cal.getTimeInMillis();
            between_days = (time2 - time1) / (1000 * 3600 * 24);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Integer.parseInt(String.valueOf(between_days));
    }


    /**
     * 获取周
     *
     * @param date
     * @return
     */
    public static String getWeek(String date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parseDate = null;
        try {
            parseDate = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        String week = sdf.format(parseDate);
        return week;
    }

    /**
     * 获取周
     *
     * @param date
     * @return
     */
    public static String getWeek(String date, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date parseDate = null;
        try {
            parseDate = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        String week = sdf.format(parseDate);
        return week;
    }

    /**
     * 新手是否超过21天
     * 超过一定天数返回30天 没具体计算
     *
     * @param creatTime
     * @return
     */
    public static int getDifferDay(long creatTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String creat = dateFormat.format(creatTime);
        String now = dateFormat.format(System.currentTimeMillis());
        String[] creatSplit = creat.split("-");
        String[] nowSplit = now.split("-");
        if (creatSplit.length == 3 && nowSplit.length == 3) {
            int creatYear = Integer.parseInt(creatSplit[0]);
            int creatMonth = Integer.parseInt(creatSplit[1]);
            int creatDay = Integer.parseInt(creatSplit[2]);
            int nowYear = Integer.parseInt(nowSplit[0]);
            int nowMonth = Integer.parseInt(nowSplit[1]);
            int nowDay = Integer.parseInt(nowSplit[2]);

            if (creatYear == nowYear && creatMonth == nowMonth) {
                return nowDay - creatDay;
            } else {
                if (creatYear == nowYear) {
                    if (nowMonth - creatMonth > 1) {
                        return 30;
                    } else {
                        return hasDays(creatYear, creatMonth, nowMonth, creatDay, nowDay);
                    }
                } else {
                    if (nowYear - creatYear > 1) {
                        return 30;
                    } else {
                        if (nowMonth - creatMonth == -11) {
                            return hasDays(creatYear, creatMonth, nowMonth, creatDay, nowDay);
                        } else
                            return 30;
                    }
                }
            }
        }
        return 30;
    }

    private static int hasDays(int creatYear, int creatMonth, int nowMonth, int creatDay, int nowDay) {
        if (nowMonth - creatMonth == 1 || nowMonth - creatMonth == -11) {
            int creatDays = monthOfDay(creatYear, creatMonth);
            return (creatDays - creatDay) + nowDay;
        } else {
            return 30;
        }
    }

    private static int monthOfDay(int creatYear, int month) {

        for (int i : day_31) {
            if (i == month)
                return 31;
        }
        for (int i : day_30) {
            if (i == month)
                return 30;
        }
        return creatYear % 4 == 0 ? 29 : 28;
    }

    /**
     * 获取两个时间的时间查 如1天2小时30分钟
     */
    public static String getDatePoor(Date endDate, Date nowDate) {

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }

    //获取两个时间的时间差(秒)
    public static int getDatePoorBySeconde(Date endDate, Date nowDate) {
        return (int) ((endDate.getTime() - nowDate.getTime()) / 1000);
    }

    /**
     * 获取两个时间的时间差
     */
    public static long getHourInterval(String date) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        Long endtime = 0l;
        if (TextUtils.isEmpty(date)) {
            return endtime;
        }
        if (date.length() <= 10) {
            endtime = toDate_v1(date).getTime();
        } else {
            endtime = toDate(date).getTime();
        }
        // 获得两个时间的毫秒时间差异
        long diff = getCurrentTime() - endtime;
        long min = diff / nm;
        // 计算差多少小时
        long hour = diff / nh;
        return hour;
    }

    /**
     * 获取两个时间的时间差
     */
    public static long getHourInterval(Date date) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        Long  endtime = date.getTime();
        // 获得两个时间的毫秒时间差异
        long diff = getCurrentTime() - endtime;
        long min = diff / nm;
        // 计算差多少小时
        long hour = diff / nh;
        return hour;
    }


    public static long getDayInterval(int mday) {
        Calendar c = Calendar.getInstance(); // 当时的日期和时间
        int day = c.get(Calendar.DAY_OF_MONTH) - mday;
        c.set(Calendar.DAY_OF_MONTH, day);
        System.out.println(toDate(c.getTime(), "yyyy/MM/dd HH:mm:ss"));
        System.out.println(c.getTime().getTime());
        return c.getTime().getTime();
    }

    public static String longToString(long currentTime, String formatType)
            throws ParseException {
        Date date = longToDate(currentTime, formatType); // long类型转成Date类型
        String strTime = dateToString(date, formatType); // date类型转成String
        return strTime;
    }

    public static long stringToLong(String strTime, String formatType)
            throws ParseException {
        Date date = stringToDate(strTime, formatType); // String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            long currentTime = dateToLong(date); // date类型转成long类型
            return currentTime;
        }
    }

    public static Date stringToDate(String strTime, String formatType) {

        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        try {
            date = formatter.parse(strTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static long dateToLong(Date date) {
        return date.getTime();
    }

    public static Date longToDate(long currentTime, String formatType)
            throws ParseException {
        Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime = dateToString(dateOld, formatType); // 把date类型的时间转换为string
        Date date = stringToDate(sDateTime, formatType); // 把String类型转换为Date类型
        return date;
    }

    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }

    /**
     * 通过年份和月份 得到当月的日子
     *
     * @param year
     * @param month
     * @return
     */
    public static int getMonthDays(int year, int month) {
        month++;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                return -1;
        }
    }

    /**
     * 返回当前月份1号位于周几
     *
     * @param date
     * @return 日：1		一：2		二：3		三：4		四：5		五：6		六：7
     */
    public static int getFirstDayWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 根据列明获取周
     *
     * @param column
     * @return
     */
    public static String getWeekName(int column) {
        switch (column) {
            case 0:
                return "周日";
            case 1:
                return "周一";
            case 2:
                return "周二";
            case 3:
                return "周三";
            case 4:
                return "周四";
            case 5:
                return "周五";
            case 6:
                return "周六";
            default:
                return "";
        }
    }


    /**
     * 日期格式化yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static Date formatDate(String date, String format) {
        try {
            return new SimpleDateFormat(format).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 日期格式化
     *
     * @param date
     * @return
     */
    public static Date getDateFormat(String date) {
        if (dateFormat == null) {
            dateFormat = new SimpleDateFormat(DATE_DEFAULT_FORMAT);
        }
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    //获取当前日期是周几
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }

    //获取年
    public static int getYear(String date) {
        return getYear(date, DATE_DEFAULT_FORMAT);
    }

    //获取月
    public static int getMonth(String date) {
        return getMonth(date, DATE_DEFAULT_FORMAT);
    }

    //获取日
    public static int getDays(String date) {
        return getDays(date, DATE_DEFAULT_FORMAT);
    }


    //获取年
    public static int getYear(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String s = sdf.format(toDate_v1(date, format));
        return Integer.valueOf(s);
    }

    //获取月
    public static int getMonth(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        String s = sdf.format(toDate_v1(date, format));
        return Integer.valueOf(s);
    }

    //获取日
    public static int getDays(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        String s = sdf.format(toDate_v1(date, format));
        return Integer.valueOf(s);
    }

    //景区门票日期转换
    public static String dateHTransformation(String date) {
        Date d = new Date();
        date = date.replace("/Date(", "").replace(")/", "");
        if (date.indexOf("+") > 0) {
            date = date.substring(0, date.indexOf("+"));
        } else if (date.indexOf("-") > 0) {
            date = date.substring(0, date.indexOf("-"));
        }
        d.setTime(Long.parseLong(date));
        return new SimpleDateFormat("yyyy-MM-dd").format(d);
    }

    /**
     * @param dateTime:指定时间，格式要与第二个参数指定的格式相同
     * @param dateFormat:格式化类型，如"yyyyMMddHHmm"
     * @param timeType:                        时间类型，定义在抽象类Calendar中，比如类中定义了final static YEAR = 1,
     *                                         调用时直接传入Calendar.YEAR，不需要关系具体数字
     * @param interval:与指定时间的间隔，比如前五分钟，传入-5
     * @throws ParseException
     * @return:指定时间加上指定间隔时间的结果
     */
    public static String getTime(String dateTime, String dateFormat, int timeType, int interval) {
        Date parseDate = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        try {
            parseDate = simpleDateFormat.parse(dateTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 设置Calendar
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(parseDate);

        int oldTime = calendar.get(timeType);
        calendar.set(timeType, oldTime + interval);
        // 转换格式
        String format = simpleDateFormat.format(calendar.getTime());
        return format;
    }

}
