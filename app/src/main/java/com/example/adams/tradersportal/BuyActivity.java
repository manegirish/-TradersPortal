package com.example.adams.tradersportal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BuyActivity extends AppCompatActivity {

    TextView textview4;
    EditText edittext2;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        button2 = (Button)findViewById(R.id.button2);
        textview4 = (TextView) findViewById(R.id.textView4);
        edittext2 = (EditText) findViewById(R.id.editText2);
        Bundle extras = getIntent().getExtras();
        if (extras.getString("grains") != null) {
            textview4.setText(extras.getString("grains"));
            setTitle(extras.getString("grains"));

            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (edittext2.getText().length() == 0) {
                        Toast mytoast = Toast.makeText(getApplicationContext(), "Field left empty!!", Toast.LENGTH_SHORT);
                        mytoast.show();
                    } else

                    {

                        Intent buyIntent = new Intent(BuyActivity.this, PaymentActivity.class);
                        startActivity(buyIntent);

                    }
                }

            });

        }
    }
}
