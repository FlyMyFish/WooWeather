package com.shichen.wooweather.data.source.local;

import android.support.annotation.NonNull;

import com.shichen.wooweather.data.ForecastWeather;
import com.shichen.wooweather.data.source.ForecastWeatherSource;
import com.shichen.wooweather.utils.AppExecutors;

/**
 * @author shichen 754314442@qq.com
 * Created by Administrator on 2018/11/7.
 */
public class ForecastWeatherLocalSource implements ForecastWeatherSource {
    private static volatile ForecastWeatherLocalSource INSTANCE;
    private ForecastWeatherDao mForecastWeatherDao;
    private AppExecutors mAppExecutors;

    private ForecastWeatherLocalSource(ForecastWeatherDao ForecastWeatherDao, AppExecutors AppExecutors) {
        mForecastWeatherDao = ForecastWeatherDao;
        mAppExecutors = AppExecutors;
    }

    public static ForecastWeatherLocalSource getInstance(ForecastWeatherDao ForecastWeatherDao, AppExecutors AppExecutors) {
        if (INSTANCE == null) {
            synchronized (ForecastWeatherLocalSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ForecastWeatherLocalSource(ForecastWeatherDao, AppExecutors);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void loadForecastWeather(@NonNull final String query, final LoadWeatherCallBack callBack) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                ForecastWeather forecastWeather = mForecastWeatherDao.getForecastWeather(query);
                if (forecastWeather != null) {
                    callBack.onLoaded(forecastWeather);
                } else {
                    callBack.onDataNotAvailable("没有从数据库中搜索到，该城市的相关天气");
                }
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void loadForecastWeather(@NonNull final String query, String lat, String lon, final LoadWeatherCallBack callBack) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                ForecastWeather forecastWeather = mForecastWeatherDao.getForecastWeather(query);
                if (forecastWeather != null) {
                    callBack.onLoaded(forecastWeather);
                } else {
                    callBack.onDataNotAvailable("没有从数据库中搜索到，该城市的相关天气");
                }
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void saveForecastWeather(final ForecastWeather forecastWeather) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                forecastWeather.setTimestemp((System.currentTimeMillis() / 1000));
                mForecastWeatherDao.insertForecastWeather(forecastWeather);
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void updateForecastWeather(final ForecastWeather forecastWeather) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                forecastWeather.setTimestemp((System.currentTimeMillis() / 1000));
                mForecastWeatherDao.updateForecastWeather(forecastWeather);
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void deleteForecastWeather(final String query) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mForecastWeatherDao.deleteForecastWeather(query);
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }
}
