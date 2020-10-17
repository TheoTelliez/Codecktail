package com.example.appcourstrois;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    Fragment randomFragment;
    Fragment listFragment;
    Fragment searchFragment;
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        randomFragment = new RandomFragment();
        listFragment = new ListFragment();
        searchFragment = new SearchFragment();

        loadFragment(randomFragment); //sert à dire que le fragment par défaut quand on lance l'appli c'est celui de texte

        navigationView = findViewById(R.id.bottomNavigationView);
        navigationView.setOnNavigationItemSelectedListener(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build(); //Toujours la même structure

        WebServicesInterface webServicesInterface = retrofit.create(WebServicesInterface.class);

        Call<Drinks> callGetTodoByRandom = webServicesInterface.getTodoByRandom();

        callGetTodoByRandom.enqueue(new Callback<Drinks>() {  //Callback = mettre dans une file d'attente pour faire 1 puis 2 puis 3 etc...
            @Override
            public void onResponse(Call<Drinks> call, Response<Drinks> response) {//Appel si ca répond 200 / 201 / tout code qui permet la validité

                System.out.println(response.body().getTodo().get(0).getStrDrink() + response.body().getTodo().get(0).getStrAlcoholic());

                //Def variables
                TextView detail_cocktail_title;
                TextView detail_alcoholic_text;
                TextView detail_glass_text;
                TextView detail_instructions_text;
                ImageView detail_cocktail_image;

                //Def findview
                detail_cocktail_title = findViewById(R.id.detail_cocktail_title);
                detail_alcoholic_text = findViewById(R.id.detail_alcoholic_text);
                detail_glass_text = findViewById(R.id.detail_glass_text);
                detail_instructions_text = findViewById(R.id.detail_instructions_text);
                detail_cocktail_image = findViewById(R.id.detail_cocktail_image);


                //Set variables en vue
                detail_cocktail_title.setText(response.body().getTodo().get(0).getStrDrink());

                detail_alcoholic_text.setText(response.body().getTodo().get(0).getStrAlcoholic());

                if (response.body().getTodo().get(0).getStrAlcoholic().equals("Non alcoholic")){ //Def vert ou rouge en fonction de alcoholic
                    detail_alcoholic_text.setTextColor(getResources().getColor(R.color.green));
                }else {
                    detail_alcoholic_text.setTextColor(getResources().getColor(R.color.red));
                }


                detail_glass_text.setText(response.body().getTodo().get(0).getStrGlass());
                detail_instructions_text.setText(response.body().getTodo().get(0).getStrInstructions());

                Picasso.get().load(response.body().getTodo().get(0).getStrDrinkThumb()).into(detail_cocktail_image);


            }

            @Override
            public void onFailure(Call<Drinks> call, Throwable t) { //Appel si l'appel fail
                System.out.println("Fail !");
            }
        });

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





}