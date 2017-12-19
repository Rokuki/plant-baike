package com.treecute.plant.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import com.treecute.plant.PlantApplication;
import com.treecute.plant.R;
import com.treecute.plant.data.CartResponse;
import com.treecute.plant.data.GoodsFactory;
import com.treecute.plant.data.GoodsService;
import com.treecute.plant.databinding.ActivityCartBinding;
import com.treecute.plant.model.Cart;
import com.treecute.plant.model.ResponseResult;
import com.treecute.plant.model.User;
import com.treecute.plant.util.SharedPreferencesHelper;
import com.treecute.plant.util.TAG;
import com.treecute.plant.view.adapter.CartAdapter;
import com.treecute.plant.viewmodel.CartViewModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class CartActivity extends AppCompatActivity {
    private ActivityCartBinding activityCartBinding;
    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private CompositeDisposable com = new CompositeDisposable();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        initView();
        getData();
    }

    private void initDataBinding() {
        activityCartBinding = DataBindingUtil.setContentView(CartActivity.this, R.layout.activity_cart);
        CartViewModel cartViewModel = new CartViewModel(CartActivity.this);
        activityCartBinding.setCart(cartViewModel);
    }

    private void initView() {
        recyclerView = activityCartBinding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(CartActivity.this));
        cartAdapter = new CartAdapter();
        recyclerView.setAdapter(cartAdapter);
        activityCartBinding.toolbar.setTitle("购物车");
        setSupportActionBar(activityCartBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public void getData() {
        User user = SharedPreferencesHelper.getUser(CartActivity.this);
        PlantApplication app = PlantApplication.create(CartActivity.this);
        GoodsService service = app.getGoodsService();
        Disposable disposable = service.queryCart(GoodsFactory.QUERY_CART, user.getAccessToken(), user.getUsername(), user.getId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(app.subscribeScheduler())
                .subscribe(new Consumer<ResponseResult<CartResponse>>() {
                    @Override
                    public void accept(ResponseResult<CartResponse> cartResponseResponseResult) throws Exception {
                        cartAdapter.setCarts(cartResponseResponseResult.getData().getCarts());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
        com.add(disposable);
    }
}
