package com.example.adams.tradersportal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
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

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonsignup;

    private EditText editTextName;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextContact;

    private TextView textViewsignin;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        setTitle("Sign Up");

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null)
        {
            finish();
            Intent intent2 = new Intent(SignupActivity.this,MainActivity.class);
            startActivity(intent2);
        }

        progressDialog = new ProgressDialog(this);

        buttonsignup = (Button) findViewById(R.id.buttonsignup);

        editTextContact = (EditText) findViewById(R.id.editTextContact);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);


        textViewsignin = (TextView) findViewById(R.id.textViewsignin);

        buttonsignup.setOnClickListener(this);
        textViewsignin.setOnClickListener(this);
    }

    private void registerUser()
    {
        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String contact = editTextContact.getText().toString().trim();

        if (editTextName.getText().length() == 0)
        {
            editTextName.setError("Please Enter the Name");
            return;
        }
        if (editTextEmail.getText().length() == 0)
        {
            editTextEmail.setError("Please Enter the Email");
            return;
        }
        if (editTextContact.getText().length() == 0)
        {
            editTextContact.setError("Please Enter the Contact Number");
            return;
        }
        if (editTextPassword.getText().length() == 0)
        {
            editTextPassword.setError("Please Enter the Password");
            return;
        }


        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Intent intent1 = new Intent(SignupActivity.this,MainActivity.class);
                            startActivity(intent1);
                        }
                        else
                        {
                            Toast.makeText(SignupActivity.this, "Registration Failed",Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }


                });
    }

    @Override
    public void onClick(View view) {
        if (view == buttonsignup)
        {
            registerUser();
        }
        if (view ==textViewsignin)
        {
            Intent intent3 = new Intent(this,SigninActivity.class);
            startActivity(intent3);
        }
    }
}
