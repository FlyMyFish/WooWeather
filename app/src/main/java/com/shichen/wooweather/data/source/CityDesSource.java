package com.shichen.wooweather.data.source;

import android.support.annotation.NonNull;

import com.shichen.wooweather.data.CityDes;

/**
 * @author shichen 754314442@qq.com
 * Created by Administrator on 2018/11/7.
 */
public interface CityDesSource {
    interface LoadCityDesCallBack {
        void onCityDesLoaded(CityDes cityDes);

        void onNoDataAvailable(String msg);
    }

    void getCityDes(@NonNull String query, @NonNull LoadCityDesCallBack loadCityDesCallBack);

    void saveCityDes(CityDes cityDes);

    void updateCityDes(CityDes cityDes);

    void deleteCityDes(String query);

    void refreshCityDes();
}
