package com.example.foodino.customs;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//setting space between items in recyclerView when layoutManagerType is GridLayoutManager
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    int layoutManagerType;
    private int space = 5;

    private static int LinearLayoutManger = 0;
    private static int GridLayoutManager = 1;


    public SpaceItemDecoration(int layoutManagerType) {
        this.layoutManagerType = layoutManagerType;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        if(layoutManagerType == GridLayoutManager){
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;

            if (parent.getChildLayoutPosition(view) == 0 || parent.getChildLayoutPosition(view) == 1) {
                outRect.top = space;
            } else {
                outRect.top = 0;
            }
        }
    }
}
