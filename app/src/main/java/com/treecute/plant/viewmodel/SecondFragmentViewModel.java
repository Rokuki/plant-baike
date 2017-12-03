package com.treecute.plant.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.util.Log;
import android.view.View;

import com.treecute.plant.util.TAG;
import com.treecute.plant.view.PublishGoodsActivity;

/**
 * Created by mkind on 2017/12/3 0003.
 */

public class SecondFragmentViewModel extends BaseObservable {
    private Context context;

    public SecondFragmentViewModel(Context context) {
        this.context = context;
    }

    public void onPublishGoodsClick(View view) {
        Intent intent = new Intent(view.getContext(), PublishGoodsActivity.class);
        view.getContext().startActivity(intent);
    }
}
