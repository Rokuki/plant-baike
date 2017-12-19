package com.treecute.plant.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.treecute.plant.R;
import com.treecute.plant.databinding.ItemPlantCollectionsBinding;
import com.treecute.plant.model.PlantCollections;
import com.treecute.plant.viewmodel.ItemPlantCollectionsViewModel;

import java.util.Collections;
import java.util.List;

/**
 * Created by mkind on 2017/12/19 0019.
 */

public class CollectionsAdapter extends RecyclerView.Adapter<CollectionsAdapter.CollectionsAdapterViewHolder> {

    private List<PlantCollections> collectionsList;

    public CollectionsAdapter() {
        this.collectionsList = Collections.emptyList();
    }

    public void setCollectionsList(List<PlantCollections> collectionsList) {
        this.collectionsList = collectionsList;
        notifyDataSetChanged();
    }

    @Override
    public CollectionsAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemPlantCollectionsBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_plant_collections, parent, false);
        return new CollectionsAdapterViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(CollectionsAdapterViewHolder holder, int position) {
        holder.bindPlantCollections(collectionsList.get(position));
    }

    @Override
    public int getItemCount() {
        return collectionsList == null ? 0 : collectionsList.size();
    }

    class CollectionsAdapterViewHolder extends RecyclerView.ViewHolder {
        ItemPlantCollectionsBinding binding;

        CollectionsAdapterViewHolder(ItemPlantCollectionsBinding binding) {
            super(binding.plantCollectionsItem);
            this.binding = binding;
        }

        void bindPlantCollections(PlantCollections collections) {
            if (binding.getItem() == null) {
                binding.setItem(new ItemPlantCollectionsViewModel(itemView.getContext(), collections));
            } else {
                binding.getItem().setPlantCollections(collections);
            }
        }
    }
}
