package com.shichen.wooweather.weather;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.shichen.wooweather.data.HourlyBean;

import java.util.List;

/**
 * @author shichen 754314442@qq.com
 * Created by Administrator on 2018/11/13.
 */
public class HourlyBinding {
    @SuppressWarnings("unchecked")
    @BindingAdapter("app:hourly")
    public static void setDaily(RecyclerView recyclerView, List<HourlyBean.DataBean> dataBeanList) {
        HourlyAdapter adapter = (HourlyAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.replaceData(dataBeanList);
        }
    }
}
