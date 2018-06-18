package cn.zk.util;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;


/**
 * 日期时间工具类
 *
 * @author lubiao
 */
public class DateUtils {

    private DateUtils() {
    }

    /**
     * yyyy-MM-dd
     */
    public static final ThreadLocal<DateFormat> DATE_FORMATTER1 = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));
    /**
     * yyyyMMdd
     */
    public static final ThreadLocal<DateFormat> DATE_FORMATTER2 = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMdd"));
    /**
     * yyyy-MM
     */
    public static final ThreadLocal<DateFormat> DATE_FORMATTER3 = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM"));
    /**
     * yyyyMM
     */
    public static final ThreadLocal<DateFormat> DATE_FORMATTER4 = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMM"));
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final ThreadLocal<DateFormat> DATETIME_FORMATTER1 = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    /**
     * yyyy-MM-dd HH:mm:ss.SSS
     */
    public static final ThreadLocal<DateFormat> DATETIME_MS_FORMATTER1 = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"));
    /**
     * yyyyMMddHHmmss
     */
    public static final ThreadLocal<DateFormat> DATETIME_FORMATTER2 = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMddHHmmss"));
    /**
     * Fri Apr 20 17:33:17 CST 2018
     */
    public static final ThreadLocal<DateFormat> DATETIME_FORMATTER3 = ThreadLocal.withInitial(() -> new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy"));
    /**
     * HH:mm:ss
     */
    public static final ThreadLocal<DateFormat> TIME_FORMATTER1 = ThreadLocal.withInitial(() -> new SimpleDateFormat("HH:mm:ss"));
    /**
     * HHmmss
     */
    public static final ThreadLocal<DateFormat> TIME_FORMATTER2 = ThreadLocal.withInitial(() -> new SimpleDateFormat("HHmmss"));
    /**
     * HHmmssS
     */
    public static final ThreadLocal<DateFormat> TIME_FORMATTER3 = ThreadLocal.withInitial(() -> new SimpleDateFormat("HHmmssS"));

    private final static int HOUR_MILLISECOND = 3600 * 1000;
    private final static int DAY_MILLISECOND = 24 * 3600 * 1000;

    /**
     * 解析字符串日期
     *
     * @param dateStr
     *         日期字符串
     *
     * @return 日期对象
     */
    public static Date parse(String dateStr) {
        if (Objects.isNull(dateStr) || Objects.equals("", dateStr.trim())) {
            return null;
        }
        try {
            return DATE_FORMATTER1.get().parse(dateStr);
        } catch (ParseException e) {
            try {
                return DATETIME_FORMATTER1.get().parse(dateStr);
            } catch (ParseException e1) {
                try {
                    return DATETIME_FORMATTER3.get().parse(dateStr);
                } catch (ParseException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 解析字符串日期
     *
     * @param dateStr
     *         日期字符串
     * @param format
     *         格式
     *
     * @return 日期对象
     */
    public static Date parse(String dateStr, DateFormat format) {
        if (Objects.isNull(dateStr) || Objects.equals("", dateStr.trim())) {
            return null;
        }
        try {
            return format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 格式化日期
     *
     * @param date
     *         日期对象
     * @param format
     *         格式
     *
     * @return 日期字符串
     */
    public static String format(Date date, DateFormat format) {
        if (Objects.isNull(date)) {
            return null;
        }
        return format.format(date);
    }

    /**
     * 获取当前日期时间
     *
     * @return 日期对象
     */
    public static Date getCurrentDateTime() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    /**
     * 获取当前日期时间字符串
     *
     * @return 日期时间字符串yyyy-MM-dd HH:mm:ss
     */
    public static String getCurrentDateTimeStr() {
        Calendar calendar = Calendar.getInstance();
        return format(calendar.getTime(), DATETIME_FORMATTER1.get());
    }

    /**
     * 日期加减指定数量
     *
     * @param date
     *         日期
     * @param field
     *         日期的指定字段，如Calendar.DAY_OF_MONTH
     * @param num
     *         数量，正数是加，负数是减
     *
     * @return 加减后的日期
     */
    public static Date addDate(Date date, int field, int num) {
        if (Objects.isNull(date)) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, num);
        return calendar.getTime();
    }

    /**
     * 日期加减天数
     *
     * @param date
     *         日期
     * @param days
     *         天数，正数是加，负数是减
     *
     * @return 加减后的日期
     */
    public static Date addDays(Date date, int days) {
        return addDate(date, Calendar.DATE, days);
    }

    /**
     * 日期加减月数
     *
     * @param date
     *         日期
     * @param months
     *         月数，正数是加，负数是减
     *
     * @return 加减后的日期
     */
    public static Date addMonths(Date date, int months) {
        return addDate(date, Calendar.MONTH, months);
    }

    /**
     * 获取Date的Calendar对象
     *
     * @param date
     *         Date对象
     *
     * @return Calendar对象
     */
    public static Calendar getCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 获取指定年月日的Date对象
     *
     * @param year
     *         年
     * @param month
     *         月，从0开始
     * @param day
     *         日
     *
     * @return Date对象
     */
    public static Date getDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }

    /**
     * 获取两个日期间的年数，
     *
     * @param startDate
     *         起始日期，不能为空，且小于endDate
     * @param endDate
     *         结束日期，不能为空，且大于endDate
     *
     * @return 年数
     */
    public static int getYears(Date startDate, Date endDate) {
        if (Objects.isNull(startDate) || Objects.isNull(endDate) || startDate.after(endDate)) {
            return -1;
        }
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate startLocalDate = startDate.toInstant().atZone(zoneId).toLocalDate();
        LocalDate endLocalDate = endDate.toInstant().atZone(zoneId).toLocalDate();
        return Period.between(startLocalDate, endLocalDate).getYears();
    }

    /**
     * 获取两个日期间的月数，
     *
     * @param startDate
     *         起始日期，不能为空，且小于endDate
     * @param endDate
     *         结束日期，不能为空，且大于endDate
     *
     * @return 月数
     */
    public static int getMonths(Date startDate, Date endDate) {
        if (Objects.isNull(startDate) || Objects.isNull(endDate) || startDate.after(endDate)) {
            return -1;
        }
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate startLocalDate = startDate.toInstant().atZone(zoneId).toLocalDate();
        LocalDate endLocalDate = endDate.toInstant().atZone(zoneId).toLocalDate();
        int years = Period.between(startLocalDate, endLocalDate).getYears();
        int months = Period.between(startLocalDate, endLocalDate).getMonths();
        return years * 12 + months;
    }

    /**
     * 获取两个日期间的天数，
     *
     * @param startDate
     *         起始日期，不能为空，且小于endDate
     * @param endDate
     *         结束日期，不能为空，且大于endDate
     *
     * @return 天数
     */
    public static int getDays(Date startDate, Date endDate) {
        startDate = DateUtils.parse(DateUtils.format(startDate, DATE_FORMATTER1.get()), DATE_FORMATTER1.get());
        endDate = DateUtils.parse(DateUtils.format(endDate, DATE_FORMATTER1.get()), DATE_FORMATTER1.get());
        if (Objects.isNull(startDate) || Objects.isNull(endDate) || startDate.after(endDate)) {
            return -1;
        }
        return Math.toIntExact((endDate.getTime() - startDate.getTime()) / DAY_MILLISECOND);
    }

    /**
     * 获取两个日期间的小时数，
     *
     * @param startDate
     *         起始日期，不能为空，且小于endDate
     * @param endDate
     *         结束日期，不能为空，且大于endDate
     *
     * @return 小时数
     */
    public static int getHours(Date startDate, Date endDate) {
        startDate = DateUtils.parse(DateUtils.format(startDate, DATE_FORMATTER1.get()), DATE_FORMATTER1.get());
        endDate = DateUtils.parse(DateUtils.format(endDate, DATE_FORMATTER1.get()), DATE_FORMATTER1.get());
        if (Objects.isNull(startDate) || Objects.isNull(endDate) || startDate.after(endDate)) {
            return -1;
        }
        return Math.toIntExact((endDate.getTime() - startDate.getTime()) / HOUR_MILLISECOND);
    }
}