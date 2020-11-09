package com.example.appcourstrois.categories;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appcourstrois.R;
import com.example.appcourstrois.model.Drinks;


public class CategoriesListAdapter extends RecyclerView.Adapter<CategoriesListAdapter.ViewHolder> {

   Drinks drink;
   private CategoriesListClickListener categoriesListClickListener;


   public CategoriesListAdapter(Drinks drink, CategoriesListClickListener categoriesListClickListener){
       this.drink = drink; //drink permet de savoir toutes les données dont on va se servir
       this.categoriesListClickListener = categoriesListClickListener;

   }

    @NonNull
    @Override
    public CategoriesListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_list_cell, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesListAdapter.ViewHolder holder, final int position) {
        holder.categoryNameCell.setText(drink.getTodo().get(position).getStrCategory());
        holder.categoryNameCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoriesListClickListener.onCategoryListClick(drink.getTodo().get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return drink.getTodo().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView categoryNameCell;

        public ViewHolder(@NonNull View itemList) {
            super(itemList);

            categoryNameCell = itemList.findViewById(R.id.categoryNameCell);

        }
    }
}