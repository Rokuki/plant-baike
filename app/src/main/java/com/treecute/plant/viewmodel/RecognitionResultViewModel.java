package com.treecute.plant.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;

import com.treecute.plant.model.RecognitionResult;

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

    public String getPossibility(){
        return recognitionResult.possibility;
    }

    public void setRecognitionResult(RecognitionResult recognitionResult) {
        this.recognitionResult = recognitionResult;
        notifyChange();
    }
}
