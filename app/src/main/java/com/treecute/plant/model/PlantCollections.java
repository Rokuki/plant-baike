package com.treecute.plant.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by mkind on 2017/12/19 0019.
 */

public class PlantCollections implements Serializable {
    @SerializedName("id")
    public Integer id;

    @SerializedName("userId")
    public Integer userId;

    @SerializedName("plantId")
    public Integer plantId;

    @SerializedName("createTime")
    public String createTime;

    @SerializedName("plant")
    public Plant plant;

    public PlantCollections() {
    }

    public PlantCollections(Integer userId, Integer plantId, String createTime) {
        this.userId = userId;
        this.plantId = plantId;
        this.createTime = createTime;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPlantId() {
        return plantId;
    }

    public void setPlantId(Integer plantId) {
        this.plantId = plantId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

}
