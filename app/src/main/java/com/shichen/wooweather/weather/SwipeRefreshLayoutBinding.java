package com.shichen.wooweather.weather;

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;

import com.shichen.wooweather.utils.LogUtilsWoo;

/**
 * @author shichen 754314442@qq.com
 * Created by shichen on 2020/5/10.
 */
public class SwipeRefreshLayoutBinding {
    @BindingAdapter("bind_swipeRefreshLayout_refreshing")
    public static void setSwipeRefreshLayoutRefreshing(SwipeRefreshLayout swipeRefreshLayout, Boolean newValue) {
        LogUtilsWoo.Log("SwipeRefreshLayoutBinding","setSwipeRefreshLayoutRefreshing -> newValue = " + newValue);
        if (swipeRefreshLayout.isRefreshing() != newValue) {
            swipeRefreshLayout.setRefreshing(newValue);
        }
    }

    @InverseBindingAdapter(attribute = "bind_swipeRefreshLayout_refreshing",
            event = "bind_swipeRefreshLayout_refreshingAttrChanged")
    public static Boolean isSwipeRefreshLayoutRefreshing(@NonNull SwipeRefreshLayout swipeRefreshLayout) {
        LogUtilsWoo.Log("SwipeRefreshLayoutBinding","isSwipeRefreshLayoutRefreshing");
        return swipeRefreshLayout.isRefreshing();
    }

    @BindingAdapter(value = "bind_swipeRefreshLayout_refreshingAttrChanged",
            requireAll = false)
    public static void setOnRefreshListener(
            SwipeRefreshLayout swipeRefreshLayout,
            final InverseBindingListener bindingListener) {
        LogUtilsWoo.Log("SwipeRefreshLayoutBinding","setOnRefreshListener");
        if (bindingListener != null)
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    bindingListener.onChange();
                }
            });
    }
}
