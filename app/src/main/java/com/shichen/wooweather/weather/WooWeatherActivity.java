package com.shichen.wooweather.weather;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.shichen.wooweather.R;
import com.shichen.wooweather.SnackbarMessage;
import com.shichen.wooweather.ViewModelFactory;
import com.shichen.wooweather.data.CurrentlyBean;
import com.shichen.wooweather.data.DailyBean;
import com.shichen.wooweather.data.HourlyBean;
import com.shichen.wooweather.databinding.ActivityWooWeatherBinding;
import com.shichen.wooweather.utils.LogUtilsWoo;
import com.shichen.wooweather.utils.SnackbarUtils;
import com.shichen.wooweather.utils.StatusUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shichen 754314442@qq.com
 * Created by Administrator on 2018/11/8.
 */
public class WooWeatherActivity extends AppCompatActivity {
    private final String TAG = "WooWeatherActivity";
    /**
     * 需要进行检测的权限数组
     */
    protected String[] needPermissions = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private static final int PERMISSON_REQUESTCODE = 0;
    /**
     * 判断是否需要检测，防止不停的弹框
     */
    private boolean isNeedCheck = true;

    @Override
    protected void onStart() {
        super.onStart();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (isNeedCheck) {
                checkPermissions(needPermissions);
            }
        } else {
            mWooWeatherViewModel.start();
        }
    }

    /**
     * @param
     * @since 2.5.0
     */
    private void checkPermissions(String... permissions) {
        List<String> needRequestPermissonList = findDeniedPermissions(permissions);
        if (null != needRequestPermissonList
                && needRequestPermissonList.size() > 0) {
            ActivityCompat.requestPermissions(this,
                    needRequestPermissonList.toArray(
                            new String[needRequestPermissonList.size()]),
                    PERMISSON_REQUESTCODE);
        } else {
            mWooWeatherViewModel.start();
        }
    }

    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     * @since 2.5.0
     */
    private List<String> findDeniedPermissions(String[] permissions) {
        List<String> needRequestPermissonList = new ArrayList<String>();
        for (String perm : permissions) {
            if (ContextCompat.checkSelfPermission(this,
                    perm) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                    this, perm)) {
                needRequestPermissonList.add(perm);
            }
        }
        return needRequestPermissonList;
    }


    /**
     * 检测是否说有的权限都已经授权
     *
     * @param grantResults
     * @return
     * @since 2.5.0
     */
    private boolean verifyPermissions(int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSON_REQUESTCODE) {
            if (!verifyPermissions(grantResults)) {
                Log.e("RequestPermissions", grantResults.toString());
                //showMissingPermissionDialog();
                //没有获取到所有的权限
                isNeedCheck = false;
                finish();
            } else {
                mWooWeatherViewModel.start();
            }
        }
    }

    private WooWeatherViewModel mWooWeatherViewModel;
    private ActivityWooWeatherBinding mActivityWooWeatherBinding;
    private DailyAdapter dailyAdapter;
    private HourlyAdapter hourlyAdapter;
    private TodayAdapter todayAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityWooWeatherBinding = DataBindingUtil.setContentView(this, R.layout.activity_woo_weather);
        StatusUtil.setUseStatusBarColor(this, Color.TRANSPARENT);
        StatusUtil.setSystemStatus(this, true, false);
        mWooWeatherViewModel = obtainViewModel(this);
        mActivityWooWeatherBinding.setViewmodel(mWooWeatherViewModel);
        setupListAdapter();
        setupSnackbar();
        setUpWooWeatherView();
    }

    private void setUpWooWeatherView() {
        //View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY
        /*mActivityWooWeatherBinding.svParent.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView nestedScrollView, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                LogUtilsWoo.Log(TAG, "onScrollChange--->  scrollY = " + scrollY);
                mActivityWooWeatherBinding.wooWeatherView.scrollTo(scrollX, scrollY);
            }
        });*/
    }

    public static WooWeatherViewModel obtainViewModel(AppCompatActivity activity) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());

        WooWeatherViewModel viewModel =
                ViewModelProviders.of(activity, factory).get(WooWeatherViewModel.class);

        return viewModel;
    }

    private void setupListAdapter() {
        RecyclerView rvDaily = mActivityWooWeatherBinding.rvDaily;
        dailyAdapter = new DailyAdapter(new ArrayList<DailyBean.DataBean>());
        rvDaily.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvDaily.setAdapter(dailyAdapter);

        RecyclerView rvHourly = mActivityWooWeatherBinding.rvHourly;
        hourlyAdapter = new HourlyAdapter(new ArrayList<HourlyBean.DataBean>());
        rvHourly.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvHourly.setAdapter(hourlyAdapter);

        RecyclerView rvToday = mActivityWooWeatherBinding.rvTodayDetail;
        todayAdapter = new TodayAdapter(new ArrayList<CurrentlyBean.DesAndValue>());
        rvToday.setLayoutManager(new LinearLayoutManager(this));
        rvToday.setAdapter(todayAdapter);
    }

    private void setupSnackbar() {
        mWooWeatherViewModel.getSnackbarMessage().observe(this, new SnackbarMessage.SnackbarObserver() {
            @Override
            public void onNewMessage(@StringRes int snackbarMessageResourceId) {
                SnackbarUtils.showSnackbar(mActivityWooWeatherBinding.getRoot(), getString(snackbarMessageResourceId));
            }

            @Override
            public void onNewMessage(String msg) {
                SnackbarUtils.showSnackbar(mActivityWooWeatherBinding.getRoot(), msg);
            }
        });
    }

    //记录用户首次点击返回键的时间
    private long firstTime = 0;

    /**
     * 第二种办法
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                firstTime = secondTime;
                return true;
            } else {
                System.exit(0);
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
