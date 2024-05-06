package com.example.lostandfoundapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Locale;

public class NewAdd extends AppCompatActivity {
    EditText name,phone,location,date,desc;
    Button buttonsave;
    RadioGroup radioGroup;
    DataBaseHelper databasehelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_add);
        buttonsave=findViewById(R.id.save);
        name=findViewById(R.id.name);
        phone=findViewById(R.id.phone);
        desc=findViewById(R.id.desc);
        date=findViewById(R.id.lf_date);
        location=findViewById(R.id.location);
        radioGroup = findViewById(R.id.radio_group);
        databasehelper= new DataBaseHelper(this);
        date.setInputType(InputType.TYPE_NULL);
        date.setFocusable(false);
        RadioButton radioButton1 = findViewById(R.id.radioButtonlost);
        RadioButton radioButton2 = findViewById(R.id.radioButtonfound);
        @SuppressLint("ResourceType") ColorStateList colorStateList = AppCompatResources.getColorStateList(this, R.drawable.radiobuttonselectioncolor);

        if (colorStateList != null) {
            radioButton1.setButtonTintList(colorStateList);
            radioButton2.setButtonTintList(colorStateList);
        }

        buttonsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    insertItemIntoDatabase();
                } else {
                    Toast.makeText(NewAdd.this, "PLEASE FILL IN ALL THE REQUIRED INPUT FIELDS", Toast.LENGTH_SHORT).show();
                }
            }

        });
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
    }
    private boolean validateFields() {
        // Check if any of the fields is empty
        return !name.getText().toString().isEmpty() &&
                !phone.getText().toString().isEmpty() &&
                !desc.getText().toString().isEmpty() &&
                !date.getText().toString().isEmpty() &&
                !location.getText().toString().isEmpty();
    }
    private void insertItemIntoDatabase() {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedId);
        String type = selectedRadioButton.getText().toString();
        String nameValue = name.getText().toString();
        String phoneValue = phone.getText().toString();
        String descValue = desc.getText().toString();
        String dateValue = date.getText().toString();
        String locationValue = location.getText().toString();
        boolean inserted = databasehelper.addItem(nameValue, descValue, locationValue, dateValue, type,phoneValue);
        if (inserted) {
            Toast.makeText(NewAdd.this, "ITEM ADDED", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(NewAdd.this, "FAILED", Toast.LENGTH_SHORT).show();
        }
        Intent i = new Intent(NewAdd.this, MainActivity.class);
        startActivity(i);
    }
    private void showDatePickerDialog() {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Using SimpleDateFormat to format the date.
                        Calendar dateCal = Calendar.getInstance();
                        dateCal.set(Calendar.YEAR, year);
                        dateCal.set(Calendar.MONTH, monthOfYear);
                        dateCal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
                        date.setText(dateFormat.format(dateCal.getTime()));
                    }
                }, year, month, day);
        datePickerDialog.show();
    }
}