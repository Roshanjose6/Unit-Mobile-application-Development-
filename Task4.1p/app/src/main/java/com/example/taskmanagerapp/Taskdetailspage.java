package com.example.taskmanagerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Locale;

public class Taskdetailspage extends AppCompatActivity {
    private EditText editTaskTitle, editTaskDescription;
    TextInputEditText editTaskDueDate;
    private Button btnUpdate;
    MainActivity mainActivity;
    DatabaseHelperclass databaseHelperclass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taskdetailspage);
        databaseHelperclass = new DatabaseHelperclass(this);
        editTaskTitle = findViewById(R.id.updatetitle);
        editTaskDescription = findViewById(R.id.updatedesc);
        editTaskDueDate = findViewById(R.id.updatedate);
        btnUpdate = findViewById(R.id.updatebutton);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Intent intent = getIntent();
        String taskId = getIntent().getStringExtra("taskId");
        if (intent != null) {
            String taskTitle = intent.getStringExtra("taskTitle");
            String taskDescription = intent.getStringExtra("taskDescription");
            String taskDueDate = intent.getStringExtra("taskDueDate");

            editTaskTitle.setText(taskTitle);
            editTaskDescription.setText(taskDescription);
            editTaskDueDate.setText(taskDueDate);

        }
        editTaskDueDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
                builder.setTitleText("Select Due Date");

                MaterialDatePicker<Long> picker = builder.build();
                picker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
                    @Override
                    public void onPositiveButtonClick(Long selection) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis(selection);

                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                        String formattedDate = sdf.format(calendar.getTime());
                        editTaskDueDate.setText(formattedDate);
                    }
                });
                picker.show(getSupportFragmentManager(), picker.toString());
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                String updatedTitle = editTaskTitle.getText().toString();
                String updatedDescription = editTaskDescription.getText().toString();
                String updatedDueDate = editTaskDueDate.getText().toString();


                if (updatedTitle.isEmpty() || updatedDescription.isEmpty() || updatedDueDate.isEmpty()) {
                    Toast.makeText(Taskdetailspage.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return; 
                }


                if (databaseHelperclass == null) {
                    Toast.makeText(Taskdetailspage.this, "Database helper is not initialized", Toast.LENGTH_SHORT).show();
                    return; 
                }


                if (taskId == null) {
                    Toast.makeText(Taskdetailspage.this, "Task ID is null", Toast.LENGTH_SHORT).show();
                    return; 
                }

                boolean isUpdated = databaseHelperclass.updatedata(taskId, updatedTitle, updatedDescription, updatedDueDate);

                if (isUpdated) {

                    Toast.makeText(Taskdetailspage.this, "Task updated successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Taskdetailspage.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Taskdetailspage.this, "Failed to update task", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}