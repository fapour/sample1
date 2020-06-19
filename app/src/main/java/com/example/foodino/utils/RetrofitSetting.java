package com.example.foodino.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.Timer;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSetting {

    Retrofit retrofit;
    JsonPlaceHolderApi jsonPlaceHolderApi;

    public RetrofitSetting() {

        Gson gson = new GsonBuilder().setLenient().create();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override public void log(String message) {
                Log.d("MyTAG", "OkHttp: " + message);
            }
        });
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new ResponseInterceptor()).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
    }


    public Retrofit getRetrofit() {
        return retrofit;
    }

    public JsonPlaceHolderApi getJsonPlaceHolderApi() {
        return jsonPlaceHolderApi;
    }

    class ResponseInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Response response = chain.proceed(chain.request());
            Response modif = response.newBuilder()
                    .addHeader("Content-Type", "application/json; charset=UTF-8")
                    .build();

            return modif;
        }
    }
}
