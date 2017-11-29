package com.treecute.plant.util;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

/**
 * Created by mkind on 2017/11/27 0027.
 */

public class Permissions {
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE" };
    private static final int RECORD_AUDIO = 1;
    private static String[] PERMISSION_RECORD_AUDIO = {
            "android.permission.RECORD_AUDIO"
    };

    public static void verifyRecordAudioPermissions(Activity activity){
        try {
            int permission = ActivityCompat.checkSelfPermission(activity,PERMISSION_RECORD_AUDIO[0]);
            if (permission!=PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(activity,PERMISSION_RECORD_AUDIO,RECORD_AUDIO);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void verifyStoragePermissions(Activity activity) {

        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
