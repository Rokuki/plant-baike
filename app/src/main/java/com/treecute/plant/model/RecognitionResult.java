package com.treecute.plant.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mkind on 2017/11/28 0028.
 */

public class RecognitionResult {
    @SerializedName("name") public String name;
    @SerializedName("possibility") public String possibility;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosibility() {
        return possibility;
    }

    public void setPosibility(String posibility) {
        this.possibility = posibility;
    }
}
