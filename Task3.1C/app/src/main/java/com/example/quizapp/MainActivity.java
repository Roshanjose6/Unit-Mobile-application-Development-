package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName;

     Button buttonstart;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.username);

        buttonstart = findViewById(R.id.submitButton);
        buttonstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editTextName.getText().toString();
                if (text.isEmpty()) {
                    Toast.makeText(MainActivity.this, "please enter a value", Toast.LENGTH_SHORT).show();
                } else {
                    String userName = editTextName.getText().toString();
                    Intent intent = new Intent(MainActivity.this, Quizstartpage.class);
                    intent.putExtra("UserName", userName);
                    startActivity(intent);
                }

            }
        });
    }
}