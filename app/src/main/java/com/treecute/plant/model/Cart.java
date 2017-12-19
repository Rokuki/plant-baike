package com.treecute.plant.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by mkind on 2017/12/18 0018.
 */

public class Cart implements Serializable {
    @SerializedName("id")
    public Integer id;
    @SerializedName("userId")
    public Integer userId;
    @SerializedName("itemId")
    public Integer itemId;
    @SerializedName("sellerId")
    public Integer sellerId;
    @SerializedName("quantity")
    public Integer quantity;
    @SerializedName("createTime")
    public String createTime;
    @SerializedName("price")
    public Double price;
    @SerializedName("updateTime")
    public String updateTime;
    @SerializedName("state")
    public Integer state;
    @SerializedName("plant")
    public Plant plant;

    public Cart() {
    }

    public Cart(Integer userId, Integer itemId, Integer sellerId, Integer quantity, String createTime, Double price) {
        this.userId = userId;
        this.itemId = itemId;
        this.sellerId = sellerId;
        this.quantity = quantity;
        this.createTime = createTime;
        this.price = price;
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

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
