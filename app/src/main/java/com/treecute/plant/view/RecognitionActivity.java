package com.treecute.plant.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.treecute.plant.R;
import com.treecute.plant.databinding.ActivityRecognitionBinding;

/**
 * Created by mkind on 2017/11/27 0027.
 */

public class RecognitionActivity extends AppCompatActivity {
    private ActivityRecognitionBinding activityRecognitionBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRecognitionBinding = DataBindingUtil.setContentView(this,R.layout.activity_recognition);
    }
}
