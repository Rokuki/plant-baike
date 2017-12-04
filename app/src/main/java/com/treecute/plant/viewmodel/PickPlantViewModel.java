package com.treecute.plant.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.util.Log;

import com.treecute.plant.PlantApplication;
import com.treecute.plant.data.PlantFactory;
import com.treecute.plant.data.PlantResponse;
import com.treecute.plant.data.PlantService;
import com.treecute.plant.util.TAG;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by mkind on 2017/12/3 0003.
 */

public class PickPlantViewModel extends BaseObservable {
    private Context context;

    public PickPlantViewModel(Context context) {
        this.context = context;
    }
}
