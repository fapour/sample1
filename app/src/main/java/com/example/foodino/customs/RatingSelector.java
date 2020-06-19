package com.example.foodino.customs;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.foodino.R;

/*custom view for managing user rate about restaurants that has three state:
    angry image and red color for rate:1
    weird image and yellow color for rate:2
    happy image and green color for rate:3
 */
public class RatingSelector extends LinearLayout implements View.OnClickListener {

    View rootView;

    ImageView angryImage;
    ImageView happyImage;
    ImageView weirdImage;

    TextView angryText;
    TextView happyText;
    TextView weirdText;

    public static int ANGRY = 0;
    public static int HAPPY = 1;
    public static int WEIRD = 2;
    public static int NOT_SELECTED = -1;

    private int selected = NOT_SELECTED;

    public RatingSelector(Context context) {
        super(context);
        init(context);
    }

    public RatingSelector(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {

        rootView = inflate(context, R.layout.rating_selector, this);

        angryImage = rootView.findViewById(R.id.angryImage);
        happyImage = rootView.findViewById(R.id.happyImage);
        weirdImage = rootView.findViewById(R.id.weirdImage);

        angryText = rootView.findViewById(R.id.angryText);
        happyText = rootView.findViewById(R.id.happyText);
        weirdText = rootView.findViewById(R.id.weirdText);

        angryImage.setOnClickListener(this);
        happyImage.setOnClickListener(this);
        weirdImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == angryImage.getId())
        {
            selected = ANGRY;
            angryImage.setColorFilter(ContextCompat.getColor(v.getContext(), R.color.angryColor));
            happyImage.setColorFilter(ContextCompat.getColor(v.getContext(), R.color.ratingTintColor));
            weirdImage.setColorFilter(ContextCompat.getColor(v.getContext(), R.color.ratingTintColor));

        }else if(v.getId() == happyImage.getId()) {

            selected = HAPPY;
            happyImage.setColorFilter(ContextCompat.getColor(v.getContext(), R.color.happyColor));
            angryImage.setColorFilter(ContextCompat.getColor(v.getContext(), R.color.ratingTintColor));
            weirdImage.setColorFilter(ContextCompat.getColor(v.getContext(), R.color.ratingTintColor));

        }else if(v.getId() == weirdImage.getId()) {

            selected = WEIRD;
            weirdImage.setColorFilter(ContextCompat.getColor(v.getContext(), R.color.weirdColor));
            happyImage.setColorFilter(ContextCompat.getColor(v.getContext(), R.color.ratingTintColor));
            angryImage.setColorFilter(ContextCompat.getColor(v.getContext(), R.color.ratingTintColor));

        }
    }

    public int getSelected() {
        return selected;
    }
}
