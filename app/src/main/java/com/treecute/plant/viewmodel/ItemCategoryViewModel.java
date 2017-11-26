package com.treecute.plant.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.treecute.plant.model.PlantCategory;
import com.treecute.plant.view.CategoryDetailActivity;

/**
 * Created by mkind on 2017/11/26 0026.
 */

public class ItemCategoryViewModel extends BaseObservable {
    private Context context;
    private PlantCategory plantCategory;


    public ItemCategoryViewModel(Context context) {
        this.context = context;
    }

    public ItemCategoryViewModel(PlantCategory plantCategory,Context context) {
        this.context = context;
        this.plantCategory = plantCategory;
    }
    
    public void onItemClick(View view){
        //打开分类详情页
        Intent intent = new Intent(view.getContext(), CategoryDetailActivity.class);
        intent.putExtra("category",plantCategory);
        view.getContext().startActivity(intent);
    }

    public String getCategory(){
        return plantCategory.category;
    }

    public String getCategoryPicS(){
        return plantCategory.categoryPicS;
    }

    public String getCategoryPic(){
        return plantCategory.categoryPic;
    }

    @BindingAdapter("categoryPic") public static void setCategoryPic(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    @BindingAdapter("categoryPicS") public static void setCategoryPicS(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    public void setPlantCategory(PlantCategory plantCategory){
        this.plantCategory = plantCategory;
        notifyChange();
    }
}
