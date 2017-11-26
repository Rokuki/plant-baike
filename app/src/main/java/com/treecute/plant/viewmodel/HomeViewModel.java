package com.treecute.plant.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;

/**
 * Created by mkind on 2017/11/25 0025.
 */

public class HomeViewModel extends BaseObservable {
    private Context context;

    public HomeViewModel(Context context) {
        this.context = context;
    }
}
