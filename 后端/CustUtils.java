package com.ysu.drgs.utils;

import org.apache.commons.lang.xwork.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @program: dataPlatform_ht
 * @description: CustUtils 自定义工具类
 * @author: 韩盛捷
 * @create: 2021-10-13 08:29
 **/
public class CustUtils {

    //昨天
    private static String getYesterday(){
        Date today = new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-M-dd");
        String yesterday = simpleDateFormat.format(today);//获取昨天日期
        return yesterday;
    }
    //月初月末字符串
    private static String getMonthStartAndEnd(){
        String yesterday = getYesterday();

        String [] split = yesterday.split("\\-");
        Integer year = Integer.parseInt(split[0]);
        Integer month = Integer.parseInt(split[1]);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-M-dd");
        //获取前月的第一天
        Calendar cal_1=Calendar.getInstance();//获取当前日期
        cal_1.set(Calendar.YEAR,year);
        cal_1.set(Calendar.MONTH,month-1);
        cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        String firstDay = format.format(cal_1.getTime());
        //firstDay += " 00:00:00";
        //获取前月的最后一天
        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.YEAR,year);
        cale.set(Calendar.MONTH,month);
        cale.set(Calendar.DAY_OF_MONTH,0);
        String lastDay = format.format(cale.getTime());
        //lastDay += " 23:59:59";
        return  firstDay+","+lastDay;
    }

    //周
    public static String getWeek() {
        String yesterday = getYesterday();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");

        Calendar cal = Calendar.getInstance();
        //设置昨天
        cal.add(5,-1);
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if(dayWeek==1){
            dayWeek = 8;
        }


        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - dayWeek);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        Date mondayDate = cal.getTime();
        String weekBegin = sdf.format(mondayDate);

        cal.add(Calendar.DATE, 4 +cal.getFirstDayOfWeek());
        Date sundayDate = cal.getTime();
        String weekEnd = sdf.format(sundayDate);
        return weekBegin+","+weekEnd;
    }
    //半年
    private static String getHalfYear(){
        String getHalfYear = "";
        String yesterday = getYesterday();
        String [] split = yesterday.split("\\-");
        Integer year = Integer.parseInt(split[0]);
        Integer month = Integer.parseInt(split[1]);

        if(month <7){
            getHalfYear = year+"-1-01,"+year+"-6-30";
        }else{
            getHalfYear = year+"-7-01,"+year+"-12-31";
        }
        return getHalfYear;
    }
    //年
    private static String getYear(){
        String getYear = "";
        String yesterday = getYesterday();
        String [] split = yesterday.split("\\-");
        Integer year = Integer.parseInt(split[0]);
        Integer month = Integer.parseInt(split[1]);
        getYear = year+"-1-01,"+year+"-12-31";
        return getYear;
    }
    //季
    private static String getQuarter(){
        String getQuarter = "";
        String yesterday = getYesterday();
        String [] split = yesterday.split("\\-");
        Integer year = Integer.parseInt(split[0]);
        Integer month = Integer.parseInt(split[1]);
        if( month<=3){
            getQuarter = year+"-1-01,"+year+"-3-31";
        }else if(month<=6){
            getQuarter = year+"-4-01,"+year+"-6-30";
        }else if(month<=9){
            getQuarter = year+"-7-01,"+year+"-9-30";
        }else{
            getQuarter = year+"-10-01,"+year+"-12-31";
        }
        return getQuarter;
    }
    public static void main(String[] args) {
        System.out.println(getMonthStartAndEnd());
        System.out.println(getYesterday());
        System.out.println(getHalfYear());
        System.out.println(getYear());
        System.out.println(getQuarter());
        System.out.println(getWeek());


    }
}
