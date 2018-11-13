package com.shichen.wooweather.weather;

import android.databinding.BindingAdapter;
import android.support.v7.widget.AppCompatImageView;

/**
 * @author shichen 754314442@qq.com
 * Created by Administrator on 2018/11/13.
 */
public class VectorDrawableBinding {
    @SuppressWarnings("unchecked")
    @BindingAdapter("app:vectorResId")
    public static void setVector(AppCompatImageView imageView, int resId) {
        imageView.setBackgroundResource(resId);
    }
}
