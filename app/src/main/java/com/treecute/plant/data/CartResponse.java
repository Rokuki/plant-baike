package com.treecute.plant.data;

import com.google.gson.annotations.SerializedName;
import com.treecute.plant.model.Cart;

import java.util.List;

/**
 * Created by mkind on 2017/12/18 0018.
 */

public class CartResponse {
    @SerializedName("data")
    private List<Cart> carts;

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }
}
