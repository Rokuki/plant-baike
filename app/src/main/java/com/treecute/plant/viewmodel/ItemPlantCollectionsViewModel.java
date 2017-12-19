package com.treecute.plant.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.treecute.plant.model.PlantCollections;

/**
 * Created by mkind on 2017/12/19 0019.
 */

public class ItemPlantCollectionsViewModel extends BaseObservable {
    private Context context;
    private PlantCollections plantCollections;

    public ItemPlantCollectionsViewModel(Context context, PlantCollections plantCollections) {
        this.context = context;
        this.plantCollections = plantCollections;
    }

    public void onPlantItemClick(View view) {

    }

    public String getCreateTime() {
        return plantCollections.createTime;
    }

    public String getName() {
        return plantCollections.plant.name;
    }

    public String getPic() {
        return plantCollections.plant.picurl;
    }

    public String getSummary() {
        String summary = plantCollections.plant.summary;
        try {
            summary = plantCollections.plant.summary.substring(0, 45);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return summary + "...";
    }

    @BindingAdapter("pic")
    public static void setPlantPic(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    public void setPlantCollections(PlantCollections plantCollections) {
        this.plantCollections = plantCollections;
        notifyChange();
    }
}
