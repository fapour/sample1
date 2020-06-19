package com.example.foodino.customs;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

//textView customized with defining font
public class GlobalTextView extends TextView {
    public GlobalTextView(Context context) {
        super(context);
        init(context);
    }

    public GlobalTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GlobalTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public GlobalTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {

        //setting font
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/BMitraBd_0.ttf");
        setTypeface(typeface);
    }

}
