package com.shichen.wooweather.weather;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.shichen.wooweather.data.CurrentlyBean;
import com.shichen.wooweather.data.DailyBean;

import java.util.List;

/**
 * @author shichen 754314442@qq.com
 * Created by Administrator on 2018/11/12.
 */
public class DailyBinding {
    @SuppressWarnings("unchecked")
    @BindingAdapter("daily")
    public static void setDaily(RecyclerView recyclerView, List<DailyBean.DataBean> dataBeanList) {
        DailyAdapter adapter = (DailyAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.replaceData(dataBeanList);
        }
    }
}
