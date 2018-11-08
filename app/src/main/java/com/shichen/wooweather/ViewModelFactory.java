package com.shichen.wooweather;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.shichen.wooweather.data.source.CityDesRepository;
import com.shichen.wooweather.data.source.local.CityDesLocalSource;
import com.shichen.wooweather.data.source.local.WooWeatherDataBase;
import com.shichen.wooweather.data.source.remote.CityDesRemoteSource;
import com.shichen.wooweather.utils.AppExecutors;
import com.shichen.wooweather.weather.WooWeatherViewModel;

/**
 * @author shichen 754314442@qq.com
 * Created by Administrator on 2018/11/8.
 */
public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    @SuppressLint("StaticFieldLeak")
    private static volatile ViewModelFactory INSTANCE;
    private final Application mApplication;
    private final CityDesRepository mCityDesRepository;

    public static ViewModelFactory getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    WooWeatherDataBase dataBase = WooWeatherDataBase.getInstance(application);
                    INSTANCE = new ViewModelFactory(application,
                            CityDesRepository.getInstance(CityDesLocalSource.getInstance(new AppExecutors(), dataBase.cityDesDao()),
                                    CityDesRemoteSource.getInstance(new AppExecutors())));
                }
            }
        }
        return INSTANCE;
    }

    @VisibleForTesting
    public static void destroyInstance() {
        INSTANCE = null;
    }

    private ViewModelFactory(Application application, CityDesRepository cityDesRepository) {
        mApplication = application;
        mCityDesRepository = cityDesRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(WooWeatherViewModel.class)) {
            return (T) new WooWeatherViewModel(mApplication, mCityDesRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
