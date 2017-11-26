package com.treecute.plant.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.treecute.plant.R;
import com.treecute.plant.databinding.ItemPlantBinding;
import com.treecute.plant.model.Plant;
import com.treecute.plant.viewmodel.ItemPlantViewModel;
import java.util.Collections;
import java.util.List;

/**
 * Created by mkind on 2017/11/26 0026.
 */

public class PlantListAdapter extends RecyclerView.Adapter<PlantListAdapter.PlantListViewHolder> {

    private List<Plant> plantList;

    public PlantListAdapter() {
        this.plantList = Collections.emptyList();
    }

    public void setPlantList(List<Plant> plantList) {
        this.plantList = plantList;
        notifyDataSetChanged();
    }

    @Override
    public PlantListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemPlantBinding itemPlantBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_plant,parent,false);
        return new PlantListViewHolder(itemPlantBinding);
    }

    @Override
    public void onBindViewHolder(PlantListViewHolder holder, int position) {
        holder.bindPlant(plantList.get(position));
    }

    @Override
    public int getItemCount() {
        return plantList.size();
    }

    static class PlantListViewHolder extends RecyclerView.ViewHolder {
        ItemPlantBinding itemPlantBinding;

        PlantListViewHolder(ItemPlantBinding itemPlantBinding) {
            super(itemPlantBinding.itemPlant);
            this.itemPlantBinding = itemPlantBinding;
        }
        void bindPlant(Plant plant){
            if (itemPlantBinding.getPlant()==null){
                itemPlantBinding.setPlant(new ItemPlantViewModel(itemView.getContext(),plant));
            }else {
                itemPlantBinding.getPlant().setPlant(plant);
            }
        }
    }
}
