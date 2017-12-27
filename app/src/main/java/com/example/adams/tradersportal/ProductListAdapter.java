package com.example.adams.tradersportal;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by GirishM on 27-12-2017.
 */

public class ProductListAdapter extends ArrayAdapter<SellProduct_> {

    private final ArrayList<SellProduct_> sellProductArrayList;

    private final Activity activity;

    ProductListAdapter(Activity activity, ArrayList<SellProduct_> sellProductArrayList) {
        super(activity, 0, sellProductArrayList);
        this.activity = activity;
        this.sellProductArrayList = sellProductArrayList;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) activity.getApplicationContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder viewHolder;

        View rowView = convertView;
        if (rowView == null) {
            viewHolder = new ViewHolder();
            assert inflater != null;
            rowView = inflater.inflate(R.layout.product_list_item_layout, parent, false);

            viewHolder.nameText = (TextView) rowView.findViewById(R.id.product_name);
            viewHolder.priceText = (TextView) rowView.findViewById(R.id.product_price);
            viewHolder.backgroundImage = (ImageView) rowView.findViewById(R.id.product_icon_background);

            rowView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) rowView.getTag();
        }
        SellProduct_ sellProduct_ = sellProductArrayList.get(position);

        viewHolder.nameText.setText(sellProduct_.getName());
        viewHolder.priceText.setText(sellProduct_.getPrice());
        viewHolder.backgroundImage.setImageResource(R.drawable.primary_light_rounded_rectangle);
        viewHolder.backgroundImage.setColorFilter(RandomCircle_.get(activity.getApplicationContext()));
        return rowView;
    }

    private class ViewHolder {
        TextView nameText, priceText;
        ImageView backgroundImage;
    }
}
