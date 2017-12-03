package com.treecute.plant.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by mkind on 2017/11/21 0021.
 */

public class Plant implements Serializable{

    @SerializedName("id") public Integer id;
    @SerializedName("name") public String name;
    @SerializedName("latinName") public String latinName;
    @SerializedName("binomialNomenclature") public String binomialNomenclature;
    @SerializedName("nickname") public String nickname;
    @SerializedName("picurl") public String picurl;
    @SerializedName("kingdom") public String kingdom;
    @SerializedName("phylum") public String phylum;
    @SerializedName("category") public String category;
    @SerializedName("pClass") public String pClass;
    @SerializedName("order") public String order;
    @SerializedName("suborder") public String suborder;
    @SerializedName("subclass") public String subclass;
    @SerializedName("namerAge") public String namerAge;
    @SerializedName("family") public String family;
    @SerializedName("subfamily") public String subfamily;
    @SerializedName("subphylum") public String subphylum;
    @SerializedName("genus") public String genus;
    @SerializedName("species") public String species;
    @SerializedName("distributionArea") public String distributionArea;
    @SerializedName("treatmentFunctions") public String treatmentFunctions;
    @SerializedName("summary") public String summary;
    @SerializedName("categoryPic") public String categoryPic;
    @SerializedName("categoryPicS") public String categoryPicS;
    @SerializedName("collection")
    public Integer collection;

    public Integer getCollection() {
        return collection;
    }

    public void setCollection(Integer collection) {
        this.collection = collection;
    }

    public String getCategoryPic() {
        return categoryPic;
    }
    public void setCategoryPic(String categoryPic) {
        this.categoryPic = categoryPic == null ? null : categoryPic.trim();
    }

    public String getCategoryPicS() {
        return categoryPicS;
    }

    public void setCategoryPicS(String categoryPicS) {
        this.categoryPicS = categoryPicS == null ? null : categoryPicS.trim();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName == null ? null : latinName.trim();
    }

    public String getBinomialNomenclature() {
        return binomialNomenclature;
    }

    public void setBinomialNomenclature(String binomialNomenclature) {
        this.binomialNomenclature = binomialNomenclature == null ? null : binomialNomenclature.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl == null ? null : picurl.trim();
    }

    public String getKingdom() {
        return kingdom;
    }

    public void setKingdom(String kingdom) {
        this.kingdom = kingdom == null ? null : kingdom.trim();
    }

    public String getPhylum() {
        return phylum;
    }

    public void setPhylum(String phylum) {
        this.phylum = phylum == null ? null : phylum.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getpClass() {
        return pClass;
    }

    public void setpClass(String pClass) {
        this.pClass = pClass == null ? null : pClass.trim();
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order == null ? null : order.trim();
    }

    public String getSuborder() {
        return suborder;
    }

    public void setSuborder(String suborder) {
        this.suborder = suborder == null ? null : suborder.trim();
    }

    public String getSubclass() {
        return subclass;
    }

    public void setSubclass(String subclass) {
        this.subclass = subclass == null ? null : subclass.trim();
    }


    public String getNamerAge() {
        return namerAge;
    }


    public void setNamerAge(String namerAge) {
        this.namerAge = namerAge == null ? null : namerAge.trim();
    }


    public String getFamily() {
        return family;
    }


    public void setFamily(String family) {
        this.family = family == null ? null : family.trim();
    }


    public String getSubfamily() {
        return subfamily;
    }


    public void setSubfamily(String subfamily) {
        this.subfamily = subfamily == null ? null : subfamily.trim();
    }


    public String getSubphylum() {
        return subphylum;
    }

    public void setSubphylum(String subphylum) {
        this.subphylum = subphylum == null ? null : subphylum.trim();
    }

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus == null ? null : genus.trim();
    }


    public String getSpecies() {
        return species;
    }


    public void setSpecies(String species) {
        this.species = species == null ? null : species.trim();
    }


    public String getDistributionArea() {
        return distributionArea;
    }


    public void setDistributionArea(String distributionArea) {
        this.distributionArea = distributionArea == null ? null : distributionArea.trim();
    }


    public String getTreatmentFunctions() {
        return treatmentFunctions;
    }


    public void setTreatmentFunctions(String treatmentFunctions) {
        this.treatmentFunctions = treatmentFunctions == null ? null : treatmentFunctions.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public boolean hasGenus() {
        return genus!=null && !genus.isEmpty();
    }

    public boolean hasNickName(){
        return nickname!=null && !nickname.isEmpty();
    }

    public boolean hasLatinName(){
        return latinName!=null && !latinName.isEmpty();
    }

    public boolean hasBinomialNomenclature(){
        return binomialNomenclature!=null && !binomialNomenclature.isEmpty();
    }
    public boolean hasKingdom(){
        return kingdom!=null && !kingdom.isEmpty();
    }
    public boolean hasPhylum(){
        return phylum!=null && !phylum.isEmpty();
    }
    public boolean hasCategory() {
        return category!=null && !category.isEmpty();
    }

    public boolean haspClass() {
        return pClass!=null && !pClass.isEmpty();
    }

    public boolean hasOrder() {
        return order!=null && !order.isEmpty();
    }

    public boolean hasNamerAge() {
        return namerAge!=null && !namerAge.isEmpty();
    }

    public boolean hasFamily() {
        return family!=null && !family.isEmpty();
    }

    public boolean hasDistributionArea() {
        return distributionArea!=null && !distributionArea.isEmpty();
    }

    public boolean hasTreatmentFunctions() {
        return treatmentFunctions!=null && !treatmentFunctions.isEmpty();
    }

    public boolean hasSpecies() {
        return species!=null && !species.isEmpty();
    }

    public boolean hasSummary(){
        return summary!=null && !summary.isEmpty();
    }


}
