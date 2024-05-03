package com.example.itunesapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class PlaylistAdapter extends CursorAdapter {

    public PlaylistAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Inflate the layout for each list item
        return LayoutInflater.from(context).inflate(R.layout.playlist_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Bind data to the views in each list item
        TextView urlTextView = view.findViewById(R.id.urlTextView);

        // Get the URL from the cursor and set it to the TextView
        String url = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelperClass.COLUMN_URL));
        urlTextView.setText(url);
    }
}
