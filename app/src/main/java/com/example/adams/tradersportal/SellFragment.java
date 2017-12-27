package com.example.adams.tradersportal;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class SellFragment extends Fragment implements View.OnClickListener {

    private EditText product, price;
    private ImageView imageView;

    private static final int PICK_IMAGE = 100;

    private Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_sell_main, container, false);

        activity = getActivity();

        product = (EditText) rootView.findViewById(R.id.editTextName);
        SetDrawable.set(activity.getApplicationContext(), R.drawable.ic_product_black_24dp, product);
        price = (EditText) rootView.findViewById(R.id.editTextPrice);
        SetDrawable.set(activity.getApplicationContext(), R.drawable.ic_price_black_24dp, price);

        imageView = (ImageView) rootView.findViewById(R.id.photo);

        Button buttonSell = (Button) rootView.findViewById(R.id.buttonsell);
        buttonSell.setOnClickListener(this);
        Button buttonPhoto = (Button) rootView.findViewById(R.id.buttonphoto);
        buttonPhoto.setOnClickListener(this);
        return rootView;
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    public void addSellProduct(String name, String cost) {
        DatabaseHelper databaseHelper = new DatabaseHelper(activity.getApplicationContext());
        boolean result = databaseHelper.insertSellProduct(name, cost);
        if (result) {
            product.setText("");
            price.setText("");
            Toast.makeText(getActivity(), "Product has been uploaded successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            Uri imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonphoto:
                openGallery();
                break;
            case R.id.buttonsell:
                String productName = product.getText().toString();
                String productPrice = price.getText().toString();
                if ((product.length() != 0) && (price.length() != 0)) {
                    addSellProduct(productName, productPrice);
                } else {
                    Toast.makeText(getActivity(), "You must put something in the text field", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
