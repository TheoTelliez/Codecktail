package com.example.appcourstrois;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Drinks {
    @SerializedName("drinks")
    @Expose
    private List<Todo> todo = null;

    public List<Todo> getTodo() {
        return todo;
    }

    public void setTodo(List<Todo> todo) {
        this.todo = todo;
    }
}
