package com.treecute.plant.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.treecute.plant.R;
import com.treecute.plant.databinding.ItemCategoryBinding;
import com.treecute.plant.model.PlantCategory;
import com.treecute.plant.viewmodel.ItemCategoryViewModel;
import java.util.Collections;
import java.util.List;

/**
 * Created by mkind on 2017/11/26 0026.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryAdapterViewHolder>{
    private List<PlantCategory> categories;

    public CategoryAdapter() {
        this.categories = Collections.emptyList();
    }

    @Override
    public CategoryAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemCategoryBinding itemCategoryBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.item_category,parent,false);
        return new CategoryAdapterViewHolder(itemCategoryBinding);
    }

    @Override
    public void onBindViewHolder(CategoryAdapterViewHolder holder, int position) {
        holder.bindCategory(categories.get(position));
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public void setCategories(List<PlantCategory> categories){
        this.categories = categories;
        notifyDataSetChanged();
    }


    static class CategoryAdapterViewHolder extends RecyclerView.ViewHolder{
        ItemCategoryBinding itemCategoryBinding;

        CategoryAdapterViewHolder(ItemCategoryBinding itemCategoryBinding) {
            super(itemCategoryBinding.itemCategory);
            this.itemCategoryBinding = itemCategoryBinding;
        }
        void bindCategory(PlantCategory plantCategory){
            if (itemCategoryBinding.getItem()==null){
                itemCategoryBinding.setItem(new ItemCategoryViewModel(plantCategory,itemView.getContext()));
            }else {
                itemCategoryBinding.getItem().setPlantCategory(plantCategory);
            }
        }
    }
}
