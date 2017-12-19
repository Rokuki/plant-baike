package com.treecute.plant.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;

/**
 * Created by mkind on 2017/12/18 0018.
 */

public class CartViewModel extends BaseObservable {
    private Context context;

    public CartViewModel(Context context) {
        this.context = context;
    }

    public String getTotal() {
        return null;
    }
}
