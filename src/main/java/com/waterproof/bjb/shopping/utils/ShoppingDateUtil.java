package com.waterproof.bjb.shopping.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
