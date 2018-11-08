package com.shichen.wooweather.data.source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.shichen.wooweather.data.CityDes;
import com.shichen.wooweather.data.ForecastWeather;

/**
 * @author shichen 754314442@qq.com
 * Created by Administrator on 2018/11/7.
 */
@Database(entities = {CityDes.class, ForecastWeather.class}, version = 1)
public abstract class WooWeatherDataBase extends RoomDatabase {
    private static WooWeatherDataBase INSTANCE;

    public abstract CityDesDao cityDesDao();

    public abstract ForecastWeatherDao forecastWeatherDao();

    private static final Object sLock = new Object();

    public static WooWeatherDataBase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        WooWeatherDataBase.class, "WooWeather.db")
                        .build();
            }
            return INSTANCE;
        }
    }
}
