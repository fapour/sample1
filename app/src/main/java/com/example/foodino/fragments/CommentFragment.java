package com.example.foodino.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodino.AddCommentActivity;
import com.example.foodino.R;
import com.example.foodino.models.Comment;
import com.example.foodino.adapters.CommentsAdapter;
import com.example.foodino.models.Restaurant;
import com.example.foodino.utils.Constants;
import com.example.foodino.utils.JsonPlaceHolderApi;
import com.example.foodino.utils.RetrofitSetting;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentFragment extends Fragment {

    public static final String EXTRA_RESTAURANT_ID = "restaurantId";

    RecyclerView commentRv;
    RatingBar ratingBar;
    TextView ratingText;

    List<Comment> commentList;
    CommentsAdapter adapter;
    int restaurantID;
    boolean isConnected = false;

    private onLoadRatingListener rateListener;

    //sending rate of every restaurant to LocationActivity using interface
    public interface onLoadRatingListener{
        void loadRating(float rate);
    }

    public static CommentFragment newInstance(int restaurantId){

        CommentFragment fragment = new CommentFragment();

        Bundle args = new Bundle();
        args.putInt("restaurantId",restaurantId);
        fragment.setArguments(args);

        return fragment;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_comment, container, false);
        view.setRotationY(180);

        commentRv = view.findViewById(R.id.comment_rv);
        ratingBar = view.findViewById(R.id.ratingBar);
        ratingText = view.findViewById(R.id.ratingText);
        final FloatingActionButton fab = view.findViewById(R.id.fab) ;

        restaurantID = getArguments().getInt("restaurantId");

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isConnected){
                    Intent intent = new Intent(getActivity(), AddCommentActivity.class);
                    intent.putExtra(EXTRA_RESTAURANT_ID,restaurantID);
                    startActivity(intent);
                }else
                    Constants.showToast(getContext(),R.string.connection_error_message);
            }
        });

        loadComments();

        return view;
    }

    //load comment lists from server
    public void loadComments()
    {
        RetrofitSetting retrofit = new RetrofitSetting();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.getJsonPlaceHolderApi();
        Call<List<Comment>> call = jsonPlaceHolderApi.getComments(restaurantID);
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if(!response.isSuccessful()){
                    rateListener.loadRating(0);
                    isConnected = false;
                    return;
                }

                isConnected = true;

                List<Comment> comments = response.body();
                if(commentList == null)
                    commentList = new ArrayList<>();
                else
                    commentList.clear();

                for(Comment comment : comments) {
                    commentList.add(comment);
                }

                showData();
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                isConnected = false;
                rateListener.loadRating(0);
            }
        });

    }

    private void showData() {

        showRate();

        adapter = new CommentsAdapter(commentList, getContext());
        commentRv.setItemAnimator(new DefaultItemAnimator());
        commentRv.setLayoutManager(new LinearLayoutManager(getContext()));
        commentRv.addItemDecoration(new DividerItemDecoration(commentRv.getContext(), DividerItemDecoration.VERTICAL));
        commentRv.setFocusable(false);
        commentRv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private void showRate() {

        float sum = 0;
        for(Comment comment : commentList){
            sum += comment.getRate();
        }
        float rate = sum/commentList.size();

        String roundRate = String.format("%.2f", rate);
        ratingText.setText(roundRate);
        ratingBar.setRating(rate);

        rateListener.loadRating(rate);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        rateListener = (onLoadRatingListener)context;
    }
}
