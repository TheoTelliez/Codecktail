package com.example.appcourstrois;

import com.example.appcourstrois.Drinks;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WebServicesInterface {

    @GET("lookup.php?i=11007")
    Call<Drinks> getTodoById();

    @GET("random.php")
    Call<Drinks> getTodoByRandom();

    @GET("search.php")
    Call<Drinks> getTodoByName(@Query("s") String s);

    @GET("list.php?c=list")
    Call<Drinks> listDrinksByCategory();

}
