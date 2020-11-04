package com.example.appcourstrois;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListFragment extends Fragment {

    private RecyclerView categoryListRecyclerView;
    private RecyclerView.Adapter drinksListAdapter;
    private RecyclerView.LayoutManager categoryUserListLayoutManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, null); //le 2e paramètre sera toujours null tandis que le premier représentera l’id de notre layout de fragment (ici : R.layout.fragment_random).
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        super.onViewCreated(view, savedInstanceState);
        categoryListRecyclerView = view.findViewById(R.id.categoryListRecyclerView);

        categoryListRecyclerView.setHasFixedSize(true);
        categoryUserListLayoutManager = new LinearLayoutManager(getContext());
        categoryListRecyclerView.setLayoutManager(categoryUserListLayoutManager);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WebServicesInterface webServicesInterface = retrofit.create(WebServicesInterface.class);

        Call<Drinks> callGetTodoByCategory = webServicesInterface.getListDrinksByCategory();

        callGetTodoByCategory.enqueue(new Callback<Drinks>() {
            @Override
            public void onResponse(Call<Drinks> call, Response<Drinks> response) {

                //categoryNameCell.setText(response.body().getTodoCat().get(0).getStrCategory()); //Fait dans l'adapter

                drinksListAdapter = new DrinksListAdapter(response.body());
                categoryListRecyclerView.setAdapter(drinksListAdapter);

            }

            @Override
            public void onFailure(Call<Drinks> call, Throwable t) {
                System.out.println("Echec du chargement de Codecktail");
            }
        });








    };

}

