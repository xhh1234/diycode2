package com.mystudy.diycode_api.log;

import android.util.Log;

public class Logger {
    private static final int LEVEL_VERBOSE = 1;
    private static final int LEVEL_DEBUG = 2;
    private static final int LEVEL_INFO = 3;
    private static final int LEVEL_WARN = 4;
    private static final int LEVEL_ERROR = 5;
    private static final int LEVEL_ASSERT = 6;
    private static final int LEVEL_NONE = 0;
    private static final int LEVEL=LEVEL_ASSERT;
    private static String DEFAULT_TAG = "GCS-LOG";

    public static void v(String msg){
        if (LEVEL_VERBOSE<=LEVEL){
            Log.i(DEFAULT_TAG, "v: "+msg);
        }
    }
    public static void d(String msg){
        if (LEVEL_DEBUG<=LEVEL){
            Log.d(DEFAULT_TAG, "v: "+msg);
        }
    }
    public static void i(String msg){
        if (LEVEL_INFO<=LEVEL){
            Log.i(DEFAULT_TAG, "v: "+msg);
        }
    }
    public static void w(String msg){
        if (LEVEL_WARN<=LEVEL){
            Log.w(DEFAULT_TAG, "v: "+msg);
        }
    }
    public static void e(String msg){
        if (LEVEL_ERROR<=LEVEL){
            Log.e(DEFAULT_TAG, "v: "+msg);
        }
    }
    public static void a(String msg){
        if (LEVEL_ASSERT<=LEVEL){
            Log.e(DEFAULT_TAG, "v: "+msg);
        }
    }
}
