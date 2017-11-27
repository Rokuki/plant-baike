package com.treecute.plant.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableInt;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.treecute.plant.PlantApplication;
import com.treecute.plant.data.PlantFactory;
import com.treecute.plant.data.PlantResponse;
import com.treecute.plant.data.PlantService;
import com.treecute.plant.model.Plant;
import com.treecute.plant.view.CategoryDetailActivity;
import com.treecute.plant.view.PlantDetailActivity;
import com.treecute.plant.view.adapter.PlantListAdapter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by mkind on 2017/11/26 0026.
 */

public class ItemPlantViewModel extends BaseObservable{
    private Context context;
    private Plant plant;

    public ItemPlantViewModel(Context context, Plant plant) {
        this.context = context;
        this.plant = plant;
    }

    public void onPlantItemClick(View view){
        Intent intent = new Intent(view.getContext(), PlantDetailActivity.class);
        intent.putExtra("plant",plant);
        view.getContext().startActivity(intent);
    }

    public ItemPlantViewModel(Context context) {
        this.context = context;
    }
    public String getPicurl(){
        return plant.picurl;
    }
    public String getName(){
        return plant.name;
    }
    public String getSummary(){
        String summary = plant.summary;
        try {
            summary = plant.summary.substring(0,45);
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
        return summary + "...";
    }
    @BindingAdapter("plantPic")public static void setPlantPic(ImageView imageView, String url){
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }
    public void setPlant(Plant plant){
        this.plant = plant;
        notifyChange();
    }
}
