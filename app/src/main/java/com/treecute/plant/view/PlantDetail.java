package com.treecute.plant.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.treecute.plant.R;
import com.treecute.plant.databinding.PlantDetailBinding;
import com.treecute.plant.model.Plant;
import com.treecute.plant.viewmodel.PlantDetailViewModel;

/**
 * Created by mkind on 2017/11/25 0025.
 */

public class PlantDetail extends AppCompatActivity {
    private Plant plant;
    private PlantDetailBinding plantDetailBinding;
    private PlantDetailViewModel plantDetailViewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPlant();
        initDataBinding();
        setSupportActionBar(plantDetailBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initDataBinding() {
        plantDetailBinding = DataBindingUtil.setContentView(this,R.layout.plant_detail);
        plantDetailViewModel = new PlantDetailViewModel(this,plant);
        plantDetailBinding.setPlantDetail(plantDetailViewModel);
    }

    private void initPlant() {
        Intent intent = getIntent();
        Gson gson = new Gson();
        String plantJson = intent.getStringExtra("plant");
        plant = gson.fromJson(plantJson,Plant.class);
    }
}
