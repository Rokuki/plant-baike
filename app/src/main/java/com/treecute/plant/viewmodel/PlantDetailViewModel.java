package com.treecute.plant.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.treecute.plant.model.Plant;

/**
 * Created by mkind on 2017/11/25 0025.
 */

public class PlantDetailViewModel extends BaseObservable {
    private Context context;
    private Plant plant;

    public PlantDetailViewModel(Context context,Plant plant) {
        this.context = context;
        this.plant = plant;
    }

    public int getPclassVisibility(){
        return plant.haspClass() ? View.VISIBLE : View.GONE;
    }
    public int getFamilyVisibility(){
        return plant.hasFamily() ? View.VISIBLE : View.GONE;
    }
    public int getOrderVisibility(){
        return plant.hasOrder() ? View.VISIBLE : View.GONE;
    }
    public int getGenusVisibility(){
        return plant.hasGenus() ? View.VISIBLE : View.GONE;
    }
    public int getPhylumVisibility(){
        return plant.hasPhylum() ? View.VISIBLE : View.GONE;
    }
    public int getSpeciesVisibility(){
        return plant.hasSpecies() ? View.VISIBLE : View.GONE;
    }

    public int getNickNameVisibility(){
        return plant.hasNickName() ? View.VISIBLE : View.GONE;
    }

    public int getCategoryVisibility(){
        return plant.hasCategory() ? View.VISIBLE : View.GONE;
    }

    public int getLatinVisibility(){
        return plant.hasLatinName() ? View.VISIBLE : View.GONE;
    }

    public int getTreatmentFunctionsVisibility(){
        return plant.hasTreatmentFunctions() ? View.VISIBLE : View.GONE;
    }

    public int getNamerAgeVisibility(){
        return plant.hasNamerAge() ? View.VISIBLE : View.GONE;
    }

    public String getName(){
        return plant.name;
    }

    public String getNickName(){
        return plant.nickname;
    }

    public String getLatinName(){
        return plant.latinName;
    }
    public Integer getId() {
        return plant.id;
    }

    public String getBinomialNomenclature() {
        return plant.binomialNomenclature;
    }

    public String picurl(){
        return plant.picurl;
    }

    @BindingAdapter("imageUrl") public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    public String getPClass(){
        return plant.pClass;
    }


    public String getKingdom() {
        return plant.kingdom;
    }

    public String getPhylum() {
        return plant.phylum;
    }

    public String getCategory() {
        return plant.category;
    }

    public String getpClass() {
        return plant.pClass;
    }

    public String getOrder() {
        return plant.order;
    }

    public String getSuborder() {
        return plant.suborder;
    }

    public String getSubclass() {
        return plant.subclass;
    }

    public String getNamerAge() {
        return plant.namerAge;
    }

    public String getFamily() {
        return plant.family;
    }

    public String getSubfamily() {
        return plant.subfamily;
    }

    public String getSubphylum() {
        return plant.subphylum;
    }

    public String getGenus() {
        return plant.genus;
    }

    public String getSpecies() {
        return plant.species;
    }

    public String getDistributionArea() {
        return plant.distributionArea;
    }

    public String getTreatmentFunctions() {
        return plant.treatmentFunctions;
    }

    public String getSummary() {
        return plant.summary;
    }


}
