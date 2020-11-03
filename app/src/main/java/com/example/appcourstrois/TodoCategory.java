package com.example.appcourstrois;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TodoCategory {


    @Expose
    @SerializedName("strCategory")
    String strCategory;


    //strCategory
    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory() {
        this.strCategory = strCategory;
    }



}
