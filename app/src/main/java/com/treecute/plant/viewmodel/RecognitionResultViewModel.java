package com.treecute.plant.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.view.View;

import com.treecute.plant.model.RecognitionResult;
import com.treecute.plant.view.RecognitionActivity;

import java.util.List;

/**
 * Created by mkind on 2017/11/28 0028.
 */

public class RecognitionResultViewModel extends BaseObservable{
    private Context context;
    private RecognitionResult recognitionResult;

    public RecognitionResultViewModel(Context context, RecognitionResult result) {
        this.context = context;
        this.recognitionResult = result;
    }

    public void back(View view){
        Intent intent = new Intent(context, RecognitionActivity.class);
        context.startActivity(intent);
    }

    public String getPossibility(){
        return recognitionResult.possibility;
    }

    public void setRecognitionResult(RecognitionResult recognitionResult) {
        this.recognitionResult = recognitionResult;
        notifyChange();
    }
}
