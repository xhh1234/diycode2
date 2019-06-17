package com.example.diycode.utils;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlUtils {

    /**
     * 获取Url的hostName
     * @param Url
     * @return
     */
    public static String getHostName(String Url){
        String hostName="";
        try {
            URL url=new URL(Url);
            hostName=url.getHost();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return hostName;
    }
}
