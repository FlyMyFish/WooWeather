package com.shichen.wooweather.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.shichen.wooweather.utils.GsonUtils;

/**
 * @author shichen 754314442@qq.com
 * Created by Administrator on 2018/11/7.
 */
@Entity(tableName = "weather")
public class ForecastWeather {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "queryStr")
    private String queryStr;
    @NonNull
    @ColumnInfo(name = "latitude")
    private String latitude;
    @NonNull
    @ColumnInfo(name = "longitude")
    private String longitude;
    @NonNull
    @ColumnInfo(name = "timezone")
    private String timezone;
    @Nullable
    @ColumnInfo(name = "currently")
    private String currently;
    @Nullable
    @ColumnInfo(name = "hourly")
    private String hourly;
    @Nullable
    @ColumnInfo(name = "daily")
    private String daily;
    @Nullable
    @ColumnInfo(name = "flags")
    private String flags;
    @ColumnInfo(name = "offset")
    private int offset;

    @NonNull
    public String getQueryStr() {
        return queryStr;
    }

    public void setQueryStr(@NonNull String queryStr) {
        this.queryStr = queryStr;
    }

    @NonNull
    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(@NonNull String latitude) {
        this.latitude = latitude;
    }

    @NonNull
    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(@NonNull String longitude) {
        this.longitude = longitude;
    }

    @NonNull
    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(@NonNull String timezone) {
        this.timezone = timezone;
    }

    @Nullable
    public String getCurrently() {
        return currently;
    }

    public void setCurrently(@Nullable String currently) {
        this.currently = currently;
    }

    @Nullable
    public String getHourly() {
        return hourly;
    }

    public void setHourly(@Nullable String hourly) {
        this.hourly = hourly;
    }

    @Nullable
    public String getDaily() {
        return daily;
    }

    public void setDaily(@Nullable String daily) {
        this.daily = daily;
    }

    @Nullable
    public String getFlags() {
        return flags;
    }

    public void setFlags(@Nullable String flags) {
        this.flags = flags;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Ignore
    public ForecastWeather(@NonNull String queryStr, @NonNull String latitude, @NonNull String longitude, @NonNull String timezone) {
        this(queryStr, latitude, longitude, timezone, null, null, null, null, 0);
    }

    public ForecastWeather(@NonNull String queryStr, @NonNull String latitude, @NonNull String longitude, @NonNull String timezone, @Nullable String currently, @Nullable String hourly, @Nullable String daily, @Nullable String flags, int offset) {
        this.queryStr = queryStr;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timezone = timezone;
        this.currently = currently;
        this.hourly = hourly;
        this.daily = daily;
        this.flags = flags;
        this.offset = offset;
    }

    public boolean isEmpty() {
        return Strings.isNullOrEmpty(currently) &&
                Strings.isNullOrEmpty(hourly) &&
                Strings.isNullOrEmpty(daily) &&
                Strings.isNullOrEmpty(flags);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ForecastWeather forecastWeather = (ForecastWeather) o;
        return Objects.equal(queryStr, forecastWeather.queryStr) &&
                Objects.equal(latitude, forecastWeather.latitude) &&
                Objects.equal(longitude, forecastWeather.longitude) &&
                Objects.equal(timezone, forecastWeather.timezone) &&
                Objects.equal(currently, forecastWeather.currently) &&
                Objects.equal(hourly, forecastWeather.hourly) &&
                Objects.equal(daily, forecastWeather.daily) &&
                Objects.equal(flags, forecastWeather.flags);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(queryStr, latitude, longitude,timezone,currently,hourly,daily,flags);
    }

    @Override
    public String toString() {
        return GsonUtils.getInstance().get().toJson(this);
    }
}
