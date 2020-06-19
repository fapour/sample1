package com.example.foodino;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodino.customs.SnackbarHelper;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class SplashActivity extends AppCompatActivity {

    public static final long FIRST_DELAY = 3000;

    boolean firstTime = true;

    TextView tvSplash;
    ImageView imgSplash;
    CoordinatorLayout splashLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        init();
        startAnimations();
        startThread();
    }

    private void init() {

        splashLayout = findViewById(R.id.splashLayout);
        tvSplash = findViewById(R.id.splashText);
        imgSplash = findViewById(R.id.splashImage);
    }

    private void startAnimations() {

        //setting font of textView
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/BTitrBd_0.ttf");
        tvSplash.setTypeface(tf);

        //setting animation of widgets
        Animation iAnim = AnimationUtils.loadAnimation(getBaseContext(), R.anim.img_anim);
        Animation tAnim = AnimationUtils.loadAnimation(getBaseContext(), R.anim.tv_anim);

        imgSplash.startAnimation(iAnim);
        tvSplash.startAnimation(tAnim);
    }


    //checks internet connection and if it is not connected => retry using snackBar
    private void startThread() {

        Thread thread = new Thread(){
            public void run(){
                try{
                    if(firstTime) {
                        sleep(FIRST_DELAY);
                    }else{
                        sleep(FIRST_DELAY/2);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();

                } finally {
                    if(!checkInternetConnection()){
                        Snackbar snackbar = Snackbar.make(splashLayout , R.string.internet_error ,Snackbar.LENGTH_INDEFINITE)
                                .setAction(R.string.retry_internet, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        interrupt();
                                        firstTime = false;
                                        startThread();
                                    }
                                });
                        SnackbarHelper.configSnackBar(getApplicationContext() ,snackbar);
                        snackbar.show();

                    }
                    else{
                        Intent intent = new Intent(SplashActivity.this,BottomNavigationActivity.class);
                        startActivity(intent);
                        finish();

                    }
                }
            }
        };
        thread.start();
    }

    private Boolean checkInternetConnection() {

        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            try {

                URL url = new URL("http://www.google.com");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(3000);
                connection.connect();
                if (connection.getResponseCode() == 200) {
                    return true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
