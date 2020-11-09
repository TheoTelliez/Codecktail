package com.example.appcourstrois.webservices;

import com.example.appcourstrois.model.Drinks;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WebServicesInterface {

    @GET("lookup.php?i=11007")
    Call<Drinks> getTodoById(@Query("i") String i);

    @GET("random.php")
    Call<Drinks> getTodoByRandom();

    @GET("search.php")
    Call<Drinks> getTodoByName(@Query("s") String s);

    @GET("list.php?c=list")
    Call<Drinks> getListDrinksByCategory();

    @GET("filter.php?c=")
    Call<Drinks> getListDrinksByCategoryCustom(@Query("c") String c);

    // Exemple :  https://www.thecocktaildb.com/api/json/v1/1/filter.php?c=Ordinary_Drink

}
