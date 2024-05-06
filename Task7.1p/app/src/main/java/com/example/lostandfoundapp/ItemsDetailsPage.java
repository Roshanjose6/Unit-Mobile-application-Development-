package com.example.lostandfoundapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ItemsDetailsPage extends AppCompatActivity {
    TextView itemname,itemdate,itemloc,itemtype;
    Button deletebutton;
    DataBaseHelper databasehelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_details_page);
        databasehelper = new DataBaseHelper(this);
        Intent intent = getIntent();
        String item_id= intent.getStringExtra("item_id");
        String item_name= intent.getStringExtra("item_name");
        String item_date= intent.getStringExtra("item_date");
        String item_loc= intent.getStringExtra("item_loc");
        String item_type= intent.getStringExtra("item_type");
        itemname= findViewById(R.id.name_of_item);
        itemdate= findViewById(R.id.lf_date);
        itemloc= findViewById(R.id.lf_location);
        itemtype= findViewById(R.id.type_of_item);
        itemname.setText(item_name);
        itemtype.setText(item_type);
        itemloc.setText(item_loc);
        itemdate.setText(item_date);
        deletebutton=findViewById(R.id.button_delete);
        deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean deleteSuccessful = databasehelper.removeItem(item_id);
                if (deleteSuccessful) {
                    Toast.makeText(ItemsDetailsPage.this, "Item deleted successfully!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(ItemsDetailsPage.this, ShowAllAddspage.class);
                    startActivity(i);
                }
            }

        });
    }
}