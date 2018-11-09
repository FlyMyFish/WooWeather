package com.shichen.wooweather.weather;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.shichen.wooweather.R;
import com.shichen.wooweather.SnackbarMessage;
import com.shichen.wooweather.ViewModelFactory;
import com.shichen.wooweather.databinding.ActivityWooWeatherBinding;
import com.shichen.wooweather.utils.SnackbarUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shichen 754314442@qq.com
 * Created by Administrator on 2018/11/8.
 */
public class WooWeatherActivity extends AppCompatActivity {
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityWooWeatherBinding = DataBindingUtil.setContentView(this, R.layout.activity_woo_weather);
        mWooWeatherViewModel = obtainViewModel(this);
        mActivityWooWeatherBinding.setViewmodel(mWooWeatherViewModel);
        setupSnackbar();
    }

    public static WooWeatherViewModel obtainViewModel(AppCompatActivity activity) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());

        WooWeatherViewModel viewModel =
                ViewModelProviders.of(activity, factory).get(WooWeatherViewModel.class);

        return viewModel;
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
}
