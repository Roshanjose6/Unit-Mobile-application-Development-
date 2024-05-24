package com.example.lostandfoundapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ShowAllAddspage extends AppCompatActivity {
    RecyclerView listofitemsrecycle;
    List<ItemModel> itemmodellist = new ArrayList<>();
    ListItemAdapter  listitemadapter;
    DataBaseHelper databasehelper;
    TextView no_item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_addspage);
        listofitemsrecycle = findViewById(R.id.list_of_all_items);
        databasehelper = new DataBaseHelper(this);
        LinearLayoutManager lm = new LinearLayoutManager(getApplication());
        lm.setOrientation(RecyclerView.VERTICAL);
        listofitemsrecycle.setLayoutManager(lm);
        listitemadapter = new ListItemAdapter(getApplicationContext(), itemmodellist);
        listofitemsrecycle.setAdapter(listitemadapter);
        Cursor c= databasehelper.fetchAllItems();
        if (c.getCount()==0){
            Toast.makeText(getApplicationContext(),"EMPTY LIST",Toast.LENGTH_SHORT).show();
        }else{
            while (c.moveToNext()){
                ItemModel i=new ItemModel(c.getString(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6));
                itemmodellist.add(i);
            }
            listitemadapter.notifyDataSetChanged();
        }
    }
}