package com.example.adams.tradersportal;

import android.content.Context;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.widget.EditText;

/**
 * Created by GirishM on 21-12-2017.
 */

class SetDrawable {

    // Convert vector image to vector drawable
    static void set(Context context, int drawableId, EditText editText){
        VectorDrawableCompat drawableCompat= VectorDrawableCompat.create(context.getResources(),
                drawableId, editText.getContext().getTheme());
        editText.setCompoundDrawablesRelativeWithIntrinsicBounds(drawableCompat, null, null, null);
    }
}
