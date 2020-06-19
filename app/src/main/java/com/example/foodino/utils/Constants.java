package com.example.foodino.utils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodino.R;

public class Constants {

    public static final String BASE_URL = "http://192.168.1.7/api/";
    public static final String PICTURE_URL = "http://192.168.1.7/pic/";

    public static final String[] categoryImages = {"category/restooran.jpg" , "category/fastfood.jpg" , "category/coffee.jpg"
    , "category/bastani.jpg" , "category/tahiye.jpg" , "category/kabab.jpg"};

    public static void showToast(Context context, int messageId) {

        //setting custom toast
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup)((Activity)context).findViewById(R.id.custom_toast_container));

        TextView text = layout.findViewById(R.id.toast_text);
        text.setText(messageId);
        text.setTextSize(16);

        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}
