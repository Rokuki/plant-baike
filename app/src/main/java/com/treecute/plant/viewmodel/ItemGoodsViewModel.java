package com.treecute.plant.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;

import com.treecute.plant.model.Goods;

/**
 * Created by mkind on 2017/12/2 0002.
 */

public class ItemGoodsViewModel extends BaseObservable {
    private Context context;
    private Goods goods;

    public ItemGoodsViewModel(Context context, Goods goods) {
        this.context = context;
        this.goods = goods;
    }

    public String getTitle() {
        return goods.getTitle();
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
        notifyChange();
    }
}
