package com.shichen.wooweather.weather;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shichen.wooweather.data.HourlyBean;
import com.shichen.wooweather.databinding.ItemHourlyBinding;

import java.util.List;

/**
 * @author shichen 754314442@qq.com
 * Created by Administrator on 2018/11/13.
 */
public class HourlyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<HourlyBean.DataBean> hourlyList;

    public HourlyAdapter(List<HourlyBean.DataBean> hourlyList) {
        this.hourlyList = hourlyList;
    }

    public void replaceData(List<HourlyBean.DataBean> hourlyList) {
        setList(hourlyList);
    }

    private void setList(List<HourlyBean.DataBean> hourlyList) {
        this.hourlyList = hourlyList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemHourlyBinding mBinding=ItemHourlyBinding.inflate(LayoutInflater.from(viewGroup.getContext()),viewGroup,false);
        return new HourlyView(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof HourlyView){
            HourlyView mHolder=(HourlyView) viewHolder;
            mHolder.mBinding.setHourly(hourlyList.get(i));
        }
    }

    @Override
    public int getItemCount() {
        return hourlyList != null ? hourlyList.size() : 0;
    }

    private class HourlyView extends RecyclerView.ViewHolder{
        ItemHourlyBinding mBinding;

        public HourlyView(ItemHourlyBinding mBinding) {
            super(mBinding.getRoot());
            this.mBinding = mBinding;
        }

        public ItemHourlyBinding getmBinding() {
            return mBinding;
        }

        public void setmBinding(ItemHourlyBinding mBinding) {
            this.mBinding = mBinding;
        }
    }
}
