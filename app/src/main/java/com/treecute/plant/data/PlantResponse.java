package com.treecute.plant.data;

import com.google.gson.annotations.SerializedName;
import com.treecute.plant.model.Plant;

import java.util.List;

/**
 * Created by mkind on 2017/11/21 0021.
 */

public class PlantResponse {

    @SerializedName("data") private List<Plant> plantList;

    public List<Plant> getPlantList() {
        return plantList;
    }

    public void setPlantList(List<Plant> plantList) {
        this.plantList = plantList;
    }
}
