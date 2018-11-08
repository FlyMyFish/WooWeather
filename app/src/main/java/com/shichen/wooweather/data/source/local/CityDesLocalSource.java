package com.shichen.wooweather.data.source.local;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.shichen.wooweather.data.CityDes;
import com.shichen.wooweather.data.source.CityDesSource;
import com.shichen.wooweather.utils.AppExecutors;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author shichen 754314442@qq.com
 * Created by Administrator on 2018/11/7.
 */
public class CityDesLocalSource implements CityDesSource {

    private static volatile CityDesLocalSource INSTANCE;
    private CityDesDao mCityDesDao;
    private AppExecutors mAppExecutors;

    private CityDesLocalSource(@NonNull AppExecutors appExecutors,
                               @NonNull CityDesDao cityDesDao) {
        mAppExecutors = appExecutors;
        mCityDesDao = cityDesDao;
    }

    public static CityDesLocalSource getInstance(@NonNull AppExecutors appExecutors,
                                                 @NonNull CityDesDao cityDesDao) {
        if (INSTANCE == null) {
            synchronized (CityDesLocalSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CityDesLocalSource(appExecutors, cityDesDao);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getCityDes(@NonNull final String query, @NonNull final LoadCityDesCallBack loadCityDesCallBack) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final CityDes cityDes = mCityDesDao.getCityDesByQuery(query);
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (cityDes != null) {
                            loadCityDesCallBack.onCityDesLoaded(cityDes);
                        } else {
                            loadCityDesCallBack.onNoDataAvailable("没有从本地数据库得到该城市的数据");
                        }
                    }
                });
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void saveCityDes(final CityDes cityDes) {
        checkNotNull(cityDes);
        Runnable saveRunnable = new Runnable() {
            @Override
            public void run() {
                mCityDesDao.insertCityDes(cityDes);
            }
        };
        mAppExecutors.diskIO().execute(saveRunnable);
    }

    @Override
    public void updateCityDes(final CityDes cityDes) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mCityDesDao.updateCityDes(cityDes);
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void deleteCityDes(final String query) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mCityDesDao.deleteCityDes(query);
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @VisibleForTesting
    static void clearInstance() {
        INSTANCE = null;
    }

    @Override
    public void refreshCityDes() {

    }
}
