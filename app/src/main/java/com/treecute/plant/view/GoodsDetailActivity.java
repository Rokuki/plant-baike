package com.treecute.plant.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.treecute.plant.PlantApplication;
import com.treecute.plant.R;
import com.treecute.plant.data.GoodsFactory;
import com.treecute.plant.data.GoodsService;
import com.treecute.plant.databinding.ActivityGoodsDetailBinding;
import com.treecute.plant.model.Goods;
import com.treecute.plant.model.Plant;
import com.treecute.plant.model.Response;
import com.treecute.plant.model.ResponseResult;
import com.treecute.plant.model.User;
import com.treecute.plant.util.NoStatusBar;
import com.treecute.plant.util.SharedPreferencesHelper;
import com.treecute.plant.util.TAG;
import com.treecute.plant.viewmodel.ItemGoodsViewModel;
import com.treecute.plant.viewmodel.PlantDetailViewModel;

import java.util.Arrays;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import retrofit2.http.Url;

public class GoodsDetailActivity extends AppCompatActivity {
    private ActivityGoodsDetailBinding detailBinding;
    private User seller;
    private Plant plant;
    private Goods goods;
    private boolean collected;
    private boolean collected_before;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private PlantApplication plantApplication;
    private GoodsService goodsService;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getIfCollected(menu);
        return super.onCreateOptionsMenu(menu);
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
        Disposable disposable = goodsService.removeCollection(GoodsFactory.REMOVE_COLLECTION, user.getAccessToken(), user.getUsername(), user.getId(), goods.getId())
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
                        Toast.makeText(GoodsDetailActivity.this, throwable.toString(), Toast.LENGTH_LONG).show();
                    }
                });
        compositeDisposable.add(disposable);
    }

    private void addToCollection() {
        User user = SharedPreferencesHelper.getUser(this);
        Disposable disposable = goodsService.addToCollection(GoodsFactory.ADD_TO_COLLECTION, user.getAccessToken(), user.getUsername(), user.getId(), goods.getId())
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
                        Log.d(TAG.TAG, "accept: ");
                    }
                });
        compositeDisposable.add(disposable);
    }

    private void getIfCollected(final Menu menu) {
        User user = SharedPreferencesHelper.getUser(this);
        plantApplication = PlantApplication.create(this);
        goodsService = plantApplication.getGoodsService();

        Disposable disposable = goodsService.getIfCollected(GoodsFactory.GET_IF_COLLECTED, user.getAccessToken(), user.getUsername(), user.getId(), goods.getId())
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
                        throwable.printStackTrace();
                    }
                });
        compositeDisposable.add(disposable);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NoStatusBar.SetNoStatusBar(this);
        getData();
        initDataBinding();
        initView();
    }

    private void initView() {
        Toolbar toolbar = detailBinding.toolbar;
        toolbar.setTitle(plant.name + "-商品详情");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initDataBinding() {
        detailBinding = DataBindingUtil.setContentView(GoodsDetailActivity.this, R.layout.activity_goods_detail);
        ItemGoodsViewModel viewModel = new ItemGoodsViewModel(this, goods, plant, seller);
        PlantDetailViewModel plantDetailViewModel = new PlantDetailViewModel(this, plant);
        detailBinding.setDetail(viewModel);
        detailBinding.setPlantDetail(plantDetailViewModel);
    }

    private void getData() {
        Intent intent = getIntent();
        goods = (Goods) intent.getSerializableExtra("goods");
        seller = (User) intent.getSerializableExtra("user");
        plant = (Plant) intent.getSerializableExtra("plant");
    }
}
