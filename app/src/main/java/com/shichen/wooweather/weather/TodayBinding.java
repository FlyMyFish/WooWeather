package com.shichen.wooweather.weather;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.shichen.wooweather.data.CurrentlyBean;

import java.util.List;

/**
 * @author shichen 754314442@qq.com
 * Created by Administrator on 2018/11/14.
 */
public class TodayBinding {
    @SuppressWarnings("unchecked")
    @BindingAdapter("today")
    public static void setDaily(RecyclerView recyclerView, List<CurrentlyBean.DesAndValue> dataBeanList) {
        TodayAdapter adapter = (TodayAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.replaceData(dataBeanList);
        }
    }
}
