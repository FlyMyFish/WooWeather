package com.shichen.wooweather.data.source.remote;

import android.support.annotation.NonNull;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.shichen.wooweather.BuildConfig;
import com.shichen.wooweather.basic.Config;
import com.shichen.wooweather.data.ForecastResponseBean;
import com.shichen.wooweather.data.ForecastWeather;
import com.shichen.wooweather.data.source.ForecastWeatherSource;
import com.shichen.wooweather.data.source.local.ForecastWeatherDao;
import com.shichen.wooweather.utils.AppExecutors;
import com.shichen.wooweather.utils.GsonUtils;
import com.shichen.wooweather.utils.LogUtilsWoo;
import com.shichen.wooweather.utils.RequestHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shichen 754314442@qq.com
 * Created by Administrator on 2018/11/12.
 */
public class ForecastWeatherRemoteSource implements ForecastWeatherSource {
    private static volatile ForecastWeatherRemoteSource INSTANCE;
    private ForecastWeatherDao mForecastWeatherDao;
    private AppExecutors mAppExecutors;
    private final String TAG = "ForecastWeatherRemoteSource";

    public static ForecastWeatherRemoteSource getInstance(AppExecutors appExecutors) {
        if (INSTANCE == null) {
            INSTANCE = new ForecastWeatherRemoteSource(appExecutors);
        }
        return INSTANCE;
    }

    private ForecastWeatherRemoteSource(AppExecutors appExecutors) {
        mAppExecutors = appExecutors;
    }

    @Override
    public void loadForecastWeather(@NonNull final String query, String lat, String lon, final LoadWeatherCallBack callBack) {
        final StringBuilder url = new StringBuilder(Config.FORECAST);
        url.append(BuildConfig.DARKSKY);
        url.append("/");
        url.append(lat);
        url.append(",");
        url.append(lon);
        url.append("?");
        final Map<String, String> params = new HashMap<>();
        params.put("lang", "zh");
        params.put("units", "si");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                RequestHelper.getInstance().GetRequest(url.toString(), params, new RequestHelper.RequestCallBack() {
                    @Override
                    public void onSuccess(String responseBody) {
                        LogUtilsWoo.Log(TAG, responseBody);
                        try {
                            JsonParser parser = new JsonParser();
                            JsonElement element = parser.parse(responseBody);
                            if (element.isJsonObject()) {
                                final ForecastResponseBean forecastResponseBean = GsonUtils.getInstance().get().fromJson(element, ForecastResponseBean.class);
                                if (forecastResponseBean != null) {
                                    final ForecastWeather forecastWeather=new ForecastWeather(query,
                                            forecastResponseBean.getLatitude(),
                                            forecastResponseBean.getLongitude(),
                                            forecastResponseBean.getTimezone(),
                                            GsonUtils.getInstance().get().toJson(forecastResponseBean.getCurrently()),
                                            GsonUtils.getInstance().get().toJson(forecastResponseBean.getHourly()),
                                            GsonUtils.getInstance().get().toJson(forecastResponseBean.getDaily()),
                                            GsonUtils.getInstance().get().toJson(forecastResponseBean.getFlags()),
                                            forecastResponseBean.getOffset());
                                    mAppExecutors.mainThread().execute(new Runnable() {
                                        @Override
                                        public void run() {
                                            callBack.onLoaded(forecastWeather);
                                        }
                                    });
                                } else {
                                    onError(callBack, "Data not available");
                                }
                            } else {
                                onError(callBack, "Not a Json");
                            }
                        } catch (JsonSyntaxException e) {
                            onError(callBack, e.toString());
                        }
                    }

                    @Override
                    public void onFailed(Exception e) {
                        onError(callBack, e.toString());
                    }
                });
            }
        };
        mAppExecutors.networkIO().execute(runnable);
    }

    private void onError(final LoadWeatherCallBack loadWeatherCallBack, final String msg) {
        mAppExecutors.mainThread().execute(new Runnable() {
            @Override
            public void run() {
                loadWeatherCallBack.onDataNotAvailable(msg);
            }
        });
    }

    @Override
    public void loadForecastWeather(@NonNull String query, LoadWeatherCallBack callBack) {

    }

    @Override
    public void saveForecastWeather(ForecastWeather forecastWeather) {

    }

    @Override
    public void updateForecastWeather(ForecastWeather forecastWeather) {

    }

    @Override
    public void deleteForecastWeather(String query) {

    }
}
