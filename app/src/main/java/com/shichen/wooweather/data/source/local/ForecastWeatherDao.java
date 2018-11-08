package com.shichen.wooweather.data.source.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.support.annotation.NonNull;

import com.shichen.wooweather.data.ForecastWeather;

/**
 * @author shichen 754314442@qq.com
 * Created by Administrator on 2018/11/7.
 */
@Dao
public interface ForecastWeatherDao {
    @Query("SELECT * FROM weather WHERE queryStr = :query")
    ForecastWeather getForecastWeather(@NonNull String query);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertForecastWeather(ForecastWeather forecastWeather);

    @Update
    void updateForecastWeather(ForecastWeather forecastWeather);

    @Query("DELETE FROM weather WHERE queryStr=:query")
    int deleteForecastWeather(String query);
}
