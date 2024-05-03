package com.example.itunesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class Myplaylistpage extends AppCompatActivity {
    private ListView mListView;
    private PlaylistAdapter mAdapter;
    private TextView textView;
    private DatabaseHelperClass mDatabaseHelper;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myplaylistpage);
        textView = findViewById(R.id.textView2);
        mListView = findViewById(R.id.mylist);
        mDatabaseHelper = new DatabaseHelperClass(this);
        username = getIntent().getStringExtra("username");

        populatePlaylist();
    }

    private void populatePlaylist() {
        Cursor cursor = mDatabaseHelper.getPlaylistItems(username);
        if (cursor.getCount() == 0) {
            textView.setVisibility(View.VISIBLE); // Show the TextView
            mListView.setVisibility(View.GONE);
            textView.setText("No playlist add some playlist items");// Hide the ListView
        } else {
            mAdapter = new PlaylistAdapter(this, cursor);
            mListView.setAdapter(mAdapter);
            textView.setVisibility(View.GONE); // Hide the TextView
            mListView.setVisibility(View.VISIBLE); // Show the ListView
        }
    }
}