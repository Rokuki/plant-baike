package com.treecute.plant.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.CameraUtils;
import com.otaliastudios.cameraview.CameraView;
import com.treecute.plant.PlantApplication;
import com.treecute.plant.data.PlantFactory;
import com.treecute.plant.data.PlantService;
import com.treecute.plant.data.RecognitionResponse;
import com.treecute.plant.util.RandomString;
import com.treecute.plant.view.RecognitionActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by mkind on 2017/11/27 0027.
 */

public class RecognitionViewModel extends BaseObservable {
    private String sdCardDir;
    private Context context;
    private CameraView cameraView;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    public RecognitionViewModel(Context context, CameraView cameraView, String sdCardDir) {
        this.context = context;
        this.cameraView = cameraView;
        this.sdCardDir = sdCardDir;
        initCamera();
    }

    private void initCamera() {
        cameraView.addCameraListener(new CameraListener() {
            @Override
            public void onPictureTaken(byte[] picture) {
                CameraUtils.decodeBitmap(picture, new CameraUtils.BitmapCallback() {
                    @Override
                    public void onBitmapReady(Bitmap bitmap) {
                        File file = new File(saveBitmapToLocalDir(bitmap));
                        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),file);
                        MultipartBody.Part body = MultipartBody.Part.createFormData("file",file.getName(),requestBody);

                        PlantApplication plantApplication = PlantApplication.create(context);
                        PlantService plantService = plantApplication.getPlantService();
                        Disposable disposable = plantService.recognition(PlantFactory.UPLOAD_PLANT_PIC,body)
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribeOn(plantApplication.subscribeScheduler())
                                .subscribe(new Consumer<RecognitionResponse>() {
                                    @Override
                                    public void accept(RecognitionResponse recognitionResponse) throws Exception {

                                    }
                                }, new Consumer<Throwable>() {
                                    @Override
                                    public void accept(Throwable throwable) throws Exception {

                                    }
                                });
                        compositeDisposable.add(disposable);
                    }
                });
            }
        });
    }

    public void onTakePicClick(View view){
        cameraView.captureSnapshot();
    }

    private String saveBitmapToLocalDir(Bitmap bitmap) {
        String fileName = RandomString.getRandomString(6) + ".jpeg";
        File newFile = new File(sdCardDir,fileName);
        if (newFile.exists()){
            newFile.delete();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(newFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG,50,fileOutputStream);
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
        }
        return sdCardDir + "/" + fileName;
    }

}
