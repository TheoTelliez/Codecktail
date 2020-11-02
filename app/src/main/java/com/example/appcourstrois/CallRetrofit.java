package com.example.appcourstrois;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CallRetrofit {
    private static Retrofit getRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static WebServicesInterface getCocktailService(){
        WebServicesInterface webServicesInterface = getRetrofit().create(WebServicesInterface.class);
        return webServicesInterface;
    }
}