package com.treecute.plant.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.treecute.plant.R;
import com.treecute.plant.databinding.ItemPickPlantBinding;
import com.treecute.plant.model.Plant;
import com.treecute.plant.util.TAG;
import com.treecute.plant.view.listener.OnPlantItemSelectListener;
import com.treecute.plant.viewmodel.ItemPickPlantViewModel;

import java.util.Collections;
import java.util.List;

/**
 * Created by mkind on 2017/12/4 0004.
 */

public class PickPlantAdapter extends RecyclerView.Adapter<PickPlantAdapter.PickPlantViewHolder> {

    private List<Plant> plantList;
    private OnPlantItemSelectListener onPlantItemSelectListener;

    public void setOnPlantItemSelectListener(OnPlantItemSelectListener onPlantItemSelectListener) {
        this.onPlantItemSelectListener = onPlantItemSelectListener;
    }

    public PickPlantAdapter() {
        this.plantList = Collections.emptyList();
    }

    public void setPlantList(List<Plant> plantList) {
        this.plantList = plantList;
        notifyDataSetChanged();
    }

    @Override
    public PickPlantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemPickPlantBinding itemPickPlantBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_pick_plant, parent, false);
        return new PickPlantViewHolder(itemPickPlantBinding);
    }

    @Override
    public void onBindViewHolder(PickPlantViewHolder holder, int position) {
        holder.bindPlant(plantList.get(position), onPlantItemSelectListener);
    }

    @Override
    public int getItemCount() {
        return plantList == null ? 0 : plantList.size();
    }

    static class PickPlantViewHolder extends RecyclerView.ViewHolder {
        ItemPickPlantBinding itemPickPlantBinding;

        PickPlantViewHolder(ItemPickPlantBinding itemPickPlantBinding) {
            super(itemPickPlantBinding.itemPlant);
            this.itemPickPlantBinding = itemPickPlantBinding;
        }

        void bindPlant(final Plant plant, final OnPlantItemSelectListener onPlantItemSelectListener) {
            if (itemPickPlantBinding.getPlant() == null) {
                itemPickPlantBinding.setPlant(new ItemPickPlantViewModel(itemView.getContext(), plant));
            } else {
                itemPickPlantBinding.getPlant().setPlant(plant);
            }
            itemPickPlantBinding.plant.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onPlantItemSelectListener.onPlantItemSelectListener(plant);
                }
            });
        }
    }
}
