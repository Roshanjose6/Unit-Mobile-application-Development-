package com.example.lostandfoundapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonNewAdd = findViewById(R.id.create_new_ad);
        Button buttonShowAll = findViewById(R.id.show_all_ads);
        Button buttonshowmap = findViewById(R.id.show_on_map);
        buttonNewAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewAdd.class);
                startActivity(intent);
            }
        });

        buttonShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowAllAddspage.class);
                startActivity(intent);
            }
        });
        buttonshowmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ShowOnMap.class);
                    startActivity(i);
            }
        });
    }
}