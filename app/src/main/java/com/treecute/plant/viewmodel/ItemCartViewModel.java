package com.treecute.plant.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.treecute.plant.model.Cart;
import com.treecute.plant.model.Plant;

/**
 * Created by mkind on 2017/12/18 0018.
 */

public class ItemCartViewModel extends BaseObservable {
    private Context context;
    private Cart cart;
    private Plant plant;

    public ItemCartViewModel(Context context, Cart cart, Plant plant) {
        this.context = context;
        this.cart = cart;
        this.plant = plant;
    }

    public String getPic() {
        return plant.picurl;
    }

    public String getPrice() {
        return String.valueOf("￥" + cart.price);
    }

    public String getTitle() {
        return plant.name;
    }

    public String getQuantity() {
        return String.valueOf("×" + cart.quantity);
    }

    @BindingAdapter("pic")
    public static void setPic(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    public void setModel(Cart cart, Plant plant) {
        this.cart = cart;
        this.plant = plant;
        notifyChange();
    }
}
