package com.example.todolistapps;

import android.content.Context;

import androidx.appcompat.widget.AppCompatButton;

import com.example.todolistapps.model.ToDoList;

public class ToDoListButton extends AppCompatButton {

    private ToDoList mToDoList;

    public ToDoListButton(Context context, ToDoList toDoList){
        super(context);
        mToDoList = toDoList;
    }


    public String getToDoListDeadline() {
        return mToDoList.getToDoListDeadline();
    }

    public String getToDoListPriority() {
        return mToDoList.getToDoListPriority();
    }

}
