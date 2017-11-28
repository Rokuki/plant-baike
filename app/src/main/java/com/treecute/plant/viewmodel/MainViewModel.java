package com.treecute.plant.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.view.View;

import com.treecute.plant.view.RecognitionActivity;

/**
 * Created by mkind on 2017/11/28 0028.
 */

public class MainViewModel extends BaseObservable{
    private Context context;

    public MainViewModel(Context context) {
        this.context = context;
    }

    public void onRecognitionClick(View view){
        Intent intent = new Intent(context, RecognitionActivity.class);
        context.startActivity(intent);
    }
}
