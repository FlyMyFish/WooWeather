package com.shichen.wooweather.weather;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.View;

import com.shichen.wooweather.SingleLiveEvent;
import com.shichen.wooweather.SnackbarMessage;
import com.shichen.wooweather.data.CityDes;
import com.shichen.wooweather.data.CurrentlyBean;
import com.shichen.wooweather.data.DailyBean;
import com.shichen.wooweather.data.ForecastWeather;
import com.shichen.wooweather.data.HourlyBean;
import com.shichen.wooweather.data.source.CityDesRepository;
import com.shichen.wooweather.data.source.CityDesSource;
import com.shichen.wooweather.data.source.ForecastWeatherRepository;
import com.shichen.wooweather.data.source.ForecastWeatherSource;
import com.shichen.wooweather.utils.GsonUtils;
import com.shichen.wooweather.utils.LocationHelper;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author shichen 754314442@qq.com
 * Created by Administrator on 2018/11/8.
 */
public class WooWeatherViewModel extends AndroidViewModel {
    private final Context mContext;
    private final LocationHelper mLocationHelper;
    private final CityDesRepository mCityDesRepository;
    private final ForecastWeatherRepository mForecastWeatherRepository;
    private final SnackbarMessage mSnackbarText = new SnackbarMessage();
    private final SingleLiveEvent<Void> mNewTaskEvent = new SingleLiveEvent<>();
    public final ObservableField<CityDes> mCityDes = new ObservableField<>();
    public final ObservableField<ForecastWeather> mForecastWeather = new ObservableField<>();
    public final ObservableField<CurrentlyBean> mCurrentlyBean = new ObservableField<>();
    public final ObservableList<DailyBean.DataBean> mDailyBean = new ObservableArrayList<>();
    public final ObservableList<HourlyBean.DataBean> mHourlyList=new ObservableArrayList<>();
    public final ObservableList<CurrentlyBean.DesAndValue> mDesAndValueList=new ObservableArrayList<>();
    public final ObservableField<Drawable> mWeatherIcon = new ObservableField<>();
    public final ObservableField<String> query = new ObservableField<>();
    public final ObservableBoolean mDailyShow=new ObservableBoolean();
    public final ObservableBoolean mHourlyShow=new ObservableBoolean();
    public final ObservableBoolean mTodayShow=new ObservableBoolean();
    public final ObservableBoolean mLoading=new ObservableBoolean();
    public final MutableLiveData<Boolean> refreshing =new MutableLiveData<>();

    public WooWeatherViewModel(@NonNull Application application, CityDesRepository mCityDesRepository, ForecastWeatherRepository mForecastWeatherRepository) {
        super(application);
        this.mContext = application.getApplicationContext();
        this.mCityDesRepository = mCityDesRepository;
        this.mForecastWeatherRepository = mForecastWeatherRepository;
        this.mLocationHelper = LocationHelper.getInstance((Application) mContext);
        mDailyShow.set(false);
        mHourlyShow.set(false);
        mTodayShow.set(false);
        mLoading.set(false);
        refreshing.setValue(false);
    }

    public void start() {
        mLocationHelper.setLocationCallback(new LocationHelper.LocationCallback() {
            @Override
            public void onSuccess(CityDes cityDes) {
                readCitySuccess(cityDes);
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

    private void readCityFromLocal() {
        mCityDesRepository.getRecentCityDes(new CityDesSource.LoadCityDesCallBack() {
            @Override
            public void onCityDesLoaded(CityDes cityDes) {
                readCitySuccess(cityDes);
            }

            @Override
            public void onNoDataAvailable(String msg) {
                mSnackbarText.setValue(msg);
            }
        });
    }

    private void readCitySuccess(CityDes cityDes) {
        mCityDes.set(cityDes);
        mCityDesRepository.saveCityDes(cityDes);
        mLoading.set(true);
        refreshing.setValue(true);
    }

    public void getWeatherData() {
        CityDes curCityDes = mCityDes.get();
        checkNotNull(curCityDes);
        mForecastWeatherRepository.loadForecastWeather(curCityDes.getQueryStr(), curCityDes.getLatitude(), curCityDes.getLongitude(), new ForecastWeatherSource.LoadWeatherCallBack() {
            @Override
            public void onLoaded(ForecastWeather forecastWeather) {
                mForecastWeather.set(forecastWeather);
                CurrentlyBean currentlyBean = GsonUtils.getInstance().get().fromJson(forecastWeather.getCurrently(), CurrentlyBean.class);
                DailyBean dailyBean = GsonUtils.getInstance().get().fromJson(forecastWeather.getDaily(), DailyBean.class);
                HourlyBean hourlyBean=GsonUtils.getInstance().get().fromJson(forecastWeather.getHourly(),HourlyBean.class);
                mCurrentlyBean.set(currentlyBean);
                mDesAndValueList.clear();
                mDesAndValueList.addAll(currentlyBean.desAndValueList());
                if (mCurrentlyBean.get()!=null){
                    mTodayShow.set(true);
                }else {
                    mTodayShow.set(false);
                }
                mWeatherIcon.set(mContext.getResources().getDrawable(currentlyBean.getIconResId()));
                mDailyBean.clear();
                mDailyBean.addAll(dailyBean.getData());
                if (mDailyBean.size()>0){
                    mDailyShow.set(true);
                }else {
                    mDailyShow.set(false);
                }
                mHourlyList.clear();
                mHourlyList.addAll(hourlyBean.getData());
                if (mHourlyList.size()>0){
                    mHourlyShow.set(true);
                }else {
                    mHourlyShow.set(false);
                }
                mLoading.set(false);
                refreshing.postValue(false);
            }

            @Override
            public void onDataNotAvailable(String msg) {
                mSnackbarText.setValue(msg);
                mLoading.set(false);
                refreshing.postValue(false);
            }
        });
    }

    SnackbarMessage getSnackbarMessage() {
        return mSnackbarText;
    }
}
