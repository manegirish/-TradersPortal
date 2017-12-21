package com.example.adams.tradersportal;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

public class ProductDetails extends AppCompatActivity {

    TextView productname, productprice, productdescription;
    ImageView productimage;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton buttoncart;
    ElegantNumberButton numberButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
    }
}
