package com.treecute.plant.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.treecute.plant.PlantApplication;
import com.treecute.plant.R;
import com.treecute.plant.data.GoodsFactory;
import com.treecute.plant.data.PlantFactory;
import com.treecute.plant.data.PlantService;
import com.treecute.plant.databinding.ActivityPlantDetailBinding;
import com.treecute.plant.model.Plant;
import com.treecute.plant.model.Response;
import com.treecute.plant.model.ResponseResult;
import com.treecute.plant.model.User;
import com.treecute.plant.util.NoStatusBar;
import com.treecute.plant.util.SharedPreferencesHelper;
import com.treecute.plant.util.TAG;
import com.treecute.plant.viewmodel.PlantDetailViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by mkind on 2017/11/25 0025.
 */

public class PlantDetailActivity extends AppCompatActivity {
    private Plant plant;
    private ActivityPlantDetailBinding plantDetailBinding;
    private PlantDetailViewModel plantDetailViewModel;
    private boolean collected;
    private boolean collected_before;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private PlantApplication plantApplication;
    private PlantService plantService;

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
    protected void onDestroy() {
        super.onDestroy();
        if (collected == collected_before) {
            //未改动是否收藏
        } else {
            if (collected) {
                addToCollection();
            } else {
                removeCollection();
            }
        }
    }

    private void removeCollection() {
        User user = SharedPreferencesHelper.getUser(this);
        Disposable disposable = plantService.removeCollection(PlantFactory.REMOVE_COLLECTION, user.getAccessToken(), user.getUsername(), user.getId(), plant.id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(plantApplication.subscribeScheduler())
                .subscribe(new Consumer<ResponseResult<Response>>() {
                    @Override
                    public void accept(ResponseResult<Response> responseResponseResult) throws Exception {
                        Log.d(com.treecute.plant.util.TAG.TAG, "accept: " + responseResponseResult.getData().getStatus());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(PlantDetailActivity.this, throwable.toString(), Toast.LENGTH_LONG).show();
                    }
                });
        compositeDisposable.add(disposable);
    }

    private void addToCollection() {
        User user = SharedPreferencesHelper.getUser(this);
        Disposable disposable = plantService.addToCollection(PlantFactory.ADD_TO_COLLECTION, user.getAccessToken(), user.getUsername(), user.getId(), plant.id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(plantApplication.subscribeScheduler())
                .subscribe(new Consumer<ResponseResult<Response>>() {
                    @Override
                    public void accept(ResponseResult<Response> responseResponseResult) throws Exception {
                        Log.d(TAG.TAG, "accept: " + responseResponseResult.getData().getStatus());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(PlantDetailActivity.this, throwable.toString(), Toast.LENGTH_LONG).show();
                    }
                });
        compositeDisposable.add(disposable);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getIfCollected(menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void getIfCollected(final Menu menu) {
        User user = SharedPreferencesHelper.getUser(this);
        plantApplication = PlantApplication.create(this);
        plantService = plantApplication.getPlantService();

        Disposable disposable = plantService.getIfCollected(PlantFactory.GET_IF_COLLECTED, user.getAccessToken(), user.getUsername(), user.getId(), plant.id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(plantApplication.subscribeScheduler())
                .subscribe(new Consumer<ResponseResult<Response>>() {
                    @Override
                    public void accept(ResponseResult<Response> responseResponseResult) throws Exception {
                        if (responseResponseResult.getData().getStatus() == 1) {
                            //已收藏
                            collected_before = collected = true;
                            getMenuInflater().inflate(R.menu.menu_collected, menu);
                        } else if (responseResponseResult.getData().getStatus() == 0) {
                            //未收藏
                            collected_before = collected = false;
                            getMenuInflater().inflate(R.menu.menu_collect, menu);
                        } else {
                            //错误
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
        compositeDisposable.add(disposable);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        } else {
            if (collected) {
                item.setIcon(R.mipmap.collection_0);
                collected = false;
            } else {
                item.setIcon(R.mipmap.collection_1);
                collected = true;
            }
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
