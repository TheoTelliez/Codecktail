package com.example.appcourstrois.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Drinks {
    @SerializedName("drinks")
    private List<Todo> todo = null;

    public List<Todo> getTodo() {
        return todo;
    }

    public void setTodo(List<Todo> todo) {
        this.todo = todo;
    }


}
