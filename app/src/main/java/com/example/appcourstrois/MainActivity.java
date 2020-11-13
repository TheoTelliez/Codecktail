package com.example.appcourstrois;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.appcourstrois.categories.CategoriesListFragment;
import com.example.appcourstrois.categories.CategoriesListClickListener;
import com.example.appcourstrois.cocktail.CocktailsSearchListFragment;
import com.example.appcourstrois.cocktail.LookupFragment;
import com.example.appcourstrois.cocktail.RandomFragment;
import com.example.appcourstrois.cocktail.SearchFragment;
import com.example.appcourstrois.cocktail.SearchListClickListener;
import com.example.appcourstrois.cocktailslist.CocktailsListClickListener;
import com.example.appcourstrois.cocktailslist.CocktailsListFragment;
import com.example.appcourstrois.model.Todo;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, CategoriesListClickListener, CocktailsListClickListener, SearchListClickListener {


    Fragment randomFragment;
    Fragment listFragment;
    Fragment searchFragment;
    BottomNavigationView navigationView;

    private CategoriesListClickListener categoriesListClickListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        randomFragment = new RandomFragment();
        listFragment = new CategoriesListFragment();
        searchFragment = new SearchFragment(this);

        loadFragment(randomFragment); //sert à dire que le fragment par défaut quand on lance l'appli c'est celui de texte

        navigationView = findViewById(R.id.bottomNavigationView);
        navigationView.setOnNavigationItemSelectedListener(this);

    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment) //id du container
                    .commit();
            return true;
        }
        return false;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.action_random:
                fragment = randomFragment;
                break;
            case R.id.action_list:
                fragment = listFragment;
                break;
            case R.id.action_search:
                fragment = searchFragment;
                break;
            default: //si jamais il ne fait ni l'un ni l'autre alors on fait un truc quand même par defaut
                break;
        }
        return loadFragment(fragment); //ca c'est pour savoir si ca s'est bien passé, il retourne bien normalement
    }


    @Override
    public void onCategoryListClick(Todo todo) {
        loadFragment(new CocktailsListFragment(todo.getStrCategory()));
        System.out.println(todo.getStrCategory()); //C'est qui qui faut passer le param
        System.out.println(todo.getStrAlcoholic()); //C'est qui que ca marche pas
    }


    @Override
    public void onCocktailListClick(Todo todo) {
        loadFragment(new LookupFragment(todo.getIdDrink()));
        System.out.println(todo.getIdDrink());
    }


    @Override
    public void onSearchListClick(String s) {
        String motcle = s;
        System.out.println();
        loadFragment(new CocktailsSearchListFragment(motcle));
    }


}