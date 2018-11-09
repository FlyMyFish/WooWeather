package com.shichen.wooweather.data.source.local;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.shichen.wooweather.data.CityDes;
import com.shichen.wooweather.data.source.CityDesSource;
import com.shichen.wooweather.utils.AppExecutors;

import java.util.List;

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
                cityDes.setTimestamp((System.currentTimeMillis() / 1000));
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
                cityDes.setTimestamp((System.currentTimeMillis() / 1000));
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

    @Override
    public void getRecentCityDes(@NonNull final LoadCityDesCallBack loadCityDesCallBack) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<CityDes> cityDesList = mCityDesDao.getAllCityDes();
                if (cityDesList == null) {
                    mAppExecutors.mainThread().execute(new Runnable() {
                        @Override
                        public void run() {
                            loadCityDesCallBack.onNoDataAvailable("数据库中没有城市");
                        }
                    });
                } else if (cityDesList.size() == 0) {
                    mAppExecutors.mainThread().execute(new Runnable() {
                        @Override
                        public void run() {
                            loadCityDesCallBack.onNoDataAvailable("数据库中没有城市");
                        }
                    });
                } else if (cityDesList.size() == 1) {
                    mAppExecutors.mainThread().execute(new Runnable() {
                        @Override
                        public void run() {
                            loadCityDesCallBack.onCityDesLoaded(cityDesList.get(0));
                        }
                    });
                } else {
                    int maxT = 0;
                    for (int i = 0; i < cityDesList.size() - 1; i++) {
                        if (cityDesList.get(i).getTimestamp() > cityDesList.get(i + 1).getTimestamp()) {
                            maxT = i;
                        } else {
                            maxT = i + 1;
                        }
                    }
                    final CityDes cityDes = cityDesList.get(maxT);
                    mAppExecutors.mainThread().execute(new Runnable() {
                        @Override
                        public void run() {
                            loadCityDesCallBack.onCityDesLoaded(cityDes);
                        }
                    });
                }
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }
}
