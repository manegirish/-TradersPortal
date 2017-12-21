package com.example.adams.tradersportal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SigninActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonsignin;

    private EditText editTextEmail1;
    private EditText editTextPassword1;

    private TextView textViewsignup;

    private ProgressDialog progressDialog1;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        setTitle("Sign In");

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            finish();
            Intent intent3 = new Intent(SigninActivity.this, MainActivity.class);
            startActivity(intent3);
        }

        progressDialog1 = new ProgressDialog(this);

        buttonsignin = (Button) findViewById(R.id.buttonsignin);

        editTextEmail1 = (EditText) findViewById(R.id.editTextEmail1);
        // Add drawable image using java to support all android versions
        VectorDrawableCompat emailDrawableCompat = VectorDrawableCompat.create(getResources(), R.drawable.ic_email_24dp, editTextEmail1.getContext().getTheme());
        editTextEmail1.setCompoundDrawablesRelativeWithIntrinsicBounds(emailDrawableCompat, null, null, null);

        // Add drawable image using java to support all android versions
        editTextPassword1 = (EditText) findViewById(R.id.editTextPassword1);
        VectorDrawableCompat passwordDrawableCompat = VectorDrawableCompat.create(getResources(), R.drawable.ic_password_24dp, editTextPassword1.getContext().getTheme());
        editTextPassword1.setCompoundDrawablesRelativeWithIntrinsicBounds(passwordDrawableCompat, null, null, null);

        textViewsignup = (TextView) findViewById(R.id.textViewsignup);

        buttonsignin.setOnClickListener(this);
        textViewsignup.setOnClickListener(this);
    }

    private void UserLogin() {
        String email = editTextEmail1.getText().toString().trim();
        String password = editTextPassword1.getText().toString().trim();

        if (editTextEmail1.getText().length() == 0) {
            editTextEmail1.setError("Please Enter the Email");
            return;
        }
        if (editTextPassword1.getText().length() == 0) {
            editTextPassword1.setError("Please Enter the Password");
            return;
        }
        progressDialog1.setMessage("Signing In...");
        progressDialog1.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog1.dismiss();
                        if (task.isSuccessful()) {
                            Intent intent2 = new Intent(SigninActivity.this, MainActivity.class);
                            startActivity(intent2);
                            finish();
                        } else {
                            Toast.makeText(SigninActivity.this, "SignIn Failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
    }

    @Override
    public void onClick(View view) {
        if (view == buttonsignin) {
            UserLogin();
        }
        if (view == textViewsignup) {
            finish();
            Intent intent = new Intent(this, SignupActivity.class);
            startActivity(intent);
        }

    }
}
