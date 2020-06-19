package com.example.foodino;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.foodino.fragments.HomeFragment;
import com.example.foodino.fragments.ProfileFragment;
import com.example.foodino.fragments.SearchFragment;
import com.example.foodino.utils.Constants;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;

    SearchFragment searchFragment;
    HomeFragment homeFragment;
    ProfileFragment profileFragment;

    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        init();
        setFragment(homeFragment);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.navigation_home:
                    setFragment(homeFragment);
                    return true;
                case R.id.navigation_profile:
                    setFragment(profileFragment);
                    return true;
                case R.id.navigation_search:
                    setFragment(searchFragment);
                    return true;
            }
            return false;
        }
    });
}

    private void init() {

        frameLayout = findViewById(R.id.container);
        bottomNavigationView = findViewById(R.id.navigationView);

        homeFragment = new HomeFragment();
        searchFragment = new SearchFragment();
        profileFragment = new ProfileFragment();
    }

    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {

        //Exit application management
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        doubleBackToExitPressedOnce = true;
        Constants.showToast(this,R.string.exit_message);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        },2000);
    }

}
