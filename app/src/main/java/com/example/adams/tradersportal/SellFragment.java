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

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class SellFragment extends Fragment {

    private EditText product, quantity, price, description;
    private Button buttonSell, buttonPhoto;
    private ImageView imageView;

    private static final int PICK_IMAGE = 100;
    private Uri imageUri;

    private Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_sell_main, container, false);

        activity = getActivity();

        product = (EditText) rootView.findViewById(R.id.editTextName);
        SetDrawable.set(activity.getApplicationContext(), R.drawable.ic_product_black_24dp, product);

        quantity = (EditText) rootView.findViewById(R.id.editTextQuantity);
        SetDrawable.set(activity.getApplicationContext(), R.drawable.ic_quantity_black_24dp, quantity);

        price = (EditText) rootView.findViewById(R.id.editTextPrice);
        SetDrawable.set(activity.getApplicationContext(), R.drawable.ic_price_black_24dp, price);

        description = (EditText) rootView.findViewById(R.id.editTextDescription);
        SetDrawable.set(activity.getApplicationContext(), R.drawable.ic_description_black_24dp, description);

        buttonSell = (Button) rootView.findViewById(R.id.buttonsell);

        imageView = (ImageView) rootView.findViewById(R.id.photo);
        buttonPhoto = (Button) rootView.findViewById(R.id.buttonphoto);

        buttonPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == buttonPhoto) {
                    openGallery();
                }
                if (view == buttonSell) {
                    //push details to buy page from here
                }
            }
        });

        return rootView;
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }
}
