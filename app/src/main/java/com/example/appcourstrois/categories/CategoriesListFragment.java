package com.example.appcourstrois.categories;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appcourstrois.cocktailslist.CocktailsListFragment;
import com.example.appcourstrois.MainActivity;
import com.example.appcourstrois.R;
import com.example.appcourstrois.model.Todo;
import com.example.appcourstrois.model.Drinks;
import com.example.appcourstrois.webservices.WebServicesInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoriesListFragment extends Fragment implements CategoriesListClickListener {

    private RecyclerView categoryListRecyclerView;
    private RecyclerView.Adapter drinksListAdapter;
    private RecyclerView.LayoutManager categoryListLayoutManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, null); //le 2e paramètre sera toujours null tandis que le premier représentera l’id de notre layout de fragment (ici : R.layout.fragment_random).
    }


    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {


        super.onViewCreated(view, savedInstanceState);
        categoryListRecyclerView = view.findViewById(R.id.categoryListRecyclerView);

        categoryListRecyclerView.setHasFixedSize(true);
        categoryListLayoutManager = new LinearLayoutManager(getContext());
        categoryListRecyclerView.setLayoutManager(categoryListLayoutManager);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WebServicesInterface webServicesInterface = retrofit.create(WebServicesInterface.class);

        Call<Drinks> callGetTodoByCategory = webServicesInterface.getListDrinksByCategory();

        callGetTodoByCategory.enqueue(new Callback<Drinks>() {
            @Override
            public void onResponse(Call<Drinks> call, Response<Drinks> response) {

                drinksListAdapter = new CategoriesListAdapter(response.body(), (MainActivity)getActivity()); //Ici c'est tricky :(
                categoryListRecyclerView.setAdapter(drinksListAdapter);

            }

            @Override
            public void onFailure(Call<Drinks> call, Throwable t) {
                System.out.println("Echec du chargement de Codecktail");
            }
        });



    };

    @Override
    public void onCategoryListClick(Todo todo) {

        //Tout ça c'est plus actif parce que c'est dans le mainacivity
        System.out.println(todo.getStrCategory());
        Intent drinkDetailListingActivityIntent = new Intent(getActivity(), CocktailsListFragment.class);

        String s = todo.getStrCategory();

        drinkDetailListingActivityIntent.putExtra("nameDuStrCategory", s);

        //Et tac on démarre l'activity
        startActivity(drinkDetailListingActivityIntent);

    }


}

