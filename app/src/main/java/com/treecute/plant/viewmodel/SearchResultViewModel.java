package com.treecute.plant.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;

/**
 * Created by mkind on 2017/11/29 0029.
 */

public class SearchResultViewModel extends BaseObservable {
    private Context context;

    public SearchResultViewModel(Context context) {
        this.context = context;
    }
}
