package com.treecute.plant.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.treecute.plant.R;
import com.treecute.plant.databinding.ActivityPlantDetailBinding;
import com.treecute.plant.model.Plant;
import com.treecute.plant.util.NoStatusBar;
import com.treecute.plant.viewmodel.PlantDetailViewModel;

/**
 * Created by mkind on 2017/11/25 0025.
 */

public class PlantDetailActivity extends AppCompatActivity {
    private Plant plant;
    private ActivityPlantDetailBinding plantDetailBinding;
    private PlantDetailViewModel plantDetailViewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NoStatusBar.SetNoStatusBar(this);
        initPlant();
        initDataBinding();
        setSupportActionBar(plantDetailBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initDataBinding() {
        plantDetailBinding = DataBindingUtil.setContentView(this,R.layout.activity_plant_detail);
        plantDetailViewModel = new PlantDetailViewModel(this,plant);
        plantDetailBinding.setPlantDetail(plantDetailViewModel);
    }

    private void initPlant() {
        Intent intent = getIntent();
        plant = (Plant) intent.getSerializableExtra("plant");
    }
}
