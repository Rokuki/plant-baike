package com.treecute.plant.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.treecute.plant.model.Plant;
import com.treecute.plant.view.PlantDetailActivity;

/**
 * Created by mkind on 2017/12/4 0004.
 */

public class ItemPickPlantViewModel extends BaseObservable {
    private Context context;
    private Plant plant;

    public ItemPickPlantViewModel(Context context, Plant plant) {
        this.context = context;
        this.plant = plant;
    }

    public ItemPickPlantViewModel(Context context) {
        this.context = context;
    }

    public String getPicurl() {
        return plant.picurl;
    }

    public String getName() {
        return plant.name;
    }

    public String getSummary() {
        String summary = plant.summary;
        try {
            summary = plant.summary.substring(0, 45);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return summary + "...";
    }

    @BindingAdapter("plantPic")
    public static void setPlantPic(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
        notifyChange();
    }
}
