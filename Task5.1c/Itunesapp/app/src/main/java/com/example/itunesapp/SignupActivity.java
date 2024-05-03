package com.example.itunesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    private DatabaseHelperClass mDatabaseHelper;
    private EditText mFullnameEditext, mUsernameEditText,mPasswordEditText, mconfirmpassword;

    private Button mSignupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signuppage);

        mDatabaseHelper = new DatabaseHelperClass(this);
        mFullnameEditext = findViewById(R.id.Fullname);
        mUsernameEditText = findViewById(R.id.Username);
        mPasswordEditText = findViewById(R.id.password);
        mconfirmpassword = findViewById(R.id.confirmpassword);
        mSignupButton = findViewById(R.id.buttonsignup);

        mSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullname = mFullnameEditext.getText().toString().trim();
                String username = mUsernameEditText.getText().toString().trim();
                String password = mPasswordEditText.getText().toString().trim();

                // Check if username already exists
                if (mDatabaseHelper.getUser(username).moveToFirst()) {
                    Toast.makeText(SignupActivity.this, "Username already exists", Toast.LENGTH_SHORT).show();
                } else {
                    // Insert new user
                    long result = mDatabaseHelper.insertUser(fullname,username, password);
                    if (result != -1) {
                        // User inserted successfully
                        Toast.makeText(SignupActivity.this, "Signup Successful", Toast.LENGTH_SHORT).show();
                        // Proceed to login activity or perform desired action
                    } else {
                        // Error inserting user
                        Toast.makeText(SignupActivity.this, "Error signing up", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}