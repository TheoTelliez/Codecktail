package com.example.appcourstrois.cocktail;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.appcourstrois.MainActivity;
import com.example.appcourstrois.R;
import com.example.appcourstrois.categories.CategoriesListClickListener;
import com.example.appcourstrois.cocktailslist.CocktailsListAdapter;
import com.example.appcourstrois.cocktailslist.CocktailsListFragment;
import com.example.appcourstrois.model.Drinks;
import com.example.appcourstrois.webservices.WebServicesInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchFragment extends Fragment {

    private SearchListClickListener searchListClickListener;

    public SearchFragment(SearchListClickListener searchListClickListener) {
        this.searchListClickListener = searchListClickListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_search, container, false);

        final EditText inputRechercher = (EditText) view.findViewById(R.id.inputRechercher);
        Button btRechercher = view.findViewById(R.id.btRechercher);
        btRechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String s = inputRechercher.getText().toString();
                System.out.println(s);
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                WebServicesInterface webServicesInterface = retrofit.create(WebServicesInterface.class);

                final Call<Drinks> callGetTotoByName = webServicesInterface.getTodoByName(s);

                callGetTotoByName.enqueue(new Callback<Drinks>() {
                    @Override
                    public void onResponse(Call<Drinks> call, Response<Drinks> response) {
                        //System.out.println("Test et ça marche bien");
                        searchListClickListener.onSearchListClick(s);
                        inputRechercher.getText().clear();
                    }

                    @Override
                    public void onFailure(Call<Drinks> call, Throwable t) {
                        System.out.println("Echec du chargement de Codecktail");
                    }
                });
            }
        });

        return view;
    }

}
