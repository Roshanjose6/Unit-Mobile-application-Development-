package com.example.itunesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class homepage extends AppCompatActivity {
    private EditText mYoutubeUrlEditText;
    private Button mPlayButton, addUrlButton, myplaylistbutton;
    private String username;
    DatabaseHelperClass mDatabaseHelper;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabaseHelper = new DatabaseHelperClass(this);
        setContentView(R.layout.activity_homepage);
        mYoutubeUrlEditText = findViewById(R.id.youtubeurl);
        mPlayButton = findViewById(R.id.Playbutton);
        myplaylistbutton = findViewById(R.id.myplaylist);
        addUrlButton = findViewById(R.id.Addtoplaylist);
        username = getIntent().getStringExtra("username");
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the YouTube video URL from the EditText
                String youtubeUrl = mYoutubeUrlEditText.getText().toString().trim();

                // Check if the URL is not empty
                if (!youtubeUrl.isEmpty()) {
                    // Redirect to VideoPlayActivity and pass the YouTube video URL
                    Intent intent = new Intent(homepage.this, Videoplaypage.class);
                    intent.putExtra("youtubeUrl", youtubeUrl);
                    startActivity(intent);
                }
            }
        });

        addUrlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = mYoutubeUrlEditText.getText().toString().trim();
                if (!TextUtils.isEmpty(url)) {
                    long inserted = mDatabaseHelper.insertPlaylistUrl(username, url);
                    if (inserted != -1) {
                        Toast.makeText(homepage.this, "URL added to playlist", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(homepage.this, "Failed to add URL to playlist", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(homepage.this, "Please enter a URL", Toast.LENGTH_SHORT).show();
                }
            }
        });
        myplaylistbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to MyPlaylistPage
                Intent intent = new Intent(homepage.this, Myplaylistpage.class);
                intent.putExtra("username", username); // Pass the username
                startActivity(intent);
            }
        });
    }
}