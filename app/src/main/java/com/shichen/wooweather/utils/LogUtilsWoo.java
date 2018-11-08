package com.shichen.wooweather.utils;

import android.util.Log;

import com.shichen.wooweather.BuildConfig;


/**
 * @author shichen 754314442@qq.com
 * 车秒秒的Log工具类
 * Created by Administrator on 2018/9/28.
 */
public class LogUtilsWoo {
    public static void Log(String TAG, String msg) {
        //根据app编译的类型判断是否需要Log
        if (BuildConfig.DEBUG) {
            Log.e(TAG, msg);
        }
    }

    public static void Log(String TAG, String msg, Throwable e) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, msg, e);
        }
    }
}
