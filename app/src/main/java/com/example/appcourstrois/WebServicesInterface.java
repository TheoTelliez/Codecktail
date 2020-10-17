package com.example.appcourstrois;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WebServicesInterface {

    @GET("lookup.php?i=11007")
    Call<Drinks> getTodoById();

    @GET("random.php")
    Call<Drinks> getTodoByRandom();

}
