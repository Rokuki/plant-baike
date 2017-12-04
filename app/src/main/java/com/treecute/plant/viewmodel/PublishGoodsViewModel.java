package com.treecute.plant.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.text.Editable;
import android.util.Log;
import android.view.View;

import com.treecute.plant.PlantApplication;
import com.treecute.plant.data.GoodsFactory;
import com.treecute.plant.data.GoodsService;
import com.treecute.plant.model.Response;
import com.treecute.plant.model.ResponseResult;
import com.treecute.plant.util.TAG;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by mkind on 2017/12/3 0003.
 */

public class PublishGoodsViewModel extends BaseObservable {
    private Context context;
    public ObservableField<String> titleHint;
    public ObservableField<String> contentHint;
    public ObservableField<String> priceHint;
    public ObservableField<String> quantityHint;
    private String title;
    private String content;
    private double price;
    private Integer quantity;
    private int plant_id;
    private String userName;
    private Integer userId;
    private String token;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    public PublishGoodsViewModel(Context context) {
        this.context = context;
        titleHint = new ObservableField<>();
        contentHint = new ObservableField<>();
        priceHint = new ObservableField<>();
        quantityHint = new ObservableField<>();
        titleHint.set("标题");
        contentHint.set("内容");
        priceHint.set("价格");
        quantityHint.set("数量");
    }

    public void onPublishClick(View view) {
        PlantApplication plantApplication = PlantApplication.create(context);
        GoodsService goodsService = plantApplication.getGoodsService();
        Disposable disposable = goodsService.add(GoodsFactory.ADD, token, userName, userId, price, quantity, title, content, plant_id)
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
                        Log.d(TAG.TAG, "accept: " + throwable.toString());
                    }
                });
        compositeDisposable.add(disposable);

    }


    public void afterTitleInput(Editable e) {
        title = e.toString();
        if (e.length() == 0) {
            titleHint.set("标题不能为空");
        }
    }

    public void afterQuantityInput(Editable e) {
        if (e.length() == 0) {
            quantityHint.set("数量不能为空");
        } else {
            quantity = Integer.valueOf(e.toString());
        }
    }

    public void afterContentInput(Editable e) {
        content = e.toString();
        if (e.length() == 0) {
            contentHint.set("内容不能为空");
        }
    }

    public void afterPriceInput(Editable e) {
        if (e.length() == 0) {
            priceHint.set("价格不能为空");
        } else {
            price = Double.parseDouble(e.toString());
        }
    }


    public void setSelectedPlantId(Integer id) {
        this.plant_id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
