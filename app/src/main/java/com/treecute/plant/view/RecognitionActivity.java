package com.treecute.plant.view;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.otaliastudios.cameraview.CameraView;
import com.treecute.plant.R;
import com.treecute.plant.databinding.ActivityRecognitionBinding;
import com.treecute.plant.util.Permissions;
import com.treecute.plant.util.SdcardHelper;
import com.treecute.plant.viewmodel.RecognitionViewModel;

import java.io.File;


/**
 * Created by mkind on 2017/11/27 0027.
 */

public class RecognitionActivity extends AppCompatActivity {
    private String sdCardDir;
    private ActivityRecognitionBinding activityRecognitionBinding;
    private CameraView cameraView;
    private String SDCARD_DIR = Environment.getExternalStorageDirectory().getPath()+"/plant/";
    private String NOSDCARD_DIR = Environment.getDownloadCacheDirectory() + "/plant/";

    @Override
    protected void onResume() {
        super.onResume();
        cameraView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        cameraView.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cameraView.destroy();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Permissions.verifyStoragePermissions(RecognitionActivity.this);
        initDir();
        initDataBinding();
        initToolbar();
    }

    private void initDataBinding() {
        activityRecognitionBinding = DataBindingUtil.setContentView(this,R.layout.activity_recognition);
        cameraView = activityRecognitionBinding.camera;
        RecognitionViewModel recognitionViewModel = new RecognitionViewModel(RecognitionActivity.this,cameraView,sdCardDir);
        activityRecognitionBinding.setRecognition(recognitionViewModel);
    }

    private void initDir() {
        if (SdcardHelper.hadSdCard()){
            sdCardDir = SDCARD_DIR;
        }else {
            sdCardDir = NOSDCARD_DIR;
        }
        File destDir = new File(sdCardDir);
        if (!destDir.exists()) {
            boolean i = destDir.mkdirs();
        }
    }

    private void initToolbar() {
        setSupportActionBar(activityRecognitionBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
