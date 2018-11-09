package com.shichen.wooweather.weather;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.view.View;

import com.shichen.wooweather.SingleLiveEvent;
import com.shichen.wooweather.SnackbarMessage;
import com.shichen.wooweather.data.CityDes;
import com.shichen.wooweather.data.source.CityDesRepository;
import com.shichen.wooweather.data.source.CityDesSource;
import com.shichen.wooweather.utils.LocationHelper;

/**
 * @author shichen 754314442@qq.com
 * Created by Administrator on 2018/11/8.
 */
public class WooWeatherViewModel extends AndroidViewModel {
    private final Context mContext;
    private final LocationHelper mLocationHelper;
    private final CityDesRepository mCityDesRepository;
    private final SnackbarMessage mSnackbarText = new SnackbarMessage();
    private final SingleLiveEvent<Void> mNewTaskEvent = new SingleLiveEvent<>();
    public final ObservableField<CityDes> mCityDes = new ObservableField<>();
    public final ObservableField<String> query = new ObservableField<>();

    public WooWeatherViewModel(@NonNull Application application, CityDesRepository mCityDesRepository) {
        super(application);
        this.mContext = application.getApplicationContext();
        this.mCityDesRepository = mCityDesRepository;
        this.mLocationHelper = LocationHelper.getInstance((Application) mContext);
    }

    public void start() {
        mLocationHelper.setLocationCallback(new LocationHelper.LocationCallback() {
            @Override
            public void onSuccess(CityDes cityDes) {
                mCityDes.set(cityDes);
                mCityDesRepository.saveCityDes(cityDes);
                mLocationHelper.stop();
            }

            @Override
            public void onFailed() {
                mLocationHelper.stop();
                readCityFromLocal();
            }
        });
        mLocationHelper.start();
    }

    public void readCityFromLocal(){
        mCityDesRepository.getRecentCityDes(new CityDesSource.LoadCityDesCallBack() {
            @Override
            public void onCityDesLoaded(CityDes cityDes) {
                mCityDes.set(cityDes);
                mCityDesRepository.saveCityDes(cityDes);
            }

            @Override
            public void onNoDataAvailable(String msg) {
                mSnackbarText.setValue(msg);
            }
        });
    }

    public void searchCity() {
        mCityDesRepository.getCityDes(query.get(), new CityDesSource.LoadCityDesCallBack() {
            @Override
            public void onCityDesLoaded(CityDes cityDes) {
                mCityDes.set(cityDes);
            }


            @Override
            public void onNoDataAvailable(String msg) {
                mSnackbarText.setValue(msg);
            }
        });
    }

    SnackbarMessage getSnackbarMessage() {
        return mSnackbarText;
    }
}
