package com.example.lostandfoundapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "LostandFound";
    public static final int DB_VERSION = 2;
    public static final String ITEMS_TABLE = "items";
    public static final String C_ID = "id";
    public static final String C_NAME = "name";
    public static final String C_DESCRIPTION = "description";
    public static final String C_LOCATION = "location";
    public static final String C_DATE = "date";
    public static final String C_ITEM_TYPE = "itemtype";
    public static final String C_PHONE = "phone";
    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ITEMS_TABLE = "CREATE TABLE " + ITEMS_TABLE + "("
                + C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + C_NAME + " TEXT,"
                + C_DESCRIPTION + " TEXT,"
                + C_LOCATION + " TEXT,"
                + C_DATE + " TEXT,"
                + C_ITEM_TYPE + " TEXT,"
                + C_PHONE + " TEXT" + ")";
        db.execSQL(CREATE_ITEMS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ITEMS_TABLE);
        onCreate(db);
    }

    public boolean addItem(String name, String description, String location, String date, String itemType, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(C_NAME, name);
        contentValues.put(C_DESCRIPTION, description);
        contentValues.put(C_LOCATION, location);
        contentValues.put(C_DATE, date);
        contentValues.put(C_ITEM_TYPE, itemType);
        contentValues.put(C_PHONE, phone);
        long result = db.insert(ITEMS_TABLE, null, contentValues);
        db.close();
        return result != -1; // returns true if insertion is successful
    }

    public boolean removeItem(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(ITEMS_TABLE, C_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
        return result > 0;
    }

    public Cursor fetchAllItems() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM items", null);
    }
}
