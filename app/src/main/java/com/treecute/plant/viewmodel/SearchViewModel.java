package com.treecute.plant.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;

/**
 * Created by mkind on 2017/11/28 0028.
 */

public class SearchViewModel extends BaseObservable {
    private Context context;

    public SearchViewModel(Context context) {
        this.context = context;
    }
}
