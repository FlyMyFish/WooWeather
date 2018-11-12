package com.shichen.wooweather.weather;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.shichen.wooweather.data.CurrentlyBean;

import java.util.List;

/**
 * @author shichen 754314442@qq.com
 * Created by Administrator on 2018/11/12.
 */
public class CurrentlyBinding {
    @SuppressWarnings("unchecked")
    @BindingAdapter("app:currently")
    public static void setCurrently(RecyclerView recyclerView, List<CurrentlyBean.DesAndValue> desAndValueList) {
        CurrentlyAdapter adapter = (CurrentlyAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.replaceData(desAndValueList);
        }
    }
}
