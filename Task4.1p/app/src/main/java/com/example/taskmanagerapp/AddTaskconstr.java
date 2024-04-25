package com.example.taskmanagerapp;

import android.widget.Button;
import android.widget.ImageButton;

public class AddTaskconstr{
    public ImageButton getDeletebutton() {
        return deletebutton;
    }

    public void setDeletebutton(ImageButton deletebutton) {
        this.deletebutton = deletebutton;
    }

    private ImageButton deletebutton;

    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String tasktitle;
    String description;
    String duedate;
    public AddTaskconstr(String id, String tasktitle, String description, String duedate, ImageButton deletebutton) {
        this.id = id;
        this.tasktitle = tasktitle;
        this.description = description;
        this.duedate = duedate;
        this.deletebutton = deletebutton;
    }
    public String getTasktitle() {
        return tasktitle;
    }

    public void setTasktitle(String tasktitle) {
        this.tasktitle = tasktitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }
}
