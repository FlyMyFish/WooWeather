package com.shichen.wooweather.data.source;

import android.support.annotation.NonNull;

import com.shichen.wooweather.data.CityDes;
import com.shichen.wooweather.utils.EspressoIdlingResource;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author shichen 754314442@qq.com
 * Created by Administrator on 2018/11/7.
 */
public class CityDesRepository implements CityDesSource {

    private volatile static CityDesRepository INSTANCE = null;
    private final CityDesSource mCityDesLocalSource;
    private final CityDesSource mCityDesRemoteSource;

    Map<String, CityDes> mCachedCityDess;

    private boolean mCacheIsDirty = false;

    private CityDesRepository(@NonNull CityDesSource mCityDesLocalSource, @NonNull CityDesSource mCityDesRemoteSource) {
        this.mCityDesLocalSource = mCityDesLocalSource;
        this.mCityDesRemoteSource = mCityDesRemoteSource;
    }

    public static CityDesRepository getInstance(@NonNull CityDesSource mCityDesLocalSource, @NonNull CityDesSource mCityDesRemoteSource) {
        if (INSTANCE == null) {
            synchronized (CityDesRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CityDesRepository(mCityDesLocalSource, mCityDesRemoteSource);
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getCityDes(@NonNull final String query, @NonNull final LoadCityDesCallBack loadCityDesCallBack) {
        checkNotNull(query);
        checkNotNull(loadCityDesCallBack);

        if (mCachedCityDess != null && !mCacheIsDirty) {
            if (mCachedCityDess.containsKey(query)) {
                loadCityDesCallBack.onCityDesLoaded(mCachedCityDess.get(query));
            } else {
                EspressoIdlingResource.increment();
                getCityDesFromRemoteDataSource(query, loadCityDesCallBack);
            }
            return;
        }

        EspressoIdlingResource.increment();

        if (mCacheIsDirty) {
            // If the cache is dirty we need to fetch new data from the network.
            getCityDesFromRemoteDataSource(query, loadCityDesCallBack);
        } else {
            // Query the local storage if available. If not, query the network.
            mCityDesLocalSource.getCityDes(query, new LoadCityDesCallBack() {
                @Override
                public void onCityDesLoaded(CityDes cityDes) {
                    refreshCache(cityDes);

                    EspressoIdlingResource.decrement(); // Set app as idle.
                    loadCityDesCallBack.onCityDesLoaded(mCachedCityDess.get(query));
                }

                @Override
                public void onNoDataAvailable(String msg) {
                    getCityDesFromRemoteDataSource(query, loadCityDesCallBack);

                }
            });
        }
    }

    private void getCityDesFromRemoteDataSource(@NonNull final String query, @NonNull final LoadCityDesCallBack callback) {
        mCityDesRemoteSource.getCityDes(query, new LoadCityDesCallBack() {
            @Override
            public void onCityDesLoaded(CityDes cityDes) {
                refreshCache(cityDes);
                refreshLocalDataSource(cityDes);
                EspressoIdlingResource.decrement(); // Set app as idle.
                callback.onCityDesLoaded(cityDes);
            }

            @Override
            public void onNoDataAvailable(String msg) {
                EspressoIdlingResource.decrement(); // Set app as idle.
                callback.onNoDataAvailable(msg);
            }
        });
    }

    private void refreshCache(CityDes cityDes) {
        if (mCachedCityDess == null) {
            mCachedCityDess = new LinkedHashMap<>();
        }
        mCachedCityDess.clear();
        mCachedCityDess.put(cityDes.getQueryStr(), cityDes);
        mCacheIsDirty = false;
    }

    private void refreshLocalDataSource(CityDes cityDes) {
        mCityDesLocalSource.deleteCityDes(cityDes.getQueryStr());
        mCityDesLocalSource.saveCityDes(cityDes);
    }

    @Override
    public void saveCityDes(CityDes cityDes) {
        checkNotNull(cityDes);
        mCityDesRemoteSource.saveCityDes(cityDes);
        mCityDesLocalSource.saveCityDes(cityDes);

        // Do in memory cache update to keep the app UI up to date
        if (mCachedCityDess == null) {
            mCachedCityDess = new LinkedHashMap<>();
        }
        mCachedCityDess.put(cityDes.getQueryStr(), cityDes);
    }

    @Override
    public void updateCityDes(CityDes cityDes) {
        checkNotNull(cityDes);
        mCityDesRemoteSource.updateCityDes(cityDes);
        mCityDesLocalSource.updateCityDes(cityDes);

        if (mCachedCityDess == null) {
            mCachedCityDess = new LinkedHashMap<>();
        }
        mCachedCityDess.put(cityDes.getQueryStr(), cityDes);
    }

    @Override
    public void deleteCityDes(String query) {
        mCityDesRemoteSource.deleteCityDes(checkNotNull(query));
        mCityDesLocalSource.deleteCityDes(checkNotNull(query));

        mCachedCityDess.remove(query);
    }

    @Override
    public void refreshCityDes() {
        mCacheIsDirty = true;
    }

    @Override
    public void getRecentCityDes(@NonNull final LoadCityDesCallBack loadCityDesCallBack) {
        mCityDesLocalSource.getRecentCityDes(new LoadCityDesCallBack() {
            @Override
            public void onCityDesLoaded(CityDes cityDes) {
                loadCityDesCallBack.onCityDesLoaded(cityDes);
            }

            @Override
            public void onNoDataAvailable(String msg) {
                loadCityDesCallBack.onNoDataAvailable(msg);
            }
        });
    }
}
