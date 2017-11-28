package com.treecute.plant.data;

import com.google.gson.annotations.SerializedName;
import com.treecute.plant.model.RecognitionResult;

import java.util.List;

/**
 * Created by mkind on 2017/11/28 0028.
 */

public class RecognitionResponse {
    @SerializedName("data") private List<RecognitionResult> list;

    public List<RecognitionResult> getList() {
        return list;
    }

    public void setList(List<RecognitionResult> list) {
        this.list = list;
    }
}
