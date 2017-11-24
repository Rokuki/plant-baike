package com.treecute.plant.data;

import com.google.gson.annotations.SerializedName;
import com.treecute.plant.model.Plant;

import java.util.List;

/**
 * Created by mkind on 2017/11/24 0024.
 */

public class IntegerResponse {

    @SerializedName("status") private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
