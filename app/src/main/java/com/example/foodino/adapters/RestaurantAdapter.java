package com.example.foodino.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodino.customs.GlobalTextView;
import com.example.foodino.R;
import com.example.foodino.models.Restaurant;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {

    List<Restaurant> restaurants;
    private final OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }


    public RestaurantAdapter(List<Restaurant> restaurants, OnItemClickListener listener) {
        this.restaurants = restaurants;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.resturant_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //set values to each row
        Restaurant restaurant = restaurants.get(position);
        holder.nameTv.setText(restaurant.getName());
        holder.addressTv.setText(restaurant.getAddress());
        if(restaurant.getImage()!=null)
            Picasso.get().load(restaurant.getImage()).error(R.drawable.food).into(holder.restaurantImg);
        else
            holder.restaurantImg.setImageResource(R.drawable.food);
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView restaurantImg;
        GlobalTextView nameTv;
        GlobalTextView addressTv;
        
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            restaurantImg = itemView.findViewById(R.id.img);
            nameTv = itemView.findViewById(R.id.tv_name);
            addressTv = itemView.findViewById(R.id.tv_address);

            //sending position of clicked row to HomeFragment using interface for managing onclick event
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int clickPosition = getAdapterPosition();
                        if (clickPosition != RecyclerView.NO_POSITION)
                            mListener.onItemClick(clickPosition);
                    }

                }
            });
        }

    }

}


