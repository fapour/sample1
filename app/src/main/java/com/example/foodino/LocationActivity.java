package com.example.foodino;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.foodino.customs.GlobalTextView;
import com.example.foodino.fragments.CommentFragment;
import com.example.foodino.fragments.MenuFragment;
import com.example.foodino.fragments.RestaurantInformationFragment;
import com.example.foodino.models.Restaurant;
import com.example.foodino.utils.Constants;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.example.foodino.fragments.HomeFragment.EXTRA_CURRENT_RESTAURANT;

public class LocationActivity extends AppCompatActivity implements CommentFragment.onLoadRatingListener{

    ViewPager viewPager;
    TabLayout tabs;
    ImageView informationImg;
    ImageView restaurantImg;
    GlobalTextView restaurantTitle;
    TextView ratingText;
    RatingBar ratingBar;
    LinearLayout screenProgressBar;

    PagerAdapter pagerAdapter;
    Restaurant restaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        init();

        //getting clicked restaurant object from homeFragment using intent
        restaurant = (Restaurant) getIntent().getSerializableExtra(EXTRA_CURRENT_RESTAURANT);

        setRestaurantInformation();
        setupViewPager();
        setupTabs();

        //show more information about restaurant
        informationImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moreInformation();
            }
        });

        //loading...
        screenProgressBar.setVisibility(View.VISIBLE);
    }

    private void init() {

        viewPager = findViewById(R.id.location_vp);
        tabs = findViewById(R.id.tabs);
        restaurantImg = findViewById(R.id.restaurant_image);
        informationImg = findViewById(R.id.inf_img);
        restaurantTitle = findViewById(R.id.title_tv);
        ratingBar = findViewById(R.id.rating);
        ratingText = findViewById(R.id.rating_tv);
        screenProgressBar = findViewById(R.id.screen_progress_bar);

    }

    private void setRestaurantInformation() {

        restaurantTitle.setText(restaurant.getName());
        setRestaurantImage();
    }

    private void setRestaurantImage() {

        Picasso picasso = Picasso.get();
        picasso.setLoggingEnabled(true);
        if(restaurant.getImage() != null)
            picasso.load(restaurant.getImage()).error(R.drawable.food).into(restaurantImg);
        else
            restaurantTitle.setTextColor(Color.BLACK);
    }

    private void setupTabs() {

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tabs.setupWithViewPager(viewPager);
        tabs.setTabMode(TabLayout.MODE_FIXED);
    }

    public void setupViewPager() {

        viewPager.setRotationY(180);

        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        prepareSlides();

    }

    private void prepareSlides() {

        String[] titles = getResources().getStringArray(R.array.tab_titles);

        CommentFragment commentsFragment = CommentFragment.newInstance(restaurant.getId());
        MenuFragment menuFragment = new MenuFragment();
        pagerAdapter.addFragment(commentsFragment, titles[0]);
        pagerAdapter.addFragment(menuFragment, titles[1]);

    }

    private void moreInformation() {
        RestaurantInformationFragment informationFragment = RestaurantInformationFragment.newInstance(restaurant.getAddress(), restaurant.getPhone(), restaurant.getWebsite());
        informationFragment.show(getSupportFragmentManager(),"restaurantInf");
    }

    //get rate from commentFragment using interface
    @Override
    public void loadRating(float rate) {

        screenProgressBar.setVisibility(View.INVISIBLE);

        if(rate == 0)
            Constants.showToast(this,R.string.connection_error_message);
        else{
            String roundRate = String.format("%.2f", rate);
            ratingText.setText(roundRate);
            ratingBar.setRating(rate);
        }
    }

    public class PagerAdapter extends FragmentPagerAdapter{
        List<Fragment> fragments;
        List<String> tabTitles;

        public PagerAdapter(FragmentManager fm) {
            super(fm);
            fragments = new ArrayList<>();
            tabTitles = new ArrayList<>();
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        public void addFragment(Fragment fragment, String tabTitle){
            fragments.add(fragment);
            tabTitles.add(tabTitle);
            notifyDataSetChanged();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles.get(position);
        }
    }
}