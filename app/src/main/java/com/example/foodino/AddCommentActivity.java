package com.example.foodino;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.foodino.customs.RatingSelector;
import com.example.foodino.models.InsertResponse;
import com.example.foodino.utils.Constants;
import com.example.foodino.utils.JsonPlaceHolderApi;
import com.example.foodino.utils.PersianDate;
import com.example.foodino.utils.RetrofitSetting;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.foodino.fragments.CommentFragment.EXTRA_RESTAURANT_ID;


public class AddCommentActivity extends AppCompatActivity implements View.OnClickListener {

    EditText commentEt;
    RatingSelector ratingSelector;
    Button postBtn;

    //set using shared preference
    int userId =1;
    int restaurantId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);

        init();

        postBtn.setOnClickListener(this);

        //getting id of clicked restaurant object from commentFragment using intent
        restaurantId = getIntent().getIntExtra(EXTRA_RESTAURANT_ID,0);

    }

    private void init() {

        commentEt = findViewById(R.id.comment_et);
        ratingSelector = findViewById(R.id.rating_selector);
        postBtn = findViewById(R.id.post_btn);
    }

    @Override
    public void onClick(View v) {

        if(!commentEt.getText().toString().trim().matches("") && ratingSelector.getSelected() != RatingSelector.NOT_SELECTED)
            addComment();
        else if(ratingSelector.getSelected() == RatingSelector.NOT_SELECTED)
            Constants.showToast(this,R.string.fill_rate_message);
        else if(commentEt.getText().toString().trim().matches(""))
            Constants.showToast(this,R.string.fill_comment_message);
    }

    //post comment to server
    private void addComment() {

        String date = PersianDate.getCurrentShamsidate();

        RetrofitSetting retrofit = new RetrofitSetting();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.getJsonPlaceHolderApi();
        Call<InsertResponse> call = jsonPlaceHolderApi.addComment(commentEt.getText().toString().trim(), date, ratingSelector.getSelected() + 1, restaurantId, userId);
        call.enqueue(new Callback<InsertResponse>() {
            @Override
            public void onResponse(Call<InsertResponse> call, Response<InsertResponse> response) {
                if (!response.isSuccessful()) {
                    Constants.showToast(AddCommentActivity.this,R.string.connection_error_message);
                    return;
                }
                InsertResponse insertResponse = response.body();
                if(insertResponse.getSuccess()!= "0") {
                    Constants.showToast(AddCommentActivity.this,R.string.successful_insert_message);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<InsertResponse> call, Throwable t) {
                Constants.showToast(AddCommentActivity.this,R.string.connection_error_message);
            }
        });
    }
}
