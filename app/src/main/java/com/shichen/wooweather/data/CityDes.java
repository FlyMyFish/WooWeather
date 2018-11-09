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
@Entity(tableName = "cityDes")
public class CityDes {
    /**
     * "name": "管城回族区, Henan",
     * "street": "商城路",
     * "latitude": 35.2992,
     * "longitude": 113.8781,
     * "queryStr":zhengzhou
     */
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "queryStr")
    private String queryStr;
    @Nullable
    @ColumnInfo(name = "name")
    private String name;
    @Nullable
    @ColumnInfo(name = "street")
    private String street;
    @NonNull
    @ColumnInfo(name = "latitude")
    private String latitude;
    @NonNull
    @ColumnInfo(name = "longitude")
    private String longitude;

    @ColumnInfo(name = "timestamp")
    private long timestamp;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }

    @Nullable
    public String getStreet() {
        return street;
    }

    public void setStreet(@Nullable String street) {
        this.street = street;
    }

    @NonNull
    public String getQueryStr() {
        return queryStr;
    }

    public void setQueryStr(@NonNull String queryStr) {
        this.queryStr = queryStr;
    }

    @Nullable
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

    @Ignore
    public CityDes(@NonNull String queryStr, String latitude, String longitude) {
        this(queryStr, latitude, longitude, null, null);
    }

    public CityDes(@NonNull String queryStr, String latitude, String longitude, String name, String street) {
        this.queryStr = queryStr;
        this.name = name;
        this.street = street;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = (System.currentTimeMillis() / 1000);
    }

    public boolean isEmpty() {
        return Strings.isNullOrEmpty(latitude) &&
                Strings.isNullOrEmpty(longitude);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityDes cityDes = (CityDes) o;
        return Objects.equal(queryStr, cityDes.queryStr) &&
                Objects.equal(latitude, cityDes.latitude) &&
                Objects.equal(longitude, cityDes.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(queryStr, latitude, longitude);
    }

    @Override
    public String toString() {
        return GsonUtils.getInstance().get().toJson(this);
    }
}
