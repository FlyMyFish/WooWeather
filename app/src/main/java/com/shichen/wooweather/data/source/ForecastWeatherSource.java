package com.shichen.wooweather.data.source;

import android.support.annotation.NonNull;

import com.shichen.wooweather.data.ForecastWeather;

/**
 * @author shichen 754314442@qq.com
 * Created by Administrator on 2018/11/7.
 */
public interface ForecastWeatherSource {
    interface LoadWeatherCallBack{
        void onLoaded(ForecastWeather forecastWeather);
        void onDataNotAvailable(String msg);
    }

    /**
     * 通过Forecast Request获取天气信息
     * @param callBack
     */
    void loadForecastWeather(@NonNull String query, LoadWeatherCallBack callBack);

    void loadForecastWeather(@NonNull String query, String lat, String lon,LoadWeatherCallBack callBack);

    void saveForecastWeather(ForecastWeather forecastWeather);

    void updateForecastWeather(ForecastWeather forecastWeather);

    void deleteForecastWeather(String query);
}
