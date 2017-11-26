package com.treecute.plant.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by mkind on 2017/11/26 0026.
 */

public class PlantCategory implements Serializable {
    @SerializedName("category") public String category;
    @SerializedName("categoryPic") public String categoryPic;
    @SerializedName("categoryPicS") public String categoryPicS;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryPic() {
        return categoryPic;
    }

    public void setCategoryPic(String categoryPic) {
        this.categoryPic = categoryPic;
    }

    public String getCategoryPicS() {
        return categoryPicS;
    }

    public void setCategoryPicS(String categoryPicS) {
        this.categoryPicS = categoryPicS;
    }
}
