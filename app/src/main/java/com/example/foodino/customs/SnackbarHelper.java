package com.example.foodino.customs;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.view.View;
import android.widget.TextView;

import androidx.core.view.ViewCompat;

import com.example.foodino.R;
import com.google.android.material.snackbar.Snackbar;

/*class for customizing received snackBar
    change text style, border and text direction
 */
public class SnackbarHelper {

    public static void configSnackBar(Context context, Snackbar snackbar){

        setSnackBarText(snackbar);
        setRoundBorder(context, snackbar);
        setToRTL(snackbar);

    }

    private static void setSnackBarText(Snackbar snackbar) {

        snackbar.setActionTextColor(Color.rgb(255,100,100));

        View sbView = snackbar.getView();

        TextView sbText = sbView.findViewById(com.google.android.material.R.id.snackbar_text);
        sbText.setTextSize(18);

        TextView sbAction = sbView.findViewById(com.google.android.material.R.id.snackbar_action);
        sbAction.setTextSize(17);
        sbAction.setTypeface(sbAction.getTypeface(), Typeface.BOLD);

    }

    private static void setToRTL(Snackbar snackbar) {

        ViewCompat.setLayoutDirection(snackbar.getView(), ViewCompat.LAYOUT_DIRECTION_RTL);
    }

    private static void setRoundBorder(Context context, Snackbar snackbar) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            snackbar.getView().setBackground(context.getDrawable(R.drawable.snackbar_shape));

        }
    }
}
