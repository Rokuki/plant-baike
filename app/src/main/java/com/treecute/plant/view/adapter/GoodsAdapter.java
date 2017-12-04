package com.treecute.plant.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.treecute.plant.R;
import com.treecute.plant.databinding.ItemGoodsBinding;
import com.treecute.plant.model.Goods;
import com.treecute.plant.model.Plant;
import com.treecute.plant.model.User;
import com.treecute.plant.viewmodel.ItemGoodsViewModel;

import java.util.Collections;
import java.util.List;

/**
 * Created by mkind on 2017/12/2 0002.
 */

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.GoodsAdapterViewHolder> {
    private List<Goods> goodsList;

    public GoodsAdapter() {
        this.goodsList = Collections.emptyList();
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
        notifyDataSetChanged();
    }

    @Override
    public GoodsAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemGoodsBinding itemGoodsBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_goods, parent, false);
        return new GoodsAdapterViewHolder(itemGoodsBinding);
    }

    @Override
    public void onBindViewHolder(GoodsAdapterViewHolder holder, int position) {
        holder.bindGoods(goodsList.get(position), goodsList.get(position).getPlant(), goodsList.get(position).getUser());
    }

    @Override
    public int getItemCount() {
        return goodsList == null ? 0 : goodsList.size();
    }

    class GoodsAdapterViewHolder extends RecyclerView.ViewHolder {
        ItemGoodsBinding itemGoodsBinding;

        GoodsAdapterViewHolder(ItemGoodsBinding itemView) {
            super(itemView.buy);
            this.itemGoodsBinding = itemView;
        }

        void bindGoods(Goods goods, Plant plant, User user) {
            if (itemGoodsBinding.getGoods() == null) {
                itemGoodsBinding.setGoods(new ItemGoodsViewModel(itemView.getContext(), goods, plant, user));
            } else {
                itemGoodsBinding.getGoods().setModel(goods, plant, user);
            }
        }
    }
}
