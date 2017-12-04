package com.treecute.plant.data;

import com.google.gson.annotations.SerializedName;
import com.treecute.plant.model.Goods;
import com.treecute.plant.model.Plant;

import java.util.List;

/**
 * Created by mkind on 2017/12/2 0002.
 */

public class GoodsResponse {
    @SerializedName("data")
    private List<Goods> goodsList;


    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }
}
