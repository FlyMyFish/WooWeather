package com.shichen.wooweather.data.source;

import android.support.annotation.NonNull;

import com.shichen.wooweather.data.ForecastWeather;
import com.shichen.wooweather.utils.EspressoIdlingResource;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author shichen 754314442@qq.com
 * Created by Administrator on 2018/11/7.
 */
public class ForecastWeatherRepository implements ForecastWeatherSource {
    private volatile static ForecastWeatherRepository INSTANCE = null;
    private final ForecastWeatherSource mForecastWeatherLocalSource;
    private final ForecastWeatherSource mForecastWeatherRemoteSource;
    Map<String, ForecastWeather> mCachedForecastWeather;

    private boolean mCacheIsDirty = false;

    private ForecastWeatherRepository(@NonNull ForecastWeatherSource forecastWeatherLocalSource,
                                      @NonNull ForecastWeatherSource forecastWeatherRemoteSource) {
        this.mForecastWeatherLocalSource = forecastWeatherLocalSource;
        this.mForecastWeatherRemoteSource = forecastWeatherRemoteSource;
    }

    public static ForecastWeatherRepository getInstance(@NonNull ForecastWeatherSource forecastWeatherLocalSource,
                                                        @NonNull ForecastWeatherSource forecastWeatherRemoteSource) {
        if (INSTANCE == null) {
            synchronized (ForecastWeatherRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ForecastWeatherRepository(forecastWeatherLocalSource, forecastWeatherRemoteSource);
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void loadForecastWeather(@NonNull String query, LoadWeatherCallBack callBack) {

    }

    private void getForecastWeatherFromRemoteDataSource(@NonNull final String query, String lat, String lon, @NonNull final LoadWeatherCallBack callback) {
        mForecastWeatherRemoteSource.loadForecastWeather(query, lat, lon, new LoadWeatherCallBack() {
            @Override
            public void onLoaded(ForecastWeather forecastWeather) {
                refreshCache(forecastWeather);
                refreshLocalDataSource(forecastWeather);
                EspressoIdlingResource.decrement(); // Set app as idle.
                callback.onLoaded(forecastWeather);
            }

            @Override
            public void onDataNotAvailable(String msg) {
                EspressoIdlingResource.decrement(); // Set app as idle.
                callback.onDataNotAvailable(msg);
            }
        });
    }

    private void refreshCache(ForecastWeather forecastWeather) {
        if (mCachedForecastWeather == null) {
            mCachedForecastWeather = new LinkedHashMap<>();
        }
        mCachedForecastWeather.clear();
        mCachedForecastWeather.put(forecastWeather.getQueryStr(), forecastWeather);
        mCacheIsDirty = false;
    }

    private void refreshLocalDataSource(ForecastWeather forecastWeather) {
        mForecastWeatherLocalSource.deleteForecastWeather(forecastWeather.getQueryStr());
        mForecastWeatherLocalSource.saveForecastWeather(forecastWeather);
    }

    @Override
    public void loadForecastWeather(@NonNull final String query, final String lat, final String lon, final LoadWeatherCallBack callBack) {
        checkNotNull(query);
        checkNotNull(callBack);
        if (mCachedForecastWeather != null && !mCacheIsDirty) {
            if (mCachedForecastWeather.containsKey(query)) {
                long currentTamp = (System.currentTimeMillis() / 1000);
                ForecastWeather cacheForecastWeather = mCachedForecastWeather.get(query);
                if (currentTamp - cacheForecastWeather.getTimestemp() > 60 * 60 * 2) {
                    EspressoIdlingResource.increment();
                    getForecastWeatherFromRemoteDataSource(query, lat, lon, callBack);
                } else {
                    callBack.onLoaded(mCachedForecastWeather.get(query));
                }
            } else {
                EspressoIdlingResource.increment();
                getForecastWeatherFromRemoteDataSource(query, lat, lon, callBack);
            }
            return;
        }

        EspressoIdlingResource.increment();

        if (mCacheIsDirty) {
            // If the cache is dirty we need to fetch new data from the network.
            getForecastWeatherFromRemoteDataSource(query, lat, lon, callBack);
        } else {
            // Query the local storage if available. If not, query the network.
            mForecastWeatherLocalSource.loadForecastWeather(query, new LoadWeatherCallBack() {
                @Override
                public void onLoaded(ForecastWeather forecastWeather) {
                    long currentTamp = (System.currentTimeMillis() / 1000);
                    if (currentTamp - forecastWeather.getTimestemp() > 60 * 60 * 2) {
                        EspressoIdlingResource.increment();
                        getForecastWeatherFromRemoteDataSource(query, lat, lon, callBack);
                    } else {
                        refreshCache(forecastWeather);
                        EspressoIdlingResource.decrement(); // Set app as idle.
                        callBack.onLoaded(mCachedForecastWeather.get(query));
                    }
                }

                @Override
                public void onDataNotAvailable(String msg) {
                    getForecastWeatherFromRemoteDataSource(query, lat, lon, callBack);
                }
            });
        }
    }

    @Override
    public void saveForecastWeather(ForecastWeather forecastWeather) {
        checkNotNull(forecastWeather);
        mForecastWeatherLocalSource.saveForecastWeather(forecastWeather);
        mForecastWeatherRemoteSource.saveForecastWeather(forecastWeather);

        // Do in memory cache update to keep the app UI up to date
        if (mCachedForecastWeather == null) {
            mCachedForecastWeather = new LinkedHashMap<>();
        }
        mCachedForecastWeather.put(forecastWeather.getQueryStr(), forecastWeather);
    }

    @Override
    public void updateForecastWeather(ForecastWeather forecastWeather) {
        checkNotNull(forecastWeather);
        mForecastWeatherLocalSource.updateForecastWeather(forecastWeather);
        mForecastWeatherRemoteSource.updateForecastWeather(forecastWeather);

        if (mCachedForecastWeather == null) {
            mCachedForecastWeather = new LinkedHashMap<>();
        }
        mCachedForecastWeather.put(forecastWeather.getQueryStr(), forecastWeather);
    }

    @Override
    public void deleteForecastWeather(String query) {
        mForecastWeatherRemoteSource.deleteForecastWeather(checkNotNull(query));
        mForecastWeatherLocalSource.deleteForecastWeather(checkNotNull(query));

        mCachedForecastWeather.remove(query);
    }
}
