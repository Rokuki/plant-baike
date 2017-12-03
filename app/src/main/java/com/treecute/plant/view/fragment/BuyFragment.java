package com.treecute.plant.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.treecute.plant.PlantApplication;
import com.treecute.plant.R;
import com.treecute.plant.data.GoodsFactory;
import com.treecute.plant.data.GoodsResponse;
import com.treecute.plant.data.GoodsService;
import com.treecute.plant.databinding.FragmentBuyBinding;
import com.treecute.plant.view.adapter.GoodsAdapter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by mkind on 2017/11/29 0029.
 */

public class BuyFragment extends android.support.v4.app.Fragment {
    private FragmentBuyBinding buyBinding;
    private RecyclerView recyclerView;
    private GoodsAdapter goodsAdapter;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buy, container, false);
        buyBinding = DataBindingUtil.bind(view);
        initRecyclerView(view);
        getData(view);
        return view;
    }

    private void initRecyclerView(View view) {
        recyclerView = buyBinding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        goodsAdapter = new GoodsAdapter();
        recyclerView.setAdapter(goodsAdapter);
    }

    public void getData(View view) {
        PlantApplication application = PlantApplication.create(view.getContext());
        GoodsService goodsService = application.getGoodsService();

        Disposable disposable = goodsService.getGoodsList(GoodsFactory.GET_GOODS, 0, 20)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(application.subscribeScheduler())
                .subscribe(new Consumer<GoodsResponse>() {
                    @Override
                    public void accept(GoodsResponse goodsResponse) throws Exception {
                        goodsAdapter.setGoodsList(goodsResponse.getGoodsList());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
        compositeDisposable.add(disposable);
    }
}
