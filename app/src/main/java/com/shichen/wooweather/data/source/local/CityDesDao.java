package com.shichen.wooweather.data.source.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.support.annotation.NonNull;

import com.shichen.wooweather.data.CityDes;

/**
 * @author shichen 754314442@qq.com
 * Created by Administrator on 2018/11/7.
 */
@Dao
public interface CityDesDao {
    /**
     * 通过query搜索城市信息
     * @param query
     * @return
     */
    @Query("SELECT * FROM cityDes WHERE queryStr = :query")
    CityDes getCityDesByQuery(@NonNull String query);

    /**
     * 插入一条城市信息，如果重复了，那么就替换掉他
     * @param cityDes
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCityDes(CityDes cityDes);

    /**
     * 更新一条城市信息
     * @param cityDes
     */
    @Update
    void updateCityDes(CityDes cityDes);

    /**
     * 删除一条城市信息
     * @param query
     * @return
     */
    @Query("DELETE FROM cityDes WHERE queryStr = :query")
    int deleteCityDes(@NonNull String query);
}
