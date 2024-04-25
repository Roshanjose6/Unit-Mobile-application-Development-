package com.example.taskmanagerapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AddTaskRecyclerAdapter extends RecyclerView.Adapter<AddTaskRecyclerAdapter.addtaskholder> {
    private Context mcontext;
    LayoutInflater inflater;
    List<AddTaskconstr> addTaskconstrsss;
    DatabaseHelperclass databaseHelperclass;
    MainActivity mainActivity;

    public AddTaskRecyclerAdapter(Context context, List<AddTaskconstr> addTaskconstrList, MainActivity mainActivity) {
        this.mcontext = context;
        this.inflater = LayoutInflater.from(context);
        this.addTaskconstrsss = addTaskconstrList;
        this.databaseHelperclass = new DatabaseHelperclass(context);
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public addtaskholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.task_details_cardview, null);
        addtaskholder addtaskholder = new addtaskholder(view);
        return addtaskholder;
    }

    @Override
    public void onBindViewHolder(@NonNull AddTaskRecyclerAdapter.addtaskholder holder, int position) {

        holder.title.setText(addTaskconstrsss.get(position).getTasktitle());
        holder.description.setText(addTaskconstrsss.get(position).getDescription());
        holder.duedate.setText(addTaskconstrsss.get(position).getDuedate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddTaskconstr task = addTaskconstrsss.get(position);

                Intent intent = new Intent(mcontext, Taskdetailspage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("taskId", task.getId());
                intent.putExtra("taskTitle", task.getTasktitle());
                intent.putExtra("taskDescription", task.getDescription());
                intent.putExtra("taskDueDate", task.getDuedate());
                mcontext.startActivity(intent);
            }
        });

        holder.deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String taskId = addTaskconstrsss.get(position).getId();


                boolean isDeleted = databaseHelperclass.deleteall(taskId);


                if (isDeleted) {

                    addTaskconstrsss.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, addTaskconstrsss.size());


                    Toast.makeText(mcontext, "Task deleted successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mcontext, "Failed to delete task", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return addTaskconstrsss.size();
    }

    public class addtaskholder extends RecyclerView.ViewHolder {
        TextView id,title,description,duedate;
        ImageButton deletebutton;
        public addtaskholder(@NonNull View taskview) {
            super(taskview);
            title = taskview.findViewById(R.id.tasktitle);
            description = taskview.findViewById(R.id.description);
            duedate = taskview.findViewById(R.id.duedate);
            deletebutton = taskview.findViewById(R.id.deletebutton);
        }
    }
}