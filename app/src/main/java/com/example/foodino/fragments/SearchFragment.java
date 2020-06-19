package com.example.foodino.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodino.R;
import com.example.foodino.customs.SpaceItemDecoration;
import com.example.foodino.adapters.CategoryAdapter;
import com.example.foodino.models.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    RecyclerView categoryRv;

    CategoryAdapter categoryAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_search, container, false);

        categoryRv = root.findViewById(R.id.category_rv);
        final SearchView searchView = root.findViewById(R.id.search);

        searchView.onActionViewExpanded();
        searchView.setIconifiedByDefault(false);
        if(!searchView.isFocused()) {
            searchView.clearFocus();
        }

        showData();

        return root;
    }

    private void showData() {
        //show categories
        categoryAdapter = new CategoryAdapter(getResources().getStringArray(R.array.food_categories),getContext(),false);
        categoryRv.setItemAnimator(new DefaultItemAnimator());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        categoryRv.setLayoutManager(gridLayoutManager);
        categoryRv.addItemDecoration(new SpaceItemDecoration(1));
        categoryRv.setAdapter(categoryAdapter);
    }
}
