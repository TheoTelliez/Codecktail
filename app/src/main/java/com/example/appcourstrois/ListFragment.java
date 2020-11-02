package com.example.appcourstrois;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ListFragment extends Fragment {

    private List<ModelCateg> drinksList;
    private RecyclerView categoryItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //C'est parti pour la liste:

        View view = inflater.inflate(R.layout.categories_list_cell, container, false);

        categoryItem = (RecyclerView) view.findViewById(R.id.categoryItem);
        categoryItem.setHasFixedSize(true);
        categoryItem.setLayoutManager(new LinearLayoutManager(getContext()));

        final TextView categoryNameCell
        ;


        //On déclare les valeurs
        categoryItem = view.findViewById(R.id.categoryItem);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WebServicesInterface webServicesInterface = retrofit.create(WebServicesInterface.class);

        drinksList = new ArrayList<>();

        Call<Drinks> calllistDrinksByCategory = webServicesInterface.listDrinksByCategory();

        calllistDrinksByCategory.enqueue(new Callback<Drinks>() {
            @Override
            public void onResponse(Call<Drinks> call, Response<Drinks> response) {
                for (int cocktailNumber = response.body().getTodo().size() - 1; cocktailNumber >= 0; cocktailNumber--) {
                    drinksList.add(new ModelCateg(response.body().getTodo().get(cocktailNumber).getStrCategory()));
                }
            }

            @Override
            public void onFailure(Call<Drinks> call, Throwable t) {

            }
        });

        categoryItem.setAdapter(new DrinksListAdapter(drinksList));



        return view;
    }



}







