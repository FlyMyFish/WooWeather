package com.shichen.wooweather.weather;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.shichen.wooweather.data.CurrentlyBean;
import com.shichen.wooweather.databinding.ItemCurrentlyBinding;

import java.util.List;

/**
 * @author shichen 754314442@qq.com
 * Created by Administrator on 2018/11/12.
 */
public class CurrentlyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<CurrentlyBean.DesAndValue> desAndValueList;

    public CurrentlyAdapter(List<CurrentlyBean.DesAndValue> desAndValueList) {
        this.desAndValueList = desAndValueList;
        setList(desAndValueList);
    }

    private void setList(List<CurrentlyBean.DesAndValue> desAndValueList) {
        this.desAndValueList = desAndValueList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemCurrentlyBinding binding = ItemCurrentlyBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        return new CurrentlyDesView(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof CurrentlyDesView) {
            CurrentlyDesView mHolder = (CurrentlyDesView) viewHolder;
            mHolder.mBinding.setDesAndValue(desAndValueList.get(i));
        }
    }

    @Override
    public int getItemCount() {
        return desAndValueList != null ? desAndValueList.size() : 0;
    }

    public void replaceData(List<CurrentlyBean.DesAndValue> desAndValueList) {
        setList(desAndValueList);
    }

    private class CurrentlyDesView extends RecyclerView.ViewHolder {
        ItemCurrentlyBinding mBinding;

        public CurrentlyDesView(ItemCurrentlyBinding mBinding) {
            super(mBinding.getRoot());
            this.mBinding = mBinding;
        }

        public ItemCurrentlyBinding getmBinding() {
            return mBinding;
        }

        public void setmBinding(ItemCurrentlyBinding mBinding) {
            this.mBinding = mBinding;
        }
    }
}
