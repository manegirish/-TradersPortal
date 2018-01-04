package com.example.adams.tradersportal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BuyNowActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText quantityBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_now);

        TextView nameText = (TextView) findViewById(R.id.textView3);
        TextView priceText = (TextView) findViewById(R.id.textView5);

        Button buyButton = (Button) findViewById(R.id.button2);
        buyButton.setOnClickListener(this);

        quantityBox = (EditText) findViewById(R.id.editText2);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("name")) {
            String name = "Product Name : " + intent.getStringExtra("name");
            String price = "Product Price : " + intent.getStringExtra("price");

            nameText.setText(name);
            priceText.setText(price);
        } else {
            Toast.makeText(getApplicationContext(), "Error Occurred!!", Toast.LENGTH_SHORT).show();
            //this will close activity
            onBackPressed();
        }


/*        if (name != null && price != null) {

            textview4.setText(name);
            textView6.setText(price);

            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (edittext2.getText().length() == 0) {
                        Toast mytoast = Toast.makeText(getApplicationContext(), "Field left empty!!", Toast.LENGTH_SHORT);
                        mytoast.show();
                    } else

                    {



                    }
                }

            });

        }*/
    }

    @Override
    public void onClick(View view) {
        String quantity = quantityBox.getText().toString().trim();
        if (quantity.length() <= 0) {
            Toast.makeText(getApplicationContext(), "Field left empty!!", Toast.LENGTH_SHORT).show();
        } else {
            Intent buyIntent = new Intent(BuyNowActivity.this, PaymentActivity.class);
            startActivity(buyIntent);
        }
    }
}
