package com.shichen.wooweather.weather;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shichen.wooweather.data.CurrentlyBean;
import com.shichen.wooweather.databinding.ItemTodayBinding;

import java.util.List;

/**
 * @author shichen 754314442@qq.com
 * Created by Administrator on 2018/11/14.
 */
public class TodayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<CurrentlyBean.DesAndValue> desAndValueList;

    public TodayAdapter(List<CurrentlyBean.DesAndValue> desAndValueList) {
        this.desAndValueList = desAndValueList;
    }

    public void replaceData(List<CurrentlyBean.DesAndValue> desAndValueList) {
        setData(desAndValueList);
    }

    private void setData(List<CurrentlyBean.DesAndValue> desAndValueList) {
        this.desAndValueList = desAndValueList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemTodayBinding itemTodayBinding=ItemTodayBinding.inflate(LayoutInflater.from(viewGroup.getContext()),viewGroup,false);
        return new TodayViewHolder(itemTodayBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof TodayViewHolder){
            TodayViewHolder mHolder=(TodayViewHolder) viewHolder;
            mHolder.mBinding.setToday(desAndValueList.get(i));
        }
    }

    @Override
    public int getItemCount() {
        return desAndValueList != null ? desAndValueList.size() : 0;
    }

    private class TodayViewHolder extends RecyclerView.ViewHolder {
        ItemTodayBinding mBinding;

        public TodayViewHolder(ItemTodayBinding mBinding) {
            super(mBinding.getRoot());
            this.mBinding = mBinding;
        }

        public ItemTodayBinding getmBinding() {
            return mBinding;
        }

        public void setmBinding(ItemTodayBinding mBinding) {
            this.mBinding = mBinding;
        }
    }
}
