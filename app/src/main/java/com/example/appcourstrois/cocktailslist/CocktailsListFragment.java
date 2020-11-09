package com.example.appcourstrois.cocktailslist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appcourstrois.MainActivity;
import com.example.appcourstrois.R;
import com.example.appcourstrois.categories.CategoriesListAdapter;
import com.example.appcourstrois.model.Todo;
import com.example.appcourstrois.model.Drinks;
import com.example.appcourstrois.webservices.WebServicesInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CocktailsListFragment extends Fragment implements CocktailsListClickListener {

    private RecyclerView cocktailsListRecyclerView;
    private RecyclerView.Adapter cocktailsListAdapter;
    private RecyclerView.LayoutManager cocktailsListLayoutManager;

    private String strCategory;

    public CocktailsListFragment(String strCategory) {
        this.strCategory = strCategory;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.cocktailslist_list, null); //le 2e paramètre sera toujours null tandis que le premier représentera l’id de notre layout de fragment (ici : R.layout.fragment_random).
    }


    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {


        super.onViewCreated(view, savedInstanceState);
        cocktailsListRecyclerView = view.findViewById(R.id.cocktailsListDetailRecyclerView);

        cocktailsListRecyclerView.setHasFixedSize(true);
        cocktailsListLayoutManager = new LinearLayoutManager(getContext());
        cocktailsListRecyclerView.setLayoutManager(cocktailsListLayoutManager);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WebServicesInterface webServicesInterface = retrofit.create(WebServicesInterface.class);

        //String stringFromIntent = getActivity().getIntent().getExtras().getString("nameDuStrCategory");



        final Call<Drinks> callGetTodoByListOfDrinksByCategories = webServicesInterface.getListDrinksByCategoryCustom(this.strCategory);

        callGetTodoByListOfDrinksByCategories.enqueue(new Callback<Drinks>() {
            @Override
            public void onResponse(Call<Drinks> call, Response<Drinks> response) {

                System.out.println("Test et ça marche");

                //System.out.println(response.body().getTodo().get(0).getStrAlcoholic());

                cocktailsListAdapter = new CocktailsListAdapter(response.body(), (MainActivity)getActivity()); //Ici c'est tricky :(
                cocktailsListRecyclerView.setAdapter(cocktailsListAdapter);


            }

            @Override
            public void onFailure(Call<Drinks> call, Throwable t) {
                System.out.println("Echec du chargement de Codecktail");
            }
        });



    };


    @Override
    public void onCocktailListClick(Todo todo) {

        System.out.println(todo.getIdDrink());

    }


}

