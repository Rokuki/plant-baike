package com.treecute.plant.util;

import android.os.Environment;

/**
 * Created by mkind on 2017/11/27 0027.
 */

public class SdcardHelper {
    public static boolean hadSdCard(){
        String status = Environment.getExternalStorageState();
        return status.equals(Environment.MEDIA_MOUNTED);
    }
}
