package com.shichen.wooweather.data.source.remote;

import android.support.annotation.NonNull;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.shichen.wooweather.BuildConfig;
import com.shichen.wooweather.basic.Config;
import com.shichen.wooweather.data.BlockBean;
import com.shichen.wooweather.data.CityDes;
import com.shichen.wooweather.data.GPSBean;
import com.shichen.wooweather.data.source.CityDesSource;
import com.shichen.wooweather.utils.AppExecutors;
import com.shichen.wooweather.utils.GsonUtils;
import com.shichen.wooweather.utils.LogUtilsWoo;
import com.shichen.wooweather.utils.RequestHelper;
import com.shichen.wooweather.utils.WooException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shichen 754314442@qq.com
 * Created by Administrator on 2018/11/7.
 */
public class CityDesRemoteSource implements CityDesSource {
    private static CityDesRemoteSource INSTANCE;
    private AppExecutors mAppExecutors;
    private final String TAG = "CityDesRemoteSource";

    public static CityDesRemoteSource getInstance(AppExecutors appExecutors) {
        if (INSTANCE == null) {
            INSTANCE = new CityDesRemoteSource(appExecutors);
        }
        return INSTANCE;
    }

    private CityDesRemoteSource(AppExecutors appExecutors) {
        mAppExecutors = appExecutors;
    }

    @Override
    public void getCityDes(@NonNull final String query, @NonNull final LoadCityDesCallBack loadCityDesCallBack) {
        final StringBuilder url = new StringBuilder(Config.Q_TO_CITY);
        url.append("q=");
        url.append(query);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                RequestHelper.getInstance().GetRequest(url.toString(), null, new RequestHelper.RequestCallBack() {
                    @Override
                    public void onSuccess(String responseBody) {
                        LogUtilsWoo.Log(TAG, responseBody);
                        try {
                            JsonParser parser = new JsonParser();
                            JsonElement element = parser.parse(responseBody);
                            if (element.isJsonObject()) {
                                GPSBean gpsBean = GsonUtils.getInstance().get().fromJson(element, GPSBean.class);
                                if (gpsBean != null) {
                                    getCityDesName(query, gpsBean.getLatitude(), gpsBean.getLongitude(), loadCityDesCallBack);
                                } else {
                                    onError(loadCityDesCallBack, "Data not available");
                                }
                            } else {
                                onError(loadCityDesCallBack, "Not a Json");
                            }
                        } catch (JsonSyntaxException e) {
                            onError(loadCityDesCallBack, e.toString());
                        }
                    }

                    @Override
                    public void onFailed(Exception e) {
                        if (e instanceof WooException) {
                            WooException wooException = (WooException) e;
                            LogUtilsWoo.Log(TAG, "code=" + wooException.getResponseCode() + ";msg=" + wooException.getMsg());
                            onError(loadCityDesCallBack, "code=" + wooException.getResponseCode() + ";msg=" + wooException.getMsg());
                        } else {
                            LogUtilsWoo.Log(TAG, e.toString());
                            onError(loadCityDesCallBack, e.toString());
                        }
                    }
                });
            }
        };
        mAppExecutors.networkIO().execute(runnable);
    }

    private void onError(final LoadCityDesCallBack loadCityDesCallBack, final String msg) {
        mAppExecutors.mainThread().execute(new Runnable() {
            @Override
            public void run() {
                loadCityDesCallBack.onNoDataAvailable(msg);
            }
        });
    }

    private void getCityDesName(final String query, final String lat, final String lon, final LoadCityDesCallBack loadCityDesCallBack) {
        final Map<String, String> params = new HashMap<>();
        params.put("lat", lat);
        params.put("lon", lon);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                RequestHelper.getInstance().GetRequest(Config.GPS_TO_CITY, params, new RequestHelper.RequestCallBack() {
                    @Override
                    public void onSuccess(String responseBody) {
                        LogUtilsWoo.Log(TAG, responseBody);
                        try {
                            JsonParser parser = new JsonParser();
                            JsonElement element = parser.parse(responseBody);
                            if (element.isJsonObject()) {
                                BlockBean blockBean = GsonUtils.getInstance().get().fromJson(element, BlockBean.class);
                                if (blockBean != null) {
                                    final CityDes cityDes = new CityDes(query, lat, lon, blockBean.getName(), blockBean.getStreet());
                                    mAppExecutors.mainThread().execute(new Runnable() {
                                        @Override
                                        public void run() {
                                            loadCityDesCallBack.onCityDesLoaded(cityDes);
                                        }
                                    });
                                } else {
                                    onError(loadCityDesCallBack, "Not a " + BlockBean.class.getSimpleName() + "Json");
                                }
                            } else {
                                onError(loadCityDesCallBack, "Not a Json");
                            }
                        } catch (JsonSyntaxException e) {
                            onError(loadCityDesCallBack, e.toString());
                        }
                    }

                    @Override
                    public void onFailed(Exception e) {
                        if (e instanceof WooException) {
                            WooException wooException = (WooException) e;
                            LogUtilsWoo.Log(TAG, "code=" + wooException.getResponseCode() + ";msg=" + wooException.getMsg());
                            onError(loadCityDesCallBack, "code=" + wooException.getResponseCode() + ";msg=" + wooException.getMsg());
                        } else {
                            LogUtilsWoo.Log(TAG, e.toString());
                            onError(loadCityDesCallBack, e.toString());
                        }
                    }
                });
            }
        };
        mAppExecutors.networkIO().execute(runnable);
    }

    @Override
    public void saveCityDes(CityDes cityDes) {

    }

    @Override
    public void updateCityDes(CityDes cityDes) {

    }

    @Override
    public void deleteCityDes(String query) {

    }

    @Override
    public void refreshCityDes() {

    }
}
