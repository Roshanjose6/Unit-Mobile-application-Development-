package com.example.taskmanagerapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.database.Cursor;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;
public class MainActivity extends AppCompatActivity {
    RecyclerView Taskdetailsrecycler;
    AddTaskRecyclerAdapter addTaskRecyclerAdapter;
    List<AddTaskconstr> addTaskconstrs = new ArrayList<>();
    FloatingActionButton addtask;
    DatabaseHelperclass databaseHelperclass;
    Button sortbydate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelperclass = new DatabaseHelperclass(this);
        Taskdetailsrecycler = findViewById(R.id.alltaskrecycler);
        addtask = findViewById(R.id.Addtask);
        LinearLayoutManager taskmanager = new LinearLayoutManager(getApplication());
        taskmanager.setOrientation(RecyclerView.VERTICAL);
        Taskdetailsrecycler.setLayoutManager(taskmanager);
        sortbydate = findViewById(R.id.btnSortByDueDate);
        addtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddTaskDialog(); // Call method to open the dialog box
            }
        });
        addTaskRecyclerAdapter = new AddTaskRecyclerAdapter(getApplicationContext(), addTaskconstrs, this);
        Taskdetailsrecycler.setAdapter(addTaskRecyclerAdapter);
        refreshRecyclerView();
    }
    public void openAddTaskDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.task_adding_dialogbox, null);

        EditText taskTitle = view.findViewById(R.id.addtitle);
        EditText taskDescription = view.findViewById(R.id.adddesc);
        EditText dueDate = view.findViewById(R.id.adddate);
        dueDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
                builder.setTitleText("Select Due Date");

                MaterialDatePicker<Long> picker = builder.build();
                picker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
                    @Override
                    public void onPositiveButtonClick(Long selection) {
                        // Convert the selected date to a formatted string
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis(selection);
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                        String formattedDate = sdf.format(calendar.getTime());

                        // Set the selected date to the EditText field
                        dueDate.setText(formattedDate);
                    }
                });
                picker.show(getSupportFragmentManager(), picker.toString());
            }
        });
        builder.setView(view)
                .setTitle("Add Task")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setPositiveButton("Insert", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String title = taskTitle.getText().toString();
                        String description = taskDescription.getText().toString();
                        String date = dueDate.getText().toString();


                        if (!title.isEmpty() && !description.isEmpty() && !date.isEmpty()) {
                            boolean isInserted = databaseHelperclass.insertdata( title, description, date);
                            if (isInserted) {
                                Toast.makeText(MainActivity.this, "Task added successfully", Toast.LENGTH_SHORT).show();
                                refreshRecyclerView(); // Reload tasks from database
                            } else {
                                Toast.makeText(MainActivity.this, "Failed to add task", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        builder.create().show();
    }
    // Method to refresh RecyclerView with updated data
    void refreshRecyclerView() {
        addTaskconstrs.clear();

        // Fetch data from the database
        Cursor cursor = databaseHelperclass.getall();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String taskid = cursor.getString(cursor.getColumnIndex(DatabaseHelperclass.column1));
                String taskTitle = cursor.getString(cursor.getColumnIndex(DatabaseHelperclass.column2));
                String taskDescription = cursor.getString(cursor.getColumnIndex(DatabaseHelperclass.column3));
                String dueDate = cursor.getString(cursor.getColumnIndex(DatabaseHelperclass.column4));
                addTaskconstrs.add(new AddTaskconstr(taskid,taskTitle, taskDescription, dueDate,null));
            } while (cursor.moveToNext());
            cursor.close();
        }

        // Notify RecyclerView adapter about data changes
        addTaskRecyclerAdapter.notifyDataSetChanged();
    }
    public void sortByDueDate(View view) {
        // Sort the list of tasks by due date
        Collections.sort(addTaskconstrs, new Comparator<AddTaskconstr>() {
            @Override
            public int compare(AddTaskconstr o1, AddTaskconstr o2) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                try {
                    Date date1 = sdf.parse(o1.getDuedate());
                    Date date2 = sdf.parse(o2.getDuedate());
                    return date1.compareTo(date2);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });

        // Notify the adapter about the data changes
        addTaskRecyclerAdapter.notifyDataSetChanged();
    }
}