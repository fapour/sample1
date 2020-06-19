package com.example.foodino.utils;

import com.example.foodino.models.Comment;
import com.example.foodino.models.InsertResponse;
import com.example.foodino.models.Restaurant;
import com.example.foodino.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {

    @Headers({
            "content-Type: application/json; charset=UTF-8"
    })

    @GET("restaurant.php")
    Call<List<Restaurant>> getRestaurants();

    @GET("comment.php")
    Call<List<Comment>> getComments(@Query("restaurantid") int userId);

    @FormUrlEncoded
    @POST("login.php")
    Call<User> login(@Header("Cookie") String userCookie, @Field("phone") String phone, @Field("password") String password);

    @FormUrlEncoded
    @POST("addcomment.php")
    Call<InsertResponse> addComment(@Field("text") String text
            , @Field("date") String date
            , @Field("rate") int rate
            , @Field("restaurantid") int restaurantId
            , @Field("userid") int userId);
}

