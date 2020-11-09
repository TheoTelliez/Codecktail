package com.example.appcourstrois.cocktailslist;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appcourstrois.R;
import com.example.appcourstrois.model.Drinks;
import com.example.appcourstrois.model.Todo;
import com.squareup.picasso.Picasso;

import java.util.List;


public class CocktailsListAdapter extends RecyclerView.Adapter<CocktailsListAdapter.ViewHolder> {

    Drinks drink;
    private CocktailsListClickListener cocktailslistListClickListener;
    ImageView categoryImageCellDetail;



    public CocktailsListAdapter(Drinks drink, CocktailsListClickListener cocktailslistListClickListener){
        this.drink = drink; //drink permet de savoir toutes les données dont on va se servir
        this.cocktailslistListClickListener = cocktailslistListClickListener;

    }

    @NonNull
    @Override
    public CocktailsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cocktailslist_list_cell, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull CocktailsListAdapter.ViewHolder holder, final int position) {


        holder.categoryNameCellDetail.setText(drink.getTodo().get(position).getStrDrink());

        String StringAmc = String.valueOf(drink.getTodo().get(position).getIdDrink());

        holder.categoryNameCellDetailAlc.setText(StringAmc); //ICI COMMENT SET LA VALEUR AVEC STR ALC ?



        Picasso.get().load(drink.getTodo().get(position).getStrDrinkThumb()).into(categoryImageCellDetail);


        holder.categoryNameCellDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cocktailslistListClickListener.onCocktailListClick(drink.getTodo().get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return drink.getTodo().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView categoryNameCellDetail, categoryNameCellDetailAlc;



        public ViewHolder(@NonNull View itemList) {
            super(itemList);

            categoryNameCellDetail = itemList.findViewById(R.id.categoryNameCellDetail);
            categoryNameCellDetailAlc = itemList.findViewById(R.id.categoryNameCellDetailAlc);
            categoryImageCellDetail = itemList.findViewById(R.id.categoryImageCellDetail);

        }
    }
}