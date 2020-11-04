package com.example.appcourstrois;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Drinks {
    @SerializedName("drinks")
    private List<Todo> todo = null;
    private List<TodoCategory> todoCat = null;



    public List<Todo> getTodo() {
        return todo;
    }

    public List<TodoCategory> getTodoCat() {
        return todoCat;
    }



    public void setTodo(List<Todo> todo) {
        this.todo = todo;
    }

    public void setTodoCat(List<TodoCategory> todoCat) {
        this.todoCat = todoCat;
    }


}
