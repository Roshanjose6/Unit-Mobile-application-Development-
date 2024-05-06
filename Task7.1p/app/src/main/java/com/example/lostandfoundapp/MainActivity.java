package com.example.lostandfoundapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void launchNewAddPage(View view) {
        Intent intent = new Intent(MainActivity.this, NewAdd.class);
        startActivity(intent);
    }

    public void launchShowAllPage(View view) {
        Intent intent = new Intent(MainActivity.this, ShowAllAddspage.class);
        startActivity(intent);
    }
}