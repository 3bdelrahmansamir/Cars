package com.softxpert.task.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Response<T> implements Serializable {
    @SerializedName("status")
    public Integer status;
    @SerializedName("data")
    public List<T> data = new ArrayList<>();
    @SerializedName("error")
    public Error error;
}