package com.shichen.wooweather.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author shichen 754314442@qq.com
 * Created by Administrator on 2018/11/8.
 */
public class RequestHelper {
    private final String TAG = "RequestHelper";
    private static RequestHelper INSTANCE;

    private RequestHelper() {

    }

    public static RequestHelper getInstance() {
        if (INSTANCE == null) {
            synchronized (RequestHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RequestHelper();
                }
            }
        }
        return INSTANCE;
    }

    public void GetRequest(@NonNull String baseUrl, @Nullable Map<String, String> params, @NonNull RequestCallBack callBack) {
        try {
            String requestUrl;
            if (params != null) {
                StringBuilder tempParams = new StringBuilder();
                int pos = 0;
                for (String key : params.keySet()) {
                    if (pos > 0) {
                        tempParams.append("&");
                    }
                    tempParams.append(String.format("%s=%s", key, URLEncoder.encode(params.get(key), "utf-8")));
                    pos++;
                }
                requestUrl = baseUrl + tempParams.toString();
            } else {
                requestUrl = baseUrl;
            }
            LogUtilsWoo.Log(TAG, "requestUrl=" + requestUrl);
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(15 * 1000);
            connection.setReadTimeout(15 * 1000);
            connection.setUseCaches(false);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.addRequestProperty("Connection", "Keep-Alive");
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                String result = streamToString(connection.getInputStream(), callBack);
                callBack.onSuccess(result);
            } else if (responseCode == 400) {
                //Bad Request
                callBack.onFailed(new WooException(responseCode, "Bad Request"));
            } else if (responseCode == 401) {
                //需要添加用户验证
                callBack.onFailed(new WooException(responseCode, "Unauthorized"));
            } else if (responseCode == 403) {
                //Forbidden
                callBack.onFailed(new WooException(responseCode, "Forbidden"));
            } else if (responseCode == 404) {
                //Not Found
                callBack.onFailed(new WooException(responseCode, "Not Found"));
            } else if (responseCode == 405) {
                //Method Not Allowed
                callBack.onFailed(new WooException(responseCode, "Method Not Allowed"));
            } else if (responseCode == 408) {
                //Request Timeout
                callBack.onFailed(new WooException(responseCode, "Request Timeout"));
            } else if (responseCode == 415) {
                //Unsupported Media Type
                callBack.onFailed(new WooException(responseCode, "Unsupported Media Type"));
            } else if (responseCode == 500) {
                //Internal Server Error
                callBack.onFailed(new WooException(responseCode, "Internal Server Error"));
            } else if (responseCode == 502) {
                //Bad Gateway
                callBack.onFailed(new WooException(responseCode, "Bad Gateway"));
            } else if (responseCode == 503) {
                //Service Unavailable
                callBack.onFailed(new WooException(responseCode, "Service Unavailable"));
            } else if (responseCode == 504) {
                //Gateway Timeout
                callBack.onFailed(new WooException(responseCode, "Gateway Timeout"));
            } else {
                callBack.onFailed(new WooException(responseCode, "UnHandled"));
            }
            connection.disconnect();
        } catch (Exception e) {
            callBack.onFailed(e);
        }
    }

    public interface RequestCallBack {
        void onSuccess(String responseBody);

        void onFailed(Exception e);
    }

    /**
     * 将输入流转换成字符串
     *
     * @param is 从网络获取的输入流
     * @return
     */
    private String streamToString(InputStream is, RequestCallBack callBack) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            baos.close();
            is.close();
            byte[] byteArray = baos.toByteArray();
            return new String(byteArray);
        } catch (Exception e) {
            callBack.onFailed(e);
            return null;
        }
    }
}
