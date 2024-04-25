package com.example.taskmanagerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelperclass extends SQLiteOpenHelper {
    public static final String databasename = "TaskManager.db";
    public static final String tablename = "Tasks";
    public static final String column1 = "id";
    public static final String column2 = "tasktitle";
    public static final String column3 = "taskdescription";
    public static final String column4 = "duedate";
    public static final String column5 = "phonenumber";
    public DatabaseHelperclass(@Nullable Context context) {
        super(context, databasename, null, 1);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table Tasks(id integer primary key autoincrement,tasktitle text,taskdescription string,duedate String)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists Tasks");
        onCreate(sqLiteDatabase);
    }
    boolean insertdata(String tasktitle, String taskdescription, String duedate){
        ContentValues contentValues = new ContentValues();
        contentValues.put(column2,tasktitle);
        contentValues.put(column3,taskdescription);
        contentValues.put(column4,duedate);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long row = sqLiteDatabase.insertOrThrow(tablename,null,contentValues);
        if (row == -1){
            return false;
        }else{
            return true;
        }
    }
    Cursor getall(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor s=  sqLiteDatabase.rawQuery("Select * from Tasks",null );
        return  s;

    }
    boolean deleteall(String taskId){
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int row = sqLiteDatabase.delete(tablename, "id = ?", new String[]{taskId});
        if (row == -1){
            return false;
        }else{
            return true;
        }
    }
    boolean updatedata(String id, String tasktitle, String taskdescription, String duedate){
        ContentValues contentValues = new ContentValues();
        contentValues.put(column2, tasktitle);
        contentValues.put(column3, taskdescription);
        contentValues.put(column4, duedate);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int rowsAffected = sqLiteDatabase.update(tablename, contentValues, "id=?", new String[]{id});
        return rowsAffected > 0;
    }
}

