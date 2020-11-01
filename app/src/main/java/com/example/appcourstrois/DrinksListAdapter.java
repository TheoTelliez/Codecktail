package com.example.appcourstrois;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class DrinksListAdapter extends RecyclerView.Adapter<DrinksListAdapter.ViewHolder> {

    List<Todo> itemList;

    public DrinksListAdapter(List<Todo> itemList) {

        this.itemList = itemList;

    }

    @NonNull
    @Override
    public DrinksListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_list_cell, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DrinksListAdapter.ViewHolder holder, int position) {

        holder.drinksName.setText(itemList.get(position).getStrDrink());
        holder.drinksType.setText(itemList.get(position).getStrAlcoholic());
        Picasso.get().load(itemList.get(position).getStrDrinkThumb()).into(holder.drinksThumbs);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView drinksName;
        TextView drinksType;
        ImageView drinksThumbs;

        public ViewHolder(@NonNull View itemList) {
            super(itemList);

            drinksName = itemList.findViewById(R.id.nameListTextView);
            drinksType = itemList.findViewById(R.id.typeListTextView);
            drinksThumbs = itemList.findViewById(R.id.thumbsCellImageView);
        }
    }
}