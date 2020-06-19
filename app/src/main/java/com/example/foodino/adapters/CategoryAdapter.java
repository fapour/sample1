package com.example.foodino.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodino.R;
import com.example.foodino.customs.GlobalTextView;
import com.example.foodino.utils.Constants;
import com.squareup.picasso.Picasso;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    String[] categories;
    Context context;
    boolean beInHomeFragment = false;

    public CategoryAdapter(String[] categories, Context context, boolean beInHomeFragment) {
        this.categories = categories;
        this.context = context;
        this.beInHomeFragment = beInHomeFragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list_item,parent,false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if(beInHomeFragment){

            //setting width of every item based on screen width
            DisplayMetrics displaymetrics = new DisplayMetrics();
            ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

            //deleting paddings and margins from screen width
            int deviceWidth = (displaymetrics.widthPixels-30) / 2;
            holder.cardView.getLayoutParams().width = deviceWidth;
        }
        holder.tv.setText(categories[position]);
        Picasso.get().load(Constants.PICTURE_URL + Constants.categoryImages[position]).error(R.drawable.preloadimg).into(holder.img);
        holder.tv.setTextColor(Color.WHITE);
        holder.tv.setBackgroundColor(Color.BLACK);

    }

    @Override
    public int getItemCount() {
        return categories.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        GlobalTextView tv;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.category_card);
            tv = itemView.findViewById(R.id.category_tv);
            img = itemView.findViewById(R.id.category_img);
        }
    }
}
