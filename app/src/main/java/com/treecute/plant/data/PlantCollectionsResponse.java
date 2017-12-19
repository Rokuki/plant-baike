package com.treecute.plant.data;

import com.google.gson.annotations.SerializedName;
import com.treecute.plant.model.PlantCollections;

import java.util.List;

/**
 * Created by mkind on 2017/12/19 0019.
 */

public class PlantCollectionsResponse {
    @SerializedName("data")
    private List<PlantCollections> collectionsList;

    public List<PlantCollections> getCollectionsList() {
        return collectionsList;
    }

    public void setCollectionsList(List<PlantCollections> collectionsList) {
        this.collectionsList = collectionsList;
    }
}
