package com.treecute.plant.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.treecute.plant.R;
import com.treecute.plant.databinding.ActivityRecognitionBinding;
import com.treecute.plant.databinding.ActivityRecognitionResultBinding;
import com.treecute.plant.databinding.ItemRecognitionResultBinding;
import com.treecute.plant.model.Plant;
import com.treecute.plant.model.RecognitionResult;
import com.treecute.plant.util.TAG;
import com.treecute.plant.viewmodel.ItemRecognitionResultViewModel;
import com.treecute.plant.viewmodel.RecognitionResultViewModel;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by mkind on 2017/11/28 0028.
 */

public class RecognitionResultAdapter extends RecyclerView.Adapter<RecognitionResultAdapter.RecognitionResultViewHolder> {
    private List<Plant> plantList;
    private List<RecognitionResult> resultList;

    public RecognitionResultAdapter() {
        this.plantList = Collections.emptyList();
    }

    public void setPlantList(List<Plant> plantList,List<RecognitionResult> resultList) {
        this.plantList = plantList;
        this.resultList = resultList;
        notifyDataSetChanged();
    }

    @Override
    public RecognitionResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemRecognitionResultBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_recognition_result,parent,false);
        return new RecognitionResultViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RecognitionResultViewHolder holder, int position) {
        holder.bindItem(plantList.get(position));
    }

    @Override
    public int getItemCount() {
        return plantList.size();
    }

    static class RecognitionResultViewHolder extends RecyclerView.ViewHolder{
        ItemRecognitionResultBinding itemRecognitionResultBinding;

        RecognitionResultViewHolder(ItemRecognitionResultBinding itemRecognitionResultBinding) {
            super(itemRecognitionResultBinding.itemRecognition);
            this.itemRecognitionResultBinding = itemRecognitionResultBinding;
        }
        void bindItem(Plant plant){
            if (itemRecognitionResultBinding.getItemRecognition()==null){
                itemRecognitionResultBinding.setItemRecognition(new ItemRecognitionResultViewModel(itemView.getContext(),plant));
            }else {
                itemRecognitionResultBinding.getItemRecognition().setPlant(plant);
            }
        }

    }

}
