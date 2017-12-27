package com.example.adams.tradersportal;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;

/**
 * Created by GirishM on 27-12-2017.
 */

public class RandomCircle_ {

    static int get(Context mContext) {
        int returnColor = Color.GRAY;
        int arrayId = mContext.getResources().getIdentifier("color_array", "array",
                mContext.getPackageName());

        if (arrayId != 0) {
            TypedArray colors = mContext.getResources().obtainTypedArray(arrayId);
            int index = (int) (Math.random() * colors.length());
            returnColor = colors.getColor(index, Color.GRAY);
            colors.recycle();
        }
        return returnColor;
    }
}
