package com.treecute.plant.view;

import android.content.Intent;
import android.database.DatabaseUtils;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.treecute.plant.PlantApplication;
import com.treecute.plant.R;
import com.treecute.plant.data.PlantFactory;
import com.treecute.plant.data.PlantResponse;
import com.treecute.plant.data.PlantService;
import com.treecute.plant.databinding.CategoryDetailBinding;
import com.treecute.plant.model.PlantCategory;
import com.treecute.plant.view.adapter.PlantListAdapter;
import com.treecute.plant.viewmodel.ItemCategoryViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by mkind on 2017/11/26 0026.
 */

public class CategoryDetailActivity extends AppCompatActivity {
    CategoryDetailBinding categoryDetailBinding;
    private PlantCategory plantCategory;
    private PlantListAdapter plantListAdapter;
    private RecyclerView recyclerView;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private String TAG = "CBC";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPlantCategory();
        initDataBinding();
        initView();
        getData();
    }

    private void getData() {
        PlantApplication plantApplication = PlantApplication.create(this);
        PlantService plantService = plantApplication.getPlantService();
        Disposable disposable = plantService.fetchPlantByCategory(PlantFactory.GET_PLANT_BY_CATEGORY,plantCategory.getCategory())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(plantApplication.subscribeScheduler())
                .subscribe(new Consumer<PlantResponse>() {
                    @Override
                    public void accept(PlantResponse plantResponse) throws Exception {
                        plantListAdapter.setPlantList(plantResponse.getPlantList());
                        Log.d(TAG, "accept: "+plantResponse.getPlantList());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG, "accept: "+throwable.toString());
                    }
                });
        compositeDisposable.add(disposable);
    }

    private void initView() {
        setSupportActionBar(categoryDetailBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = categoryDetailBinding.plantList;
        plantListAdapter = new PlantListAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(plantListAdapter);
        recyclerView.setNestedScrollingEnabled(false);
    }

    private void getPlantCategory() {
        Intent intent = getIntent();
        plantCategory = (PlantCategory)intent.getSerializableExtra("category");
    }

    private void initDataBinding() {
        categoryDetailBinding = DataBindingUtil.setContentView(this,R.layout.category_detail);
        ItemCategoryViewModel itemCategoryViewModel = new ItemCategoryViewModel(plantCategory,this);
        categoryDetailBinding.setCategory(itemCategoryViewModel);
    }
}
