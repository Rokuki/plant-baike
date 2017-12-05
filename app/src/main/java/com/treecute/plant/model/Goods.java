package com.treecute.plant.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Goods implements Serializable {
    @SerializedName("id")
    public Integer id;
    @SerializedName("sellerId")
    public Integer sellerId;
    @SerializedName("price")
    public Double price;
    @SerializedName("quantity")
    public Integer quantity;
    @SerializedName("title")
    public String title;
    @SerializedName("content")
    public String content;
    @SerializedName("saleCount")
    public Integer saleCount;
    @SerializedName("collection")
    public Integer collection;
    @SerializedName("plantId")
    public Integer plantId;
    @SerializedName("user")
    public User user;
    @SerializedName("plant")
    public Plant plant;

    public Goods() {
    }

    public Goods(Double price, Integer quantity, String title, String content) {
        this.price = price;
        this.quantity = quantity;
        this.title = title;
        this.content = content;
    }

    public Goods(Integer sellerId, Double price, Integer quantity, String title, String content, Integer plantId) {
        this.sellerId = sellerId;
        this.price = price;
        this.quantity = quantity;
        this.title = title;
        this.content = content;
        this.plantId = plantId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        if (plant.getName() == null) {
            this.plant = null;
        } else {
            this.plant = plant;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    public Integer getCollection() {
        return collection;
    }

    public void setCollection(Integer collection) {
        this.collection = collection;
    }

    public Integer getPlantId() {
        return plantId;
    }

    public void setPlantId(Integer plantId) {
        this.plantId = plantId;
    }
}