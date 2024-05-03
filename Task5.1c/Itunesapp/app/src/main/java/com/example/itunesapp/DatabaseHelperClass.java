package com.example.itunesapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelperClass extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ItunesAndroid.db";
    private static final int DATABASE_VERSION = 1;

    // Define your table and column names
    private static final String TABLE_USERS = "users";
    private static final String TABLE_PLAYLIST = "playlist";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_FULLNAME = "fullname";
    private static final String COLUMN_USERNAME = "username";
    protected static final String COLUMN_PASSWORD = "password";
    protected static final String COLUMN_URL = "url";

    private static final String SQL_CREATE_USERS_TABLE =
            "CREATE TABLE " + TABLE_USERS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_FULLNAME + " STRING," +
                    COLUMN_USERNAME + " TEXT," +
                    COLUMN_PASSWORD + " TEXT)";

    public DatabaseHelperClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_USERS_TABLE);
        String SQL_CREATE_PLAYLIST_TABLE =
                "CREATE TABLE " + TABLE_PLAYLIST + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COLUMN_USERNAME + " TEXT," +
                        COLUMN_URL + " TEXT)";
        db.execSQL(SQL_CREATE_PLAYLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public long insertUser(String fullname, String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FULLNAME, fullname);
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);

        return db.insert(TABLE_USERS, null, values);
    }
    public Cursor getUser(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {COLUMN_ID, COLUMN_USERNAME, COLUMN_PASSWORD};
        String selection = COLUMN_USERNAME + " = ?";
        String[] selectionArgs = {username};
        return db.query(TABLE_USERS, projection, selection, selectionArgs, null, null, null);
    }
    // Method to add a URL to the playlist for a specific user
    public long insertPlaylistUrl(String username, String url) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_URL, url);
        return db.insert(TABLE_PLAYLIST, null, values);
    }
    public Cursor getPlaylistItems(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {"id AS _id", COLUMN_URL};
        String selection = COLUMN_USERNAME + " = ?";
        String[] selectionArgs = {username};
        return db.query(TABLE_PLAYLIST, projection, selection, selectionArgs, null, null, null);
    }
}
