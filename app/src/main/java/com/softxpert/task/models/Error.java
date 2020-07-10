package com.softxpert.task.models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Error implements Serializable {
    @SerializedName("code")
    public Integer code;
    @SerializedName("message")
    public String message;
}