package com.shichen.wooweather.weather;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.shichen.wooweather.data.DailyBean;
import com.shichen.wooweather.databinding.ItemDailyBinding;

import java.util.List;

/**
 * @author shichen 754314442@qq.com
 * Created by Administrator on 2018/11/12.
 */
public class DailyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<DailyBean.DataBean> dataBeanList;

    public DailyAdapter(List<DailyBean.DataBean> dataBeanList) {
        this.dataBeanList = dataBeanList;
        setList(dataBeanList);
    }

    private void setList(List<DailyBean.DataBean> dataBeanList) {
        this.dataBeanList = dataBeanList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemDailyBinding binding = ItemDailyBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        return new DailyDesView(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof DailyDesView) {
            DailyDesView mHolder = (DailyDesView) viewHolder;
            mHolder.mBinding.setDaily(dataBeanList.get(i));
        }
    }

    @Override
    public int getItemCount() {
        return dataBeanList != null ? dataBeanList.size() : 0;
    }

    public void replaceData(List<DailyBean.DataBean> dataBeanList) {
        setList(dataBeanList);
    }

    private class DailyDesView extends RecyclerView.ViewHolder {
        ItemDailyBinding mBinding;

        public DailyDesView(ItemDailyBinding mBinding) {
            super(mBinding.getRoot());
            this.mBinding = mBinding;
        }

        public ItemDailyBinding getmBinding() {
            return mBinding;
        }

        public void setmBinding(ItemDailyBinding mBinding) {
            this.mBinding = mBinding;
        }
    }
}
