package com.treecute.plant.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.view.View;

import com.treecute.plant.view.CartActivity;

/**
 * Created by mkind on 2017/12/17 0017.
 */

public class SuccessViewModel extends BaseObservable {
    private Context context;
    public ObservableField<String> hint;
    public ObservableField<String> buttonHint;

    public SuccessViewModel(Context context, String hintText, String btnHint) {
        this.context = context;
        hint = new ObservableField<>(hintText);
        buttonHint = new ObservableField<>(btnHint);
    }

    public void onBtnClick(View view) {
        if (buttonHint.get().equals("查看购物车")) {
            Intent intent = new Intent(context, CartActivity.class);
            context.startActivity(intent);
        }
    }
}
