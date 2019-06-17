package com.example.diycode.utils;

import android.provider.ContactsContract;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 时间的工具类
 */
public class TimeUtil {
    private static final String TAG = "TimeUtil";
    public static String computePastTime(String time) {
        String result="刚刚";
        //2019-04-03T13:40:17.594+08:00 time的格式
        try {
            time=time.replace("T","");
            time=time.substring(0,22);
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-ddHH:mm:ss.SSS",
                    Locale.SIMPLIFIED_CHINESE);
            Date nowDate=new Date(System.currentTimeMillis());
            Date timeDate=simpleDateFormat.parse(time);
            long pastLong=(nowDate.getTime()-timeDate.getTime())/1000;//毫秒
            if (pastLong<60){//秒
                result="刚刚";
            }else if ((pastLong/=60)<60){
                result=pastLong+"分钟前";
            }else if ((pastLong/=60)<24){
                result=pastLong+"小时前";
            }else if ((pastLong/=24)<30){
                result=pastLong+"天前";
            }else if ((pastLong/=30)<12){
                result=pastLong+"月前";
            }else {
                pastLong /= 12;
                result = pastLong + "年前";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;


    }
}
