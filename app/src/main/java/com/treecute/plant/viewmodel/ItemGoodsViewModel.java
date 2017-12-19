package com.treecute.plant.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.rey.material.app.BottomSheetDialog;
import com.rey.material.app.Dialog;
import com.treecute.plant.PlantApplication;
import com.treecute.plant.R;
import com.treecute.plant.data.GoodsFactory;
import com.treecute.plant.data.GoodsService;
import com.treecute.plant.databinding.GoodsDetailBottomsheetBinding;
import com.treecute.plant.databinding.SuccessBinding;
import com.treecute.plant.model.Goods;
import com.treecute.plant.model.Plant;
import com.treecute.plant.model.Response;
import com.treecute.plant.model.ResponseResult;
import com.treecute.plant.model.User;
import com.treecute.plant.util.SharedPreferencesHelper;
import com.treecute.plant.util.TAG;
import com.treecute.plant.view.GoodsDetailActivity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by mkind on 2017/12/2 0002.
 */

public class ItemGoodsViewModel extends BaseObservable {
    private Context context;
    private Goods goods;
    private Plant plant;
    private User user;
    private GoodsDetailBottomsheetBinding goodsDetailBottomsheetBinding;
    private SuccessBinding successBinding;
    private ItemGoodsViewModel itemGoodsViewModel;
    public ObservableField<String> inputQuantity;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private BottomSheetDialog mDialog;
    public ObservableField<String> btnHint;


    public ItemGoodsViewModel(Context context, Goods goods, Plant plant, User user) {
        this.context = context;
        this.goods = goods;
        this.plant = plant;
        this.user = user;
        itemGoodsViewModel = this;
        inputQuantity = new ObservableField<>("1");
    }

    public void onGoodsItemClick(View view) {
        Intent intent = new Intent(context, GoodsDetailActivity.class);
        intent.putExtra("goods", goods);
        intent.putExtra("plant", plant);
        intent.putExtra("user", user);
        context.startActivity(intent);
    }

    public void onAddToCartClick(View view) {
        btnHint = new ObservableField<>("加入购物车");
        View bottomsheet = LayoutInflater.from(context).inflate(R.layout.goods_detail_bottomsheet, null);
        goodsDetailBottomsheetBinding = DataBindingUtil.bind(bottomsheet);
        goodsDetailBottomsheetBinding.setDetail(itemGoodsViewModel);
        goodsDetailBottomsheetBinding.quantity.clearFocus();
        mDialog = new BottomSheetDialog(context);
        mDialog.applyStyle(android.R.style.Theme_Material_Dialog)
                .contentView(bottomsheet)
                .inDuration(500)
                .cancelable(true)
                .show();

    }

    public void onBuyClick(View view) {
        btnHint = new ObservableField<>("立即购买");
        View bottomsheet = LayoutInflater.from(context).inflate(R.layout.goods_detail_bottomsheet, null);
        goodsDetailBottomsheetBinding = DataBindingUtil.bind(bottomsheet);
        goodsDetailBottomsheetBinding.setDetail(itemGoodsViewModel);
        goodsDetailBottomsheetBinding.quantity.clearFocus();
        mDialog = new BottomSheetDialog(context);
        mDialog.applyStyle(android.R.style.Theme_Material_Dialog)
                .contentView(bottomsheet)
                .inDuration(500)
                .cancelable(true)
                .show();
    }

    public void onPlusClick(View view) {
        int v = Integer.valueOf(inputQuantity.get());
        String s = String.valueOf(v + 1);
        inputQuantity.set(s);
    }

    public void onMinusClick(View view) {
        int v = Integer.valueOf(inputQuantity.get());
        if (v >= 2) {
            String s = String.valueOf(v - 1);
            inputQuantity.set(s);
        }
    }

    public void onConfirmClick(View view) {
        if (btnHint.get().equals("加入购物车")) {
            addToCart();
        } else if (btnHint.get().equals("立即购买")) {
            buy();
        }
    }

    private void addToCart() {
        int quantity = Integer.valueOf(inputQuantity.get());
        User user = SharedPreferencesHelper.getUser(context);
        PlantApplication plantApplication = PlantApplication.create(context);
        GoodsService goodsService = plantApplication.getGoodsService();
        Disposable disposable = goodsService.addToCart(GoodsFactory.ADD_TO_CART,
                user.getAccessToken(),
                user.getUsername(),
                user.getId(),
                goods.id,
                quantity,
                goods.sellerId,
                goods.price,
                goods.plantId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(plantApplication.subscribeScheduler())
                .subscribe(new Consumer<ResponseResult<Response>>() {
                    @Override
                    public void accept(ResponseResult<Response> responseResponseResult) throws Exception {
                        if (responseResponseResult.getMessage().equals("success")) {
                            mDialog.hide();
                            View dialogView = LayoutInflater.from(context).inflate(R.layout.success, null);
                            successBinding = DataBindingUtil.bind(dialogView);
                            SuccessViewModel successViewModel = new SuccessViewModel(context, "加入购物车成功", "查看购物车");
                            successBinding.setSuccess(successViewModel);
                            Dialog d = new Dialog(context);
                            d.applyStyle(android.R.style.Theme_Material_Dialog)
                                    .contentView(dialogView)
                                    .show();

                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
        compositeDisposable.add(disposable);
    }

    private void buy() {
        int quantity = Integer.valueOf(inputQuantity.get());

    }

    public String getTitle() {
        return goods.getTitle();
    }

    public String getContent() {
        return goods.getContent();
    }

    public String getPrice() {
        return "￥" + String.valueOf(goods.price);
    }

    public String getQuantity() {
        return "剩余" + String.valueOf(goods.quantity);
    }

    public String getSaleCount() {
        return "销量：" + String.valueOf(goods.saleCount);
    }

    public String getCollection() {
        return "收藏：" + String.valueOf(goods.collection);
    }

    public String getPlantName() {
        String plantName = "";
        try {
            plantName = plant.name;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return plantName;
    }

    public String getSellerName() {
        return user.username;
    }

    public String getPlantPic() {
        String picUrl = "";
        try {
            picUrl = plant.picurl;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return picUrl;
    }

    @BindingAdapter("plantPic")
    public static void setImg(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    public void setModel(Goods goods, Plant plant, User user) {
        this.goods = goods;
        this.plant = plant;
        this.user = user;
        notifyChange();
    }
}
