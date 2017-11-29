package com.treecute.plant.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.treecute.plant.PlantApplication;
import com.treecute.plant.R;
import com.treecute.plant.data.PlantFactory;
import com.treecute.plant.data.PlantResponse;
import com.treecute.plant.data.PlantService;
import com.treecute.plant.databinding.ActivityCategoryDetailBinding;
import com.treecute.plant.model.PlantCategory;
import com.treecute.plant.util.NoStatusBar;
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
    ActivityCategoryDetailBinding categoryDetailBinding;
    private PlantCategory plantCategory;
    private PlantListAdapter plantListAdapter;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private String TAG = "CBC";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NoStatusBar.SetNoStatusBar(this);
        getPlantCategory();
        initDataBinding();
        initView();
        getData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
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
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(CategoryDetailActivity.this,throwable.toString(),Toast.LENGTH_LONG).show();
                    }
                });
        compositeDisposable.add(disposable);
    }

    private void initView() {
        setSupportActionBar(categoryDetailBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerView recyclerView = categoryDetailBinding.plantList;
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
        categoryDetailBinding = DataBindingUtil.setContentView(this,R.layout.activity_category_detail);
        ItemCategoryViewModel itemCategoryViewModel = new ItemCategoryViewModel(plantCategory,this);
        categoryDetailBinding.setCategory(itemCategoryViewModel);
    }
}
