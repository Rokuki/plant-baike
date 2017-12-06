package com.treecute.plant.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.treecute.plant.PlantApplication;
import com.treecute.plant.R;
import com.treecute.plant.data.PlantFactory;
import com.treecute.plant.data.PlantResponse;
import com.treecute.plant.data.PlantService;
import com.treecute.plant.databinding.ActivityRecognitionResultBinding;
import com.treecute.plant.model.Plant;
import com.treecute.plant.model.RecognitionResult;
import com.treecute.plant.util.NoStatusBar;
import com.treecute.plant.util.TAG;
import com.treecute.plant.view.adapter.RecognitionResultAdapter;
import com.treecute.plant.viewmodel.ItemRecognitionResultViewModel;
import com.treecute.plant.viewmodel.RecognitionResultViewModel;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.Pivot;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by mkind on 2017/11/28 0028.
 */

public class RecognitionResultActivity extends AppCompatActivity {
    private List<RecognitionResult> resultList;
    private List<Plant> plantList;
    private String names = "";
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private DiscreteScrollView discreteScrollView;
    private RecognitionResultAdapter adapter;
    private ActivityRecognitionResultBinding binding;
    private RecognitionResultViewModel recognitionResultViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getResultList();
        initDataBinding();
        initToolbar();
        getData();
    }

    private void initToolbar() {
        binding.toolbar.setTitle(R.string.recognition_result);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initDataBinding() {
        binding = DataBindingUtil.setContentView(RecognitionResultActivity.this,R.layout.activity_recognition_result);
        recognitionResultViewModel = new RecognitionResultViewModel(RecognitionResultActivity.this,resultList.get(0));
        binding.setRecognitionResult(recognitionResultViewModel);
        discreteScrollView = binding.picker;
        adapter = new RecognitionResultAdapter();
        discreteScrollView.setAdapter(adapter);
        discreteScrollView.setItemTransformer(new ScaleTransformer.Builder()
                .setMaxScale(1.05f)
                .setMinScale(0.8f)
                .setPivotX(Pivot.X.CENTER) // CENTER is a default one
                .setPivotY(Pivot.Y.BOTTOM) // CENTER is a default one
                .build());
        discreteScrollView.setItemTransitionTimeMillis(200);
        discreteScrollView.addScrollStateChangeListener(new DiscreteScrollView.ScrollStateChangeListener<RecyclerView.ViewHolder>() {
            @Override
            public void onScrollStart(@NonNull RecyclerView.ViewHolder currentItemHolder, int adapterPosition) {

            }

            @Override
            public void onScrollEnd(@NonNull RecyclerView.ViewHolder currentItemHolder, int adapterPosition) {
                recognitionResultViewModel.setRecognitionResult(resultList.get(adapterPosition));
            }

            @Override
            public void onScroll(float scrollPosition, int currentPosition, int newPosition, @Nullable RecyclerView.ViewHolder currentHolder, @Nullable RecyclerView.ViewHolder newCurrent) {

            }
        });
    }

    public void getResultList() {
        Intent intent = getIntent();
        resultList = (List<RecognitionResult>) intent.getSerializableExtra("resultList");
        for (RecognitionResult r:resultList){
            names = names + r.getName() + ",";
        }
        names = names.substring(0,names.length()-1);
        Log.d(TAG.TAG, "getResultList: "+names);
    }

    public void getData() {
        PlantApplication plantApplication = PlantApplication.create(RecognitionResultActivity.this);
        PlantService plantService = plantApplication.getPlantService();

        Disposable disposable = plantService.getPlantsByNameList(PlantFactory.GET_PLANTS_BY_NAME_LIST,names)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(plantApplication.subscribeScheduler())
                .subscribe(new Consumer<PlantResponse>() {
                    @Override
                    public void accept(PlantResponse plantResponse) throws Exception {
                        plantList = plantResponse.getPlantList();
                        adapter.setPlantList(plantList,resultList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG.TAG, "accept: "+throwable.toString());
                    }
                });
        compositeDisposable.add(disposable);
    }
}
