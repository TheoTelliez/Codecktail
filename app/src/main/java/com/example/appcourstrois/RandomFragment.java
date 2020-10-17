package com.example.appcourstrois;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class RandomFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        //C'est parti pour le random:

        View view = inflater.inflate(R.layout.fragment_random, container, false);

        final TextView detail_cocktail_title, detail_alcoholic_text, detail_glass_text, detail_instructions_text,

                detail_ingredient_1, detail_ingredient_2, detail_ingredient_3, detail_ingredient_4,
                detail_ingredient_5, detail_ingredient_6, detail_ingredient_7, detail_ingredient_8,

                text_ingredient_1, text_ingredient_2, text_ingredient_3, text_ingredient_4,
                text_ingredient_5, text_ingredient_6, text_ingredient_7, text_ingredient_8,

                mesure_ingredient_1, mesure_ingredient_2, mesure_ingredient_3, mesure_ingredient_4,
                mesure_ingredient_5, mesure_ingredient_6, mesure_ingredient_7, mesure_ingredient_8,

                detail_category

        ; //Max 8 car il n'y jamais + de 8 ingrédients dans le json (et si c'est le cas un jour beh faudra se débrouiller et inventer une nouvelle recette par soi même --"


        final ImageView detail_cocktail_image;
        //final LinearLayout detail_ingredients_list;


        //On déclare les valeurs
        detail_cocktail_title = view.findViewById(R.id.detail_cocktail_title);
        detail_alcoholic_text = view.findViewById(R.id.detail_alcoholic_text);
        detail_glass_text = view.findViewById(R.id.detail_glass_text);
        detail_instructions_text = view.findViewById(R.id.detail_instructions_text);
        detail_cocktail_image = view.findViewById(R.id.detail_cocktail_image);


        detail_ingredient_1 = view.findViewById(R.id.detail_ingredient_1);
        detail_ingredient_2 = view.findViewById(R.id.detail_ingredient_2);
        detail_ingredient_3 = view.findViewById(R.id.detail_ingredient_3);
        detail_ingredient_4 = view.findViewById(R.id.detail_ingredient_4);
        detail_ingredient_5 = view.findViewById(R.id.detail_ingredient_5);
        detail_ingredient_6 = view.findViewById(R.id.detail_ingredient_6);
        detail_ingredient_7 = view.findViewById(R.id.detail_ingredient_7);
        detail_ingredient_8 = view.findViewById(R.id.detail_ingredient_8);

        text_ingredient_1 = view.findViewById(R.id.text_ingredient_1);
        text_ingredient_2 = view.findViewById(R.id.text_ingredient_2);
        text_ingredient_3 = view.findViewById(R.id.text_ingredient_3);
        text_ingredient_4 = view.findViewById(R.id.text_ingredient_4);
        text_ingredient_5 = view.findViewById(R.id.text_ingredient_5);
        text_ingredient_6 = view.findViewById(R.id.text_ingredient_6);
        text_ingredient_7 = view.findViewById(R.id.text_ingredient_7);
        text_ingredient_8 = view.findViewById(R.id.text_ingredient_8);

        mesure_ingredient_1 = view.findViewById(R.id.mesure_ingredient_1);
        mesure_ingredient_2 = view.findViewById(R.id.mesure_ingredient_2);
        mesure_ingredient_3 = view.findViewById(R.id.mesure_ingredient_3);
        mesure_ingredient_4 = view.findViewById(R.id.mesure_ingredient_4);
        mesure_ingredient_5 = view.findViewById(R.id.mesure_ingredient_5);
        mesure_ingredient_6 = view.findViewById(R.id.mesure_ingredient_6);
        mesure_ingredient_7 = view.findViewById(R.id.mesure_ingredient_7);
        mesure_ingredient_8 = view.findViewById(R.id.mesure_ingredient_8);

        detail_category = view.findViewById(R.id.detail_category);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WebServicesInterface webServicesInterface = retrofit.create(WebServicesInterface.class);

        Call<Drinks> callGetTodoByRandom = webServicesInterface.getTodoByRandom();

        callGetTodoByRandom.enqueue(new Callback<Drinks>() {
            @Override
            public void onResponse(Call<Drinks> call, Response<Drinks> response) {
                final Drinks drinks = response.body();
                System.out.println("nom : " + drinks.getTodo().get(0).getStrDrink() + " alcoholic : " + drinks.getTodo().get(0).getStrAlcoholic() + " ingrédient 1 : " + drinks.getTodo().get(0).getStrIngredient1() + " ingrédient 2 : " + drinks.getTodo().get(0).getStrIngredient2() + " ingrédient 3 : " + drinks.getTodo().get(0).getStrIngredient3() + " ingrédient 4 : " + drinks.getTodo().get(0).getStrIngredient4() + " mesure 1 : " + drinks.getTodo().get(0).getStrMeasure1() + " mesure 2 : " + drinks.getTodo().get(0).getStrMeasure2() + " mesure 3 : " + drinks.getTodo().get(0).getStrMeasure3() + " mesure 4 : " + drinks.getTodo().get(0).getStrMeasure4() + " instruction : " + drinks.getTodo().get(0).getStrInstructions());

                detail_cocktail_title.setText(drinks.getTodo().get(0).getStrDrink());
                //Set variables en vue
                detail_cocktail_title.setText(response.body().getTodo().get(0).getStrDrink());

                if (response.body().getTodo().get(0).getStrAlcoholic().equals("Non alcoholic")){ //Def vert ou rouge en fonction de alcoholic
                    detail_alcoholic_text.setTextColor(getResources().getColor(R.color.green));
                    detail_alcoholic_text.setText("✓ " + response.body().getTodo().get(0).getStrAlcoholic());
                    //detail_alcoholic_text.setText("Good" + response.body().getTodo().get(0).getStrAlcoholic());
                }else {
                    detail_alcoholic_text.setTextColor(getResources().getColor(R.color.red));
                    detail_alcoholic_text.setText("⚠ " + response.body().getTodo().get(0).getStrAlcoholic());
                    //detail_alcoholic_text.setText("Pas good" + response.body().getTodo().get(0).getStrAlcoholic());
                }


                detail_glass_text.setText(response.body().getTodo().get(0).getStrGlass());

                detail_instructions_text.setText(response.body().getTodo().get(0).getStrInstructions());

                Picasso.get().load(response.body().getTodo().get(0).getStrDrinkThumb()).into(detail_cocktail_image);


                detail_ingredient_1.setText(response.body().getTodo().get(0).getStrIngredient1());
                detail_ingredient_2.setText(response.body().getTodo().get(0).getStrIngredient2());
                detail_ingredient_3.setText(response.body().getTodo().get(0).getStrIngredient3());
                detail_ingredient_4.setText(response.body().getTodo().get(0).getStrIngredient4());
                detail_ingredient_5.setText(response.body().getTodo().get(0).getStrIngredient5());
                detail_ingredient_6.setText(response.body().getTodo().get(0).getStrIngredient6());
                detail_ingredient_7.setText(response.body().getTodo().get(0).getStrIngredient7());
                detail_ingredient_8.setText(response.body().getTodo().get(0).getStrIngredient8());

                mesure_ingredient_1.setText(response.body().getTodo().get(0).getStrMeasure1());
                mesure_ingredient_2.setText(response.body().getTodo().get(0).getStrMeasure2());
                mesure_ingredient_3.setText(response.body().getTodo().get(0).getStrMeasure3());
                mesure_ingredient_4.setText(response.body().getTodo().get(0).getStrMeasure4());
                mesure_ingredient_5.setText(response.body().getTodo().get(0).getStrMeasure5());
                mesure_ingredient_6.setText(response.body().getTodo().get(0).getStrMeasure6());
                mesure_ingredient_7.setText(response.body().getTodo().get(0).getStrMeasure7());
                mesure_ingredient_8.setText(response.body().getTodo().get(0).getStrMeasure8());


                if(response.body().getTodo().get(0).getStrIngredient1() == null || response.body().getTodo().get(0).getStrIngredient1().trim().equals("")){ //pas == "" sinon ca ne fonctionne pas
                    text_ingredient_1.setVisibility(View.INVISIBLE); //on cache le texte
                    mesure_ingredient_1.setVisibility(View.INVISIBLE);  //et la mesure forcément histoire de ne pas avoir d'espaces dans le vent
                }
                if(response.body().getTodo().get(0).getStrIngredient2() == null || response.body().getTodo().get(0).getStrIngredient2().trim().equals("")){ //ne pas oublier trim sinon ca detecte pas
                    text_ingredient_2.setVisibility(View.INVISIBLE);
                    mesure_ingredient_2.setVisibility(View.INVISIBLE);
                }
                if(response.body().getTodo().get(0).getStrIngredient3() == null || response.body().getTodo().get(0).getStrIngredient3().trim().equals("")){ //ya des "" dans les valeurs
                    text_ingredient_3.setVisibility(View.INVISIBLE);
                    mesure_ingredient_3.setVisibility(View.INVISIBLE);
                }
                if(response.body().getTodo().get(0).getStrIngredient4() == null || response.body().getTodo().get(0).getStrIngredient4().trim().equals("")){ //ya des null dans les valeurs
                    text_ingredient_4.setVisibility(View.INVISIBLE);
                    mesure_ingredient_4.setVisibility(View.INVISIBLE);
                }
                if(response.body().getTodo().get(0).getStrIngredient5() == null || response.body().getTodo().get(0).getStrIngredient5().trim().equals("")){ //ducoup faut faire les deux vérifs
                    text_ingredient_5.setVisibility(View.INVISIBLE);
                    mesure_ingredient_5.setVisibility(View.INVISIBLE);
                }
                if(response.body().getTodo().get(0).getStrIngredient6() == null || response.body().getTodo().get(0).getStrIngredient6().trim().equals("")){
                    text_ingredient_6.setVisibility(View.INVISIBLE);
                    mesure_ingredient_6.setVisibility(View.INVISIBLE);
                }
                if(response.body().getTodo().get(0).getStrIngredient7() == null || response.body().getTodo().get(0).getStrIngredient7().trim().equals("")){
                    text_ingredient_7.setVisibility(View.INVISIBLE);
                    mesure_ingredient_7.setVisibility(View.INVISIBLE);
                }
                if(response.body().getTodo().get(0).getStrIngredient8() == null || response.body().getTodo().get(0).getStrIngredient8().trim().equals("")){
                    text_ingredient_8.setVisibility(View.INVISIBLE);
                    mesure_ingredient_8.setVisibility(View.INVISIBLE);
                }

                detail_category.setText(response.body().getTodo().get(0).getStrCategory());





            }

            @Override
            public void onFailure(Call<Drinks> call, Throwable t) {
                System.out.println("Echec du chargement de Codecktail");
            }
        });
        return view;
    }








    }







