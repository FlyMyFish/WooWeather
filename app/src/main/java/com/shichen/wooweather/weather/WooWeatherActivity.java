package com.shichen.wooweather.weather;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import com.shichen.wooweather.R;
import com.shichen.wooweather.ViewModelFactory;
import com.shichen.wooweather.databinding.ActivityWooWeatherBinding;

/**
 * @author shichen 754314442@qq.com
 * Created by Administrator on 2018/11/8.
 */
public class WooWeatherActivity extends AppCompatActivity{
    private WooWeatherViewModel mWooWeatherViewModel;
    private ActivityWooWeatherBinding mActivityWooWeatherBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityWooWeatherBinding= DataBindingUtil.setContentView(this, R.layout.activity_woo_weather);
        mWooWeatherViewModel=obtainViewModel(this);
        mActivityWooWeatherBinding.setViewmodel(mWooWeatherViewModel);
    }

    public static WooWeatherViewModel obtainViewModel(AppCompatActivity activity) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());

        WooWeatherViewModel viewModel =
                ViewModelProviders.of(activity, factory).get(WooWeatherViewModel.class);

        return viewModel;
    }
}
