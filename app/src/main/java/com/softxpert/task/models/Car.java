package com.softxpert.task.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Car implements Serializable {

    @SerializedName("id")
    public Integer id;
    @SerializedName("brand")
    public String brand;
    @SerializedName("constructionYear")
    public String constructionYear;
    @SerializedName("isUsed")
    public Boolean isUsed;
    @SerializedName("imageUrl")
    public String imageUrl;

}