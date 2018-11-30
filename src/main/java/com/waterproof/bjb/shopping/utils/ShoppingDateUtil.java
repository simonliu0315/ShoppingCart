package com.waterproof.bjb.shopping.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waterproof.bjb.shopping.controller.MemberController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ShoppingDateUtil {

    public static String convertTWDate(String AD, String beforeFormat,
            String afterFormat) {
        if (AD == null)
            return "";
        SimpleDateFormat df4 = new SimpleDateFormat(beforeFormat);
        SimpleDateFormat df2 = new SimpleDateFormat(afterFormat);
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(df4.parse(AD));
            if (cal.get(Calendar.YEAR) > 1492)
                cal.add(Calendar.YEAR, -1911);
            else
                cal.add(Calendar.YEAR, +1911);
            return df2.format(cal.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String date2String(Date AD, String beforeFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(beforeFormat);
        String dateString;
        try {
            dateString = sdf.format(AD);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dateString;
    }
    
    public static String date2MingKuo(Date date){
        String result = date2String(date, "yyyy/MM/dd HH:mm:ss");
        result = convertTWDate(result, "yyyy/MM/dd HH:mm:ss", "yyy/MM/dd");
        
        return result;
    }
    
    /**
     * 
     * @param AD
     * @param beforeFormat
     * @return
     */
    public static String date2ToTWString(Date AD, String beforeFormat) {

        SimpleDateFormat sdf = new SimpleDateFormat(beforeFormat);
        String dateString;
        try {
            dateString = sdf.format(AD);
            int year = 0;
            year = Integer.parseInt(dateString.substring(0, 4)) - 1911;
            dateString = year + dateString.substring(4);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dateString;
    }

    public static Date String2date(String dates, String beforeFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(beforeFormat);
        Date date;
        try {
            date = sdf.parse(dates);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
        return date;
    }
    
    
    /**
     * 民國年轉西元年 
     * @param dates
     * @param beforeFormat
     * @return
     */
    public static Date parseMingoYear  (String mingkou) {
        
        if (null == mingkou || mingkou.length() < 6)
            return null;
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date;
        String year,month,day;
        
        if (mingkou.length() > 6){
            if (mingkou.contains("/")){
                String[] mingkouArray = mingkou.split("/");
                year = mingkouArray[0];
                month = mingkouArray[1];
                day = mingkouArray[2];
            }else if (mingkou.contains("-")){
                String[] mingkouArray = mingkou.split("-");
                year = mingkouArray[0];
                month = mingkouArray[1];
                day = mingkouArray[2];
            }else{
                year = mingkou.substring(0, 3);
                month = mingkou.substring(3, 5);
                day = mingkou.substring(5, 7);
            }
        }else{
            year = "1" + mingkou.substring(0, 2);
            month = mingkou.substring(2, 4);
            day = mingkou.substring(4, 6);
        }
        
        try {
            
            Integer yearAD = Integer.valueOf(year) +1911;
            
            String strDate = yearAD +  "/" + month + "/" + day; 
             
            date = sdf.parse(strDate);
            
            System.out.println("parseMingoYear=="+date);
            
        } catch (ParseException e) { 
            throw new RuntimeException(e);
        }
        return date;
    }
    /**
     * 取回系統日期，格式:yyyyMMdd.
     *
     * @return the system date
     */
    public static String getSystemDate() {
        return new SimpleDateFormat("yyyyMMdd").format(new GregorianCalendar().getTime());
    }

    /**
     * 取回系統時間，格式:HHmmss.
     *
     * @return the system time
     */
    public static String getSystemTime() {
        return new SimpleDateFormat("HHmmss").format(new GregorianCalendar().getTime());
    }

    /**
     * 取回系統日期的月份，格式:MM.
     *
     * @return the system month
     */
    public static String getSystemMonth() {
        return new SimpleDateFormat("MM").format(new GregorianCalendar().getTime());
    }

    /**
     * 取回系統日期的日，格式:dd.
     *
     * @return the system day
     */
    public static String getSystemDay() {
        return new SimpleDateFormat("dd").format(new GregorianCalendar().getTime());
    }

    /**
     * 取回明天的日期，格式:yyyyMMdd.
     *
     * @return the next date
     * @throws ParseException
     *             the parse exception
     */
    public static String getNextDate() throws ParseException {
        return getNextDate(ShoppingDateUtil.getSystemDate(), 1);
    }

    /**
     * 取回下N天的系統日期，格式：yyMMdd.
     *
     * @param n
     *            the n
     * @return the next date
     * @throws ParseException
     *             the parse exception
     */
    public static String getNextDate(int n) throws ParseException {
        return getNextDate(ShoppingDateUtil.getSystemDate(), n);
    }

    /**
     * 取回指定日期的下N天日期，格式：yyyyMMdd.
     *
     * @param date
     *            the date
     * @param n
     *            the n
     * @return the next date
     * @throws ParseException
     *             the parse exception
     */
    public static String getNextDate(String date, int n) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new SimpleDateFormat("yyyyMMdd").parse(date));
        calendar.add(Calendar.DAY_OF_YEAR, n);

        return new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
    }

    /**
     * 取回指定日期的下一天日期，格式：yyyyMMdd.
     *
     * @param date
     *            the date
     * @return the next date
     * @throws ParseException
     *             the parse exception
     */
    public static String getNextDate(String date) throws ParseException {
        return getNextDate(date, 1);
    }

    /**
     * 取回昨天的系統日期，格式:yyyyMMdd.
     *
     * @return the previous date
     * @throws ParseException
     *             the parse exception
     */
    public static String getPreviousDate() throws ParseException {
        return getPreviousDate(ShoppingDateUtil.getSystemDate(), 1);
    }

    /**
     * 取回前N天的系統日期，格式:yyyyMMdd.
     *
     * @param n
     *            the n
     * @return the previous date
     * @throws ParseException
     *             the parse exception
     */
    public static String getPreviousDate(int n) throws ParseException {
        return getPreviousDate(ShoppingDateUtil.getSystemDate(), n);
    }

    /**
     * 取回指定日期的前N天日期，格式：yyyyMMdd.
     *
     * @param date
     *            the date
     * @param n
     *            the n
     * @return the previous date
     * @throws ParseException
     *             the parse exception
     */
    public static String getPreviousDate(String date, int n) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new SimpleDateFormat("yyyyMMdd").parse(date));
        calendar.add(Calendar.DAY_OF_YEAR, -n);

        return new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
    }

    /**
     * 取回指定日期的前一天日期，格式：yyyyMMdd.
     *
     * @param date
     *            the date
     * @return the previous date
     * @throws ParseException
     *             the parse exception
     */
    public static String getPreviousDate(String date) throws ParseException {
        return getPreviousDate(date, 1);
    }

    /**
     * 取得下N個月的日期，格式：yyyymmdd.
     *
     * @param date
     *            the date
     * @param months
     *            the months
     * @return the next month date
     * @throws ParseException
     *             the parse exception
     */
    public static String getNextMonthDate(String date, int months) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new SimpleDateFormat("yyyyMMdd").parse(date));
        calendar.add(Calendar.MONTH, months);

        return new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
    }

    /**
     * 取得下個月的日期，格式：yyyymmdd.
     *
     * @param date
     *            the date
     * @return the next month date
     * @throws ParseException
     *             the parse exception
     */
    public static String getNextMonthDate(String date) throws ParseException {
        return getNextMonthDate(date, 1);
    }

    /**
     * 取得下幾個月份，格式：yyyymm.
     *
     * @param yearMonth
     *            the year month
     * @param months
     *            the months
     * @return the next month
     * @throws ParseException
     *             the parse exception
     */
    public static String getNextMonth(String yearMonth, int months) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new SimpleDateFormat("yyyyMM").parse(yearMonth));
        calendar.add(Calendar.MONTH, months);

        return new SimpleDateFormat("yyyyMM").format(calendar.getTime());
    }

    /**
     * 取得下一個月份，格式：yyyymm.
     *
     * @param yearMonth
     *            the year month
     * @return the next month
     * @throws ParseException
     *             the parse exception
     */
    public static String getNextMonth(String yearMonth) throws ParseException {
        return getNextMonth(yearMonth, 1);
    }

    /**
     * 取得下一年的日期，格式：yyyymmdd.
     *
     * @param date
     *            the date
     * @return the next year date
     * @throws ParseException
     *             the parse exception
     */
    public static String getNextYearDate(String date) throws ParseException {
        return getNextYearDate(date, 1);
    }

    /**
     * 取得下N年的日期，格式：yyyymmdd.
     *
     * @param date
     *            the date
     * @param years
     *            the years
     * @return the next year date
     * @throws ParseException
     *             the parse exception
     */
    public static String getNextYearDate(String date, int years) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new SimpleDateFormat("yyyyMMdd").parse(date));
        calendar.add(Calendar.YEAR, years);

        return new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
    }

    /**
     * 取得前幾個月的日期，格式：yyyyMMdd.
     *
     * @param date
     *            the date
     * @param months
     *            the months
     * @return the previous month date
     * @throws ParseException
     *             the parse exception
     */
    public static String getPreviousMonthDate(String date, int months) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new SimpleDateFormat("yyyyMMdd").parse(date));
        calendar.add(Calendar.MONTH, -months);

        return new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
    }

    /**
     * 取得上一個月的日期，格式：yyyyMMdd.
     *
     * @param date
     *            the date
     * @return the previous month date
     * @throws ParseException
     *             the parse exception
     */
    public static String getPreviousMonthDate(String date) throws ParseException {
        return getPreviousMonthDate(date, 1);
    }

    /**
     * 取得前幾個月份，格式：yyyymm.
     *
     * @param yearMonth
     *            the year month
     * @param months
     *            the months
     * @return the previous month
     * @throws ParseException
     *             the parse exception
     */
    public static String getPreviousMonth(String yearMonth, int months) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new SimpleDateFormat("yyyyMM").parse(yearMonth));
        calendar.add(Calendar.MONTH, -months);

        return new SimpleDateFormat("yyyyMM").format(calendar.getTime());
    }

    /**
     * 取得上一個月份，格式：yyyymm.
     *
     * @param yearMonth
     *            the year month
     * @return the previous month
     * @throws ParseException
     *             the parse exception
     */
    public static String getPreviousMonth(String yearMonth) throws ParseException {
        return getPreviousMonth(yearMonth, 1);
    }

    /**
     * 取回指定民國日期的下N天日期，格式：yyyMMdd.
     *
     * @param date
     *            the date
     * @param n
     *            the n
     * @return the next date
     * @throws ParseException
     *             the parse exception
     */
    public static String getNextRocDate(String date, int n) throws ParseException {
        String acDate = convertRocDayToAcDay(date);
        String nextAcDate = getNextDate(acDate, n);
        return convertAcDayToRocDay(nextAcDate);
    }

    /**
     * 取得下N個月的民國日期，格式：yyymmdd.
     *
     * @param date
     *            the date
     * @param months
     *            the months
     * @return the next month date
     * @throws ParseException
     *             the parse exception
     */
    public static String getNextRocMonthDate(String date, int months) throws ParseException {
        String acDate = convertRocDayToAcDay(date);
        String nextAcDate = getNextMonthDate(acDate, months);
        return convertAcDayToRocDay(nextAcDate);
    }

    public static String getNextRocYearDate(String date, int years) throws ParseException {
        String acDate = convertRocDayToAcDay(date);
        String nextAcDate = getNextYearDate(acDate, years);
        return convertAcDayToRocDay(nextAcDate);
    }

    /**
     * 取得此月的第一天日期，格式：yyyymmdd.
     *
     * @param yearMonth
     *            the year month
     * @return the first day of month
     * @throws ParseException
     *             the parse exception
     */
    public static String getFirstDayOfMonth(String yearMonth) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new SimpleDateFormat("yyyyMM").parse(yearMonth));

        return new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
    }

    /**
     * 取得此月的最後一天日期，格式：yyyymmdd.
     *
     * @param yearMonth
     *            the year month
     * @return the last day of month
     * @throws ParseException
     *             the parse exception
     */
    public static String getLastDayOfMonth(String yearMonth) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new SimpleDateFormat("yyyyMM").parse(yearMonth));
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_YEAR, -1);

        return new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
    }

    /**
     * 判斷該日期是否合法.
     *
     * @param date
     *            the date
     * @return true, if is valid date
     */
    public static boolean isValidDate(String date) {
        try {
            return date.equals(new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("yyyyMMdd").parse(date)));
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * 將指定的format之日期轉換成yyyyMMdd.
     *
     * @param date
     *            the date
     * @param format
     *            the format
     * @return the string
     * @throws ParseException
     *             the parse exception
     */
    public static String transfer(String date, String format) throws ParseException {
        return new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat(format).parse(date));
    }

    /**
     * 轉換指定的日期格式.
     *
     * @param date
     *            the date
     * @param origFormat
     *            the orig format
     * @param format
     *            the format
     * @return the string
     * @throws ParseException
     *             the parse exception
     */
    public static String transfer(String date, String origFormat, String format) throws ParseException {
        return new SimpleDateFormat(format).format(new SimpleDateFormat(origFormat).parse(date));
    }

    /**
     * Convert m mdd toyyyy m mdd.
     *
     * @param MMdd
     *            the m mdd
     * @return the string
     */
    public static final String convertMMddToyyyyMMdd(String MMdd) {
        String sysDate = formatDate(new Date(), "yyyyMMdd");
        long sysDateCount = Long.parseLong(sysDate);
        String cnvDate = (new StringBuilder(String.valueOf(sysDate.substring(0, 4)))).append(MMdd).toString();
        long cnvDateCount = Long.parseLong(cnvDate);
        if (cnvDateCount > sysDateCount) {
            for (; cnvDateCount > sysDateCount; cnvDateCount -= 10000L) {
                ;
            }
            cnvDate = String.valueOf(cnvDateCount);
        }
        return cnvDate;
    }

    /**
     * 取回系統民國日期，格式:yyyMMdd.
     *
     * @param yyyMMdd
     *            the yyy m mdd
     * @return the string
     */
    public static final String convertRocDayToAcDay(String yyyMMdd) {
        StringBuffer retDateStr = null;
        if (yyyMMdd == null) {
            return "";
        }
        String yyMMddStr = yyyMMdd.trim();
        if (yyMMddStr.length() >= 6) {
            retDateStr = new StringBuffer();
            if (yyMMddStr.length() == 6) {
                retDateStr.append(String.valueOf(Integer.parseInt(yyMMddStr.substring(0, 2)) + 1911));
                retDateStr.append(yyMMddStr.substring(2));
            } else {
                retDateStr.append(String.valueOf(Integer.parseInt(yyMMddStr.substring(0, 3)) + 1911));
                retDateStr.append(yyMMddStr.substring(3));
            }
            return retDateStr.toString();
        } else {
            return yyyMMdd;
        }
    }

    /**
     * 取回系統民國日期，格式:yyyMMdd.
     *
     * @param yyyyMMdd
     *            the yyyy m mdd
     * @return the string
     */
    public static final String convertAcDayToRocDay(String yyyyMMdd) {
        StringBuffer retDateStr = null;
        if (StringUtils.isEmpty(yyyyMMdd)) {
            return null;
        }
        String yyyyMMddStr = yyyyMMdd.trim();
        if (yyyyMMddStr.length() >= 8) {
            retDateStr = new StringBuffer();
            int rocYearInt = Integer.parseInt(yyyyMMddStr.substring(0, 4)) - 1911;
            retDateStr.append(String.valueOf(rocYearInt));
            retDateStr.append(yyyyMMddStr.substring(4));
            return retDateStr.toString();
        } else {
            return yyyyMMdd;
        }
    }

    /**
     * Gets the gMT string.
     *
     * @param date
     *            the date
     * @param pattern
     *            the pattern
     * @return the gMT string
     */
    public static final String getGMTString(Date date, String pattern) {
        DateFormat dFormat = new SimpleDateFormat(pattern);
        dFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dFormat.format(date);
    }

    /**
     * Gets the local string.
     *
     * @param date
     *            the date
     * @param pattern
     *            the pattern
     * @return the local string
     */
    public static final String getLocalString(Date date, String pattern) {
        DateFormat dFormat = new SimpleDateFormat(pattern);
        return dFormat.format(date);
    }

    /**
     * Gets the roc date string.
     *
     * @param date
     *            the date
     * @return the roc date string
     */
    public static final String getRocDateString(Date date) {
        String yyyyStr = formatDate(date, "yyyy");
        int yyyyInt = Integer.parseInt(yyyyStr);
        int yyInt = yyyyInt - 1911;
        String yyStr = String.valueOf(yyInt);
        String rocyyMMddhhmmss = (new StringBuilder(String.valueOf(yyStr))).append(formatDate(date, "MMddHHmmss"))
                .toString();
        return rocyyMMddhhmmss;
    }

    /**
     * Parses the date time.
     *
     * @param dateString
     *            the date string
     * @param pattern
     *            the pattern
     * @return the date
     */
    public static final Date parseDateTime(String dateString, String pattern) {
        DateFormat dateformat = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = dateformat.parse(dateString);
        } catch (ParseException e) {
            log.error("", e);
        }
        return date;
    }

    /**
     * Conv gm t2 local.
     *
     * @param fld7GMT
     *            the fld7 gmt
     * @return the string
     */
    public static String convGMT2Local(String fld7GMT) {
        String ret = "";
        try {
            SimpleDateFormat format0 = new SimpleDateFormat("MMddHHmmss");
            SimpleDateFormat format1 = new SimpleDateFormat("MMddHHmmss");
            Calendar cal0 = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
            Calendar cal1 = Calendar.getInstance(TimeZone.getDefault());
            format0.setCalendar(cal0);
            format1.setCalendar(cal1);
            Date date = format0.parse(fld7GMT);
            ret = format1.format(date);
        } catch (Exception e) {
            log.error("", e);
        }
        return ret;
    }

    /**
     * Checks if is valid.
     *
     * @param value
     *            the value
     * @param datePattern
     *            the date pattern
     * @return true, if is valid
     */
    public static boolean isValid(String value, String datePattern) {
        if (value == null || datePattern == null || datePattern.length() <= 0) {
            return false;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(datePattern);
        formatter.setLenient(false);
        try {
            formatter.parse(value);
        } catch (ParseException e) {
            return false;
        }
        return datePattern.length() == value.length();
    }

    /**
     * Format date.
     *
     * @param d
     *            the d
     * @param pattern
     *            the pattern
     * @return the string
     */
    public static String formatDate(Date d, String pattern) {
        SimpleDateFormat df = (SimpleDateFormat) DateFormat.getDateTimeInstance();
        df.setTimeZone(TimeZone.getDefault());
        df.applyPattern(pattern);
        return df.format(d);
    }

    /**
     * Gets the rOC date.
     *
     * @return the rOC date
     */
    public static String getROCDate() {
        return convertAcDayToRocDay(getSystemDate());
    }

    /**
     * Replace date.
     *
     * @param dateTime
     *            the date time
     * @return the string
     */
    public static String replaceDate(String dateTime) {
        if (dateTime == null) {
            return null;
        } else {
            return dateTime.replaceAll("/", "");
        }
    }

    /**
     * Fill delimiter roc date.
     *
     * @param dateTime
     *            the date time
     * @return the string
     */
    public static String fillDelimiterROCDate(String dateTime) {
        if (dateTime == null) {
            return "";
        } else if (dateTime.length() == 7) {
            return dateTime.substring(0, 3) + "/" + dateTime.substring(3, 5) + "/" + dateTime.substring(5, 7);
        } else if (dateTime.length() == 6) {
            return dateTime.substring(0, 2) + "/" + dateTime.substring(2, 4) + "/" + dateTime.substring(4, 6);
        } else if (dateTime.length() == 13) {
            return dateTime.substring(0, 3) + "/" + dateTime.substring(3, 5) + "/" + dateTime.substring(5, 7)
                    + dateTime.substring(7, 9) + ":" + dateTime.substring(9, 11) + ":" + dateTime.substring(11, 13);
        } else if (dateTime.length() == 12) {
            return dateTime.substring(0, 2) + "/" + dateTime.substring(2, 4) + "/" + dateTime.substring(4, 6)
                    + dateTime.substring(6, 8) + ":" + dateTime.substring(8, 10) + ":" + dateTime.substring(10, 12);
        } else if (dateTime.length() == 5) {
            return dateTime.substring(0, 3) + "/" + dateTime.substring(3, 5);
        } else if (dateTime.length() > 13) {
            return dateTime.substring(0, 3) + "/" + dateTime.substring(3, 5) + "/" + dateTime.substring(5, 7) + " "
                    + dateTime.substring(7, 9) + ":" + dateTime.substring(9, 11) + ":" + dateTime.substring(11, 13);
        }
        return dateTime;
    }

    /**
     * Gets the rOC date time.
     *
     * @return the rOC date time
     */
    public static String getROCDateTime() {
        StringBuffer retDateStr = null;
        String GMTDateTimeStr = (getSystemDate() + getSystemTime().trim());
        if (GMTDateTimeStr.length() == 14) {
            retDateStr = new StringBuffer();
            int rocYearInt = Integer.parseInt(GMTDateTimeStr.substring(0, 4)) - 1911;
            retDateStr.append(String.valueOf(rocYearInt));
            retDateStr.append(GMTDateTimeStr.substring(4, 14));
            return retDateStr.toString();
        } else {
            return null;
        }
    }

    /**
     * Gets the rOC date time millis.
     *
     * @return the rOC date time millis
     */
    public static String getROCDateTimeMillis() {
        StringBuffer retDateStr = null;
        String GMTDateTimeStr = (getSystemDate() + getSystemTime().trim() + getSystemMillis());
        if (GMTDateTimeStr.length() == 17) {
            retDateStr = new StringBuffer();
            int rocYearInt = Integer.parseInt(GMTDateTimeStr.substring(0, 4)) - 1911;
            retDateStr.append(String.valueOf(rocYearInt));
            retDateStr.append(GMTDateTimeStr.substring(4, 16));
            return retDateStr.toString();
        } else {
            return null;
        }
    }

    /**
     * 取回系統時間，格式:SSS.
     *
     * @return the system millisecord
     */
    public static String getSystemMillis() {
        return new SimpleDateFormat("SSS").format(new GregorianCalendar().getTime());
    }

    /**
     * Fill delimiter roc date.
     *
     * @param dateTime
     *            the date time
     * @return the string
     */
    public static String fillDelimiterROCDate2CHN(String dateTime) {
        if (dateTime == null) {
            return "";
        } else if (dateTime.length() == 7) {
            return dateTime.substring(0, 3) + "年" + dateTime.substring(3, 5) + "月" + dateTime.substring(5, 7) + "日";
        } else if (dateTime.length() == 6) {
            return dateTime.substring(0, 2) + "年" + dateTime.substring(2, 4) + "月" + dateTime.substring(4, 6) + "日";
        } else if (dateTime.length() == 13) {
            return dateTime.substring(0, 3) + "年" + dateTime.substring(3, 5) + "月" + dateTime.substring(5, 7) + "日"
                    + dateTime.substring(7, 9) + "時" + dateTime.substring(9, 11) + "分" + dateTime.substring(11, 13)
                    + "秒";
        } else if (dateTime.length() == 15) {
            return dateTime.substring(0, 3) + "年" + dateTime.substring(3, 5) + "月" + dateTime.substring(5, 7) + "日"
                    + dateTime.substring(7, 9) + "時" + dateTime.substring(9, 11) + "分" + dateTime.substring(11, 13)
                    + "秒" + "." + dateTime.substring(13, 15);
        } else if (dateTime.length() == 12) {
            return dateTime.substring(0, 2) + "年" + dateTime.substring(2, 4) + "月" + dateTime.substring(4, 6) + "日"
                    + dateTime.substring(6, 8) + "時" + dateTime.substring(8, 10) + "分" + dateTime.substring(10, 12)
                    + "秒";
        } else if (dateTime.length() == 5) {
            return dateTime.substring(0, 3) + "年" + dateTime.substring(3, 5) + "月";
        }
        return dateTime;
    }
}
