package com.treecute.plant.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.treecute.plant.model.Goods;
import com.treecute.plant.model.Plant;
import com.treecute.plant.model.User;
import com.treecute.plant.util.TAG;
import com.treecute.plant.view.GoodsDetail;

/**
 * Created by mkind on 2017/12/2 0002.
 */

public class ItemGoodsViewModel extends BaseObservable {
    private Context context;
    private Goods goods;
    private Plant plant;
    private User user;

    public ItemGoodsViewModel(Context context, Goods goods, Plant plant, User user) {
        this.context = context;
        this.goods = goods;
        this.plant = plant;
        this.user = user;
    }

    public void onGoodsItemClick(View view) {
        Intent intent = new Intent(context, GoodsDetail.class);
        intent.putExtra("goods", goods);
        intent.putExtra("plant", plant);
        intent.putExtra("user", user);
        context.startActivity(intent);
    }
    
    public String getTitle() {
        return goods.getTitle();
    }

    public String getPrice() {
        return "￥" + String.valueOf(goods.price);
    }

    public String getQuantity() {
        return String.valueOf(goods.quantity);
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
