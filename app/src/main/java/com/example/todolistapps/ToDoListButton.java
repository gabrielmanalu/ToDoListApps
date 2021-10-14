package com.example.todolistapps;

import android.content.Context;

import androidx.appcompat.widget.AppCompatButton;

import com.example.todolistapps.model.ToDoList;

public class ToDoListButton extends AppCompatButton {

    private ToDoList mToDoList;
    private int id;

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

    public int getToDoListID(){ return mToDoList.getToDoListID(); }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getToDoListName() { return  mToDoList.getToDoListName(); }

}
