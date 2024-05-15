package com.example.chatbot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private Button Gobutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernameEditText = findViewById(R.id.musername);
        Gobutton = findViewById(R.id.go);
        Gobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = usernameEditText.getText().toString();


                Intent intent = new Intent(MainActivity.this, Chatpage.class);
                intent.putExtra("USERNAME", username);
                startActivity(intent);
            }
        });
    }
}