package com.example.foodino.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodino.LocationActivity;
import com.example.foodino.R;
import com.example.foodino.customs.SpaceItemDecoration;
import com.example.foodino.adapters.CategoryAdapter;
import com.example.foodino.models.Restaurant;
import com.example.foodino.adapters.RestaurantAdapter;
import com.example.foodino.utils.Constants;
import com.example.foodino.utils.JsonPlaceHolderApi;
import com.example.foodino.utils.RetrofitSetting;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements RestaurantAdapter.OnItemClickListener, View.OnClickListener {

    public static final String EXTRA_CURRENT_RESTAURANT = "current";

    RecyclerView bestRv;
    RecyclerView categoryRv;
    RelativeLayout layout;
    LinearLayout retryLayout;
    ProgressBar progressBar;
    Button retryBtn;

    RestaurantAdapter bestAdapter;
    CategoryAdapter categoryAdapter;
    List<Restaurant> restaurantList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        bestRv = root.findViewById(R.id.home_rv) ;
        categoryRv = root.findViewById(R.id.category_rv);
        progressBar = root.findViewById(R.id.pb);
        layout = root.findViewById(R.id.home_layout);
        retryLayout = root.findViewById(R.id.retry_layout);
        retryBtn = root.findViewById(R.id.retry_btn);
        retryBtn.setOnClickListener(this);

        loadData();

        return root;
    }

    //load restaurant lists from server
    private void loadData() {

        RetrofitSetting retrofit = new RetrofitSetting();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.getJsonPlaceHolderApi();
        Call<List<Restaurant>> call = jsonPlaceHolderApi.getRestaurants();
        call.enqueue(new Callback<List<Restaurant>>() {
            @Override
            public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {

                if(!response.isSuccessful()){
                    layout.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                    retryLayout.setVisibility(View.VISIBLE);
                    return;
                }

                List<Restaurant> restaurants = response.body();

                if(restaurantList == null)
                    restaurantList = new ArrayList<>();
                else
                    restaurantList.clear();

                for(Restaurant restaurant : restaurants) {

                    restaurantList.add(restaurant);
                }

                progressBar.setVisibility(View.INVISIBLE);
                layout.setVisibility(View.VISIBLE);

                showData();
            }

            @Override
            public void onFailure(Call<List<Restaurant>> call, Throwable t) {
                layout.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
                retryLayout.setVisibility(View.VISIBLE);
            }
        });
    }

    //show restaurant list and categories in recycler view
    private void showData() {

        categoryAdapter = new CategoryAdapter(getResources().getStringArray(R.array.food_categories),getContext(),true);
        categoryRv.setItemAnimator(new DefaultItemAnimator());
        categoryRv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true));
        categoryRv.addItemDecoration(new SpaceItemDecoration(0));
        categoryRv.setAdapter(categoryAdapter);

        bestAdapter = new RestaurantAdapter(restaurantList , this);
        bestRv.setItemAnimator(new DefaultItemAnimator());
        bestRv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true));
        bestRv.setAdapter(bestAdapter);
        bestAdapter.notifyDataSetChanged();
    }

    //onClick event of restaurant recycler view
    @Override
    public void onItemClick(int position) {

        Intent intent = new Intent(getContext(), LocationActivity.class);
        intent.putExtra(EXTRA_CURRENT_RESTAURANT, restaurantList.get(position));
        getContext().startActivity(intent);

    }

    //onClick of retry button that appears when internet is not connected or server does not response
    @Override
    public void onClick(View v) {
        retryLayout.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        loadData();
    }
}