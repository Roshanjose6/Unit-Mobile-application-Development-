package com.example.itunesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelperClass mDatabaseHelper;
    private Button singupbutton, loginbutton;
    private EditText tlusernameeditext, lpasswordedittext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabaseHelper = new DatabaseHelperClass(this);
        tlusernameeditext = findViewById(R.id.loginUsername);
        lpasswordedittext = findViewById(R.id.loginPassword);
        loginbutton = findViewById(R.id.loginbutton);
        singupbutton = findViewById(R.id.signupbutton);
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get username and password from EditText fields
                String username = tlusernameeditext.getText().toString().trim();
                String password = lpasswordedittext.getText().toString().trim();

                // Check if username and password are not empty
                if (!username.isEmpty() && !password.isEmpty()) {
                    // Attempt to fetch user from the database
                    Cursor cursor = mDatabaseHelper.getUser(username);
                    if (cursor != null && cursor.moveToFirst()) {
                        // User exists
                        String storedPassword = cursor.getString(cursor.getColumnIndex(DatabaseHelperClass.COLUMN_PASSWORD));
                        if (password.equals(storedPassword)) {
                            // Passwords match, login successful
                            Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, homepage.class);
                            intent.putExtra("username", username);
                            startActivity(intent);
                        } else {
                            // Passwords do not match
                            Toast.makeText(MainActivity.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // User does not exist
                        Toast.makeText(MainActivity.this, "User does not exist", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Username or password is empty
                    Toast.makeText(MainActivity.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
                }
            }
        });
        singupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}