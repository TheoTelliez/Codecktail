package com.example.appcourstrois;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategDrinks {
    @SerializedName("drinks")
    @Expose
    private List<CategTodo> categtodo = null;

    public List<CategTodo> getCategTodo() {
        return categtodo;
    }

    public void setCategTodo(List<CategTodo> categtodo) {
        this.categtodo = categtodo;
    }



}
