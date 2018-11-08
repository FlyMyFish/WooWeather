package com.shichen.wooweather.utils;

/**
 * @author shichen 754314442@qq.com
 * Created by Administrator on 2018/11/8.
 */
public class WooException extends Exception {
    private int responseCode;
    private String msg;

    public WooException() {
        super();
    }

    public WooException(int responseCode, String msg) {
        this.responseCode = responseCode;
        this.msg = msg;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getMsg() {
        return msg;
    }
}
