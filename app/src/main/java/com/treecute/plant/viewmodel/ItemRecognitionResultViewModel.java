package com.treecute.plant.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.treecute.plant.model.Plant;
import com.treecute.plant.model.RecognitionResult;

/**
 * Created by mkind on 2017/11/28 0028.
 */

public class ItemRecognitionResultViewModel extends BaseObservable {
    private Context context;
    private Plant plant;

    public ItemRecognitionResultViewModel(Context context, Plant plant) {
        this.context = context;
        this.plant = plant;
    }

    public String getName(){
        return plant.name;
    }

    public String getPicurl(){
        return plant.picurl;
    }

    @BindingAdapter("plantPic") public static void setPic(ImageView imageView,String url){
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
        notifyChange();
    }
}
