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

    List<ModelCateg> itemList;

    public DrinksListAdapter(List<ModelCateg> itemList) {

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

        holder.categoryNameCell.setText(itemList.get(position).getStrCategory());

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView categoryNameCell;


        public ViewHolder(@NonNull View itemList){
            super(itemList);

            categoryNameCell = itemList.findViewById(R.id.categoryItem);

        }
    }
}
