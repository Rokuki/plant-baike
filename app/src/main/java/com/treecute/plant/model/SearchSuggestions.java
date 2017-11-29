package com.treecute.plant.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by mkind on 2017/11/28 0028.
 */

public class SearchSuggestions implements Serializable {
    @SerializedName("name") public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
